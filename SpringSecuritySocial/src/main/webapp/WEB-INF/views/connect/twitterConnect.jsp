<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="_baseFront">
    
    <tiles:putAttribute name="body"> 
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>Connect</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="connect">
					<h3>Connect to Social Site</h3>
					<div>
						<form action="/connect/twitter" method="POST">
							<p>You haven't created any connections with 
								Social Media yet. Click the button to create a connection between your account and 
								your Social Media profile. (You'll be redirected to twitter where you'll be
								asked to authorize the connection.)</p>
							<button type="submit">Connect</button>
						</form>
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
