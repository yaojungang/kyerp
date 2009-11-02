package org.kyerp.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
// 默认情况下表名称是根据实体类名称创建的，name可以修改表名称
// @Table(name="person_table")
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private User user;
	private String realname;
	
	public Employee() {}
	
	// 主键生成策略@GeneratedValue(strategy=GenerationType.AUTO),
	// 值为AUTO表示根据数据库由Hibernate自动选择生成策略，也可以省略写@GeneratedValue
	// 值为IDENTITY表示主键自增长
	// 值为SEQUENCE表示主键采用序列的方式
	// 值为TABLE各个数据库都通用，但效率较低
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}
	
}
