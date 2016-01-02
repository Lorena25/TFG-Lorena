<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
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

</body>
</html>