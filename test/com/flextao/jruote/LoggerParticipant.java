/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import java.util.HashMap;
import java.util.Map;

import org.jruby.runtime.builtin.IRubyObject;

import com.flextao.jruote.BasicParticipant;

public class LoggerParticipant extends BasicParticipant {
    public static String log = "";
    public static Map<String, IRubyObject> workitems = new HashMap<String, IRubyObject>();
    public static void clear() {
        log = "";
        workitems.clear();
    }

    private final String name;

    public LoggerParticipant(String name) {
        this.name = name;
    }

    public void consume(IRubyObject workitem) {
        log += "consumed participant[" + this.name + "];";
        workitems.put(name, workitem);
    }

    @Override
    public void cancel(IRubyObject workitem) {
        log += "canceled participant[" + this.name + "];";
    }

}
