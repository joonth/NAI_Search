


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

    	  //
    	   $.ajax({
				url:"main",
				data:{"subTitle":txtValue1},// "seq="+seq
				datatype:"json",
				method:"post",
				success:function(obj){ //컨트롤에서 전달받은 객체(map)--> obj
					var dto=obj["dto"];//map에서 dto객체 꺼내고
					$("textarea[name=testAjax]").val(dto["content"]);
				},
				error:function(){
					alert("서버통신실패!!");
				}
			}); 
    	   
    	 //
    	  
    	  console.log(txtValue1);
      } else {
    	    
        tr[i].style.display = "none";
      }
    }
  }
	  if(val === ""){	//검색창에 문자가 없을경우 리스트 출력하지 않음.
	  document.getElementById("none").style.display="";
  }
}
