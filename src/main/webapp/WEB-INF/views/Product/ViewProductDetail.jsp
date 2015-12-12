<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%
 	String errs = (String) request.getAttribute("errorss");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet"/>
	<script src="<c:url value='/resources/js/jquery.js' />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>
<div id="container" class="container" >
	<div class="row">
		<div class="col-sm-9 col-md-9 main">
		<h1 id="productTitle" class="page-header">Product Detail</h1>
		<div id="editContainer">
			<div>
				<label class="bold labelLength_20">Product Name:</label>
				${product.getProductName()}
			</div>
			<div>
				<label class="bold labelLength_20">Product Code:</label>
				${product.getProductCode()}
			</div>
			<div>
				<label class="bold labelLength_20">Sales Price:</label>
				<span id="pUnitCost">${product.getUnitCost()}</span>
			</div>	
			<div>
				<label class="bold labelLength_20">Category:</label>
				<span id="pCategory">${product.getCategory()}</span>
			</div>								
			<div>
				<label class="bold labelLength_20">Description:</label>
				<span id="pDescription">${product.getDescription()}</span>
			</div>
			<hr>	
		<button  id="editProductBtn"  class="btn btn-success">Edit Product</button>
		<button  id="removeProductBtn"  class="btn btn-danger">Delete Product</button>					
		</div>
		
		<div id="updateContainer" class="center-block">
		<sf:form name="updateProduct" id="updateProductCommand" method="post" action="/abankus/products/updateProducts">
			<div>
				<label class="bold labelLength_20">Product Name:</label>
				${product.getProductName()}
			</div>
			<div class="spaceBelow_10">
				<label class="bold labelLength_20">Product Code:</label>
				${product.getProductCode()}
			</div>
			<div>
				<label class="bold labelLength_20">Sales Price:</label>
				<input type="text" value="${product.getUnitCost()}" name="unitCost" class="form-state width-30" id="updateCost">
			</div>	
			<div>
				<label class="bold labelLength_20">Category:</label>
				<input type="text" value="${product.getCategory()}" name="category" class="form-state width-30" id="updateCategory">
			</div>								
			<div>
				<label class="bold labelLength_20">Description:</label>
				<textarea rows="5" cols="5"  class="form-state width-50" name="description" id="updateDesc">${product.getDescription()}</textarea>
				
			</div>	
			</sf:form>
			<hr>	
			<button  id="updateProductBtn"  class="btn btn-success">Update Product</button>
			<button  id="cancelProductBtn"  class="btn btn-default">Cancel</button>				
		</div>		
		
		</div>
		<div class="col-lg-3">
			<div class="list-group">
  				<a href="<c:url value="/products/listProduct" />" class="list-group-item">Back to Product List <i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
  			</div>
		</div>
</div>
</div>



<script type="text/javascript">
$(document).ready(function(){
	$('#updateContainer').hide();
	$('#editProductBtn').on('click',function(){
		$('#editContainer').hide();
		$('#updateContainer').show();
	});
	
	$('#removeProductBtn').on('click',function(){
		
	});	
	
	$('#cancelProductBtn').on('click',function(){
		$('#editContainer').show();
		$('#updateContainer').hide();		
	});	
	
	$('#updateProductBtn').on('click',function(){
		var price = $('#updateCost').val();
		var category = $('#updateCategory').val();
		var desc = $('#updateDesc').val();
		
		document.updateProduct.submit();
		
	});
});
</script>
</body>