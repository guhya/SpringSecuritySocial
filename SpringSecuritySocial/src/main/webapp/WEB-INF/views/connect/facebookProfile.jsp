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
					<h3>Social</h3>
					<div class="pic">
						<img src="http://graph.facebook.com/${profile.id}/picture" alt="profile image"/>
						<span>${profile.name}</span>
					</div>
					<div class="detail">
						<dl>
							<dt>ID:</dt>
							<dd>${profile.id}</dd>
							<dt>Name:</dt>
							<dd>${profile.firstName} ${profile.lastName}</dd>
							<dt>Email :</dt>
							<dd><a href="mailto:${profile.email}">${profile.email}</a></dd>
							<dt>Profile Url:</dt>
							<dd><a href="${profile.link}">${profile.link}</a></dd>
						</dl>
					</div>
					
					<form id="disconnect" action="/connect/facebook" method="post">
						<button type="submit">Disconnect from Facebook</button>	
						<input type="hidden" name="_method" value="delete" />
					</form>
					<p>You will have to sign up once more if you are registered through facebook!</p>
					<hr/>
					<p>
					<a href="/admin/logout" class="btn btn-lrg btn-danger">Logout</a>
					</p>				
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
