package co.soft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.LocationInfoBean;

public interface LocationRepository extends JpaRepository<LocationInfoBean, Long>{

	List<LocationInfoBean> findAllByFoodType(String foodtype);
}
