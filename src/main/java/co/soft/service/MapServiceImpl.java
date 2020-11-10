package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.MapInfoBean;
import co.soft.persistence.MapRepository;

@Service
public class MapServiceImpl implements MapService{
	
	@Autowired
	private MapRepository mapRepo;

	public List<MapInfoBean> getMapList(MapInfoBean mapinfo) {
		return (List<MapInfoBean>) mapRepo.findAll();
	}

	public void insertMap(MapInfoBean mapinfo) {
		mapRepo.save(mapinfo);
	}

	public MapInfoBean getMap(Long boardidx) {
		return (MapInfoBean) mapRepo.findByBoardidx(boardidx);
	}

	@Override
	public void updateMap(MapInfoBean mapinfo) { //미사용
		// TODO Auto-generated method stub
		
	}

	public void updateMapLike(MapInfoBean mapinfo) { //미사용
//		MapInfoBean mapBoard=mapRepo.findById(mapinfo.getRestaurant()).get();
//		
//		mapBoard.setFoodLikeScore(mapinfo.getFoodLikeScore()); //좋아요 점수 수정
//		mapBoard.setFoodLikePerson(mapinfo.getFoodLikePerson()); //투표 인원수 수정
//		mapRepo.save(mapBoard);
		
	}

	
	public void deleteMap(MapInfoBean mapinfo) {
		mapRepo.deleteById(mapinfo.getBoardidx());
	}

	//게시물 리스트 불러오기 by 메뉴 
	public List<MapInfoBean> getMapListByLocation(String location) {
		return (List<MapInfoBean>) mapRepo.findAllByLocation(location);
	}
	
	public List<MapInfoBean> locList(Long idx) {
		return (List<MapInfoBean>) mapRepo.findByBoardidx(idx);
	}
	
}
