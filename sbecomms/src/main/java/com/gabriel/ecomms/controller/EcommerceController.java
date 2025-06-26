package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.service.EcommerceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class EcommerceController {
	Logger logger = LoggerFactory.getLogger( EcommerceController.class);
	@Autowired
	private EcommerceService ecommerceService;
	@GetMapping("/api/ecommerce")
	public ResponseEntity<?> listEcommerce()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Ecommerce[] ecommerce = ecommerceService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(ecommerce);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/ecommerce")
	public ResponseEntity<?> add(@RequestBody Ecommerce ecommerce){
		logger.info("Input >> " + ecommerce.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Ecommerce newEcommerce = ecommerceService.create(ecommerce);
			logger.info("created ecommerce >> " + newEcommerce.toString() );
			response = ResponseEntity.ok(newEcommerce);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve ecommerce with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/ecommerce")
	public ResponseEntity<?> update(@RequestBody Ecommerce ecommerce){
		logger.info("Update Input >> ecommerce.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Ecommerce newEcommerce = ecommerceService.update(ecommerce);
			response = ResponseEntity.ok(ecommerce);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve ecommerce with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/ecommerce/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input ecommerce id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Ecommerce ecommerce = ecommerceService.get(id);
			response = ResponseEntity.ok(ecommerce);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/ecommerce/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ecommerceService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
