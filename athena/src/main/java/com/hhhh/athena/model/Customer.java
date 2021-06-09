package com.hhhh.athena.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="customer",schema="athenadbschema")
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long id;
	@NotNull
	private String first_name;
	@NotNull
	private String last_name;
	@NotNull
	private String middle_name;
	@NotNull
	private String sex;
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="registred_address_id",insertable = true, updatable = false)
	private Address registred_address;
	
	public Address getRegistred_address() {
		return registred_address;
	}
	public void setRegistred_address(Address registred_address) {
		this.registred_address = registred_address;
	}
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="actual_address_id",insertable = true, updatable = false)
	private Address actual_address;
	
	public Address getActual_address() {
		return actual_address;
	}
	public void setActual_address(Address actual_address) {
		this.actual_address = actual_address;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", middle_name="
				+ middle_name + ", sex=" + sex + ", registred_address=" + registred_address + ", actual_address="
				+ actual_address + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actual_address == null) ? 0 : actual_address.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((middle_name == null) ? 0 : middle_name.hashCode());
		result = prime * result + ((registred_address == null) ? 0 : registred_address.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (actual_address == null) {
			if (other.actual_address != null)
				return false;
		} else if (!actual_address.equals(other.actual_address))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id != other.id)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (middle_name == null) {
			if (other.middle_name != null)
				return false;
		} else if (!middle_name.equals(other.middle_name))
			return false;
		if (registred_address == null) {
			if (other.registred_address != null)
				return false;
		} else if (!registred_address.equals(other.registred_address))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}
}
