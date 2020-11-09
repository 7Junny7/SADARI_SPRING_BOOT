package co.soft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import co.soft.domain.UserInfoBean;
import co.soft.service.UserService;

@SessionAttributes("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping("/join")
	public String joinView(UserInfoBean user) {
		return "/user/join";
	}
	
	@PostMapping("/join")
	public String join(@Valid UserInfoBean user,BindingResult result) {
	
	if(result.hasErrors()) {
		return "/user/false";
	}
		
	userService.insertUser(user	);
	return "/user/join_success";
	}
	
	@RequestMapping("/getUserInfo")	
	public String getUserInfo(Model model,UserInfoBean user) {
		model.getAttribute("user");
		return "userinfo/userinfo";
	}
	
	@RequestMapping("/login")
	public String loginView(UserInfoBean user) {
		
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(UserInfoBean user, Model model,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:loginFail";
		}
		UserInfoBean findUser = userService.getUserInfo(user);
		if (findUser != null && findUser.getUserPassword().equals(user.getUserPassword())) {
			model.addAttribute("user", findUser);
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
		return "redirect:login";		
	}
	
	@RequestMapping("/userUpdate")
	public String userUpdateView(Model model) {
		model.getAttribute("user");
		return "/userinfo/userinfo_modify";
	}
	
	@PostMapping("/userUpdate")
	public String userUpdate(Model model,UserInfoBean user) {
		userService.updateUser(user);
		model.addAttribute("user",user);
		return "/userinfo/userinfo_modify_Success";
	}
	
	
	 @PostMapping("/idCheck")
	    public String id_check(String id) {
	        System.out.println(id);
	        String str = userService.idCheck(id);
	        return str;
	    }
}
