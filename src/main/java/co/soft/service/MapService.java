package co.soft.service;

import java.util.List;

import co.soft.domain.MapInfoBean;

public interface MapService {

	List<MapInfoBean> getMapList(MapInfoBean mapinfo);

	void insertMap(MapInfoBean mapinfo);

	MapInfoBean getMap(MapInfoBean mapinfo);

	void updateMap(MapInfoBean mapinfo);
	
	void updateMapLike(MapInfoBean mapinfo);

	void deleteMap(MapInfoBean mapinfo);
}
