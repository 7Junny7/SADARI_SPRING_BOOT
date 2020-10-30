package co.soft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.LocationInfoBean;

public interface LocationRepository extends JpaRepository<LocationInfoBean, Long>{

}
