package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Inventory;
public interface InventoryService {
	Inventory[] getAll() throws Exception;
	Inventory get(Integer id) throws Exception;
	Inventory create(Inventory inventory) throws Exception;
	Inventory update(Inventory inventory) throws Exception;
	void delete(Integer id) throws Exception;
}
