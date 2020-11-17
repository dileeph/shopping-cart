package org.ejb.session.ejb;

import org.ejb.session.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ShoppingCart {

	void addProduct(Product product);
	
	void completePurchase();

	List<String> getProducts();

	List<Product> getCartProducts();
}
