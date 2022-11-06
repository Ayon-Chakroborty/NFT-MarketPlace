<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<head><title>List An NFT for Sale</title></head> 
<body>
	<div align="center">
	
		<center><h1>Welcome! <%=session.getAttribute("username") %> have been successfully logged in</h1> </center>
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="listSale">
			<table border="1" cellpadding="5">
				<tr>
					<th>NFT Listed For Sale*: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftListed" size="45"  placeholder="NFT Name" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Price*: </th>
					<td align="left" colspan="3">
						<input type="text" name="price">
					</td>
				</tr>
				<tr>
					<th>Posting Date*: </th>
					<td align="left" colspan="3">
						<input type="date" name="postingDate">
					</td>
				</tr>
				<tr>
					<th>Ending Date*: </th>
					<td align="left" colspan="3">
						<input type="date" name="endingDate" size="45"">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Post NFT Listing for Sale"/>
					</td>
				</tr>
			</table>
			
			<a href="login.jsp" target="_self">Return to Login Page</a>
			<a href="activitypage.jsp" target="_self">Return to Home</a>
			
		</form>
	</div>
</body>