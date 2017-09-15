package com.web.test.proxy.cglibproxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * @author Administrator
 * cglib动态代理需引入jar包：cglib-3.2.5.jar
 *                       asm-5.2.jar（注意：这个jar为辅助用的，尽量用高版本的，不然会有冲突的） 
 */
public class CglibProxy implements MethodInterceptor {


    public Object getProxy(Class<?> clazz) {  
		Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(clazz); //①设置需要创建子类的类  
        enhancer.setCallback(this);   
        return enhancer.create(); //②通过字节码技术动态创建子类实例  
    }  
  
	@Override
	  //③拦截父类所有方法的调用  
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		//要求：在执行委托类中的方法之前输出“before”
		System.out.println("Cglib动态代理before");  
        Object result=proxy.invokeSuper(obj, args); 
         //要求：在执行完毕后输出“after”
      	System.out.println("Cglib动态代理after");
        return result;  
	}

}
