package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.LocationInfoBean;
import co.soft.persistence.LocationRepository;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locRepo;

	public List<LocationInfoBean> getLocationList(LocationInfoBean locinfo) {
		return (List<LocationInfoBean>) locRepo.findAll();
	}

	public void insertLocation(LocationInfoBean locinfo) {
		locRepo.save(locinfo);
	}

	public LocationInfoBean getLocation(LocationInfoBean locinfo) {
		return locRepo.findById(locinfo.getBoardidx()).get();
	}

	public void updateLocation(LocationInfoBean locinfo) { //글내용 수정
		LocationInfoBean locBoard = locRepo.findById(locinfo.getBoardidx()).get();
		
				locBoard.setDate(locinfo.getDate()); //원하는 날짜 수정
				locBoard.setTime(locinfo.getTime()); //원하는 시간 수정
				locBoard.setWantWho(locinfo.getWantWho()); //같이 먹고 싶은 사람 수정
				locBoard.setFoodComment(locinfo.getFoodComment()); //글 내용 수정
//				locBoard.setFoodPicture(locinfo.getFoodPicture()); //음식 사진 수정
				locRepo.save(locBoard);		
	}
	
	public void updateLocationLike(LocationInfoBean locinfo) { // 좋아요 수정
		LocationInfoBean locBoard = locRepo.findById(locinfo.getBoardidx()).get();
		
		locBoard.setFoodLikeScore(locinfo.getFoodLikeScore()); //좋아요 점수 수정
		locBoard.setFoodLikePerson(locinfo.getFoodLikePerson()); //투표 인원수 수정
//		locRepo.save(locBoard);		
	}

	public void deleteLocation(LocationInfoBean locinfo) {
		locRepo.deleteById(locinfo.getBoardidx());
		
	}

}
