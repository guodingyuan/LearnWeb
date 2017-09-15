package com.web.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.web.test.beans.GuoBean;
import com.web.test.jdbc.GuoDao;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private boolean isGet=true;
	private ServletContext servletContext;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//请确认 servlet 的 init() 方法接受一个 ServletConfig 参数，并调用 super.init(config)。
		super.init();
		//获取servletcontext的方法
		servletContext = config.getServletContext( );
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		        
		   
/*		ServletContext 把它的文本消息记录到 Servlet 容器的日志文件中。对于 Tomcat，这些日志可以在 <Tomcat-installation-directory>/logs 目录中找到。
		这些日志文件确实对新出现的错误或问题的频率给出指示。正因为如此，建议在通常不会发生的异常的 catch 子句中使用 log() 函数。*/
		
		        //打印log,用于进行调试
		        servletContext.log("哈哈哈哈哈哈!!!");
		        //通过ServletContext共享数据
		        servletContext.setAttribute("guodingyuan", "郭锭源");
		        
		        // 设置响应内容类型	
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				String title = "使用 "+(isGet?"GET":"POST")+" 方法读取表单数据";				
				String name=request.getParameter("name");			
				
				// 如果不存在 session 会话，则创建一个 session 对象
				HttpSession session = request.getSession(true);
				// 获取 session 创建时间
				Date createTime = new Date(session.getCreationTime());
				// 获取该网页的最后一次访问时间
				Date lastAccessTime = new Date(session.getLastAccessedTime());					 
			    //设置日期输出的格式  
			    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    Integer visitCount = new Integer(0);
				String visitCountKey = new String("visitCount");
				String userIDKey = new String("userID");
				String userID = new String("guodingyuan");
			
				// 检查网页上是否有新的访问者
				if (session.isNew()){
				 	session.setAttribute(userIDKey, userID);
				} else {
				 	visitCount = (Integer)session.getAttribute(visitCountKey);
				 	visitCount = visitCount + 1;
				 	userID = (String)session.getAttribute(userIDKey);
				}
				session.setAttribute(visitCountKey,  visitCount);
				//输出session的超时时间
				//Tomcat默认session超时时间为30分钟，以下将输出1800（单位秒）
				System.out.println("session超时时间："+session.getMaxInactiveInterval());
				
				if(name!=null) {
					if(!isGet)
						// POST处理中文
						name =new String(name.getBytes("ISO8859-1"),"UTF-8");
					String  url=request.getParameter("url");
					
					// 创建 Cookie      
					Cookie nameCookie = new Cookie("name",URLEncoder.encode(name, "UTF-8")); // 中文转码
					Cookie urlCookie = new Cookie("url",url);					
					// 为两个 Cookie 设置过期日期为 24 小时后
					nameCookie.setMaxAge(60*60*24); 
					urlCookie.setMaxAge(60*60*24); 					
					// 在响应头中添加两个 Cookie
					response.addCookie(nameCookie);
					response.addCookie(urlCookie);
			
					String docType = "<!DOCTYPE html> \n";
					out.println(docType +
					    "<html>\n" +
					    "<head><title>" + title + "</title></head>\n" +
					    "<body bgcolor=\"#f0f0f0\">\n" +
					    "<h1 align=\"center\">" + title + "</h1>\n" +
					    "<ul>\n" +
					    "  <li><b>站点名</b>："
					    + name+ "\n" +
					    "  <li><b>网址</b>："
					    + url + "\n" +
					    "</ul>\n" +
					    "</body></html>");
				}else {			
					String docType =
						"<!doctype html public \"-//w3c//dtd html 4.0 " +
						"transitional//en\">\n";
						out.println(docType +
						"<html>\n" +
						"<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
						"<body bgcolor=\"#f0f0f0\">\n" +
						"<h1 align=\"center\">" + title + "</h1>\n" +
						"<table width=\"100%\" border=\"1\" align=\"center\">\n" +
						"<tr bgcolor=\"#949494\">\n" +
						"<th>参数名称</th><th>参数值</th>\n"+
						"</tr>\n");

					Enumeration<?> paramNames = request.getParameterNames();
					if(!paramNames.hasMoreElements()){
						out.print("<tr><td colspan=\"2\" align=\"center\">没有参数</td></tr>");
						//输入请求头
						out.print("<tr><td colspan=\"2\" align=\"center\">以下为请求头参数</td></tr>");
						Enumeration<?> headerNames = request.getHeaderNames();
						while(headerNames.hasMoreElements()) {
							String paramName = (String)headerNames.nextElement();
							out.print("<tr><td>" + paramName + "</td>\n");
							String paramValue = request.getHeader(paramName);
							//对中文字符进行解码
							paramValue=URLDecoder.decode(paramValue, "UTF-8");			
							out.println("<td> " + paramValue + "</td></tr>\n");
						}
						
						//获取session
						out.print("<tr><td colspan=\"2\" align=\"center\">以下为session信息</td></tr>");
						out.print("<tr><td>sessionID</td>\n");
						out.println("<td> " + session.getId() + "</td></tr>\n");
						out.print("<tr><td>session创建时间</td>\n");
						out.println("<td> " + df.format(createTime) + "</td></tr>\n");
						out.print("<tr><td>session最后访问时间</td>\n");
						out.println("<td> " + df.format(lastAccessTime) + "</td></tr>\n");
						out.print("<tr><td>用户ID</td>\n");
						out.println("<td> " + userID + "</td></tr>\n");
						out.print("<tr><td>访问统计</td>\n");
						out.println("<td> " + visitCount + "</td></tr>\n");
						
						//使用JDBC访问数据库
						out.print("<tr><td colspan=\"2\" align=\"center\">以下为使用JDBC访问数据库</td></tr>");
						GuoDao guoDao=new GuoDao();
						try {
							List<GuoBean> guoList = guoDao.getGuoList();
							for(int i=0;i<guoList.size();i++) {
								out.print("<tr><td>第"+(i+1)+"个</td>\n");
								out.println("<td> " + guoList.get(i).toString()+ "</td></tr>\n");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//输入当前的时间
						//使用默认时区和语言环境获得一个日历  
					    Calendar cale = Calendar.getInstance();  
					    //将Calendar类型转换成Date类型  
					    Date tasktime=cale.getTime();  
					    //格式化输出  
					    String nowTime = df.format(tasktime);
					    out.print("<tr><td colspan=\"2\" align=\"center\">当前时间："+nowTime+"</td></tr>");
						out.println("</table>\n</body></html>");
					}else {
						while(paramNames.hasMoreElements()) {
							String paramName = (String)paramNames.nextElement();
							String[] paramValues =request.getParameterValues(paramName);
							paramName=new String(paramName.getBytes("ISO8859-1"),"UTF-8");
							out.print("<tr><td>" + paramName + "</td>\n");
							// 读取单个值的数据
							if (paramValues.length == 1) {
								if (paramValues[0].length() == 0)
									out.println("<td><i>没有值</i></td>");
								else {
									out.println("<td>" + paramValues[0]+"</td>");
								}
							} else {
								// 读取多个值的数据
								out.println("<td><ul>");
								for(int i=0; i < paramValues.length; i++) {
									out.println("<li>" + paramValues[i]+"</li>");
							}
								out.println("</ul></td>");
							}
							out.print("</tr>");
						}
						out.println("\n</table>\n</body></html>");
					   }
					}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isGet=false;
		doGet(request, response);
		isGet=true;
	}

}
