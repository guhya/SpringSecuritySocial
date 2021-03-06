<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="baseURL" value="${fn:replace(req.requestURL, fn:substring(req.requestURI, 1, fn:length(req.requestURI)), req.contextPath)}" />

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${pageTitle}</title>
	<link rel="stylesheet" type="text/css" href="/resources/front/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/front/css/font-awesome.min.css" />
	
	<tiles:insertAttribute name="css" />
</head>
<body style="padding-top: 20px;padding-bottom: 20px; margin-bottom: 20px;">
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
					<a class="navbar-brand" href="#">Ewideplus Spring Social Security</a>
				</div>
				
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="/"><spring:message code="home.hello" arguments="Anonymous,"/></a></li>
						<li><a href="/admin/user/mydetail"><spring:message code="home.userSession" /></a></li>
						<li><a href="#"><spring:message code="home.socialStatus" /></a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/admin/facebook/profile"><i class="fa fa-facebook"></i> <spring:message code="home.facebookProfile" /></a></li>
						<li><a href="/admin/twitter/profile"><i class="fa fa-twitter"></i> <spring:message code="home.twitterProfile" /></a></li>
						<li><a href="/admin/logout"><i class="fa fa-lock"></i> <spring:message code="home.logout" /></a></li>
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
	
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center" style="margin-top: 50px;">
					<p class="text-muted">
						<c:set var="localeCode" value="${pageContext.response.locale}" />
					
						<c:if test="${localeCode == 'en'}">English</c:if>
						<c:if test="${localeCode != 'en'}"><a href="${baseUrl}?lang=en">English</a></c:if>
						| 					
						<c:if test="${localeCode == 'ko'}">한국어</c:if>
						<c:if test="${localeCode != 'ko'}"><a href="${baseUrl}?lang=ko">한국어 </a></c:if> 
						|
						<c:if test="${localeCode == 'in'}">Bahasa Indonesia</c:if> 
						<c:if test="${localeCode != 'in'}"><a href="${baseUrl}?lang=in">Bahasa Indonesia</a></c:if>
					</p>
				</div>
			</div>
		</div>
	</footer>
	
	<script src="/resources/front/js/jquery-1.11.0.min.js"></script>
	<script src="/resources/front/js/bootstrap.min.js"></script>
	<script>
		var welcome = '<spring:message code="home.hello" arguments="Anonymous,"/>';
	</script>
	
	<tiles:insertAttribute name="js" />	
	
</body>
</html>
