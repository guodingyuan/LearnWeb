package com.web.test.annotation;

public class TestBean {
	
	@MyAnnotation(true)
    public void eat() {
		System.out.println("我要吃饭了！");
	}
    
	@MyAnnotation(false)
    public void sleep() {
		System.out.println("我要睡觉了！");
	}
    
    public void study() {
		System.out.println("我要学习了！");
	}
}
