package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.entity.VerificationToken;
import com.example.nagoyameshi.event.SignupEventPublisher;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.service.UserService;
import com.example.nagoyameshi.service.VerificationTokenService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
	private final UserService userService;
	private final SignupEventPublisher signupEventPublisher;
	private final VerificationTokenService verificationTokenService;
	
	public AuthController(UserService userService,SignupEventPublisher signupEventPublisher,VerificationTokenService verificationTokenService)
	{
		this.userService=userService;
		this.signupEventPublisher=signupEventPublisher;
		this.verificationTokenService=verificationTokenService;
	}
	
	
	
	 @GetMapping("/login")
	 public String login()
	 {
		 
		 return "auth/login";
	 }
	 
	 
	 @GetMapping("/signup")
	 public String signup(Model model)
	 {
		 model.addAttribute("signupForm",new SignupForm());
		 System.out.println("signup");
		 return "auth/signup";
	 }
	 
	 @PostMapping("/signup")
	 public String signup(@ModelAttribute @Validated SignupForm signupForm,BindingResult bindingResult,RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest)
	 {
		 System.out.println("signupPOST");
		 
		 if(userService.isEmailRegistered(signupForm.getEmail()))
		 {
			  FieldError fieldError=new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
			  bindingResult.addError(fieldError);
		 }
		 
		 if((signupForm.getPassword()!=null||signupForm.getConfirmPassword()!=null)&&!userService.isSamePassword(signupForm.getPassword(), signupForm.getConfirmPassword()))
		 {
			 FieldError fieldError=new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
		  		bindingResult.addError(fieldError);
		 }
		 
		 if(bindingResult.hasErrors())
		 {
			 return "auth/signup";
		 }
		 
		 System.out.println("signupPOST:SUCCESS");
		 
		 Users user=userService.create(signupForm);
		 
		 String requestUrl = new String(httpServletRequest.getRequestURL());
		 
         signupEventPublisher.publishSignupEvent(user, requestUrl);
         
         
		 redirectAttributes.addFlashAttribute("successMessage","ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");
		 
		 return "redirect:";
	 }
	 
	 
	 @GetMapping("/signup/verify")
     public String verify(@RequestParam(name = "token") String token, Model model) {
         VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
         
         System.out.println("verification:Entry");
         if (verificationToken != null) {
             Users user = verificationToken.getUser();  
             userService.enableUser(user);
             System.out.println("verification:SUCCESS");
             String successMessage = "会員登録が完了しました。";
             model.addAttribute("successMessage", successMessage);            
         } else {
             String errorMessage = "トークンが無効です。";
             model.addAttribute("errorMessage", errorMessage);
         }
         
         return "auth/verify";         
     }    
	 
}
