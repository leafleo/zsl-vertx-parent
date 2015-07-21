package org.zsl.core.web.ext.templ.impl;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.impl.CachingTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zsl.core.web.ext.templ.FreemakerTemplateEngine;
import org.zsl.util.Config;
import org.zsl.util.S;

public class FreemakerTemplateEngineImpl extends
		CachingTemplateEngine<Template> implements FreemakerTemplateEngine {
	final Charset utf8 = Charset.forName("UTF-8");
	private final static Logger logger = LoggerFactory.getLogger(FreemakerTemplateEngineImpl.class);
	@SuppressWarnings("deprecation")
	private final Configuration configuration = new Configuration();
	private String webroot = S.path.detectWebRootPath();
	private Config config = Config.getInstance();
	private String templateDirectory = webroot + File.separator  +"webroot" + File.separator + S.avoidNull(config.get(Config.VIEWS_NAME), "views");
	@SuppressWarnings("unused")
	@Override
	public void render(RoutingContext context, String templateFileName,
			Handler<AsyncResult<Buffer>> handler) {
		templateFileName = templateFileName+"." + DEFAULT_TEMPLATE_EXTENSION;
		Integer delay = Config.getInstance().getInt(Config.FREEMAKER_TEMPLATE_UPDATE_DELAY);
		Template template = null;
		if(delay > 0 )
				this.cache.get(templateFileName);
		logger.debug("find templateFile:"+templateFileName);
		if(templateFileName== null)
			throw new RuntimeException(templateFileName + " not found.");
		if (template == null) {
			synchronized (this) {
				// Compile
				try {
					template = configuration.getTemplate(templateFileName);
				} catch (IOException e) {
					logger.error(e.getMessage(),e);;
					e.printStackTrace();
				}
			}
			 cache.put(templateFileName, template);
		}
		Map<String, Object> variables = new HashMap<>(1);
	    variables.put("context", context);
	    MultiMap trailers = context.response().trailers();
	    if(!trailers.isEmpty()){
	    	trailers.forEach((k)->{
	    		String key =k.getKey();
	    		String value = k.getValue();
	    		variables.put(key, value);
	    		logger.debug("trailers->{}:{}",key,value);
	    	});
	    }
	    Map<String, Object> data = context.data();
	    if(!data.isEmpty()){
	    	data.forEach((k,v)->{
	    		variables.put(k,v);
	    		logger.debug("context->{}:{}",k,v);
	    	});
	    }
	    if(logger.isInfoEnabled()){
	    	variables.forEach((k,v)->{
	    		 logger.debug("current context variables->k:{},v:{}"+k,v);
	    	});
	    }
	   
	    StringWriter writer = new StringWriter();
	    try //(Writer writer = new OutputStreamWriter(new FileOutputStream(templateFile)), utf8))
	    {
	    	template.process(variables, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    handler.handle(Future.succeededFuture(Buffer.buffer(writer.toString())));

	}

	public FreemakerTemplateEngineImpl() {
		super(FreemakerTemplateEngine.DEFAULT_TEMPLATE_EXTENSION,
				FreemakerTemplateEngine.DEFAULT_MAX_CACHE_SIZE);
		try {
			File f = new File(templateDirectory);
	        logger.info("find tmpl==>" + f.getAbsolutePath());
			configuration.setDirectoryForTemplateLoading(f);
			configuration.setDefaultEncoding("UTF-8");
			configuration.setURLEscapingCharset("UTF-8");
			configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
			configuration.setTemplateUpdateDelay(Config.getInstance().getInt(Config.FREEMAKER_TEMPLATE_UPDATE_DELAY));
			configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
			configuration.setNumberFormat("0.######");
			configuration.setBooleanFormat("true,false");
			configuration.setDateTimeFormat("yyyy-MM-dd");
			configuration.setDateFormat("yyyy-MM-dd");
			configuration.setTimeFormat("HH:mm:ss");
			configuration.setObjectWrapper(new freemarker.ext.beans.BeansWrapper());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	public FreemakerTemplateEngineImpl(String ext, int maxCacheSize) {
		super(ext, maxCacheSize);
	}

	@Override
	public FreemakerTemplateEngine setExtension(String extension) {
		doSetExtension(extension);
		return this;
	}

	@Override
	public FreemakerTemplateEngine setMaxCacheSize(int maxCacheSize) {
		this.cache.setMaxSize(maxCacheSize);
		return this;
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

}
