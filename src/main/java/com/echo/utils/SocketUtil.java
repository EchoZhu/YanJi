package com.echo.utils;

import java.net.Socket;

/**
 * Created by zhuyikun on 12/22/15.
 */
public class SocketUtil {
    public static Socket socket = null;
    public static String IP = "192.168.10.10";
    public static int PORT = 8080;
    public static String OPENTVIDEO = "5AA500010801ffff0d0a";
    public static String CLOSEVIDEO = "5AA500010802ffff0d0a";
    public static String TURNOFF = "5AA500010401ffff0d0a";

    public static class HexUtils {
        private static final char[] hexArray = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

        public static boolean equals(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
        {


            if ((paramArrayOfByte1 == null) && (paramArrayOfByte2 == null))
                return true;
            if ((paramArrayOfByte1 == null) && (paramArrayOfByte2 != null))
                return false;
            if ((paramArrayOfByte1 != null) && (paramArrayOfByte2 == null))
                return false;
            if (paramArrayOfByte1.length != paramArrayOfByte2.length)
                return false;
            for (int i = 0; i < paramArrayOfByte1.length; i++)
                if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
                    return false;
            return true;

        }

        public static byte[] getBytes(String paramString)
        {
            byte[] arrayOfByte;
            if ((paramString == null) || ("".equals(paramString)))
                arrayOfByte = null;
            String str = paramString.toLowerCase();
            arrayOfByte = new byte[str.length() / 2];
            for (int i = 0; i < str.length(); i+= 2)
            {
                int j = getCharIndex(str.charAt(i));
                if (j < 0)
                    return null;
                arrayOfByte[(i / 2)] = ((byte)(0xF0 & j << 4));
                int k = getCharIndex(str.charAt(i + 1));
                if (k < 0)
                    return null;
                int m = i / 2;
                arrayOfByte[m] = ((byte)(arrayOfByte[m] | (byte)(0xF & k << 0)));
            }
            return arrayOfByte;
        }

        private static int getCharIndex(char paramChar)
        {
            for (int i = 0; ; i++)
            {
                if (i >= hexArray.length)
                    i = -1;
                while (paramChar == hexArray[i])
                    return i;
            }
        }

        public static String getString(byte[] paramArrayOfByte)
        {
            if (paramArrayOfByte == null)
                return "";
            char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
            for (int i = 0; ; i++)
            {
                if (i >= paramArrayOfByte.length)
                    return new String(arrayOfChar);
                int j = i * 2;
                arrayOfChar[j] = hexArray[((0xFF & paramArrayOfByte[i]) >> 4)];
                arrayOfChar[(j + 1)] = hexArray[(0xF & paramArrayOfByte[i])];
            }
        }

        public static String getUUIDByBytes(byte[] paramArrayOfByte)
        {
            String str = getString(paramArrayOfByte);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(str.substring(0, 8));
            localStringBuilder.append("-");
            localStringBuilder.append(str.substring(8, 12));
            localStringBuilder.append("-");
            localStringBuilder.append(str.substring(12, 16));
            localStringBuilder.append("-");
            localStringBuilder.append(str.substring(16, 20));
            localStringBuilder.append("-");
            localStringBuilder.append(str.substring(20, 32));
            return localStringBuilder.toString();
        }
    }
}
