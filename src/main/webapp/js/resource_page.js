function deleteKey(obj) {
	$(obj).parent().parent().remove();
}
function validateKey() {
	var passwordtemp=$("#ResourceKeyFormId").find('[name="password"]');
	var mactemp=$("#ResourceKeyFormId").find('[name="mac"]');
	$('#resourceFormId').bootstrapValidator("addField",passwordtemp,{
		validators : {
			notEmpty : {
				message : '密码不能为空'
			}
		}
	});
	$('#resourceFormId').bootstrapValidator("addField",mactemp,{
		validators : {
			notEmpty : {
				message : '密码不能为空'
			}
		}
	});
}
// 添加新钥匙
function addResourceKey(objForm) {
	// console.log(objForm);
	$(objForm)
			.append(
					'<div class="form-group resourceKeyForm">'
							+ '<label class="col-lg-1 control-label">钥匙信息</label>'
							+ '<div class="col-lg-2">'
							+ '<select class="form-control keyName" name="manufacturerId">'
							+ ' <option value="1">特斯连</option>'
							+ '<option value="2">立方</option>'
							+ ' </select>'
							+ '</div>'
							+ '<div class="col-lg-2">'
							+ '<input type="text" class="form-control keyName" placeholder="设备Mac地址" name="mac">'
							+ '</div>'
							+ '<div class="col-lg-3">'
							+ '<input type="text" class="form-control keyName" placeholder="密码" name="password">'
							+ ' </div>'
							+ '<div class="col-lg-2">'
							+ '<input type="text" class="form-control keyName" placeholder="优先级" name="rInt2">'
							+ ' </div>'
							+ '<div class="col-lg-1">'
							+ '<button type="button" class="btn btn-danger" onclick="deleteKey(this)"><i class="icon-remove"></i>删除</button>'
							+ '</div>' + ' </div>');
}

$("#updateResourceKeyButtonId").click(function() {
	addResourceKey($("#ResourceKeyFormId"))
});
// 重命名节点触发
$('#jstree_resource').on(
		"rename_node.jstree",
		function(e, node) {
			// console.log(node);
			// 新建节点
			if (node.node.id.indexOf("j") == 0) {
				var nodeModel = {
					name : node.text,
					parentId : node.node.parent
				};
				$.post(rootUri + "/manage/addorUpdateNode.json", nodeModel,
						function(data) {
							if (data.status == "0") {
								node.instance.set_id(node.node, data.message);
							} else {
								node.instance.refresh();
							}
						});
			} else {
				if (node.text != node.old) {
					var nodeModel = {
						id : node.node.id,
						name : node.text,
						parentId : node.node.parent
					};
					// 更新节点
					$.post(rootUri + "/manage/updateNode.json", nodeModel,
							function(data) {
								if (data.status == "1") {
									node.instance.refresh();
								}
							});
				}
			}
			// console.log(node);
		});

// 删除节点触发方法
$('#jstree_resource').on("delete_node.jstree", function(e, node) {
	if (node.node.children.length > 0) {
		alert("请先删除子节点");
		node.instance.refresh();
		return false;
	}
	$.ajax({
		type : "POST",
		url : rootUri + "/manage/deleteNode.json",
		data : {
			nodeId : node.node.id
		},
		dataType : "json",
		success : function(data) {
			if (data.status == "1") {
				node.instance.refresh();
			}
		},
		error : function(e) {
			alert("该节点已包含资源，不能删除");
		}
	});
});

// 创建节点
function nodeCreate() {
	var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
	if (!sel.length) {
		return false;
	}
	sel = sel[0];
	sel = ref.create_node(sel);
	if (sel) {
		ref.edit(sel);
	}
};

// 重命名节点
function nodeRename() {
	var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
	if (!sel.length) {
		return false;
	}
	sel = sel[0];
	ref.edit(sel);
};

// 删除节点
function nodeDelete() {
	var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
	if (!sel.length) {
		return false;
	}
	ref.delete_node(sel);
};

// 初始化FORM
function initResourceForm() {
	$("#updateResourceName").val("");
	$("#updateResourceMac").val("");
	$("#updateResourceBuilding").val("");
	$("#updateResourceFloor").val("");
	$("#updateResourceDeviceType").val("");
	$("#updateResourcePermissionAttrId").val("1");
	$("#updateResourceManufacturerId").val("");
	// $("#updateResourceShareEnable").val("");
	// $("#updateResourceStatus").val("");
	$("#updateResourceSequenceId").val("");
	$("#updateResourceId").val("");
	$("#updateResourceNodeId").val("");
	$("#updateResourceNodePath").val("");
	$('#jstree_updateResource').jstree(true).deselect_all();
	$("#ResourceKeyFormId")
			.html(
					'<div class="form-group resourceKeyForm">'
							+ '<label class="col-lg-2 control-label">钥匙信息</label>'
							+ '<div class="col-lg-2">'
							+ '<select class="form-control keyName" name="manufacturerId">'
							+ '<option value="1">特斯连</option>'
							+ '<option value="2">立方</option>' + '</select>'
							+ '</div>' + '<div class="col-lg-2">'
							+ '<input type="text" class="form-control keyName"'
							+ 'placeholder="设备Mac地址" name="mac">' + '</div>'
							+ '<div class="col-lg-3">'
							+ '<input type="text" class="form-control keyName"'
							+ 'placeholder="密码" name="password">' + '</div>'
							+ '<div class="col-lg-2">'
							+ '<input type="text" class="form-control keyName" placeholder="优先级" name="rInt2">'
							+ ' </div>'
							+ '<div class="col-lg-1">'
							+ '<button type="button" class="btn btn-danger" onclick="deleteKey(this)"><i class="icon-remove"></i>删除</button>'
							+ '</div>' + ' </div>');
}
// 编辑资源
function resourceEditPre(row) {
	$
			.post(
					rootUri + "/manage/getResourceById.json",
					{
						"id" : row
					},
					function(data) {
						$("#updateResourceName").val(data.name);
						$("#updateResourceMac").val(data.mac);
						$("#updateResourceBuilding").val(data.buildingId);
						$("#updateResourceFloor").val(data.floor);
						$("#updateResourceDeviceType").val(data.deviceType);
						$("#updateResourcePermissionAttrId").val(
								data.permissionAttrId);
						$("#updateResourceManufacturerId").val(
								data.manufacturerId);

						// $("#updateResourceStatus").val(data.status);
						if ('N' == data.status) {
							$('#updateResourceStatus').bootstrapSwitch(
									'setState', false);
						} else {
							$('#updateResourceStatus').bootstrapSwitch(
									'setState', true);
						}
						// $("#updateResourceShareEnable").val(data.shareEnable);
						if ('N' == data.shareEnable) {
							$('#updateResourceShareEnable').bootstrapSwitch(
									'setState', false);
						} else {
							$('#updateResourceShareEnable').bootstrapSwitch(
									'setState', true);
						}

						$("#updateResourceSequenceId").val(data.sequence);
						$("#updateResourceId").val(data.id);
						$("#ResourceKeyFormId").html("");
						$("#updateResourceNodeId").val(data.nodeId);
						$("#updateResourceNodePath").val(data.nodePath);
						$('#jstree_updateResource').jstree(true).deselect_all();
						$('#jstree_updateResource').jstree(true).select_node(
								data.nodeId);
						$
								.each(
										data.resourceKeys,
										function(n, value) {
											var tempHtml = '<div class="form-group resourceKeyForm">'
													+ '<label class="col-lg-3 control-label">钥匙信息</label>'
													+ '<div class="col-lg-2">'
													+ '<select class="form-control keyName" name="manufacturerId">';
											if (value.manufacturerId == 1) {
												tempHtml += '<option value="1" selected="true">特斯连</option>';
												tempHtml += '<option value="2">立方</option>';
											} else {
												tempHtml += '<option value="1">特斯连</option>';
												tempHtml += '<option value="2"  selected="true">立方</option>';
											}

											tempHtml += '</select>'
													+ '</div>'
													+ '<div class="col-lg-2">'
													+ '<input type="text" class="form-control keyName" placeholder="设备Mac地址" name="mac" value="'
													+ value.mac
													+ '">'
													+ '</div>'
													+ '<div class="col-lg-3">'
													+ '<input type="text" class="form-control keyName" placeholder="密码" name="password" value="'
													+ value.password + '">'
													+ ' </div>'
													+ '<div class="col-lg-2">'
													+ '<input type="text" class="form-control keyName" placeholder="优先级" name="rInt2" value="'
													+ value.rInt2 + '">'
													+ ' </div>'
													+ '<div class="col-lg-1">';
											tempHtml += '<button type="button" class="btn btn-danger" onclick="deleteKey(this)"><i class="icon-remove"></i>删除</button>';
											tempHtml += '</div>'
													+ '<input type="hidden" class="keyName" value="'
													+ value.id
													+ '" name="id"></div>';
											$("#ResourceKeyFormId").append(
													tempHtml);
										});
					});
	
}
// 添加资源
function addResource() {
	validateKey();
	$("#updateResourceButtonId").attr('disabled', "true");
	$('#resourceFormId').bootstrapValidator('validate');
	$("#ResourceKeyFormId").bootstrapValidator('validate');
	if ($('#resourceFormId').data('bootstrapValidator').isValid()
			&& $('#ResourceKeyFormId').data('bootstrapValidator').isValid()) {
		var resourceModel = {};
		$.each($("#resourceFormId").serializeArray(), function(i, field) {
			resourceModel[field.name] = field.value;
		});
		var resourcekeys = new Array();
		$.each($("#ResourceKeyFormId .resourceKeyForm"), function(i) {
			var resourceKey = {};
			$.each($($("#ResourceKeyFormId .resourceKeyForm")[i]).find(
					".keyName").serializeArray(), function(i, field) {
				resourceKey[field.name] = field.value;
			});
			resourcekeys[i] = resourceKey;
		});
		resourceModel["resourceKeys"] = resourcekeys;
		var data = {
			"resourceModelJson" : JSON.stringify(resourceModel)
		}
		$.post(rootUri + "/manage/addResource.json", data,
				function(data, state) {
					console.log(state);
					if (0 == data.status) {
						resourceEditPre(data.message);
						alert("保存成功！");
					} else if(null != data.message && '' != data.message){
						alert(data.message);
					}else {
						alert("后台异常！");
					}
				});
	}
	$("#updateResourceButtonId").removeAttr('disabled');
}
// 更新资源数据
function updateResource() {
	validateKey();
	$("#updateResourceButtonId").attr('disabled', "true");
	$('#resourceFormId').bootstrapValidator('validate');
	$("#ResourceKeyFormId").bootstrapValidator('validate');
	if ($('#resourceFormId').data('bootstrapValidator').isValid()
			&& $('#ResourceKeyFormId').data('bootstrapValidator').isValid()) {
		var resourceModel = {};
		$.each($("#resourceFormId").serializeArray(), function(i, field) {
			resourceModel[field.name] = field.value;
		});
		var resourcekeys = new Array();
		$.each($("#ResourceKeyFormId .resourceKeyForm"), function(i) {
			var resourceKey = {};
			$.each($($("#ResourceKeyFormId .resourceKeyForm")[i]).find(
					".keyName").serializeArray(), function(i, field) {
				resourceKey[field.name] = field.value;
			});
			resourcekeys[i] = resourceKey;
		});
		resourceModel["resourceKeys"] = resourcekeys;
		var data = {
			"resourceModelJson" : JSON.stringify(resourceModel)
		}
		$.post(rootUri + "/manage/updateResource.json", data, function(data) {
			if (0 == data.status) {
				alert("保存成功！");
			} else {
				alert("后台异常！");
			}
		});

	}
	$("#updateResourceButtonId").removeAttr('disabled');
}

$('#updateResourceButtonId').click(
		function() {
			if ($("#updateResourceId").val() != null
					&& $("#updateResourceId").val() != "") {
				updateResource();
			} else {
				addResource();
			}
		});

// 查询刷新
function refreshSearch() {
	var params = $('#resourceTableId').bootstrapTable('getOptions')
	params.queryParams = function(params) {
		// 定义参数
		var search = {};
		// 遍历form 组装json
		$.each($("#searchform").serializeArray(), function(i, field) {
			// console.info(field.name + ":" + field.value + " ");
			// 可以添加提交验证
			search[field.name] = field.value;
		});
		// 参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件
		params.search = JSON.stringify(search);
		return params;
	}
	console.info(params);
	$('#resourceTableId').bootstrapTable('refresh', params)
}

// 删除
function resourceDel(resourceId) {
	if (!confirm('您确定要删除选中的资源吗？')) {
		return;
	}
	var data = {
		"resourceId" : resourceId
	};
	$.post(rootUri + "/manage/delResource.json", data, function(data) {
		if (data.status == "0") {
			refreshSearch();
		}
	});
}

// 给楼栋赋值Ajax
var buildings;
$.get(rootUri + "/manage/allBuildings.json", function(data, status) {
	if (status = 4) {
		$.each(data, function(n, value) {
			$("#buildingId").append(
					"<option value='" + value.id + "'>" + value.name
							+ "</option>");
			$("#updateResourceBuilding").append(
					"<option value='" + value.id + "'>" + value.name
							+ "</option>");
			buildings = data;
		});
	}
});
function findBuildName(id) {
	var tempvalue;
	$.each(buildings, function(n, value) {
		if (id == value.id) {
			tempvalue = value.name;
			return false;
		}
	});
	return tempvalue;
}
$('#jstree_resource').jstree({
	"core" : {
		"multiple" : false,
		"animation" : 0,
		"check_callback" : true,
		"themes" : {
			"stripes" : true
		},
		'data' : {
			'url' : rootUri + "/manage/showNode.json",
			'data' : function(node) {
			}
		}
	},
	"types" : {
		"#" : {
			"max_children" : 1,
			"max_depth" : 6,
			"valid_children" : [ "root" ]
		},
		"root" : {
			"icon" : rootUri + "/js/themes/default/tree_icon.png",
			"valid_children" : [ "default" ]
		},
		"default" : {
			"valid_children" : [ "default", "file" ]
		},
		"file" : {
			"icon" : "glyphicon glyphicon-file",
			"valid_children" : []
		}
	},
	"plugins" : [ "contextmenu", "search", "types", "wholerow" ],
	"contextmenu" : {
		"items" : {
			"create" : null,
			"rename" : null,
			"remove" : null,
			"ccp" : null,
			"add" : {
				"label" : "add",
				"action" : function(obj) {
					var inst = jQuery.jstree.reference(obj.reference);
					var clickedNode = inst.get_node(obj.reference);
					nodeCreate();
				}
			},
			"delete" : {
				"label" : "delete",
				"action" : function(obj) {
					var inst = jQuery.jstree.reference(obj.reference);
					var clickedNode = inst.get_node(obj.reference);
					nodeDelete();
				}
			},
			"update" : {
				"label" : "update",
				"action" : function(obj) {
					nodeRename();
				}
			}
		}
	}
});
// JSTree 事件
$('#jstree_resource').on("changed.jstree", function(e, data) {
	$("#queryNodeId").val(data.node.id);
	$("#queryNodeId_html").text(data.node.id);
	
	refreshSearch();
});
// 分页
var pageNumber = 1;
$('#resourceTableId')
		.bootstrapTable(
				{
					method : 'get',
					url : rootUri + "/manage/resourceSearch.json",
					dataType : "json",
					pageSize : 10,
					pageList : [ 10, 25, 50 ], // 可供选择的每页的行数（*）
					pageNumber : pageNumber,
					pagination : true, // 分页
					singleSelect : false,
					striped : true,
					idField : "id", // 标识哪个字段为id主键
					sidePagination : "server", // 服务端处理分页
					columns : [
							{
								title : '名称',
								field : 'name',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '类型',
								field : 'permissionAttrId',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									if (value == "1")
										return "公共资源";
									if (value == "2")
										return "基础资源";
									if (value == "3")
										return "私有资源";
								}
							},
							{
								title : '楼栋',
								field : 'buildingId',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									return findBuildName(value);
								}
							},
			               {
			            	   title: '节点',
			                   field: 'nodePath',
			                   align: 'center',
			                   valign: 'middle'
			               },
							{
								title : '状态',
								field : 'status',
								align : 'center',
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
									var e = '<button class="btn btn-xs btn-warning" onclick="showResourceGroup('
											+ value
											+ ')"><i class="icon-pencil"></i> </button>  ';
									var d = '<button class="btn btn-xs btn-danger" onclick="resourceDel(\''
											+ row.id
											+ '\')"><i class="icon-remove"></i> </button>';
									return e + d;
								}
							} ],
					formatLoadingMessage : function() {
						return "请稍等，正在加载中...";
					}
				});
// 自定义查询
$('#dosearch').click(function() {
	$("#queryNodeId").val("");
	refreshSearch();
});

// FORM输入验证
$('#resourceFormId').bootstrapValidator({
	message : '输入值错误',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		name : {
			validators : {
				notEmpty : {
					message : '资源名称不能为空'
				}
			}
		},
		floor : {
			validators : {
				stringLength : {
					min : 0,
					max : 10,
					message : '不能超过10位'
				},
				integer : {
					message : '楼层只能输入数字'
				}
			}
		},
		sequence : {
			validators : {
				stringLength : {
					min : 0,
					max : 10,
					message : '不能超过10位'
				},
				integer : {
					message : '排序只能使用数字'
				}
			}
		}
	}
});

// 文件选择器
$(document)
		.on(
				'ready',
				function() {

					$("#input-folder-1")
							.fileinput(
									{
										language : 'zh', // 设置语言,
										uploadUrl : "uploadFile",
										uploadAsync : true,
										// browseLabel: 'Select Folder...',
										allowedFileExtensions : [ 'xls', 'xlsx' ],// 接收的文件后缀
										showUpload : true, // 是否显示上传按钮
										showCaption : false,// 是否显示标题
										showPreview : true,
										browseClass : "btn btn-primary", // 按钮样式
										previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
										// elErrorContainer: "#errorBlock",
										maxFilePreviewSize : 10240,
										uploadExtraData : function() {
											// return "";
											/*
											 * var extraValue = null; var radios =
											 * document.getElementsByName('excelType');
											 * for(var i=0;i<radios.length;i++){
											 * if(radios[i].checked){ extraValue =
											 * radios[i].value; } } return
											 * {"excelType": extraValue};
											 */

											return {
												"nodeId" : "123123"
											};
										}
									});
					// 上传失败
					$('#input-folder-1').on('fileuploaderror',
							function(event, data, previewId, index) {
								alert("文件上传失败！");
								// var form = data.form, files = data.files,
								// extra = data.extra,
								// response = data.response, reader =
								// data.reader;
								// console.log(data);
								// console.log('File upload error');
							});
					// 上传失败
					$('#input-folder-1').on('fileerror', function(event, data) {
						alert("文件解析失败！");
						// console.log(data.id);
						// console.log(data.index);
						// console.log(data.file);
						// console.log(data.reader);
						// console.log(data.files);
					});
					// 上传成功返回监听
					$('#input-folder-1')
							.on(
									'fileuploaded',
									function(event, data, previewId, index) {
										var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
										if (response.status != 0) {
											alert(response.message);
										} else {
											alert("资源导入成功！");
											$("#uploadModalClose").click();
										}
										// console.log('File uploaded
										// triggered');
									});
				});
resourceTableInit();
$(function() {
	$('#myTab a:first').tab('show');// 初始化显示哪个tab
	$('#myTab a').click(function(e) {
		e.preventDefault();// 阻止a链接的跳转行为
		$(this).tab('show');// 显示当前选中的链接及关联的content
		// 点击tab调用对应function
		if ($(this).attr("href") == "#resourceGroupResource") {

			$("#resourceGroupTableId").bootstrapTable('refresh');
		}
	})
})

// 显示用户详情内容
var showResourceGroup = function(resourceId) {
	$("#icon_group_list1").click();
	$('#myTab a:first').click();
	if ($("#icon_group_list2 i.icon-chevron-down").length > 0) {
		// console.log("1");
		$("#icon_group_list2").click();
	}
	if (null != resourceId && "" != resourceId) {
		resourceEditPre(resourceId);
	} else {
		initResourceForm();
	}
	// initgroupGroupDetailForm(resourceGroupId);
};

$("#addResourceButton").click(function() {
	showResourceGroup();
});
$('#jstree_updateResource').jstree({
	"core" : {
		"multiple" : false,
		"animation" : 0,
		"check_callback" : true,
		"themes" : {
			"stripes" : true
		},
		'data' : {
			'url' : rootUri + "/manage/showNode.json",
			'data' : function(node) {
			}
		}
	},
	"types" : {
		"#" : {
			"max_children" : 1,
			"max_depth" : 6,
			"valid_children" : [ "root" ]
		},
		"root" : {
			"icon" : rootUri + "/js/themes/default/tree_icon.png",
			"valid_children" : [ "default" ]
		},
		"default" : {
			"valid_children" : [ "default", "file" ]
		},
		"file" : {
			"icon" : "glyphicon glyphicon-file",
			"valid_children" : []
		}
	},
	"plugins" : [ "search", "types", "wholerow" ]
});

$('#jstree_updateResource').on(
		"changed.jstree",
		function(e, data) {
			// console.log(data.node.id);
			if (data.node != null) {
				$("#updateResourceNodeId").val(data.node.id);
				$("#updateResourceNodePath").val(
						data.instance.get_path(data.node, '/'));
				//$("#updateResourceNodePath").val(data.node.text);
				// console.log($("#newnodeId").val());
				// console.log($("#newnodePath").val());
			}
		});
// 资源与资源组
function resourceTableInit() {
	$('#resourceGroupTableId')
			.bootstrapTable(
					{
						method : 'get',
						url : rootUri + "/manage/resGroupSearchWithRes.json",
						dataType : "json",
						queryParams : resourceQueryParams,
						pageSize : 10,
						pageList : [ 10, 25, 50 ], // 可供选择的每页的行数（*）
						pageNumber : 1,
						pagination : true, // 分页
						singleSelect : false,
						striped : true,
						idField : "id", // 标识哪个字段为id主键
						sidePagination : "server", // 服务端处理分页
						columns : [
								{
									title : '用户组ID',
									field : 'id',
									align : 'center',
									valign : 'middle'
								},
								{
									title : '用户组名称',
									field : 'name',
									align : 'center',
									valign : 'middle'
								},
								{
									title : '创建时间',
									field : 'createDate',
									align : 'center',
									formatter : function(value, row, index) {
										return new Date(value)
												.format("yyyy-MM-dd");
									}
								},
								{
									title : '状态',
									field : 'resourceId',
									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {
										if (typeof (value) != "undefined") {
											return '<span class="label label-success">已加入</span>';
										}
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									width : 90,
									formatter : function(value, row, index) {
										if (typeof (row.resourceId) != "undefined") {
											return '<button type="button" class="btn btn-xs btn-warning"  onclick="deleteResourceGroupRel(this, \''
													+ row.id
													+ '\')" data-toggle="modal" data-loading-text="Loading...">移除</button>';
										} else {
											return '<button type="button" class="btn btn-xs btn-success"  onclick="addResourceGroupRel(this, \''
													+ row.id
													+ '\')" data-toggle="modal" data-loading-text="Loading...">加入</button>';
										}
									}
								} ],
						formatLoadingMessage : function() {
							return "请稍等，正在加载中...";
						},
						formatNoMatches : function() { // 没有匹配的结果
							return '无符合条件的记录';
						}
					});
};

function resourceQueryParams(params) {
	var search = {};
	$.each($("#groupSearchForm").serializeArray(), function(i, field) {
		if (field.name == "ifBindGroup" && field.value == "on") {
			search["ifBindGroup"] = "Y";
		} else {
			search[field.name] = field.value;
		}
	});
	search["id"] = $("#updateResourceId").val();
	params.search = JSON.stringify(search);
	return params;
}

$('#doGroupsearch').click(function() {
	$('#resourceGroupTableId').bootstrapTable('refresh');
});
function addResourceGroupRel(buttonObj, resourceGroupId) {
	$(buttonObj).button('loading');
	// $("#resourceGroupId_hidden").val();
	$.ajax({
		url : rootUri + "/manage/addResourceGroupRel.json",
		data : {
			resourceGroupId : resourceGroupId,
			resourceId : $("#updateResourceId").val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$(buttonObj).button('reset');
			if (data.status == 1) {
				$('#resourceGroupTableId').bootstrapTable('refresh');
				// alert("保存成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$(buttonObj).button('reset');
		}
	});

}
function deleteResourceGroupRel(buttonObj, resourceGroupId) {
	$(buttonObj).button('loading');
	// $("#resourceGroupId_hidden").val();
	$.ajax({
		url : rootUri + "/manage/deleteResourceGroupRel.json",
		data : {
			resourceGroupId : resourceGroupId,
			resourceId : $("#updateResourceId").val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$(buttonObj).button('reset');
			if (data.status == 1) {
				$('#resourceGroupTableId').bootstrapTable('refresh');
				// alert("保存成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$(buttonObj).button('reset');
		}
	});
}

$('#ResourceKeyFormId').bootstrapValidator({
	message : '输入值错误',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	}
});