<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/nav.jsp"%>

<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 资源组管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 资源组管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">资源组列表</a>
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
							<div class="pull-left">资源组列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="groupGroupListTable">
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form" id="groupSearchForm">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
										<div class="col-lg-3">
											<input type="text" name="name" class="form-control" placeholder="资源组名称">
										</div>
										<div class="col-lg-3">
											<button type="button"  class="btn btn-primary" id="doGroupsearch">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#createNewGroupModal">
												<i class="icon-plus"></i> 新增资源组
											</button>
										</div>
									</div>
								</form>
							</div>
							
							<div class="col-lg-12">
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
	<!-- Matter ends -->
	
	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">资源组详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="groupGroupDetailsTable">
						 
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">资源组详情</a></li>
							      <li><a href="#resourceGroupResource" >资源组与资源</a></li>
							    </ul>
							    <div class="tab-content">
							      <div class="tab-pane active" id="home">
							      		<!-- Bootstrap 表单 -->
							      		<form class="form-horizontal" role="form">
							      			<div class="form-group">
				                                  <label class="col-lg-2 control-label">ID</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="resourceGroupId_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label"  style="width: 120px">资源组名称</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" id="resourceGroupName_InForm" placeholder="用户组名称" name="name">
				                                  </div>
			                                </div>
			                                <div class="form-group">
				                                   <label class="col-lg-2 control-label">创建时间</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  id="resourceGroupCreateTime_InForm" placeholder="创建时间">
				                                  </div>
				                                    <label class="col-lg-2 control-label"  style="width: 120px">创建人</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  placeholder="创建人" id="resourceGroupCreategroup_InForm">
				                                  </div>
			                                </div>
										</form>
										<div class="control-group">
									          <label class="col-lg-2 control-label"></label>
									          <!-- Button -->
									          <div class="controls">
									            <button class="btn btn-success" id="updateResourceGroup_Button">保存</button>
									          </div>
									     	</div>
					      		  </div>
							      <div class="tab-pane" id="resourceGroupResource">
								  	    <!-- 资源树节点 -->
							      		<div class="col-lg-3">
												<div class="widget treeMinHeight" id="jstree_resource"></div>
										</div>
										<!-- 资源table -->
									  	<div class="col-lg-9">
										<hr>
										<form class="form-horizontal" role="form" id="resourceSearchform">
											<label class="col-lg-2 control-label">已绑定</label>
			                                  <div class="col-lg-2">
			                                  <div id="selectBindUserFlag" class="make-switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
												 <input type="checkbox" checked id="ifBindGroupId" name="ifBindGroup"/>
											  </div>
			                                  </div>
											<div class="form-group">
												<div class="col-lg-2">
													<input type="text" class="form-control" placeholder="资源名称" name="name">
												</div>
												<div class="col-lg-2">
													<select class="form-control" name="permissionAttrId">
													  <option value="">资源属性</option>
													  <option value="1">公共资源</option>
													  <option value="2">基础资源</option>
													  <option value="3">私有资源</option>
													</select>
												</div>
												<div class="col-lg-2">
													<select class="form-control" name="buildingId" id="buildingId">
													  <option value="">选择楼栋</option>
													</select>
												</div>
												
												<div class="col-lg-2">
													<button type="button" class="btn btn-primary" id="doSearchResource">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
											<input type="hidden" id="resourceNodeId_hidden" name="nodeId">
											<input type="hidden" id="resourceGroupId_hidden" name=resourceGroupId>
										</form>
										<table class="table table-striped table-bordered table-hover"
											id="resourceTableId">
		
										</table>
   									</div>
								  </div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
						
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>
<!-- Content ends -->
<!--创建新用户组 弹窗 -->
<!-- Modal -->
<div class="modal fade" id="createNewGroupModal" tabindex="-1" role="dialog"
	aria-labelledby="createNewGroupModalModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="createNewGroupModalLabel">添加资源组</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
                       <label class="col-lg-3 control-label" style="width:120px">资源组名称</label>
                       <div class="col-lg-4">
                         <input type="text" class="form-control" placeholder="资源组名称" name="name" id="groupGroupNameId">
                       </div>
                    </div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="closeCreateGroupWindow">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createResourceGroup();" >保存</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/resourceGroup_page.js" />"></script>
