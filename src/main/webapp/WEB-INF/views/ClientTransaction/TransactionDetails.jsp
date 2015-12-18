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
		<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery.js" />"	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/bootstrap.js" />" 	type="text/javascript"></script>
		<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"  charset="utf-8"></script>
<style>
.tooltips {
  background: #eee;
  box-shadow: 0 0 5px #999999;
  color: #333;
  display: none;
  font-size: 12px;
  left: 90px;
  padding: 10px;
  position: absolute;
  text-align: center;
  top: 350px;
  width: 190px;
  z-index: 10;
}
</style>
	</head>
<body>
	<%-- Include page header --%>
	<jsp:include page="../header.jsp" />
	<div id="container" class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp"/>
			</div>		
			<div class="col-sm-10 col-md-10 col-lg-10">
				<div class="page-header col-sm-12 col-md-12 col-lg-12">
					<span class="lead">Transaction Details </span>
					<div class="pull-right">
						<c:set var="cust" value="${billing.getCustomer().getCustomerNumber()}"/>
						<c:set var="customerId" value="${billing.getCustomer().getCustomerId()}"/>
						<a href="javascript:document.viewCustomerProfileBckBtn.submit()" class="">Back to Profile Page</a>
					</div>				
				</div>
				<div class="col-sm-12 col-md-10 col-lg-10">
				<div class="orderDetails">
					<c:set var="orderNumber" value="${viewTransactionDetailsOrderNumber}"/>
					<p><label class="labelLength_20">Order Date:</label><span>${billing.getClientOrderId().convertOrderDate() }</span></p>
					<p><label class="labelLength_20">Order Number:</label>${orderNumber}</p>
					<p><label class="labelLength_20">Product Name:</label>${billing.getClientOrderId().getProductCode() }</p>
					<p><label class="labelLength_20">Total Amount:</label><fmt:formatNumber value="${billing.getTotalOrderAmount()}" type="currency"/></p>
					<p><label class="labelLength_20">Amount Remaining:</label><fmt:formatNumber value="${billing.totalAmountRemaining() }" type="currency"/></p>
					<p><label class="labelLength_20">Payment Status:</label>${billing.getClientOrderId().getPaymentStatus() }</p>
					<p><label class="labelLength_20">Next Payment Date:</label>${billing.getClientOrderId().getNextPaymentDate()}</p>
					<input type="hidden" value="${orderNumber}" id="orderNumber"/>
				</div>
				</div>
				<div class="col-sm-12 col-md-2 col-lg-2">
					<div class="list-group">
					
					<c:set value="${billing.encryptOrderNumber(orderNumber) }" var="keySec" />
						<a href="javascript:submitCustomerPayment('${keySec}','${billing.getTotalOrderAmount()}');" class="btn btn-success list-group-item">Make Payment</a>
					</div>
					<div class="list-group">
						<a href="" class="btn btn-primary list-group-item">Print Statement</a>
					</div>	
				</div>	
				<div class="clearfix"></div>		
		<h3 class="page-header">Payment History</h3>
			<div class="col-sm-12 col-md-8 col-lg-8">
				
					<table id="transactionTable" class="table">
						<thead>
						<tr>
							<th>List</th>
							<th>Date</th>
							<th>Amount Paid:</th>
							<th>Payment Type</th>
							<th>Staff Name</th>
							<th>Action</th>
						</tr>
						</thead>
						<tbody id="transactionBody">
						<c:choose>
							<c:when test="${not empty paymentList }">
								<c:forEach items="${paymentList }" var="payments" varStatus="i">
									<tr>
										<td>${i.count })</td>
										<td>${payments.paymentDate }</td>
										<td><fmt:formatNumber value="${payments.amountPaid }" type="currency"/></td>
										<td>${payments.paymentType.toUpperCase() }</td>
										<td>${payments.getEmployeeName()}</td>
										<td></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="45" style="text-align:center;">No Payment History found.</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>	
					
					</div>
				<div class="col-sm-12 col-md-4">
					<div id="transactionChart">
					
					</div>
				</div>						
			</div>
				
			</div>
	</div>
	<sf:form action="/abankus/Payments/makeCustomerOrderPayment" name="makeCustomerOrderPayment" method="post">
		<input type="hidden" name="orderNumber" id="orderNumberHdn" value=""/>
		<input type="hidden" name="totalAmount" id="totalAmountHdn"  value=""/>
		<input type="hidden" name="orderDate" id="OrderDateHdn"  value=""/>
		<input type="hidden" name="productCode" id="productCodeHdn"  value=""/>
		<input type="hidden" name="customerId" id="customerIdHdn"  value="${customerId }"/>
	</sf:form>
	<sf:form name="viewCustomerProfileBckBtn" action="/abankus/customers/viewProfile" method="post">
		<input type="hidden" name="searchType" id="searchType" value="customerNumber">
		<input type="hidden"  name="customerNumber" value="${cust}"/>	          
	</sf:form>	
	<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		var orderNumber = $('#orderNumber').val();
		drawPieChart(orderNumber );
		
		$('#transactionTable').DataTable();
	});


	function makePayment(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}
		form.action="/abankus/customers/"+url
		form.submit();
	}
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
	var AMT_PAID = 0;
	function drawPieChart(tr){
		var orderNumber = $('#orderNumber').val();
		var width = 360;
        var height = 360;
        var radius = Math.min(width, height) / 2;
        var donutWidth = 75;
        var legendRectSize = 18;                                  // NEW
        var legendSpacing = 4;     

        var svg = d3.select('#transactionChart')
        .append('svg')
        .attr('width', width)
        .attr('height', height)
        .append('g')
        .attr('transform', 'translate(' + (width / 2) + 
          ',' + (height / 2) + ')');
		
       
        
		var color = d3.scale.category10();

		var pie = d3.layout.pie()
	    .value(function(d) { return d.amountPaid; })
	    .sort(null);
		
        var arc = d3.svg.arc()
        .innerRadius(radius - donutWidth)
        .outerRadius(radius);		

        var tooltip = d3.select('#transactionChart')            // NEW 
        .append('div')                             // NEW
        .attr('class', 'tooltips');                 // NEW

      	tooltip.append('div')                        // NEW
        .attr('class', 'paymentDate');                   // NEW

      	tooltip.append('div')                        // NEW
        .attr('class', 'paymentType');                   // NEW

        
		d3.json("/abankus/platform/loadCustomerOrderByOrderNumber?orderNumber="+tr, function(error, data) {
			
			
			var path = svg.selectAll('path')
            .data(pie(data))
            .enter()
            .append('path')
            .attr('d', arc)
            .attr('fill', function(d, i) { 
              return color(d.amountPaid);
            });

				path.on('mouseover', function(d) {
			 
				  tooltip.select('.paymentDate').html("<b>Payment Date: </b>"+d.data.paymentDate);
				  tooltip.select('.paymentType').html("<b>Payment Type: </b>"+d.data.paymentType); 
				  tooltip.style('display', 'block');
				});
			path.on('mouseout', function() {
				  tooltip.style('display', 'none');
				});

		    /***
			var legend = svg.selectAll('.legend')
            .data(color.domain())
            .enter()
            .append('g')
            .attr('class', 'legend')
            .attr('transform', function(d, i) {
              var height = legendRectSize + legendSpacing;
              var offset =  height * color.domain().length / 2;
              var horz = -2 * legendRectSize;
              var vert = i * height - offset;
              return 'translate(' + horz + ',' + vert + ')';
            });

          legend.append('rect')
            .attr('width', legendRectSize)
            .attr('height', legendRectSize)
            .style('fill', color)
            .style('stroke', color);
            
          legend.append('text')
          .data(data)
            .attr('x', legendRectSize + legendSpacing)
            .attr('y', legendRectSize - legendSpacing)
         
            .text(function(d) { return d.paymentDate });  	
          ***/
          svg.append("text")
	      .attr("dy", "0em")
	      .style("text-anchor", "middle")
	      .attr("class", "bold")
	      .text(function(d) { return "Amount Paid: $"+d.orderAmount; });
          
          svg.append("text")
          .data(data)
	      .attr("dy", "2em")
	      .attr("dx", "-5em")
	      .style("text-anchor", "bottom")
	      .attr("class", "bold")
	      .text(function(d) { return "Amount Left: $"+(d.amountRemaining).toFixed(2); });
       
			});		
        

		

        

	}
	
	function submitCustomerPayment(orderNo,amount){
		var form = document.makeCustomerOrderPayment;
		form.orderNumber.value = orderNo;
		form.totalAmount.value = amount;
		form.submit();	
	}
	</script>
	
	</body>
	</html>
