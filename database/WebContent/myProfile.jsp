<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	<form action = "myProfile">
		<input type = "submit" value = "Show User Information"/>
	</form>
	<a href="activitypage.jsp"target ="_self" > Home</a><br><br>
<h1>My Profile</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Birthday</th>
                <th> Address </th>
                <th>cash_bal($)</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value= "${users.address_zip_code} ${users.address} ${users.address_city} ${users.address_state}" /></td>
                    <td><c:out value="${users.balance}"/></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>