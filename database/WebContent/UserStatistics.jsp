
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	boolean doesUserExist = Boolean.TRUE == request.getAttribute("doesUserExist");

%> 
   
<!DOCTYPE html>  
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
	
	<form action="userStatistics">
			<input type="text" name="userName" placeholder="Search User">
			<button class="btn btn-success" type="submit">Search</button>
	</form>
	
	<a href="login.jsp"target ="_self" > logout</a><br><br>
	<a href="activitypage.jsp" target="_self">Return to Home</a>&nbsp

	<% if(doesUserExist==true){ %>
	    <div align="center">
	        <table border="1" cellpadding="6">
	            <caption><h2>List User Statistics</h2></caption>
	            <tr>
	                <th>User</th>
	                <th>Buys</th>
	                <th>Sells</th>
	                <th>Transfers</th>
	                <th>Nfts Owned</th>
	            </tr>
	            <c:forEach var="users" items="${listUserStatistics}">
	                <tr style="text-align:center">
	                    <td><c:out value="${users.getEmail()}" /></td>
	                    <td><c:out value="${users.getBuys()}" /></td>
	                    <td><c:out value="${users.getSells()}" /></td>
	                    <td><c:out value="${users.getTransfers()}" /></td>
	                    <td><c:out value="${users.getNftsOwned()}" /></td>
	                            
	            </c:forEach>
	        </table>
		</div>
	<%}%>
	</div>

</body>
</html>