package com.joker.collector.init;

import java.util.concurrent.ConcurrentHashMap;

import com.joker.collector.attribute.EqmContext;

/**
 * 
 * @ClassName: EqmContextMap
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XS guo
 * @date 2017年7月18日 上午9:47:38
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class EqmContextMap {

	// 保存通道信息
	private static ConcurrentHashMap<String, EqmContext> instance = new ConcurrentHashMap<String, EqmContext>();

	public static ConcurrentHashMap<String, EqmContext> getInstance() {
		return instance;
	}

}
