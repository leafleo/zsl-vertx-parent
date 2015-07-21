package org.zsl.core;
/**
 * 服务配置接口
 * @author leaf
 *
 * @param <V>
 */
public interface ServerConfiguration<V> {
	public void config(V v);
}
