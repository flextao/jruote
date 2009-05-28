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
