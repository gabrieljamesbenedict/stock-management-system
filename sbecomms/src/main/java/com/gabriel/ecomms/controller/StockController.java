package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.model.Stock;
import com.gabriel.ecomms.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class StockController {
	Logger logger = LoggerFactory.getLogger( StockController.class);
	@Autowired
	private StockService stockService;
	//@GetMapping("/api/ecommerce")
	@GetMapping("/api/stock")
	public ResponseEntity<?> listEcommerce()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Stock[] stock = stockService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(stock);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	//@PutMapping("api/ecommerce")
	@PutMapping("api/stock")
	public ResponseEntity<?> add(@RequestBody Stock stock){
		logger.info("Input >> " + stock.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Stock newStock = stockService.create(stock);
			logger.info("created stock >> " + newStock.toString() );
			response = ResponseEntity.ok(newStock);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve stock with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	//@PostMapping("api/ecommerce")
	@PostMapping("api/stock")
	public ResponseEntity<?> update(@RequestBody Stock stock){
		logger.info("Update Input >> stock.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Stock newStock = stockService.update(stock);
			response = ResponseEntity.ok(stock);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve stock with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	//@GetMapping("api/ecommerce/{id}")
	@GetMapping("api/stock/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input stock id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Stock stock = stockService.get(id);
			response = ResponseEntity.ok(stock);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	//@DeleteMapping("api/ecommerce/{id}")
	@DeleteMapping("api/stock/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			stockService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
