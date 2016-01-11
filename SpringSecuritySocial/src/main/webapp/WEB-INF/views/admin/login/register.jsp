<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

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
					
						<form:form name="registrationForm" modelAttribute="userVo" action="/admin/register" class="form-horizontal" method="post">
							<c:if test="${not empty message}"><p class="alert alert-info">${message}</p></c:if>							
							<c:if test="${not empty error}"><p class="alert alert-danger">${error}</p></c:if>
							
							<div class="form-group row" id="formErrorsRow" style="display:none">
								<div class="col-lg-12">
									<div class="alert alert-danger" id="formErrors"></div>									
								</div>
							</div>
							
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
							<div class="form-group row">
								<div class="col-lg-8 col-lg-offset-4">
									<div id="html_element"></div>
								</div>
							</div>
							<hr/>
							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-4">
									<input type="submit" name="submitHandler" style="display:none"/> 
									<button class="btn btn-success" type="button" onclick="saveProc();">Register</button>
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
    	<c:set var="localeCode" value="${pageContext.response.locale}" />
   	    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit&hl=${localeCode}" async defer></script>    
    	<script>
    		var widget;
			var onloadCallback = function() {
		        widget = grecaptcha.render("html_element", {
		            "sitekey" : "6Ld4zBQTAAAAAE9m4RC2QFswntc7la0cdvFDZ2qU",
		            "theme" : "light"
				});
			};
			
			var showAlert = function(msg){
				$("#formErrors").html(msg);
				$("#formErrorsRow").show();
			};
			
			var clearAlert = function(){
				$("#formErrors").html("");
				$("#formErrorsRow").hide();
			};
			
			var saveProc = function(){
				clearAlert();
				
				var frm = document.registrationForm;
				var cr = grecaptcha.getResponse(widget);
				
				if(cr==""){
					showAlert('<spring:message code="register.captchaBlank" />');
					return;
				}
				
				frm.submitHandler.click();
			};
    	
    		$(document).ready(function(){    			
    			
    		});
    	</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
