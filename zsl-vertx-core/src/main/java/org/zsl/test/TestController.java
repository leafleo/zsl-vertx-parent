package org.zsl.test;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

import org.zsl.core.BaseController;
import org.zsl.core.annotation.RequestMapping;
@org.zsl.core.annotation.Controller
@RequestMapping(value="/test")
public class TestController extends BaseController{
	
	public TestController() {
	}
	
	@RequestMapping(value="/hello")
	public String sayHelloWord(HttpServerRequest request,HttpServerResponse response,String hello,RoutingContext context ){
		//response.end("hellohellohellohello");
		Session session = context.session();
		response.putTrailer("test", "操作了");
		//return "redirect:list";
		return "/sys/index";
	}
	
	@RequestMapping(value="/list")
	public String list_(HttpServerRequest request,HttpServerResponse response,String hello){
		//response.end("hellohellohellohello");
		
		response.putTrailer("test", "操作了");
		return "/sys/list";
	}

}
