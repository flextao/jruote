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
