package co.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.soft.domain.MapInfoBean;
import co.soft.domain.MenuInfoBean;
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
		List<MapInfoBean> mapList = mapService.getMapList(mapinfo);
		model.addAttribute("mapList", mapList);
		model.addAttribute("userinfo", user);
		model.addAttribute("loginUserBean", user);
		return "home";
	}

}
