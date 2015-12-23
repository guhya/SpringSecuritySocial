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
						<img src="${profile.profileImageUrl}" alt="profile image"/>
						<span>${profile.name}</span>
					</div>
					<div class="detail">
						<dl>
							<dt>ID:</dt>
							<dd>${profile.id}</dd>
							<dt>Name:</dt>
							<dd>${profile.name}</dd>
							<dt>Screen Name :</dt>
							<dd>${profile.screenName}</dd>
							<dt>Twitter Url:</dt>
							<dd><a href="https://twitter.com/${profile.screenName}">${profile.screenName}</a></dd>
							<dt>Website Url:</dt>
							<dd><a href="${profile.url}">${profile.url}</a></dd>
						</dl>
					</div>
					
					<form id="disconnect" action="/connect/twitter" method="post">
						<button type="submit">Disconnect from Twitter</button>	
						<input type="hidden" name="_method" value="delete" />
					</form>
					<p>You will have to sign up once more if you are registered through twitter!</p>
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
