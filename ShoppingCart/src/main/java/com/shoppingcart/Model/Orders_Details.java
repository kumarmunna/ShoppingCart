package com.shoppingcart.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderDetails")
public class Orders_Details {

	
	private int id;
	private Orders OrderId;
	private String product_code;
	private int quantity;
	private double product_price;
	private double total_amount;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "OrderId")
	public Orders getOrderId() {
		return OrderId;
	}
	public void setOrderId(Orders orderId) {
		OrderId = orderId;
	}
	@Column(name = "product_code")
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name = "prod_price")
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	@Column(name = "total_amount")
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Orders_Details [OrderId=" + OrderId + ", product_code="
				+ product_code + ", quantity=" + quantity + ", product_price="
				+ product_price + ", total_amount=" + total_amount + "]";
	}
	
	
}
