
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<script src="<c:url value="/resources/js/application.js" />" type="text/javascript"></script>
</head>
<body>
<%-- Include page header --%>
<jsp:include page="../header.jsp"/>

<div id="container" class="container">
	<div class="row">
		<div class="col-sm-12 col-md-3 col-lg-3">
			
		</div>	
		<div class="col-sm-9 col-md-9">
			<div class="row">
			<%-- Filter List --%>
				<div>
				<h2>Employee List</h2>
				<hr/>
				</div>
				<div id="listOfEmployee">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Full Name</th>
								<th>Address</th>
								<th>Contact Information</th>
								<th>View Profile</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="empList" items="${employeeList}" varStatus="TheCount">
								<tr>
									<td>${TheCount.count}</td>
									<td>${empList.firstname} ${empList.middlename} ${empList.lastname}</td>
									<td>
										${empList.address1 }&nbsp;${empList.address1 }
										<p>${empList.city }${empList.state }${empList.zipcode }</p>
									</td>
									<td>${empList.cellphone } ${empList.email}</td>
									<td><a href="" class="btn btn-success"><i class="fa fa-eye moveR_10"></i>Select</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- prefix free to deal with vendor prefixes -->
</body>

</html>