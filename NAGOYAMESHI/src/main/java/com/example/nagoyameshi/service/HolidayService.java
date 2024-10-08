package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Holiday;
import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.form.RestaurantEditForm;
import com.example.nagoyameshi.form.RestaurantForm;
import com.example.nagoyameshi.repository.HolidayRepository;

import jakarta.transaction.Transactional;

@Service
public class HolidayService {
	private final HolidayRepository holidayRepository;
	
	public HolidayService(HolidayRepository holidayRepository)
	{
		this.holidayRepository=holidayRepository;
	}
	
//	定休日作成
	@Transactional
	public void create(Restaurants restaurants,RestaurantForm restaurantForm) {
		Holiday holiday=new Holiday();
		holiday.setRestaurantId(restaurants);
		holiday.setMonday(restaurantForm.getMonday());
		holiday.setTuesday(restaurantForm.getTuesday());
		holiday.setWednesday(restaurantForm.getWednesday());
		holiday.setThursday(restaurantForm.getThursday());
		holiday.setFriday(restaurantForm.getFriday());
		holiday.setSaturday(restaurantForm.getSaturday());
		holiday.setSunday(restaurantForm.getSunday());
		
		
		holidayRepository.save(holiday);
		
		
	}
	
//	定休日更新
	@Transactional
	public void update(Restaurants restaurants,RestaurantEditForm restaurantEditForm)
	{
		
		Holiday holiday=holidayRepository.findByRestaurantId(restaurants);
		holiday.setMonday(restaurantEditForm.getMonday());
		holiday.setTuesday(restaurantEditForm.getTuesday());
		holiday.setWednesday(restaurantEditForm.getWednesday());
		holiday.setThursday(restaurantEditForm.getThursday());
		holiday.setFriday(restaurantEditForm.getFriday());
		holiday.setSaturday(restaurantEditForm.getSaturday());
		holiday.setSunday(restaurantEditForm.getSunday());
		holidayRepository.save(holiday);
		
		
	}
	
//	定休日削除
	public void delete(Restaurants restaurants)
	{
		Holiday holiday=holidayRepository.findByRestaurantId(restaurants);
		holidayRepository.delete(holiday);
	}

}
