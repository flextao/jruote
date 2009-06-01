
import static org.fluentjava.FluentUtils.as;
import static org.fluentjava.FluentUtils.list;

import java.util.Formatter;

import com.flextao.jruote.WorkItemAdapter;
import com.google.inject.Inject;

public class WorkItemFields {
    private static final int A_MOMENT = 100;
    private static final int TEN_SECS = 10 * 1000;
    // when you update the following 2 const values, you should also change same
    // values inside process definitions.
    private static final String ENTITY_ID = "entity_id";
    private static final String ENTITY_TYPE = "entity_type";
    private static final String LAUNCHER = "launcher";

    public static String join(Collection<?> collections, String sep) {
        StringBuffer result = new StringBuffer();
        for (Iterator<?> iter = collections.iterator(); iter.hasNext();) {
            result.append(iter.next());
            if (iter.hasNext()) {
                result.append(sep);
            }
        }
        return result.toString();
    }

    @Inject
    private HibernateEntityManager manager;

    public String asVariables(Entity entity, String username) {
        String variable = "'%s' => '%s'";
        Formatter entityId = new Formatter().format(variable, ENTITY_ID, entity.getId());
        Formatter entityType = new Formatter().format(variable, ENTITY_TYPE, entity.getClass()
                .getName());
        Formatter launcher = new Formatter().format(variable, LAUNCHER, username);
        return join(list(entityId, entityType, launcher), ", ");
    }

    public Entity entity(WorkItemAdapter workitem) {
        String id = as(workitem.getAttribute(ENTITY_ID));
        Class<?> type = ClassHelper.forName((String) workitem.getAttribute(ENTITY_TYPE));
        return loadEntity(workitem, id, type);
    }

    // for we are running in different thread with creating the Entity code
    // so that we have to wait until the entity is commited into database.
    private Entity loadEntity(WorkItemAdapter workitem, String id, Class<?> type) {
        long timeout = TEN_SECS;
        long startAt = System.currentTimeMillis();
        do {
            Entity entity = (Entity) manager.load(type, id);
            if (entity != null) {
                return entity;
            }
            boolean isTimeout = System.currentTimeMillis() - startAt > timeout;
            if (isTimeout) {
                throw new IllegalArgumentException("Couldn't find entity by type[" + type
                        + "] and id[" + id + "], workitem is " + workitem);
            }
            ThreadHelper.sleep(A_MOMENT);
        } while (true);
    }

    public User launcher(WorkItemAdapter workitem) {
        String username = as(workitem.getAttribute(LAUNCHER));
        // here, loadByUsername is not real interface of the HibernateEntityManager
        // get it right in your case.
        User launcher = manager.loadByUsername(User.class, username);
        if (launcher == null) {
            throw new BusinessException("couldn't find user by [username = " + username + "]");
        }
        return launcher;
    }
}
