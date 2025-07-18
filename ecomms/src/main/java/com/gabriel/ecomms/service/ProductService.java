package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/product";

	protected static ProductService service= null;
	public static ProductService getService(){
		if(service == null){
			service = new ProductService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public Product get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Product> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Product.class);
		return response.getBody();
	}

	public Product[] getAll() {
		String url = endpointUrl;
		logger.info("getProducts: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Product[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Product[].class);
		Product[] products = response.getBody();
		return products;
	}

	public Product create(Product product) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Product> request = new HttpEntity<>(product, headers);
		final ResponseEntity<Product> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Product.class);
		return response.getBody();
	}
	public Product update(Product product) {
		logger.info("update: " + product.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Product> request = new HttpEntity<>(product, headers);
		final ResponseEntity<Product> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Product.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Product> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Product> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Product.class);
	}
}
