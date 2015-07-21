package org.zsl.web.modules.test.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.zsl.common.entity.DataEntity;

@Entity
@Table(name="person")
public class Person extends DataEntity<Person>{
	private static final long serialVersionUID = 1L;
	public String name;
	private Set<Product> proudcts = new HashSet<>();
	@Column(name="name")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany
	@JoinTable(name="person_product")
	public Set<Product> getProudcts() {
		return this.proudcts;
	}
	
	public void setProudcts(Set<Product> proudcts) {
		this.proudcts = proudcts;
	}
	
	
	
}
