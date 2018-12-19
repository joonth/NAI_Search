<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script type="text/javascript">
		history.pushState(null, null, location.href); 
		window.onpopstate = function(event) { 
			location.href="main";
		}
	</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			.table { display:table; }
			.table-row { display:table-row; }
			.table-cell { display:table-cell; }
		</style>
	</head>
<body>
	<div class="table">
		<div class="table-row"> <img alt="img" src="${infoDto.img}"></div>
		<div class="table-row">${infoDto.addr1} ${infoDto.addr2}</div>
		<div class="table-row">${infoDto.hpaddr}</div>
		<div class="table-row">${infoDto.inonm}</div>
		<div class="table-row">${infoDto.trprchaptel}</div>
		<div class="table-row">${infoDto.trprnm}</div><br>
	</div>
	<div class="table">
		<c:choose>
			<c:when test="${empty list}">
				<div class="table-row">---작성된 글이 없습니다.---</div>
			</c:when>
			<c:otherwise>
				<c:forEach var = "dto" items="${list}">
					<div class="table-row">
						<div class="table-cell">${dto.ac_name}</div>
						<div class="table-cell">${dto.m_id}</div>
						<div class="table-cell">${dto.ac_comment}</div>
						<div class="table-cell">${dto.ac_score}</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<form action="addComment">
	
			<input type="hidden" name="ac_name" value="${infoDto.inonm}">
			<input type="hidden" name="m_id" value="session에서꺼내오기">
			<input type="text" name="ac_comment" placeholder="평을 작성하실때 과정명과 강사님 성함을 넣어주세요~!" >
			<select name="ac_score">
				<option value="5.0">5.0</option>
				<option value="4.5">4.5</option>
				<option value="4.0">4.0</option>
				<option value="3.5">3.5</option>
				<option value="3.0">3.0</option>
				<option value="2.5">2.5</option>
				<option value="2.0">2.0</option>
				<option value="1.5">1.5</option>
				<option value="1.0">1.0</option>
				<option value="0.5">0.5</option>
				<option value="0.0">0.0</option>
			</select>
			<input type="submit" value="글작성">
			<input type="button" value="뒤로가기" onclick="location.href='main'">
		</form>
	</div>
</body>
</html>