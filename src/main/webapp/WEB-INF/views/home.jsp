<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<script type='text/javascript' src='../js/config.js'></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
	* {
	  box-sizing: border-box;
	}

	#none{
		display:none;
	}

	#myInput {
	  background-image: url('https://www.w3schools.com/css/searchicon.png');
	  background-position: 10px 10px;
	  background-repeat: no-repeat;
	  width: 100%;
	  font-size: 16px;
	  padding: 12px 20px 12px 40px;
	  border: 1px solid #ddd;
	  margin-bottom: 12px;
	}

	#myTable {
	  border-collapse: collapse;
	  width: 100%;
	  border: 1px solid #ddd;
	  font-size: 18px;
	}

	#myTable th, #myTable td {
	  text-align: left;
	  padding: 12px;
	}

	#myTable tr {
	  border-bottom: 1px solid #ddd;
	}

	#myTable tr.header, #myTable tr:hover {
	  background-color: #f1f1f1;
	}
	</style>
	<script>
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
	      txtValue = td.textContent || td1.innerText;
	      txtValue1 = td1.textContent || td1.innerText;
	      txtValue2 = td2.textContent || td2.innerText;

	      if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValue1.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1) {
			document.getElementById("none").style.display="block";
	    	  tr[i].style.display = "";

	      } else {
	    	    
	        tr[i].style.display = "none";
	      }
	    }
	  }
	  if(val === ""){	//검색창에 문자가 없을경우 리스트 출력하지 않음.
		  document.getElementById("none").style.display="";
	  }
	}
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
