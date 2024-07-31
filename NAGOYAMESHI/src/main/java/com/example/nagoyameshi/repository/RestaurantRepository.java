package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurants, Integer> {
	public Page<Restaurants>findByNameLike(String namekeyword,Pageable pageable);
	public Page<Restaurants>findByNameLikeOrderByPriceAsc(String namekeyword,Pageable pageable);
	public Page<Restaurants>findByNameLikeOrderByPriceDesc(String namekeyword,Pageable pageable);
	
	public Page<Restaurants>findByCategoryAndNameLike(Category category,String nasmeLewyword,Pageable pageable);
	public Page<Restaurants>findByCategoryAndNameLikeOrderByPriceAsc(Category category,String nasmeLewyword,Pageable pageable);
	public Page<Restaurants>findByCategoryAndNameLikeOrderByPriceDesc(Category category,String nasmeLewyword,Pageable pageable);
	
	public Page<Restaurants>findByCategory(Category category,Pageable pageable);
	public Page<Restaurants>findByCategoryOrderByPriceAsc(Category category,Pageable pageable);
	public Page<Restaurants>findByCategoryOrderByPriceDesc(Category category,Pageable pageable);
	
	public Page<Restaurants>findAllByOrderByPriceAsc(Pageable pageable);
	public Page<Restaurants>findAllByOrderByPriceDesc(Pageable pageable);
	
	public List<Restaurants>findByCategory(Category category);
	
	public List<Restaurants>findByNameLikeOrderByCreatedAtDesc(String Name);
	
	
}
