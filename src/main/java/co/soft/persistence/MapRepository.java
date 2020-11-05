package co.soft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.MapInfoBean;

public interface MapRepository extends JpaRepository<MapInfoBean, String>{

	List<MapInfoBean> findAllByLocation(String location);
}
