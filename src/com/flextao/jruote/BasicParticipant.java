/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.jruote;

import org.jruby.runtime.builtin.IRubyObject;

/**
 * A basic participant super class, for those who only need to implement consume method. 
 */
public abstract class BasicParticipant implements Participant {

    public void cancel(IRubyObject cancelitem) {
    }

    public boolean doNotThread() {
        return false;
    }

}
