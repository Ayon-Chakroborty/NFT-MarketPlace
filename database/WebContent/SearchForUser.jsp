<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	boolean doesUserExist = Boolean.TRUE == request.getAttribute("doesUserExist");

	System.out.println("In SearchForUser.jsp");
	System.out.println("doesUserExist: " + doesUserExist);

%>       
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>	
	<div class="container"></div>	
	<div class="container">
		<form class="d-flex justify-content-center h-100" action="searchUser">
			<input class="form-control" type="text" name="userName" placeholder="Search">
			<span class="input-group-btn">
				<button class="btn btn-success" type="submit">Search</button>
			</span>
		</form>
		
		<div class="container"></div>	
		<div class="container"></div>	
		
		<% if (doesUserExist == true){ %>
		<div class="container">
		  <div class="row">
		    <div class="col-sm-4"></div>
		    <div class="col-sm-4">
		    <div style="object-fit: contain;">
		    <img style="padding-top: 3em" class="center-block" src="${nftInfo.getImageLink()}">
		    </div>
		    </div>
		    <div class="col-sm4"></div>
		  </div>
		  <div class="row">
		    <div class="col-sm-4"></div>
		    <div class="col-sm-8">
		    	<h5>User: ${userInfo.getEmail()}</h5>
		    	<h5>Balance: ${userInfo.getBalance()}</h5>
		    	
		    </div>	    	
		    </div>
		  <% } %><%  else { %>
		  <div class="container">
		  	<div class="row"> 
		  		<div class="col-sm-12">
		  			<div class="text-center"> 
		  				<h5 style="padding: 3em 4em">Please Search For A User.</h5>
		  			</div>
		  		</div>
		  	</div>
		  </div>
		  <% } %>
		  
		  
		</div>
		<div class="row"  style="text-align:center">
		<div class="col-sm-12">
		<a href="login.jsp" target="_self">Return to Login Page</a>&nbsp
		<a href="activitypage.jsp" target="_self">Return to Home</a>&nbsp
		</div>
		</div>
	</div>
	

</body>
</html>