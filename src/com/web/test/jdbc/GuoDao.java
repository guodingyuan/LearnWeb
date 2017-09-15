package com.web.test.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.web.test.beans.GuoBean;

public class GuoDao {
    //增
    public void addGuo(GuoBean guoBean) throws SQLException {
    	  //获取连接
        Connection conn = DBUtil.getConnection();
        //sql
        String sql = "INSERT INTO guo(name, age, sex)"
                +"values("+"?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setString(1, guoBean.getName());
        ptmt.setInt(2, guoBean.getAge());
        ptmt.setBoolean(3, guoBean.isSex());
 
        //执行
        ptmt.execute();
    }
    //删
    public void deleteGuo(int id) throws SQLException {
    	//获取连接
        Connection conn = DBUtil.getConnection();
        //sql, 每行加空格
        String sql = "delete from guo where id=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);

        //传参
        ptmt.setInt(1, id);

        //执行
        ptmt.execute();
    }
    //改
    public void updateGuo(GuoBean guoBean,int id) throws SQLException {
    	  //获取连接
        Connection conn = DBUtil.getConnection();
        //sql, 每行加空格
        String sql = "UPDATE guo" +
                " set name=?, age=?, sex=?"+         
                " where id=?";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setString(1, guoBean.getName());
        ptmt.setInt(2, guoBean.getAge());
        ptmt.setBoolean(3, guoBean.isSex());
        ptmt.setInt(4, id);
   
        //执行
        ptmt.execute();
    }
    //查——列表
    public List<GuoBean> getGuoList() throws SQLException {
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM guo");

        List<GuoBean> guoBeans = new ArrayList<GuoBean>();
        GuoBean guoBean = null;
        while(rs.next()){
        	guoBean = new GuoBean();
        	guoBean.setId(rs.getInt("id"));
        	guoBean.setName(rs.getString("name"));
        	guoBean.setAge(rs.getInt("age"));
            guoBean.setSex(rs.getBoolean("sex"));
            guoBean.setCreateTime(rs.getString("createTime"));
            guoBean.setUpdateTime(rs.getString("updateTime"));
        	guoBeans.add(guoBean);
        }
        return guoBeans;
    }
    //查——单个
    public GuoBean getGuo(int id) throws SQLException {
    	 Connection conn = DBUtil.getConnection();
    	  //sql, 每行加空格
         String sql = "SELECT * FROM guo where id =?";
         //预编译
         PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

         //传参
         ptmt.setInt(1, id);
         //执行
         ResultSet rs = ptmt.executeQuery();
         
         GuoBean guoBean = null;
         while(rs.next()){
         	guoBean = new GuoBean();
         	guoBean.setId(rs.getInt("id"));
         	guoBean.setName(rs.getString("name"));
         	guoBean.setAge(rs.getInt("age"));
             guoBean.setSex(rs.getBoolean("sex"));
             guoBean.setCreateTime(rs.getString("createTime"));
             guoBean.setUpdateTime(rs.getString("updateTime"));      
         }
         return guoBean;
    }
}
