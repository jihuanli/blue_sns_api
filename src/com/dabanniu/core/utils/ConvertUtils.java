/**
 * $Id: ConvertUtils.java 18793 2013-09-25 07:54:50Z yijiu.chen $
 * Copyright @2012  Qunar.com Inc. All rights reserved.
 */
package com.dabanniu.core.utils;

/**
 * 
 * <p>
 * 
 * @author chenyijiu Initial Created at 2013-11-11
 *         <p>
 */
public final class ConvertUtils {

    /**
     * 将一个short型数字转成字节数组，按低字节到高字节存储
     * 
     * @param number
     * @return
     */
    public static final byte[] short2byte(short number) {
        byte[] result = new byte[2];
        result[0] = (byte) (number & 0xff);
        result[1] = (byte) ((number >> 8) & 0xff);
        return result;
    }

    /**
     * 将字节数组转换成short
     * 
     * @param data
     * @return
     */
    public static final short byte2short(byte[] data) {
        if (data != null && data.length == 2) {
            return (short) (((data[1] & 0xff) << 8) + (data[0] & 0xff));
        }
        return 0;
    }

    /**
     * 将一个int型数字转成字节数组，按低字节到高字节存储
     * 
     * @param number
     * @return
     */
    public static final byte[] int2byte(int number) {
        byte[] result = new byte[4];
        result[0] = (byte) (number);
        result[1] = (byte) (number >> 8);
        result[2] = (byte) (number >> 16);
        result[3] = (byte) (number >> 24);
        return result;
    }

    /**
     * 将字节数组转换成int
     * 
     * @param data
     * @return
     */
    public static final int byte2int(byte[] data) {
        if (data != null && data.length == 4) {
            return (((data[3] & 0xff) << 24) + ((data[2] & 0xff) << 16) + ((data[1] & 0xff) << 8) + (data[0] & 0xff));
        }
        return 0;
    }

    /**
     * 将一个long型数字转成字节数组，按低字节到高字节存储
     * 
     * @param number
     * @return
     */
    public static final byte[] long2byte(long number) {
        byte[] result = new byte[8];
        result[0] = (byte) (number);
        result[1] = (byte) (number >> 8);
        result[2] = (byte) (number >> 16);
        result[3] = (byte) (number >> 24);
        result[4] = (byte) (number >> 32);
        result[5] = (byte) (number >> 40);
        result[6] = (byte) (number >> 48);
        result[7] = (byte) (number >> 56);
        return result;
    }

    /**
     * 将字节数组转换成long
     * 
     * @param data
     * @return
     */
    public static final long byte2long(byte[] data) {
        if (data != null && data.length == 8) {
            return (((data[7] & 0xffL) << 56) | ((data[6] & 0xffL) << 48) | ((data[5] & 0xffL) << 40)
                    | ((data[4] & 0xffL) << 32) | ((data[3] & 0xffL) << 24) | ((data[2] & 0xffL) << 16)
                    | ((data[1] & 0xffL) << 8) | (data[0] & 0xffL));
        }
        return 0;
    }

    /**
     * 两个int组成一个long
     * 
     * @param high
     * @param low
     * @return
     */
    public static final long pack2int(int high, int low) {
        long highPacked = ((long) high) << 32;
        long lowPacked = low & 0xFFFFFFFFL;
        return highPacked | lowPacked;
    }

    /**
     * 提取高位
     * 
     * @param packed
     * @return
     */
    public static final int unpackHigh(long packed) {
        return (int) (packed >> 32);
    }

    /**
     * 提取低位
     * 
     * @param packed
     * @return
     */
    public static final int unpackLow(long packed) {
        return (int) (packed & 0xFFFFFFFFL);
    }

    /**
     * 两个byte组成一个char
     * 
     * @param high
     * @param low
     * @return
     */
    public static final char pack2byte(byte high, byte low) {
        return (char) (((high << 8) & 0xFFFF) | (low & 0xFF));
    }

    /**
     * 提取高位
     * 
     * @param packed
     * @return
     */
    public static final byte unpackHigh(char packed) {
        return (byte) (packed >> 8);
    }

    /**
     * 提取低位
     * 
     * @param packed
     * @return
     */
    public static final byte unpackLow(char packed) {
        return (byte) (packed & 0xFF);
    }

    private ConvertUtils() {
    }
}
