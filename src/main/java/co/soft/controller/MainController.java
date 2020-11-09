package co.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.soft.domain.MapInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.MapService;

@Controller
@SessionAttributes("user")
public class MainController {

	@Autowired
	private MapService mapService;
 
	@RequestMapping("/") // OK
	public String start() {
		return "start";
	} 
	
	
	@RequestMapping("/home") // OK
	public String getMenuList(Model model, MapInfoBean mapinfo, UserInfoBean user) {
//	   if (user.getUserId() == null) { //로그인 안하면 작성 못함
//	         return "redirect:login";
//	    }
		String keyword = "종로구";
		String key [] = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "금천구", "구로구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};

		List<MapInfoBean> mapList = mapService.getMapList(mapinfo);
		model.addAttribute("mapList", mapList);
		model.addAttribute("userinfo", user);
		model.addAttribute("loginUserBean", user);
	    model.addAttribute("key",key);
		return "home";
	}

}
