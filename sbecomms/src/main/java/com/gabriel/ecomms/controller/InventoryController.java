package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.model.Inventory;
import com.gabriel.ecomms.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class InventoryController {
	Logger logger = LoggerFactory.getLogger( InventoryController.class);
	@Autowired
	private InventoryService inventoryService;
	@GetMapping("/api/inventory")
	public ResponseEntity<?> listInventory()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Inventory[] inventory = inventoryService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(inventory);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/inventory")
	public ResponseEntity<?> add(@RequestBody Inventory inventory){
		logger.info("Input >> " + inventory.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Inventory newInventory = inventoryService.create(inventory);
			logger.info("created inventory >> " + newInventory.toString() );
			response = ResponseEntity.ok(newInventory);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve inventory with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/inventory")
	public ResponseEntity<?> update(@RequestBody Inventory inventory){
		logger.info("Update Input >> inventory.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Inventory newInventory = inventoryService.update(inventory);
			response = ResponseEntity.ok(inventory);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve inventory with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/inventory/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input inventory id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Inventory inventory = inventoryService.get(id);
			response = ResponseEntity.ok(inventory);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/inventory/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			inventoryService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
