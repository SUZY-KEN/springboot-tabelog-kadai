package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Users;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;


@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
//	ユーザー定数
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	

	
	
	public AdminUserController(UserRepository userRepository,RoleRepository roleRepository)
	{
		this.userRepository=userRepository;
		this.roleRepository=roleRepository;
		
		
		
		
	}
	
	
//	ユーザー一覧
	@GetMapping("/show")
	public String  show(@RequestParam(name="keyword",required = false)String keyword,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page=0,size=10,sort="id" )Pageable pageable,Model model) 
	{
		Page<Users>userPage;
		Users myUser=userDetailsImpl.getUser();
		
		if(keyword!=null&&!keyword.isEmpty())
		{
			userPage=userRepository.findAllByEmailLike("%"+keyword+"%", pageable);

		}
		else
		{
			userPage=userRepository.findAll(pageable);
		}
		
		
		model.addAttribute("userPage",userPage);
		model.addAttribute("myUser",myUser);
		return "/user/admin/show";
	}

	@GetMapping("/authorize")
	public String authorize(@RequestParam(name="id")Integer id,RedirectAttributes redirectAttributes) {
		
		Users user=userRepository.getReferenceById(id);
		
		user.setRole(roleRepository.getReferenceById(3));
		userRepository.save(user);
		
		redirectAttributes.addFlashAttribute("successMessage",user.getName()+"さんに管理者権限を付与しました");
		
		return "redirect:/admin/user/show";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id")Integer id,RedirectAttributes redirectAttributes) {
		
		Users user=userRepository.getReferenceById(id);
		user.setRole(roleRepository.getReferenceById(2));
		userRepository.save(user);
		
		redirectAttributes.addFlashAttribute("successMessage",user.getName()+"さんの管理署権限を剥奪しました");
		
		return "redirect:/admin/user/show";
	}
	

}
