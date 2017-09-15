package com.web.test.proxy;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.web.test.proxy.cglibproxy.CglibProxy;
import com.web.test.proxy.cglibproxy.CglibVendor;
import com.web.test.proxy.jdkproxy.DynamicProxy;
import com.web.test.proxy.jdkproxy.Sell;
import com.web.test.proxy.jdkproxy.Vendor;

/**
 * @author Administrator
 * 动态生成代理类
 * Java的反射机制和动态代理为AOP提供了技术支持，从该例子也可以很好滴看出这一点
 */
public class ProxyTest {
		
	@Test
	public void testJDKProxy() {
		//创建中介类实例
    	DynamicProxy inter = new DynamicProxy(new Vendor());
    	//加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
     	System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    	//获取代理类实例sell
    	Sell sell = (Sell)(Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[] {Sell.class}, inter));
     	//通过代理类对象调用代理类方法，实际上会转到invoke方法调用
    	sell.sell();
    	sell.ad();
	}
	
	@Test
	public void testCGLibProxy() {
		CglibProxy cglibProxy=new CglibProxy();
		CglibVendor cglibVendor = (CglibVendor) cglibProxy.getProxy(CglibVendor.class);
		cglibVendor.sell();
		cglibVendor.ad();
	}
}
