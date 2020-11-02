package co.soft.interceptor;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import co.soft.domain.MenuInfoBean;


@Component
public class TopMenuInterceptor implements HandlerInterceptor{

	@Autowired
	private co.soft.service.MenuService MenuService;
	
//	@Resource(name = "loginUserBean")	
//	@Lazy
//	private UserBean loginUserBean;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MenuInfoBean menuinfo=new MenuInfoBean();
		List<MenuInfoBean> topMenuList = MenuService.getMenuList(menuinfo);
		request.setAttribute("topMenuList", topMenuList);
//		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
