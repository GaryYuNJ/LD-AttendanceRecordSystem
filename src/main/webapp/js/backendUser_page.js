var getUserURL = rootUri + "/admin/backendUserSearch.json";
var pageNumber = 1;
$('#userListTableId')
		.bootstrapTable(
				{
					method : 'get',
					url : getUserURL,
					dataType : "json",
					queryParams : userQueryParams,
					pageSize : 10,
					pageList : [ 10, 25, 50 ], // 可供选择的每页的行数（*）
					pageNumber : pageNumber,
					pagination : true, // 分页
					singleSelect : false,
					idField : "id", // 标识哪个字段为id主键
					// showColumns: true, //显示隐藏列
					// showRefresh: true, //显示刷新按钮
					locale : "zh-CN", // 表格汉化
					// search: true, //显示搜索框
					sidePagination : "server", // 服务端处理分页
					columns : [
							{
								title : '用户ID',
								field : 'id',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '用户名',
								field : 'name',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '角色名',
								field : 'userRole.name',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '状态',
								field : 'status',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									if (value == "Y") {
										return '<span class="label label-success">可用</span>';
									} else {
										return '<span class="label label-danger">不可用</span>';
									}
								}
							},
							{
								title : '创建时间',
								field : 'createDate',
								align : 'center',
								formatter : function(value, row, index) {
									return new Date(value).format("yyyy-MM-dd");
								}
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var e = '<button class="btn btn-xs btn-warning" data-toggle="modal" data-target="#userUpdateModal" onclick="showUser(\''
											+ row.id
											+ '\',\''+row.name+'\',\''+row.status+'\',\''+row.roleId+'\')"><i class="icon-pencil"></i> </button>  ';
									if(row.id!="1000"){
										var d = '<button class="btn btn-xs btn-danger" onclick="deleteUserById(\''
												+ row.id
												+ '\')"><i class="icon-remove"></i> </button>';
									}else{
										var d="";
									}
									return e + d;
								}
							} ],
					formatLoadingMessage : function() {
						return "请稍等，正在加载中...";
					}
				});

function userQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageNumber : params.pageNumber, // 页码
		limit : params.limit, // 页面行数大小
		offset : params.offset, // 分页偏移量
		sort : params.sort, // 排序列名
		sortOrder : params.order,// 排位命令（desc，asc）
		search : $("#userNameSearch").val()
	};
	return temp;
}


//角色信息
var roles;
$.get(rootUri + "/admin/allRole.json", function(data, status) {
	if (status = 4) {
		$.each(data, function(n, value) {
			$("#updateUserRoleId").append(
					"<option value='" + value.id + "'>" + value.name
							+ "</option>");
			$("#userRoleId").append(
					"<option value='" + value.id + "'>" + value.name
							+ "</option>");
			roles = data;
		});
	}
	$('#userRoleId').selectpicker('refresh');
	$('#updateUserRoleId').selectpicker('refresh');
});

function newUserPre(){
	$("#nameId").val("");
	$("#userStatusId").bootstrapSwitch('setState', true);
	$("#saveButtonId").button('reset');
}

function saveUser(){
	if(null==$("#nameId").val()||$("#nameId").val()==""){
		alert("用户名不能为空！");
		return;
	}
	var status="Y";
	if(!$("#statusId").prop('checked')){
		status="N";
	}
	$("#saveButtonId").button('loading');
	$.ajax({
		url : rootUri + "/admin/saveBackEndUser.json",
		data : {
			name : $("#nameId").val(),
			status :status,
			roleId :$('#userRoleId').val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$("#saveButtonId").button('reset');
			if (data.status == 1) {
				$('#userListTableId').bootstrapTable('refresh');
				alert("保存成功");
			} else if(data.status==2){
				alert("该用户已存在");
			}else{
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$("#saveButtonId").button('reset');
		}
	});
}
function showUser(userId,name,status,roleId){
	$("#UpdateNameId").val(name);
	$("#backUserId").val(userId);
	if(userId=="Y"){
		$("#userStatusId").bootstrapSwitch('setState', true);
	}else{
		$("#userStatusId").bootstrapSwitch('setState', false);
	}
	$('#updateUserRoleId').selectpicker('val', roleId);
	//$('#roleListTableId').bootstrapTable('refresh');
}

function updateUser(){
	$("#updateButtonId").button('loading');
	var status="Y";
	if(!$("#updateStatusId").prop('checked')){
		status="N";
	}
	$.ajax({
		url : rootUri + "/admin/updateEndUser.json",
		data : {
			id : $("#backUserId").val(),
			status :status,
			roleId:$("#updateUserRoleId").val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$("#updateButtonId").button('reset');
			if (data.status == 1) {
				$('#userListTableId').bootstrapTable('refresh');
				alert("保存成功");
			} else if(data.status==2){
				alert("该用户不存在");
			}else{
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$("#updateButtonId").button('reset');
		}
	});
}

function deleteUserById(userId){
	if(!confirm("是否确认删除")){
		return ;
	}
	$.ajax({
		url : rootUri + "/admin/delBackUser.json",
		data : {
			bUserId : userId
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.status == 1) {
				$('#userListTableId').bootstrapTable('refresh');
				alert("删除成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
		}
	});
}
/*
var rolePageNumber = 1;
$('#roleListTableId')
		.bootstrapTable(
				{
					method : 'get',
					url : rootUri + "/admin/roleSearchWithUser.json",
					dataType : "json",
					queryParams : roleQueryParams,
					pageSize : 10,
					pageList : [ 10, 25, 50 ], // 可供选择的每页的行数（*）
					pageNumber : rolePageNumber,
					pagination : true, // 分页
					singleSelect : false,
					idField : "id", // 标识哪个字段为id主键
					// showColumns: true, //显示隐藏列
					// showRefresh: true, //显示刷新按钮
					locale : "zh-CN", // 表格汉化
					// search: true, //显示搜索框
					sidePagination : "server", // 服务端处理分页
					columns : [
							{
								title : '角色ID',
								field : 'id',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '角色名称',
								field : 'name',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '状态',
								field : 'status',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									if (value == "Y") {
										return '<span class="label label-success">已授权</span>';
									} else {
										return '<span class="label label-danger">未授权</span>';
									}
								}
							},
							{
								title : '创建时间',
								field : 'createDate',
								align : 'center',
								formatter : function(value, row, index) {
									return new Date(value).format("yyyy-MM-dd");
								}
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var e ="";
									if(row.status=="Y"){					
										e = '<button type="button" class="btn btn-xs btn-warning" onclick="removePermission(\''+row.id+'\',this)">禁用</button>';
									}else{
										e = '<button type="button" class="btn btn-xs btn-success" onclick="addPermission(\''+row.id+'\',this)">授权</button>';
									}
									return e;
								}
							} ],
					formatLoadingMessage : function() {
						return "请稍等，正在加载中...";
					}
				});

function roleQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageNumber : params.pageNumber, // 页码
		limit : params.limit, // 页面行数大小
		offset : params.offset, // 分页偏移量
		sort : params.sort, // 排序列名
		sortOrder : params.order,// 排位命令（desc，asc）
		search : $("#roleNameSearch").val(),
		userId : $("#roleSearchUserId").val()
	};
	return temp;
}
function addPermission(rowId,obj){
	if(null==$("#roleSearchUserId").val()||null==rowId){
		alert("用户不能为空！")
		return;
	}
	$(obj).button('loading');
	
	$.ajax({
		url : rootUri + "/admin/addUserRole.json",
		data : {
			bUserId : $("#roleSearchUserId").val(),
			roleId :rowId
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$(obj).button('reset');
			if (data.status == 1) {
				$('#roleListTableId').bootstrapTable('refresh');
				alert("保存成功");
			}else{
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$(obj).button('reset');
		}
	});
	
	
}

function removePermission(rowId,obj){
	if(null==$("#roleSearchUserId").val()||null==rowId){
		alert("用户不能为空！")
		return;
	}
	$(obj).button('loading');
	
	$.ajax({
		url : rootUri + "/admin/delUserRole.json",
		data : {
			bUserId : $("#roleSearchUserId").val(),
			roleId :rowId
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$(obj).button('reset');
			if (data.status == 1) {
				$('#roleListTableId').bootstrapTable('refresh');
				alert("保存成功");
			}else{
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$(obj).button('reset');
		}
	});
}*/
