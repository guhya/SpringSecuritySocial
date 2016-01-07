<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<tiles:insertDefinition name="_baseFront">
    
    <tiles:putAttribute name="body"> 
		<h1>Hello world!</h1>
		<p>${serverTime}</p>
		
		<a href="/admin/facebook/profile"><button class="btn btn-lrg btn-info"><spring:message code="home.facebookProfile" /></button></a>	
		<a href="/admin/twitter/profile" class="btn btn-lrg"><button class="btn btn-lrg btn-success"><spring:message code="home.twitterProfile" /></button></a>	
		<a href="/admin/login" class="btn btn-lrg"><button class="btn btn-lrg btn-warning"><spring:message code="home.login" /></button></a>
		<a href="/admin/register" class="btn btn-lrg"><button class="btn btn-lrg btn-default"><spring:message code="home.register" /></button></a>
		
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
    	<script>
    		$(document).ready(function(){    			
    			
    		});
    	</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
