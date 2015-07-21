package org.zsl.core;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.impl.ConcurrentLRUCache;
import io.vertx.ext.web.templ.TemplateEngine;





import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;





import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.omg.stub.java.rmi._Remote_Stub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.zsl.core.annotation.Controller;
import org.zsl.core.annotation.RequestMapping;
import org.zsl.core.bean.ext.LoaderBean;
import org.zsl.core.web.ext.templ.FreemakerTemplateEngine;
import org.zsl.util.Config;


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
public class BaseController<T>{
	public final  Logger logger = LoggerFactory.getLogger(getClass());
	public final ConcurrentLRUCache<String,Object> cache = new ConcurrentLRUCache<>(100000);
	private final static TemplateEngine engine = FreemakerTemplateEngine.create();
	private Router router;
	public  final static String REDIRECT_PREFIX = "redirect:";
	public static ApplicationContext applicationContext;
	private static final Map<Class<?>,Map<String,Method>> classesMethodsMap = new  ConcurrentHashMap<>();
	private static boolean inited = false;
	//创建线程局部变量保存上下文
	private static  ThreadLocal<ApplicationContext> applicationContextLocal = new ThreadLocal<>();
	public BaseController() {
		super();
		
	}
	
	public void  init(Router router,ApplicationContext applicationContext){
		if(inited){
			new RuntimeException("The init method allows only one initial.");
		}
		this.router = router;
		BaseController.applicationContext = applicationContext;
		//初始化
		//getApplicationContext();
		//扫黄指定目录并加载指定到内存
		LoaderBean classLoaderBean = LoaderBean.create();
		Map<String, Class<?>> scanClassMap = classLoaderBean.loadAll(new String[]{"org.zsl"}, BaseController.class.getClassLoader(), Controller.class);
		//Stream.of(scanClassMap).filter((k,v)->)
		scanClassMap.forEach((k,v)->{
			if(!v.isAnnotationPresent(Controller.class)){
				logger.warn("Skip not @Controller for {}.",v.getName());
				return;
			}
			logger.warn("Find @Controller for {}.",v.getName());
			convertToRouter(v);
		});
		if(!inited){
			inited = true;
		}
	}
	
	private void convertToRouter(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		Annotation[] annotationsClass = clazz.getAnnotationsByType(RequestMapping.class);
		String prefixPath = ((RequestMapping)annotationsClass[0]).value();
			logger.info("["+clazz+"]"+"preFixPath:"+prefixPath);
		Stream.of(methods).forEach(m->{
			logger.debug("Scaning mehtod:"+m);
			Annotation[] annotations = m.getAnnotationsByType(RequestMapping.class);
			Map<String,Method> methodsMap = new ConcurrentHashMap<>();
			for(Annotation ann : annotations){
				if(ann instanceof RequestMapping){
					String value = ((RequestMapping)ann).value();
					//如果存在相同的path,说明配置错误
					if(methodsMap.containsKey(value)){
						throw new RuntimeException(m.toString()+" is same requestMapping value");
					}
					methodsMap.put(value, m);
				}
			}
			classesMethodsMap.put(clazz,methodsMap);
			logger.info("router join methodsMap:"+methodsMap);
			Stream.of(annotations).forEach(ann->{
				if(ann instanceof RequestMapping){
					String value = ((RequestMapping)ann).value();
					HttpMethod[] httpMethods = ((RequestMapping)ann).method();
					String headers =  ((RequestMapping)ann).headers();
					//如果为空，就取方法名与访问路径
					if(value == null){
						value = prefixPath+"/" + m.getName();
					}else{
						value = prefixPath+value;
					}
					for(HttpMethod hm : httpMethods){
						//OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH
						logger.info("Mapping [{}][{}][{}]",new Object[]{hm,value,m});
						if(hm == HttpMethod.GET){
							router.get(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);
								_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);
								});
						}else if(hm == HttpMethod.POST){
							router.post(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);
								_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);
							});
						}else if(hm == HttpMethod.OPTIONS){
							router.options(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.HEAD){
							router.head(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.PUT){
							router.put(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.DELETE){
							router.delete(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.TRACE){
							router.trace(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.CONNECT){
							router.connect(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}else if(hm == HttpMethod.PATCH){
							router.patch(value).handler(ctx->{_ctxPutConfigFile(ctx);ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, headers);_setReqAndResAndInvokeMethod(clazz,m,ctx.request(),ctx.response(),ctx);});
						}
					}
				}
			});
		});
		
		
	}

	// config_file properteis join to ctx
	private void _ctxPutConfigFile(RoutingContext ctx) {
		Config confog = Config.getInstance();
		confog.forEach((k,v)->{
			ctx.put(k, v);
			logger.debug("{}:{} join to ctx.",k,v);
		});
	}

	/**
	 * 设置request与response
	 *@param m
	 *@param request
	 *@param response
	 */
	private void _setReqAndResAndInvokeMethod(Class<?> clazz,Method m, HttpServerRequest request, HttpServerResponse response,RoutingContext context) {
		long now = System.currentTimeMillis();
		logger.debug("Entry start [{}][{}]",new Object[]{request.uri(),request.method().toString()});
		try {
		  Object newIntance = cache.get(clazz); 
		  if(newIntance== null)
			newIntance = clazz.newInstance();
			Class<?>[] types = m.getParameterTypes();
			Class<?> retTypes = m.getReturnType();
			//如果方法为无返回值
			if("void".equals(retTypes.getSimpleName())){
				Object[] ret = new Object[types.length];
				Class<?> c;
				 for (int i = 0; i < types.length; i++) {
			            c = types[i];
			            if(c.isAssignableFrom(HttpServerRequest.class))
			                ret[i] = request;
			            if(c.isAssignableFrom(HttpServerResponse.class))
			                ret[i] = response;
			            if(c.isAssignableFrom(RoutingContext.class))
			            	ret[i] = context;
			        }
		             m.setAccessible(true);
		             m.invoke(newIntance,ret);
		             if(!response.ended()){//如果响应浏览器，抛出异常
		            	 logger.error("Plean setting reponse data for [{}].",clazz.getSimpleName());
		            	 response.end("Plean setting reponse data for [" + clazz.getSimpleName() + "].");
		             }
			}else{
				
				Object[] ret = new Object[types.length];
				Class<?> c;
				 for (int i = 0; i < types.length; i++) {
			            c = types[i];
			            if(c.isAssignableFrom(HttpServerRequest.class))
			                ret[i] = request;
			            if(c.isAssignableFrom(HttpServerResponse.class))
			                ret[i] = response;
			            if(c.isAssignableFrom(RoutingContext.class))
			            	ret[i] = context;
			        }
				
		             m.setAccessible(true);
		             Object invoke = m.invoke(newIntance,ret);
		             if(retTypes.isAssignableFrom(String.class)){
		            	 String path = (String)invoke;
		            	 //以rediredt:开头的跳转配置对应的方法
		            	 if(path.startsWith(BaseController.REDIRECT_PREFIX)){
		            		 String rediectPath = path.indexOf("?") !=-1 ? path.substring(0,path.lastIndexOf("?")):path;
		            		 Map<String, Method> methodsMap = classesMethodsMap.get(clazz);
		            		 Method redircetMethod = methodsMap.get(rediectPath);
		            		 if(redircetMethod == null){
		            			 response.setStatusCode(404);
		            			 response.setStatusMessage("not found ["+rediectPath+"]");
		            			 logger.error(this.getClass().getName()+" redirect url:["+path+"] is worng!");
		            		 }
		            		 Class<?>[] methodtypes = redircetMethod.getParameterTypes();
		            		 Object[] methodret = new Object[methodtypes.length];
		            		 Class<?> methodc;
		            		for (int i = 0; i < types.length; i++) {
		            			methodc = methodtypes[i];
		            		            if(methodc.isAssignableFrom(HttpServerRequest.class))
		            		            	methodret[i] = request;
		            		            if(methodc.isAssignableFrom(HttpServerResponse.class))
		            		            	methodret[i] = response;
		            		            if(methodc.isAssignableFrom(RoutingContext.class))
		            		            	methodret[i] = context;
		            		}
		            		redircetMethod.setAccessible(true);
		            		redircetMethod.invoke(newIntance,methodret);
		            	 }else{
		            		 //this.render(path, context);
		            		 Method method = clazz.getMethod("render", new Class<?>[]{String.class,RoutingContext.class});
		            		 method.setAccessible(true);
		            		 method.invoke(clazz.newInstance(),new Object[]{path,context});
		            	 }
		            	 
		         		logger.debug("Entry end [{}][{}][{}ms]",new Object[]{request.uri(),request.method().toString(),System.currentTimeMillis()-now});
		             }
				}
			
	         } catch (IllegalAccessException e) {
	        	 throw new RuntimeException(e.getMessage(),e);
	         } catch (InvocationTargetException e) {
	        	 throw new RuntimeException(e.getMessage(),e);
	         }catch (Exception e) {
	        	 throw new RuntimeException(e.getMessage(),e);
			}
	}
	
	
	
	private void  _redirect(Class<?> clazz,Method m, HttpServerRequest request, HttpServerResponse response,RoutingContext context) {
		this._setReqAndResAndInvokeMethod(clazz, m, request, response, context);
	}
	

	public static void render(String path,RoutingContext context){
		RoutingContext ctx = context;
	    engine.render(ctx, path, res -> {
	      if (res.succeeded()) {
	    	  ctx.response().end(res.result());
	      } else {
	    	 int statusCode =  ctx.response().getStatusCode();
	    	String error_404_path =  (String)context.get(Config.ERROR_404_PATH);
	    	 /*if(StringUtils.isNoneBlank(error_404_path)  && statusCode == 404){
	    		 Map<String, Method> map = classesMethodsMap.get(GetClassLoader.class.getClass());
	    		 Method m = map.get(error_404_path);
	    		 BaseController._redirect(GetClassLoader.class.getClass(), m, ctx.request(), ctx.response(), context);
	    	 }
	    	  if(statusCode == 404){
	    		 
	    	  }else if(statusCode == 500){
	    		  
	    	  }*/
	    	  ctx.fail(res.cause());
	      }
	    });
	}

	public TemplateEngine getEngine() {
		return engine;
	}

	public static ApplicationContext getApplicationContext(){
		ApplicationContext ret = applicationContextLocal.get();
		if(ret == null){
			applicationContextLocal.set(applicationContext);
		}
		return ret;
	}

}
