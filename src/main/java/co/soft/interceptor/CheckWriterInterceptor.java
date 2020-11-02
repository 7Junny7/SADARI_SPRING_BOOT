package co.soft.interceptor;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import co.soft.domain.LocationInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.MenuService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserInfoBean loginUserBean;
	@Autowired
	private MenuService menuService;
	
//	public CheckWriterInterceptor(UserInfoBean loginUserBean, MenuService menuService) {
//		// TODO Auto-generated constructor stub
//		this.loginUserBean = loginUserBean;
//		this.menuService = menuService;
//	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
//		String str1 = request.getParameter("content_idx");
//		int content_idx = Integer.parseInt(str1);
//		
//		LocationInfoBean locationBean = menuService.getContentInfo(content_idx);
//		
//		if(locationBean.getContent_writer_idx() != loginUserBean.getUser_idx()) {
//			String contextPath = request.getContextPath();
//			response.sendRedirect(contextPath + "/board/not_writer");
//			return false;
//		}
		
		return true;
	}
}












