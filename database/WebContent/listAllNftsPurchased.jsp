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
	
	<form action = "listAllPurchased">
		<input type = "submit" value = "Get All Purchased NFTs"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br>

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of NFTs Purchased</h2></caption>
            <tr>
                <th>NFT Name</th>
                <th>NFT Description</th>
                <th>Current Owner</th>
                <th>Date Created</th>

            </tr>
            <c:forEach var="nfts" items="${listAllNftsPurchased}">
                <tr style="text-align:center">
                    <td><c:out value="${nfts.getNFTname()}" /></td>
                    <td><c:out value="${nfts.getNFTDescription()}" /></td>
                    <td><c:out value="${nfts.getNFTOwner()}" /></td>
                    <td><c:out value="${nfts.getDateCreated()}" /></td>
       
                    
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>