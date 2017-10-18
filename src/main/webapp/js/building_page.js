var getUserURL = rootUri + "/admin/buildingSearch.json";
var pageNumber = 1;
$('#buildingListTableId')
		.bootstrapTable(
				{
					method : 'get',
					url : getUserURL,
					dataType : "json",
					queryParams : buildingQueryParams,
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
								title : '楼栋ID',
								field : 'id',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '楼栋名称',
								field : 'name',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
										var e = '<button class="btn btn-xs btn-warning" onclick="showbuilding(\''
												+ row.id
												+ '\',\''
												+ row.name
												+ '\')"><i class="icon-pencil"></i> </button>  ';
										var d = '<button class="btn btn-xs btn-danger" onclick="deleteBuildingById(\''
												+ row.id
												+ '\')"><i class="icon-remove"></i> </button>';
										return e + d;
								}
							} ],
					formatLoadingMessage : function() {
						return "请稍等，正在加载中...";
					}
				});

function buildingQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageNumber : params.pageNumber, // 页码
		limit : params.limit, // 页面行数大小
		offset : params.offset, // 分页偏移量
		sort : params.sort, // 排序列名
		sortOrder : params.order,// 排位命令（desc，asc）
		search : $("#buildingNameSearch").val()
	};
	return temp;
}

function newbuildingPre(){
	$("#buildingNameId").val("");
	$("#buildingId").val("");
}

function savebuilding(){
	$("#saveButtonId").button('loading');
	$.ajax({
		url : rootUri + "/admin/buildingSave.json",
		data : {
			name : $("#buildingNameId").val(),
			id : $("#buildingId").val(),
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$("#saveButtonId").button('reset');
			if (data.status == 1) {
				$('#buildingListTableId').bootstrapTable('refresh');
				alert("保存成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$("#saveButtonId").button('reset');
		}
	});
}
function showbuilding(id,name){
	$("#buildingNameId").val(name);
	$("#buildingId").val(id);
	$('#buildingModal').modal('show');
}

function deleteBuildingById(id){
	if(!confirm("是否确认删除")){
		return ;
	}
	$.ajax({
		url : rootUri + "/admin/buildingDelete.json",
		data : {
			id : id
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.status == 1) {
				$('#buildingListTableId').bootstrapTable('refresh');
				alert("删除成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("该单位有资源或角色正在使用，不能删除");
		}
	});
}

