package com.rameysoft.main.model;

// Generated Jun 28, 2015 9:47:25 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Products generated by hbm2java
 */
@Entity
@Table(name = "products")
public class Products implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4458905136377402297L;
	private Integer id;
	private Categories categories;
	private Double purchasePrice;
	private double salePrice;
	private String name;
	private Integer quantity;
	private Set<OrderLines> orderLineses = new HashSet<OrderLines>(0);
	private Set<PoLines> poLineses = new HashSet<PoLines>(0);

	public Products() {
	}

	public Products(Categories categories, double salePrice, String name) {
		this.categories = categories;
		this.salePrice = salePrice;
		this.name = name;
	}

	public Products(Categories categories, Double purchasePrice,
			double salePrice, String name, Integer quantity,
			Set<OrderLines> orderLineses, Set<PoLines> poLineses) {
		this.categories = categories;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.name = name;
		this.quantity = quantity;
		this.orderLineses = orderLineses;
		this.poLineses = poLineses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "category_id", nullable = false)
	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	@Column(name = "purchase_price", precision = 22, scale = 0)
	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Column(name = "sale_price", nullable = false, precision = 22, scale = 0)
	public double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	public Set<OrderLines> getOrderLineses() {
		return this.orderLineses;
	}

	public void setOrderLineses(Set<OrderLines> orderLineses) {
		this.orderLineses = orderLineses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
	public Set<PoLines> getPoLineses() {
		return this.poLineses;
	}

	public void setPoLineses(Set<PoLines> poLineses) {
		this.poLineses = poLineses;
	}

}
