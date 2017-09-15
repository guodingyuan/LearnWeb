package com.web.test.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @author Administrator
 * 动态代理的中介类，用作“调用处理器”
 */
public class DynamicProxy implements InvocationHandler{

	private Object obj; //obj为委托类对象;

	public DynamicProxy(Object obj) {
     	this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//要求：在执行委托类中的方法之前输出“before”
		System.out.println("JDK动态代理before");
		Object result = method.invoke(obj, args);
		//要求：在执行完毕后输出“after”
		System.out.println("JDK动态代理after");
		return result;
	}

}
