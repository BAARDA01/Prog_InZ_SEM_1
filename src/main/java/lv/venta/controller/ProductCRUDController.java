package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	
	@Autowired
	private IProductCRUDService crudService;
	
	@GetMapping("/all") //localhost:8080/product/crud/all
	public String getProductCRUDAll(Model model) 
	{
		try {
			ArrayList<Product> allProducts = crudService.retrieveAll();
			model.addAttribute("mydata", allProducts);
			return "product-show-all-page";//tiks parādīta producty-show-all-page.html ar visiem produktiem
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/one") //localhost:8080/product/crud/one?id=2
	public String getProductCRUDOne(@RequestParam("id") int id, Model model)
	{
		try
		{
			Product foundProduct = crudService.retrieveById(id);
			model.addAttribute("mydata", foundProduct);
			return "product-show-one-page";//tiks parādīta product-show-one-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/all/{id}") //localhost:8080/product/crud/all/2
	public String getProductCRUDById(@PathVariable("id") int id, Model model)
	{
		try
		{
			Product foundProduct = crudService.retrieveById(id);
			model.addAttribute("mydata", foundProduct);
			return "product-show-one-page";//tiks parādīta product-show-one-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/insert") //localhost:8080/product/crud/insert
	public String getProductCRUDInsert(Model model) {
		
		model.addAttribute("product", new Product());
		return "product-insert-page"; //tiks parādīta product-insert-page.html lapa ar iedotu tuksu produktu
		
	}
	
	@PostMapping("/insert")
	public String postProductCRUDInsert(@Valid Product product, BindingResult result) {//ienāk aizpildītais produkts
		//vai ir kādi validācijas pāŗkāpumi
		if(result.hasErrors())
		{
			return "product-insert-page"; //turpinām palikt product-insert-page.html lapā
		}
		else
		{
			crudService.create(product);
			return "redirect:/product/crud/all";
		}
		
	}
	
	

}