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
						<h3>Your Social Media Profile</h3>
						<div class="connect">
							<h3>Success! You have connected to Facebook!</h3>
							<p>
								<span>Spring Social Facebook</span>
								<span> is connected to your facebook account.
								Click the button if you wish to see your profile.</span>
							</p>
							
							<form id="disconnect" action="/admin/facebook/profile" method="get">
								<button type="submit">View Profile</button>	
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
