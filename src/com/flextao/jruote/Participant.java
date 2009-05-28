/******************************************************************************

* 杭州昼韬信息技术有限公司版权所有。

* 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
* 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

***********************************************************************/

package com.flextao.jruote;

import org.jruby.runtime.builtin.IRubyObject;

/**
 * Java participant interface of OpenWFE::Participant
 */
public interface Participant {
    /**
     * A Participant will be receiving OpenWFE::WorkItem's via this method.
     */
    void consume(IRubyObject workitem);
    /**
     * (optional)
     */
    void cancel(IRubyObject workitem);

    /**
     * By default, returns false. When returning true the dispatch to the
     * participant will not be done in his own thread.
     * Since ActiveRecord 2.2, ActiveParticipant returns true (well, this
     * method was introduced for ActiveParticipant and AR 2.2)
     */
    boolean doNotThread();
}
