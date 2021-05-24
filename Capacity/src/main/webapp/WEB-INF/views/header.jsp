<header class="main-header">
	<div class="capacity-menu">
		<a class="logo" href="/redirect"> 
			<span>
				<strong class="main-logo">CAPACITY</strong>
			</span>
		</a> <a id="infor"> <img id="avatar" alt="" src=""> 
			<span id="name" class="custom-user-name"></span>
		</a> <a class="fa fa-power-off btn-menu-logout" onclick="return confirm('Are you sure to logout?');" href="/logout"></a>
		<a 	class="fa fa-arrow-left btn-menu"
			onclick="javascript:history.go(-1)">
		</a>
	</div>
</header>
<script src="/js/cookie.js"></script>
<script>
	var img = getCookie('imageUser');
	var name = getCookie('userName');
	document.getElementById("avatar").src = img;
	document.getElementById("name").innerHTML = name;
	if (name == null || name == "") {
		window.location.replace("/logout");
	}
</script>

