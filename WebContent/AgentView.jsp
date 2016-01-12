<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.google.gson.JsonArray" %>
<%@page import="com.google.gson.JsonElement" %>
<%@page import="net.sf.json.JSONArray" %>
<%@page import="com.google.gson.JsonObject" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agente</title>
</head>
<body>
<h1>Agente para configurar una casa</h1>

 <jsp:scriptlet>
  String [] elementos = new String []{"Router","Thermostat","SmartTV"};
	pageContext.setAttribute("elementos", elementos);
</jsp:scriptlet>

 
<c:forEach var="element" items="${pageScope.elementos}" >
    <td>
        <li><c:set var="titleURL">
               <c:url value="AgentServlet"></c:url>
               </c:set>
          <a href="${titleURL}">${element}</a>
				</li>
    </td>
</c:forEach>


<c:if test="${agente!= null}">
<b><c:out value="Agente router dice:"/></b>
</c:if>
<% /*
<br><textarea rows="4" cols="30">
<c:out value="${agente}"/>
</textarea>
</c:if>
<form action="WebAgentServlet" method="get">
<c:forEach var="menu" items="${json}" >
<input type="submit" name="submit" value="${menu}"/>
</c:forEach>
</form>
</center>*/
%>

<form id="userinput" method="get" action="Agent" >
  <input type="hidden" name="userAgent" value="web_html_v1" />
  <input type="hidden" name="user" value="miguel" />
  <input type="hidden" name="bot" value="Nico" />
  <input type="hidden" name="type" value="json" />
  <input type="text" name="q"/>
  <button type="submit" name="submit" value="send" >Send</button>
</form>

    <img id="bot_avatar" src="images/avatars/happy.png" />

  <body>
      <div class="" id="bot_chat_window">
          <div id="upper_bar">
            <h3>
              <!--<a id="bot_name" href="#">Duke</a>-->
              <a id="max_icon" href="#"></a>
              <span style="clear: both;"></span>
            </h3>
          </div>
          <div id="screen_wrapper">
            <div id="msg_dialog"></div>
            <div id="screen">
            </div>
            <div id="meta_msgs"></div>
            <div id="userbar">    
                <form id="userinput" method="get" action="Agent" >
                  <input type="hidden" name="userAgent" value="web_html_v1" />
                  <input type="hidden" name="bot" value="Nico" />
                  <input type="hidden" name="type" value="json" />
                  <input type="text" name="q" />
                  <input type="submit" name="submit" value="Enviar" />
                </form>
            </div><!-- #userbar -->
          </div><!-- #screen_wrapper -->
      </div><!-- #bot_chat_window -->
  </body>
</html>
</body>
</html>