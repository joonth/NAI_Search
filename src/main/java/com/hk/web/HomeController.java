package com.hk.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.web.daos.SearchDao;
import com.hk.web.dtos.SearchDto;
import com.hk.web.services.SearchService;


@Controller
public class HomeController {
	
	@Autowired
	SearchService service;
	
	@Value("#{apiKey['key']}")
	private String key;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		logger.info("학원리스트 출력",locale);
		long initTime = System.nanoTime();
		List<SearchDto> list = new ArrayList<>();
		int count = 0;
		org.jsoup.nodes.Document doc=
		Jsoup.connect("http://www.hrd.go.kr/hrdp/api/apieo/APIEO0101T.do?srchTraEndDt=20191231&pageSize=1500&srchTraStDt=20181211&sortCol=TOT_FXNUM&authKey="+key+"&sort=ASC&returnType=XML&outType=1&pageNum=1&srchTraPattern=2&srchPart=-99&apiRequstPageUrlAdres=/jsp/HRDP/HRDPO00/HRDPOA11/HRDPOA11_1.jsp&apiRequstIp=112.221.224.124")
		.timeout(60000).maxBodySize(10*1024*1024).get();
		Elements datas = doc.select("scn_list");
		
		for(int i = 0; i < datas.size(); i++){
			String tmpTitle = datas.get(i).select("title").toString();
			String title = tmpTitle.substring(tmpTitle.indexOf("<title>")+7, tmpTitle.indexOf("</title>")).trim();
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
					|| title.contains("보안"))
			 {
				 SearchDto dto =  new SearchDto();
				 String tmpsubTitle = datas.get(i).select("subTitle").toString();
				 String tmpAddress = datas.get(i).select("address").toString();
				 String subTitle = tmpsubTitle.substring(tmpsubTitle.indexOf("<subtitle>")+10, tmpsubTitle.indexOf("</subtitle>")).trim();
				 String address = tmpAddress.substring(tmpAddress.indexOf("<address>")+9, tmpAddress.indexOf("</address>")).trim();
				 
				 ////////////////////////////////// 사진요청
				  org.jsoup.nodes.Document doc1=
					Jsoup.connect("http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA40/HRDPOA40_2.jsp?authKey="+key+"&returnType=XML&outType=2&srchTrprId="+datas.get(i).select("trprId").toString().substring(9, 28).trim()+"&srchTrprDegr=1")
					.timeout(80000).maxBodySize(10*1024*1024).get();
				 ///////////////////////////////////	 
		 		 
				  if(!doc1.select("filePath").toString().equals("")){
					dto.setImg(doc1.select("filePath").toString().substring(10, 94).trim());
				  }else{
					dto.setImg("a");		
				  }  
				  dto.setTitle(title);
				  dto.setSubTitle(subTitle);
				  dto.setAddress(address);
				  dto.setScore(service.getScore(subTitle));
				  count++;
				  list.add(dto);
			}
		}//for
		long endTime = System.nanoTime();
		System.out.println("출력 과정수 : "+count);
		System.out.println("전체 리스트 출력에 걸린시간(s) : " + (endTime - initTime)/1000000000);
		model.addAttribute("list", list);	
		return "home";
	}
}
