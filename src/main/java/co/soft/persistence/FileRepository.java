package co.soft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import co.soft.domain.FileInfoBean;

public interface FileRepository extends JpaRepository<FileInfoBean, Long>{

}
