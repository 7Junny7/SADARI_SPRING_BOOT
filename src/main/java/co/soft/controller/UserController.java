package co.soft.controller;

import javax.annotation.Resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import co.soft.domain.UserInfoBean;
import co.soft.service.UserService;

@SessionAttributes("UserInfoBean")
@Controller
public class UserController {

	@Autowired
	@Lazy
	private UserService userService;

//	@Resource(name = "loginUserBean")
//	@Lazy
//	private UserInfoBean loginuserBean;
	
	@RequestMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserInfoBean joinUserBean) {
		return "/user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUSerBean") UserInfoBean joinUserBean,BindingResult result) {
	
	if(result.hasErrors()) {
		return "/user/false";
	}
		
	userService.insertUser(joinUserBean);
	return "/user/join_success";
	}
	
	@RequestMapping("/getUserInfoList")
	public String getUserInfoList(Model model, UserInfoBean userinfo) {
		return "userinfo";
	}
	
	@RequestMapping("/login")
	public String loginView(@ModelAttribute("loginUserBean") UserInfoBean user) {
		return "/user/login";
	}

	@PostMapping("/login_pro")
	public String login(@ModelAttribute("loginUserBean") UserInfoBean user, Model model) {
		UserInfoBean findUser = userService.getUserInfo(user);

		if (findUser != null && findUser.getUserPassword().equals(user.getUserPassword())) {
			model.addAttribute("member", findUser);
			return "redirect:home";
		} else {
			return "redirect:loginFail";
		}
	}
	
	@RequestMapping("/loginFail")
	public String loginFail() {
		return "/user/loginFail";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:getMenuList";		
	}
	
}
