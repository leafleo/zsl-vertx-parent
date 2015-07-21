package org.zsl.web.modules.sys.controller;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.zsl.core.BaseController;
import org.zsl.core.annotation.Controller;
import org.zsl.core.annotation.RequestMapping;
import org.zsl.web.main.SpringRunner;
import org.zsl.web.modules.sys.model.Admin;
import org.zsl.web.modules.sys.service.AdminService;

@Controller
@RequestMapping(value="/admin/admin")
public class AdminController extends BaseController<Admin> {
	private  AdminService adminService;
	
	public AdminController() {
		adminService = super.applicationContext.getBean(AdminService.class);
	}
	
	@RequestMapping(value="/list")
	public void list(HttpServerRequest request,HttpServerResponse response,String hello,RoutingContext context){
		Session session = context.session();
		List<Admin> list = adminService.findAll();
		System.out.println(list);
		response.end(list.toString());
		//return "/sys/list";
	}
	
	@RequestMapping(value="/add")
	public String add(HttpServerRequest request,HttpServerResponse response,String hello){
		
		response.putTrailer("test", "操作了");
		return "/sys/add";
	}
}
