package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.form.CategoryForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

//	カテゴリ定数
	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;
	
	
	
	public AdminCategoryController(CategoryRepository categoryRepository,CategoryService categoryService)
	{
		
		this.categoryRepository=categoryRepository;
		this.categoryService=categoryService;
		
		
	}
	
//	カテゴリー一覧
	@GetMapping("/show")
	public String  show(@RequestParam(name="keyword",required = false)String keyword,
			@PageableDefault(page=0,size=30,sort="id" )Pageable pageable,Model model) 
	{
		Page<Category>categoryPage;
		
		
		if(keyword!=null&&!keyword.isEmpty())
		{
			categoryPage=categoryRepository.findAllByNameLike("%"+keyword+"%", pageable);

		}
		else
		{
			categoryPage=categoryRepository.findAll(pageable);
		}
		
		
		
		
		model.addAttribute("categoryPage",categoryPage);
		
		return "adminCategory/show";
	}
	
	
//	カテゴリ登録
	@GetMapping("/create")
	public String create(Model model)
	{
		model.addAttribute("categoryForm",new CategoryForm());
		
		return "adminCategory/create";
		
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated CategoryForm categoryForm,BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model)
	{
		if(!categoryService.isSameCategory(categoryForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(),"name","すでに登録済みのカテゴリーです。");
			bindingResult.addError(fieldError);
			
		}
		
		if(bindingResult.hasErrors())
		{
			return "/adminCategory/create";
		}
		
		categoryService.create(categoryForm);
		redirectAttributes.addFlashAttribute("successMessage","カテゴリを登録しました");
		
		return "redirect:/admin/category/show";
		
	}
	
//	カテゴリ編集
	@PostMapping("/edit/{id}")
	public String edit(@PathVariable (name="id")Integer id,Model model)
	{
		Category category=categoryRepository.getReferenceById(id);
		
		CategoryForm categoryForm=new CategoryForm();
		categoryForm.setName(category.getName());
		model.addAttribute("category",category);
		model.addAttribute("categoryForm",categoryForm);

		
		return "adminCategory/edit";
	}
	
//	カテゴリ編集確定
	@PostMapping("/register/{id}")
	public String register(@PathVariable(name="id")Integer id,@ModelAttribute @Validated CategoryForm categoryForm,
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model)
	{
		Category category=categoryRepository.getReferenceById(id);
		if(!categoryService.isSameCategory(categoryForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(),"name","すでに登録済みのカテゴリーです。");
			bindingResult.addError(fieldError);
			
		}
		
		if(bindingResult.hasErrors())
		{
			model.addAttribute("category",category);
			return "adminCategory/edit";
		}
		
		category.setName(categoryForm.getName());
		categoryRepository.save(category);
		
		redirectAttributes.addFlashAttribute("successMessage","カテゴリーの編集を完了しました");
		
		return "redirect:/admin/category/show";
	}	
	
	
//	カテゴリ削除
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name="id")Integer id,RedirectAttributes redirectAttributes,Model model)
	{
		Category category=categoryRepository.getReferenceById(id);
		
		if(categoryService.confirm(category)!=null)
		{
			model.addAttribute("restaurantList",categoryService.confirm(category));
			model.addAttribute("category",category);
			
			return "adminCategory/confirm";
		}
		
		redirectAttributes.addFlashAttribute("successMessage",category.getName()+"を削除しました");
		
		return "redirect:/admin/category/show";
	}
	
//	カテゴリ削除確定
	@GetMapping("/confirm/{id}")
	public String confirm(@PathVariable(name="id")Integer id,RedirectAttributes redirectAttributes)
	{
		Category category=categoryRepository.getReferenceById(id);
		categoryService.delete(category);
		
		redirectAttributes.addFlashAttribute("successMessage",category.getName()+"を削除しました");
		
		return "redirect:/admin/category/show";
	}
	

}
