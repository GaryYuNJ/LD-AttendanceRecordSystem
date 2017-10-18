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
	var totalRefreshTable = function () {
		  $.ajax({
			    url:rootUri + "/openApi/attendenceReportColumnName.json",
			    data:{resourceId : resourceId, userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    async: true,
			    success:function(returnValue) {
			    	//异步获取要动态生成的列
					var arr = returnValue;
					$.each(arr, function (i, item) {
						columns.push({ "field": item.column, "title": item.column, "align":"center", "valign": "middle", "sortable": true });
					});
					
					//用户列表table
					$('#userListTableId').bootstrapTable('destroy').bootstrapTable({
						method: 'get',
					    url: rootUri + "/openApi/attendenceReportList.json", 
					    dataType: "json",
					    queryParams: userQueryParams,
					    pageSize: 10,
					    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
					    pageNumber: 1, // 默认页面
					    pagination: true, //分页
					    singleSelect: false,
					    idField: "id",  //标识哪个字段为id主键
					    //showColumns: true, //显示隐藏列  
					    //showRefresh: true,  //显示刷新按钮
					    locale: "zh-CN", //表格汉化
					    //search: true, //显示搜索框
					    sidePagination: "server", //服务端处理分页
				       	columns: columns,
						formatLoadingMessage: function () {
					    	return "请稍等，正在加载中...";
					  	},
				        formatNoMatches: function () {  //没有匹配的结果
				            return '无符合条件的记录';
				        }
				      });
			     },  
			     error : function() { 
			          alert("系统异常！");  
			          $(buttonObj).button('reset');
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
	      userName: $("#companySearch").val(),
	      mobile: $("#monthSearch").val()
	    };
	    return temp;
	  }
	  
      //显示用户详情内容
	  var showUser = function (userId,userName) {
		  $("#userId_hidden").val(userId);
		  $("#userName_hidden").val(userName);
		  $("#userListTable").hide();
		  $("#userDetailsTable").show();
		  initUserDetailForm(userId);
		  $('#resourceGroupTableId').bootstrapTable('refresh');
		  $('#resourceTableId').bootstrapTable('refresh');  
		  $("#userGroupListTableId").bootstrapTable('refresh');
	  };
      
	 
	//时间选择器
	$('.datetimepicker').datetimepicker({
		format: "yyyy-mm",
		language: 'zh-CN',
		autoclose:true,
		todayHighlight:true
	});

