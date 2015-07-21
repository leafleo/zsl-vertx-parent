package org.zsl.core.bean.ext;

import java.util.Map;

public interface LoaderBean {
	static LoaderBean create(){
		return new ScanClassPathLoaderBean();
	}
	
	public  Map<String, Class<?>>  loadAll(String[] basePackages,ClassLoader classLoader,Class<?> containClass) ;
}
