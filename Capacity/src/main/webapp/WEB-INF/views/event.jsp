<link rel="stylesheet" href="css/huy.css" />
<link rel="stylesheet" href="css/emojionearea.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" rel="stylesheet">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="event">
    <div class="container">
        <div class="container-form">
            <div class="head">

            </div>
            <div class="body">
                <div class="container cn-event">
                	<p class="result-action text-success" style="display: none"></p>
                    <form class="form-event">
                   	 	<c:if test="${id != null}">
                   	 		<input type="hidden" value="${id}" name="id" class="event-id" id="check-event">
                   	 		<input type="hidden" value="" name="status" class="status">
                 	 	</c:if>
                    	
                    	<div class="form-group">
                          <label for="uname">Choose the event type</label>
                          <select class="form-control ip-event category" id="sel1" name="categoryId">
						  </select>
                        </div>
                        <div class="form-group">
                        	<label for="comment" style="display: none">Community</label>
                          	<input type="text" class="form-control ip-event community" id="uname" placeholder="Community" name="community" required>
                        </div>
                        <div class="form-group">
                        	<label for="comment" style="display: none">Ability to act on its own</label>
                          	<input type="text" class="form-control ip-event act" placeholder="Ability to act on its own" name="abilityAction" required>
                        </div>
                        <div class="form-group">
                        	<label for="comment" style="display: none">Ability to prove yourself</label>
                          	<input type="text" class="form-control ip-event prove" placeholder="Ability to prove yourself" name="abilityProve" required>
                        </div>
                        <div class="form-group">
                        	<label for="comment" style="display: none">Ability to self-development</label>
                          	<input type="text" class="form-control ip-event development" placeholder="Ability to self-development" name="abilityDevelop" required>
                        </div>
                        <div class="form-group">
                        	<label for="comment" style="display: none">Ability to think</label>
                          	<input type="text" class="form-control ip-event think" placeholder="Ability to think" name="abilityThink" required>
                        </div>
                        <div class="submit-event">
                        	<button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                        
                    </form>
                </div>
            </div>
        </div>
        <div class="comment">
	        <div class="list-comment">
	          <div class="content-comment content-comment-realtime" style="display: none">
	          	<div class="c-comment">
	          		<div class="c-c cc">
		          		<div class="content-c"><p></p></div>
		          		<div class="creat"><p></p></div>
	          		</div>
	          		<div class="function">
	          			<button class="del-comment"><i class="bi bi-trash" style="color:red;"></i></button>
	          			<button class="edit-comment"><i class="bi bi-pencil-square" style="color:#7b0d7d;"></i></button>
	          		</div>
	          		<div class="draw"></div>
	          	</div>
	          	
	          	<div class="user-comment">
	          		<div class="user-image"><img></div>
	          		<div class="user-name"></div>
	          	</div>
	          </div>
	          
	          <div class="content-comment-left content-comment-realtime" style="display: none">
	          	<div class="user-comment">
	          		<div class="user-image"><img></div>
	          		<div class="user-name"></div>
	          	</div>
	          	<div class="c-comment">
	          		<div class="draw-left"></div>
	          		<div class="c-c-left cc">
	          			<div class="role"><p></p></div>
		          		<div class="content-c"><p></p></div>
		          		<div class="creat"><p></p></div>
	          		</div>
	          	</div>
	          </div>
	        </div>
	        <div class="form-comment">
	          <form class="form-inline" id="messageForm" name="messageForm" nameForm="messageForm">
	            <div class="avatar-comment"><div class="avatar-img"></div></div>
	            <div class="input-comment">
	            	<input type="hidden" class="id" name="id" disabled>
	            	<input type="hidden" class="event-id" value="${id}" name="event_id">
	              	<textarea class="ip-comment" id="message" name="content" placeholder="Enter a comment"></textarea>
	              	<input type="hidden" class="abilityName" name="abilityName">
	            </div>
	            <div class="submit-comment">
	              <button type="submit" class="submit"></button>
	              <input type="button" class="remove-edit">
	            </div>
	          </form>
	        </div>
      	</div>
    </div>
</div>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/cookie.js"></script>
<script src="/js/socket.js"></script>
<script src="/js/emojionearea.min.js"></script>
<script>
	$(document).ready(function(){
		
		//load avatar comment
		var avatar = getCookie("imageUser");
		$(".avatar-comment").find(".avatar-img").css("background-image","url(../"+avatar+")");
		
		var abilitys = [];
		var abilityName = [];
		var event_id = null;
		if($("#check-event").val() != undefined){
			event_id = $(".event-id").val();
		}
		var check_ability = 0;
		//load data event
		if($("#check-event").val() != undefined){
			$.ajax({
	            type: "get",
	            url: "api/event/"+event_id,
		        success : function (result){
		        	if(result == null || result == ""){
		        		alert('Error: The account is not allowed to access ');
		        		$(".form-event").find("input,select").prop("disabled",true);
		        		$(".submit-event").hide();
		        		$(".comment").hide();
		        	}else{
		        		if(result.classes.teacher.userName != getCookie("userName") && getCookie("role") == "ROLE_TEACHER"){
		        			$(".form-comment").hide();	
		        		}
			        	
		        		$(".status").val(result.status);
		        		$(".category").val(result.category.id);
			        	$(".community").val(result.community);
			        	$(".act").val(result.abilityAction);
			        	$(".prove").val(result.abilityProve);
			        	$(".development").val(result.abilityDevelop);
			        	$(".think").val(result.abilityThink);
			        	if(result.user.userName != getCookie('userName')){
			        		$(".form-event").find("input,select").prop("disabled",true);
			        		$(".submit-event").hide();
			        	}
			        	if(parseInt(result.status) == 1){
			        		check_ability = 1;
			        	}
		        	}
		        	
		        	if(result.status == 1){
		        		$(".form-event").find("input,select").prop("disabled",true);
		        		$(".submit-event").hide();
		        	}
		        	//hide show label
		    		$(".form-control").each(function(i,r){
		    			if($(this).is(':disabled')){
		    				$(this).closest(".form-group").find("label").show();
		    			}else{
		    				if($(this).val() != ""){
		    					$(this).closest(".form-group").find("label").show();
		    				}else{
		    					$(this).closest(".form-group").find("label").hide();
		    				}
		    			}
		    		})
		        }
	        });
			
		}else{
			$(".form-comment").hide();
		}
		
		
		//emoji 
		
		$(".ip-comment").emojioneArea({
			pickerPosition: "top",
	    	tonesStyle: "bullet",
			events: {
	         	keyup: function (editor, event) {
	         		//load ability
	         		if(check_ability == 0){
	         			if(getCookie('role') == "ROLE_ADVISER"){
		        			var val_input = this.getText();
			         		var t_this = this;
			         		var editor = editor;
							if(val_input.substr(val_input.length-1) == "#"){
								$.ajax({
						            type: "get",
						            url: "api/event/abilityEvent/"+event_id,
						            contentType: 'application/json',
						            dataType : 'json',
							        success : function (result){
							        	abilityName = [];
							        	abilitys = [];
							        	$.each(result,function(i,r){
							        		abilityName.push(t_this.getText()+r.name);
							        		abilitys.push("#"+r.name);
							        	})
							        	editor.autocomplete({
							        		position: { my : "right bottom", at: "right top" },
						        	      	source: abilityName,
						        	      	response: function( event, ui ) {
							        	      	$.each(ui.content,function(i,con){
							        	      		con.label = con.label.slice(con.label.lastIndexOf("#"));
							        	      	})
						        	      	}
							            });
							        }
						        });
							};
		        		}
	         		}
	        	},
	        	//read comment notification
				click: function (editor, event) {
					$.ajax({
	    	            type: "put",
	    	            url: "api/comment/status/"+event_id,
	    	            contentType: 'application/json',
	    	            dataType : 'json',
	    		        success : function (result){
	    		        	
	    		        }
	    	        });
				}
	    	}
		
		});
		
		//load list category
		$.ajax({
            type: "get",
            url: "api/event/category",
	        success : function (result){
	        	$.each(result,function(i,r){
	        		$(".category").append("<option value='"+r.id+"'>"+r.name+"</option>");
	        	})
	        }
        });
		
		var clone_comment = $(".content-comment").clone();
		var clone_comment_left = $(".content-comment-left").clone();
		
		//load comment of event
		$.ajax({
            type: "get",
            url: "/api/comment?event_id=${id}",
	        success : function (result){
	        	console.log(result)
	        	$.each(result.listResult,function(i,r){
	        		$.each(r.abilities,function(j,ra){
	        			r.content = r.content.replace("#"+ra.name, "<p style='color:"+ra.color+";display: inline; '>#"+ra.name+"</p>");
	        		})
	        		
	        		clone = clone_comment.clone().show();
	        		if(r.user.userName != getCookie("userName")){
	        			clone = clone_comment_left.clone().show();
	        			clone.find(".role").html(r.user.roles[0].name.slice(r.user.roles[0].name.lastIndexOf("_")+1));
	        		}
	        		clone.find(".c-comment .content-c p").html(r.content);
	        		var date = new Date(r.createdDate);
	        		clone.find(".c-comment .creat p").html(date.getHours()+" : "+date.getMinutes()+" "+(parseInt(date.getMonth())+1)+"/"+date.getDate()+"/"+date.getFullYear());
	        		clone.find(".user-image").css("background-image","url(../"+r.user.image+")");
	        		clone.find(".user-name").html("<b>"+r.user.userName+"</b>");
	        		clone.find(".del-comment").attr("data-id",r.id);
	        		if(parseInt(r.event.status) == 1){
	        			if(r.abilities.length > 0){
	        				clone.find(".del-comment").hide();
		        			clone.find(".edit-comment").hide();
	        			}
	        		}
	        		clone.find(".edit-comment").attr("data-id",r.id);
	        		clone.removeClass("content-comment-realtime");
	        		clone.addClass("content-comment-"+r.id);
	        		$(".list-comment").append(clone);
	        	})
	        	
	        	//scroll bottom
	        	$(".list-comment").animate({ scrollTop: 10000000 },1000);
	        	
	        	$(document).on("mouseenter",".c-comment",function(){
					$(this).find(".cc").css("z-index","0");
				})
				$(document).on("mouseleave",".c-comment",function(){
					$(this).find(".cc").css("z-index","2");
				})
				
				//delete comment
				$(document).on("click",".del-comment",function(){
	            	id = $(this).attr("data-id");
	            	this_t = $(this);
	            	$.ajax({
	    	            type: "delete",
	    	            url: "api/comment",
	    	            contentType: 'application/json',
	    	            dataType : 'json',
	    	            data : JSON.stringify([id]),
	    		        success : function (result){
	    		        	alert('Success! ');
	    		        	send(result[0],"delete");
	    		        	this_t.closest(".content-comment").hide();
	    		        },
	    	            error: function (error) {
	    	                alert('Operation failed');
	    	            }
	    	        });
	            })
	            
	            //edit comment
	            $(document).on("click",".edit-comment",function(){
	            	var this_t = $(this);
	            	var contentComment = $(this).closest(".c-comment").find(".content-c p").html();
	            	while(contentComment.indexOf("<") != -1){
	            		console.log(contentComment)
	            		contentComment = contentComment.slice(0,contentComment.indexOf("<"))+contentComment.slice(contentComment.indexOf(">") + 1,contentComment.length);
	            	}
	            	$(".input-comment").find(".id").val($(this).attr("data-id")).prop("disabled",false);
	            	$(".input-comment").find(".ip-comment").val(contentComment);
	            	$(".input-comment").css("width","78%");
	            	$(".submit-comment").css("width","14%");
	            	$(".remove-edit").show();
	            	$(document).on("click",".remove-edit",function(){
	            		$(".emojionearea-editor").html("");
	            		this_t.closest(".content-comment").show();
	            		$(".input-comment").css("width","85%");
		            	$(".submit-comment").css("width","7%");
	            		$(".remove-edit").hide();
	            		$(".input-comment").find(".id").prop("disabled",true);
	            	});
	            	
	            	$(".ip-comment")[0].emojioneArea.setText($(".ip-comment").val());
	            })
	        }
            
        });
		
		//submit event
		$(".form-event").on("submit",function(e){
			e.preventDefault();
			var data = $(this).serializeArray();
			var type = "post";
			if($("#check-event").val() != undefined){
				type = "put";
			}
			
			$.ajax({
	            type: type,
	            url: "api/event",
	            contentType: 'application/json',
	            dataType : 'json',
	            data : JSON.stringify(getFormData(data)),
		        success : function (result){
		        	if(type == 'post'){
		        		window.location.href = '/student?studentCode='+getCookie('studentCode');
		        	}else{
		        		$(".text-danger").addClass(".text-success").removeClass(".text-danger").show().html("Update successful!");
		        		$(".text-success").show().html("Update successful");
		        	}
		        },
	            error: function (error) {
	            	if(type == 'post'){
	            		$(".text-success").addClass(".text-danger").removeClass(".text-success").show().html("Add failed!");
	            		$(".text-danger").show().html("Add failed");
		        	}else{
		        		$(".text-success").addClass(".text-danger").removeClass(".text-success").show().html("Update failed!");
		        		$(".text-danger").show().html("Update failed");
		        	}
	            }
	        });
		
		})
		
		//submit comment
		$('#messageForm').on('submit', function(e){
			e.preventDefault();
			var method = "post";
			var url = "api/comment";
			var this_t = $(this);
			if(!this_t.find(".input-comment .id").is(':disabled')){
				method = "put";
				url = "api/comment/"+$(this).find(".input-comment .id").val();
			}
			$(".abilityName").val("");
			$.each(abilitys,function(i,r){
				if($(".ip-comment").val().indexOf(r) >= 0){
					ability = $(".abilityName").val();
					$(".abilityName").val(ability+","+r.slice(1));
				}
			})
			var data = $(this).serializeArray();
			$.ajax({
	            type: method,
	            url: url,
	            contentType: 'application/json',
	            dataType : 'json',
	            data : JSON.stringify(getFormData(data)),
		        success : function (result){
		        	send(result,"submit");
		        	if(method == "put"){
		        		$(".input-comment").css("width","85%");
		            	$(".submit-comment").css("width","7%");
	            		$(".remove-edit").hide();
	            		$(".input-comment").find(".id").prop("disabled",true);
					}
		        	$(".emojionearea-editor").html("");
		        },
	            error: function (error) {
	                alert('Operation failed');
	            }
	        });
		    
		});
		
		
		//hide show label
		$(".form-control").on("keyup",function(){
			if($(this).val() == ""){
				console.log($(this).val());
				$(this).closest(".form-group").find("label").hide();
			}else{
				console.log($(this).val());
				$(this).closest(".form-group").find("label").show();
			}
		})
		
		//covert form data
		function getFormData(data) {
		   var unindexed_array = data;
		   var indexed_array = {};
		
		   $.map(unindexed_array, function(n, i) {
		    indexed_array[n['name']] = n['value'];
		   });
		
		   return indexed_array;
		}
		
	})
</script>