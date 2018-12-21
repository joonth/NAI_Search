<%@page import="java.util.List"%>
<%@page import="com.hk.web.dtos.SearchDto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/search.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/search.css">
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>	
		
	</head>
	<body>
<div>${map1}</div>
		
		<input type="text" id="myInput" onkeypress="if(event.keyCode==13) {myFunction(this.value); return false;}" placeholder="학원,과정,주소로 검색 가능합니다." title="Type in a name">
		<div id="none">
			<table id="myTable">
				<tr class="header">
					<th style="width:20%;">사진</th>
					<th style="width:40%;">과정명</th>
					<th style="width:25%;">학원명</th>
					<th style="width:10%;">주소</th>
					<th style="width:5%;">평점</th>
									
				</tr>
		 		
				<c:forEach var = "dto" items="${list}" >
					<tr onclick="window.location='info?subTitle=${dto.subTitle}'">
						
			
			
						<td class="img" ></td>	
						<td>${dto.title}</td>
						<td>${dto.subTitle}</td>
						<td>${dto.address}</td>
						<td>${dto.score}</td>
					
					</tr>
				</c:forEach>
			</table>
		</div>
	 </body>
</html>
