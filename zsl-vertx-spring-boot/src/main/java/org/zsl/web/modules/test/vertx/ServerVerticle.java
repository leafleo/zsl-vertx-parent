package org.zsl.web.modules.test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

import org.springframework.context.ApplicationContext;
import org.zsl.web.modules.sys.service.AdminService;
import org.zsl.web.modules.test.service.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple web server verticle to expose the results of the Spring service bean
 * call (routed via a verticle - see SpringDemoVerticle)
 */
public class ServerVerticle extends AbstractVerticle {

	private final ObjectMapper mapper = new ObjectMapper();
	private final ProductService service;
	private final AdminService adminService;
	
	public ServerVerticle(final ApplicationContext context) {

		service = (ProductService) context.getBean("productService");
		adminService = context.getBean(AdminService.class);
	}
	
	@Override
	public void start() throws Exception {
		super.start();
		HttpServer server = vertx.createHttpServer();
		server.requestHandler(req -> {
			System.out.println(adminService.findAll());
			if (req.method() == HttpMethod.GET) {
				req.response().setChunked(true);

				if (req.path().equals("/products")) {
					vertx.eventBus().<String> send(
							SpringDemoVerticle.ALL_PRODUCTS_ADDRESS,
							"",
							result -> {
								if (result.succeeded()) {
									req.response().setStatusCode(200)
											.write(result.result().body())
											.end();
								} else {
									req.response().setStatusCode(500)
											.write(result.cause().toString())
											.end();
								}
							});
				} else {
					req.response().setStatusCode(200)
							.write("Hello from vert.x").end();
				}

			} else {
				// We only support GET for now
				req.response().setStatusCode(405).end();
			}
		});

		server.listen(8080);
	}
}
