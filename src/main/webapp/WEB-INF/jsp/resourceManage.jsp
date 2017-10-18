<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/nav.jsp"%>
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 资源管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 资源管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">资源列表</a>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">资源列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i
									class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content">
							<div class="col-lg-3">
								<hr>
								<button type="button" class="btn btn-primary"
									onclick="nodeCreate()">
									<i class="icon-plus"></i> 添加
								</button>
								<button type="button" class="btn btn-warning"
									onclick="nodeRename()">
									<i class="icon-pencil"></i> 修改
								</button>
								<button type="button" class="btn btn-danger"
									onclick="nodeDelete()">
									<i class="icon-remove"></i> 删除
								</button>
								<span class="badge" id="queryNodeId_html"></span>
								<hr>
								<div class="widget treeMinHeight" id="jstree_resource"></div>
							</div>
							
							
							<div class="col-lg-9">
								<hr>
								
								<form class="form-horizontal" role="form" id="searchform">
									<div class="form-group">
										<div class="col-lg-2">
											<input type="text" class="form-control" placeholder="资源名称"
												name="name">
										</div>
										<div class="col-lg-2">
											<select class="form-control" name="permissionAttrId" >
												<option value="">资源属性</option>
												<option value="1">公共资源</option>
												<option value="2">基础资源</option>
												<option value="3">私有资源</option>
											</select>
										</div>
										<div class="col-lg-2">
											<select class="form-control" name="buildingId"
												id="buildingId">
												<option value="">选择楼栋</option>
											</select>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary" id="dosearch">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-1">
											<button type="button" class="btn btn-primary"
												id="addResourceButton">
												<i class="icon-plus"></i> 新增
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#importModal">
												<i class="icon-plus"></i> 导入
											</button>
										</div>
									</div>
									<input type="hidden" name="nodeId" id="queryNodeId">
								</form>
								<table class="table table-striped table-bordered table-hover"
									id="resourceTableId">
								</table>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Matter ends -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">资源详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i
									class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" style="display: none;"
							id="groupGroupDetailsTable">
							<ul class="nav nav-tabs" id="myTab">
								<li class="active"><a href="#home">资源详情</a></li>
								<li><a href="#resourceGroupResource">资源与资源组</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="home">
									<div class="col-lg-3">
										<div class="widget treeMinHeight" id="jstree_updateResource"></div>
									</div>
									<div class="col-lg-9">
										<form class="form-horizontal" role="form"
											id="resourceFormId">
											<div class="form-group">
												<label class="col-lg-2 control-label">资源名称</label>
												<div class="col-lg-4">
													<input type="text" class="form-control" placeholder="资源名称"
														name="name" id="updateResourceName">
												</div>
												<label class="col-lg-2 control-label">显示顺序</label>
												<div class="col-lg-4">
													<input type="text" class="form-control" placeholder="1000"
														name="sequence" id="updateResourceSequenceId">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">楼栋</label>
												<div class="col-lg-4">
													<select class="form-control" id="updateResourceBuilding"
														name="buildingId">
														<option value="">请选择楼栋</option>
													</select>
												</div>
												<label class="col-lg-2 control-label">楼层</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="楼层(数字)" name="floor" id="updateResourceFloor">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">资源类型</label>
												<div class="col-lg-4">
													<select class="form-control" name="deviceType"
														id="updateResourceDeviceType">
														<option value="">资源类型</option>
														<option value="1">通行</option>
														<option value="2">家居</option>
														<option value="3">其它</option>
													</select>
												</div>
												<label class="col-lg-2 control-label">资源属性</label>
												<div class="col-lg-4">
													<select class="form-control" name="permissionAttrId"
														id="updateResourcePermissionAttrId">
														<option value="1">公共资源</option>
														<option value="2">基础资源</option>
														<option value="3">私有资源</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">可用状态</label>
												<div class="col-lg-4">
													<div id="updateResourceStatus" class="make-switch" data-on="success" data-off="warning" 
														data-on-label="启用" data-off-label="禁用" >
														<input type="checkbox" checked name="status"
															>
													</div>
												</div>
												<label class="col-lg-2 control-label">可分享</label>
												<div class="col-lg-4">
													<div id="updateResourceShareEnable" class="make-switch" data-on="success" data-off="warning" >
														<input type="checkbox" checked name="shareEnable"
															>
													</div>
												</div>
											</div>
											<input type="hidden" name="id" id="updateResourceId">
											<input type="hidden" name="nodeId" id="updateResourceNodeId">
											<input type="hidden" name="nodePath" id="updateResourceNodePath">
											
										</form>
										<hr>
										<form class="form-horizontal" role="form"
											id="ResourceKeyFormId">
											
										</form>
									<div class="center">
									<button type="button" class="btn btn-primary"
										id="updateResourceKeyButtonId">
										<i class="icon-plus"></i>新增钥匙
									</button>
									<button type="button" class="btn btn-primary"
										id="updateResourceButtonId">保存</button>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="resourceGroupResource">
									<form class="form-horizontal" role="form" id="groupSearchForm">
										<div class="form-group">
											<label class="col-lg-2 control-label">已绑定</label>
			                                  <div class="col-lg-2">
			                                  <div id="selectBindUserFlag" class="make-switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
												 <input type="checkbox" checked id="ifBindGroupId" name="ifBindGroup"/>
											  </div>
			                                  </div>
											<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
											<div class="col-lg-3">
												<input type="text" name="name" class="form-control" placeholder="资源组名称">
											</div>
											<div class="col-lg-3">
												<button type="button"  class="btn btn-primary" id="doGroupsearch">
													<i class="icon-search"></i> 查询
												</button>
											</div>
										</div>
									</form>
									<table class="table table-striped table-bordered table-hover"
									id="resourceGroupTableId">
									
									</table>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>

<!-- 导入资源Modal -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
	aria-labelledby="importModalLabel">
	<div class="modal-dialog" role="document"
		style="width: 800px; height: 600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="importModal">导入资源</h4>
				<a href="<c:url value="/common/ResourceUploadSample.xlsx" />">示例文件下载</a>
			</div>
			<div class="modal-body">
				<form action="uploadFile" id="uploadFileForm" method="post"
					enctype="multipart/form-data">
					<input id="input-folder-1" type="file" name="file"
						class="file-loading">
					<!-- <input type="submit" value="submit"> -->
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="uploadModalClose">关闭</button>
				<!-- <button type="button" class="btn btn-primary" id="importResourceButtId">保存</button> -->
			</div>
		</div>
	</div>
</div>


<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/resource_page.js" />"></script>
