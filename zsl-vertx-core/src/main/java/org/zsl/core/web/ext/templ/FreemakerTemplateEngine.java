package org.zsl.core.web.ext.templ;

import org.zsl.core.web.ext.templ.impl.FreemakerTemplateEngineImpl;

import freemarker.template.Configuration;
import io.vertx.ext.web.templ.TemplateEngine;

public interface FreemakerTemplateEngine extends TemplateEngine {
	 /**
	   * Default max number of templates to cache
	   */
	  int DEFAULT_MAX_CACHE_SIZE = 10000;

	  /**
	   * Default template extension
	   */
	  String DEFAULT_TEMPLATE_EXTENSION = "htm";

	  /**
	   * Create a template engine using defaults
	   *
	   * @return  the engine
	   */
	  static FreemakerTemplateEngine create() {
	    return new FreemakerTemplateEngineImpl();
	  }

	  /**
	   * Set the extension for the engine
	   *
	   * @param extension  the extension
	   * @return a reference to this for fluency
	   */
	  FreemakerTemplateEngine setExtension(String extension);

	  /**
	   * Set the max cache size for the engine
	   *
	   * @param maxCacheSize  the maxCacheSize
	   * @return a reference to this for fluency
	   */
	  FreemakerTemplateEngine setMaxCacheSize(int maxCacheSize);
	  
	  /**
	   * Get a reference to the internal configuration object so it
	   * can be configured.
	   *
	   * @return a reference to the internal configuration instance.
	   */
	  Configuration getConfiguration();
}
