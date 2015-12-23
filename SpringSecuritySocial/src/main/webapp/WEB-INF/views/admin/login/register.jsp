<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="_baseFront">
    
    <tiles:putAttribute name="body">
	    <div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>Register</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">				
					<div class="form">
					
						<form:form modelAttribute="userVo" action="/admin/register" class="form-horizontal" method="post">
							<c:if test="${not empty message}"><p class="alert alert-info">${message}</p></c:if>							
							<c:if test="${not empty error}"><p class="alert alert-danger">${error}</p></c:if>
							<hr/>						
							<div class="form-group row">
								<label for="username" class="control-label col-lg-4">First Name</label>
								<div class="col-lg-8">
									<input type="text" value="${user.firstName}" id="firstName" name="firstName" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group row">
								<label for="username" class="control-label col-lg-4">Last Name</label>
								<div class="col-lg-8">
									<input type="text" value="${user.lastName}" id="lastName" name="lastName" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group row">
								<label for="username" class="control-label col-lg-4">Username</label>
								<div class="col-lg-8">
									<input type="text" value="${user.username}" id="username" name="username" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group row">
								<label for="password" class="control-label col-lg-4">Password</label>
								<div class="col-lg-8">
									<input type="password" id="password" name="password" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<hr/>
							<div class="form-group">
								<div class="col-lg-12">
									<button class="btn btn-success" type="submit">Register</button>
								</div>
							</div>
						</form:form>
						
					</div>
				</div>
				
				<div class="col-lg-6">
				</div>
										
			</div>
		</div>
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
    	<script>
    		$(document).ready(function(){    			
    			
    		});
    	</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
