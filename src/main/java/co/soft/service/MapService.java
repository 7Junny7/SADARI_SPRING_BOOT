package co.soft.service;

import java.util.List;

import co.soft.domain.MapInfoBean;

public interface MapService {

	//리스트 불러오기 for Map
	List<MapInfoBean> getMapList(MapInfoBean mapinfo);
	
	//게시물 리스트 불러오기 by 메뉴 
	List<MapInfoBean> getMapListByLocation(String location);


	void insertMap(MapInfoBean mapinfo);

	MapInfoBean getMap(MapInfoBean mapinfo);

	void updateMap(MapInfoBean mapinfo);
	
	void updateMapLike(MapInfoBean mapinfo);

	void deleteMap(MapInfoBean mapinfo);
	
	List<MapInfoBean> locList(Long idx);
	
}
