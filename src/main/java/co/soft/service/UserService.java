package co.soft.service;

import java.util.List;


import org.springframework.stereotype.Service;

import co.soft.domain.MenuInfoBean;
import co.soft.domain.UserInfoBean;
public interface UserService {
	//회원정보입력
	void insertUser(UserInfoBean userinfo);
	
	//회원정보불러오기
	List<UserInfoBean> selectUserAll(UserInfoBean userinfo);
	
	//아이디 중복확인
	boolean selectUserOne(UserInfoBean userinfo);
	
	//회원삭제
	void deleteUser(UserInfoBean userinfo);
	
	//회원정보수정
	void updateUser(UserInfoBean userinfo);
	
	UserInfoBean getUserInfo(UserInfoBean userinfo);

}
