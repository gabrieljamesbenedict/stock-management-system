package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.ProductData;
import com.gabriel.ecomms.model.Product;
import com.gabriel.ecomms.repository.ProductDataRepository;
import com.gabriel.ecomms.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ProductServiceImpl implements ProductService {
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductDataRepository productDataRepository;
	@Autowired
	@Override
	public Product[] getAll() {
		List<ProductData> productsData = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		productDataRepository.findAll().forEach(productsData::add);
		Iterator<ProductData> it = productsData.iterator();
		while(it.hasNext()) {
			ProductData productData = it.next();
			Product product = new Product();
			product.setId(productData.getId());
			product.setName(productData.getName());
			products.add(product);
		}
		Product[] array = new Product[products.size()];
		for  (int i=0; i<products.size(); i++){
			array[i] = products.get(i);
		}
		return array;
	}
	@Override
	public Product create(Product product) {
		logger.info(" add:Input " + product.toString());
		ProductData productData = new ProductData();
		productData.setName(product.getName());
		productData = productDataRepository.save(productData);
		logger.info(" add:Input " + productData.toString());
			Product newProduct = new Product();
			newProduct.setId(productData.getId());
			newProduct.setName(productData.getName());
		return newProduct;
	}

	@Override
	public Product update(Product product) {
		Product updatedProduct = null;
		int id = product.getId();
		Optional<ProductData> optional  = productDataRepository.findById(product.getId());
		if(optional.isPresent()){
			ProductData originalProductData = new ProductData();
			originalProductData.setId(product.getId());
			originalProductData.setName(product.getName());
			originalProductData.setCreated(optional.get().getCreated());
			ProductData productData = productDataRepository.save(originalProductData);
			updatedProduct = new Product();
			updatedProduct.setId(productData.getId());
			updatedProduct.setName(productData.getName());
			updatedProduct.setCreated(productData.getCreated());
			updatedProduct.setLastUpdated(productData.getLastUpdated());
		}
		else {
			logger.error("Product record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedProduct;
	}

	@Override
	public Product get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Product product = null;
		Optional<ProductData> optional = productDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			product = new Product();
			product.setId(optional.get().getId());
			product.setName(optional.get().getName());
			product.setCreated(optional.get().getCreated());
			product.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return product;
	}
	@Override
	public void delete(Integer id) {
		Product product = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ProductData> optional = productDataRepository.findById(id);
		if( optional.isPresent()) {
			ProductData productDatum = optional.get();
			productDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Product record with id: " + Integer.toString(id));
			product = new Product();
			product.setId(optional.get().getId());
			product.setName(optional.get().getName());
			product.setCreated(optional.get().getCreated());
			product.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate product with id:" +  Integer.toString(id));
		}
	}
}
