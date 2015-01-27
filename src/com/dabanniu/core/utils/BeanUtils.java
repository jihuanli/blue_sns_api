package com.dabanniu.core.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BeanUtils {
	/**
	 * 利用反射实现对象之间属性复制
	 * @param from
	 * @param to
	 */
	public static void copyProperties(Object from, Object to)  {
		copyPropertiesExclude(from, to, null);
	}
	
	
	
	/**
	 * 复制对象属性--以目标对象为准
	 * @param from
	 * @param to
	 * @param excludsArray 排除属性列表
	 * @throws Exception
	 */
	public static void copyPropertiesExclude(Object from, Object to, String[] excludsArray){
		List<String> excludesList = null;
		if(excludsArray != null && excludsArray.length > 0) {
			excludesList = Arrays.asList(excludsArray);	//构造列表对象
		}
		try {
			Method[] fromMethods = from.getClass().getMethods();
			Method[] toMethods = to.getClass().getDeclaredMethods();
			Method fromMethod = null, toMethod = null;
			String fromMethodName = null, toMethodName = null;
			for (int i = 0; i < toMethods.length; i++) {
				toMethod = toMethods[i];
				toMethodName = toMethod.getName();
				if (toMethodName.indexOf("set")!=0)
					continue;
				//排除列表检测
				if(excludesList != null && excludesList.contains(toMethodName.substring(3).toLowerCase())) {
					continue;
				}
				fromMethodName = "get" + toMethodName.substring(3);
				fromMethod = findMethodByName(fromMethods, fromMethodName);
				if (fromMethod == null)
					continue;
				Object value = fromMethod.invoke(from, new Object[0]);
				if(value == null)
					continue;
				//集合类判空处理
				if(value instanceof Collection) {
					Collection newValue = (Collection)value;
					if(newValue.size() <= 0)
						continue;
				}
				toMethod.invoke(to, new Object[] {value});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	
//	/**
//	 * 复制对象属性--以来源为准
//	 * @param from
//	 * @param to
//	 * @param excludsArray 排除属性列表
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public static void copyPropertiesExclude(Object from, Object to, String[] excludsArray){
//		List<String> excludesList = null;
//		if(excludsArray != null && excludsArray.length > 0) {
//			excludesList = Arrays.asList(excludsArray);	//构造列表对象
//		}
//		try {
//			Method[] fromMethods = from.getClass().getDeclaredMethods();
//			Method[] toMethods = to.getClass().getDeclaredMethods();
//			Method fromMethod = null, toMethod = null;
//			String fromMethodName = null, toMethodName = null;
//			for (int i = 0; i < fromMethods.length; i++) {
//				fromMethod = fromMethods[i];
//				fromMethodName = fromMethod.getName();
//				if (!fromMethodName.contains("get"))
//					continue;
//				//排除列表检测
//				if(excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
//					continue;
//				}
//				toMethodName = "set" + fromMethodName.substring(3);
//				toMethod = findMethodByName(toMethods, toMethodName);
//				if (toMethod == null)
//					continue;
//				Object value = fromMethod.invoke(from, new Object[0]);
//				if(value == null)
//					continue;
//				//集合类判空处理
//				if(value instanceof Collection) {
//					Collection newValue = (Collection)value;
//					if(newValue.size() <= 0)
//						continue;
//				}
//				toMethod.invoke(to, new Object[] {value});
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	/**
//	 * 对象属性值复制，仅复制指定名称的属性值
//	 * @param from
//	 * @param to
//	 * @param includsArray
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public static void copyPropertiesInclude(Object from, Object to, String[] includsArray)  {
//		List<String> includesList = null;
//		if(includsArray != null && includsArray.length > 0) {
//			includesList = Arrays.asList(includsArray);	//构造列表对象
//		} else {
//			return;
//		}
//		Method[] fromMethods = from.getClass().getDeclaredMethods();
//		Method[] toMethods = to.getClass().getDeclaredMethods();
//		Method fromMethod = null, toMethod = null;
//		String fromMethodName = null, toMethodName = null;
//		try {
//			for (int i = 0; i < fromMethods.length; i++) {
//				fromMethod = fromMethods[i];
//				fromMethodName = fromMethod.getName();
//				if (!fromMethodName.contains("get"))
//					continue;
//				//排除列表检测
//				String str = fromMethodName.substring(3);
//				if(!includesList.contains(str.substring(0,1).toLowerCase() + str.substring(1))) {
//					continue;
//				}
//				toMethodName = "set" + fromMethodName.substring(3);
//				toMethod = findMethodByName(toMethods, toMethodName);
//				if (toMethod == null)
//					continue;
//				Object value = fromMethod.invoke(from, new Object[0]);
//				if(value == null)
//					continue;
//				//集合类判空处理
//				if(value instanceof Collection) {
//					Collection newValue = (Collection)value;
//					if(newValue.size() <= 0)
//						continue;
//				}
//				toMethod.invoke(to, new Object[] {value});
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	

	/**
	 * 从方法数组中获取指定名称的方法
	 * 
	 * @param methods
	 * @param name
	 * @return
	 */
	public static Method findMethodByName(Method[] methods, String name) {
		for (int j = 0; j < methods.length; j++) {
			if (methods[j].getName().equals(name))
				return methods[j];
		}
		return null;
	}
}
