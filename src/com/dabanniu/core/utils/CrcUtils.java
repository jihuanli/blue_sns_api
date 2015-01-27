/*
 * $Id: CrcUtils.java 5556 2012-10-29 07:22:53Z yijiu.chen $
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.utils;

import java.util.zip.CRC32;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 参数加密解密类，会进行简单的crc校验
 */
//--------------------- Change Logs----------------------
// <p>@author yijiu.chen Initial Created at 2012-6-26<p>
//-------------------------------------------------------
public final class CrcUtils {

    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(CrcUtils.class);

    /**
     * 解密
     * 
     * @param str 加密的串
     * @param key 校验码
     * @return
     */
    public static String decrypt(String str, String key) {
        if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(key)) {
            long crc, crc1;
            try {
                byte[] b = hex2Byte(str);
                crc = (long) (b[0] & 0xff) + ((long) (b[1] & 0xff) << 24) + ((long) (b[2] & 0xff) << 8)
                        + (((long) (b[3] & 0xff)) << 16);
                CRC32 zCrc = new CRC32();
                zCrc.update(b, 4, b.length - 4);
                crc1 = zCrc.getValue();
                if (crc1 == crc) {
                    byte[] bb = dd(b, key, 4, b.length - 4);
                    str = new String(bb, "UTF-8");
                }
            } catch (Exception e) {
                logger.error("decrypt (String str, String key)", e);
            }
        }
        return str;
    }

    /**
     * 加密串
     * 
     * @param str
     * @param key
     * @return
     */
    public static String encrypt(String str, String key) {
        try {
            byte[] b = str.getBytes("UTF-8");
            byte[] bb = ed(b, key, 0, b.length);
            java.util.zip.CRC32 zCrc = new java.util.zip.CRC32();
            zCrc.update(bb, 0, bb.length);
            long crc = zCrc.getValue();
            byte[] bbb = new byte[bb.length + 4];
            bbb[0] = (byte) crc;
            bbb[1] = (byte) (crc >> 24);
            bbb[2] = (byte) (crc >> 8);
            bbb[3] = (byte) (crc >> 16);
            System.arraycopy(bb, 0, bbb, 4, bb.length);
            str = byte2Hex(bbb);
        } catch (Exception e) {
            logger.error("encrypt(String str, String key)", e);
        }
        return str;
    }

    private static byte[] dd(byte[] data, String key, int start, int length) {
        byte[] result = new byte[length];
        byte v = 91;
        try {
            int nKeyLen = key.length();
            byte[] keyChars;
            keyChars = key.getBytes("UTF-8");
            for (int i = 0; i < length; i++) {
                data[i + start] -= keyChars[i % nKeyLen];
                data[i + start] ^= v;
            }
            System.arraycopy(data, start, result, 0, length);
        } catch (Exception e) {
            logger.error("dd(byte[] data, String key, int start, int length)", e);
        }
        return result;
    }

    private static byte[] ed(byte[] data, String key, int start, int length) {
        byte[] result = new byte[length];
        byte v = 91;
        try {
            int nKeyLen = key.length();
            byte[] keyChars = key.getBytes("UTF-8");
            for (int i = 0; i < length; i++) {
                data[i + start] ^= v;
                data[i + start] += keyChars[i % nKeyLen];
            }
            System.arraycopy(data, start, result, 0, length);
        } catch (Exception e) {
            logger.error("ed(byte[] data, String key, int start, int length)", e);
        }
        return result;
    }

    private static String byte2Hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length == 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toLowerCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte[] hex2Byte(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private CrcUtils() {
    }

}
