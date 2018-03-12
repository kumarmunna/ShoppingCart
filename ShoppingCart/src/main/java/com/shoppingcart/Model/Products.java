package com.shoppingcart.Model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {

	@Id
	private String code;
	
	@Column(name="create_date")
	private Date create_date;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private double price;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Products [code=" + code + ", create_date=" + create_date
				+ ", filename=" + filename + ", name=" + name + ", price="
				+ price + "]";
	}
	
	
}
