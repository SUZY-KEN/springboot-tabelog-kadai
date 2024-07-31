package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Users;



public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	public List<Review>findTop6ByRestaurantsAndEnabledOrderByCreatedAtDesc(Restaurants restaurants,boolean bool) ;
	public Review findByRestaurantsAndUsersAndEnabled(Restaurants restaurants,Users users,boolean bool);
	public long countByRestaurantsAndEnabled(Restaurants restaurants,boolean bool);
	public Page<Review>findByRestaurantsAndEnabledOrderByCreatedAtDesc(Restaurants restaurants,boolean bool,Pageable pageable) ;
	public List<Review>findAllByRestaurantsAndEnabled(Restaurants restaurants,boolean bool);
	
	public Page<Review>findAllByOrderByCreatedAtDesc(Pageable pageable) ;
	public Page<Review>findAllByUsersInOrderByCreatedAtDesc(List<Users>users,Pageable pageable);
	public Page<Review>findAllByRestaurantsInOrderByCreatedAtDesc(List<Restaurants>restaurants,Pageable pageable) ;
	
	public List<Review>findAllByRestaurants(Restaurants restaurants);
	
	
	
	
}
