package co.soft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.LocationInfoBean;
import co.soft.persistence.LocationRepository;


@Service
public class LocationServiceImpl implements LocationService {
 
	@Autowired
	private LocationRepository locRepo;
 
	//리스트 불러오기
	public List<LocationInfoBean> getLocationList(LocationInfoBean locinfo) {
		return (List<LocationInfoBean>) locRepo.findAll();
	}
	
	//글에대한정보 입력
	public void insertLocation(LocationInfoBean locinfo) {
		locRepo.save(locinfo);
	}
	//게시물 한개 불러오기
	public LocationInfoBean getLocation(LocationInfoBean locinfo) {
		return locRepo.findById(locinfo.getBoardidx()).get();
	}
	//게시물 수정
	public void updateLocation(LocationInfoBean locinfo) { //글내용 수정
		LocationInfoBean locBoard = locRepo.findById(locinfo.getBoardidx()).get();
		
				locBoard.setDate(locinfo.getDate()); //원하는 날짜 수정
				locBoard.setTime(locinfo.getTime()); //원하는 시간 수정
				locBoard.setWantWho(locinfo.getWantWho()); //참석 인원 수정
				locBoard.setFoodComment(locinfo.getFoodComment()); //글 내용 수정
				locRepo.save(locBoard);		
	}
	
	//게시물 좋아요/투표인원수 수정
	public void updateLocationLike(LocationInfoBean locinfo) { // 좋아요 수정
		LocationInfoBean locBoard = locRepo.findById(locinfo.getBoardidx()).get();
		
		locBoard.setFoodLikeScore(locinfo.getFoodLikeScore()); //좋아요 점수 수정
		locBoard.setFoodLikePerson(locinfo.getFoodLikePerson()); //투표 인원수 수정
//		locRepo.save(locBoard);		
	}
	//참석 수정
	public void updateLocationApply(LocationInfoBean locinfo) {
		LocationInfoBean locBoard = locRepo.findById(locinfo.getBoardidx()).get();
		
		locBoard.setLikeup(locinfo.getLikeup()); //참여자수
		locBoard.setUserId(locinfo.getUserId()); //참여자id
		locRepo.save(locBoard);
	}
	
	//게시물 삭제
	public void deleteLocation(LocationInfoBean locinfo) {
		locRepo.deleteById(locinfo.getBoardidx());
	}

	//게시물 리스트 불러오기 by 메뉴 
	public List<LocationInfoBean> getLocationListByFoodtype(String foodtype) {
		return (List<LocationInfoBean>) locRepo.findAllByFoodType(foodtype);
	}
	
	
	//게시물 리스트 boardidx로 불러오기
	public LocationInfoBean getLocation(Long idx) {
		LocationInfoBean locinfo = new LocationInfoBean();
		Optional<LocationInfoBean> loc = Optional.ofNullable(locRepo.findById(idx).orElse(null));
		if(loc.isPresent()) {
			locinfo = locRepo.findById(idx).get();
		}else {
			locinfo = null;
		}
		return locinfo;
	}
}
