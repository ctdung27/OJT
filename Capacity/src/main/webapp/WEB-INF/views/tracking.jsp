
<!-- Tracking -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="middle-content">
<div class="row">
	<!-- Student Information -->
	<div class="col-lg-3 col-md-6">
		<div class="box capacity-box">
			<div class="capacity-box-header">
				<div class="st-data">
						<div class="infor">
							<div class="head-infor"></div>
							<div><strong>Code: </strong>
								<strong id="codeSt">ST001</strong>
							</div>
							<div><strong>Name: </strong>
								<strong id="nameSt">Hieu Nguyen</strong>
							</div>
						</div>
						<div class="st-img">
							<img alt="Student Image" src="Images/Cat5.png" class="imgStudent"
								id="imgSt">
						</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Ability List -->
	<div class="col-lg-4 col-md-6">
		 <div class="box capacity-box" style="position: relative;z-index:2">
    		          <div class="capacity-eventtotal">
        		      <strong id='TotalAb'></strong>
		              </div>
		              <canvas id="doughnutChart"></canvas>
		  </div>
	</div>
	<!-- Ability Information -->
	<div class="col-lg-5">
		<div class="box capacity-box">
					<div>
						<table class="table table-borderless table-responsive ability-tb" >
							<tbody>
								<tr id="ab1">
								</tr>
								<tr id="ab2">
								</tr>
								<tr id="ab3">
								</tr>
								<tr id="ab4">
								</tr>
								<tr id="ab5">
								</tr>
								<tr id="ab6">
								</tr>
							</tbody>
						</table>
					</div>
			</div>
	</div>
</div>
</div>
<!-- Ability table -->
<div class="col-xl-12" style="line-height: 1.0;">
	<div class="box capacity-box" style="height: auto;">
		<!-- Toggle button -->
		<div class="col-xl-12" style="padding-top: 10px; display: table;"> 
                      <span style="padding-top: 10px;float: right">Table</span>
                      <label class="switch" style="float: right;">
                        <input type="checkbox" id="charttotable">
                        <span class="slider round"></span> 
                      </label>            
        </div>   
		<!-- Box body -->
		<div class="box-body">
			<!-- Chart -->
			<div class="d-block" id="abchart">
				<canvas id="barChart" width="1800" height="425"></canvas>
			</div>
			<!-- Table -->
			<div class="d-none" id="abtable">
				<table class="table table-hover table-bordered table-responsive-sm table-text">
					<thead style="text-align: center;">
						<tr class="capacity-tr">
							<th>Khả Năng \ Lớp</th>
							<th>1</th>
							<th>2</th>
							<th>3</th>
							<th>4</th>
							<th>5</th>
							<th>6</th>
							<th>7</th>
							<th>8</th>
							<th>9</th>
							<th>10</th>
							<th>11</th>
							<th>12</th>
						</tr>
					</thead>
					<tbody style="text-align: center;" id="rowAbTable">
                   	</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!--/. Ability Table -->
<!-- Event modal -->
<div class="modal fade modal-event"id="eventModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable" role="document" style="padding: 10px;">
      <div class="modal-content event-modal">
          <div style="background: #1b21a0" class="modal-header text-light">
              <h5 class="modal-title" id="modalTitle">Events</h5>
              <button type="button" class="close text-light" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span>
              </button>
          </div>
          <div class="modal-body">
              <table id="eventTable" class="table table-hover table-bordered event_modal">
                  <thead style="background: #1b21a0" class="text-light">
                      <tr>
                          <th>ID</th>
                          <th>Event Name</th>
                      </tr>
                  </thead>
                  <tbody id="eventtbModal">
                  </tbody>
              </table>
          </div>
      </div>
  </div>
</div>
<!--/. Event modal -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/tracking.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/emn178/chartjs-plugin-labels/src/chartjs-plugin-labels.js"></script>