package org.zsl.core.bean.ext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanClassPathLoaderBean implements LoaderBean {
	private static final Logger logger = LoggerFactory.getLogger(ScanClassPathLoaderBean.class);
	public ScanClassPathLoaderBean() {
	}
	@Override
	public Map<String, Class<?>> loadAll(String[] basePackages,ClassLoader classLoader,Class<?> containClass) {
		Map<String, Class<?>> classMap = new HashMap<String,Class<?>>();
		for(String basePackage : basePackages){
			PackageScanner scan = new ClasspathPackageScanner(basePackage);
			try {
				List<String> fullyQualifiedClassNameList = scan.getFullyQualifiedClassNameList();
				Stream.of(fullyQualifiedClassNameList.toArray())
				//.filter(className->className.equals(containClass.toString()))
				.forEach(className->{
					try {
						Class<?> clazz = Class.forName((String) className, true, classLoader);
						classMap.put((String)className,clazz);
						logger.debug("load clazz:{} success,join claasMap",className);
					} catch (Exception e) {
							logger.error("Load Bean Error:"+className,e);
							throw new RuntimeException("Load Bean Error:"+className,e);
					}
				});
				
			} catch (IOException e) {
				logger.error("Scaning basePackages is Worng,check you configuration path！",e);
				throw new RuntimeException("Scaning basePackages is Worng,check you configuration path！",e);
			}
			
		}
		return classMap;
	}

}
