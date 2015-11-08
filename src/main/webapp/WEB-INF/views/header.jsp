 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Page Header -->
<nav id="" class="navbar  navbar-default navbar-static-top bg-primary">
      <div class="container-fluid bg-primary">
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
            <li id="">
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/employee/editEmployee?employeeId=${employee.employeeId}&jumboId=${employee.id}"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Update your Profile</a></li>
		          <sec:authorize access="hasRole('supervisor')">
			          <li><a href="#"><i class="fa fa-cogs fa-lg moveR_5"></i></a>Account Settings</li>
		          </sec:authorize>
		          <li role="separator" class="divider"></li>
		          <li><a href="<c:url value="/platform/logout"/>"><i class="fa fa-sign-out fa-lg moveR_5"></i>Logout</a></li>
		        </ul>
            </li>

          </ul>

        </div>
      </div>
    </nav>
  <nav id="stickyNav" class="navbar navbar-blue ">
  <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand sr-only" href="#">Abankus Connection</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
		<ul id="stickyMenu" class="nav navbar-nav navbar-right hidden">
            <li id="">
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/employee/editEmployee?employeeId=${employee.employeeId}&jumboId=${employee.id}"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Update your Profile</a></li>
		          <sec:authorize access="hasRole('supervisor')">
			          <li><a href="#"><i class="fa fa-cogs fa-lg moveR_5"></i></a>Account Settings</li>
		          </sec:authorize>
		          <li role="separator" class="divider"></li>
		          <li><a href="<c:url value="/platform/logout"/>"><i class="fa fa-sign-out fa-lg moveR_5"></i>Logout</a></li>
		        </ul>
            </li>

          </ul>
          <ul class="nav navbar-nav">
            <li>
            	<a href="/abankus/platform/dashboard"><i class="fa fa-home fa-lg moveR_10"></i>DashBoard</a>
            </li>
            <sec:authorize access="hasRole('sales-manager')">
            <li>
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>Employee Services
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/registration/employee"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>New Employee</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/employee/listEmployee"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Employees</a></li>
		        </ul>            
            </li>            
            </sec:authorize>
			<sec:authorize access="hasRole('sales-manager')">
            <li>
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="glyphicon glyphicon-tasks moveR_10"></i>Sale Transaction
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/registration/employee"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Accept Payment</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/employee/listEmployee"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Employees</a></li>
		        </ul>            
            </li>			
			</sec:authorize>
			<sec:authorize access="hasRole('EMPLOYEE')">
            <li>
	            <a id="aEmployeeInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="glyphicon glyphicon-tasks moveR_10"></i>Payments and Collection
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aEmployeeInfoLink">
		          <li><a href="/abankus/registration/employee"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Accept Payment</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/employee/listEmployee"><i class="fa fa-sign-out fa-lg moveR_5"></i>Search for Transaction</a></li>
		          
		        </ul>            
            </li>			
			</sec:authorize>			
            <li>
	            <a id="aClientInfoLink" href="#" class=" dropdown-toggle header-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>Client Services
		          <span class="caret"></span>
		        </a> 
				<ul id="menu1" class="dropdown-menu" aria-labelledby="aClientInfoLink">
		          <li><a href="/abankus/client/createOrders"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Create Orders</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="/abankus/customers/create"><i class="fa fa-sign-out fa-lg moveR_5"></i>Add New Customers</a></li>
		          <li><a href="/abankus/customers/listCustomer"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Customers</a></li>
		        </ul>		                  
            </li>
                  
          </ul>
          </div>        
  </div>
</nav>
<script>
$(document).ready(function() {
	var stickyNavTop = $('#stickyNav').offset().top;
	 
	var stickyNav = function(){
	var scrollTop = $(window).scrollTop();
	      
	if (scrollTop > stickyNavTop) { 
	    $('#stickyNav').addClass('sticky');
	    $('#stickyMenu').removeClass('hidden');
	} else {
	    $('#stickyNav').removeClass('sticky'); 
	    $('#stickyMenu').addClass('hidden');
	}
	};
	 
	stickyNav();
	 
	$(window).scroll(function() {
	    stickyNav();
	});
	});
</script>