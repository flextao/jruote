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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;

import org.jruby.Ruby;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;
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

    @SuppressWarnings("unchecked")
    @Test
    public void hashMap() {
        Ruby runtime = JavaEmbedUtils.initialize(new ArrayList<String>());
        JRubyObject aInstance = new JRubyObject(runtime.evalScriptlet("require 'yaml';class A; def a_hash;@a_hash ||= {};end;def to_y; YAML.dump(self); end;end;x = A.new"));
        Map<String, Object> map = (Map<String, Object>) aInstance.send("a_hash");
        map.put("abc", BytesHelper.convertToString("test".getBytes()));
        String yamlStr = aInstance.send("to_y");

        JRubyObject yaml = new JRubyObject(runtime.evalScriptlet("YAML"));
        IRubyObject loaded = yaml.send("load", yamlStr);
        JRubyObject theAInstance = new JRubyObject(loaded);
        JRubyObject theHash = new JRubyObject((IRubyObject) theAInstance.send("a_hash"));
        String stored = new String(BytesHelper.convertToBytes((String) theHash.send("[]", "abc")));
        assertEquals("test", stored);
    }
}
