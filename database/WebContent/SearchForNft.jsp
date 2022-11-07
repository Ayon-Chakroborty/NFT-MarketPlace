<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<form class="d-flex justify-content-center h-100" action="searchNft">
			<input class="form-control" type="text" name="nftName" placeholder="Search">
			<span class="input-group-btn">
				<button class="btn btn-success" type="submit">Search</button>
			</span>
		</form>
		<div class="container"></div>	
		<div class="container"></div>	
		<div class="container">
		  <div class="row">
		    <div class="span4"></div>
		    <div class="span4"><img class="center-block" src="${nftInfo.getImageLink()}"></div>
		    <div class="span4">
		    	<h3>NFT Name: ${nftInfo.getNFTname()}</h3>
		    	<h3>NFT Owner: ${nftInfo.getNFTOwner()}</h3>
		    	<h3>NFT Description: ${nftInfo.getNFTDescription()}</h3>		    	
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>