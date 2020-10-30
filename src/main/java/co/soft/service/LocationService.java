package co.soft.service;

import java.util.List;

import co.soft.domain.LocationInfoBean;

public interface LocationService {

	List<LocationInfoBean> getLocationList(LocationInfoBean locinfo);

	void insertLocation(LocationInfoBean locinfo);

	LocationInfoBean getLocation(LocationInfoBean locinfo);

	void updateLocation(LocationInfoBean locinfo);
	
	void updateLocationLike(LocationInfoBean locinfo);

	void deleteLocation(LocationInfoBean locinfo);

}