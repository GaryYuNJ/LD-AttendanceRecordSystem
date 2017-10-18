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
			<i class="icon-home"></i> 管理员管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 管理员管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">管理员列表</a>
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
							<div class="pull-left">管理员列表</div>
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
										<label class="col-lg-2 control-label" style="width: 120px">管理员名称</label>
										<div class="col-lg-3">
											<input type="text" id="userNameSearch" class="form-control" placeholder="管理员名称">
										</div>
										<div class="col-lg-3">
											<button type="button" onclick = "$('#userListTableId').bootstrapTable('refresh');" class="btn btn-primary">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#userModal" onclick="newUserPre()">
												<i class="icon-plus"></i> 新增管理员
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

</div>
</div>
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document"  style="width:800px"> 
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">管理员详情</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form" id="roleDetailId">
                            <div class="form-group">
                              <label class="col-lg-2 control-label">登陆账号</label>
                              <div class="col-lg-4">
                                <input type="text" class="form-control" placeholder="登陆账号" name="name" id="nameId">
                              </div>
                              <label class="col-lg-2 control-label">可用状态</label>
								<div class="col-lg-4">
									<div id="userStatusId" class="make-switch" data-on="success" data-off="warning" 
										data-on-label="启用" data-off-label="禁用" >
										<input type="checkbox" checked name="status" id="statusId">
									</div>
								</div>
                          </div>
                          <div class="form-group">
							<label class="col-lg-4 control-label">角色</label>
							<div class="col-lg-8">
								<select id="userRoleId" name="userRole"
									class="selectpicker show-tick form-control" data-live-search="false">
								</select>
							</div>
						 </div>
                        </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="saveButtonId" onclick = "saveUser();" >保存</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="userUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document"  style="width:800px"> 
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">管理员详情</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form" id="roleDetailId">
                            <div class="form-group">
                              <label class="col-lg-2 control-label">登陆账号</label>
                              <div class="col-lg-4">
                                <input type="text" class="form-control" placeholder="登陆账号" name="name" id="UpdateNameId" readonly="true">
                              </div>
                              <label class="col-lg-2 control-label">可用状态</label>
								<div class="col-lg-4">
									<div id="updateUserStatusId" class="make-switch" data-on="success" data-off="warning" 
										data-on-label="启用" data-off-label="禁用" >
										<input type="checkbox" checked name="status" id="updateStatusId">
									</div>
								</div>
                          </div>
                          <input type="hidden" id="backUserId">
                       <div class="form-group">
							<label class="col-lg-4 control-label">角色</label>
							<div class="col-lg-8">
								<select id="updateUserRoleId" name="updateUserRole"
									class="selectpicker show-tick form-control" data-live-search="false">
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="updateButtonId" onclick = "updateUser();" >保存</button>
					</div>
				</div>
			</div>
		</div>
<!-- Latest compiled and minified JavaScript -->
<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/backendUser_page.js" />"></script>
