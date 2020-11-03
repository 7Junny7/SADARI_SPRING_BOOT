package co.soft.service;

import java.util.List;

import co.soft.domain.LocationInfoBean;

public interface LocationService {

	//리스트불러오기
	List<LocationInfoBean> getLocationList(LocationInfoBean locinfo);
	//글에대한정보입력
	void insertLocation(LocationInfoBean locinfo);
	//게시물 한개 불러오기
	LocationInfoBean getLocation(LocationInfoBean locinfo);
	//게시물 수정
	void updateLocation(LocationInfoBean locinfo);
	//게시물 좋아요/투표인원수 수정
	void updateLocationLike(LocationInfoBean locinfo);
	//게시물 삭제
	void deleteLocation(LocationInfoBean locinfo);

}