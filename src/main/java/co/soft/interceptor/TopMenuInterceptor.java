package co.soft.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import co.soft.domain.MenuInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.MenuService;
import jdk.internal.org.jline.utils.Log;

public class TopMenuInterceptor implements HandlerInterceptor{

	@Resource(name = "loginUserBean")
	@Lazy
	private UserInfoBean loginUserBean;
	@Autowired
	private MenuService menuService;
	
//	public TopMenuInterceptor(co.soft.service.MenuService MenuService, UserInfoBean loginUserBean) {
//		this.menuService = MenuService;
//		this.loginUserBean = loginUserBean;
//	}	// 생성자로 넘겨서 빈 주입 받음
//		// 인터셉터는 자동주입을 통해 bean 주입 받지 못한다. (@Autowired 불가능)
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		MenuInfoBean menuinfo=new MenuInfoBean();
//		List<MenuInfoBean> topMenuList = menuService.getMenuList(menuinfo);
//		request.setAttribute("topMenuList", topMenuList);
//		request.setAttribute("loginUserBean", loginUserBean);
		
		Log.info("AC8");
		
		return true;
	}
}
