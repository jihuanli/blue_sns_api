package com.dabanniu.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.dabanniu.utils.EmojiFilter;

public class EmojiCharFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new FilteredRequest(request), response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("加载EMOJI过滤。。。");
	}

	final private static class FilteredRequest extends
			HttpServletRequestWrapper {
		public FilteredRequest(ServletRequest request) {
			super((HttpServletRequest) request);
		}

		@Override
		public String getParameter(String paramName) {

			return EmojiFilter.encodeEmoji(super.getParameter(paramName));
		}

		@Override
		public String[] getParameterValues(String paramName) {
			String[] result = super.getParameterValues(paramName);
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {
					String string = result[i];
					result[i] = EmojiFilter.encodeEmoji(string);
				}
			}
			return result;
		}

		@Override
		public Map getParameterMap() {
			Map<Object, Object> parameterMap = new HashMap<Object, Object>();
			Map originalParameterMap = super.getParameterMap();
			for (Object o : originalParameterMap.entrySet()) {
				Map.Entry<Object, Object> pairs = (Entry<Object, Object>) o;
				Object v = pairs.getValue();
				if (v != null) {
					if (v instanceof String[]) {
						String[] strArr = (String[]) v;
						for (int i = 0; i < strArr.length; i++) {
							String string = strArr[i];
							strArr[i] = EmojiFilter.encodeEmoji(string);
						}
					} else if (v instanceof String) {
						v=EmojiFilter.encodeEmoji((String)v);
					} 
				}
				parameterMap.put(pairs.getKey(), v);
			}
			return parameterMap; // Returning a modifiable ParameterMap.
		}
	}

}
