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
  String [] elementos = new String []{"Router","Calefacción","Persianas"};
	pageContext.setAttribute("elementos", elementos);
	
	 JsonArray json = (JsonArray)session.getAttribute("json");
	 System.out.println(json);
	 JsonObject lore= new JsonObject();
	 JsonElement fin ;
	 JsonElement hola;
	if (json != null){
		 hola = json.get(1);
		 lore= hola.getAsJsonObject();
		 System.out.println(lore);
		 fin=lore.get("menu");
		 System.out.println(fin);
		
	}else{
		System.out.println( "caca");
	}
	
	
	
	
</jsp:scriptlet>


 <c:out value="que tal : ${pageScope.hola}" />  
 
<c:forEach var="element" items="${pageScope.elementos}" >
    <td>
        <li><c:set var="titleURL">
               <c:url value="AgentServlet"></c:url>
               </c:set>
          <a href="${titleURL}">${element}</a>
				</li>
    </td>
</c:forEach>

   	 
<c:if test="${json!=null}">
<c:forTokens var="elements" items="${session.getAttribute("json")} delims="," >
   <c:out value="${elements}"/>

</c:forTokens>
    
</c:if>

</body>
</html>