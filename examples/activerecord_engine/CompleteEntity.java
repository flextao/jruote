import org.jruby.runtime.builtin.IRubyObject;

import com.flextao.jruote.BasicParticipant;
import com.flextao.jruote.WorkItemAdapter;

// a simple participant is registered as sync participant in Engine.java.
// a sync participant would be consumed directly in the process without
// waiting for anyone's reply.
public class CompleteEntity extends BasicParticipant {

    // this participant shows you how to do some separated job after an ArParticipant.
    // 
    @WithTransaction
    public void consume(IRubyObject workitem) {
        Entity taskable = (Entity) itemFields().entity(new WorkItemAdapter(workitem));
        Entity.complete();
    }

    private WorkItemFields itemFields() {
        return Context.injector().getInstance(WorkItemFields.class);
    }

}
