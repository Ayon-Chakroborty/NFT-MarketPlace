
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

	
		<form action="commonNfts">
			<input type="text" name="user1" placeholder="Search User 1">
			<input type="text" name="user2" placeholder="Search User 2">
			<button class="btn btn-success" type="submit">Search</button>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br>
	<a href="activitypage.jsp" target="_self">Return to Home</a>&nbsp
	<%if(doesUserExist==true){ %>
	    <div align="center">
	        <table border="1" cellpadding="6">
	            <caption><h2>List all Common Nfts</h2></caption>
	            <tr>
	                <th>Common Nfts</th>
	
	            </tr>
	            <c:forEach var="nfts" items="${commonNfts}">
	                <tr style="text-align:center">
	                    <td><c:out value="${nfts.getNFTname()}" /></td>             
	            </c:forEach>
	        </table>
		</div>
	<%} %>
	</div>

</body>
</html>