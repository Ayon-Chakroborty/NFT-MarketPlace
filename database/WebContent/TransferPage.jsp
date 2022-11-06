<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	
		<center><h1>Welcome! <%=session.getAttribute("username") %> Welcome to the Transfer Page!</h1> </center>
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="transfer">
			<table border="1" cellpadding="5">
				<tr>
					<th>NFT To Be Transfered*: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftToBeTransferred" size="45"  placeholder="NFT Name" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Transferred To: </th>
					<td align="left" colspan="3">
						<input type="text" name="transferredTo" size="45"  placeholder="Transferred To" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Transfer Date*: </th>
					<td align="left" colspan="3">
						<input type="date" name="transferDate">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Transferr NFT"/>
					</td>
				</tr>
			</table>
			
			<a href="login.jsp" target="_self">Return to Login Page</a>
			<a href="activitypage.jsp" target="_self">Return to Home</a>
			
		</form>
	</div>
</body>
</html>