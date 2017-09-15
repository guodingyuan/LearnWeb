package com.web.test.proxy.jdkproxy;


/**
 * @author Administrator
 * 委托类
 */
public class Vendor implements Sell{

	@Override
	public void sell() {
		System.out.println("In sell method");
	}

	@Override
	public void ad() {
		System.out.println("ad method");
	}

}
