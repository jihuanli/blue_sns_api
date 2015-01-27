package com.dabanniu.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

import com.dabanniu.exception.DabanniuException;

public class ImageHelper {
	
	public static class PicInfo{
		public PicInfo(int width,int height){
			this.width=width;
			this.height=height;
		}
		private int width;
		private int height;
		public int getWidth() {
			return width;
		}
		public int getHeight() {
			return height;
		}
		
	}
	
	public static PicInfo getPicFormatInfo(String pic) throws Exception{;
		try {
			String formatInfo=getFormatInfo(pic);
			if(formatInfo!=null){
				String[] infos=formatInfo.split("x");	
				return new PicInfo(Integer.parseInt(infos[0]),Integer.parseInt(infos[1]));
			}else{
				throw new DabanniuException("不是一个合格的图片");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	private static void createDir(String path) {
		String realPath;
		try {
			realPath = path.substring(0, path.lastIndexOf("/"));
		} catch (Exception e) {
			realPath = path.substring(0, path.lastIndexOf("\\"));
		}
		File dir = new File(realPath);
		dir.mkdirs();
	}
	/**
	 * 获取格式化信息，宽高
	 * */
	private static String getFormatInfo(String pic) {
		String command = "identify -format \"%[fx:w]x%[fx:h]\" \"" + pic + "\"";
		String[] str = { command.toString() };
		try {
			return execWindowsOrLinux(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 加水印
	 * */
	public static String addWaterMark(String source, String target) {
		String  waterMarkPath="/usr/local/openresty/nginx/newapi/watermark.png";
		String command="composite -gravity southeast -geometry +5+10 -dissolve 80 "+waterMarkPath+" "+source+" "+target;
		String[] str = { command.toString() };
		try {
			return execWindowsOrLinux(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 按比例，将最大边长缩放到指定尺寸
	 * */
	public static void thumbWidth(String source, String target, int orgWidth,
			int orgHeight, int width) {
		double ratio = (double) orgWidth/width ;
		int newW = width;
		int newH = (int) Math.floor(orgHeight/ratio);
		String command = "convert " + source + " -thumbnail \"" + newW + "x"
				+ newH + "\" -quality '85' " + target;
		String[] str = { command.toString() };
		createDir(target);
		try {
			execWindowsOrLinux(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按比例，将最大边长缩放到指定尺寸
	 * */
	public static void thumbBigSide(String source, String target, int orgWidth,
			int orgHeight, int width, int height) {

		double ratioW = (double) orgWidth/width ;
		double ratioH = (double) orgHeight/height ;
		double ratio = ratioW;
		if (ratio < ratioH) {
			ratio = ratioH;
		}
		int newW = (int) Math.floor(orgWidth/ratio);
		int newH = (int) Math.floor(orgHeight/ratio);
		String command = "convert " + source + " -thumbnail \"" + newW + "x"
				+ newH + "\" -quality '85' " + target;
		String[] str = { command.toString() };
		createDir(target);
		try {
			execWindowsOrLinux(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按比例，将最小边长缩放到指定尺寸
	 * */
	public static void thumbSmallSide(String source, String target, int orgWidth,
			int orgHeight, int width, int height) {

		double ratioW = (double) width/orgWidth ;
		double ratioH = (double) height/orgHeight ;
		double ratio = ratioW;
		if (ratio < ratioH) {
			ratio = ratioH;
		}
		int newW = (int) Math.floor(orgWidth*ratio);
		int newH = (int) Math.floor(orgHeight*ratio);
		String command = "convert " + source + " -thumbnail \"" + newW + "x"
				+ newH + "\" -quality '85' " + target;
		String[] str = { command.toString() };
		createDir(target);
		try {
			execWindowsOrLinux(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按尺寸 裁剪
	 * @throws DabanniuException 
	 * */
	public static void thumb(String source,String target,int width, int height) throws DabanniuException{
		try {
			String command = "convert " + source + " -thumbnail \"" + width + "x"
			+ height + "^\" -quality '85' " + target;
			createDir(target);
			String[] str = { command.toString() };
			execWindowsOrLinux(str);
			//获取缩略图尺寸
			PicInfo picinfo=getPicFormatInfo(target);
			int newW=picinfo.getWidth();
			//裁切
			int offsetX = (int)((newW-width)/2);
			command = "convert " + target + " -crop "+width+"x"+height+"+"+offsetX+"+0 " + target;
			String cropCommand[] = { command.toString() };
			execWindowsOrLinux(cropCommand);
		} catch (DabanniuException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 按比例，限定最大宽度
	 * */
	public static void thumbMaxWidth(String source, String target, int orgWidth,int orgHeight,int maxWidth) {
		int width=orgWidth;
		int height=orgHeight;
		if(orgWidth>maxWidth){
			width=maxWidth;
			double ratio=(double)orgWidth/orgHeight;
			height=(int) Math.floor(maxWidth/ratio);
		}
		String command = "convert " + source + " -thumbnail \"" + width + "x"
				+ height + "\" -quality '85' " + target;
		String[] str = { command.toString() };
		createDir(target);
		try {
			execWindowsOrLinux(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取操作系统名称
	 * 
	 * @return
	 */
	public static String getOS() {
		Properties pros = System.getProperties();
		String os = (String) pros.get("os.name");
		System.out.println(os);
		return os;
	}

	/**
	 * 根据操作系统类型来执行调用系统命令
	 * 
	 * @param command
	 * @throws Exception
	 */
	public static String execWindowsOrLinux(String[] command) throws Exception {
		Process proc = null;
		String comman = command[0];
		System.out.println("命令为：" + comman);
		String osName = getOS();
		int result = 0;
		StringBuffer sbf = new StringBuffer();
		try {
			if (osName.startsWith("Windows")) {// windows下调用系统命令
				String[] cmdWindows = { "cmd.exe", "/c", comman };
				proc = Runtime.getRuntime().exec(cmdWindows);
			} else if (osName.startsWith("Linux")) {// Linux下调用系统命令
				String[] cmdLinux = { "/bin/sh", "-c", comman };
				proc = Runtime.getRuntime().exec(cmdLinux);
			} else if (osName.startsWith("Unix")) {
				String[] cmdUnix = { comman };
				proc = Runtime.getRuntime().exec(cmdUnix);
			}
			InputStreamReader ier = new InputStreamReader(proc.getErrorStream());
			BufferedReader input = new BufferedReader(ier);
			StringBuffer error = new StringBuffer();
			String line;
			while ((line = input.readLine()) != null) {
				error.append(line);
			}
			if (error.length() > 0) {
				throw new Exception(error.toString());
			}
			result = proc.waitFor();
			System.out.println("Process result:" + result);
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			input = new BufferedReader(ir);

			while ((line = input.readLine()) != null) {
				sbf.append(line);
				System.out.println("line:" + line);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return sbf.toString();
	}
}
