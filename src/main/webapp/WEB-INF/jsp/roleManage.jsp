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
			<i class="icon-home"></i> 角色管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 角色管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">角色列表</a>
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
							<div class="pull-left">角色列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i
									class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="userListTable">
							<div class="col-lg-12">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">角色名称</label>
										<div class="col-lg-3">
											<input type="text" id="userNameSearch" class="form-control"
												placeholder="角色名称">
										</div>
										<div class="col-lg-3">
											<button type="button"
												onclick="$('#roleListTableId').bootstrapTable('refresh');"
												class="btn btn-primary">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#roleModal"
												onclick="newRolePre()">
												<i class="icon-plus"></i> 新增角色
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover"
									id="roleListTableId">

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
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document" style="width: 600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">角色详情</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="roleDetailId">
					<div class="form-group">
						<label class="col-lg-2 control-label">角色名称</label>
						<div class="col-lg-4">
							<input type="text" class="form-control" placeholder="名称"
								name="name" id="roleNameId">
						</div>
						<label class="col-lg-2 control-label">角色类型</label>
						<div class="col-lg-4">
						<label class="radio-inline"> <input type="radio"
							name="roleType" id="roleTypeRadio1" value="2">
							物业
						</label> <label class="radio-inline"> <input type="radio"
							name="roleType" id="roleTypeRadio2" value="3">
							授权人员
						</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">楼栋信息</label>
						<div class="col-lg-8">
							<select id="buildingId" name="building"
								class="selectpicker show-tick form-control" multiple
								data-live-search="false">
							</select>
						</div>
					</div>
					<input type="hidden" id="roleId">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveButtonId"
					onclick="saveRole();">保存</button>
			</div>
		</div>
	</div>
</div>
<!-- Latest compiled and minified JavaScript -->
<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/role_page.js" />"></script>
