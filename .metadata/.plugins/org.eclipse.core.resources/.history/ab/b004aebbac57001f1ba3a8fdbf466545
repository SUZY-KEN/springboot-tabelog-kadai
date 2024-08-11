package com.example.nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.form.RestaurantEditForm;
import com.example.nagoyameshi.form.RestaurantForm;
import com.example.nagoyameshi.form.RestaurantinputCsvForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final CategoryRepository categoryRepository;
	private final HolidayService holidayService;
	
	private final ReviewRepository reviewRepository;
	private final FavoriteRepository favoriteRepository;
	private final ReservationRepository reservationRepository;
	
	
	public RestaurantService(RestaurantRepository restaurantRepository,CategoryRepository categoryRepository,
			ReviewRepository reviewRepository,FavoriteRepository favoriteRepository,ReservationRepository reservationRepository,HolidayService holidayService)
	{
		this.restaurantRepository=restaurantRepository;
		this.categoryRepository=categoryRepository;
		this.reviewRepository=reviewRepository;
		this.favoriteRepository=favoriteRepository;
		this.reservationRepository=reservationRepository;
		this.holidayService=holidayService;
	}
	

	
	
	//店舗登録
	@Transactional
	public void register(RestaurantForm restaurantForm)
	{
		Restaurants restaurant=new Restaurants();
		System.out.println(restaurant.getId());
		MultipartFile imageFile=restaurantForm.getImageFile();
		
		if(!imageFile.isEmpty())
		{
			String imageName=imageFile.getOriginalFilename();
			String hashedFileName=generateNewFile(imageName);
			Path filePath=Paths.get("src/main/resources/static/storage/"+hashedFileName);
			copyImageFile(imageFile, filePath);
			restaurant.setImage(hashedFileName);
			
		}
		
		if(restaurantForm.getCategoryId()!=null)
		{
			restaurant.setCategory(categoryRepository.getReferenceById(restaurantForm.getCategoryId()));
		}
		
		if(restaurantForm.getDescription()!=null)
		{
			restaurant.setDescription(restaurantForm.getDescription());

		}
		
		
		restaurant.setName(restaurantForm.getName());
		restaurant.setPrice(restaurantForm.getPrice());
		restaurant.setCapacity(restaurantForm.getCapacity());
		restaurant.setAddress(restaurantForm.getAddress());
		restaurant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		restaurantRepository.save(restaurant);
		System.out.println(restaurant.getId());
		holidayService.create(restaurant, restaurantForm);
		
		
	}
	
	//店舗CSV登録
		@Transactional
		public void registerCsv(RestaurantinputCsvForm restaurantinputCsvForm)
		{
			Restaurants restaurant=new Restaurants();
			
			if(restaurantinputCsvForm.getCategory()!=null)
			{
				restaurant.setCategory(categoryRepository.getReferenceById(restaurantinputCsvForm.getCategory().getId()));
			}
			
			if(restaurantinputCsvForm.getDescription()!=null)
			{
				restaurant.setDescription(restaurantinputCsvForm.getDescription());

			}
			
			restaurant.setName(restaurantinputCsvForm.getName());
			restaurant.setPrice(restaurantinputCsvForm.getPrice());
			restaurant.setCapacity(restaurantinputCsvForm.getCapacity());
			restaurant.setAddress(restaurantinputCsvForm.getAddress());
			restaurant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			
			restaurantRepository.save(restaurant);
			
			
			
		}
	
	
//	店舗情報更新
	@Transactional
	public void update(RestaurantEditForm restaurantEditForm)
	{
		
		Restaurants restaurant=restaurantRepository.getReferenceById(restaurantEditForm.getId());

		MultipartFile imageFile=restaurantEditForm.getImageFile();
		
		if(!imageFile.isEmpty())
		{
			String imageName=imageFile.getOriginalFilename();
			String hashedFileName=generateNewFile(imageName);
			Path filePath=Paths.get("src/main/resources/satic/storage/"+hashedFileName);
			copyImageFile(imageFile, filePath);
			restaurant.setImage(hashedFileName);
			
		}
		
		if(restaurantEditForm.getCategoryId()!=null)
		{
			restaurant.setCategory(categoryRepository.getReferenceById(restaurantEditForm.getCategoryId()));
		}
		
		if(restaurantEditForm.getDescription()!=null)
		{
			restaurant.setDescription(restaurantEditForm.getDescription());

		}
		
		restaurant.setName(restaurantEditForm.getName());
		restaurant.setPrice(restaurantEditForm.getPrice());
		restaurant.setCapacity(restaurantEditForm.getCapacity());
		restaurant.setAddress(restaurantEditForm.getAddress());
		restaurant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		restaurantRepository.save(restaurant);
		
		
		
		
	}
	
//	店舗の消去
	@Transactional
	public void delete(Restaurants restaurant) {
		
		reviewDestroy(restaurant);
		favoriteDestroy(restaurant);
		reservationDestroy(restaurant);
		
		restaurantRepository.delete(restaurant);
	}
	
	
//	Reviewの破壊
	public void reviewDestroy(Restaurants restaurant)
	{
		List<Review>list=reviewRepository.findAllByRestaurants(restaurant);
		
		if(list!=null&&!list.isEmpty())
		{
			for(Review review:list)
			{
				reviewRepository.delete(review);
			}
		}
	}
	
//	Favoriteの破壊
	public void favoriteDestroy(Restaurants restaurant)
	{
		List<Favorite>list=favoriteRepository.findAllByRestaurant(restaurant);
				
				if(list!=null&&!list.isEmpty())
				{
					for(Favorite favorite:list)
					{
						favoriteRepository.delete(favorite);
					}
				}
	}
	
//	ReserVationの破壊
	public void reservationDestroy(Restaurants restaurant)
	{
		List<Reservation>list=reservationRepository.findAllByRestaurant(restaurant);
		
		if(list!=null&&!list.isEmpty())
		{
			for(Reservation reservation:list)
			{
				reservationRepository.delete(reservation);
			}
		}
	}
	
	
	
	//ファイル名の生成
	public String generateNewFile(String fileName)
	{
		String[] fileNames=fileName.split("\\.");
		for(int i=0;i<fileNames.length-1;i++)
		{
			fileNames[i]=UUID.randomUUID().toString();
		}
		String hashedFileName=String.join(".", fileNames);
		
		return hashedFileName;
	}
	
	//画像ファイルのコピー
	public void copyImageFile(MultipartFile imageFile,Path filePath)
	{
		try {
			Files.copy(imageFile.getInputStream(),filePath);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	//店舗名が重複してないか判定
		public boolean hasSameRestaurantName(RestaurantForm restaurantForm)
		{
			List<Restaurants>list=restaurantRepository.findAll();
			
			for(Restaurants restaurant:list)
			{
				if(restaurantForm.getName().equals(restaurant.getName()))
				{
					return true;
				}
			}
			return false;
		}
		
	//店舗名が重複してないか判定(更新時)
			public boolean hasSameRestaurantEditName(RestaurantEditForm restaurantEditForm)
			{
				List<Restaurants>list=restaurantRepository.findAll();
				Restaurants myRestaurant=restaurantRepository.getReferenceById(restaurantEditForm.getId());
				
				for(Restaurants restaurant:list)
				{
					if(restaurant!=myRestaurant&&restaurantEditForm.getName().equals(restaurant.getName()))
					{
						return true;
					}
				}
				return false;
			}
			
			//店舗名が重複してないか判定(CSV)
			public boolean hasSameRestaurantName(String name)
			{
				List<Restaurants>list=restaurantRepository.findAll();
				
				for(Restaurants restaurant:list)
				{
					if(name.equals(restaurant.getName()))
					{
						return true;
					}
				}
				return false;
			}
			
//			CSVが１以上の数字か判定
			
			public boolean isNumber(String val) {
				try {
					
					Integer i=Integer.parseInt(val);
					
					if(i>0)
					{
						return true;
					}
					return false;
				} catch (NumberFormatException nfex) {
					return false;
				}
			}

	
}
