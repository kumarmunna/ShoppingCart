package com.shoppingcart.Beans;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CartInfoBean {

	private String code;
	private String name;
	private double price;
	private String filename;
	private int quantity;
	private double subtotal;
	
	public CartInfoBean() {
	}
	public CartInfoBean(String code, String name, double price,
			String filename, int quantity, int subtotal) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.filename = filename;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int hashCode(){
	        int hashcode = 0;
	        hashcode = new Double(price).intValue() * 20;
	        hashcode = code.hashCode();
	        return hashcode;
	}
	public boolean equals(Object obj){
		   if (obj instanceof CartInfoBean) {
			   CartInfoBean pp = (CartInfoBean) obj;
	            return (pp.code.equals(this.code) && pp.price == this.price);
	        } else {
	            return false;
	        }
	}
	@Override
	public String toString() {
		return "CartInfoBean [code=" + code + ", name=" + name + ", price="
				+ price + ", filename=" + filename + ", quantity=" + quantity
				+ ", subtotal=" + subtotal + "]";
	}
}
