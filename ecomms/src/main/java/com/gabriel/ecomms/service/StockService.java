package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Stock;
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
public class StockService {
	Logger logger = LoggerFactory.getLogger(StockService.class);
	@Value("${service.api.endpoint}")
	//private String endpointUrl = "http://localhost:8080/api/ecommerce";
	private String endpointUrl = "http://localhost:8080/api/stock";

	protected static StockService service= null;
	public static StockService getService(){
		if(service == null){
			service = new StockService();
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

	public Stock get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Stock> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Stock.class);
		return response.getBody();
	}

	public Stock[] getAll() {
		String url = endpointUrl;
		logger.info("getEcommerces: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Stock[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Stock[].class);
		Stock[] stocks = response.getBody();
		return stocks;
	}

	public Stock create(Stock stock) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Stock> request = new HttpEntity<>(stock, headers);
		final ResponseEntity<Stock> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Stock.class);
		return response.getBody();
	}
	public Stock update(Stock stock) {
		logger.info("update: " + stock.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Stock> request = new HttpEntity<>(stock, headers);
		final ResponseEntity<Stock> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Stock.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Stock> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Stock> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Stock.class);
	}
}
