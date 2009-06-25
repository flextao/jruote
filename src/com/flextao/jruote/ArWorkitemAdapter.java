/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.jruote;

import org.jruby.runtime.builtin.IRubyObject;

public class ArWorkitemAdapter {
    private JRubyObject item;

    public ArWorkitemAdapter(IRubyObject arWorkitem) {
        this.item = new JRubyObject(arWorkitem);
    }
    public Long getId() {
        return item.send("id");
    }
}
