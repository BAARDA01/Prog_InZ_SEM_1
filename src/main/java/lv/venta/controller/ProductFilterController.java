package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductFilteringService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterController {
	@Autowired
	private IProductFilteringService filterService;
	
	
	@GetMapping("/price/{param}") //localhost:8080/product/filter/price/1.99
	public String getProductFilterByPrice(@PathVariable("param") float param, 
			Model model) {
		try
		{
			ArrayList<Product> result = filterService.filterByPriceLess(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Products filtered by price");
			return "product-show-all-page";//tiks parādīta product-show-all-page.html lapa ar izfilrētiem produktiem
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/quantity/{param}") //localhost:8080/product/filter/quantity/1
	public String getProductFilterByQuantity(@PathVariable("param") int param, 
			Model model) {
		try
		{
			ArrayList<Product> result = filterService.filterByQuantityLess(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Products filtered by quantity");
			return "product-show-all-page";//tiks parādīta product-show-all-page.html lapa ar izfilrētiem produktiem
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/text/{param}") //localhost:8080/product/filter/text/gar
	public String getProductFilterByTitleOrDescription(@PathVariable("param") String param, 
			Model model) {
		try
		{
			ArrayList<Product> result = filterService.filterByTitleOrDescription(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Products filtered by phrase");
			return "product-show-all-page";//tiks parādīta product-show-all-page.html lapa ar izfilrētiem produktiem
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/stat/total") //localhost:8080/product/filter/stat/total
	public String getproductFilterStatTotal(Model model) {
		try
		{
			float result = filterService.calculateTotalValueOfProducts();
			model.addAttribute("mydata", "Total " + result + " eur");
			return "hello-msg-page";//tiks parādīta hello-msg-page.html, ar padotajiem datiem
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
}