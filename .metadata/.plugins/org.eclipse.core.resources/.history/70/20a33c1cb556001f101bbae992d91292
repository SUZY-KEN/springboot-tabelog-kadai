package com.example.nagoyameshi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.csv.CsvService;
import com.example.nagoyameshi.csv.DownloadHelper;
import com.example.nagoyameshi.csvData.RestaurantCsvData;
import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Holiday;
import com.example.nagoyameshi.entity.Restaurants;
import com.example.nagoyameshi.form.RestaurantCSVForm;
import com.example.nagoyameshi.form.RestaurantEditForm;
import com.example.nagoyameshi.form.RestaurantForm;
import com.example.nagoyameshi.form.RestaurantinputCsvForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.HolidayRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.service.HolidayService;
import com.example.nagoyameshi.service.RestaurantService;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import jakarta.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("admin/restaurant")
public class AdminRestaurantController {
	
	private final RestaurantService restaurantService;
	private final HolidayService holidayService;
	private final CategoryRepository categoryRepository;
	private final RestaurantRepository restaurantRepository;
	private final CsvService csvService;
	private final DownloadHelper downloadHelper;
	private final HolidayRepository holidayRepository;
	
	public AdminRestaurantController(RestaurantService restaurantService,CategoryRepository categoryRepository,RestaurantRepository restaurantRepository,
			CsvService csvService,DownloadHelper downloadHelper,HolidayService holidayService,HolidayRepository holidayRepository)
	{
		this.restaurantService=restaurantService;
		this.holidayService=holidayService;
		this.categoryRepository=categoryRepository;
		this.restaurantRepository=restaurantRepository;
		
		this.csvService=csvService;
		this.downloadHelper=downloadHelper;
		this.holidayRepository=holidayRepository;
		
	}

	@GetMapping("/create")
	public String show(Model model)
	{
		RestaurantForm restaurantForm=new RestaurantForm();
		
		
		model.addAttribute("categoryList",categoryRepository.findAll());
		model.addAttribute("restaurantForm",restaurantForm);
		return "restaurants/admin/create";
	}
	
	
	//店舗登録
	@PostMapping("/register")
	public String register(@ModelAttribute @Validated RestaurantForm restaurantForm,
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model)
	{
		if(restaurantService.hasSameRestaurantName(restaurantForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(),"name", "すでに店舗が存在しています");
			bindingResult.addError(fieldError);
			
			System.out.println("registerName:error");
		}
		
		if(bindingResult.hasErrors())
		{
			model.addAttribute("categoryList",categoryRepository.findAll());
			System.out.println("register:error");
			return "restaurants/admin/create";
		}
		redirectAttributes.addFlashAttribute("successMessage",restaurantForm.getName()+"の店舗情報を登録しました");
		
		restaurantService.register(restaurantForm);
		return "redirect:/restaurants?nameKeyword=&category=";
	}
	

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable (name="id")Integer id,Model model)
	{
		Restaurants restaurant=restaurantRepository.getReferenceById(id);
		Holiday holiday=holidayRepository.findByRestaurantId(restaurant);
		
		Integer categoryId=null;
		if(restaurant.getCategory()!=null)
		{
			categoryId=restaurant.getCategory().getId();
		}
		
		String description=null;
		if(restaurant.getDescription()!=null)
		{
			description=restaurant.getDescription();
		}
		
		
	
		RestaurantEditForm restaurantEditForm=new RestaurantEditForm(restaurant.getId(), restaurant.getName(), null,restaurant.getPrice(),
			categoryId, restaurant.getCapacity(), description, restaurant.getAddress(),holiday.getMonday(),holiday.getTuesday(),
			holiday.getWednesday(),holiday.getThursday(),holiday.getFriday(),holiday.getSaturday(),holiday.getSunday());
	
		
		
		
		model.addAttribute("restaurant",restaurant);
		model.addAttribute("restaurantEditForm",restaurantEditForm);
		model.addAttribute("categoryList",categoryRepository.findAll());
		System.out.println("edit:success");
		
		return "/restaurants/admin/edit";
		
	}
	
//	店舗編集
	@PostMapping("/update")
	public String update(@ModelAttribute @Validated RestaurantEditForm restaurantEditForm,
			BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model)
	{
		Restaurants restaurants=restaurantRepository.getReferenceById(restaurantEditForm.getId());
		
		if(restaurantService.hasSameRestaurantEditName(restaurantEditForm))
		{
			FieldError fieldError=new FieldError(bindingResult.getObjectName(),"name", "すでに店舗が存在しています");
			bindingResult.addError(fieldError);
		}
		
		if(bindingResult.hasErrors())
		{
			model.addAttribute("restaurant",restaurantRepository.getReferenceById(restaurantEditForm.getId()));
			model.addAttribute("categoryList",categoryRepository.findAll());
			return "restaurants/admin/edit";
		}
		redirectAttributes.addFlashAttribute("successMessage",restaurantEditForm.getName()+"の店舗情報を更新しました");
		
		restaurantService.update(restaurantEditForm);
		holidayService.update(restaurants, restaurantEditForm);
		
		return "redirect:/restaurants?nameKeyword=&category=";
	}
	
//店舗削除
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name="id")Integer id,RedirectAttributes redirectAttributes)
	{
		Restaurants restaurant=restaurantRepository.getReferenceById(id);
		
		
		redirectAttributes.addFlashAttribute("successMessage",restaurant.getName()+"の店舗情報を削除しました");
		holidayService.delete(restaurant);
		restaurantService.delete(restaurant);
		
		
		return "redirect:/restaurants?nameKeyword=&category=";
	}
	
	
//	店舗情報CSV出力
	@PostMapping("/download")
	public ResponseEntity<byte[]> downlodPageall(@ModelAttribute RestaurantCSVForm restaurantCSVForm)
	throws IOException
	{
		
		List<RestaurantCsvData>restaurantCsvDataList=new ArrayList<RestaurantCsvData>();
		
		
		List<Restaurants>restaurantList=restaurantRepository.findAllById(restaurantCSVForm.getRestaurantId());
		
		
		
		
		for(Restaurants restaurant:restaurantList)
		{
			
			
			RestaurantCsvData restaurantCsvData=new RestaurantCsvData(restaurant.getName(),restaurant.getAddress(),restaurant.getDescription(),restaurant.getPrice(),
				csvService.hasCategory(restaurant),restaurant.getCapacity(),restaurant.getEvaluesDouble(),restaurant.getCreatedAt());
			restaurantCsvDataList.add(restaurantCsvData);
		}
		
		
		
		HttpHeaders headers=new HttpHeaders();
		downloadHelper.addContentDisposition(headers, "restaurants.csv");
		
	    CsvSchema csvSchemaRestaurant = csvService.getCsvHeaderRestaurant();
		
		return new ResponseEntity<byte[]>(csvService.writeCsvTextRestaurant(restaurantCsvDataList,csvSchemaRestaurant).getBytes("MS932"),headers,HttpStatus.OK);
	}
	
//	店舗情報CSV入力
	@PostMapping("/upload")
	public String upload(@RequestParam(name="file")MultipartFile file,Model model,RedirectAttributes redirectAttributes)
	{
		
		List<RestaurantinputCsvForm>restaurantInputCsvFormsList=new ArrayList<RestaurantinputCsvForm>();
		boolean hasError=true;
		
		if(file.isEmpty())
		{
			hasError=false;
		}
		
		try(InputStream inputStream=file.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("MS932"))))
		{
			
			String line;
			
//			2行目から読み取る
			line=br.readLine();
			
			
//		2行目から格納
			
			
			while((line=br.readLine())!=null)
			{
				String[] csvSplit=line.split(",");
			
				
				String name = (csvSplit[0]);
				 String description = csvSplit[1];
		            String address = csvSplit[2];
				
				Integer price=null;
				if(restaurantService.isNumber(csvSplit[3]))
				{
					price=Integer.parseInt(csvSplit[3]);
				}
				
	           
	            
	            Category category = null;
	            if(categoryRepository.findByName(csvSplit[4])!=null)
	            {
	            	category = categoryRepository.findByName(csvSplit[4]);
	            }
	            
	            
	            Integer capacity=null;
	            if(restaurantService.isNumber(csvSplit[5]))
				{
	            	  capacity = Integer.parseInt(csvSplit[5]);
				}
	           
	   
	            // エラーがあった時はスキップ
	            if (price == null || capacity == null||name==null||restaurantService.hasSameRestaurantName(name)) {
	            	System.out.println("要素の問題");
	            	hasError=false;
	                continue;
	            }
	            //すでにあるフォームの店舗名が重複していないか。
	            if(restaurantInputCsvFormsList.size()>0)
	            {
	            	System.out.println("フォーム内の重複を確認");
	            	for(RestaurantinputCsvForm restaurantinputCsvForm:restaurantInputCsvFormsList )
	            	{
	            		if(restaurantinputCsvForm.getName().equals(name))
	            		{
	            			System.out.println("フォーム内の重複を発見");
	            			hasError=false;
	    	                continue;
	            		}
	            	}
	            }
	           

	            RestaurantinputCsvForm restaurantinputCsvForm = new RestaurantinputCsvForm(name, price, category, capacity, description, address);
	            
	            restaurantInputCsvFormsList.add(restaurantinputCsvForm);
	            
	            
				
			}
			br.close();
				
		}catch (Exception e) 
		{
			System.out.println("ファイル自体の問題");
			hasError=false;
			
			e.printStackTrace();
			
			
		}
		
		if(hasError)
		{
			for(RestaurantinputCsvForm restaurantInputCsvForm:restaurantInputCsvFormsList)
			{
				restaurantService.registerCsv(restaurantInputCsvForm);
			}
			System.out.println("店舗登録");
			
			redirectAttributes.addFlashAttribute("successMessage","店舗情報をCSV登録しました");
			return "redirect:/restaurants?nameKeyword=&category=";
		}
		
		
		redirectAttributes.addFlashAttribute("errorMessage","CSVファイルを読み取れませんでした");
		return "redirect:/admin/restaurant/create";
		
	}
	
	
	//CSVフォームのダウンロード
	@GetMapping("/downloadform")
	public String downlodForm(HttpServletResponse httpServletResponse)
	throws IOException
	{
		csvService.output(httpServletResponse);
		
		return null;
	}
	
	
	

}
