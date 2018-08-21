package com.server.java.util.redis;

import java.util.Set;

public interface RedisOperation {

	/**
	 * 将value存入redis中
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * 将value存入redis中
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void set(String key, String value, Integer seconds);

	/**
	 * 根据key删除值
	 * 
	 * @param key
	 */
	public void del(String key);

	/**
	 * 根据key查询值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);
	/**
	 * 根据pattern查询值
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern);
}
