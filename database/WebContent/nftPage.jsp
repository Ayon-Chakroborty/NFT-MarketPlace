<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	boolean doesNftExist = Boolean.TRUE == request.getAttribute("doesNftExist");
	boolean doesSaleListingExist = Boolean.TRUE == request.getAttribute("saleListingExists");
	boolean isDateWithinBuyingTime = Boolean.TRUE == request.getAttribute("dateWithinListingDateRange");
	System.out.println("Date within time:" + isDateWithinBuyingTime);
	boolean doesUserHaveEnoughFunds = Boolean.TRUE == request.getAttribute("canUserBuyNft");
	
	System.out.println("In nftPage.jsp");
	System.out.println("doesNftExist: " + doesNftExist);
	System.out.println("doesSaleListingExist: " + doesSaleListingExist);
	System.out.println("isDateWithinBuyingTime: " + isDateWithinBuyingTime);
	System.out.println("doesUserHaveEnoughFunds: " + doesUserHaveEnoughFunds);


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
		<form class="d-flex justify-content-center h-100" action="renderNftPage">
			<input name="nftName" placeholder="Search" type="hidden">
			<span class="input-group-btn">
				<button class="btn btn-success" type="submit">Get NFT INFO</button>
			</span>
		</form>
		
		<div class="container"></div>	
		<div class="container"></div>	
		
		<% if (doesNftExist == true){ %>
		<div class="container">
		  <div class="row">
		    <div class="col-sm-4"></div>
		    <div class="col-sm-4">
		    <div style="object-fit: contain;">
		    <img style="padding-top: 3em" class="center-block" src="${nftInfo2.getImageLink()}">
		    </div>
		    </div>
		    <div class="col-sm4"></div>
		  </div>
		  <div class="row">
		    <div class="col-sm-4"></div>
		    <div class="col-sm-8">
		    	<h5>NFT Name: ${nftInfo2.getNFTname()}</h5>
		    	<h5>NFT Owner: ${nftInfo2.getNFTOwner()}</h5>
		    	<h5>NFT Description: ${nftInfo2.getNFTDescription() }</h5>
		    </div>	    	
		    </div>
	 <% if (doesSaleListingExist == true) { %>
		    	<% if (isDateWithinBuyingTime == true) { %>
					 <div class="row">
					    <div class="col-sm-4"></div>
					    <div class="col-sm-8">
					    	<h5>NFT Price ETH: ${saleListing.getPrice()}</h5>
					    </div>	    	
				    </div>
		    		<% if (doesUserHaveEnoughFunds == true) { %>
					    <div class="row">
					    	<form action="buyNft">
					    		<input type=hidden name="nftName" value="${nftInfo.getNFTname()}">
					    		<button class="btn btn-success" type="submit" id="buyNftButton">Buy NFT</button>
					    	</form>
					    </div>
					
					<% } else { %>
						 <div class="row">
					    		<button class="btn btn-secondary" dissabled>Not Enough Funds To Buy NFT</button>
					    </div>
					<% } %>
				<% } %>
		  	
		  	<% } %>
		  <% } else { %>
		  <div class="container">
		  	<div class="row"> 
		  		<div class="col-sm-12">
		  			<div class="text-center"> 
		  				<h5 style="padding: 3em 4em">Please Search For An NFT.</h5>
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