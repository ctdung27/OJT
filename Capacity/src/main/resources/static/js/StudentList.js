
$(document).ready(function() {
	var temp_page;
	var page_now = 1;
	loadPage(page_now,true);
	function loadPage(page,start){
		$.ajax({
			type: "GET",
	        url: "api/listPage?pageNo="+page, 
	        dataType: "json",
	        success : function(result){ 
	        	Pagination(result, start)
	    		GetDataInTable(result);
	        	ClickToNextPage();
	         }
		});
		$(".item-search").on("submit",function(e){
			e.preventDefault();
	    	$.ajax({
				type: "GET",
		        url: "api/listPage?pageNo="+page+"&studentName="+$(".studentName").val()
		        		+"&className="+$("#select_class").val()+"&eventName="+$("#select_event").val()
		        		+"&hashTag="+$("#hashtag_event").val().substring(1), 
		        dataType: "json",
		        success : function(result){ 
		        	$(".pagination").html("");
		            $('#hashtag_event').val("");
		            $('.studentName').val("");
		        	Pagination(result, true);
		    		GetDataInTable(result);
		        	ClickToNextPage();
		        	
		        	/*
		    		 * Checks for empty data when searching
		    		 * */
		    		var tr = $('#student_table tr');
		        	if(tr.length == 0){
		        		$(".none-table").show().html("Không tồn tại dữ liệu tìm kiếm").css("text-align", "center");
		    			$('.table_stList').hide();
		        	}else{
		        		$(".none-table").hide();
		        		$('.table_stList').show();
		        	}
		         }
			});
	 });
    	
	}
    
    /*
     * Select Class of Student list
     * */
    $.getJSON("api/Class", function(data) {
    	class_data = '<option value="">Class</option>';
    	$('#select_class').append(class_data);
        $.each(data, function(key, value) {
        	if(value.status == 1){
	            class_data = '<option value="'+value.name+'">' + value.name + '</option>';
	            $('#select_class').append(class_data);
        	}				
        })
       
    });	
    /*
     * Select Event
     * */
    $.getJSON("api/Event", function(data) {
    	event_data = '<option value="">Event</option>';
    	$('#select_event').append(event_data);
        $.each(data, function(key, value) {
            event_data = '<option value="'+value.code+'">' + value.name + '</option>';
            $('#select_event').append(event_data);
        })
       
    });
    /*
     * Hashtag ability
     * */
    $.getJSON("api/Ability", function(data) {
    	var ability_name=[];
        $.each(data, function(key, value) {
        	ability_name.push("#"+value.name);        	
        })
        $("#hashtag_event" ).autocomplete({
  	      source: ability_name
  	    });
        
        var temp;
        $('#hashtag_event').keypress(function(e) {
        	temp=this;
        	 });
        $('#btn_search').click(function(){
            $('.bootstrap-tagsinput input')[0].value += temp.value+", ";
            $('.bootstrap-tagsinput input').focus();
            $('#hide_input').focus();
        });
    });
    /*
     * function click anh double click row in table to next page
     * */
    function ClickToNextPage() {
    	/*
         * Double click row table to StudentPage
         * Code : diennv
         * */
        $('table.table_stList tbody tr').dblclick(function () {
            window.location.href = $(this).data('href');
            //window.open($(this).data('href'), $(this).data('target'));
        });
        /*
         * Click to icon tracking to TrackPage
         * */
        $('table.table_stList tr td#btn-track a').click(function () {
            window.location.href = $(this).data('href');
        });
    };
    /*
     * Function get data in table
     * */
    function GetDataInTable(result){

    	$('tr#tr_link').remove();
    	
    	/*
		 * Load Page When Pagination
		 * */
    	 $('td').remove();
    		$.each(result.listResult, function(key1, valuelistPage){
    			var student_data = '';
                var student_item_ability = '';
                var student_item_event = '';
                        student_data += '<tr id="tr_link" data-href="/student?studentCode=' + valuelistPage.code + '">';
                        student_data += '<td>' + valuelistPage.code + '</td>';
                        student_data += '<td>' + valuelistPage.fullName + '</td>';
                        //column class
                        var class_data = "";
                        $.each(valuelistPage.classes, function(key, valueClass) {
                            if (valueClass.status == 1) {
                                class_data += valueClass.name;
                            }
                        });
                        student_data += '<td>' + class_data + '</td>';
                        //data of column events
                        var event_data = [];
                        var ability_data = [];
                        var color_data = [];
                        $.each(valuelistPage.events, function(key, valueEvent) {
                            event_data.push(" " + valueEvent.category.name);
                            //column comments
                            $.each(valueEvent.comments, function(key, valueComment) {
                                //column hashtag ability
                                $.each(valueComment.abilities, function(key, valueAbility) {
                                    ability_data.push(valueAbility);
                                });
                            });
                        });		                        
                        /*
                         * Handle events loop 
                         * */
                        var event_loop = [];
                        $.each(event_data, function(key, event) {
                        	var check = 0;
                        	$.each(event_loop, function(i, r){
                        		if(r == event){
                        			check=1;
                        			return false;
                        		}
                        	})
                        	if(check == 0){
                        		student_item_event += '<span>, ' + event + '</span>';
                        		event_loop.push(event);
                        	}
                        });
                        /*
                         * Handle abilities loop 
                         * */
                        var ability_loop = [];
                        $.each(ability_data, function(key, ability) {
                        	var check = 0;
                        	$.each(ability_loop, function(i, r){
                        		if(r == ability.name){
                        			check=1;
                        			return false;
                        		}
                        	})
                        	if(check == 0){
                        		student_item_ability += '<span style="color:' + ability.color + ';">#' + ability.name + '    </span>';
                        		ability_loop.push(ability.name);
                        	}
                        });
                        student_data += '<td class="font-weight-bold event_'+ key1 +'" style="text-align: left; width: 500px;"></td>';
                        student_data += '<td style="text-align: left; width: 600px;" class="font-weight-bold hashtag_' + key1 + '"></td>';
                        var _role = getCookie('role');
                        if(_role != "ROLE_PARENT"){
                            student_data += '<td id="btn-track"><a data-href="/tracking?studentCode=' + valuelistPage.code + '"><i style="color: #1cc5dc;" class="fas fa-chart-pie fa-2x"></i></a></td>';
                        }                    
                    
                $('#student_table').append(student_data);
                /*
                 * Xử lý độ dài của hashtag và event
                 * */
                if(student_item_ability.length > 230){
                    $(".hashtag_" + key1).html(student_item_ability.substring(0,211)+"....");
                }
                else{
                	$(".hashtag_" + key1).html(student_item_ability);
                }
                if(student_item_event.length > 150){
                    $(".event_" + key1).html(student_item_event.substring(7, 119)+"....");
                }
                else{
                	$(".event_" + key1).html(student_item_event.substring(7));
                }
    		});
    		
    }
    
    /*
     * Function pagination
     * */
    function Pagination(result, start){
    	if(start==true){
    		if(result.totalPage>=1){
        		$(".pagination").append('<li class="page-item "><button class="back-link pages-link">&laquo;</button></li>');
        	}
    		var i = 1;
        	while(i<=result.totalPage){
        		$(".pagination").append('<li class="page-item"><button class="pages-link" data-page="'+i+'">'+i+'</button></li>');
        		i++;
        	}
        	if(result.totalPage>=1){
        		$(".pagination").append('<li class="page-item"><button class="next-link pages-link">&raquo;</button></li>');
        	}
        	$(".pages-link").click(function(){
        		page_now = $(this).attr("data-page");
        		loadPage(page_now,false);
        	})
    	}
    	if(page_now == 1){
			$(".back-link").prop("disabled", true);
		} else {
			$(".back-link").prop("disabled", false);
			$(".back-link").attr("data-page",parseInt(page_now)-1);
		}
		if(page_now == result.totalPage){
    		$(".next-link").prop("disabled", true);
    	}else{
    		$(".next-link").prop("disabled", false);
    		$(".next-link").attr("data-page",parseInt(page_now)+1);
    	}
    }

});

	