package org.zsl.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zsl.util.Config;


public class Server extends AbstractVerticle {
	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private Router router;
	public Server() {
		super();
		if(router == null)
			router = Router.router(vertx);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start() throws Exception {
		router = Router.router(vertx);
		BaseController<Object> controller = new BaseController<Object>();
		controller.init(router,null);
	    // Serve the non private static pages
	    router.route().handler(StaticHandler.create());
	    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	    logger.info("server start listen port 8080");
	}

	public static void main(String[] args) {
		logger.info("start...");
		Run(Server.class.getName(), new VertxOptions().setClustered(false),null);
	}

	public static void Run(String verticleID,VertxOptions options, DeploymentOptions deploymentOptions) {
		//System.setProperty(io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME,"org.slf4j.LoggerFactory");
		//System.setProperty("vertx.cwd", exampleDir);
		
		Consumer<Vertx> runner = vertx -> {
			try {
				if (deploymentOptions != null) {
					vertx.deployVerticle(verticleID, deploymentOptions);
				} else {
					vertx.deployVerticle(verticleID);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		};
		if (options.isClustered()) {
			Vertx.clusteredVertx(options, res -> {
				if (res.succeeded()) {
					Vertx vertx = res.result();
					runner.accept(vertx);
				} else {
					res.cause().printStackTrace();
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(options);
			runner.accept(vertx);
		}
	}


}
