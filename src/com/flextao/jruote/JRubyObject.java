/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import static org.jruby.javasupport.JavaEmbedUtils.invokeMethod;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

public class JRubyObject {

    public static JRubyObject load(Ruby runtime, String dump) {
        return new JRubyObject(((IRubyObject) marshalClass(runtime).send("load", dump)));
    }

    private static JRubyObject marshalClass(Ruby runtime) {
        return new JRubyObject(runtime.evalScriptlet("Marshal"));
    }

    private final IRubyObject obj;

    public JRubyObject(IRubyObject obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public <T> T send(String method, Object... args) {
        Object[] nonAdapterArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            nonAdapterArgs[i] = getRidOfAdapter(args[i]);
        }
        return (T) invokeMethod(obj.getRuntime(), obj, method, nonAdapterArgs, Object.class);
    }

    @Override
    public String toString() {
        return this.obj.toString();
    }

    private Object getRidOfAdapter(Object object) {
        if (object instanceof JRubyObject) {
            return ((JRubyObject) object).obj;
        }
        return object;
    }

    public String dump() {
        return marshalClass(obj.getRuntime()).send("dump", obj);
    }
}
