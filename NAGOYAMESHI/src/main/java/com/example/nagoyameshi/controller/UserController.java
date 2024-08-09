package com.example.nagoyameshi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.form.EditUserForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.UserService;

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	private final UserService userService;
	
	public UserController(UserRepository userRepository,UserService userService)
	{
		this.userRepository=userRepository;
		this.userService=userService;
	}

//	会員情報
	@GetMapping("/user")
	public String showUser(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model)
	{
		Users user=userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
		model.addAttribute("user",user);
		return "user/show";
	}
	
//	会員情報編集ページへ
	@GetMapping("/user/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model)
	{
		Users user=userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
		model.addAttribute("editUserForm",new EditUserForm(user.getName(), user.getEmail()));
		return "user/edit";
	}
	
	//会員情報編集処理
	@PostMapping("/user/edited")
	public String edited(@ModelAttribute @Validated EditUserForm editUserForm,BindingResult bindingResult,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
		{
			return "user/edit";
		}
		
		Users user=userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		userService.edit(user,editUserForm);
		redirectAttributes.addFlashAttribute("successMessage","会員情報を更新しました");
		
		return "redirect:/user";
	}
	
	
}
