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
			<i class="icon-home"></i> 用户组管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 用户组管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">用户组列表</a>
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
							<div class="pull-left">用户组列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="userGroupListTable">
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">用户组名称</label>
										<div class="col-lg-3">
											<input type="text" id="userGroupNameSearch" class="form-control" placeholder="用户组名称">
										</div>
										<div class="col-lg-3">
											<button type="button" onclick = "$('#userGroupTableId').bootstrapTable('refresh');" class="btn btn-primary">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#createNewGroupModal">
												<i class="icon-plus"></i> 新增用户组
											</button>
										</div>
									</div>
								</form>
							</div>
							
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover"
									id="userGroupTableId">
									
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
							<div class="pull-left">用户组详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="userGroupDetailsTable">
						 
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">用户组详情</a></li>
							      <li><a href="#userAndUserGroup" >用户组与用户</a></li>
							      <li><a href="#userGroupResource"  >用户组与资源</a></li>
							      <li><a href="#userGroupResourceGroup"  >用户组与资源组</a></li>
							    </ul>
							    <div class="tab-content">
							      <div class="tab-pane active" id="home">
							      		<!-- Bootstrap 表单 -->
							      		<form class="form-horizontal" role="form">
							      			<div class="form-group">
				                                  <label class="col-lg-2 control-label">ID</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="userGroupId_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label">用户组名称</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" id="userGroupName_InForm" placeholder="用户组名称">
				                                  </div>
			                                </div>
			                                <div class="form-group">
				                                   <label class="col-lg-2 control-label">创建时间</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  id="userGroupCreateTime_InForm" placeholder="创建时间">
				                                  </div>
				                                  <!-- 
				                                  <label class="col-lg-2 control-label">状态</label>
				                                  <div class="col-lg-3">
					                                  <div class="make-switch" data-on="primary" data-off="info">
				                         					<input type="checkbox" checked id = "userGroupStatus_InForm">
				                     				  </div>
				                                  </div>
				                                   -->
				                                    <label class="col-lg-2 control-label">创建人</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  placeholder="创建人" id="userGroupCreateUser_InForm">
				                                  </div>
			                                </div>
										</form>
										<div class="control-group">
								          <label class="control-label"></label>
								          <!-- Button -->
								          <div class="controls">
								            <button class="btn btn-success" id="updateUserGroup_Button">保存</button>
								          </div>
								     	</div>
					      		  </div>
					      		  <!-- usergroup table -->
							      <div class="tab-pane" id="userAndUserGroup">
								      <div class="col-lg-12">
									  <hr>
										<form class="form-horizontal" role="form">
											<div class="form-group">
												<label class="col-lg-2 control-label">只看绑定</label>
				                                  <div class="col-lg-1">
				                                  	<div id="selectBindUserFlag" class="make-switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
													    <input type="checkbox" checked id="selectBindUserCheckBox"/>
													</div>
				                                  </div>
												<label class="col-lg-2 control-label" style="width: 120px">用户名称</label>
												<div class="col-lg-2">
													<input type="text" id="userNameSearch" class="form-control" placeholder="用户名称">
												</div>
												
												<label class="col-lg-2 control-label" style="width: 120px">手机号</label>
												<div class="col-lg-3">
													<input type="text" id="userMobileSearch" class="form-control" placeholder="手机号">
												</div>
												
												<div class="col-lg-1">
													<button type="button" onclick = "$('#userListTableId').bootstrapTable('refresh');" class="btn btn-primary">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
										</form>
										</div>
								      	<div class="col-lg-12">
											<table class="table table-striped table-bordered table-hover"
												id="userListTableId">
												
											</table>
										</div>
								  </div>
							      <div class="tab-pane" id="userGroupResource">
								  	    <!-- 资源树节点 -->
							      		<div class="col-lg-3">
												<div class="widget treeMinHeight" id="jstree_resource"></div>
										</div>
										<!-- 资源table -->
									  	<div class="col-lg-9">
										<hr>
										<form class="form-horizontal" role="form" id="resourceSearchform">
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
												
												<div class="col-lg-3">
													<button type="button" class="btn btn-primary" id="doSearchResource">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
											<input type="hidden" id="resourceNodeId_hidden" name="nodeId">
											<input type="hidden" id="userGroupId_hidden" name="specificCusGroupId">
										</form>
										<table class="table table-striped table-bordered table-hover"
											id="resourceTableId">
		
										</table>
   									</div>
								  </div>
							      <div class="tab-pane" id="userGroupResourceGroup">
										<div class="col-lg-9">
												<hr>
												<form class="form-horizontal" role="form" id="groupSearchForm">
													<div class="form-group">
														<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
														<div class="col-lg-3">
															<input type="text" name="name" class="form-control" placeholder="资源组名称">
														</div>
														<div class="col-lg-3">
															<button type="button"  class="btn btn-primary" id="doResGroupsearch">
																<i class="icon-search"></i> 查询
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
				<h4 class="modal-title" id="createNewGroupModalLabel">添加用户组</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
                       <label class="col-lg-2 control-label">用户组名称</label>
                       <div class="col-lg-4">
                         <input type="text" class="form-control" placeholder="用户组名称" id="userGroupName_CreateForm">
                       </div>
                    </div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="closeCreateGroupWindow">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createUserGroup();" >保存</button>
			</div>
		</div>
	</div>
</div>

<!--用户组授权 弹窗 -->
<!-- Modal -->
<div class="modal fade" id="addPermissionModal" tabindex="-1" role="dialog"
	aria-labelledby="addPermissionModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="addPermissionModalLabel">权限更新</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addResPermissionForm">
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">资源ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="resourceId_addPer" name="resourceId"  readonly="true" placeholder="资源ID">
	                     </div>
	                      <label class="col-lg-2 control-label">资源名称</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="resourceName_addPer" disabled="true" placeholder="资源名称">
	                     </div>
	                   </div>
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">用户组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="cusGrpId_addPer" name="cgroupId"  readonly="true" placeholder="资源ID">
	                     </div>
	                      <label class="col-lg-2 control-label">用户组名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="cusGrpName_addPer" disabled="true" placeholder="用户名">
	                     </div>
	                   </div>
	                   
	                   <div class="form-group">
	                     <label class="col-lg-2 control-label">授权状态</label>
	                     <div class="col-lg-4">
	                      <div class="make-switch" id="permissionStatus_addPer" data-on="success" data-off="warning" data-off="info" data-on-label="启用" data-off-label="禁用">
	            					<input type="checkbox" disabled checked name="enable">
	        				  </div>
	                     </div>
	                     <label class="col-lg-2 control-label">连带授权</label>
	                     <div class="col-lg-4">
	                      	  <div class="make-switch" id="jointAuthFlagSwitch_addPer" data-on="success" data-off="warning" data-off="info" data-on-label="是" data-off-label="否">
	            					<input type="checkbox" checked name="jointAuthFlag_addPer">
	        				  </div>
	                     </div>
	                  </div>
	                  
	                    <div class="form-group">  
	                    	<label class="col-lg-2 control-label">起始时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;'>
			                    <input type='text' class="form-control"  id="startDate_addPer" name="startDateStr"  placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	   </div>  
                	   <div class="form-group">  
	                    	<label class="col-lg-2 control-label">过期时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;' >
			                    <input type='text' class="form-control" id="endDate_addPer" name="endDateStr" placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	  </div>
	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeButton_addPer" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveButton_addPer" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 用户组与资源组关系 -->
<div class="modal fade" id="addResGroupPermissionLayer" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">权限更新</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addResGrpPermissionForm">
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">资源组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="resGrpId_addGrpPer" name="rgroupId"  readonly="true" placeholder="资源组ID">
	                     </div>
	                      <label class="col-lg-2 control-label">资源组名称</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="reGrpName_addGrpPer" disabled="true" placeholder="资源组名称">
	                     </div>
	                   </div>
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">用户组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="userGrpId_addGrpPer" name="cgroupId"  readonly="true" placeholder="用户组ID">
	                     </div>
	                      <label class="col-lg-2 control-label">用户组名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="userGrpName_addGrpPer" disabled="true" placeholder="用户组名">
	                     </div>
	                   </div>
	                   
	                    <div class="form-group">  
	                    	<label class="col-lg-2 control-label">起始时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;'>
			                    <input type='text' class="form-control"  id="startDate_addGrpPer" name="startDateStr"  placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	   </div>  
                	   <div class="form-group">  
	                    	<label class="col-lg-2 control-label">过期时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;' >
			                    <input type='text' class="form-control" id="endDate_addGrpPer" name="endDateStr" placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	  </div>
	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeButton_addGrpPer" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveButton_addGrpPer" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>


<input type="hidden" id="selectBindUserFlag_hidden" value="1">
<input type="hidden" id="cusGrpName_hidden" value="1">

<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/userGroup_page.js" />"></script>
