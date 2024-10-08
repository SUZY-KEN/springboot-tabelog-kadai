package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/admin/review")
public class AdminReviewController {
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	
	public AdminReviewController(ReviewRepository reviewRepository,ReviewService reviewService)
	{
		this.reviewRepository=reviewRepository;
		this.reviewService=reviewService;
	}
	
	
	

	@GetMapping("/show")
	public String show(@PageableDefault(page=0,size=10,sort="id")Pageable pageable,
			@RequestParam(name="userKeyword" , required = false)String userKeyword,@RequestParam(name="restaurantKeyword",required =false)String restaurantKeyword,Model model)
	{
		
		Page<Review>reviewPage=null;
		
		if(userKeyword!=null&&!userKeyword.isEmpty())
		{
			List<Users>userList=reviewService.usertIndex(userKeyword);
			
			
	            if (!userList.isEmpty()) {
	                reviewPage = reviewRepository.findAllByUsersInOrderByCreatedAtDesc(userList, pageable);
	            }
		}
		else if(restaurantKeyword!=null&&!restaurantKeyword.isEmpty())
		{
			 List<Restaurants> restaurantList = reviewService.restaurantIndex(restaurantKeyword);
	            if (!restaurantList.isEmpty()) {
	                reviewPage = reviewRepository.findAllByRestaurantsInOrderByCreatedAtDesc(restaurantList, pageable);
	            }
			
			
		}
		else
		{
			reviewPage=reviewRepository.findAllByOrderByCreatedAtDesc(pageable);

		}
		
		model.addAttribute("reviewPage",reviewPage);
		System.out.println(reviewPage);
		return "review/admin/show";
	}
	
//	レビュー非表示処理
	@GetMapping("/hidden/{id}")
	public String hidden(@PathVariable(name="id")Integer id,RedirectAttributes redirectAttributes)
	{
		Review review=reviewRepository.getReferenceById(id);
		review.setEnabled(false);
		reviewRepository.save(review);
		
		return "redirect:/admin/review/show";
	}
	
//	レビュー表示処理
	@GetMapping("/display/{id}")
	public String display(@PathVariable(name="id")Integer id,RedirectAttributes redirectAttributes)
	{
		Review review=reviewRepository.getReferenceById(id);
		review.setEnabled(true);
		reviewRepository.save(review);
		return "redirect:/admin/review/show";

	}
}
