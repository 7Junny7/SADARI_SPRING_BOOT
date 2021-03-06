package co.soft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		UserInfoBean idx = userRepo.findById(userinfo.getUserId()).get();

		if (idx.getUserId().equals(userinfo.getUserId())) {
			return false;
		}
		return true;
	}

	// 회원정보삭제
	@Override
	public void deleteUser(UserInfoBean userinfo) {
//		userRepo.findById(userinfo.getUseridx()).get();
	}

	// 모든 회원정보 불러오기
	@Override
	public List<UserInfoBean> selectUserAll(UserInfoBean userinfo) {
		return (List<UserInfoBean>) userRepo.findAll();
	}

	// 회원정보수정
	@Override
	public void updateUser(UserInfoBean userinfo) {
		 userRepo.save(userinfo);
	}

	// 1인 회원정보 불러오기
	@Override
	public UserInfoBean getUserInfo(UserInfoBean userinfo) {
		Optional<UserInfoBean> findUser=Optional.of(userRepo.findById(userinfo.getUserId()).get());
		if(findUser.isPresent()) {
			return findUser.get();
		}else {
			return null;
		}
	}
	
	@Override
	public String idCheck(String userId) {
        //System.out.println(userRepo.findById(userId));
		/*
		 * System.out.println(userRepo.findById(userId)); System.out.println(); if
		 * (userRepo.findById(userId).get().getUserId() == null) { return "YES"; } else
		 * { return "NO"; }
		 */
			return userRepo.findById(userId).get().getUserId();
    }
	
}
