package com.demo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.Dept;
import com.demo.entity.Emp;
import com.demo.entity.User;
import com.demo.repository.DeptRepository;
import com.demo.repository.EmpRepository;
import com.demo.repository.UserRepository;

@RestController
@RequestMapping("/boot")
public class HelloController {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@PersistenceContext
    protected EntityManager entityManager;
	
	@Autowired
    private DeptRepository deptRepository;
	@Autowired
    private EmpRepository empRepository;
	@Autowired
    private UserRepository userRepository;
	
    @RequestMapping("/addUser1")
    public String index(){
        
        String sql = "insert into scott.T_USER values(1,'zhangsan')";
        jdbcTemplate.execute(sql);
        System.out.println();
        System.out.println("执行完成");
        
        
        return "hello spring boot";
    }
    
    
    
    
    
    
	@RequestMapping("/queryDeptList")
	public JSONObject hello() {
		JSONObject result = new JSONObject();
		String sql="select deptno,dname,loc from scott.dept";
		RowMapper<Dept> rowMapper=new BeanPropertyRowMapper<Dept>(Dept.class);
		List<Dept> users= jdbcTemplate.query(sql, rowMapper);
		for (Dept user : users) {
		    System.out.println(user);
		    
		}
		result.put("depts", users);
		return result;

	}
	
	@RequestMapping("/queryEmpList")
	public JSONObject queryEmpList() {
		JSONObject result = new JSONObject();
		//方法一
		String sql="select * from scott.emp";
		RowMapper<Emp> rowMapper=new BeanPropertyRowMapper<Emp>(Emp.class);
		List<Emp> users= jdbcTemplate.query(sql, rowMapper);
		for (Emp user : users) {
		    System.out.println(user);
		    
		}
		System.out.println();
		//方法二
		List<Emp> emps = empRepository.findAll();
		for (Emp emp : emps) {
		    System.out.println(emp);
		    
		}
		result.put("empList", users);
		result.put("========", "========");
		result.put("emps", emps);
		return result;

	}
	
	@RequestMapping("/queryDeptByDeptno")
	public JSONObject queryDeptByDeptno() {
		JSONObject result = new JSONObject();
		Dept dept = deptRepository.findByDeptno(10);
		result.put("dept", dept);
		System.out.println(dept);
		return result;

	}
	
	@RequestMapping("/addUser")
	public JSONObject addUser() {
		JSONObject result = new JSONObject();
		User user = new User();
		user.setName("王五");
		userRepository.saveAndFlush(user);
		return result;

	}
	
	@RequestMapping("/addDept")
	public JSONObject addDept() {
		JSONObject result = new JSONObject();
		Dept dept = new Dept();
		dept.setDname("海外部");
		dept.setLoc("浦东新区");
		deptRepository.saveAndFlush(dept);
		result.put("dept", dept);
		return result;

	}

}