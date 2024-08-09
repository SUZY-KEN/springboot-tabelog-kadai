package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Company;
import com.example.nagoyameshi.form.CompanyEditForm;
import com.example.nagoyameshi.repository.CompanyRepository;
import com.example.nagoyameshi.service.CompanyService;

@Controller
public class CompanyController {
	
	private final CompanyRepository companyRepository;
	private final CompanyService companyService;
	
	public CompanyController(CompanyRepository companyRepository,CompanyService companyService)
	{
		this.companyRepository=companyRepository;
		this.companyService=companyService;
	}

	@GetMapping("/company")
	public String show(Model model)
	{
		Company company=companyRepository.getReferenceById(1);
		
		model.addAttribute("company",company);
		return "companies/show"; 
	}
	
	@GetMapping("/company/admin/edit")
	public String edit(Model model)
	{
		
		Company company=companyRepository.getById(1);
		String []postalCode=companyService.splitPostalCode(company);
		
		CompanyEditForm companyEditForm=new CompanyEditForm(company.getName(), postalCode[0] ,postalCode[1] , 
				company.getAddress(), company.getPrseident(), company.getEstablishedAt(),company.getCapital(),company.getContents(), company.getEmployees());
		System.out.println(companyEditForm);
		model.addAttribute("companyEditForm",companyEditForm);
		
		return"companies/admin/edit";
	}
	
	@PostMapping("/company/admin/update")
	public String update(@ModelAttribute @Validated CompanyEditForm companyEditForm,BindingResult bindingResult,RedirectAttributes redirectAttributes)
	{
		if(!companyService.hasPostalCode(companyEditForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(), "postalFirstcode", "郵便番号を入力してください");
			bindingResult.addError(fieldError);
		}
		
		else if(!companyService.isMatchPostalCode(companyEditForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(), "postalFirstcode", "適切な郵便番号を入力してください");
			bindingResult.addError(fieldError);
		}
		
		if(bindingResult.hasErrors())
		{
			return "companies/admin/edit";
		}
		
		
		companyService.update(companyEditForm);
		
		redirectAttributes.addFlashAttribute("successMessage","会社情報を編集しました");
		return "redirect:/company";

	}
}
