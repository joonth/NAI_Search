package com.hk.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.web.daos.CommentDao;
import com.hk.web.dtos.InfoDto;
import com.hk.web.dtos.SearchDto;
import com.hk.web.dtos.commentDto;
import com.hk.web.services.InfoService;
import com.hk.web.services.SearchService;
import com.hk.web.utils.SearchUtil;


@Controller
public class HomeController {
	
	
	Map<String,SearchDto> titleIdMapper = new HashMap<>();
	
	@Autowired		//api로 얻어온 xml data의 tag를 없애는 util.
	SearchUtil util;
	@Autowired
	CommentDao commentDao;
	@Autowired
	InfoDto infoDto;
	@Autowired
	SearchService Sserv;
	@Autowired
	InfoService Iserv;
	@Value("#{apiKey['key']}")		//github에 apikey를 올리지 않기 위해서 key를 따로 저장후 받아옴.
	private String key;
	
	
	List<SearchDto> list = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	int count = 0;		//출력되는 과정수를 나타내기 위한 변수.
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		if(list.size() ==0) {		// 과정정보 list를 구하는 for문을 한번만 돌리기 위한 if문.		
			logger.info("학원리스트 출력",locale);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	
		    Calendar c1 = Calendar.getInstance();
		    String strToday = sdf.format(c1.getTime());
			
			org.jsoup.nodes.Document doc=
			Jsoup.connect("http://www.hrd.go.kr/hrdp/api/apieo/APIEO0101T.do?srchTraEndDt=20191231&pageSize=1500&srchTraStDt="+strToday+"&sortCol=TOT_FXNUM&authKey="+key+"&sort=ASC&returnType=XML&outType=1&pageNum=1&srchTraPattern=2&srchPart=-99&apiRequstPageUrlAdres=/jsp/HRDP/HRDPO00/HRDPOA11/HRDPOA11_1.jsp&apiRequstIp=112.221.224.124")
			.timeout(60000).maxBodySize(10*1024*1024).get();
			Elements datas = doc.select("scn_list");
			
			for(int i = 0; i < datas.size(); i++){
				String title = util.tagTrim(datas.get(i).select("title"), "title");
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
					 SearchDto searchDto = new SearchDto();
					 searchDto.setTitle(title);
					 searchDto.setSubTitle(util.tagTrim(datas.get(i).select("subtitle"), "subtitle"));
					 searchDto.setAddress(util.tagTrim(datas.get(i).select("address"), "address"));
					 searchDto.setScore(Sserv.getScore(util.tagTrim(datas.get(i).select("subtitle"), "subtitle")));
					 searchDto.setTrprId(util.tagTrim(datas.get(i).select("trprid"), "trprid"));
					
					 titleIdMapper.put(util.tagTrim(datas.get(i).select("subtitle"), "subtitle"), searchDto);
					 list.add(searchDto);
				}
				 count++;	
			}//for
			System.out.println("출력 과정수 : "+count);	
		} // if(
		model.addAttribute("key", key);
		model.addAttribute("map", titleIdMapper);
		model.addAttribute("list", list);	
		return "home";
	}
	
	
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Locale locale, Model model, String subTitle) throws IOException {
		
		 org.jsoup.nodes.Document docInfo=
					Jsoup.connect("http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA40/HRDPOA40_2.jsp?authKey="+key+"&returnType=XML&outType=2&srchTrprId="+titleIdMapper.get(subTitle).getTrprId()+"&srchTrprDegr=1")
					.timeout(80000).maxBodySize(10*1024*1024).get();

		infoDto.setImg(titleIdMapper.get(subTitle).getImg());
		infoDto.setAddr1(util.tagTrim(docInfo.select("addr1"),"addr1"));
		infoDto.setAddr2(util.tagTrim(docInfo.select("addr2"),"addr2"));
		infoDto.setHpaddr(util.tagTrim(docInfo.select("hpaddr"),"hpaddr"));
		infoDto.setInonm(util.tagTrim(docInfo.select("inonm"),"inonm"));
		infoDto.setTrprchaptel(util.tagTrim(docInfo.select("trprchaptel"),"trprchaptel"));
		infoDto.setTrprnm(util.tagTrim(docInfo.select("trprnm"),"trprnm"));
		model.addAttribute("infoDto", infoDto);
		
		List<commentDto> commentList = new ArrayList<>();
		commentList = Iserv.getComment(subTitle);
		if(!(commentList.size()==0)) {
			model.addAttribute("list", commentList);
		}else {
			model.addAttribute("list", null);
		}
		
		return "info";
	}

	
	
	
	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public String addComment(Locale locale, Model model,commentDto dto) throws IOException {
		//inonm
		System.out.println("##########11" + dto.getAc_name());
		String tmpAc_name = dto.getAc_name();
		String ac_name = tmpAc_name.substring(tmpAc_name.indexOf("<inonm>")+8, tmpAc_name.indexOf("</inonm>")).trim();
		dto.setAc_name(ac_name);
		org.jsoup.nodes.Document docInfo=
					Jsoup.connect("http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA40/HRDPOA40_2.jsp?authKey="+key+"&returnType=XML&outType=2&srchTrprId="+titleIdMapper.get(ac_name).getTrprId()+"&srchTrprDegr=1")
					.timeout(80000).maxBodySize(10*1024*1024).get();
		
		 
		infoDto.setImg(titleIdMapper.get(ac_name).getImg());
		infoDto.setAddr1(docInfo.select("addr1").toString());
		infoDto.setAddr2(docInfo.select("addr2").toString());
		infoDto.setHpaddr(docInfo.select("hpAddr").toString());
		infoDto.setInonm(docInfo.select("inoNm").toString());
		infoDto.setTrprchaptel(docInfo.select("trprChapTel").toString());
		infoDto.setTrprnm(docInfo.select("trprNm").toString());
		model.addAttribute("infoDto", infoDto);
	
		
		commentDao.addComment(dto);
		List<commentDto> commentList = new ArrayList<>();
		commentList = Iserv.getComment(ac_name);
		if(!(commentList.size()==0)) {
			model.addAttribute("list", commentList);
		}else {
			model.addAttribute("list", null);
		}
		return "info";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/getImg", method = RequestMethod.POST)
	public Map<String, SearchDto> getImg(Locale locale, Model model,String[] acTitle) throws IOException {
		Map<String,SearchDto> map = new HashMap<>();
		 String text = "";

		 for(int i=0; i<acTitle.length; i++) {
			 text = acTitle[i];
			
			 SearchDto searchDto = new SearchDto();
			 searchDto.setTrprId(titleIdMapper.get(text).getTrprId());

		org.jsoup.nodes.Document docImg=
				Jsoup.connect("http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA40/HRDPOA40_2.jsp?authKey="+key+"&returnType=XML&outType=2&srchTrprId="+searchDto.getTrprId()+"&srchTrprDegr=1")
				.timeout(80000).maxBodySize(10*1024*1024).get();
		if(!docImg.select("filePath").toString().equals("")){
			searchDto.setImg(docImg.select("filePath").toString().substring(10, 94).trim());
			titleIdMapper.put(text, searchDto);
		  }else{
			searchDto.setImg("http://sign.kedui.net/rtimages/n_sub/no_detail_img.gif");
		  }
		map.put(text, searchDto);
		 }
		return map;
	}
	
}
