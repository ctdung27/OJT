<!-- table page student list 
	By: diennv	
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="col-xs-12" style="padding: 25px;">
	<div class="box">
		<div class="box-header">
			<div class="col-xs-12"
				style="padding: 25px 15px 0 15px; margin-left: 20px;">
				<form action="#" method="get" class="row item-search">
					<input type="text" class="studentName" style="margin-left: 10px;" placeholder="StudentName" >
					<select style="padding-bottom: 5px;" id="select_class"> </select> 
					<select style="padding-bottom: 5px;" id="select_event"></select> 
					<input type="text" id="hashtag_event" style="width: 250px; margin-left: 10px;" placeholder="#HashTag" />
					<button type="submit" id="btn_search"
						style="border: none; background: none; margin-left: 5px">
						<i class="fa fa-search" style="color: #33ccff"></i>
					</button>
				</form>
			</div>
			<div class="col-xs-9 btn-tag">
				<input type="text" id="tags_input" data-role="tagsinput" />
				<input type="text" id="hide_input" style="" />
			</div>
			
		</div>
		<hr>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<div class="none-table" style="display: none"></div>
			<table class="table table-hover table-bordered table-responsive-sm table_stList">
				<thead>
					<tr class="capacity-thead">
						<th>ID</th>
						<th>Name</th>
						<th>Class</th>
						<th>Event</th>
						<th>Hashtag</th>
						<th id="_tracking">Tracking</th>
					</tr>
				</thead>
				<tbody id="student_table" class="capacity-tbody">
				</tbody>
			</table>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-end">

				</ul>
			</nav>
		</div>
		<!-- /.box-footer -->
	</div>
	<!-- /.box -->
</div>
<script type="text/javascript">
	var _role = getCookie('role');
	if(_role == "ROLE_PARENT"){
		$("#_tracking").hide();
	}
</script>