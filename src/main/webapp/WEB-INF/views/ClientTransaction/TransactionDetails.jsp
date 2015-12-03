<%-- Page when the Sales Person goes to the Client/Customer to collect money or payment --%>
<%-- Page for enter Client Order which creates a ClientBilling instance --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Abankus Corporation - Order Transaction Details</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/platform.css" />"	rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
		<script	src="<c:url value="/resources/js/validation/jquery.validation.js" />" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"  charset="utf-8"></script>
		<script src="<c:url value="/resources/js/jquery-masked.js" />" type="text/javascript"></script>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container">
		<div class="row">
			<div class="col-sm-12 col-md-7">
				<h2  class="underline">Transaction Details</h2>
				<div class="orderDetails">
					<c:set var="orderNumber" value="${billing.getClientOrderId().getOrderNumber() }"/>
					<p><label>Order Date:</label><span>${billing.getClientOrderId().convertOrderDate() }</span></p>
					<p><label>Order Number:</label> ${orderNumber}</p>
					<p><label>Product Name:</label>${billing.getClientOrderId().getProductCode() }</p>
					<p><label>Total Amount:</label> <fmt:formatNumber value="${billing.getTotalOrderAmount()}" type="currency"/></p>
					<p><label>Amount Remaining:</label> <fmt:formatNumber value="${billing.totalAmountRemaining() }" type="currency"/></p>
					<input type="hidden" value="${viewTransactionDetailsOrderNumber}" id="orderNumber"/>
				</div>
				<h3>Payment History</h3>
					<table class="table">
						<tr>
							<th></th>
							<th>Date</th>
							<th>Amount</th>
							<th>Payment Type</th>
							<th>Staff Name</th>
						</tr>
						<tbody id="transactionBody">
						<c:choose>
							<c:when test="${not empty paymentList }">
								<c:forEach items="${paymentList }" var="payments" varStatus="i">
									<tr>
										<td>${i.count }</td>
										<td>${payments.paymentDate }</td>
										<td>${payments.amountPaid }</td>
										<td>${payments.paymentType }</td>
										<td>${payments.getEmployeeName()}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="45" style="text-align:center;">No Payment Details where found.</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>				
			</div>
			<div class="col-sm-12 col-md-5 col-lg-5 main-container">
				<jsp:include page="../sidebar.jsp" />
			</div>			
				<div class="col-sm-12 col-md-7">
				
				</div>
				<div class="col-sm-12 col-md-5">
					<div id="transactionChart">
					
					</div>
				</div>
			
		</div>
		<c:set var="cust" value="${billing.getCustomer().getCustomerNumber()}"/>
		<a href="javascript:document.viewCustomerProfileBckBtn.submit()">Back to Customer Profile Page</a>
	</div>
	<sf:form name="viewCustomerProfileBckBtn" action="/abankus/customers/viewProfile" method="post">
		<input type="hidden" name="searchType" id="searchType" value="customerNumber">
		<input type="hidden"  name="customerNumber" value="${cust}"/>	          
	</sf:form>	
	<script>
	$(document).ready(function(){
		var orderNumber = $('#orderNumber').val();
		drawPieChart(orderNumber );
		
	});
	
	function loadPaymentByOrderNumber(orderNumber){
		$.ajax({
			url:'loadPaymentsByOrderNumber',
			contentType: "application/json; charset=utf-8",
			method: 'get',
			data: {
				orderNumber: orderNumber
			},
			success : function(results){
				console.log(results[0]);
				var str = "";
				$.each(results, function(i,value){
					
					
				});
				drawPieChart(results);
				
			},
			error: {
				
			}
		});
	}
	
	function drawPieChart(tr){
		var orderNumber = $('#orderNumber').val();
		var width = 265,
	    height = 300,
	    radius = Math.min(width, height) / 2;
		
		var color = d3.scale.ordinal()
	    .range(["#DC143C", "#109618", "#990099", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

		var pie = d3.layout.pie()
	    .value(function(d) { return d.amountPaid; })
	    .sort(null);

		var arc = d3.svg.arc()
		    .innerRadius(radius - 70)
		    .outerRadius(radius - 10);		
		
		var svg = d3.select("#transactionChart").append("svg")
	    .attr("width", width)
	    .attr("height", height)
	  .append("g")
	    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");	

		d3.json("/abankus/platform/loadPaymentsByOrderNumber?orderNumber="+tr, function(error, data) {

			var g = svg.selectAll(".arc")
		      	.data(pie(data))
		    	.enter().append("g")
		      	.attr("class", "arc");

		  g.append("path")
		      .attr("d", arc).transition()
		      .style("fill", function(d) { return color(d.data.amountPaid); });


		  g.append("text")
		      .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
		      .attr("dy", ".35em")
		      .style("text-anchor", "middle")
		      .text(function(d) { return d.data.paymentDate; });

			});		

	}
	</script>
	</body>
	</html>
