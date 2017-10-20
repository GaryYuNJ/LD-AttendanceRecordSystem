<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Footer starts -->
<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- Copyright info -->
				<p class="copy">
					Copyright 2016 Greenland Group All rights reserved	绿地集团© 版权所有
				</p>
			</div>
		</div>
	</div>
</footer>
<!-- Footer ends -->
<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
<!-- JS -->
<script src="<c:url value="/js/jquery.js" />"></script>
<!-- jQuery -->
<script src="<c:url value="/js/bootstrap.js" />"></script>
<!-- Bootstrap -->
<script src="<c:url value="/js/jquery-ui-1.9.2.custom.min.js" />"></script>
<!-- jQuery UI -->
<!-- jQuery Flot -->

<!-- Date picker -->
<script src="<c:url value="/js/jstree.min.js" />"></script>
<script src="<c:url value="/js/bootstrap-table.min.js" />"></script>
<script src="<c:url value="/js/bootstrap-table-zh-CN.js" />"></script>
<script src="<c:url value="/js/bootstrap-switch.min.js" />"></script>
<script src="<c:url value="/js/custom.js" />"></script>
<script src="<c:url value="/js/bootstrapValidator.min.js" />"></script>
<script src="<c:url value="/js/bootstrap-datetimepicker.js" />"></script>
<script src="<c:url value="/js/bootstrap-datetimepicker.zh-CN.js" />"></script>
<script src="<c:url value="/js/bootstrap-select.min.js" />"></script>
<script src="<c:url value="/js/defaults-zh_CN.min.js" />"></script>

<script src="<c:url value="/js/fileinput.min.js" />"></script>
<script src="<c:url value="/js/fileinput_locale_zh.js" />"></script>

<script src="<c:url value="/js/bootstrap-table-export.js" />"></script>
<script src="<c:url value="/js/tableExport.min.js" />"></script>
<!-- 
<script src="<c:url value="/js/rawgit-bootstrap-editable.js" />"></script>
<script src="<c:url value="/js/bootstrap-table-editable.min.js" />"></script>
 -->
<script type="text/javascript">
$("#nav li:eq(${pageIndex}) a").addClass("open");
</script>