package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	//public abstract jau pēc noeklusejuma
	//SELECT * FROM product_table 
	//WHERE title= 1.arguments AND description= 2.arguments AND price= 3.arguments  
	Product findByTitleAndDescriptionAndPrice
	(String title, String description, float price);

	//public abstract jau pēc noeklusejuma
	//SELECT * FROM product_table WHERE price < 1.argumentu
	ArrayList<Product> findByPriceLessThan(float threshold);
	
	//public abstract jau pēc noeklusejuma
	//SELECT * FROM product_table WHERE quantity < 1.argumentu
	ArrayList<Product> findByQuantityLessThan(int threshold);
	//public abstract jau pēc noeklusejuma
	//SELECT * FROM product_table WHERE UPPER(title) LIKE UPPER(1.arguments) OR UPPER(description) LIKE UPPER(2.arguments)
	ArrayList<Product> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String phrase, String phrase2);

	//public abstract jau pēc noeklusejuma
	//bet vaicajumu veidojam paši
	@Query(nativeQuery = true, value = "SELECT SUM(price * quantity) FROM product_table;")
	float calculateTotalValueFromRepoProducts();

	
	
	
	
	
	
}
