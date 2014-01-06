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
	<div class="bs-header" id="content">
		<div class="container">
			<h1>年会抽奖系统</h1>
			<p>年会盼什么？快乐！什么时候最快乐？天上掉馅饼的时候最快乐！</p>
			<p>天上掉馅饼的时候到了！不止是馅饼哦！</p>
		</div>
	</div>
	<div class="container">
		<div style="height: 100px; visibility: hidden"></div>
		<div class="jumbotron">
			<h1>好运来！好运来！拼人品的时刻终于到啦！</h1>
			<p>扶摔倒的老奶奶、公交车让座、归还失物......<br/>这些平日攒下的人品终于要排上用场啦！<br/>人品内存小的别溢出了啊！哈哈哈哈！<br/>今天看看谁的运气最好。</p>
			<p>
				<a class="btn btn-primary btn-lg" role="button" href='<c:url value="/getLuckyPage"></c:url>'>开始抽奖</a>
			</p>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
</body>
</html>