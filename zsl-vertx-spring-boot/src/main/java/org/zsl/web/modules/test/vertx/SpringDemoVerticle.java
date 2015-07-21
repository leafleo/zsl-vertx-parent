package org.zsl.web.modules.test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

import org.springframework.context.ApplicationContext;
import org.zsl.web.modules.test.service.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpringDemoVerticle extends AbstractVerticle {
	public static final String ALL_PRODUCTS_ADDRESS = "example.all.products";

	private final ObjectMapper mapper = new ObjectMapper();
	private final ProductService service;

	public SpringDemoVerticle(final ApplicationContext context) {

		service = (ProductService) context.getBean("productService");

	}

	private Handler<Message<String>> allProductsHandler(ProductService service) {
		return msg -> vertx.<String> executeBlocking(future -> {
			try {
				future.complete(mapper.writeValueAsString(service
						.getAllProducts()));
			} catch (JsonProcessingException e) {
				System.out.println("Failed to serialize result");
				future.fail(e);
			}
		}, result -> {
			if (result.succeeded()) {
				msg.reply(result.result());
			} else {
				msg.reply(result.cause().toString());
			}
		});
	}

	@Override
	public void start() throws Exception {
		super.start();
		vertx.eventBus().<String> consumer(ALL_PRODUCTS_ADDRESS)
				.handler(allProductsHandler(service));
	}
}
