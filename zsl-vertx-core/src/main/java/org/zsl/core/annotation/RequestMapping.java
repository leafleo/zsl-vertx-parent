package org.zsl.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.vertx.core.http.HttpMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestMapping {
	public  String value();

	public HttpMethod[] method() default { HttpMethod.GET, HttpMethod.POST };
	
	public String headers() default "text/html;charset=utf-8";
}
