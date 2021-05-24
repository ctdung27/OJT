function showPassword() {
    var x = document.getElementById("pwd");
    var y = document.getElementById("show");
    var z = document.getElementById("hide");

    if (x.type === 'password') {
        x.type = "text";
        y.style.display = "inline-block";
        z.style.display = "none";
    } else {
        x.type = "password";
        y.style.display = "none";
        z.style.display = "inline-block";
    }
}
/*
 *  ^: Bắt đầu tên
	[a-z0-9_@-]: Tên được quay định chứa các kí tự sau: a-z,0-9,dấu gạch dưới, dấu gạch ngang, dấu @
	{3,15}: Tên có độ dài là từ 3 đến 15
	$: Kết thúc tên
	By: diennv
 * */
function ValidateEmail() {
    // var pattern = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    var pattern = /^([a-zA-Z0-9_@-]{5,20})$/;
    var form = document.getElementById("form");
    var email = document.getElementById("email").value;
    var text = document.getElementById("text");

    if (email.match(pattern)) {
        form.classList.add("valid");
        form.classList.remove("invalid");
        text.innerHTML = "UserName Is Valid.";
        text.style.color = "#00ff00";
    } else {
        form.classList.remove("valid");
        form.classList.add("invalid");
        text.innerHTML = "Enter Valid UserName.";
        text.style.color = "#ff0000";
    }

    if (email == "") {
        form.classList.remove("valid");
        form.classList.remove("invalid");
        text.innerHTML = "";
    }

};