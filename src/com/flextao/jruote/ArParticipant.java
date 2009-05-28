/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * ActiveRecord Participant helper class. AcitveRecord Participant could extend this class
 * for getting abilities to consume/cancel an ArParticipant by storeName and workitem.
 */
public abstract class ArParticipant implements Participant {

    protected void consumeArParticipant(String storeName, IRubyObject workitem) {
        callArParticipant(storeName, "consume", workitem);
    }

    protected void cancelArParticipant(String storeName, IRubyObject workitem) {
        callArParticipant(storeName, "cancel", workitem);
    }

    protected void callArParticipant(String storeName, String method, IRubyObject workitem) {
        Ruby runtime = workitem.getRuntime();
        newArParticipant(runtime, storeName).callMethod(runtime.getCurrentContext(), method, workitem);
    }

    public boolean doNotThread() {
        return false;
    }

    private synchronized IRubyObject newArParticipant(Ruby runtime, String storeName) {
        runtime.getLoadService().require("openwfe/extras/participants/ar_participants");
        StringBuffer script = new StringBuffer("OpenWFE::Extras::ArParticipant.new(");
        if (storeName != null) {
            script.append("'");
            script.append(storeName);
            script.append("'");
        }
        script.append(")");
        return runtime.evalScriptlet(script.toString());
    }

}
