package com.echo.utils;

/**
 * Created by zhuyikun on 4/24/16.
 */

import java.util.HashMap;
import java.util.Map;

public class MyBase64Encoder {

    private static final Map<Integer, Character> INDEX_MAP = new HashMap<Integer, Character>();

    private static final char PADDING_CHAR = '=';
    static {
        int index = 0;
        for (int i = 0; i <= 25; i++) {
            INDEX_MAP.put(index, (char) ((int) 'A' + i));
            index++;
        }

        for (int j = 0; j <= 25; j++) {
            INDEX_MAP.put(index, (char) ((int) 'a' + j));
            index++;
        }

        for (int k = 0; k <= 9; k++) {
            INDEX_MAP.put(index, (char) ((int) '0' + k));
            index++;
        }

        INDEX_MAP.put(index, '+');
        index++;
        INDEX_MAP.put(index, '/');
    }

    public static String encode(byte[] bytes) throws Exception {
        /**
         * 1.转成二进制的字符串(长度为6的倍数)
         * 2.获取转义后的字符串
         * 3.不是4的位数，填充=号
         */
        String binaryString = convertByteArray2BinaryString(bytes);
        String escapeString = escapeBinaryString(binaryString);
        return paddingEscapeString(escapeString);
    }

    private static String convertByteArray2BinaryString(byte[] bytes) {

        StringBuilder binaryBuilder = new StringBuilder();
        for (byte b : bytes) {
            binaryBuilder.append(convertByte2BinaryString(b));
        }

        int paddingCount = binaryBuilder.length() % 6;
        int totalCount = paddingCount > 0 ? binaryBuilder.length() / 6 + 1
                : binaryBuilder.length() / 6;
        int actualLength = 6 * totalCount;

        //百分号后面的-号表示长度不够规定长度时，右填充。否则左填充。
        return String.format("%-" + actualLength + "s",
                binaryBuilder.toString()).replace(' ', '0');
    }

    private static String escapeBinaryString(String binaryString)
            throws Exception {
        if (null == binaryString || binaryString.isEmpty()
                || binaryString.length() % 6 != 0) {
            System.out.println("error");
            throw new Exception("escape binary string error.");
        }

        StringBuilder escapeBuilder = new StringBuilder();
        for (int i = 0; i <= binaryString.length() - 1; i += 6) {
            String escapeString = binaryString.substring(i, i + 6);
            int index = Integer.parseInt(escapeString, 2);
            escapeBuilder.append(INDEX_MAP.get(index));
        }

        return escapeBuilder.toString();
    }

    private static String paddingEscapeString(String escapeString) {
        int paddingCount = escapeString.length() % 4;
        int totalCount = paddingCount > 0 ? escapeString.length() / 4 + 1
                : escapeString.length() / 4;
        int actualCount = 4 * totalCount;
        return String.format("%-" + actualCount + "s", escapeString).replace(
                ' ', PADDING_CHAR);
    }

    private static String convertByte2BinaryString(byte b) {

        /**
         * 对于非负数，直接使用Integer.toBinaryString方法把它打印出来
         */
        if (b >= 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(Integer.toBinaryString(b));
            return String.format("%08d", Integer.parseInt(builder.toString()));
        } else {
            /**
             * 对于负数，要记住内存保存的是补码。
             * 不能直接使用Byte.parseByte()方法。
             * 因为这个方法最终调的是Integer.parseInt()方法，也就是说，负数如：10000001
             * 对Integer.parseInt()来说并不会认为是负数，符号位1被当作数值位，是129
             * 同时Byte.parseByte()方法里还对数值范围做了校验，符号位1，已超出范围，这样
             * 会抛出异常。而Byte又没有提供toBinaryString的方法
             * 为了保存byte的二进制值，可利用按位与的方法
             * 例如有一个负数1000 1111，要把它以字符串保留出来，利用它与1111 1111的与操作，
             * 再转成int类型。1000 1111 & 1111 1111
             * 在内存中保存的就是 00000000 10001111，这时保存的是一个正整数。但我们不关心整数的正负，
             * 因为我们的目的是要把这串字符串截取出来
             * 再利用Integer.toBinaryString()打印出来。
             * Integer.toBinaryString()对于正数，会将前面的零去掉，如上将打印出1000 1111，这就是我们要的结果。
             */
            int value = b & 0xFF;
            return Integer.toBinaryString(value);
        }
    }

}