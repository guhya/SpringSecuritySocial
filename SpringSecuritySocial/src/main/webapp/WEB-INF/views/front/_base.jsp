<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<title>${pageTitle}</title>
	<link rel="stylesheet" type="text/css" href="/resources/front/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/front/css/font-awesome.min.css" />
	
	<tiles:insertAttribute name="css" />
</head>
<body style="padding-top: 20px;padding-bottom: 20px;">
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
			
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Spring Social Security</a>
				</div>
				
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="#">Home</a></li>
						<li><a href="#">User</a></li>
						<li><a href="#">Social Status</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/admin/facebook/profile"><i class="fa fa-facebook"></i> Facebook Profile</a></li>
						<li><a href="/admin/twitter/profile"><i class="fa fa-twitter"></i> Twitter Profile</a></li>
						<li><a href="/admin/logout"><i class="fa fa-lock"></i> Logout</a></li>
					</ul>
				</div>				

			</div>
		</nav>
		<div class="row">
			<div class="col-lg-12">
				<tiles:insertAttribute name="body" />			
			</div>
		</div>
	</div>
	
	<script src="/resources/front/js/jquery-1.11.0.min.js"></script>
	<script src="/resources/front/js/bootstrap.min.js"></script>
	
	<tiles:insertAttribute name="js" />	
	
</body>
</html>
