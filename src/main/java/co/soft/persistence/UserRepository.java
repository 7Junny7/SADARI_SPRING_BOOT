package co.soft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.UserInfoBean;

public interface UserRepository extends JpaRepository<UserInfoBean, Long>{

}
