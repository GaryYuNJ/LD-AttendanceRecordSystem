<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/common/header_ad.jsp"%>
 
<style>
 .table{
    table-layout: fixed;
    font-size:7px;
}
 .container {
  max-width:2070px
 }
</style>
<!-- Main bar -->
<div class="">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 外派月报表页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 外派月报表页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">外派月报表</a>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container" >
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
								
								<form class="form-horizontal" role="form">
								
										<label for="monthSearch" class="col-md-2 control-label">月度</label>
						                <div class="input-group date col-md-3 form_month" data-date-format="MM yyyy" data-link-format="yyyy-mm" data-link-field="monthSearch">
						                    <input class="form-control" size="16" type="text" value="" readonly>
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						                </div>
										<input type="hidden" id="monthSearch" name="monthSearch" value="" />
										
										<div class="col-lg-2" style='padding-right:15px;'>
											<button type="button" onclick = "totalRefreshTable(0);" class="btn btn-primary">
												<i class="icon-search"></i> 查询全部记录
											</button>
										</div>
						                
			                		</div>
								</form>
								
							</div>
							<hr>
							<hr>
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
<div class="modal fade" id="modifyRemarkLayer" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">备注编辑</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addResPermissionForm">
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">月份</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="month_modifyRemarkLayer" name="resourceId"  readonly="true" placeholder="月份">
	                     </div>
	                      <label class="col-lg-2 control-label">手机号</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="mobile_modifyRemarkLayer" disabled="true" placeholder="手机号">
	                     </div>
	                   </div>
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">姓名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="name_modifyRemarkLayer" name="name_modifyRemarkLayer"  readonly="true" placeholder="用户名">
	                     </div>
	                   </div>
	                   
	                   <div class="form-group">
	                   		<label for="name" class="col-lg-2 control-label">文本框</label>
	                   		<div class="col-lg-10">
    							<textarea class="form-control" id="remark_modifyRemarkLayer" rows="3"></textarea>
    						</div>
	                  </div>
	                  
	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeButton_modifyRemarkLayer" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveButton_modifyRemarkLayer" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="dataTypeSearch" name="dataTypeSearch">

<%@ include file="/common/script.jsp"%>
<%@ include file="/common/footer.html"%>
<script src="<c:url value="/js/attendance_additional_page.js" />"></script>
