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
	 	<div">
		 	 <a href="CreateNFTs.jsp" target="_self">Mint An NFT</a> &nbsp <!-- nbsp used to put spaces inbetween links. Will remove once we include css to application -->
		 	 <a href="saleListNftForm.jsp" target="_self">List An NFT For Sale</a> &nbsp
		 	 <a href="TransferPage.jsp" target="_self">Transfer An NFT</a> &nbsp
		 	 <a href="SearchForNft.jsp" target="_self">Search For An NFT</a> &nbsp	
		 	 <a href="SearchForUser.jsp" target="_self">Search For A User</a> &nbsp		  
			 <a href="login.jsp"target ="_self" > Logout</a><br><br> 
		 </div>
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>