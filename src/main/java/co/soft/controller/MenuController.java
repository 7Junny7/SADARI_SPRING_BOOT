package co.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.soft.domain.MenuInfoBean;
import co.soft.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	@Lazy
	private MenuService menuService;
	
	@RequestMapping("/getmenuList")
	public String getmenuList(Model model, MenuInfoBean menuinfo) {
//		List<MenuInfoBean> menuList=menuService.getMenuList(menuinfo);
//		model.addAttribute("menuList", menuList);
		return "menu";
	}
	
}
