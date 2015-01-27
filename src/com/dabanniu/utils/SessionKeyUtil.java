package com.dabanniu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;

import com.dabanniu.core.bean.SessionKey;
import com.dabanniu.core.utils.SessionKeyUtils;

public class SessionKeyUtil {
	public static void main(String[] args) {
		File file = new File("C:/Users/Sould/Desktop/user.txt");
		
		StringBuffer result = new StringBuffer();
		
		if (file.isFile()) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				int line=0;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					String[] aa=tempString.split("\t");
					SessionKey sessionKey=new SessionKey();
					sessionKey.setuID(Long.parseLong(aa[1]));
					sessionKey.setCreationDate(System.currentTimeMillis());
					sessionKey.setExpirationDate(30);
					result.append(aa[0]);
					result.append("\t");
					result.append(aa[1]);
					result.append("\t");
					result.append(SessionKeyUtils.encrypt(sessionKey));
					result.append("\r\n");
					line++;
					
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
			result.append(")");
			File errorFile = new File("C:/Users/Sould/Desktop/user2.txt");
			try {
				FileCopyUtils.copy(result.toString().getBytes(), errorFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("错误的任务文件");
		}
	}
}
