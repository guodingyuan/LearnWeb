package com.web.test.annotation;


import java.lang.reflect.Method;

import org.junit.Test;


/**
 * @author Administrator
 * 对注解进行测试
 */
public class AnnotationTest {
	
    @Test
    public void testAnnotation() throws Exception{
        //①得到TestBean对应的Class对象  
        Class<TestBean> clazz = TestBean.class;   
        //②得到TestBean对应的Method数组  
        Method[] methods = clazz.getDeclaredMethods();         
        System.out.println("总共的方法数："+methods.length);  
        
       /* ClassLoader loader = Thread.currentThread().getContextClassLoader();  
        Class clazz = loader.loadClass("com.web.test.annotation.TestBean");  */
        TestBean testBean = (TestBean)clazz.newInstance();  
             
        for (Method method : methods) { 
        	//用于判定是否含有某类注解
        	System.out.println(method.getName()+"是否包含@MyAnnotation？"+method.isAnnotationPresent(MyAnnotation. class));
        
            //③获取方法上所标注的注解对象  
        	MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);  
            if (myAnnotation != null) {  
            	//获取得到注解
            	System.out.println(method.getName()+"有注解");
                if (myAnnotation.value()) {  
                    System.out.println(method.getName() + "值为true");  
                } else {  
                    System.out.println(method.getName() + "值为false");  
                }  
            }else {
            	//获取得到注解
            	System.out.println(method.getName()+"无注解");
			}  
            //通过反射调用内部方法
            method.invoke(testBean,(Object[])null);  
        }  
    }
}
