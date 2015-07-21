package org.zsl.web.modules.test.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.zsl.common.entity.DataEntity;

/**
 * Trivial JPA entity for vertx-spring demo
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends DataEntity<Product> {
	private static final long serialVersionUID = 1L;

	private String description;
	
	private Set<Person> persons = new HashSet<>();

	@Column
	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToMany(mappedBy="proudcts")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
}
