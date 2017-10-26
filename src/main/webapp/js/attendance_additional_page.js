	//显示详情内容
	var showResourceGroup = function (resourceGroupId) {
		  $("#icon_group_list1").click();
		  if($("#icon_group_list2 i.icon-chevron-down").length>0){
			  console.log("1");
			  $("#icon_group_list2").click();
			}
	};
	
	var columns = [];
	//显示用户详情内容
	var totalRefreshTable = function (type) {
		//$('#userListTableId').bootstrapTable('destroy');
		columns = [];
		$("#dataTypeSearch").val(type);
		
		var month = $("#monthSearch").val();
		if(month == "" || null == month){
			alert("必须选择月份");
			return;
		}
		
		  $.ajax({
			    url:rootUri + "/openApi/attendenceAdditionalReportColumnName.json",
			    data:{month : month },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    async: true,
			    success:function(returnValue) {
			    	//异步获取要动态生成的列名称
					var arr = returnValue;
					$.each(arr, function (i, item) {
						//columns.push({ "field": item.column, "title": item.column, "align":"center","width":200, "valign": "middle", "sortable": true });
						//columns.push({ "field": item.column, "title": item.column, "align":"center","width": 500, "data-width":"400px", "valign": "middle"});
						
						var columnTitle = item.column;
						if(columnTitle == "month"){
							columnTitle = "月份";
						}else if(columnTitle == "mobile"){
							columnTitle = "手机号";
						}else if(columnTitle == "realName"){
							columnTitle = "姓名";
						}else if(columnTitle == "company"){
							columnTitle = "公司";
						}else if(columnTitle == "department"){
							columnTitle = "部门";
						}else if(columnTitle == "project"){
							columnTitle = "项目名称";
						}else if(columnTitle == "workTime"){
							columnTitle = "上班时间";
						}else if(columnTitle == "remark"){
							columnTitle = "备注";
						}else if(columnTitle == "deviceTotal"){
							columnTitle = "打卡手机数量";
						}else if(columnTitle == "amWorkTime"){
							columnTitle = "上班时间";
						}else if(columnTitle == "pmWorkTime"){
							columnTitle = "下班时间";
						}else if(columnTitle == "lateAmount"){
							columnTitle = "迟到次数";
						}else if(columnTitle == "earlyLeaveAmount"){
							columnTitle = "早退次数";
						}else if(columnTitle == "unCheckAmount"){
							columnTitle = "未打卡次数";
						}
						
//						if(item.column == "remark"){
//							columns.push({ "field": item.column, "title": columnTitle, "align":"center", "width":150, "valign": "middle", "editable":true});
//						}else{
//							columns.push({ "field": item.column, "title": columnTitle, "align":"center", "width":150, "valign": "middle"});
//						}
						if(item.column == "realName"){
							columns.push({ "field": item.column, "title": columnTitle, "align":"center", "width":22, "valign": "middle"});
						}else{
							columns.push({ "field": item.column, "title": columnTitle, "align":"center", "width":20, "valign": "middle"});
						}
						
						
					});
					//配置每一行的修改按钮
					//columns.push({ "field": 'operate', "title": '操作', "events": "operateEvents1", "formatter": "operateFormatter", "align":"center", "width":20, "valign": "middle"});
					//用户列表table
					$('#userListTableId').bootstrapTable('destroy').bootstrapTable({
						method: 'get',
					    url: rootUri + "/openApi/attendenceAdditionalReportList.json", 
					    dataType: "json",
					    queryParams: userQueryParams,
					    pageSize: 1000,
					    //pageList: [100, 1000,10000],  //可供选择的每页的行数（*）
					    pageNumber: 1, // 默认页面
					    //pagination: true, //分页
					    singleSelect: false,
					    idField: "id",  //标识哪个字段为id主键
					    //showColumns: true, //显示隐藏列  
					    //showRefresh: true,  //显示刷新按钮
					    locale: "zh-CN", //表格汉化
					    //search: true, //显示搜索框
					    sidePagination: "server", //服务端处理分页
					    
					    showExport: true,         //是否显示导出
			            exportDataType: "all",    //basic', 'all', 'selected'.
			            buttonsAlign:"right",  //按钮位置  
			            exportTypes:['excel','csv'],  //导出文件类型  
			            Icons:'glyphicon-export',  
			            exportOptions:{
			                //ignoreColumn: [0,1],  //忽略某一列的索引  
			            	//ignoreColumn: [columns.length-1],  //忽略某一列的索引
			                fileName: '月度考勤报表',  //文件名称设置  
			                worksheetName: 'sheet1',  //表格工作区名称  
			                tableName: '月度考勤报表',  
			                //excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],  
			                onMsoNumberFormat: DoOnMsoNumberFormat  
			            },
			            
				       	columns: columns,
						formatLoadingMessage: function () {
					    	return "请稍等，正在加载中...";
					  	},
				        formatNoMatches: function () {  //没有匹配的结果
				            return '无符合条件的记录';
				        }
//					  	,
//				        onEditableSave: function (field, row, oldValue, $el) {
//				            $.ajax({
//				                type: "post",
//				                url: "/wadmin/ad/updateAdInfo",
//				                dataType : 'json',
//				                data: row,
//				                success: function (data, status) {
//				                    if (status == "success" && data.result == 0) {
//				                        toastr.success('更新成功');
//				                        if (field == 'jumpUrl') {
//				                            $('#table').bootstrapTable("refresh");
//				                        }
//				                        return true;
//				                    } else {
//				                        toastr.info(data.message);
//				                        $('#table').bootstrapTable("refresh");
//				                    }
//				                },
//				                error: function () {
//				                    alert("Error");
//				                },
//				                complete: function () {
//
//				                }
//
//				            });
//				        }
				      });
			     },  
			     error : function() { 
			          alert("系统异常！");  
			          //$(buttonObj).button('reset');
			     }  
			});
		  
	  };
    
	  //user table 入参
	 function userQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search: params.search,
	      userName: $("#userNameSearch").val(),
	      mobile: $("#userMobileSearch").val(),
	      company: $("#companySearch").val(),
	      department: $("#departmentSearch").val(),
	      month: $("#monthSearch").val(),
	      dataType: $("#dataTypeSearch").val()  //0 全部记录，1 异常记录
	    };
	    return temp;
	  }
	 
	 function querySummaryInfo() { 
		 var useName = $("#userNameSearch").val();
	     var mobile = $("#userMobileSearch").val();
	     var  company = $("#companySearch").val();
	     var  department = $("#departmentSearch").val();
	     var  month = $("#monthSearch").val();
	      
		 $.ajax({
			    url:rootUri + "/openApi/queryMonthSummary.json",
			    data:{   useName : useName, mobile : mobile, company : company, department : department, month : month },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
			    		$('#unCheckAmount').val(data.content.unCheckAmount);  
			    		$('#lateAmount').val(data.content.lateAmount);  
			    		$('#earlyLeaveAmount').val(data.content.earlyLeaveAmount);  
			    	}else if(data.status == -10){
			    		windows.location = "http://admin.greenlandjs.com/auth/login";
			    	}else{
			    		alert("操作失败！");
			    	}
			     },  
			     error : function() {
			         alert("系统异常！");  
			     }
			});
	 }
	  
      //显示用户详情内容
//	  var showUser = function (userId,userName) {
//		  $("#userId_hidden").val(userId);
//		  $("#userName_hidden").val(userName);
//		  $("#userListTable").hide();
//		  $("#userDetailsTable").show();
//		  initUserDetailForm(userId);
//		  $('#resourceGroupTableId').bootstrapTable('refresh');
//		  $('#resourceTableId').bootstrapTable('refresh');  
//		  $("#userGroupListTableId").bootstrapTable('refresh');
//	  };
//      
	  function DoOnMsoNumberFormat(cell, row, col) {  
	       var result = "";  
	       if (row > 0 && col == 0)  
	           result = "\\@";  
	       return result;  
	   }  
	 

    
    //table里的按钮样式
    function operateFormatter(value, row, index) {  
        return [  
            '<button id="btn_detail" type="button" class="btn btn-xs btn-warning RoleOfA" data-toggle="modal" data-target="#modifyRemarkLayer">修改备注</button>',  
        ].join('');  
    };
    
    window.operateEvents1 = { 
            'click .RoleOfA': function(e, value, row, index) {
                
            	$("#month_modifyRemarkLayer").val(row.month);
            	$("#mobile_modifyRemarkLayer").val(row.mobile);
            	$("#name_modifyRemarkLayer").val(row.realName);
            	$("#remark_modifyRemarkLayer").val(row.remark);
                //$("#dev_id").val(row.id);  
                //modifyRemarkLayer.open();  
            }  
        };  
    
    //修改备注
	 $('#saveButton_modifyRemarkLayer').click(function() {
		 //button失效，防止重复提交
		 //disabled="true"
		 $('#saveButton_modifyRemarkLayer').attr("disabled", true);

		 var month = $("#month_modifyRemarkLayer").val();
     	 var mobile = $("#mobile_modifyRemarkLayer").val();
     	 
     	 var remark = $("#remark_modifyRemarkLayer").val();
       
	    $.ajax({
		    url:rootUri + "/openApi/modifyRemark.json",
		    data:{   month : month, mobile : mobile, remark : remark },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#userListTableId').bootstrapTable('refresh');  
		    		$("#closeButton_modifyRemarkLayer").click();
		    	}else{
		    		alert("操作失败！");
		    	}
		    	$('#saveButton_modifyRemarkLayer').attr("disabled", false);
		     },  
		     error : function() {
		         alert("系统异常！");  
		         $('#saveButton_modifyRemarkLayer').attr("disabled", false);
		     }
		});
       
	 });  
    

		$('.form_month').datetimepicker({
			format: "yyyy-mm",
			language: 'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3, //同 startView: 'year',
			minView: 3, //同 minView:'year',
			maxView:'decade',
			forceParse: 0
	    });