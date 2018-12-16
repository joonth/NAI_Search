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
	<script type="text/javascript">
		var mykey = config.MY_KEY;
	</script>

</head>
<body>

<%
	int count = 0;
org.jsoup.nodes.Document doc=
			Jsoup.connect("http://www.hrd.go.kr/hrdp/api/apieo/APIEO0101T.do?srchTraEndDt=20191231&pageSize=1500&srchTraStDt=20181211&sortCol=TOT_FXNUM&authKey=&sort=ASC&returnType=XML&outType=1&pageNum=1&srchTraPattern=2&srchPart=-99&apiRequstPageUrlAdres=/jsp/HRDP/HRDPO00/HRDPOA11/HRDPOA11_1.jsp&apiRequstIp=112.221.224.124")
			.timeout(60000).maxBodySize(10*1024*1024).get();
			Elements datas = doc.select("scn_list");
			out.print("<input type='text' id='myInput' onkeyup='myFunction(this.value)' placeholder='학원,과정,주소로 검색 가능합니다.' title='Type in a name'>");
			out.print("<div id='none'><table id='myTable'><tr class='header'><tr>");
			out.print("<th style='width:20%;'>사진</th>");
			out.print("<th style='width:40%;'>과정명</th>");
			out.print("<th style='width:25%;'>학원명</th>");
			out.print("<th style='width:15%;'>주소</th></tr>");
	for(int i = 0; i < datas.size(); i++){
		String title = datas.get(i).select("title").toString()+"<br>";
		 if(title.contains("자바")
				|| title.contains("웹")
				|| title.contains("앱")
				|| title.contains("빅데이터")
				|| title.contains("개발자")
				|| title.contains("Iot")
				|| title.contains("ICT")
				|| title.contains("파이썬")
				|| title.contains("오라클")
				|| title.contains("UI")
				|| title.contains("UX")
				|| title.contains("디지털컨버전스")
				|| title.contains("오픈소스")
				|| title.contains("사물인터넷")
				|| title.contains("프로그래밍")
				|| title.contains("보안")
				){
		//////////////////////////////////
	 
		  org.jsoup.nodes.Document doc1=
					Jsoup.connect("http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA40/HRDPOA40_2.jsp?authKey=&returnType=XML&outType=2&srchTrprId="+datas.get(i).select("trprId").toString().substring(9, 28).trim()+"&srchTrprDegr=1")
					.timeout(80000).maxBodySize(10*1024*1024).get();
  
		//System.out.println(datas1.get(i).select("filePath"));
	
		///////////////////////////////////	 filePath
		//System.out.println(datas.get(i).select("trprId").toString().substring(9, 28).trim());
	 		  if(!doc1.select("filePath").toString().equals("")){
				out.print("<tr><td><img src='"+doc1.select("filePath").toString().substring(10, 94).trim()+"' alt='pic' width='150' height='90'></td>");			
			}else{
				out.print("<tr><td><img src='https://hanamsport.or.kr/www/images/contents/thum_detail.jpg' alt='pic' width='150' height='90'></td>");			
			}  
	
			out.print("<td>"+title.substring(7)+"</td>");
			out.print("<td>"+datas.get(i).select("subTitle")+"</td>");
			out.print("<td>"+datas.get(i).select("address")+"</td></tr></div>");
			count++;
		 }
}
			System.out.println("출력 과정수 : "+count);


%>

 
 </body>
</html>
