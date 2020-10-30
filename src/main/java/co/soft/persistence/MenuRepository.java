package co.soft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.MenuInfoBean;

public interface MenuRepository extends JpaRepository<MenuInfoBean, Long>{

}
