package com.dabanniu.core.utils;

import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;

public class CommonUtils {
	private CommonUtils() {
	}

	public static final Random random = new Random(System.currentTimeMillis());

	/**
	 * 获取当前SERVLET目录
	 */
	public static String GetBasePath(HttpServletRequest request) {
		return request.getSession(true).getServletContext().getRealPath("/");
	}

	/**
	 * 获取当前serverURL
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String path = request.getContextPath();
		int port = request.getServerPort();
		String portInfo = port == 80 ? "" : ":" + port;
		String basePath = "http://" + request.getServerName() + portInfo + path
				+ "/";
		return basePath;
	}

	/**
	 * UID 转为 userId
	 * 
	 * @param long 编号
	 * @return
	 */
	public static long uID2UserId(long uID) {
		String memberId = uID + "";
		if (memberId.length() > 2) {
			return Long.parseLong(memberId.substring(0, memberId.length() - 2));
		} else {
			return 0;
		}
	}

	/**
	 * json 转为 Id集合
	 * 
	 * @param jsonStr
	 *            json
	 * @return
	 */
	public static List<Long> jsonIds2Array(String jsonStr) throws IOException {
		if (StringUtils.isNotBlank(jsonStr)) {
			return JsonUtils.toTypeSafe(jsonStr,
					new TypeReference<List<Long>>() {
					});
		}
		return null;
	}

	/**
	 * 对一个字符串的(“"”，“'”，“\”)字符进行转义
	 * 
	 * @param text
	 *            被转义字符
	 * @return
	 */
	public static String addslashes(String text) {
		StringBuffer sb = new StringBuffer(text.length() * 2);
		StringCharacterIterator iterator = new StringCharacterIterator(text);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE) {
			switch (character) {
			case '"':
				sb.append("\\\"");
				break;
			case '\'':
				sb.append("\\\'");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			default:
				sb.append(character);
				break;
			}
			character = iterator.next();
		}
		return sb.toString();
	}

	/**
	 * 对数据中的(“"”，“'”，“\”)字符进行转义
	 * 
	 * @param obj
	 *            数据对象
	 * @return 转义后的数据
	 */
	@SuppressWarnings("unchecked")
	public static Object saddslashes(Object obj) {
		if (obj instanceof String) {
			return addslashes((String) obj);
		} else if (obj instanceof Object[]) {
			Object[] temp = (Object[]) obj;
			for (int i = 0; i < temp.length; i++) {
				temp[i] = saddslashes(temp[i]);
			}
			return temp;
		} else if (obj instanceof Map) {
			Map temp = (Map) obj;
			Set<Object> keys = temp.keySet();
			for (Object key : keys) {
				saddslashes(temp.get(key));
			}
			return temp;
		} else if (obj instanceof List) {
			List temp = new ArrayList();
			for (Object str : (List) obj) {
				temp.add(saddslashes(str));
			}
			return temp;
		}
		return null;
	}

	/**
	 * 将数据集合、映射、数组的元素，根据separator分隔符号分隔连接成字符串.
	 * 
	 * @param data
	 *            数据集合
	 * @param separator
	 *            分隔符号
	 * @return 字符串
	 */
	@SuppressWarnings("unchecked")
	public static String implode(Object data, String separator) {
		if (data == null) {
			return "";
		}
		StringBuffer out = new StringBuffer();
		if (data instanceof Object[]) {
			Object[] temp = (Object[]) data;
			boolean flag = false;
			for (Object obj : temp) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(obj);
			}
		} else if (data instanceof Map) {
			Map temp = (Map) data;
			Set<Object> keys = temp.keySet();
			boolean flag = false;
			for (Object key : keys) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(temp.get(key));
			}
		} else if (data instanceof List) {
			List temp = (List) data;
			boolean flag = false;
			for (Object obj : temp) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(obj);
			}
		}
		return out.toString();
	}

	/**
	 * 获取首行有效文字
	 * 
	 * @param content
	 *            原文
	 * @return 字符串
	 */
	public static String getFirstText(String content) {
		if (content == null) {
			return "";
		}
		String result = "";
		String[] contents = content.split("\r|\n");
		for (int i = 0; i < contents.length; i++) {
			if (StringUtils.isBlank(contents[i])) {
				continue;
			} else {
				result = contents[i];
				break;
			}
		}
		return result;
	}

	public static String getRandomString(int len) {
		String[] a = new String[] { "a", "b", "c", "d", "e", "f", "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "0" };
		len = len < 1 ? 1 : len;
		StringBuffer con = new StringBuffer();
		for (int i = 0; i < len; i++) {
			con.append(a[CommonUtils.random.nextInt(a.length)]);
		}
		return con.toString();
	}
	public static String getRandomWords(int len) {
		String[] a = new String[] { 
				"a","b","c","d","e","f",
				"g","h","i","j","k","l",
				"m","n","o","p","q","r",
				"s","t","u","v","w","x","y","z"};
		len = len < 1 ? 1 : len;
		StringBuffer con = new StringBuffer();
		for (int i = 0; i < len; i++) {
			con.append(a[CommonUtils.random.nextInt(a.length)]);
		}
		return con.toString();
	}
	
	public static int charLength(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
		   String temp = value.substring(i, i + 1);
		   if (temp.matches(chinese)) {
			   valueLength += 2;
		   } else {
			   valueLength += 1;
		   }
		 }
		 return valueLength;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> covertMapToArrayList(Map map) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			list.add(new Object[]{entry.getKey(),entry.getValue()});
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> covertMapToKeyArrayList(HashMap map) {
		List<Object> list = new ArrayList<Object>();
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Entry entry = (Entry) it.next();
			list.add(entry.getKey());
		}
		return list;
	}
}
