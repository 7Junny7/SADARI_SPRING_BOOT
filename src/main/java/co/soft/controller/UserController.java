package co.soft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import co.soft.domain.UserInfoBean;
import co.soft.persistence.UserRepository;
import co.soft.service.UserService;

@SessionAttributes("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	//회원가입 페이지로 이동
	@RequestMapping("/join")
	public String joinView(UserInfoBean user) {
		return "/user/join";
	}
	
	//회원가입 창에서 오류가 있을경우 false 페이지로, 회원가입 성공할 경우 DB 저장 후 success 페이지로 이동
	@PostMapping("/join")
	public String join(@Valid UserInfoBean user,BindingResult result) {
	
	if(result.hasErrors()) {
		return "/user/false";
	}
	userService.insertUser(user	);
	return "/user/join_success";
	}
	
	// 마이페이지로 이동
	@RequestMapping("/getUserInfo")	
	public String getUserInfo(Model model,UserInfoBean user) {
		model.getAttribute("user");
		return "userinfo/userinfo";
	}
	
	// 로그인 페이지로 이동
	@RequestMapping("/login")
	public String loginView(UserInfoBean user) {
		return "/user/login";
	}

	//로그인 창에서 오류가 있을 경우 loginFail로 이동, 로그인 실패할 경우 loginFail로 이동, 
	//로그인 성공할 경우 SessionAttributes에 정보 저장 후 home페이지로 이동
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
	 
	// loginFail 페이지로 이동
	@RequestMapping("/loginFail")
	public String loginFail() {
		return "/user/loginFail";
	}
	
	// logout 세션 정보 삭제 후 login으로 이동
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:login";		
	}
	
	// userinfo_modify 페이지로 이동
	@RequestMapping("/userUpdate")
	public String userUpdateView(Model model) {
		model.getAttribute("user");
		return "/userinfo/userinfo_modify";
	}
	
	// user 정보 DB에 업데이트 후  modify_success 페이지로 이동
	@PostMapping("/userUpdate")
	public String userUpdate(Model model,UserInfoBean user) {
		userService.updateUser(user);
		model.addAttribute("user",user);
		return "/userinfo/userinfo_modify_Success";
	}
	
	@Autowired
	private UserRepository userRepo;
	
	// 아이디 중복 검사용
	@ResponseBody
	 @RequestMapping(value="/idCheck", method = RequestMethod.POST)
	    public String id_check(String id, Model model) {
	        String str;
	        if (userRepo.findById(id).isPresent()==false) {
	            str= "YES";
	        } else {
	            str= "NO";
	        }
	        
	        model.addAttribute("str", str);
	        return str;
	    }
} 
