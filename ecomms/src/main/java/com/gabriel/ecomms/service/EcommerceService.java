package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Ecommerce;
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
public class EcommerceService {
	Logger logger = LoggerFactory.getLogger(EcommerceService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/ecommerce";

	protected static EcommerceService service= null;
	public static EcommerceService getService(){
		if(service == null){
			service = new EcommerceService();
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

	public Ecommerce get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Ecommerce> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Ecommerce.class);
		return response.getBody();
	}

	public Ecommerce[] getAll() {
		String url = endpointUrl;
		logger.info("getEcommerces: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Ecommerce[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Ecommerce[].class);
		Ecommerce[] ecommerces = response.getBody();
		return ecommerces;
	}

	public Ecommerce create(Ecommerce ecommerce) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Ecommerce> request = new HttpEntity<>(ecommerce, headers);
		final ResponseEntity<Ecommerce> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Ecommerce.class);
		return response.getBody();
	}
	public Ecommerce update(Ecommerce ecommerce) {
		logger.info("update: " + ecommerce.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Ecommerce> request = new HttpEntity<>(ecommerce, headers);
		final ResponseEntity<Ecommerce> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Ecommerce.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Ecommerce> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Ecommerce> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Ecommerce.class);
	}
}
