/**
 * ����ϵͳʵ����
 */
package com.xr.bean;

import java.io.Serializable;

public class Good implements Serializable{
	//����
	private Integer id;
	private String name;
	private Double price;
	
	//��������Է���
	public Good() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Good(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	//toString����
	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
	
}
