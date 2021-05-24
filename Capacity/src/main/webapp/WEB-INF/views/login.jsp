<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Link bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="css/bootstrap.css" /> -->
    <!-- Link font awesome-->
    <link rel="stylesheet" 
    	  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" 
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" 
          crossorigin="anonymous" />
    <!-- Link Css-->
    <link rel="stylesheet" href="/css/Login.css">

</head>
<!-- Form Login by diennv -->

<body>
    <div class="container-fluid main">
        <div class="login">
            <form id="form" class ="authenticate">
                <div class="form-group input-box">
                    <i class="far fa-user"></i>
                    <input type="text" class="" id="email" placeholder="UserName" onkeydown="ValidateEmail()" required>
                    <span class="flash-alert" id="text"></span>
                </div>
                <div class="form-group input-box">
                    <i class="far fa-lock-alt"></i>
                    <input type="password" class="" id="pwd" placeholder="PassWord" onclick="this.value = '' " required>
                    <span class="flash-alert" onclick="showPassword()">
                        <i id="show" class="far fa-eye"></i>
                        <i id="hide" class="far fa-eye-slash"></i>
                    </span>
                </div>
                <p class="login-fail" id= "message"></p>
                <button type="submit" id="login-btn" class="login-btn">Login</button><br>
                <label for="">Forgot Your Password</label>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript" src="/js/Login.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script src="/js/cookie.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		var name = getCookie('userName');
		console.log(name);
		if(name != ""){
			$.ajax({
				type: "GET",
	            url: "api/logout",
		        success : function(result){ 
		        	$("#message").html("");
		        	console.log(result);
	             }
			});
		}
		$(".authenticate").on("submit",function(e){
			e.preventDefault();
			var userName = $("#email").val();
			var passWord = $("#pwd").val();
			$.ajax({
				type: "POST",
	            url: "api/authenticate",
	            dataType: "json",
	            contentType:'application/json',
	            data: JSON.stringify({
	            	"userName" : userName,
	            	"passWord" : passWord
	            }),
		        success : function(result){ 
		        	$("#message").html("");
		        	window.location.href = "/redirect";	        	
	             },
				error: function() {
					$("#message").html("Invalid UserName or Password.");
	             }

			});
		});
	});
</script>
</html>


