package co.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.soft.domain.LocationInfoBean;
import co.soft.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuservice;
	
	@RequestMapping("/getboard")
	public String getboard(Model model, LocationInfoBean locinfo) {
		List<LocationInfoBean> menuList=locationService.getLocationList(locinfo);
		model.addAttribute("boardList", menuList);
		return "getboard";
	}
	
}
