/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import java.util.Map;

import org.jruby.runtime.builtin.IRubyObject;

/**
 * WorkItem<IRubyObject> adapter for easy access to attributes/fields of
 * workitem object.
 */
public class WorkItemAdapter {
    private final JRubyObject workitem;

    public WorkItemAdapter(IRubyObject workitem) {
        this.workitem = new JRubyObject(workitem);
    }

    public String participantName() {
        return workitem.send("participant_name");
    }

    public String getWfid() {
        return new JRubyObject(fei()).send("wfid");
    }

    private IRubyObject fei() {
        return (IRubyObject) workitem.send("flow_expression_id");
    }

    public void setAttribute(String name, Object value) {
        getAttributes().put(name, value);
    }

    public Object getAttribute(String name) {
        return getAttributes().get(name);
    }

    public Map<String, Object> getAttributes() {
        return this.workitem.send("attributes");
    }

    @Override
    public String toString() {
        return this.workitem.toString();
    }
}
