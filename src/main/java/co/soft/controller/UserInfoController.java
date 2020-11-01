package co.soft.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.soft.domain.UserInfoBean;

@Controller
public class UserInfoController {

	@RequestMapping("/getUserInfoList")
	public String getUserInfoList(Model model, UserInfoBean userinfo) {
		return "userinfo";
	}
	
}
