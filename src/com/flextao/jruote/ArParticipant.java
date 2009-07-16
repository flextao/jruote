/******************************************************************************
    Copyright (c) 2009 Flextao

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

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * ActiveRecord Participant helper class. AcitveRecord Participant could extend this class
 * for getting abilities to consume/cancel an ArParticipant by storeName and workitem.
 */
public abstract class ArParticipant implements Participant {

    protected IRubyObject consumeArParticipant(String storeName, IRubyObject workitem) {
       return callArParticipant(storeName, "consume", workitem);
    }

    protected IRubyObject cancelArParticipant(String storeName, IRubyObject workitem) {
        return callArParticipant(storeName, "cancel", workitem);
    }

    /**
     * have to be synchronized, looks like jruby runtime has thread safe problem when doing this work
     */
    protected synchronized IRubyObject callArParticipant(String storeName, String method, IRubyObject workitem) {
        Ruby runtime = workitem.getRuntime();
        return newArParticipant(runtime, storeName).callMethod(runtime.getCurrentContext(), method, workitem);
    }

    public boolean doNotThread() {
        return false;
    }

    private IRubyObject newArParticipant(Ruby runtime, String storeName) {
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
