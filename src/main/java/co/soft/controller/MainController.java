package co.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.soft.domain.MapInfoBean;
import co.soft.domain.MenuInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.MapService;
import co.soft.service.MenuService;

@Controller
@SessionAttributes("UserInfoBean")
public class MainController {

	@Autowired
	private MapService mapService;

	@GetMapping("/start") // OK
	public String start(Model model, MenuInfoBean menuinfo, UserInfoBean userinfo) {
//		List<MenuInfoBean> menuList = menuService.getMenuList(menuinfo);
//		model.addAttribute("menuList", menuList);
		model.addAttribute("loginUserBean", userinfo);
		return "start";
	}
	
	@PostMapping("/home") // OK
	public String getMenuList(Model model, MapInfoBean mapinfo, UserInfoBean userinfo) {
		List<MapInfoBean> mapList = mapService.getMapList(mapinfo);
		model.addAttribute("mapList", mapList);
		
		model.addAttribute("loginUserBean", userinfo);
		return "home";
	}

}
