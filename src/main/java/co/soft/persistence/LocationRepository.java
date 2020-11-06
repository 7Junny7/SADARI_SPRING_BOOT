package co.soft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.LocationInfoBean;

public interface LocationRepository extends JpaRepository<LocationInfoBean, Long>{
	//푸드 타입 기반으로 리스트불러오기
	List<LocationInfoBean> findAllByFoodType(String foodtype);
	//board 인덱스 기반으로 리스트불러오기
	LocationInfoBean findByBoardidx(Long idx);
}
