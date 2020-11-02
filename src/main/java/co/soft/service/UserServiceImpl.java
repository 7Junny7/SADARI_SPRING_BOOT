package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.MenuInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	// 회원정보 입력
	@Override
	public void insertUser(UserInfoBean userinfo) {
		userRepo.save(userinfo);
	}

	// 아이디 중복확인
	@Override
	public boolean selectUserOne(UserInfoBean userinfo) {
		UserInfoBean idx = userRepo.findById(userinfo.getUseridx()).get();

		if (idx.getUserId().equals(userinfo.getUserId())) {
			return false;
		}
		return true;
	}

	// 회원정보삭제
	@Override
	public void deleteUser(UserInfoBean userinfo) {
		userRepo.findById(userinfo.getUseridx()).get();
	}

	// 회원정보 불러오기
	@Override
	public List<UserInfoBean> selectUserAll(UserInfoBean userinfo) {
		return (List<UserInfoBean>) userRepo.findAll();
	}

	// 회원정보수정
	@Override
	public void updateUser(UserInfoBean userinfo) {
		UserInfoBean userIdx = userRepo.findById(userinfo.getUseridx()).get();
	}

	@Override
	public List<UserInfoBean> getUserInfoList(UserInfoBean userinfo) {
		return (List<UserInfoBean>) userRepo.findAll();
	}

	@Override
	public UserInfoBean getUser(UserInfoBean userinfo) {
		return userRepo.findById(userinfo.getUseridx()).get();
	}

	public UserInfoBean getUserInfo(UserInfoBean userinfo) {
		return null;
	}//?????????????????????????????????????????이게뭐람..
	


}
