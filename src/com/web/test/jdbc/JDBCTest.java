package com.web.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	
	public static final String URL = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8&useSSL=false";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	
    public static void main(String[] args) throws Exception {
    	normal();//基本示例
    	GuoDao guoDao=new GuoDao();
    	//增
//    	GuoBean guoBean=new GuoBean("小孩子2", 100, true);
//    	guoDao.addGuo(guoBean);
    	//删
//    	guoDao.deleteGuo(20);
    	//改
//    	guoDao.updateGuo(new GuoBean("小孩子2", 100, true), 25);
    	//查所有
    	//System.out.println(guoDao.getGuoList().toString());
    	//查单个
    	System.out.println(guoDao.getGuo(27));
   } 
    
    //基本示例
    private static void normal() throws Exception{
    	//1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM guo");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println(rs.getString("name")+" 年龄："+rs.getInt("age")+" 性别："+ (rs.getBoolean("sex")?"男":"女"));
        }      
	}
}
