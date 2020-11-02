package co.soft.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.soft.domain.UserInfoBean;
import co.soft.interceptor.CheckLoginInterceptor;
import co.soft.interceptor.CheckWriterInterceptor;
import co.soft.interceptor.TopMenuInterceptor;
import co.soft.service.MenuService;

@Configuration
//@EnableWebMvc
//@ComponentScan("co.soft.controller")
//@ComponentScan("co.soft.service")
//@ComponentScan("co.soft.persistence")
public class WebMvcConfiguration implements WebMvcConfigurer {

//	@Autowired
//	private MenuService menuService;
//	@Resource(name = "loginUserBean")
//	private UserInfoBean loginUserBean;
	
//	@Autowired
//	TopMenuInterceptor menuInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		registry.addInterceptor(new TopMenuInterceptor()).addPathPatterns("/**");

//		WebMvcConfigurer.super.addInterceptors(registry);
//
//		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(menuService, loginUserBean);
//		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
//		reg1.addPathPatterns("/**"); // 모든 요청
//
//		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
//		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
//		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
//		reg2.excludePathPatterns("/board/main");
//
//		CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService);
//		InterceptorRegistration reg3 = registry.addInterceptor(checkWriterInterceptor);
//		reg3.addPathPatterns("/board/modify", "/board/delete");

	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
