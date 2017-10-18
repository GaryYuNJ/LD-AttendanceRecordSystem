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
			<i class="icon-home"></i> 考勤月报表页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 考勤月报表页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">考勤月报表</a>
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
							<div class="pull-left">考勤报表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="userListTable">
							<div class="col-lg-12">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 80px">用户名称</label>
										<div class="col-lg-2">
											<input type="text" id="userNameSearch" class="form-control" placeholder="用户名称">
										</div>
										<label class="col-lg-2 control-label" style="width: 80px">手机号</label>
										<div class="col-lg-2">
											<input type="text" id="userMobileSearch" class="form-control" placeholder="手机号">
										</div>
										<label class="col-lg-2 control-label" style="width: 80px">公司</label>
										<div class="col-lg-2">
											<input type="text" id="companySearch" class="form-control" placeholder="公司">
										</div>
										
				                    	<label class="col-lg-2 control-label" style="width: 80px">月度</label>
						                <div class='col-lg-2 datetimepicker'  style='padding-left:15px;'>
						                    <input type='text' class="form-control"  id="monthSearch" name="monthSearch"  placeholder="月度"/>
						                    <span class="input-group-addon">
						                        <span class="glyphicon glyphicon-calendar"></span>
						                    </span>
						                </div>
				                	   <!-- 
										<label class="col-lg-2 control-label" style="width: 80px">月度</label>
										<div class="col-lg-2">
											<input type="text" id="monthSearch" class="form-control" placeholder="月度">
										</div>
										 -->
										<div class="col-lg-3">
											<button type="button" onclick = "totalRefreshTable();" class="btn btn-primary">
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
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Matter
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">备注详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="userDetailsTable">
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">备注详情</a></li>
							    </ul>
							       
							    <div class="tab-content">
								      <div class="tab-pane active" id="home">
										  <form class="form-horizontal" role="form">
											    <div class="form-group">
					                                  <label class="col-lg-2 control-label">ID</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="userId_InForm">
					                                  </div>
					                                   <label class="col-lg-2 control-label">用户名</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control"  disabled="true" id="userName_InForm" placeholder="用户名">
					                                  </div>
				                                </div>
				                                <div class="form-group">
					                                  <label class="col-lg-2 control-label">手机号</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control"  disabled="true" placeholder="手机号" id="userMobile_InForm">
					                                  </div>
					                                   <label class="col-lg-2 control-label">性别</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control"  disabled="true" id="userSex_InForm" placeholder="性别">
					                                  </div>
				                                </div>
											    <div class="form-group">
					                                  <label class="col-lg-2 control-label">邮箱</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control"  disabled="true" placeholder="邮箱" id="userEmail_InForm">
					                                  </div>
					                                   <label class="col-lg-2 control-label">生日</label>
					                                  <div class="col-lg-3">
					                                    <input type="text" class="form-control"  disabled="true" id="userBirth_InForm" placeholder="生日">
					                                  </div>
				                                </div>
				                                <div class="form-group">
				                                	  <label class="col-lg-2 control-label">客户关系</label>
					                                  <div class="col-lg-3">
					                                    <select class="form-control"  disabled="true"  id="userRelation_InForm">
					                                      <option></option>
					                                      <option value="1">来访</option>
					                                      <option value="2">业主</option>
					                                    </select>
					                                  </div>
				                                </div>
										    	<div class="control-group">
										          <label class="control-label"></label>
										          <div class="controls">
										            <button class="btn btn-success disabled">保存</button>
										          </div>
										        </div>
										  </form>
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
	<!-- Matter ends -->
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>
<!-- Content ends -->
<!-- 弹窗 -->
<div class="modal fade" id="addPermissionLayer" tabindex="-1" role="dialog"
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
	                      <label class="col-lg-2 control-label">用户ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="userId_addPer" name="customerId"  readonly="true" placeholder="资源ID">
	                     </div>
	                      <label class="col-lg-2 control-label">用户名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="userName_addPer" disabled="true" placeholder="用户名">
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

<input type="hidden" id="userName_hidden" name="specificUserId">

<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/attendance_page.js" />"></script>
