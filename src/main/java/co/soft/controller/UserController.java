package co.soft.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.soft.domain.UserInfoBean;
import co.soft.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Lazy
	private UserService userService;

	@Resource(name = "loginUserBean")
	@Lazy
	private UserInfoBean loginuserBean;
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserInfoBean joinUserBean) {
		return "/user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUSerBean") UserInfoBean joinUserBean,BindingResult result) {
//	
//	if(result.hasErrors()) {
//		return "/user/false";
//	}
		
	userService.insertUser(joinUserBean);
	return "redirct:/user/join_success";
	}
}
