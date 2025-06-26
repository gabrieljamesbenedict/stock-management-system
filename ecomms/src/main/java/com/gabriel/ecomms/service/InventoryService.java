package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Inventory;
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
public class InventoryService {
	Logger logger = LoggerFactory.getLogger(InventoryService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/inventory";

	protected static InventoryService service= null;
	public static InventoryService getService(){
		if(service == null){
			service = new InventoryService();
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

	public Inventory get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Inventory> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Inventory.class);
		return response.getBody();
	}

	public Inventory[] getAll() {
		String url = endpointUrl;
		logger.info("getInventorys: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Inventory[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Inventory[].class);
		Inventory[] inventorys = response.getBody();
		return inventorys;
	}

	public Inventory create(Inventory inventory) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Inventory> request = new HttpEntity<>(inventory, headers);
		final ResponseEntity<Inventory> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Inventory.class);
		return response.getBody();
	}
	public Inventory update(Inventory inventory) {
		logger.info("update: " + inventory.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Inventory> request = new HttpEntity<>(inventory, headers);
		final ResponseEntity<Inventory> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Inventory.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Inventory> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Inventory> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Inventory.class);
	}
}
