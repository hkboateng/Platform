<!-- Page Header -->
<nav class="navbar navbar-inverse navbar-fixed-top">
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
            <li>
            	<a href="/abankus/platform/dashboard"><i class="fa fa-home fa-lg moveR_10"></i>DashBoard</a>
            </li>
            
            <li>
	            <a id="drop4" href="#" class=" dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user fa-md moveR_10"></i>${employee.firstname}&nbsp;${employee.lastname}
		          <span class="caret"></span>
		        </a>
	            <ul id="menu1" class="dropdown-menu" aria-labelledby="drop4">
		          <li><a href="#"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Update your Profile</a></li>
		          <li role="separator" class="divider"></li>
		          <li><a href="#"><i class="fa fa-sign-out fa-lg moveR_5"></i>Logout</a></li>
		        </ul>
            </li>

          </ul>

        </div>
      </div>
    </nav>

<!-- Page Header ends -->