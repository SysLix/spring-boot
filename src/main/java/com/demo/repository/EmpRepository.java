package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.Emp;
@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{
	/**
     * 根据年纪查询用户
     * @param age
     * @return
     */
	@Query(value = "SELECT * FROM emp WHERE deptno = ?1", nativeQuery = true)
	Emp findByDeptno(Integer deptno);
}
