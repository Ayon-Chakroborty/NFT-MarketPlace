
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
	
	<form action = "hotNfts">
		<input type = "submit" value = "List all Hot Nfts"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br>
	<a href="activitypage.jsp" target="_self">Return to Home</a>&nbsp

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List all Hot Nfts</h2></caption>
            <tr>
                <th>Hot Nfts</th>

            </tr>
            <c:forEach var="nfts" items="${listAllHotNfts}">
                <tr style="text-align:center">
                    <td><c:out value="${nfts.getNFTname()}" /></td>             
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>