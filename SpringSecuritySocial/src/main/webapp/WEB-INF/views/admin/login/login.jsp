<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="_baseFront">
    
    <tiles:putAttribute name="body">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>Login</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">				
					<div class="form">
					
						<form action="/j_spring_security_check" class="form-horizontal" method="post">
							<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
								<p class="alert alert-danger">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
							</c:if>
							
							<hr/>						
							<div class="form-group row">
								<label for="j_username" class="control-label col-lg-4">Username</label>
								<div class="col-lg-8">
									<input type="text" id="j_username" name="j_username" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group row">
								<label for="j_password" class="control-label col-lg-4">Password</label>
								<div class="col-lg-8">
									<input type="password" id="j_password" name="j_password" class="form-control" required="required" />
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-4"></div>
								<div class="col-lg-8">								
									<input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" />
									<label for="_spring_security_remember_me" class="control-label">Remember me!</label>
									<p class="help-block"></p>
								</div>
							</div>
							<hr/>
							<div class="form-group">
								<div class="col-lg-12">
									<button class="btn btn-success" type="submit">Login</button>
								</div>
							</div>
						</form>
						
					</div>
				</div>
				
				<div class="col-lg-6">
					<div class="row"><div class="col-lg-12">&nbsp;</div></div>
					
					<div class="row">
						<div class="col-lg-12">
							<button class="btn btn-lrg" id="twitter-btn" onclick="top.location='/auth/twitter';" style="width: 100%; background-color: #4099FF; color:#fff;">
							<span class="fa fa-twitter"></span> Twitter Login</button>
						</div>
					</div>
					
					<div class="row"><div class="col-lg-12">&nbsp;</div></div>
					
					<div class="row">
						<div class="col-lg-12">
							<button class="btn btn-lrg" id="fb-btn" onclick="top.location='/auth/facebook';" style="width: 100%; background-color: #3b5998; color:#fff;">
							<span class="fa fa-facebook"></span> Facebook Login</button>
						</div>
					</div>
					
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