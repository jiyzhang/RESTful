package com.pretendco.ar.repository;


import com.pretendco.ar.entity.ARDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ARDemoRepository extends JpaRepository<ARDemo, Long>, JpaSpecificationExecutor<ARDemo> {
    ARDemo findById(long id);

}
