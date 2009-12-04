package org.kyerp.domain.org;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.kyerp.domain.print.Presswork;

@Entity
// 默认情况下表名称是根据实体类名称创建的，name可以修改表名称
// @Table(name="person_table")
public class Employee {
	@Id
	@GeneratedValue
	private Long					id;
	private String					realname;
	@OneToOne
	private User					user;
	@OneToMany(mappedBy = "moneyInputMan")
	private final Set<Presswork>	pressworskForMoneyInput	= new HashSet<Presswork>();
	@OneToMany(mappedBy = "moneyGetMan")
	private final Set<Presswork>	pressworksForMoneyGet	= new HashSet<Presswork>();

	public Employee() {
	}

	// 主键生成策略@GeneratedValue(strategy=GenerationType.AUTO),
	// 值为AUTO表示根据数据库由Hibernate自动选择生成策略，也可以省略写@GeneratedValue
	// 值为IDENTITY表示主键自增长
	// 值为SEQUENCE表示主键采用序列的方式
	// 值为TABLE各个数据库都通用，但效率较低

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public Set<Presswork> getPressworksForMoneyGet() {
		return pressworksForMoneyGet;
	}

	public Set<Presswork> getPressworskForMoneyInput() {
		return pressworskForMoneyInput;
	}

	public String getRealname() {
		return realname;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", realname=" + realname + ", user="
				+ user + "]";
	}

}
