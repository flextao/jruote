/******************************************************************************
    Copyright (c) 2009 Flextao, Li Xiao <xli@flextao.com>

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
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
