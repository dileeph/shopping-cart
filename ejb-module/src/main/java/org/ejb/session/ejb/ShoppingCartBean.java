package org.ejb.session.ejb;

import org.ejb.session.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
public class ShoppingCartBean implements ShoppingCart {

	@PersistenceContext(unitName = "ejbSampleDS")
	private EntityManager em;

	private List<Product> products;

	@PostConstruct
	private void init(){
		products = new ArrayList<>();
	}

	@Override
	public void addProduct(Product product){
		products.add(product);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void completePurchase(){
		for(Product product : products){
			System.out.println("Persisting product -- " + product.getId() + " --- " + product.getDescription());
			em.persist(product);
			em.flush();
		}
		products.clear();
	}

	public List<Product> getCartProducts() {
		return this.products;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<String> getProducts(){
		return em.createNativeQuery("select description from product").getResultList();

	}

}
