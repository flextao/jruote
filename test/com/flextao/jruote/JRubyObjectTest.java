/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.jruote;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.jruby.Ruby;
import org.jruby.javasupport.JavaEmbedUtils;
import org.junit.Test;

public class JRubyObjectTest {

    @Test
    public void dumpAndLoadDumpString() {
        Ruby runtime = JavaEmbedUtils.initialize(new ArrayList<String>());
        JRubyObject o = new JRubyObject(runtime.evalScriptlet("[]"));
        String dump = o.dump();
        JavaEmbedUtils.terminate(runtime);

        runtime = JavaEmbedUtils.initialize(new ArrayList<String>());
        assertNotNull(JRubyObject.load(runtime, dump));
    }
}
