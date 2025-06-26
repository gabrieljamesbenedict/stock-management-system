package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.InventoryData;
import com.gabriel.ecomms.model.Inventory;
import com.gabriel.ecomms.repository.InventoryDataRepository;
import com.gabriel.ecomms.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class InventoryServiceImpl implements InventoryService {
	Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
	@Autowired
	InventoryDataRepository inventoryDataRepository;
	@Autowired
	@Override
	public Inventory[] getAll() {
		List<InventoryData> inventorysData = new ArrayList<>();
		List<Inventory> inventorys = new ArrayList<>();
		inventoryDataRepository.findAll().forEach(inventorysData::add);
		Iterator<InventoryData> it = inventorysData.iterator();
		while(it.hasNext()) {
			InventoryData inventoryData = it.next();
			Inventory inventory = new Inventory();
			inventory.setId(inventoryData.getId());
			inventory.setName(inventoryData.getName());
			inventorys.add(inventory);
		}
		Inventory[] array = new Inventory[inventorys.size()];
		for  (int i=0; i<inventorys.size(); i++){
			array[i] = inventorys.get(i);
		}
		return array;
	}
	@Override
	public Inventory create(Inventory inventory) {
		logger.info(" add:Input " + inventory.toString());
		InventoryData inventoryData = new InventoryData();
		inventoryData.setName(inventory.getName());
		inventoryData = inventoryDataRepository.save(inventoryData);
		logger.info(" add:Input " + inventoryData.toString());
			Inventory newInventory = new Inventory();
			newInventory.setId(inventoryData.getId());
			newInventory.setName(inventoryData.getName());
		return newInventory;
	}

	@Override
	public Inventory update(Inventory inventory) {
		Inventory updatedInventory = null;
		int id = inventory.getId();
		Optional<InventoryData> optional  = inventoryDataRepository.findById(inventory.getId());
		if(optional.isPresent()){
			InventoryData originalInventoryData = new InventoryData();
			originalInventoryData.setId(inventory.getId());
			originalInventoryData.setName(inventory.getName());
			originalInventoryData.setCreated(optional.get().getCreated());
			InventoryData inventoryData = inventoryDataRepository.save(originalInventoryData);
			updatedInventory = new Inventory();
			updatedInventory.setId(inventoryData.getId());
			updatedInventory.setName(inventoryData.getName());
			updatedInventory.setCreated(inventoryData.getCreated());
			updatedInventory.setLastUpdated(inventoryData.getLastUpdated());
		}
		else {
			logger.error("Inventory record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedInventory;
	}

	@Override
	public Inventory get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Inventory inventory = null;
		Optional<InventoryData> optional = inventoryDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			inventory = new Inventory();
			inventory.setId(optional.get().getId());
			inventory.setName(optional.get().getName());
			inventory.setCreated(optional.get().getCreated());
			inventory.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return inventory;
	}
	@Override
	public void delete(Integer id) {
		Inventory inventory = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<InventoryData> optional = inventoryDataRepository.findById(id);
		if( optional.isPresent()) {
			InventoryData inventoryDatum = optional.get();
			inventoryDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Inventory record with id: " + Integer.toString(id));
			inventory = new Inventory();
			inventory.setId(optional.get().getId());
			inventory.setName(optional.get().getName());
			inventory.setCreated(optional.get().getCreated());
			inventory.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate inventory with id:" +  Integer.toString(id));
		}
	}
}
