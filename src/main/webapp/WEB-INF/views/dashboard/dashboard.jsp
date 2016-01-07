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
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/platform-functions.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/visualization/d3.js" />" type="text/javascript"  charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/js/visualization/guage.js" />"></script>
<script src="<c:url value="/resources/js/visualization/visualization.js" />" type="text/javascript"  charset="utf-8"></script>
<style>

 .axis {
   font: 10px sans-serif;
 }
.tick line{

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
			<div class="col-sm-12 col-md-12 col-lg-12  page-header">

				<span class="lead">Welcome, ${employee.firstname}&nbsp;${employee.lastname}</span>

				<script>
					periodList();
				</script>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 center-block main">
			
			<!-- Messages -->
			          <c:if test="${not empty success }">
				          <div class="alert alert-success close" role="alert"  data-dismiss="alert" aria-label="Close">
				          	<span aria-hidden="true">&times;</span>
				          	${success}
				          </div>
			          </c:if>
			          <div class="col-lg-12">
					      <c:if test="${not empty searchError }">
						    <div class="alert alert-danger col-lg-8" role="alert">
						        ${searchError }
						    </div>
					      </c:if>			          
			          </div>					
			          <div id="" class="col-lg-12 col-md-12"> 
			          <div class="col-lg-9 col-md-9">
						<div id="" class="col-lg-3 col-md-4">
				         <span class="lead">Total Payments for  <span class="yearId"></span></span>
				         <table id="tblTotalYearPayment" class="table table-bordered">
				         	<thead>
				         		<tr>
					         		<th>Year</th>
					         		<th>Total Amount</th>
				         		</tr>
				         	</thead>
				         	<tbody id="totalYearPayment">
				         	
				         	</tbody>
				         </table>
				         </div>	
				         
				         <div id="totalDayPayment" class="col-lg-3 col-md-4">
					         <span class="lead">Payments collected in <span id="todayId"></span></span>
					         <table class="table table-bordered ">
					         	<thead>
					         		<tr class="success">
						         		<th>Transactions Processed</th>
					         		</tr>
					         	</thead>
					         	<tbody id="transactionToday">
					         	
					         	</tbody>
					         </table>
				         </div>
				         <div class="clearfix"></div>
				         <!-- Guage -->
					 <div class="col-md-3 col-lg-4">
					 	<div class="panel panel-default">
						  	<!-- Default panel contents -->
						  	<div class="panel-heading">Number of Customers</div>
						  </div>
						  <span id="customerGaugeContainer">
						  
						  </span>
					  
					  	
					 </div>		
					 <div class="col-md-9 col-lg-8">
					 	<div id="barChart">
						
						</div>
					 </div>		         				         		          
			          </div>
			          <div class="col-lg-3 col-md-3">
					         <span class="lead">Payments for each month in <span class="yearId"></span></span>
					         <table class="table table-bordered ">
					         	<thead>
					         		<tr class="success">
						         		<th>Month</th>
						         		<th>Total Amount</th>
					         		</tr>
					         	</thead>
					         	<tbody id="totalMonthPayment">
					         	
					         	</tbody>
					         </table>	          
			          </div>				          
			          </div>

					 <div class="clear"></div>

					 <div class="col-md-3">
					
					 </div>
					 
					 
			</div>
		</div>
	</div>
	<%-- Include page header --%>
	<jsp:include page="../footer.jsp"/>
	<!-- Page Header ends -->
<sf:form action="/abankus/customers/viewProfile" method="post" name="customerViewProfileForm">
<input type="hidden" name="searchType" id="searchType" value="customerNumber">
<input type="hidden" name="customerNumber" id="customerNumber" value="">
</sf:form>
</body>
<script>
	$(document).ready(function(){
		createGauges();
		setInterval(loadTotalCustomerCount(), 5000);
		$(document).ajaxStart(function() {
			  $("#loading-text").show();
			});

			$(document).ajaxStop(function() {
			  $("#loading-text").hide();
			});
		var year = new Date().getFullYear();
		var month = new Date().getMonth()+1;
		changeAnalystics(year,month);

		var pullData = function(){
			graph(year);
		}
		setTimeout(pullData,20000);
	});
	function createGauges()	{
		createGauge("customer", "Customer");
	}
	function loadEmployeeCustomers(){
		
	}
	function changeAnalystics(year,month){
		//graph(year)
		loadYearPayments(year);
		countTodayPayment();
		loadMonthAndYearPayments(month,year);
		loadTotalCustomerCount();
	}
	///customers/countTotalCustomers
	function loadTotalCustomerCount(){
		$.ajax({
			url: 'countTotalCustomers',
			method: 'get',
			dataType: 'json',
			success: function(result){
				console.log(result);
				updateGauges('customer',result);
			},
			error : function(err){
				console.log(err.responseText);
			}
		});			
	}
	function loadYearPayments(year){
		if(year == null){
			year = new Date().getFullYear();
		}
		$.ajax({
			url: 'http://localhost:8080/paymenthub/paymentservice/findPaymentByOrderYear',
			method: 'get',
			data : {
				year : year
			},
			dataType: 'json',
			success: function(result){
				var table = "";
				if(result[0] != null && result[1] != null){
				
					table +="<tr>";
					table +="<td>"+result[0]+"</td>";
					table +="<td>$"+result[1].toFixed(2)+"</td>";
					table +="</tr>";
				$('#totalYearPayment').html(table);					
			}else{
				table = "<tr><td colspan='2'>No Data</td></tr>";
				$('#totalYearPayment').html(table);		
			}
			},
			error : function(err){
				console.log(err.responseText);
			}
		});				
	}
	function loadMonthAndYearPayments(month,year){
		$('.yearId').html(year);
		if(year == null){
			year = new Date().getFullYear();
		}
		$.ajax({
			url: 'http://localhost:8080/paymenthub/paymentservice/findPaymentByOrderMonthAndYear',
			method: 'get',
			data: {
				year:year
			},
			dataType: 'json',
			success: function(result){
				var table="";
				if(result.length > 0 && result != null){
					$.each(result,function(key,value){
						table +="<tr>";
						table +="<td>"+result[key][0]+"</td>";
						table +="<td>$"+result[key][2]+"</td>";
						table +="</tr>";
					});
					$('#totalMonthPayment').html(table);					
				}else{
					table = "<tr><td colspan='3'>No Data</td></tr>";
					$('#totalMonthPayment').html(table);		
				}

			},
			error : function(err){
				$('#transactionMonth').html("No Transaction");
			},
			complete: function(e){
				$('#yearId').html(year);
			}
		});		
	}
	function countTodayPayment(){
		var now = new Date();
		$("#todayId").html(now.toLocaleDateString("en-US"));
		var day = now.getDate();
		if(day <10){
			day = day;
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
			url: "http://localhost:8080/paymenthub/paymentservice/countTotalPaymentDate",
			method: 'get',
			data: {
				date: date
			},
			dataType: 'json',
			success: function(result){
				Number(result);
				
				var table = "";
						table +="<tr>";
						table +="<td>"+result+"</td>";
						table +="</tr>";
					$('#transactionToday').html(table);	
			},
			error : function(err){
				table = "<tr><td>No Data</td></tr>";
				$('#transactionToday').html(table);	
			}
		});
	}
	function calculateTotalAmount(amount){
		var r = 0;
		r+= amount;
		return r;
		
	}
	
	function graph(year){
		var margin = {top: 20, right: 20, bottom: 70, left: 40},
	    width = 800 - margin.left - margin.right,
	    height = 600 - margin.top - margin.bottom;
	
	// Parse the date / time
	var parseDate = d3.time.format("%m").parse;

	var x = d3.scale.ordinal().rangeRoundBands([0, width], 0.5);

	var y = d3.scale.linear().range([height, 0]);

	var xAxis = d3.svg.axis()
	    .scale(x)
	    .orient("bottom");

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

	d3.json("http://localhost:8080/paymenthub/paymentservice/findPaymentByOrderMonthAndYear?year="+year, function(error, data) {
		console.log(data);
 		if(data.length > 0){
			  x.domain(data.map(function(d) { return d[0]; }));
			  y.domain([0, d3.max(data, function(d) { return d[2]; })]);
		
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
		
			  var path = svg.selectAll("bar")
			      .data(data)
			    .enter().append("rect")
			      .style("fill", function(d, i) { 
		              return color(d[2]);
		          })
			      .attr("x", function(d) { return x(d[0]); })
			      .attr("width", x.rangeBand())
			      .attr("y", function(d) { return y(d[2]); })
			      .attr("height", function(d) { return height - y(d[2]); });
			}else{
				$('#barChart').html('No Data');
			}
		});		
	}

</script>
</html>
