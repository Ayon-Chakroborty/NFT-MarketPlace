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
			 <a href="login.jsp"target ="_self" > Logout</a>&nbsp
			 <a href="SearchForUser.jsp"target ="_self" > Search For User</a>&nbsp
			 <a href="listAllNftsMinted.jsp" target="_self">List NFTS Minted</a>&nbsp
			 <a href="listAllNftsPurchased.jsp" target="_self">List NFTS Purchased</a>&nbsp
			 <a href="listAllNftsSold.jsp" target="_self">List NFTS Sold</a>
			 <a href="bigCreators.jsp" target="_self">List Big Creators</a>&nbsp
			 <a href="bigSellers.jsp" target="_self">List Big Sellers</a>&nbsp
			 <a href="bigBuyers.jsp" target="_self">List Big Buyers</a>&nbsp
			 <a href="HotNfts.jsp" target="_self">List Hot Nfts</a>&nbsp
			 <a href="InactiveUsers.jsp" target="_self">List Inactive Users</a>&nbsp
			 <a href="GoodBuyers.jsp" target="_self">List Good Buyers</a>&nbsp
			 <a href="DiamondHands.jsp" target="_self">List Diamond Hands</a>&nbsp
			 <a href="PaperHands.jsp" target="_self">List Paper Hands</a>&nbsp
			 <a href="UserStatistics.jsp" target="_self">List User Statistics</a>&nbsp
			 <a href="CommonNfts.jsp" target="_self">List Common Nfts</a>&nbsp
			 
		 </div>
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>