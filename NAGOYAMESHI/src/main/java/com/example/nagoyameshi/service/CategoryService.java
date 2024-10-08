package com.example.nagoyameshi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.form.CategoryForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final RestaurantRepository restaurantRepository;

	public CategoryService(CategoryRepository categoryRepository,RestaurantRepository restaurantRepository)
	{
		this.categoryRepository=categoryRepository;
		this.restaurantRepository=restaurantRepository;
	}
	
	
	@Transactional
	public void create(CategoryForm categoryForm)
	{
		Category category=new Category();
		
		category.setName(categoryForm.getName());
		categoryRepository.save(category);
	}
	
	
	
//	カテゴリ名があるかどうかの判定
	public boolean isSameCategory(CategoryForm categoryForm)
	{
		List<Category> listCategory=categoryRepository.findAll();
		
		
		for(Category category:listCategory)
		{
			if(categoryForm.getName().equals(category.getName()))
			{
				return false;
			}
		
		}	
		return true;
		
	}
	
//	カテゴリーを店舗が持っているか
	public List<Restaurants> confirm(Category category)
	{
		List<Restaurants>restaurants=restaurantRepository.findByCategory(category);
		
		
		return restaurants;
	}
	
//	店舗のカテゴリー情報とカテゴリー自体の削除
	public void delete(Category category)
	{
		List<Restaurants>restaurants=restaurantRepository.findByCategory(category);
		for(Restaurants restaurant:restaurants)
		{
			restaurant.setCategory(null);
		
		}
		
		categoryRepository.delete(category);
		
	}
}
