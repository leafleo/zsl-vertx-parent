package org.zsl.web.modules.common.controller;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import org.zsl.core.BaseController;
import org.zsl.core.annotation.Controller;
import org.zsl.core.annotation.RequestMapping;
import org.zsl.web.modules.sys.model.Admin;

@Controller
@RequestMapping(value="/common")
public class CommonController extends BaseController<Admin> {
	
	public CommonController() {
	}
	
	@RequestMapping(value="/error404")
	public String error404(HttpServerRequest request,HttpServerResponse response,String hello,RoutingContext context){
		return "/common/error404";
	}
	
	@RequestMapping(value="/error500")
	public String errorr00(HttpServerRequest request,HttpServerResponse response,String hello){
		return "/common/error500";
	}
	
	@RequestMapping(value="/1")
	public String index1(HttpServerRequest request,HttpServerResponse response,String hello){
		return "/common/1";
	}
	
	@RequestMapping(value="/2")
	public String index2(HttpServerRequest request,HttpServerResponse response,String hello){
		return "/common/2";
	}
}
