package org.zsl.util;

import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component(value="config")
@Lazy(false)
public class Config extends HashMap<String,String> {
		private static final long serialVersionUID = -6340054257202289483L;
		private static final Logger logger = LoggerFactory.getLogger(Config.class);
		public final static String ROOT = "g.root";
	    public final static String ROOT_WEB = "g.root_web";
	    public final static String VIEWS_PATH = "g.views_path";
	    public final static String VIEWS_NAME = "g.views_name";
	    public final static String VIEW_CACHE = "g.view_cache";
	    public final static String CONFIG_FILE = "g.config_file";
	    public final static String PORT = "g.port";
	    public final static String CONFIG_FILE_NAME = "zsl.conf";
	    public final static String ERROR_404_PATH = "g.error_404_path";
	    public final static String ERROR_500_PATH = "g.error_500_path";
	    public final static String BASE_Path = "base";
	    public final static String FREEMAKER_TEMPLATE_UPDATE_DELAY =  "g.freemaker.template_update_delay";
	    
	    private final String config_file_name = CONFIG_FILE_NAME;
	    private static Config config = new Config();
	    private Config() {
		}
	    
	    public static Config getInstance(){
	    	return config;
	    }
	    
	    @PostConstruct
	    public void init(){
	    	this.load(S.file.loadProperties(config_file_name));
	    }
	    
		public Integer getInt(String k){
	        return Integer.parseInt(getOrDefault(k, "0"));
	    }
	    public Boolean getBool(String k) {
	        return Boolean.parseBoolean(getOrDefault(k, "false"));
	    }

	    public void load(Properties p) {
	    	p.keySet().forEach(k-> {
	            String o = p.getProperty((String)k);
	            Config.logger.info("load {}:{}",k,o);
	            Config.config.put((String)k, o);
	        });
	    }
}
