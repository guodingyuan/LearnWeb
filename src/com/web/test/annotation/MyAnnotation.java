package com.web.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //①声明注解的保留期限  
@Target(ElementType.METHOD)//②声明可以使用该注解的目标类型  
public @interface MyAnnotation {//③定义注解  
	 boolean value() default true;//④声明注解成员  
}

/*
1)成员以无入参无抛出异常的方式声明，如boolean value(String str)、boolean value() throws Exception等方式是非法的；
2)可以通过default为成员指定一个默认值，如String level() default "LOW_LEVEL"、int high() default 2是合法的，当然也可以不指定默认值；
3)成员类型是受限的，合法的类型包括原始类型及其封装类、String、Class、enums、注解类型，以及上述类型的数组类型。如ForumService value()、List foo()是非法的。
*/

/*
    如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号（=），如@NeedTest(true)。
    注解类拥有多个成员时，如果仅对value成员进行赋值则也可不使用赋值号，如果同时对多个成员进行赋值，
    则必须使用赋值号，如DeclareParents (value = "NaiveWaiter", defaultImpl = SmartSeller.class)。
    注解类可以没有成员，没有成员的注解称为标识注解，解释程序以标识注解存在与否进行相应的处理；
    此外，所有的注解类都隐式继承于java.lang.annotation.Annotation，但注解不允许显式继承于其他的接口。 
*/