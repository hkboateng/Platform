<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/fonts-awesome/font-awesome.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/tables/jquery.dataTables.css" />" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"  charset="utf-8"></script>
<style>

 .axis {
   font: 10px sans-serif;
 }

 .axis path,
 .axis line {
   fill: none;
   stroke: #000;
   shape-rendering: crispEdges;
 }
</style>
</head>
<body>
<!-- Page Header -->
	<%-- Include page header --%>
	<jsp:include page="../header.jsp"/>
	<!-- Page Header ends -->
	<!-- Body Begins-->
	<div id="container" class="container-fluid">
		<div class="row">
		 	<div class=" spaceBelow_20"></div>
			<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<jsp:include page="../sidebar.jsp"/>
			</div>	
			<div class="col-sm-12 col-md-10 col-lg-10 center-block main">
			<h2 class="page-header">Welcome, ${employee.firstname}&nbsp;${employee.lastname}</h2>
			<!-- Messages -->
			          <c:if test="${not empty success }">
				          <div class="alert alert-success" role="alert">
				          	${success}
				          </div>
			          </c:if>
			          <div class="col-lg-12">
					      <c:if test="${not empty searchError }">
						    <div class="alert alert-danger" role="alert">
						        ${searchError }
						    </div>
					      </c:if>			          
			          </div>					 	
					 <div class="clear"></div>
					<div id="barChart">
					
					</div>
			           
			       <%--
				          <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
				          	
				          	<h3>My Customer(s)</h3>
				          	<c:if test="${not empty employeeCustomerList }">
				          	<table id="assignedCustomerList" class="table">
				          		<thead>
				          		<tr>
				          			<th>Customer Name</th>
				          			<th>Account Status</th>
				          			<th>Action</th>
				          		</tr>
				          		</thead>
				          		<tbody>
				          		<c:forEach items="${employeeCustomerList }" var="customerList" >
				          		<tr>
				          			<td>${customerList.customer.getCustomerName() }</td>
				          			<td>${customerList.getStatus() }</td>
				          			<td>
				          			<!-- Split button -->
										<div class="btn-group">
										  <button type="button" class="btn btn-success">Action</button>
										  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										    <span class="caret"></span>
										    <span class="sr-only">Toggle Dropdown</span>
										  </button>
										  <ul class="dropdown-menu">
										    <li><a href="javascript:submitCustomerURL(document.customerViewProfileForm,'${customerList.getCustomer().getCustomerNumber()}')">View Customer Details</a></li>
										    <li><a href="#">Transaction History</a></li>
										    <li role="separator" class="divider"></li>
										    <li><a href="#">Separated link</a></li>
										  </ul>
										</div>
				          			</td>
				          		</tr>
				          		</c:forEach>
				          		</tbody>
				          	</table>
				          	</c:if>
				          	<c:if test="${empty employeeCustomerList}">				          	
								No Customer
								<div class="center-block">
									<a class="btn btn-success" href="<c:url value="/customers/create"/>"> Add New Customer</a>
								</div>	          		
				          	</c:if>		 	          	

				          <h3 class="underline-div"> Transaction History</h3>				          
							  <div class="btn-group btn-group-justified" role="group" >
								  <div class="btn-group" role="group">
								    <a aria-controls="todayDivContainer" role="tab" data-toggle="tab" id="todayDiv" href="#todayDivContainer"  class="btn btn-success">Today</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#monthlyDivContainer" class="btn btn-default">Monthly</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#YTDDivContainer"  class="btn btn-default">Year-To-Date</a>
								  </div>
								  <div class="btn-group" role="group">
								    <a aria-controls="monthlyDivContainer" role="tab" data-toggle="tab" href="#FilterDivContainer"  class="btn btn-default">
								    	Filter
								    </a>						    
								  </div>							  
							   </div>	
								  <div class="tab-content">
								  <div class="spaceBelow_10"></div>
								  	<div  role="tabpanel" class="tab-pane active" id="todayDivContainer">								  		
									        <table id="transactionTodayTbl" class="table table-striped transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionToday">
																          	
									          	</tbody>  
						          				<tfoot>
						          					<tr>
						          						<td colspan="1"><span class="bold">Total Amount:</span></td>
						          						<td colspan="2" id="todayTotal"></td>
						          						<td></td>
						          					</tr>
						          				</tfoot>
						          			</table>								  		
								  		</div>

								  	<div  role="tabpanel" class="tab-pane" id="monthlyDivContainer">
									        <table id="transactionMonthTbl" class="table table-striped transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionMonth">
																          	
									          	</tbody>  
						          			</table>								  		
								  	</div>		
								  	<div  role="tabpanel" class="tab-pane" id="YTDDivContainer">
									        <table id="transactionYTDTbl" class="table transactionTable">
									        	<thead>
										          	<tr>
											          	<th>Date</th>
											          	<th>Amount</th>
											          	<th>Type Of Payment</th>
											          	<th>Action</th>
										          	</tr>		
									          	</thead>	
									          	<tbody id="transactionYTD">
																          	
									          	</tbody>  
						          				<tfoot>
						          					<tr>
						          						<td colspan="1"><span class="bold">Total Amount:</span></td>
						          						<td colspan="2">Total</td>
						          						<td></td>
						          					</tr>
						          				</tfoot>
						          			</table>
						          			<div>
						          				<a href="transactionList">Transaction List</a>
						          			</div>								  		
								  	</div>
								  	<div  role="tabpanel" class="tab-pane" id="FilterDivContainer">
								  		<h4>Filter Transaction</h4>
								  	</div>								  						  	
								  </div>				          
				         	</div>		
							--%>		          	
			</div>
			
		</div>

	</div>
	<footer class="footer">
		<div class="container">
			<p>Copyright &copy; 2015</p>
		</div>		
	</footer>
<sf:form action="/abankus/customers/viewProfile" method="post" name="customerViewProfileForm">
<input type="hidden" name="searchType" id="searchType" value="customerNumber">
<input type="hidden" name="customerNumber" id="customerNumber" value="">
</sf:form>
</body>

<script src="<c:url value="/resources/js/tables/jquery.dataTables.js" />" type="text/javascript"></script>
<script>
	$(document).ready(function(){
		//loadEmployeeCustomers();
		//loadYearPayments()
		//loadTodayPayment();
		//loadMonthAndYearPayments();
		//$('#assignedCustomerList').DataTable({});
		//graph();
	});
	
	function loadEmployeeCustomers(){
		
	}
	function loadYearPayments(){
		var year = new Date().getFullYear();
		$.ajax({
			url: 'http://localhost:8080/paymenthub/rs/paymentservice/loadYearTransactionHistory',
			method: 'get',
			data : {
				year : year
			},
			dataType: 'json',
			success: function(result){
				console.log(result);
				if(result.length > 0){
					var table = "";
				$.each(result,function(key,value){
					table +="<tr>";
					table +="<td>"+value.paymentDate+"</td>";
					table +="<td>"+value.amountPaid+"</td>";
					table +="<td>"+toTitleCase(value.paymentType)+"</td>";
					table +="<td><a class='btn btn-primary' href='#' role='button' onclick=''>Action</a>";
					table +="</tr>";
				});
				$('#transactionYTD').html(table);					
			}else{
				$('#transactionYTD').html("No Data");		
			}
			},
			error : function(err){
				console.log(err.responseText);
			}
		});				
	}
	function loadMonthAndYearPayments(){
		var thisMonth= new Date().getMonth()+1;
		var year = new Date().getFullYear();
		$.ajax({
			url: 'http://localhost:8080/paymenthub/rs/paymentservice/findPaymentTransactionByMonthAndYear',
			method: 'get',
			data: {
				month: thisMonth,
				year:year
			},
			dataType: 'json',
			success: function(result){
				
				if(result.length > 0){
					$.each(result,function(key,value){
						table +="<tr>";
						table +="<td>"+value.paymentDate+"</td>";
						table +="<td>"+value.amountPaid+"</td>";
						table +="<td>"+toTitleCase(value.paymentType)+"</td>";
						table +="<td><a class='btn btn-primary' href='#' role='button' onclick=''>Action</a>";
						table +="</tr>";
					});
					$('#transactionMonth').html(table);					
				}else{
					$('#transactionMonth').html("No Data");		
				}

			},
			error : function(err){
				$('#transactionMonth').html("No Transaction");
			}
		});		
	}
	function loadTodayPayment(){
		var now = new Date();
		var day = now.getDate();
		if(day <10){
			day = "0"+day;
		}
		var month = now.getMonth()+1;
		if(month < 10){
			month = "0"+month;
		}
		var year = now.getFullYear();
		
		var date = month+"/"+day+"/"+year;
		var fromDate = new Date().toLocaleDateString("en-US");
		
		var toDate = new Date().toLocaleDateString("en-US");
		$.ajax({
			url: "loadPayments",
			method: 'get',
			data: {
				date: date
			},
			dataType: 'json',
			success: function(result){
				
				var table = "";
				var total = 0;
				$.each(result,function(key,value){
					table +="<tr>";
					table +="<td>"+value.paymentDate+"</td>";
					table +="<td>"+value.amountPaid+"</td>";
					table +="<td>"+toTitleCase(value.paymentType)+"</td>";
					table +="<td><a class='btn btn-primary' href='#' role='button' onclick=''>Action</a>";
					table +="</tr>";
					total  += Number(value.amountPaid);
				});
				$('#transactionToday').html(table);
				console.log(total);
				$('#todayTotal').html(total.toFixed(2));
			},
			error : function(err){
				console.log(err.responseText);
			}
		});
	}
	function calculateTotalAmount(amount){
		var r = 0;
		r+= amount;
		return r;
		
	}
	
	function graph(){
		var margin = {top: 20, right: 20, bottom: 70, left: 40},
	    width = 900 - margin.left - margin.right,
	    height = 600 - margin.top - margin.bottom;
	var year = new Date().getFullYear();
	// Parse the date / time
	var parseDate = d3.time.format("%m").parse;

	var x = d3.scale.ordinal().rangeRoundBands([0, width], 0.05);

	var y = d3.scale.linear().range([height, 0]);

	var xAxis = d3.svg.axis()
	    .scale(x)
	    .orient("bottom")
	    .tickFormat(d3.time.format("%B"));

	var yAxis = d3.svg.axis()
	    .scale(y)
	    .orient("left")
	    .ticks(10);
	
	var color = d3.scale.category10();
	
	var svg = d3.select("#barChart").append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  .append("g")
	    .attr("transform", 
	          "translate(" + margin.left + "," + margin.top + ")");

	d3.json("http://localhost:8080/abankus/platform/loadYearTransactionHistory", function(error, data) {
		console.log(data);
	    data.forEach(function(d) {
	        d.date = parseDate(d.paymentMonth);
	        d.value = +d.amountPaid;
	    });
	 
	  x.domain(data.map(function(d) { return d.date; }));
	  y.domain([0, d3.max(data, function(d) { return d.value; })]);

	  svg.append("g")
	      .attr("class", "x axis")
	      .attr("transform", "translate(0," + height + ")")
	      .call(xAxis)
	    .selectAll("text")
	      .style("text-anchor", "end")
	      .attr("dx", "1em")
	      .attr("dy", "1.55em")
	      .attr("transform", "rotate(-0)" );

	  svg.append("g")
	      .attr("class", "y axis")
	      .call(yAxis)
	    .append("text")
	      .attr("transform", "rotate(-90)")
	      .attr("y", 6)
	      .attr("dy", ".71em")
	      .style("text-anchor", "end")
	      .text("Revenue ($)");

	  svg.selectAll("bar")
	      .data(data)
	    .enter().append("rect")
	      .style("fill", function(d, i) { 
              return color(d.date);
          })
	      .attr("x", function(d) { return x(d.date); })
	      .attr("width", x.rangeBand()-300)
	      .attr("y", function(d) { return y(d.value); })
	      .attr("height", function(d) { return height - y(d.value); });

	});		
	}

</script>
</html>
