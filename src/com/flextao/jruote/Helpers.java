/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Helpers {
    @SuppressWarnings("unchecked")
    public static <T> T as(Object o) {
        return (T) o;
    }

    public static <T> T[] array(Object... objects) {
        return as(objects);
    }

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    public static String read(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuffer buffer = new StringBuffer();
        try {
            while (true) {
                char[] cbuf = new char[1024];
                int len = reader.read(cbuf);
                if (len == -1) {
                    break;
                }
                buffer.append(cbuf, 0, len);
            }
        } finally {
            in.close();
        }
        return buffer.toString();
    }
}
