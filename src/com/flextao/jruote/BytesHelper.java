/******************************************************************************

 * 杭州昼韬信息技术有限公司版权所有。

 * 本源代码所包含的以及第三方所授权的知识产权均归杭州昼韬信息技术有限公司所有。
 * 本源代码及所包含的知识产权仅限于由得到杭州昼韬信息技术有限公司版权许可的公司或个人使用。

 ***********************************************************************/

package com.flextao.jruote;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BytesHelper {

    private static String ID = "235290996559476282L";

    public static boolean isBytesString(Object str) {
        if (str instanceof String) {
            return isBytesString((String) str);
        }
        return false;
    }

    public static boolean isBytesString(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith(ID);
    }

    public static String convertToString(ByteArrayOutputStream out) {
        return convertToString(out.toByteArray());
    }

    public static ByteArrayInputStream convertToByteArrayInputStream(String bytes) {
        return new ByteArrayInputStream(convertToBytes(bytes));
    }

    public static String convertToString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(ID);
        for (int i = 0, len = bytes.length; i < len; i++) {
            buffer.append(",");
            buffer.append(bytes[i]);
        }
        return buffer.toString();
    }

    public static byte[] convertToBytes(String bytesStr) {
        String[] bytes = bytesStr.substring(ID.length() + 1).split(",");
        byte[] result = new byte[bytes.length];
        for (int i = 0, len = result.length; i < len; i++) {
            result[i] = Byte.parseByte(bytes[i]);
        }
        return result;
    }
}
