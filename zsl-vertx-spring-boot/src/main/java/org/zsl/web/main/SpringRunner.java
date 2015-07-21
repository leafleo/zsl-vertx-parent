package org.zsl.web.main;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zsl.core.BaseController;
import org.zsl.util.Config;
import org.zsl.web.context.SpringConfiguration;

public class SpringRunner {
	private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class) ;
	private static final Logger logger = LoggerFactory.getLogger(SpringRunner.class);
	private static final Config config = Config.getInstance();
	public static void main( String[] args ) {
		//init applicationContext
	    final Vertx vertx = Vertx.vertx();
	   // vertx.deployVerticle(new Server());*/
	   // vertx.deployVerticle(new ServerVerticle(context));
		final Router router = Router.router(vertx);
		BaseController<Object> controller = new BaseController<Object>();
		controller.init(router,applicationContext);
	    // Serve the non private static pages
	    router.route().handler(StaticHandler.create());
	    vertx.createHttpServer().requestHandler(router::accept).listen(config.getInt(Config.PORT));
	    logger.info("server start listen port {}",config.getInt(Config.PORT));
	  }
	
}
