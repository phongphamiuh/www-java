package com.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.model.request.ProductRequest;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/product")
@AllArgsConstructor
public class AdminProductController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	private static final String pathImage ="D:\\Document\\2TwoYearUniverSity\\WWW Java Spring\\ecommerce_project\\ecommerce_project\\src\\main\\webapp\\resources\\images\\products\\";
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@ModelAttribute("product") @Valid ProductRequest productRequest,BindingResult result,
			RedirectAttributes redirect,
			@RequestParam("image") MultipartFile multipartFile) {
		
		if (result.hasErrors()) {
            return "admin-product-add";
        }
		Product product= productService.saveProduct(productRequest);
		
		if (multipartFile != null && !multipartFile.isEmpty()) {
			Path path = Paths
					.get("D:\\Document\\2TwoYearUniverSity\\WWW Java Spring\\ecommerce_project\\ecommerce_project\\src\\main\\webapp\\resources\\images\\products\\"
							+ product.getId() + ".png");

			try {
				multipartFile.transferTo(new File(path.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		redirect.addAttribute("page","1");
		redirect.addAttribute("categoryName",productRequest.getCategoryName());
		redirect.addAttribute("sortField","id");
		redirect.addAttribute("sortDir","lowest");
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public String get(Model model,@PathVariable("id")Long id) {
		ProductResponse productResponse = productService.get(id);
		model.addAttribute("product", productResponse);
		return "admin-product-edit";
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.POST)
	public RedirectView put(@PathVariable("id")Long id,
			@ModelAttribute("product")ProductResponse productResponse,
			@RequestParam("newImage") MultipartFile multipartFile,
			RedirectAttributes redirect) {
		System.out.println(multipartFile.getOriginalFilename());
		if (multipartFile != null && !multipartFile.isEmpty()) {
			
			Path path = Paths.get(pathImage+id+".png");
			
			
			try {
				Files.delete(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				
				multipartFile.transferTo(new File(path.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Product product = productService.findById(id);
		
		product.setBrand(productResponse.getBrand());
		product.setName(productResponse.getName());
		product.setPrice(productResponse.getPrice());
		product.setStock(productResponse.getStock());
		product.setOnSalePrice(productResponse.getOnSalePrice());
		product.setCargoPrice(productResponse.getCargoPrice());
		product.setDescription(productResponse.getDescription());
		
		productService.updateProduct(product);
		
		redirect.addAttribute("page","1");
		redirect.addAttribute("categoryName",productResponse.getCategoryName());
		redirect.addAttribute("sortField","id");
		redirect.addAttribute("sortDir","lowest");
		
		return new RedirectView("/ecommerce_project/admin/product");
	}
	
	@RequestMapping("/add")
	public String getProductForm(Model model) {
		ProductRequest productRequest =new ProductRequest();
		model.addAttribute("product", productRequest);
		return "admin-product-add";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String findPaginated(@RequestParam(value = "page",required = false,defaultValue = "1") Integer pageNo,
			@RequestParam(value="categoryName")String categoryName,
		    @RequestParam(value = "sortField" ,required = false,defaultValue = "id") String sortField,
		    @RequestParam(value = "sortDir" ,required = false,defaultValue = "asc") String sortDir,
		    Model model) {
		    int pageSize = 5;
		    List<ProductCategory> categories= categoryService.getAll();
		    Page < Product > page = productService.findPaginated(pageNo, pageSize, sortField, sortDir,categoryName);
		    List < Product > productList = page.getContent();
		    productList.forEach(product ->{
		    	System.out.println(product);
		    });
		    model.addAttribute("categories", categories);
		    model.addAttribute("sortField", sortField);
		    model.addAttribute("sortDir", sortDir);
		    model.addAttribute("categoryName",categoryName);		 
		    model.addAttribute("products", page);
		    
		    return "admin-product-list";
	}
	
	@RequestMapping(value="/delete/{id}/{categoryName}",method = RequestMethod.GET)
	public RedirectView delete(@PathVariable("id")Long id,@PathVariable("categoryName")String categoryName,
			RedirectAttributes redirect) {
		
		productService.deleteProduct(id);
		
		redirect.addAttribute("page","1");
		redirect.addAttribute("categoryName",categoryName);
		redirect.addAttribute("sortField","id");
		redirect.addAttribute("sortDir","lowest");
		return new RedirectView("/ecommerce_project/admin/product");
	}
}
