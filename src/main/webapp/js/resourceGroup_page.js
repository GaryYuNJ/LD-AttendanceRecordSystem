
	var getgroupURL = rootUri + "/manage/resourceGroupSearch.json";
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
                   title: '创建时间',
                   field: 'createDate',
                   align: 'center',
                   formatter:function (value, row, index) {
                	   return new Date(value).format("yyyy-MM-dd"); 
                    }
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
                   formatter:function(value,row,index){
                	   var e = '<button class="btn btn-xs btn-warning" onclick="showResourceGroup('
							+ row.id
							+ ')"><i class="icon-pencil"></i> </button>  ';
					var d = '<button class="btn btn-xs btn-danger" onclick="deletegroupGroupById(\''
							+ row.id
							+ '\')"><i class="icon-remove"></i> </button>';
                    return e+d;  
                 } 
               }
           ],
		formatLoadingMessage: function () {
	    	return "请稍等，正在加载中...";
	  	}
      });
	  
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
			    return JSON.stringify(search);
	      }
	    };
	    return temp;
	  }
	
	$('#doGroupsearch').click(function() {  
		$('#resourceGroupTableId').bootstrapTable('refresh');
    }); 
	  //创建新的resourceGroup
	 function createResourceGroup(){
		var resourceGroupName =   $("#groupGroupNameId").val();
		if(null == resourceGroupName || resourceGroupName == ""){
			alert("资源组名称不能为空");
		}
		$.ajax( {  
		    url:rootUri + "/manage/addResourceGroup.json",
		    data:{"resourceGroupName" : resourceGroupName },  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');
		    		$("#closeCreateGroupWindow").click();
		    		$("#groupGroupNameId").val("");
		    	}else{
		    		alert("已存在同名用户组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名用户组！");
		     }
		});
	  };
	  
	  //删除resGroup 
		 function deletegroupGroupById(resGroupId){
			if(null == resGroupId || resGroupId == ""){
				alert("用户组ID为空");
				return;
			}
			if (!confirm('您确定要删除选中的资源组吗？')) {
				return;
			}
			$.ajax( {  
			    url:rootUri + "/manage/deleteResGroupById.json",
			    data:{   resGroupId : resGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
			    		$('#resourceGroupTableId').bootstrapTable('refresh');
			    	}else{
			    		alert("操作失败！");
			    	}
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
		  };
		  
		  
      //显示用户详情内容
	  var showResourceGroup = function (resourceGroupId) {
		  $("#icon_group_list1").click();
		  $('#myTab a:first').click();
		  if($("#icon_group_list2 i.icon-chevron-down").length>0){
			  console.log("1");
			  $("#icon_group_list2").click();
			}
		  initgroupGroupDetailForm(resourceGroupId);
	  };

	  
	  //初始化 groupGroupDetailForm 
	  var initgroupGroupDetailForm = function (resourceGroupId) {
		  $.ajax( {  
			    url:rootUri + "/manage/showResourceGroup.json",
			    data:{resourceGroupId : resourceGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$("#resourceGroupId_hidden").val(data.id);
			    	$("#resourceGroupId_InForm").val(data.id);
			    	$("#resourceGroupName_InForm").val(data.name);
			    	$("#resourceGroupCreateTime_InForm").val(data.createDate);
			    	$("#resourceGroupCreategroup_InForm").val(data.createUser);
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
	  };
	  
	  
	//更新 资源组
	 $('#updateResourceGroup_Button').click(function() {
		 var resourceGroupId = $("#resourceGroupId_InForm").val();
		 var resourceGroupName = $("#resourceGroupName_InForm").val();
		 if(null ==  resourceGroupName || '' == resourceGroupName){
			 alert("资源组名称不能为空");
			 return;
		 }
		 $.ajax({
		    url:rootUri + "/manage/updateResourceGroup.json",
		    data:{resourceGroupId : resourceGroupId, resourceGroupName : resourceGroupName},  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');
		    		alert("保存成功");
		    	}else{
		    		alert("已存在同名资源组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名资源组！");
		     }
		});
	 });
	
	 //tab 切换
	 resourceTableInit();
    $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
          //点击tab调用对应function
          if($(this).attr("href") == "#resourceGroupResource"){
        	  $("#resourceTableId").bootstrapTable('refresh');
          } 
        })
      })
	function resourceTableInit() {
		$('#resourceTableId').bootstrapTable({
			method: 'get',
		    url: rootUri + "/manage/resourceSearchWithGroup.json", 
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
	                   title: '状态',
	                   field: 'resourceGroupId',
	                   align: 'center',
	                   valign: 'middle',
	                   formatter:function(value,row,index){
	                	   if(typeof(value) != "undefined"){
	                		   return '<span class="label label-success">已加入</span>';
	                	 }
		               } 
	               }, 
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 90,
	                   formatter:function(value,row,index){
	                	   if(typeof(row.resourceGroupId) != "undefined"){
	                		   return '<button type="button" class="btn btn-xs btn-warning"  onclick="deleteResourceGroupRel(this, \''+ row.id +'\')" data-toggle="modal" data-loading-text="Loading...">移除</button>';
	                		   //return '<button type="button" class="btn btn-warning" onclick="deleteResourceGroupRel('+value+')"><i class="icon-remove"></i> 移除</button>';
	                	 }else{
	                		 return '<button type="button" class="btn btn-xs btn-success"  onclick="addResourceGroupRel(this, \''+ row.id + '\')" data-toggle="modal" data-loading-text="Loading...">加入</button>';
	                		 //return '<button type="button" class="btn btn-primary" onclick="addResourceGroupRel('+value+')"><i class="icon-plus"></i> 加入</button>';;
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
        var search = {};  
        $.each($("#resourceSearchform").serializeArray(), function(i, field) {  
        	if(field.name=="ifBindGroup"&&field.value=="on"){
        		search["ifBindGroup"] = "Y";
        	}else{
        		search[field.name] = field.value;
        	}
        });  
        params.search = JSON.stringify(search);
        return params;  
    }  
	
	//自定义resource查询
	 $('#doSearchResource').click(function() {
	        //var params = $('#resourceTableId').bootstrapTable('getOptions');  
	        $("#resourceNodeId_hidden").val(null);
	        $("#resourceTableId").bootstrapTable('destroy');
	        resourceTableInit();
	        //$('#resourceTableId').bootstrapTable('refresh');   //直接用 refresh会有页码数缓存问题
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
	
   function addResourceGroupRel(buttonObj, resourceId){
	   $(buttonObj).button('loading');
	  // $("#resourceGroupId_hidden").val();
	   $.ajax({
		    url:rootUri + "/manage/addResourceGroupRel.json",
		    data:{resourceGroupId : $("#resourceGroupId_hidden").val(), resourceId : resourceId},  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	$(buttonObj).button('reset');
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');
		    		//alert("保存成功");
		    	}else{
		    		alert("系统异常！");
		    	}
		     },  
		     error : function() {  
		    	 alert("系统异常！");
		    	 $(buttonObj).button('reset');
		     }
		});
	   
   }
   function deleteResourceGroupRel(buttonObj, resourceId){
	   $(buttonObj).button('loading');
	  // $("#resourceGroupId_hidden").val();
	   $.ajax({
		    url:rootUri + "/manage/deleteResourceGroupRel.json",
		    data:{resourceGroupId : $("#resourceGroupId_hidden").val(), resourceId : resourceId},  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	$(buttonObj).button('reset');
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');
		    		//alert("保存成功");
		    	}else{
		    		alert("系统异常！");
		    	}
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
			"search","types", "wholerow"
		]
	});
	//JSTree 点击事件
	$('#jstree_resource').on("changed.jstree", function (e, data) {
		//console.log(data.node.id);
		if(data.node!=null){
			$("#resourceNodeId_hidden").val(data.node.id);
			$('#resourceTableId').bootstrapTable('refresh');  
		}
	 });