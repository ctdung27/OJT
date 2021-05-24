	

    $(document).ready(function () {
	
	    loadStudent();
	    loadTable();
	    
	});
	
	
	function GetURLParameter(sParam) {
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) {
	            return sParameterName[1];
	        }
	    }
	}
	
	// Take last words
	function StringSplit(St){
	    const rs = St.split(' ');
	    return rs[1];
	}
	
	// Get student information
	async function loadStudent(studentCode){
	    studentCode= GetURLParameter('studentCode');
	    const url = '/api/usertracking?code='+studentCode;
	    await fetch(url)
	        .then((response) => response.json())
	        .then((data) => {
	            $('#nameSt').text(data.fullName);
	            $('#codeSt').text(studentCode);
	            if(data.image==null){
	
	            }
	            else{
	                document.getElementById("imgSt").src = data.image;
	            }
	        })
	        
	}
	
	async function getAbilityNameAndColor(){
	    const url = '/api/Ability';
	    await fetch(url)
	        .then((response) => response.json())
	        .then((data) => {
	            data.forEach(item => {
	                var ab = new Ability;
	                ab.Id = item.id;
	                ab.Name =item.name;
	                ab.Color = item.color;
	                ability.push(ab);
	            });
	        })
	}
	
	class Ability{
	    constructor(Id,Name,Color){
	    	this.Id = Id;
	        this.Name = Name;
	        this.Color = Color;
	    }
	}
	const ability = [Ability];
	var  abilityByClass = new Array();
	
	async function loadAbility(){
	            await loadDougnutChart();
	            $('#ab1').append('<td><div class="item-list"  style="background:'+ability[1].Color+';display:-webkit-inline-box"></div>'+ability[1].Name +'</td>');
	            $('#ab1').append('<td><div class="item-list"  style="background:'+ability[2].Color+';display:-webkit-inline-box";></div>'+ability[2].Name +'</td>');
	            $('#ab2').append('<td><div class="item-list"  style="background:'+ability[3].Color+';display:-webkit-inline-box"></div>'+ability[3].Name +'</td>');
	            $('#ab2').append('<td><div class="item-list"  style="background:'+ability[4].Color+';display:-webkit-inline-box";></div>'+ability[4].Name +'</td>');
	            $('#ab3').append('<td><div class="item-list"  style="background:'+ability[5].Color+';display:-webkit-inline-box"></div>'+ability[5].Name +'</td>');
	            $('#ab3').append('<td><div class="item-list"  style="background:'+ability[6].Color+';display:-webkit-inline-box";></div>'+ability[6].Name +'</td>');
	            $('#ab4').append('<td><div class="item-list"  style="background:'+ability[7].Color+';display:-webkit-inline-box"></div>'+ability[7].Name +'</td>');
	            $('#ab4').append('<td><div class="item-list"  style="background:'+ability[8].Color+';display:-webkit-inline-box";></div>'+ability[8].Name +'</td>');
	            $('#ab5').append('<td><div class="item-list"  style="background:'+ability[9].Color+';display:-webkit-inline-box"></div>'+ability[9].Name +'</td>');
	            $('#ab5').append('<td><div class="item-list"  style="background:'+ability[10].Color+';display:-webkit-inline-box";></div>'+ability[10].Name +'</td>');
	            $('#ab6').append('<td><div class="item-list"  style="background:'+ability[11].Color+';display:-webkit-inline-box"></div>'+ability[11].Name +'</td>');
	            $('#ab6').append('<td><div class="item-list"  style="background:'+ability[12].Color+';display:-webkit-inline-box";></div>'+ability[12].Name +'</td>');
	}
	
	
	async function getdata(){
		studentCode=GetURLParameter('studentCode');
	    let ab1 = [0,0,0,0,0,0,0,0,0,0,0,0]; 
	        ab2 = [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab3=  [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab4 = [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab5=  [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab6 = [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab7=  [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab8 = [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab9 = [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab10= [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab11= [0,0,0,0,0,0,0,0,0,0,0,0];
	        ab12= [0,0,0,0,0,0,0,0,0,0,0,0];
	    const a = [];
	    const url = '/api/tracking?code='+studentCode;
	    await fetch(url)
	        .then((response) => response.json())
	        .then((data) => {
	            data.forEach(data => {
	            const index = a.findIndex(item => item.ability === data.ability && item.class_name ===data.class_name);
	            if (index !== -1) {
	            	a[index].numberAppear ++;
	            } else {
	            	a.push(data);
	            }
	          });
	        })
	            a.forEach(item => {
	                if(item.ability =='Tính tự lập'){  
	                    ab1[StringSplit(item.class_name)-1]=item.numberAppear;   
	                }
	                if(item.ability =='Năng lực khuyến khích'){
	                    ab2[StringSplit(item.class_name)-1]=item.numberAppear;   
	          
	                }
	                if(item.ability =='Năng lực thực hành'){
	                    ab3[StringSplit(item.class_name)-1]=item.numberAppear;   
	                
	                }
	                if(item.ability =='Năng lực phát hiện vấn đề'){
	                    ab4[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                 
	                 }
	                if(item.ability =='Năng lực lên kế hoạch'){
	                    ab5[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                
	                }
	                if(item.ability =='Sức sáng tạo'){
	                    ab6[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                  
	                }
	                if(item.ability =='Khả năng truyền đạt'){
	                    ab7[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                   
	                }
	                if(item.ability =='Khả năng lắng nghe'){
	                    ab8[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                  
	                }
	                if(item.ability =='Tính linh hoạt'){
	                    ab9[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                
	                }
	                if(item.ability =='Năng lực nắm bắt tình huống'){
	                    ab10[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                   
	                }
	                if(item.ability =='Tính kỷ luật'){
	                    ab11[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                   
	                 }
	                if(item.ability ==  'Năng lực kiểm soát căng thẳng'){
	                    ab12[StringSplit(item.class_name)-1]=item.numberAppear;   
	                                  
	                 }
	            });
	            abilityByClass.push(ab1);
	            abilityByClass.push(ab2);
	            abilityByClass.push(ab3);
	            abilityByClass.push(ab4);
	            abilityByClass.push(ab5);
	            abilityByClass.push(ab6);
	            abilityByClass.push(ab7);
	            abilityByClass.push(ab8);
	            abilityByClass.push(ab9);
	            abilityByClass.push(ab10);
	            abilityByClass.push(ab11);
	            abilityByClass.push(ab12);
	}
	
	
	// Doughnut Chartjs
	async function loadDougnutChart(){
	    await getdata();
	    await getAbilityNameAndColor();
	    var sum  = [];
	    abilityByClass.forEach(arr=>{
	        sum.push(arr.reduce(function (a, b) { return a + b; }));
	    });
	    var total=sum.reduce(function (a, b) { return a + b; });
	    $('#TotalAb').text(total);
	    const data = {
	        labels: [ability[1].Id,ability[2].Id,ability[3].Id,ability[4].Id,ability[5].Id,ability[6].Id,
	                 ability[7].Id,ability[8].Id,ability[9].Id,ability[10].Id,ability[11].Id,ability[12].Id],
	        datasets: [{
	          label: 'Ability Dougnut Chart',
	          data: sum,
	          backgroundColor:[ability[1].Color,ability[2].Color,ability[3].Color,ability[4].Color,ability[5].Color,ability[6].Color,
	                          ability[7].Color,ability[8].Color,ability[9].Color,ability[10].Color,ability[11].Color,ability[12].Color],
	        }]      
	    };
	    var draw = Chart.controllers.doughnut.prototype.draw;
	    Chart.controllers.doughnut = Chart.controllers.doughnut.extend({
	    draw: function() {
	        draw.apply(this, arguments);
	        let ctx = this.chart.chart.ctx;
	        let _fill = ctx.fill;
	        ctx.fill = function() {
	            ctx.save();
	            ctx.shadowColor = 'gray';
	            ctx.shadowBlur = 10;
	            ctx.shadowOffsetX = 1;
	            ctx.shadowOffsetY = 2;
	            _fill.apply(this, arguments)
	            ctx.restore();
	        }
	    }
	    });
	
	    var canvas = document.getElementById('doughnutChart');
	    var ctx = canvas.getContext('2d');
	    var myNewChart = new Chart(ctx,{
	        type:"doughnut",
	        data,
	        options: {
	        	responsive: true,
	        	 maintainAspectRatio: false,
		        legend: {
		            display: false,
		        },
		        tooltips: {
		        	enabled: false	
		        },
		        hover: {
		            onHover: function(e) {
		               var point = this.getElementAtEvent(e);
		               if (point.length) e.target.style.cursor = 'pointer';
		               else e.target.style.cursor = 'default';
		            }
		         },
		         plugins: {
		                labels: {
		                  render: 'value',
		                  fontStyle: 'bold',
		                  fontColor: '#fff',
		                  precision: 2
		                }
		         },
	        }
	    });
	    canvas.onclick = async function(evt) {
	        var activePoints = myNewChart.getElementsAtEvent(evt);
	        if (activePoints[0]) {
	          var chartData = activePoints[0]['_chart'].config.data;
	          var idx = activePoints[0]['_index'];
	  
	          var label = chartData.labels[idx];
	          var value = chartData.datasets[0].data[idx];
	          loadEventDetail(label);
	        }
	      };
	}
	
	// Load event modal
	async function loadModal(abilityCode){
	    var code = GetURLParameter('studentCode');
	    const url = '/api/events?code='+code+'&ability='+abilityCode;
	    await fetch(url)
	        .then((response) => response.json())
	        .then((data) => {
	        	var myData = data;
	            data = Array.from(new Set(myData.map(JSON.stringify))).map(JSON.parse);
	            var rOut ="";
	            data.forEach(item =>{
	            	rOut += '<tr style="cursor: pointer;" data-href="/event?id='+ item.id + '">';
	                rOut += "<td>"+item.id +"</td>";
	                rOut += "<td>"+item.name+"</td>";
	                rOut += "</tr>";
	            })
	            $('#eventtbModal').html(rOut);
	        })
	}
	
	
	// Table
	async function loadTable(){
	    await loadBarChart();
	    var rOut= "";
	    for (var y=0; y<abilityByClass.length; y++ ) { 
	        
	        rOut += "<tr>";
	        rOut += '<th  style="background:'+ability[y+1].Color+';text-align: left;width:300px;color:white"> '+ ability[y+1].Name + '</th>';
	        for (var x=0; x<abilityByClass[y].length; x++ ) { 
	            if(abilityByClass[y][x] == 0){
	                rOut += "<td> - </td>";
	            }
	            else{
	            rOut += "<td>" + abilityByClass[y][x] + "</td>";
	            }
	        }
	        rOut += "</tr>";
	       
	     }
	     $('#rowAbTable').html(rOut)
	}
	
	
	// Bar chart
	async function  loadBarChart(){
	    await loadAbility();
	    const data = {
	            labels: ['Lớp 1', 'Lớp 2', 'Lớp 3', 'Lớp 4', 'Lớp 5', 'Lớp 6', 'Lớp 7', 'Lớp 8', 'Lớp 9', 'Lớp 10', 'Lớp 11', 'Lớp 12'],
	            datasets: [{
	                label: ability[1].Id,
	                data: abilityByClass[0],
	                backgroundColor: ability[1].Color
	            }, {
	                label: ability[2].Id,
	                data: abilityByClass[1],
	                backgroundColor: ability[2].Color
	            }, {
	                label: ability[3].Id,
	                data: abilityByClass[2],
	                backgroundColor: ability[3].Color
	            }, {
	                label: ability[4].Id,
	                data: abilityByClass[3],
	                backgroundColor: ability[4].Color
	            }, {
	                label: ability[5].Id,
	                data: abilityByClass[4],
	                backgroundColor: ability[5].Color
	            }, {
	                label: ability[6].Id,
	                data: abilityByClass[5],
	                backgroundColor: ability[6].Color
	            }, {
	                label: ability[7].Id,
	                data: abilityByClass[6],
	                backgroundColor: ability[7].Color
	            }, {
	                label: ability[8].Id,
	                data: abilityByClass[7],
	                backgroundColor: ability[8].Color
	            }, {
	                label: ability[9].Id,
	                data: abilityByClass[8],
	                backgroundColor: ability[9].Color
	            }, {
	                label: ability[10].Id,
	                data: abilityByClass[9],
	                backgroundColor: ability[10].Color
	            }, {
	                label: ability[11].Id,
	                data: abilityByClass[10],
	                backgroundColor: ability[11].Color
	            }, {
	                label: ability[12].Id,
	                data: abilityByClass[11],
	                backgroundColor: ability[12].Color 
	            }],
	        };
	    var ctx = document.getElementById('barChart').getContext('2d');
	
	    var canvas = document.getElementById('barChart');
	    var ctx = canvas.getContext('2d');
	    var myNewChart = new Chart(ctx,{
	        type:"bar",
	        data,
	        options: {
		        responsive: true,
		        maintainAspectRatio: false,
		        legend: {
		            position: 'center' ,
		            display: false
		        },
		        tooltips: {
		        	enabled: false	
		        },	
		        scales: {
		        xAxes: [{
		            stacked: true,
		            scaleLabel: {
	                    display: true,
	                    labelString: 'Class',
	                    position:'left',
	                    fontStyle: 'bold',
	                    fontSize:'15',
	                },
	                gridLines: {
		                display: false,
		                color: "rgba(0, 0, 0, 0)",
		             },
		        }],
		        yAxes: [{
		            stacked: true,
		            ticks: {
		                beginAtZero: true
		            },
		            scaleLabel: {
	                    display: true,
	                    labelString: 'Occurrences',
	                    position:'top',
	                    fontStyle: 'bold',
	                    fontSize:'15',
	                },
	                gridLines: {
	                    drawBorder: false
	                   
	                 },
		        }]
		        },
		        hover: {
		            onHover: function(e) {
		               var point = this.getElementAtEvent(e);
		               if (point.length) e.target.style.cursor = 'pointer';
		               else e.target.style.cursor = 'default';
		            }
		         },
		         plugins: {
		        	 labels: {
		                 render: () => {}
		               }
		          },
	        }
	    });

	    canvas.onclick = function(evt) {
	        var activePoint = myNewChart.getElementAtEvent(evt)[0];
	        var data = activePoint._chart.data;
	        var datasetIndex = activePoint._datasetIndex;
	        var label = data.datasets[datasetIndex].label;
	        loadEventDetail(label);
	     };
	 }
	async function  loadEventDetail(label){
		await loadModal(label);
		$('#eventModal').modal('show');
        $('table.event_modal tbody tr').click(function () {
            window.open($(this).data("href"), $(this).data("target"));
        });
	}
	
	
// Change to Table data
	$('#charttotable').change(function(){

	    if(document.getElementById("charttotable").checked){
	        document.getElementById("abtable").classList.remove('d-none');
	        document.getElementById("abtable").classList.add('d-block'); 
	        document.getElementById("abchart").classList.remove('d-block');
	        document.getElementById("abchart").classList.add('d-none');
	    } 
	    else{
	        document.getElementById("abtable").classList.remove('d-block');
	        document.getElementById("abtable").classList.add('d-none'); 
	        document.getElementById("abchart").classList.remove('d-none');
	        document.getElementById("abchart").classList.add('d-block');
	    }
	
	 });
	
	
