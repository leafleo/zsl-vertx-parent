package org.zsl.core;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zsl.core.annotation.RequestMapping;


/**
 *     
 * 项目名称：vertx_web    
 * 类名称：Controller    
 * 类描述： 控制主类   
 * 创建人：liujunqing    
 * 创建时间：2015年7月18日  
 * @version 1.0    
 *
 */
public class Controller<T>{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Router router;
	public Controller(Router router) {
		this.router = router;
		convertToRouter();
	}
	
	/**
	 * 处理路由
	 */
	private void convertToRouter() {
		Method[] methods = this.getClass().getMethods();
		Annotation[] annotationsClass = this.getClass().getAnnotationsByType(RequestMapping.class);
		String prefixPath = ((RequestMapping)annotationsClass[0]).value();
		if(logger.isDebugEnabled())
			logger.debug("["+this.getClass()+"]"+"preFixPath:"+prefixPath);
		Stream.of(methods).forEach(m->{
			Annotation[] annotations = m.getAnnotations();
			Stream.of(annotations).forEach(ann->{
				if(ann instanceof RequestMapping){
					String value = ((RequestMapping)ann).value();
					System.out.println(value);
					HttpMethod[] httpMethods = ((RequestMapping)ann).method();
					//如果为空，就取方法名与访问路径
					if(value == null){
						value = prefixPath+"/" + m.getName();
					}else{
						value = prefixPath+value;
					}
					for(HttpMethod hm : httpMethods){
						//OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH
						if(hm == HttpMethod.GET){
							router.get(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.POST){
							router.post(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.OPTIONS){
							router.options(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.HEAD){
							router.head(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.PUT){
							router.put(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.DELETE){
							router.delete(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.TRACE){
							router.trace(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.CONNECT){
							router.connect(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}else if(hm == HttpMethod.PATCH){
							router.patch(value).handler(ctx->_setReqAndResAndInvokeMethod(m,ctx.request(),ctx.response()));
						}
					}
				}
			});
		});
		
	}


	/**
	 * 设置request与response
	 *@param m
	 *@param request
	 *@param response
	 */
	private void _setReqAndResAndInvokeMethod(Method m, HttpServerRequest request, HttpServerResponse response) {
		Class<?>[] types = m.getParameterTypes();
		Object[] ret = new Object[types.length];
		Class<?> c;
		 for (int i = 0; i < types.length; i++) {
	            c = types[i];
	            if(c.isAssignableFrom(HttpServerRequest.class))
	                ret[i] = request;
	            if(c.isAssignableFrom(HttpServerResponse.class))
	                ret[i] = response;
	        }
		 try {
             m.setAccessible(true);
             m.invoke(this,ret);
         } catch (IllegalAccessException e) {
             throw new RuntimeException(e);
         } catch (InvocationTargetException e) {
             throw new RuntimeException(
                 e.getTargetException());
         }
	}
	
	
	
}
