package com.example.nagoyameshi.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.form.ReviewCreateForm;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	
	public ReviewService(ReviewRepository reviewRepository,RestaurantRepository restaurantRepository,UserRepository userRepository)
	{
		this.reviewRepository=reviewRepository;
		this.restaurantRepository=restaurantRepository;
		this.userRepository=userRepository;
	}
	
	
	
	public boolean hasUserAlreadyReviewed(Restaurants restaurants,Users users)
	{
		
		return reviewRepository.findByRestaurantsAndUsersAndEnabled(restaurants,users,true)!=null;
	}
	
//	レビュー登録
	@Transactional
	public void register(ReviewCreateForm reviewCreateForm,Restaurants restaurants,Users users)
	{
		Review review=new Review();
		review.setUsers(users);
		review.setRestaurants(restaurants);
		review.setEvalue(reviewCreateForm.getEvalue());
		review.setReviewComment(reviewCreateForm.getReviewComment());
		review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		review.setEnabled(true);
		
		
		reviewRepository.save(review);
		
		saveAverageEvaluesToRestaurantEvalues(restaurants);
		
	}
	
	//レビュー更新
	@Transactional
	public void update(ReviewEditForm reviewEditForm,Restaurants restaurants,Review review)
	{
		
		 
		review.setEvalue(reviewEditForm.getEvalue());
		review.setReviewComment(reviewEditForm.getReviewComment());
		review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		reviewRepository.save(review);
		saveAverageEvaluesToRestaurantEvalues(restaurants);
	}
	
//	管理者名前検索
	public List<Users> usertIndex(String userKeyword)
	{
		return userRepository.findByNameLikeOrderByCreatedAtDesc("%"+userKeyword+"%");
	}
	
	
//	管理者店舗検索
	public List<Restaurants> restaurantIndex(String restaurantKeyword)
	{
		return restaurantRepository.findByNameLikeOrderByCreatedAtDesc("%"+restaurantKeyword+"%");
	}
	
	
	//個人の評価を集計して平均をはじき、restaurantsクラスのevaluesを設定
	public void saveAverageEvaluesToRestaurantEvalues(Restaurants restaurants)
	{
		List<Review>reviewList=reviewRepository.findAllByRestaurantsAndEnabled(restaurants, true);
		
		Integer evalueSum=0;
		
		for(Review review:reviewList)
		{
			evalueSum+=review.getEvalue();
		}
		System.out.println("evalueSum"+evalueSum);
		long reviewSize=reviewRepository.countByRestaurantsAndEnabled(restaurants, true);
		System.out.println("reviewSize"+reviewSize);
		
		Double averageEvalue=Math.floor((((double)evalueSum/reviewSize)*10))/10;
		
		restaurants.setEvaluesDouble(averageEvalue);
		restaurants.setEvalues((int)Math.floor(averageEvalue));
		restaurantRepository.save(restaurants);
	}
	
}
