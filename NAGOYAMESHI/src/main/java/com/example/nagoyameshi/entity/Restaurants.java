package com.example.nagoyameshi.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="restaurants")
@Data
public class Restaurants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private Integer price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="sales_date")
	private String salesDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="address")
	private String address;
	
	@Column(name="capacity")
	private Integer capacity;
	
	@Column(name="evalues")
	private Integer evalues;
	
	@Column(name="evalues_double")
	private Double evaluesDouble;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
}

