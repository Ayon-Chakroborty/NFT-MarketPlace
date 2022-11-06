<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
   
<%
	// Redirects the user to login.jsp if the user is null
	if(session.getAttribute("username") == null){
		System.out.println("Username is none in this page, redirecting user to login page");
		response.sendRedirect("login.jsp");
	}
	else{
		System.out.println((session.getAttribute("username")));
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! <%=session.getAttribute("username") %> have been successfully logged in</h1> </center>

 
	<body>
	 <center>
	 	 <a href="CreateNFTs.jsp" target="_self">Mint an NFT</a>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>