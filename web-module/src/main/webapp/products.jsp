<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

	<h2>All Products</h2>
	<table padding="4" border="1" >
		<tr>
			<th>products</th>
		</tr>
		<c:forEach var="product" items="${allProducts}">
			<tr>
				<td><c:out value="${product}"/></td>
			</tr>
		</c:forEach>
	</table>

	<h2>Cart Products</h2>
    	<table padding="4" border="1" >
    		<tr>
    			<th>products</th>
    		</tr>
    		<c:forEach var="product" items="${cartProducts}">
    			<tr>
    				<td><c:out value="${product.description}"/></td>
    			</tr>
    		</c:forEach>
    	</table>
	
</body>
</html>