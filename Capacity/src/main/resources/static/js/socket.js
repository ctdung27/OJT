'use strict';

var messageForm = $('#messageForm');
var messageInput = $('#message');
var eventId = $('.event-id').val();
var stompClient = null;
var clone_comment = $(".content-comment").clone();
var clone_comment_left = $(".content-comment-left").clone();
var noti = $(".badge");
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
    var socket = new SockJS('/javatechie');
    stompClient = Stomp.over(socket);
    console.log("connect");
    stompClient.connect({}, onConnected, onError);
    
}


function onConnected() {
    // Subscribe to the Public Topic
	console.log("onconnect");
	if(noti.html() != undefined){
		$.each(noti,function(){
			stompClient.subscribe('/comment/send/public/'+$(this).data("id"), onMessageReceived);
		})
	}else{
		stompClient.subscribe('/comment/send/public/'+eventId, onMessageReceived);
	}
}


function onError(error) {
    
}


function onMessageReceived(payload) {
	console.log("onMessageReceived");
    var message = JSON.parse(payload.body);
    if(noti.html() != undefined){
		$(".badge-"+message.eventId).html(parseInt($(".badge-"+message.eventId).html())+1);
	}
    if(message.method == "delete"){
    	$(".content-comment-"+message.id).hide();
    }else{
    	var clone;
        if($(".content-comment-"+message.id).html() != undefined){
        	clone = $(".content-comment-"+message.id);
        }else{
        	if(message.userName == getCookie("userName")){
            	clone = clone_comment.clone().show();
            	clone.find(".del-comment").attr("data-id",message.id);
        		clone.find(".edit-comment").attr("data-id",message.id);
        	}else{
        		clone = clone_comment_left.clone().show();
        		
        		clone.find(".role").html(message.roleName.slice(message.roleName.lastIndexOf("_")+1));
        	}
        }
        
        clone.find(".c-comment .content-c p").html(message.content);
    	var date = new Date(parseInt(message.created));
    	clone.find(".c-comment .creat p").html(date.getHours()+" : "+date.getMinutes()+" "+(parseInt(date.getMonth())+1)+"/"+date.getDate()+"/"+date.getFullYear());
    	clone.find(".user-image").css("background-image","url(../"+message.imageUser+")");;
    	clone.find(".user-name").html("<b>"+message.userName+"</b>");
    	clone.addClass("content-comment-"+message.id);
//    	clone.removeClass("content-comment-realtime");
    	if($(".content-comment-"+message.id).html() == undefined){
    		$(".list-comment").append(clone);
    	}
    	$(".list-comment").animate({ scrollTop: 10000000 },1000);
    }
}
$(document).ready(function(){
	connect();
})

function send(result,method){
	console.log(result)
	$.each(result.abilities,function(j,ra){
		console.log(ra);
		result.content = result.content.replace("#"+ra.name, "<p style='color:"+ra.color+";display: inline;'>#"+ra.name+"</p>");
	})
    var chatMessage = {
    	id: result.id,
    	userName: result.user.userName,
    	roleName: result.user.roles[0].name,
		imageUser: result.user.image,
		content: result.content,
		created: result.createdDate,
		eventId: eventId,
		method: method
    };
    stompClient.send("/app/comment/send/"+eventId,{}, JSON.stringify(chatMessage));
    messageInput.val("");
}
