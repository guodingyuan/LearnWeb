<%-- JSP指令 --%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello World - By guodingyuan.com</title>
</head>
<body>
 <%-- 通过ServletContext获取到共享的数据 --%>
<% System.out.println(application.getAttribute("guodingyuan")); %>
	<%-- JSP声明 --%>
	<%! 
  private int initVar=0;
  private int serviceVar=0;
  private int destroyVar=0;
%>

	<%!
  public void jspInit(){
    initVar++;
    System.out.println("jspInit(): JSP被初始化了"+initVar+"次");
  }
  public void jspDestroy(){
    destroyVar++;
    System.out.println("jspDestroy(): JSP被销毁了"+destroyVar+"次");
  }
%>

	<%
  serviceVar++;
  System.out.println("_jspService(): JSP共响应了"+serviceVar+"次请求");

  String content1="初始化次数 : "+initVar;
  String content2="响应客户请求次数 : "+serviceVar;
  String content3="销毁次数 : "+destroyVar;
%>
	<h1>菜鸟教程 JSP 测试实例</h1>
	<%-- JSP表达式 --%>
	<p><%=content1 %></p>
	<p><%=content2 %></p>
	<p><%=content3 %></p>

	<hr />
	Hello World!
	<br />
	<p>

		今天是:<%=(new java.util.Date()).toLocaleString()%>
		<%-- JSP注释，注释内容不会被发送至浏览器甚至不会被编译 --%>
	</p>
	<!-- HTML注释，通过浏览器查看网页源代码时可以看见注释内容-->
	<div id="demo"></div>
	<hr />
	<%! int day = 3;%>
	<h3>IF...ELSE 实例</h3>
	<% if (day == 1 | day == 7) { %>
	<p>今天是周末</p>
	<% } else { %>
	<p>今天不是周末</p>
	<% } %>
	<hr>
	<h3>SWITCH...CASE 实例</h3>
	<% 
switch(day) {
case 0:
   out.println("星期天");
   break;
case 1:
   out.println("星期一");
   break;
case 2:
   out.println("星期二");
   break;
case 3:
   out.println("星期三");
   break;
case 4:
   out.println("星期四");
   break;
case 5:
   out.println("星期五");
   break;
default:
   out.println("星期六");
}
%>
	<hr>
	<h3>For 循环实例</h3>
	<%for (int fontSize = 1; fontSize <= 3; fontSize++){ %>
	<font color="green" size="<%= fontSize %>"> 菜鸟教程 </font>
	<br />
	<%}%>
	<hr>

	<h3>While 循环实例</h3>
	<%! int fontSize=0; %>
	<%while ( fontSize <= 3){ %>
	<font color="green" size="<%= fontSize %>"> 菜鸟教程 </font>
	<br />
	<%fontSize++;%>
	<%}%>
	<hr>

 	<%--在JSP文件被转换成Servlet的时候引入文件 --%>
<%--	<%@ include file="/index.jsp"%>
	<hr>
	插入文件的时间是在页面被请求的时候
	<jsp:include page="/index.jsp" flush="true" />
	<hr>
 --%>
    <%--jsp动作元素 ：Javabean--%>
    <jsp:useBean id="testBean" class="com.web.test.beans.TestBean"/>
	<jsp:setProperty name="testBean" property="message" value="大家好！我是郝仁！！！" />
	<p>输出消息：</p>
	<jsp:getProperty name="testBean" property="message"/>
	
	<%--jsp:forward动作把请求转到另外的页面。--%>
	<%-- <jsp:forward page="/index.jsp"/> --%>
	
	<hr>
	
    <%-- <jsp:element> 、 <jsp:attribute>、 <jsp:body>动作元素动态定义XML元素 --%>
	<jsp:element name="xmlElement">
		<jsp:attribute name="xmlElementAttr">
		   属性值
		</jsp:attribute>
		<jsp:body>
		   XML 元素的主体
		</jsp:body>
	</jsp:element>
	
	<hr>
	
	<%--<jsp:text>动作元素允许在JSP页面和文档中使用写入文本的模板 --%>
	<p>模板数据</p>
	<jsp:text>模板数据</jsp:text>
	
	<hr>
	
	
	
	<%--获取HTTP请求头 --%>
	<table width="100%" border="1" align="center">
       <tr bgcolor="#949494">
          <th>Header Name</th><th>Header Value(s)</th>
       </tr>    
     <%!private String paramName,paramValue; %>  
     <%
       Enumeration<String> headerNames = request.getHeaderNames();
	   while(headerNames.hasMoreElements()) {
	      paramName = (String)headerNames.nextElement();%>
	      <tr><td><%= paramName %></td>
	      <% paramValue = request.getHeader(paramName);%>
	      <td><%= paramValue %></td></tr>
	   <% } %>  
       
    <%--
	   Enumeration<String> headerNames = request.getHeaderNames();
	   while(headerNames.hasMoreElements()) {
	      String paramName = (String)headerNames.nextElement();
	      out.print("<tr><td>" + paramName + "</td>\n");
	      String paramValue = request.getHeader(paramName);
	      out.println("<td> " + paramValue + "</td></tr>\n");
	   }
	--%>
	
	 <%--HTTP响应头--%>   
	<%--
	   // 设置每隔5秒自动刷新
	   response.setIntHeader("Refresh", 60);
	   // 获取当前时间
	   Calendar calendar = new GregorianCalendar();
	   String am_pm;
	   int hour = calendar.get(Calendar.HOUR);
	   int minute = calendar.get(Calendar.MINUTE);
	   int second = calendar.get(Calendar.SECOND);
	   if(calendar.get(Calendar.AM_PM) == 0)
	      am_pm = "AM";
	   else
	      am_pm = "PM";
	   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
	   out.println("当前时间: " + CT + "\n");
   --%> 
   </table>
   
   <hr>
   	<%--
	   // 设置错误代码，并说明原因
	   response.sendError(407, "Need authentication!!!" );
   --%>
  
  
  
  
  <%--设置 Cookie--%>>
  <%
	   // 编码，解决中文乱码   
	   String a=request.getParameter("name");
	   if(a!=null){
		   String str = URLEncoder.encode(request.getParameter("name"),"utf-8");  
		   // 设置 name 和 url cookie 
		   Cookie name = new Cookie("name",
				   str);
		   Cookie url = new Cookie("url",
					  request.getParameter("url"));
		
		   // 设置cookie过期时间为24小时。
		   name.setMaxAge(60*60*24); 
		   url.setMaxAge(60*60*24); 
		
		   // 在响应头部添加cookie
		   response.addCookie( name );
		   response.addCookie( url );
	   }
	   
   %>
  
    <%--获取 Cookie--%>>
	<%
	   Cookie cookie = null;
	   Cookie[] cookies = null;
	   // 获取cookies的数据,是一个数组
	   cookies = request.getCookies();
	   if( cookies != null ){
	      out.println("<h2> 查找 Cookie 名与值</h2>");
	      for (int i = 0; i < cookies.length; i++){
	         cookie = cookies[i];
	        
	         out.print("参数名 : " + cookie.getName());
	         out.print("<br>");
	         out.print("参数值: " + URLEncoder.encode(cookie.getValue(), "utf-8") +" <br>");
	         out.print("------------------------------------<br>");
	      }
	  }else{
	      out.println("<h2>没有发现 Cookie</h2>");
	  }
	%>

  <hr>

   <h3>使用 GET 方法读取数据</h3>
	<ul>
	<li><p><b>站点名:</b>
	   <%= request.getParameter("name")%>
	</p></li>
	<li><p><b>网址:</b>
	   <%= request.getParameter("url")%>
	</p></li>
	</ul>
	
	<hr>

   <h3>使用 POST 方法读取数据</h3>
	<ul>
	<li><p><b>站点名:</b> 
	<%
	    String name=null;
		if(request.getParameter("name")!=null){
			// 解决中文乱码的问题
			name = new String((request.getParameter("name")).getBytes("ISO-8859-1"),"UTF-8");
		}
	%>
    <%=name%>
	</p></li>
	<li><p><b>网址:</b>
	   <%= request.getParameter("url")%>
	</p></li>
	</ul>

	<hr>

	<h3>从复选框中读取数据</h3>
	<ul>
	<li><p><b>Google 是否选中:</b>
	   <%= request.getParameter("google")%>
	</p></li>
	<li><p><b>菜鸟教程是否选中:</b>
	   <%= request.getParameter("runoob")%>
	</p></li>
	<li><p><b>淘宝是否选中:</b>
	   <%= request.getParameter("taobao")%>
	</p></li>
	</ul>
	
    <hr>
    
    <%--session的应用 --%>
	<%
	
	   // 获取session创建时间
	   Date createTime = new Date(session.getCreationTime());
	   // 获取最后访问页面的时间
	   Date lastAccessTime = new Date(session.getLastAccessedTime());
	
	   String title = "再次访问菜鸟教程实例";
	   Integer visitCount = new Integer(0);
	   String visitCountKey = new String("visitCount");
	   String userIDKey = new String("userID");
	   String userID = new String("ABCD");
	
	   // 检测网页是否由新的访问用户
	   if (session.isNew()){
	      title = "访问菜鸟教程实例";
	      session.setAttribute(userIDKey, userID);
	      session.setAttribute(visitCountKey,  visitCount);
	   } else {
		   visitCount = (Integer)session.getAttribute(visitCountKey);
		   visitCount ++;
		   userID = (String)session.getAttribute(userIDKey);
		   session.setAttribute(visitCountKey,  visitCount);
	   }
	%>

	<h2>Session 跟踪</h2>
	
	<table border="1"> 
	<tr bgcolor="#949494">
	   <th>Session 信息</th>
	   <th>值</th>
	</tr> 
	<tr>
	   <td>id</td>
	   <td><% out.print( session.getId()); %></td>
	</tr> 
	<tr>
	   <td>创建时间</td>
	   <td><% out.print(createTime); %></td>
	</tr> 
	<tr>
	   <td>最后访问时间</td>
	   <td><% out.print(lastAccessTime); %></td>
	</tr> 
	<tr>
	   <td>用户 ID</td>
	   <td><% out.print(userID); %></td>
	</tr> 
	<tr>
	   <td>访问次数</td>
	   <td><% out.print(visitCount); %></td>
	</tr> 
	</table> 
    
    
   <%--
	   // 重定向到新地址
	   String site = new String("http://www.runoob.com");
	   response.setStatus(response.SC_MOVED_TEMPORARILY);
	   response.setHeader("Location", site); 
   --%>
     <hr>
     
     <%--点击量统计,在 web 服务器重启后，计数器会被复位为 0 --%>
     <% 
	    Integer hitsCount = (Integer)application.getAttribute("hitCounter");
	    if( hitsCount ==null || hitsCount == 0 ){
	       /* 第一次访问 */	
	       hitsCount = 1;
	    }else{
	       /* 返回访问值 */
	       hitsCount += 1;
	    }
	    application.setAttribute("hitCounter", hitsCount);
	%>
   <p>页面访问量为: <%= hitsCount%></p>
   
   <hr>
   
</body>
<script type="text/javascript">
  document.getElementById("demo").innerHTML="今天是:"+Date();
</script>
</html>