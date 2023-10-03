package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Entity(name = "Order1")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotBlank(message = "Item name cannot be blank.")
	String item;
	@Min(value = 1, message = "Price cannot be negative")
	@Column(name = "cost")
	float price;
	Date createdDate;
	@Email(message = "email is invalid")
	String email;
	@NotBlank
	@Pattern(regexp = "^\\d{10}$", message = "invalid number")
	@Column( unique = true)
//	@Transient
	String mobile;
	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="address_id")
	Address address;
	@ManyToMany
	Set<Supplier> suppliers;
	public Set<Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
