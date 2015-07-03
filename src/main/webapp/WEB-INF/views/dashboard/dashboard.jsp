<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abankus Corporation - Sales Connection</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"/>
<link href="<c:url value="/resources/css/platform.css" />" rel="stylesheet"/>
</head>
<body>
<!-- Page Header -->
<nav class="navbar navbar-blue navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Abankus Connection</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        
          <ul class="nav navbar-nav navbar-right">
          <li><a href="">Home</a></li>
          <li><a href="">Dashboard</a>
		  <sec:authorize access="isAuthenticated()"> 
 			<li>
			    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
			      <span class="glyphicon glyphicon-user moveR_5" aria-hidden="true">
			      	</span><sec:authentication property="principal.username" />
			      <span class="caret"></span>
			    </a>
			    <ul class="dropdown-menu" role="menu">
		            <li><a href="#">Update Profile</a></li>
		            <li class="divider"><a href="#"></a></li>
		            <li><a href="logout">Logout</a></li>         	
			    </ul>
          	</li>		  
		  </sec:authorize>
         
          </ul>
        </div>
      </div>
    </nav>

<!-- Page Header ends -->
<!-- Body Begins-->
<div class="container-fluid">
<div class="row">
<div class="col-sm-3 col-md-2 sidebar">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#clientServices" aria-expanded="false" aria-controls="clientServices">
          <span class="glyphicon glyphicon-user moveR_10" aria-hidden="true"></span>Client Services
        </a>
      </h4>
    </div>
    <div id="clientServices" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
<div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          <span class="glyphicon glyphicon-tasks moveR_10" aria-hidden="true"></span>Client Services
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		<ul class="list-group">
		  <li class="list-group-item"><a href="<c:url value='/customers/create' />">New Prospect Customer</a></li>
		  <li class="list-group-item">Dapibus ac facilisis in</li>
		  <li class="list-group-item">Morbi leo risus</li>
		  <li class="list-group-item">Porta ac consectetur ac</li>
		  <li class="list-group-item">Vestibulum at eros</li>
		</ul>
    </div>
  </div>
  <div data-tools="accordion">
    <a href="#panel1" class="accordion-title"> Client Services</a>
    <div id="panel1" class="accordion-panel">
		<ul class="list-group">
		  <li class="list-group-item"><a href="<c:url value='/customers/create/individual' />">New Prospect Customer - Individual</a></li>
		  <li class="list-group-item"><a href="<c:url value='/customers/create/company' />">New Prospect Customer - Customer</a></li>
		  <li class="list-group-item">Dapibus ac facilisis in</li>
		  <li class="list-group-item">Morbi leo risus</li>
		  <li class="list-group-item">Porta ac consectetur ac</li>
		  <li class="list-group-item">Vestibulum at eros</li>
		</ul>
	</div>
</div>
<li><a href="logout"><span class="glyphicon glyphicon-user moveR_10" aria-hidden="true"></span>Logout</a></li>

</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <h1>Dashboard</h1>
			<hr>
          <div class="row">
          
          </div>
</div>
</div>
</div>
</body>
<script src="<c:url value="/resources/js/jquery.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/kube.js" />" type="text/javascript"></script>
</html>
