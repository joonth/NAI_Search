
function myFunction(val) {
		
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  
  var acTitle = new Array();
  var tdNum = new Array();
  var count =0;
  var imgs=document.getElementsByClassName("img");
  
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
    	  //console.log("view : "+ (i-1));
    	  
    	  acTitle.push(txtValue1);
    	  tdNum.push(i-1);
   	     
      } else {	    
        tr[i].style.display = "none";
      }
   }  
	  if(val === ""){	//검색창에 문자가 없을경우 리스트 출력하지 않음.
	  document.getElementById("none").style.display="";
	  }
	}
  $.ajax({
		url:"getImg?",
		traditional:true,
		data:{"acTitle":acTitle},
		datatype:"json",
		method:"post",
		success:function(obj){ //컨트롤에서 전달받은 객체(map)--> obj
			for(var i=0; i<acTitle.length; i++){
			
		//	var dto=obj[acTitle[count]]; 
			//console.log(imgs[tdNum[count]] +" , tdNum : " + tdNum[count] + " , dto : " + dto);
		
			// 검색에 해당하는 학원들의 img만 구해서 변경해준다.
	
				imgs[tdNum[count]].innerHTML="<img src='"+obj[acTitle[count]].img+"' alt='img' width='150' height='90'>";
				//console.log("imgNum : " +tdNum[count] + "imgTitle : " + acTitle[count]);
				count++;
				console.log(i);
			}
				
				
		},
		error:function(){
			console.log("서버통신실패!!");
		}
	});  
  
  
  
  
}