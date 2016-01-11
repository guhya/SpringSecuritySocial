<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<tiles:insertDefinition name="_baseFront">
    <tiles:putAttribute name="css">
    	<style>
    		#map {width: 100%;height: 600px;} 
    		.overlayMarker{
    			position: absolute;
			    width: 92px;
			    height: 28px;
			    line-height: 28px;
			    color: #fff;
			    background: rgba(59,169,205,.85);
			    margin-top: -14px;
			    margin-left: -46px;
			    cursor: pointer;
			    border-radius: 5%;
			}
			.overlayMarker .name {
			    text-align: center;
			    letter-spacing: .5;
			    font-weight: 200;
			    width: 100%;
			    height: 100%;
			}
			.smallOverlayMarker {
				position: absolute;
			    width: 40px;
			    height: 40px;
			    line-height: 40px;
			    font-size: 12px;
			    margin-top: -20px;
			    margin-left: -20px;
    			background: rgba(59,169,205,.85);
    			text-align: center;
    			cursor: pointer;
    			border-radius: 100%;
			}
			.smallOverlayMarker .name{
			    width: 3em;
			    margin-left: -1.5em;
			    top: 12px;
    			font-size: 12px;
    			position: absolute;
			    line-height: 1.2em;
			    color: #fff;
			    margin-top: 2px;
				font-weight: 700;
			    z-index: 10000;
			    text-align: center;
			    letter-spacing: .5;
			    left: 50%;
   			}			
    	</style>					
    </tiles:putAttribute> 
    
    <tiles:putAttribute name="body"> 
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div id="map">Google Map</div>
				</div>
			</div>
		</div>
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
    	<!--<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBiL8HOG4d86DVm4uCSJNf4PfxVDWIMb4E&signed_in=true"></script>-->
    	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    	
    	<script>
    		ewOverlay.prototype = new google.maps.OverlayView();
    		
    		ewOverlay.prototype.onAdd = function(){    			
    			var div = document.createElement("div");
    			div.style.position = "absolute";
    			div.className = this.cssClass_;
    			div.innerHTML = "<div class='name'>"+this.nameCaption_+"</div>";
    			
    			var self = this;
    			div.addEventListener("click", function(){
    				if(self.cssClass_ == "overlayMarker"){
	    				self.map_.setZoom(15);
	    				self.map_.setCenter(self.pos_);
    				}else{
    					alert("Ajax Request : Ewideplus");
    				}
    			});
    			div.addEventListener("mouseover", function(){
    				div.style.border = "2px solid #fff";
    			});
    			div.addEventListener("mouseout", function(){
    				div.style.border = "none";
    			});
    			this.div_ = div;
    			
    			var panes = this.getPanes();
    			panes.overlayImage.appendChild(div);    			
    		};
    		
    		ewOverlay.prototype.draw = function(){
    			var overlayProjection = this.getProjection();
    			
    	        var position = overlayProjection.fromLatLngToDivPixel(this.pos_);
    	        var panes = this.getPanes();
    	        this.div_.style.left = position.x + 'px';
    	        this.div_.style.top = position.y - 30 + 'px';
    		};
    		
    		ewOverlay.prototype.hide = function() {
				if (this.div_) {
					this.div_.style.visibility = 'hidden';
				}
   			};

   			ewOverlay.prototype.show = function() {
				if (this.div_) {
   			    	this.div_.style.visibility = 'visible';
				}
   			};
    		
    		function ewOverlay(pos, map, cssClass, nameCaption){
    			this.pos_ = pos;
    			this.map_ = map;
    			this.cssClass_ = cssClass;
    			this.nameCaption_ = nameCaption;
    			
				//this.setMap(map);
    			if(this.cssClass_ == "overlayMarker"){
    				this.setMap(map);
    			}
    		}
    		
    		var overlay, soverlay;
    		var map, pos, spos;
			function initMap() {
				
				pos = new google.maps.LatLng(37.5404634, 127.0387922);		
				spos = new google.maps.LatLng(37.5419965, 127.0500875);
				
				map = new google.maps.Map(document.getElementById("map"), {
					zoom: 12,
					center : pos,
					mapTypeId: google.maps.MapTypeId.ROADMAP
				});
				
				overlay = new ewOverlay(pos, map, "overlayMarker", "성동구");
				soverlay = new ewOverlay(spos, map, "smallOverlayMarker", "1");
				
				var zoomLevel =  map.getZoom();

			  	map.addListener("zoom_changed", function() {
			  		
					if(soverlay.map==undefined){
						soverlay.setMap(map);
					}
			  		
					zoomLevel =  map.getZoom();
			  		console.log("Zoom Changed " + zoomLevel);
					
					if(zoomLevel > 0 && zoomLevel < 14){
						overlay.show();
					}else{
						overlay.hide();						
					}
					
					if(zoomLevel > 13 && zoomLevel < 20){
						soverlay.show();
					}else{
						soverlay.hide();						
					}
				});
			  	
			  	map.addListener("tilesloaded", function() {
			  		console.log("Map Loaded");
			  	});
			}
    	
    		$(document).ready(function(){    			
    			initMap();
    		});
    	</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
