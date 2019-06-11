package com.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */
 
@Entity
@Table(name="EMP")
public class Emp {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_emp")
	@SequenceGenerator(name="seq_emp",sequenceName="seq_emp_id",allocationSize=1,initialValue=1)
	private Integer empno;
	
	@ManyToOne
	@JoinColumn(name="deptno")
	private Dept dept;
	
	@Column(name="ename")
	private String ename;
	
	@Transient
	private String job;
	
	@Transient
	private Integer mgr;
	
	@Column(name="hiredate")
	private Date hiredate;
	
	@Transient
	private Double sal;
	
	@Transient
	private Double comm;

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", dept=" + dept + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr
				+ ", hiredate=" + hiredate + ", sal=" + sal + ", comm=" + comm + "]";
	}
 
	
}