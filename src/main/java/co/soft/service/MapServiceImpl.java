package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.soft.domain.MapInfoBean;
import co.soft.persistence.MapRepository;

public class MapServiceImpl implements MapService{
	
	@Autowired
	private MapRepository mapRepo;

	@Override
	public List<MapInfoBean> getMapList(MapInfoBean mapinfo) {
		return (List<MapInfoBean>) mapRepo.findAll();
	}

	@Override
	public void insertMap(MapInfoBean mapinfo) {
		mapRepo.save(mapinfo);
	}

	@Override
	public MapInfoBean getMap(MapInfoBean mapinfo) {
		return mapRepo.findById(mapinfo.getRestaurant()).get();
	}

	@Override
	public void updateMap(MapInfoBean mapinfo) { //미사용
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMapLike(MapInfoBean mapinfo) { //좋아요 수정
		MapInfoBean mapBoard=mapRepo.findById(mapinfo.getRestaurant()).get();
		
		mapBoard.setFoodLikeScore(mapinfo.getFoodLikeScore()); //좋아요 점수 수정
		mapBoard.setFoodLikePerson(mapinfo.getFoodLikePerson()); //투표 인원수 수정
		mapRepo.save(mapBoard);
		
	}

	@Override
	public void deleteMap(MapInfoBean mapinfo) {
		mapRepo.deleteById(mapinfo.getRestaurant());
	}

}
