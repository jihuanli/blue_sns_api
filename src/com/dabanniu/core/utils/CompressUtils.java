/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * 压缩工具类
 * 
 * @author yijiu.chen <br />
 *         Initial Created at 2012-5-22
 */
public final class CompressUtils {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CompressUtils.class);

    /**
     * zlib压缩
     * 
     * @param data
     * @return
     */
    public static final byte[] zlibCompress(byte[] data) {
        byte[] output;

        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            logger.error("zlibCompress", e);
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                logger.error("zlibCompress", e);
            }
        }
        compresser.end();
        return output;
    }

    /**
     * zlib解压
     * 
     * @param data
     * @return
     */
    public static byte[] zlibDecompress(byte[] data) {
        byte[] output;

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            logger.error("zlibDecompress", e);
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                logger.error("zlibDecompress", e);
            }
        }

        decompresser.end();
        return output;
    }

    /**
     * gzib压缩
     * 
     * @param data
     * @return
     */
    public static final byte[] gzibCompress(byte[] data) {
        byte[] output = new byte[0];

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipo = new GZIPOutputStream(o);
            gzipo.write(data);
            gzipo.finish();
            gzipo.close();
            output = o.toByteArray();
        } catch (Exception e) {
            logger.error("gzibCompress", e);
        } finally {
            try {
                o.close();
            } catch (Exception e) {
                logger.error("gzibCompress", e);
            }
        }
        return output;
    }

    /**
     * gzib解压
     * 
     * @param data
     * @return
     */
    public static byte[] gzibDecompress(byte[] data) {
        byte[] output = new byte[0];
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;

            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, num);
            }

            output = bos.toByteArray();
            gzip.close();
        } catch (Exception e) {
            logger.error("gzibDecompress", e);
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (Exception e) {
                logger.error("gzibDecompress", e);
            }
        }
        return output;
    }

    /**
     * 
     * @param data被压缩数据
     * @param zipType 1,gzib;2,zlib
     * @return
     */
    public static byte[] getCompressBytes(byte[] data, int zipType) {
        if (zipType == 1) {
            return gzibCompress(data);
        } else if (zipType == 2) {
            return zlibCompress(data);
        }
        return data;
    }

    private CompressUtils() {
    }
}
