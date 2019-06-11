package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Dept;
@Repository
public interface DeptRepository extends JpaRepository<Dept, Long>{
	/**
     * 根据年纪查询用户
     * @param age
     * @return
     */
	Dept findByDeptno(Integer deptno);
}
