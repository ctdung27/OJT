<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<c:url var="studentURL" value="/student" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Spring Boot</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Link bootstrap-->
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="sw/sweetalert2.min.css" />
<!-- Link font awesome-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="css/capacity.css" />
<link rel="stylesheet" href="css/std.css" />
<link rel="stylesheet" href="css/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/sw/sweetalert2.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</head>
<body class="capacity-content">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="cp-content">
		<div class="col-xs-12" style="padding: 15px;">
			 <security:authorize ifAnyGranted="ROLE_STUDENT">
			 		<style>
						a.fa.fa-arrow-left
							{
								display: none;
							}
					</style>
					<button class="btn createbtn" onclick="window.location.href='/event'">
						<i class="fa fa-plus-circle"></i> Create event
					</button>
			</security:authorize> 
			<div class="box">
				<div class="box-header">
				 <security:authorize ifNotGranted="ROLE_STUDENT" >
					<div class="row col-xs-4" style="padding: 15px 10px 10px 22px;">
						<div class="item-search " style="display: flex">
							<div class="item-infor">${student.get(0).studentName}</div>
							<div class="item-infor">${student.get(0).studentCode}</div>
							<div class="item-infor">${student.get(0).studentClass}</div> 
						</div>
					</div>
					<hr>
				</security:authorize> 
					<div class="row">
					<div class="col-7" style="padding-top: 10px;">
						<form:form action="${studentURL}" commandName="model" method="get">
							<div class="item-search" style="display: flex">
								<security:authorize ifNotGranted="ROLE_STUDENT" >
									<form:hidden path="studentCode"
									value="${student.get(0).studentCode}" />
								</security:authorize>
								
								<div class="boder-right">
									<form:select path="classes"  class="item-selects">
										<form:option value="" label="All Class" />
										<form:options items="${classs}" />
									</form:select>
								</div>
								<div class="boder-right">
									<form:select path="eventName" class="item-selects">
										<form:option value="" label="All Event" />
										<form:options items="${eventCategories}" />
									</form:select>
								</div>
								<span style="padding-left: 30px;">Status:   </span>
								<form:checkbox path="status" value="1" style="margin: 5px 5px 5px 25px"  />Approved
								<form:checkbox path="status" value="2" style="margin: 5px 5px 5px 25px" />Pending
								<form:checkbox path="status" value="3" style="margin: 5px 5px 5px 25px" />Denied 
							
								<form:button type="submit" style="border: none; background: none; margin-left: 25px">
									<i class="fas fa-search" style="color: #33ccff; font-size:25px;"></i>
								</form:button>
							</div>
						</form:form>
					</div>
					<c:if test="${not empty message}">
					
					<div class="col-5">
							<div class="alert alert-${alert}" style="text-align: center; margin-bottom: 0; margin-right: 15px;" >
 						 		<strong> ${message}</strong>
							</div>
					</div>
					</c:if>
					</div>
					<hr>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="overflow: hidden;">
						<table id="datatable" class="table table-hover table-bordered">
							<thead style="text-align: center">
								<tr class="capacity-tr">
									<th style="text-align: center;">Year</th>
									<th>Event</th>
									<th style="text-align: center;">Status</th>
									<th style="text-align: center;">Notice</th>
									<security:authorize ifAnyGranted="ROLE_STUDENT,ROLE_ADVISER">
										<th style="text-align: center;">Action</th>
									</security:authorize>

								</tr>
							</thead>
							<tbody class="tbody-table">
								<c:forEach var="item" items="${student}">
									<tr class="content-table">
										<td style="text-align: center;">${item.classes}</td>
										<td>
											<div>
											<div style="display:inline-block; width: 60px;">
												<i class="${item.icon}" style="color: #33ccff; padding:0px 5px 0px 20px; font-size:25px"></i>
											</div>
											
											<div style="display:inline-block;">
												${item.eventName}
											</div>
											</div>
										</td>
										<td style="text-align: center;"><c:if
												test="${item.status == 1}">
												<a class="button5 button-color1">Approved</a>
											</c:if> <c:if test="${item.status == 2}">
												<a class="button5 button-color2">Pending</a>
											</c:if> <c:if test="${item.status == 3}">
												<a  class="button5 button-color3">Denied</a>
											</c:if></td>
										<td style="text-align: center;">
											<c:url var="editEvent" value="/event">
												<c:param name="id" value="${item.id}" />
											</c:url> 
											<c:if test="${not empty item.status}">
											<a href="${editEvent}" class="notification"> 
												<i style="font-size: 40px; color: dodgerblue" class="las la-bell"></i> 
												<span class="badge badge-${item.id}" data-id="${item.id}"></span>
											</a>
											</c:if>
										</td>
									<security:authorize ifAnyGranted="ROLE_STUDENT">										
										<td style="text-align: center;">
												<c:url var="editEvent" value="/event">
													<c:param name="id" value="${item.id}" />
												</c:url>
												<c:if test="${(item.status == 2) or (item.status == 3)}">
													<a href="${editEvent}"> <i class="fas fa-pencil-alt" style="padding-right: 10px;"></i> </a> 
													<button onclick="warning(${item.id})" style="border: none; background: none; outline:none;"><i class="fas fa-trash-alt text-danger"></i></button>
												</c:if>
												<c:if test="${item.status == 1}">
													<a> <i class="fas fa-pencil-alt" style="padding-right: 10px; color: #7b858c;"></i> </a> 
													<button style="border: none; background: none; outline:none; cursor:default;"><i class="fas fa-trash-alt" style="color: #7b858c;"></i></button>
												
												</c:if> 
										 </td>
										</security:authorize> 
										<security:authorize ifAnyGranted="ROLE_ADVISER">
											<td style="text-align: center;">
												<c:if test="${item.status == 2}">
													<a  onclick="warningChange(${item.id})"><i class="fas fa-check" style="padding-right: 10px; color: #33ccff;"></i></a>
												</c:if>
												<c:if test="${(item.status == 1) or (item.status == 3)}">
													<a><i class="fas fa-check" style="padding-right: 10px; color: #7b858c;"></i></a>
												</c:if>
											</td>
										</security:authorize>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->

				</div>
				<!-- /.box -->
			</div>
		</div>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/cookie.js"></script>
<script src="/js/socket.js"></script>
<script type="text/javascript"  >

$(document).ready(function() {
	$('#datatable').DataTable({
		"searching": false,
		//"info": false,
		"lengthChange": false,
		"scrollCollapse": false,
		"order": [[0, "desc" ]]
		
	});
	
	var noti = $(".badge");
	$.each(noti,function(i,r){
		var this_t = $(this);
		$.ajax({
			url: '/api/student/statusComment/'+$(this).data("id"),
			type: 'get',
			contentType: 'application/json',
			success: function(res) {
				if(parseInt(res) == 0 ){
					this_t.hide();
				}
				this_t.html(res);
			},
			error: function(res ) {
			}
		});
	})
});
function warning(code) {
	showAlertBeforeDelete(function() {
		event.preventDefault();
		var data = [];
		data.push(code);
		deleteEvent(data);
	});
}
function showAlertBeforeDelete(callback) {
	swal({
		title: "DELETE",
		text: "Do You Really Want To Delete?",
		type: "warning",
		showCancelButton: true,
		confirmButtonText: "Ok, delete it!",
		cancelButtonText: "Cancel",
		confirmButtonClass: "#0bb965",
		cancelButtonClass: "btn btn-danger"

	}).then(function(res) {
		if (res.value) {
			callback();
		} else if (res.dismiss == 'cancel') {
			console.log('cancel');
		}
	});
}
function deleteEvent(data) {
	console.log(data);
	var studentCode = "${student.get(0).studentCode}"; 
	$.ajax({
		url: '/api/student?studentCode=' + studentCode,
		type: 'DELETE',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(res) {
				
				window.location.href = "student?message=delete_success";
			},
		error: function(res ) {
				window.location.href = "student?message=error_system";
		}
	});
}

function warningChange(code) {
	showAlertBeforeChange(function() {
		event.preventDefault();
		var data = [];
		data.push(code);
		changesStatus(data);
	});
}


function showAlertBeforeChange(callback) {
	swal({
		title: "Do You Want To Changes Status?",
		text: "Pending to Approved",
		type: "question",
		showCancelButton: true,
		confirmButtonText: "Ok, changes it!",
		cancelButtonText: "Cancel",
		confirmButtonClass: "#0bb965",
		cancelButtonClass: "btn btn-danger"

	}).then(function(res) {
		if (res.value) {
			callback();
		} else if (res.dismiss == 'cancel') {
			console.log('cancel');
		}
	});
}
function changesStatus(data) {
	console.log(data);
	var studentCode = "${student.get(0).studentCode}"; 
	$.ajax({
		url: '/api/student?studentCode='+ studentCode,
		type: 'PUT',
		dataType : 'json',
		contentType: 'application/json',
		data: JSON.stringify({"id":data[0]}),
		success: function(res) {
			console.log(res);
			window.location.href = "student?studentCode="+ studentCode + "&message=changes_success";
		},
		error: function(res) {
			console.log('error');
		}
	});
}
</script> 

</html>





























