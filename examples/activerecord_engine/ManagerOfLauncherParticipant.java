
import org.jruby.runtime.builtin.IRubyObject;

import com.flextao.jruote.ArParticipant;
import com.flextao.jruote.WorkItemAdapter;

// an ArParticipant is an adapter of ar_participant class in ruote.
// after consumed the participant, the process would be pause until
// you call Engine#reply asynchronizly with the Entity need process.
public class ManagerOfLauncherParticipant extends ArParticipant {

    // you need wrap the action in a transaction which is out of your
    // web application's transaction for it's called from ruote in jruby.
    @WithTransaction
    public void cancel(IRubyObject workitem) {
        Entity entity = detectEntity(workitem);
        EntityService.getInstance().cancel(entity);
        super.cancelArParticipant(entity.getId(), workitem);
    }

    @WithTransaction
    public void consume(IRubyObject workitem) {
        Entity entity = detectEntity(workitem);
        User launcher = itemFields().launcher(new WorkItemAdapter(workitem));

        // business process, do you need to do....

        super.consumeArParticipant(entity.getId(), workitem);
    }

    private Entity detectEntity(IRubyObject workitem) {
        WorkItemAdapter itemAdapter = new WorkItemAdapter(workitem);
        return (Entity) itemFields().entity(itemAdapter);
    }

    private WorkItemFields itemFields() {
        return Context.injector().getInstance(WorkItemFields.class);
    }

}
