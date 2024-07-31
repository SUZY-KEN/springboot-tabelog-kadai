package com.example.nagoyameshi.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.form.ReservationForm;
import com.example.nagoyameshi.repository.ReservationRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) 
	{
		this.reservationRepository=reservationRepository;
	}
	
	//登録
	public void create(Users user,Restaurants restaurant,ReservationForm reservationForm)
	{
		
		
		Reservation reservation=new Reservation();
		
		LocalDate checkinDate=LocalDate.parse(reservationForm.getCheckinDate());
		LocalTime checkinTime=LocalTime.parse(reservationForm.getCheckinTime());
		
		reservation.setRestaurant(restaurant);
		reservation.setUser(user);
		reservation.setCheckinDate(checkinDate);
		reservation.setCheckinTime(checkinTime);
		reservation.setNumberOfPeople(reservationForm.getNumberOfPeople());
		reservation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		reservationRepository.save(reservation);
	}
	
	//キャンセル
	public void cancel(Users user,Restaurants restaurant)
	{
		Reservation reservation=reservationRepository.findByUserAndRestaurant(user, restaurant);
		reservationRepository.delete(reservation);
	}
	
//	定員数から超えているかの判定
	public boolean isLessThanCapacity(Restaurants restaurant,ReservationForm reservationForm)
	{
		if(reservationForm.getNumberOfPeople()!=null)
		{
			return restaurant.getCapacity()>=reservationForm.getNumberOfPeople();
		}
		
		return false;
	}
}
