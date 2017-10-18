	//显示详情内容
	var showResourceGroup = function (resourceGroupId) {
		  $("#icon_group_list1").click();
		  if($("#icon_group_list2 i.icon-chevron-down").length>0){
			  console.log("1");
			  $("#icon_group_list2").click();
			}
	};

	//用户列表table
	$('#userListTableId').bootstrapTable({
		method: 'get',
	    url: rootUri + "/user/showUserList.json", 
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
       	columns: [
			{
			    title: '用户ID',
			      field: 'id',
			      align: 'center',
			      valign: 'middle'
			  }, 
               {
                 title: '用户名称',
                   field: 'name',
                   align: 'center',
                   valign: 'middle'
               }, 
               {
                   title: '性别',
                   field: 'sex',
                   align: 'center',
                   valign: 'middle',
               }, 
               {
                   title: '手机号',
                   field: 'mobile',
                   align: 'center'
               },
               {
                   title: '邮箱',
                   field: 'email',
                   align: 'center'
               },
               {
                   title: '生日',
                   field: 'birthdayStr',
                   align: 'center',
               },
               {
                   title: '客户关系',
                   field: 'relation',
                   align: 'center',
               },
               {
                   title: '操作',
                   field: 'id',
                   align: 'center',
                   formatter:function(value,row,index){
                	 var e = '<button class="btn btn-xs btn-warning" onclick="showUser(\''+ row.id + '\',\''+ row.name + '\')"><i class="icon-pencil"></i>详情</button>  ';
	                 //var e = '<a href="javascript:void(0);" mce_href="#" onclick="showUser(\''+ row.id + '\',\''+ row.name + '\')">详情</a> ';  
	                 //var d = '<a href="#" mce_href="#" onclick="delete(\''+ row.id +'\')">删除</a> ';  
	                 //   return e+d;
	                 return e; 
                 } 
               }
           ],
		formatLoadingMessage: function () {
	    	return "请稍等，正在加载中...";
	  	},
        formatNoMatches: function () {  //没有匹配的结果
            return '无符合条件的记录';
        }
      });
	
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
	      mobile: $("#userMobileSearch").val()
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
      
	  //初始化 UserDetailForm 
	  var initUserDetailForm = function (userId) {
		  $.ajax( {  
			    url:rootUri + "/user/showUserDetail.json",
			    data:{   userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$("#userId_InForm").val(data.id);
			    	$("#userName_InForm").val(data.name);
			    	$("#userMobile_InForm").val(data.mobile);
			    	$("#userSex_InForm").val(data.sex);
			    	$("#userEmail_InForm").val(data.email);
			    	$("#userBirth_InForm").val(data.birthdayStr);
			    	$("#userRelation_InForm").val(data.relation);
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
	  };
	
      //userGroupListTableId 加载
	  var userGroupListTableInit = function () {
		    var oUserGroupListTableInit = new Object();
		    $('#userGroupListTableId').bootstrapTable({
				method: 'get',
			    url: rootUri + "/userGroup/showUserGroupJoinUserId.json", 
			    dataType: "json",
			    queryParams: userGroupQueryParams,
			    pageSize: 10,
			    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
			    pageNumber: 1,
			    pagination: true, //分页
			    singleSelect: false,
			    idField: "id",  //标识哪个字段为id主键
			    //showColumns: true, //显示隐藏列  
			    //showRefresh: true,  //显示刷新按钮
			    locale: "zh-CN", //表格汉化
			    //search: true, //显示搜索框
			    sidePagination: "server", //服务端处理分页
			    height: 390, 
			    cache: true,
			    uniqueId: "id",           //每一行的唯一标识，一般为主键列
			    toolbar: '#toolbar',        //工具按钮用哪个容器
		        striped: true,           //是否显示行间隔色
		       	columns: [
					{
					    title: '用户组ID',
					      field: 'id',
					      align: 'center',
					      valign: 'middle'
					  }, 
		               {
		                 title: '用户组名称',
		                   field: 'name',
		                   align: 'center',
		                   valign: 'middle'
		               }, 
		               {
		                   title: '创建时间',
		                   field: 'createDateStr',
		                   align: 'center'
		               },
		               {
		                   title: '创建人',
		                   field: 'createUser',
		                   align: 'center'
		               },
		               {
		                   title: '操作',
		                   field: 'id',
		                   align: 'center',
		                   width: 90,
		                   formatter:function(value,row,index){
		                	   if(row.extendLong1 != null){
		                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="delUserGroupRelation(this, \''+ row.id +'\')" data-toggle="modal" data-loading-text="Loading...">移除</button>';
		                		   //var e = '<a href="javascript:void(0)" mce_href="#" onclick="delUserGroupRelation(this, \''+ row.id +'\')">移除</a> ';  
		                	   }else{
		                		   var e ='<button type="button" class="btn btn-xs btn-success"  onclick="addUserGroupRelation(this, \''+ row.id + '\')" data-toggle="modal" data-loading-text="Loading...">加入</button>';
		                		   //var e = '<a href="javascript:void(0)" mce_href="#" onclick="addUserGroupRelation(this, \''+ row.id + '\')">加入</a> ';  
		                	   }
		                    return e;  
		                 } 
		               }
		           ],
				formatLoadingMessage: function () {
			    	return "请稍等，正在加载中...";
			  	},
                formatNoMatches: function () {  //没有匹配的结果
                    return '无符合条件的记录';
                }
		      });
	  };
	
	  //userGroup table 入参
	 function userGroupQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search: $("#userGroupNameSearch").val(),
	      userId: $("#userId_hidden").val()
	    };
	    return temp;
	  }
	  
	  //删除usergroup与user关系
	 function delUserGroupRelation(obj, groupId) {
		 $(obj).button('loading');
		 var userId = $("#userId_hidden").val();
		 $.ajax( {  
			    url:rootUri + "/user/delUserGroupRelation.json",
			    data:{   groupId : groupId, userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	//$(obj).button('reset');//使用reset会恢复原始状态，导致后面的html文字修改失效
			    	if(data.status == 1){
				    	$(obj).attr("onclick", "addUserGroupRelation(this, "+groupId+")");
				    	$(obj).html("加入");
			    		//更换属性
			    		$(obj).removeClass('btn-warning');
			    		$(obj).addClass('btn-success');
			    		$(obj).removeClass('disabled');
			    		$(obj).attr("disabled", false);
			    	}else{
			    		alert("操作失败！");
			    	}
			    	
			     },  
			     error : function() {  
			          alert("系统异常！");  
			          $(obj).button('reset');
			     }  
			});
	  };
	  
		//添加usergroup与user关系
		 function addUserGroupRelation(obj, groupId) {
			 $(obj).button('loading');
			 var userId = $("#userId_hidden").val();
			 $.ajax( {  
				    url:rootUri + "/user/addUserGroupRelation.json",
				    data:{   groupId : groupId, userId : userId },  
				    type:'get',  
				    cache:false,  
				    dataType:'json',  
				    success:function(data) {
				    	//$(obj).button('reset');//使用reset会恢复原始状态，导致后面的html文字修改失效
				    	if(data.status == 1){
					    	$(obj).attr("onclick", "delUserGroupRelation(this, "+groupId+")");
					    	$(obj).html("移除");
					    	//更换属性
				    		$(obj).removeClass('btn-success');
				    		$(obj).addClass('btn-warning');
				    		$(obj).removeClass('disabled');
				    		$(obj).attr("disabled", false);
				    	}else{
				    		alert("操作失败！");
				    	}
				     },  
				     error : function() {  
				          alert("系统异常！");  
				          $(obj).button('reset');
				     }  
				});
		  };
	  
	 //tab 切换
    $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
      
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
          
          //点击tab调用对应function
          if($(this).attr("href") == "#userGroup"){
        	  userGroupListTableInit();
        	  //$("#userGroupListTableId").bootstrapTable('refresh');
          } 
        //点击tab调用对应function
          if($(this).attr("href") == "#userResource"){
        	  resourceTableInit();
        	  //$("#userGroupListTableId").bootstrapTable('refresh');
          } 
        
        //点击tab调用对应function
          if($(this).attr("href") == "#userResourceGroup"){
        	  resourceGroupTableInit();
        	  //$("#userGroupListTableId").bootstrapTable('refresh');
          } 
        })
      })
	 
	function resourceTableInit() {
		$('#resourceTableId').bootstrapTable({
			method: 'get',
		    url: rootUri + "/manage/resourceSearchWithCusId.json", 
		    dataType: "json",
		    queryParams: resourceQueryParams,
		    pageSize: 10,
		    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
		    pageNumber: 1,
		    pagination: true, //分页
		    singleSelect: false,
		    striped: true,
		    idField: "id",  //标识哪个字段为id主键
		    sidePagination: "server", //服务端处理分页
	       	columns: [
				{
				    title: '名称',
				      field: 'name',
				      align: 'center',
				      valign: 'middle'
				  }, 
	               {
	                 title: '类型',
	                   field: 'permissionAttrId',
	                   align: 'center',
	                   valign: 'middle',
	                   formatter:function (value, row, index) {
	                	   if(value=="1") return "公共资源";
	                	   if(value=="2") return "基础资源";
	                	   if(value=="3") return "私有资源";
                        }
	               }, 
	               {
	                   title: '楼栋',
	                   field: 'buildingId',
	                   align: 'center',
	                   valign: 'middle'
	               }, 
	               {
	                   title: '节点路径',
	                   field: 'nodePath',
	                   align: 'center',
	                   valign: 'middle',
	                   formatter:function (value, row, index) {
	                	   return findBuildName(value);
                        }
	               }, 
	               {
	                   title: '权限',
	                   field: 'cusResRelModel',
	                   align: 'center',
	                   formatter:function (value, row, index) {
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value 
		                			   && value.enable=="Y"){
		                		   if((null == row.cusResRelModel.startDate || new Date(row.cusResRelModel.startDate) < new Date() )
			                			   &&  (null == row.cusResRelModel.endDate || new Date(row.cusResRelModel.endDate) > new Date() )){
		                			   return '<span class="label label-success">有权限</span>';
		                		   }else{
		                			   return '<span class="label label-danger">已过期</span>';
		                		   }
		                	   }else{
		                		   return '<span class="label label-danger">无权限</span>';
		                	   }
	                	   }
	                	   
                        }
	               },
	               {
	                   title: '权限起始时间',
	                   field: 'cusResRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value){
		                		   if(null == value.startDate){
		                			   return "无限制";
		                		   }else{
		                			   return new Date(value.startDate).format("yyyy-MM-dd HH:mm");
		                		   }
		                	   }else{
		                		   return "-";
		                	   }
	                	   }
	                 } 
	               },
	               {
	                   title: '权限截至时间',
	                   field: 'cusResRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value){
		                		   if(null == value.endDate){
		                			   return "无限制";
		                		   }else{
		                			   return new Date(value.endDate).format("yyyy-MM-dd HH:mm"); 
		                		   }
		                	   }else{
		                		   return "-";
		                	   }
	                	   }
	                 }
	               },
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 80,
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
		                	   if(null != row.cusResRelModel 
		                			   && row.cusResRelModel.enable == "Y"
		                			   && (null == row.cusResRelModel.startDate || new Date(row.cusResRelModel.startDate) < new Date() ) 
		                			   &&  (null == row.cusResRelModel.endDate || new Date(row.cusResRelModel.endDate) > new Date() ) ){
		                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="removePermission(\''+ row.id +'\',this)" data-toggle="modal" data-loading-text="Loading...">禁用</button>';
		                	   }else{
		                		   var cusResRelModel = null;
		                		   var startDate = null;
		                		   var endDate = null;
		                		   if(null != row.cusResRelModel ){
		                			   if(null != row.cusResRelModel.startDate){
		                				   startDate = new Date(row.cusResRelModel.startDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                			   if(null != row.cusResRelModel.endDate){
		                				   endDate = new Date(row.cusResRelModel.endDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                		   }
		                		   var e ='<button type="button" class="btn btn-xs btn-success" data-toggle="modal" onclick="addPermmissionPreProcess(\''+ row.id +'\',\''+ row.name +'\',\''+ startDate +'\',\''+ endDate +'\')" data-target="#addPermissionLayer">授权</button>';
		                	   }
		                    	return e;  
	                	   }
	                 } 
	               }
	           ],
	           formatLoadingMessage: function () {
			    	return "请稍等，正在加载中...";
			  	},
               formatNoMatches: function () {  //没有匹配的结果
                   return '无符合条件的记录';
               }
	      });
	};
	
	function resourceQueryParams(params) {
        //定义参数  
        var search = {};  
        //遍历form 组装json  
        $.each($("#resourceSearchform").serializeArray(), function(i, field) {  
            //console.info(field.name + ":" + field.value + " ");  
            //可以添加提交验证  
            search[field.name] = field.value;  
        });  

        //参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
        params.search = JSON.stringify(search);
        console.info(params);  
        return params;  
    }  
	
	//自定义resource查询
	 $('#doSearchResource').click(function() {
	        //var params = $('#resourceTableId').bootstrapTable('getOptions');  
	        $("#resourceNodeId_hidden").val(null);
	        $('#resourceTableId').bootstrapTable('refresh');  
	        //console.info(params);  
    });  	

	//给楼栋赋值Ajax
	var buildings;
	$.get(rootUri + "/manage/allBuildings.json",function(data,status){
		if(status=4){
			$.each(data, function (n,value) {
				$("#buildingId").append("<option value='"+value.id+"'>"+value.name+"</option>");
				$("#addbuildsId").append("<option value='"+value.id+"'>"+value.name+"</option>");
				buildings=data;
			});
		}
	});
	
	function findBuildName(id){
		var tempvalue;
		$.each(buildings, function (n,value) {
			if(id==value.id) {
				tempvalue=value.name;
				return false;
			}
		});
		return tempvalue;
	}
	
   function findBuildName(id){
		var tempvalue;
		$.each(buildings, function (n,value) {
			if(id==value.id) {
				tempvalue=value.name;
				return false;
			}
		});
		return tempvalue;
	}
 	
	
	//更新用户权限
	 $('#saveButton_addPer').click(function() {
		 //button失效，防止重复提交
		 //disabled="true"
		 $('#saveButton_addPer').attr("disabled", true);

		 //关联用户组查询用户标识
		 var jointAuthFlag = null;
        //定义参数  
        var array = {};  
        //遍历form 组装json  
        $.each($("#addResPermissionForm").serializeArray(), function(i, field) {  
            //可以添加提交验证  
            if('' == field.value){
            	array[field.name] = null;  
            }else{
            	array[field.name] = field.value;  
            }
            if(field.name == 'jointAuthFlag_addPer'){
            	jointAuthFlag = field.value;
            }
        });  

        //参数转为json字符串，并赋给变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
        var modelJsonStr = JSON.stringify(array);
        
        var startDateStr =$("#startDate_addPer").val();
        var endDateStr =$("#endDate_addPer").val();
 	    $.ajax({
 		    url:rootUri + "/user/authCusResPermission.json",
 		    data:{   modelJsonStr : modelJsonStr, jointAuthFlag : jointAuthFlag, startDateStr : startDateStr, endDateStr : endDateStr },  
 		    type:'get',  
 		    cache:false,  
 		    dataType:'json',  
 		    success:function(data) {
 		    	if(data.status == 1){
 		    		$('#resourceTableId').bootstrapTable('refresh');  
 		    		$("#closeButton_addPer").click();
 		    	}else{
 		    		alert("操作失败！");
 		    	}
 		    	$('#saveButton_addPer').attr("disabled", false);
 		     },  
 		     error : function() {  
 		          alert("系统异常！");  
 		         $('#saveButton_addPer').attr("disabled", false);
 		     }  
 		});
        
	 });  
	
	
   function addPermmissionPreProcess(resourceId, resourceName, startDate, endDate){
	   $('#resourceId_addPer').val(resourceId);
	   $('#resourceName_addPer').val(resourceName);
	   $('#userId_addPer').val($("#userId_hidden").val());
	   $('#userName_addPer').val($("#userName_hidden").val());
	   if(null != startDate && startDate != 'null'){
		   $('#startDate_addPer').val(startDate);
	   }
	   if(null != endDate && endDate != 'null'){
		   $('#endDate_addPer').val(endDate);
	   }
   }
   
   //禁用资源
   function removePermission(resourceId, buttonObj){
	   if (!confirm('您确定取消授权吗？')) {
			return;
		}
		$(buttonObj).button('loading');
		//.delay(1000).queue(function() {
		//	$(buttonObj).button('reset');
		//});
		
	   var userId = $("#userId_hidden").val();
	   $.ajax({
		    url:rootUri + "/user/disableResourcePermission.json",
		    data:{   resourceId : resourceId, userId : userId },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');  
		    	}else{
		    		alert("操作失败！");
		    	}
		    	$(buttonObj).button('reset');
		     },  
		     error : function() {  
		          alert("系统异常！");  
		          $(buttonObj).button('reset');
		     }  
		});
   }
   
   //资源树
   $('#jstree_resource').jstree({
		"core": {
			"multiple" : false,
			"animation": 0,
			"check_callback": true,
			"themes": {
				"stripes": true
			},
			'data': {
				'url': rootUri + "/manage/showNode.json",
				'data': function(node) {
				}
			}
       },
		"types": {
			"#": {
				"max_children": 1,
				"max_depth": 6,
				"valid_children": ["root"]
			},
			"root": {
				"icon": rootUri + "/js/themes/default/tree_icon.png",
				"valid_children": ["default"]
			},
			"default": {
				"valid_children": ["default", "file"]
			},
			"file": {
				"icon": "glyphicon glyphicon-file",
				"valid_children": []
			}
		},
		"plugins": [
			"contextmenu",  "search","types", "wholerow"
		],
		"contextmenu": {    
           "items": {    
               "create": null,    
               "rename": null,    
               "remove": null,    
               "ccp": null,    
               "add": {    
                   "label": "add",    
                   "action": function (obj) {  
                       var inst = jQuery.jstree.reference(obj.reference);    
                       var clickedNode = inst.get_node(obj.reference);   
                       nodeCreate();
                   }    
               },    
               "delete": {    
                   "label": "delete",    
                   "action": function (obj) {  
                       var inst = jQuery.jstree.reference(obj.reference);    
                       var clickedNode = inst.get_node(obj.reference);   
                       nodeDelete();
                   }    
               },
               "update": {    
                   "label": "update",    
                   "action": function (obj) {  
                       nodeRename();  
                   }    
               }    
           }   
       }  
	});
	//JSTree 点击事件
	$('#jstree_resource').on("changed.jstree", function (e, data) {
		console.log(data.node.id);
		if(data.node!=null){
			$("#resourceNodeId_hidden").val(data.node.id);
			$('#resourceTableId').bootstrapTable('refresh');  
		}
	 });

//用户与资源组 -->
	function resourceGroupTableInit() {
		var getgroupURL = rootUri + "/manage/resGroupSearchWithCusId.json";
		var pageNumber = 1;
		$('#resourceGroupTableId').bootstrapTable({
			method: 'get',
		    url: getgroupURL, 
		    dataType: "json",
		    queryParams: resrouceGroupQueryParams,
		    pageSize: 10,
		    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
		    pageNumber: pageNumber,
		    pagination: true, //分页
		    singleSelect: false,
		    idField: "id",  //标识哪个字段为id主键
		    locale: "zh-CN", //表格汉化
		    sidePagination: "server", //服务端处理分页
	       	columns: [
				{
				    title: '用户组ID',
				      field: 'id',
				      align: 'center',
				      valign: 'middle'
				  }, 
	               {
	                 title: '用户组名称',
	                   field: 'name',
	                   align: 'center',
	                   valign: 'middle'
	               }, 
	               {
	                   title: '权限',
	                   field: 'cusResGrpRelModel',
	                   align: 'center',
	                   formatter:function (value, row, index) {
	               		   if(null != value ){
	                		   if((null == row.cusResGrpRelModel.startDate || new Date(row.cusResGrpRelModel.startDate) < new Date() )
		                			   &&  (null == row.cusResGrpRelModel.endDate || new Date(row.cusResGrpRelModel.endDate) > new Date() )){
	                			   return '<span class="label label-success">有权限</span>';
	                		   }else{
	                			   return '<span class="label label-danger">已过期</span>';
	                		   }
	                	   }else{
	                		   return '<span class="label label-danger">无权限</span>';
	                	   }
	                	   
	                    }
	               },
	               {
	                   title: '权限起始时间',
	                   field: 'cusResGrpRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	               		   if(null != value){
	                		   if(null == value.startDate){
	                			   return "无限制";
	                		   }else{
	                			   return new Date(value.startDate).format("yyyy-MM-dd HH:mm");
	                		   }
	                	   }else{
	                		   return "-";
	                	   }
	                 } 
	               },
	               {
	                   title: '权限截至时间',
	                   field: 'cusResGrpRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	               		   if(null != value){
	                		   if(null == value.endDate){
	                			   return "无限制";
	                		   }else{
	                			   return new Date(value.endDate).format("yyyy-MM-dd HH:mm"); 
	                		   }
	                	   }else{
	                		   return "-";
	                	   }
	                 }
	               },
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 90,
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
		                	   if(null != row.cusResGrpRelModel 
		                			   && (null == row.cusResGrpRelModel.startDate || new Date(row.cusResGrpRelModel.startDate) < new Date() ) 
		                			   &&  (null == row.cusResGrpRelModel.endDate || new Date(row.cusResGrpRelModel.endDate) > new Date() ) ){
		                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="removeResGroupPermission(\''+ row.id +'\', this)" data-toggle="modal"  data-loading-text="Loading...">禁用</button>';
		                	   }else{
		                		   var cusResGrpRelModel = null;
		                		   var startDate = null;
		                		   var endDate = null;
		                		   if(null != row.cusResGrpRelModel ){
		                			   if(null != row.cusResGrpRelModel.startDate){
		                				   startDate = new Date(row.cusResGrpRelModel.startDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                			   if(null != row.cusResGrpRelModel.endDate){
		                				   endDate = new Date(row.cusResGrpRelModel.endDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                		   }
		                		   var e ='<button type="button" class="btn btn-xs btn-success" data-toggle="modal" onclick="addResGroupPermPreProcess(\''+ row.id +'\',\''+ row.name +'\',\''+ startDate +'\',\''+ endDate +'\')" data-target="#addResGroupPermissionLayer">授权</button>';
		                	   }
		                    	return e;  
	                	   }
	                 } 
	               }
	           ],
			formatLoadingMessage: function () {
		    	return "请稍等，正在加载中...";
		  	}
	      });
	}
	  
	function resrouceGroupQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search:function(){
	    	 	var search = {};  
			    $.each($("#groupSearchForm").serializeArray(), function(i, field) {  
			        search[field.name] = field.value;  
			    });
			    search['specificCusId'] = $("#userId_hidden").val();  
			    return JSON.stringify(search);
	      }
	    };
	    return temp;
	  }
	
	$('#doGroupsearch').click(function() {  
		$('#resourceGroupTableId').bootstrapTable('refresh');
    }); 
	
   function addResGroupPermPreProcess(resGrpId, resGrpName, startDate, endDate){
	   $('#resGrpId_addGrpPer').val(resGrpId);
	   $('#reGrpName_addGrpPer').val(resGrpName);
	   $('#userId_addGrpPer').val($("#userId_hidden").val());
	   $('#userName_addGrpPer').val($("#userName_hidden").val());
	   if(null != startDate && startDate != 'null'){
		   $('#startDate_addGrpPer').val(startDate);
	   }
	   if(null != endDate && endDate != 'null'){
		   $('#endDate_addGrpPer').val(endDate);
	   }
   }
   
   
	//更新用户与资源组权限
	$('#saveButton_addGrpPer').click(function() {
		 //button失效，防止重复提交
		 //disabled="true"
		 $('#saveButton_addGrpPer').attr("disabled", true);

		 //关联用户组查询用户标识
		 var jointAuthFlag = null;
      //定义参数  
      var array = {};  
      //遍历form 组装json  
      $.each($("#addResGrpPermissionForm").serializeArray(), function(i, field) {  
          //可以添加提交验证  
          if('' == field.value){
          	array[field.name] = null;  
          }else{
          	array[field.name] = field.value;  
          }
      });  

      //参数转为json字符串，并赋给变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
      var modelJsonStr = JSON.stringify(array);
      
      var startDateStr =$("#startDate_addGrpPer").val();
      var endDateStr =$("#endDate_addGrpPer").val();
	    $.ajax({
		    url:rootUri + "/user/authCusResGrpPermission.json",
		    data:{   modelJsonStr : modelJsonStr, startDateStr : startDateStr, endDateStr : endDateStr },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');  
		    		$("#closeButton_addGrpPer").click();
		    	}else{
		    		alert("操作失败！");
		    	}
		    	$('#saveButton_addGrpPer').attr("disabled", false);
		     },  
		     error : function() {  
		          alert("系统异常！");  
		         $('#saveButton_addGrpPer').attr("disabled", false);
		     }  
		});
      
	 });  
	
	//禁用资源
	   function removeResGroupPermission(resGroupId, buttonObj){
		   if (!confirm('您确定取消授权吗？')) {
				return;
			}
		   $(buttonObj).button('loading');
			//.delay(1000).queue(function() {
			//	$(buttonObj).button('reset');
			//});
			
			
		   var userId = $("#userId_hidden").val();
		   $.ajax({
			    url:rootUri + "/user/disableResGroupPermission.json",
			    data:{   resGroupId : resGroupId, userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
			    		$('#resourceGroupTableId').bootstrapTable('refresh');  
			    	}else{
			    		alert("操作失败！");
			    	}
			    	$(buttonObj).button('reset');
			     },  
			     error : function() {  
			          alert("系统异常！");  
			          $(buttonObj).button('reset');
			     }  
			});
	   }
   
	//时间选择器
	$('.datetimepicker').datetimepicker({
		format: "yyyy-mm-dd hh:ii",
		language: 'zh-CN',
		autoclose:true,
		todayHighlight:true
	});

