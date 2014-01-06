<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/inc/commons.jsp"%>
<title><fmt:message key="home.title" /></title>
</head>
<body>
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<div class="container">
		
		<div class="container">
			<div class="alert alert-warning fade in">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">×</button>
				<strong>移除参与者成功</strong>&nbsp;&nbsp;&nbsp;&nbsp;成功的将参与者从抽奖箱内移除。
			</div>
			<div style="height: 100px; visibility: hidden"></div>
			<form action="<c:url value='/export'/>" method="post"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">

						<fieldset>
							<legend>移除参与者信息</legend>
							<input class="file-inputs" type="file" name="luckyDogs">
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class="row">
					<div class="col-md-5"></div>
					<div class="col-md-7">
						<button type="submit" class="btn  btn-default">提交</button>
					</div>
				</div>
			</form>

		</div>
		<div style="height: 300px; visibility: hidden"></div>
	</div>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>

	<script type="text/javascript">
		var flag =
	<%if (null == request.getAttribute("flag")) {
				out.print("false");
			} else {
				out.print(request.getAttribute("flag"));
			}%>
		;
		if (true == flag)
			$(".alert").alert();
		else
			$(".alert").hide();
	</script>
</body>
</html>