package com.lhs.ms.goforit.common.config.cache;

import org.springframework.stereotype.Component;

@Component
public interface Cache {
	public void set(String key, String value, long seconds);

	public void set(String key, String value);

	public void removeItem(String key);

	public String getKey(String key);

}
