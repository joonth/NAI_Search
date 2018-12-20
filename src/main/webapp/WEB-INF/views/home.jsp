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
		<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/search.js"></script> --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/search.css">
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>	
		<script type="text/javascript">
	
			function myFunction(val) {
					
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable");
			  tr = table.getElementsByTagName("tr");
				  for (i = 0; i < tr.length; i++) {				 
					td = tr[i].getElementsByTagName("td")[1];
				    td1 = tr[i].getElementsByTagName("td")[2];
					td2 = tr[i].getElementsByTagName("td")[3];
				    if (td) {
				      txtValue = td.textContent || td.innerText;
				      txtValue1 = td1.textContent || td1.innerText;
				      txtValue2 = td2.textContent || td2.innerText; 
				      
				      if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValue1.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1) {
						document.getElementById("none").style.display="block";
				    	  tr[i].style.display = "";
				    	  //
				   	      $.ajax({
								url:"getImg",
								data:{"text":txtValue1},// "seq="+seq
								datatype:"json",
								method:"get",
								success:function(obj){ //컨트롤에서 전달받은 객체(map)--> obj
									var dto=obj["한경닷컴IT교육센터"]; 
									var imgs=document.getElementsByClassName("img");
									for(var i=0;i<imgs.length;i++){
										imgs[i].innerHTML="<img src='"+dto.img+"' alt='img' width='150' height='90'>";
									}
								},
								error:function(){
									console.log("서버통신실패!!");
								}
							});    
				      } else {	    
				        tr[i].style.display = "none";
				      }
				   }  
					  if(val === ""){	//검색창에 문자가 없을경우 리스트 출력하지 않음.
					  document.getElementById("none").style.display="";
					  }
					}
				}
		</script>
		
	</head>
	<body>
		
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
		 
				<c:forEach var = "dto" items="${list}">
					<tr onclick="window.location='info?subTitle=${dto.subTitle}'">
						
			
					<c:set var="test" value="한경닷컴IT교육센터"/>
						<c:choose>
						    <c:when test="${test eq dto.subTitle}">
						        <td class="img">1</td>	
						    </c:when>
						    <c:otherwise>
					    	  <td>2</td>
						    </c:otherwise>
						</c:choose>
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
