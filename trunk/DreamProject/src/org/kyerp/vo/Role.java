package org.kyerp.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany
	private Set<User> users= new HashSet<User>();
	private String roleName;
	public Role(){}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<User> getUsers() {
		return users;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
