package co.soft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.MapInfoBean;

public interface MapRepository extends JpaRepository<MapInfoBean, Long>{

	// location 정보 기반으로 모든 정보 DB에서 수집하여 Bean에 저장 후 List화
	List<MapInfoBean> findAllByLocation(String location);
	
	// idx(boardidx) 기준으로 일치하는 정도 DB에서 수집하여 Bean에 저장 후 List화
	List<MapInfoBean> findByBoardidx(Long idx);
	
}
