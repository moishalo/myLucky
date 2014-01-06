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
	<div style="height: 100px; visibility: hidden"></div>

	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h1>中奖信息</h1>
			</div>
			<div class="col-md-4"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-2">
						<h2>姓名</h2>
					</div>

					<div class="col-md-10">
						<h2 id="h_name">xxx</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<h2>部门</h2>
					</div>

					<div class="col-md-10">
						<h2 id="h_org">xxx</h2>
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>

	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-8 center-block">
				<button id="btn_do" type="button" class="btn btn-success btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;开始&nbsp;&nbsp;&nbsp;&nbsp;</button>

				<button id="btn_shake" type="button" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;摇一摇&nbsp;&nbsp;&nbsp;&nbsp;</button>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>

	<script type="text/javascript">
		var box = null;
		var current = 0;
		var status = 0; //0 停止状态,1 转动状态
		$.ajax({
			url : "<c:url value='/luckyBox'/>",
			type : "GET",
			async : false,
			dataType : "json",
			success : function(data, textStatus) {
				box = data;
			}
		});

		function update() {
			var num = ++current;
			if (num >= box.length)
				current = 0;
			$("#h_name").text(box[current].name);
			$("#h_org").text(box[current].org);
		}

		var intervalId;

		function start() {
			//换颜色
			$("#btn_do").removeClass("btn-success");
			$("#btn_do").addClass("btn-danger");

			$("#btn_do").html(
					"&nbsp;&nbsp;&nbsp;&nbsp;停止&nbsp;&nbsp;&nbsp;&nbsp;");

			intervalId = self.setInterval("update()", 50);

		}

		function end() {
			self.clearInterval(intervalId);
			//换颜色
			$("#btn_do").removeClass("btn-danger");
			$("#btn_do").addClass("btn-success");

			$("#btn_do").html(
					"&nbsp;&nbsp;&nbsp;&nbsp;开始&nbsp;&nbsp;&nbsp;&nbsp;");
			var postData = box[current];
			$.ajax({
				url : "<c:url value='/getLucky'/>",
				type : "POST",
				async : false,
				data : postData,
				dataType : "json",
				success : function(data, textStatus) {
				}
			});
		}

		$("#btn_do").bind("click", function(event) {
			if (status == 0) {
				status = 1;
				start();
			} else {
				status = 0;
				end();
			}
		});

		$("#btn_shake").bind("click", function(event) {
			$.ajax({
				url : "<c:url value='/luckyBox'/>",
				type : "GET",
				async : false,
				dataType : "json",
				success : function(data, textStatus) {
					box = data;
				}
			});
		});

		$("body").bind("keydown", function(e) {
			var keynum;

			if (window.event) // IE
			{
				keynum = e.keyCode;
			} else if (e.which) // Netscape/Firefox/Opera
			{
				keynum = e.which;
			}

			if (keynum == 32) {
				if (status == 0) {
					status = 1;
					start();
				} else {
					status = 0;
					end();
				}
			}
			if (keynum == 13) {
				if (status == 1) {
					status = 0;
					end();
				}
				$.ajax({
					url : "<c:url value='/luckyBox'/>",
					type : "GET",
					async : false,
					dataType : "json",
					success : function(data, textStatus) {
						box = data;
					}
				});
			}
		});
	</script>
</body>
</html>