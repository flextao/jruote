import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.transaction.Synchronization;

import com.flextao.jruote.EngineAdapter;
import com.flextao.jruote.models.ArWorkitem;
import com.google.inject.Singleton;

@Singleton
public class Engine {
    private static final int HALF_SEC = 500;

    public static void setWorkDir(String path) {
        System.setProperty("engine_work_dir", path);
    }

    public static void setDatabaseName(String name) {
        System.setProperty("database_name", name);
    }

    private static long waitTimeAfterTransactionCompleted = HALF_SEC;

    public static void setWaitTimeAfterTransactionCompleted(int time) {
        waitTimeAfterTransactionCompleted = time;
    }

    private EngineAdapter engine;

    public Engine() {
        loadEngine();
        registerParticipants();
        engine.ready();
    }

    public void launch(URL processDefinition, Entity entity, User user) {
        String variables = itemFields().asVariables(entity, user.getUsername());
        engine.launch(processDefinition, variables);
        appendWaitToTransactionComplete();
    }

    public void reply(Entity entity) {
        String storeName = entity.relatedEntity().getId();
        // you should create ArWorkItemService by your self, which is just a lookup service of ArWorkItem
        List<ArWorkitem> items = ArWorkItemService.getInstance().listByStoreName(storeName);
        for (ArWorkitem item : items) {
            engine.reply(item);
        }
        appendWaitToTransactionComplete();
    }

    public void cancel(Entity entity) {
        String storeName = entity.relatedEntity().getId();
        List<ArWorkitem> items = ArWorkItemService.getInstance().listByStoreName(storeName);
        for (ArWorkitem item : items) {
            engine.cancelProcess(item);
        }
        appendWaitToTransactionComplete();
    }

    /**
     * Don't call it, unless you really want to do it
     */
    public void stop() {
        engine.stop();
    }

    /**
     * we should wait a moment for ruote background process catch up the work.
     * you don't have to do this if you don't need the process result directly
     * see ruote document for more info.
     * the following code is depending on Hibernate Transaction interface.
     */
    private void appendWaitToTransactionComplete() {
         //'Context.injector()' returns a com.google.inject.Injector instance
        Context.injector().getInstance(Session.class).getTransaction().registerSynchronization(new Synchronization() {
            public void afterCompletion(int arg0) {
                ThreadHelper.sleep(waitTimeAfterTransactionCompleted);
            }

            public void beforeCompletion() {
            }
        });
    }

    private WorkItemFields itemFields() {
        //'Context.injector()' returns a com.google.inject.Injector instance
        return Context.injector().getInstance(WorkItemFields.class);
    }

    private void loadEngine() {
        InputStream engineDefinition = getClass().getResourceAsStream("/engine.rb");
        try {
            engine = new EngineAdapter(engineDefinition);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //it looks like a bug of activerecord, always gget error in multi-threads environment while loading AssociationCollection
        engine.require("active_record/associations/association_collection");
    }

    private void registerParticipants() {
        engine.registerParticipant("manager-of-launcher", Context.injector().getInstance(ManagerOfRequestOwnerParticipant.class));
        engine.registerSyncParticipant("complete-entity", Context.injector().getInstance(CompleteEntity.class));
    }

}
