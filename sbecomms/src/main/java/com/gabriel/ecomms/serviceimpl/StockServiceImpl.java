package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.StockData;
import com.gabriel.ecomms.model.Stock;
import com.gabriel.ecomms.repository.StockDataRepository;
import com.gabriel.ecomms.service.StockService;
import com.gabriel.ecomms.transform.TransformStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StockServiceImpl implements StockService {
	Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
	@Autowired
	StockDataRepository stockDataRepository;
	@Autowired
	TransformStockService tansformStockService;
	@Override
	public Stock[] getAll() {
		List<StockData> stocksData = new ArrayList<>();
		List<Stock> stocks = new ArrayList<>();
		stockDataRepository.findAll().forEach(stocksData::add);
		Iterator<StockData> it = stocksData.iterator();
		while(it.hasNext()) {
			StockData stockData = it.next();
			Stock stock = tansformStockService.transform(stockData);
			stocks.add(stock);
		}
		Stock[] array = new Stock[stocks.size()];
		for  (int i = 0; i< stocks.size(); i++){
			array[i] = stocks.get(i);
		}
		return array;
	}
	@Override
	public Stock create(Stock stock) {
		logger.info(" add:Input " + stock.toString());
		StockData stockData = tansformStockService.transform(stock);
		stockData = stockDataRepository.save(stockData);
		logger.info(" add:Input " + stockData.toString());
			Stock newStock = tansformStockService.transform(stockData);
		return newStock;
	}

	@Override
	public Stock update(Stock stock) {
		Stock updatedStock = null;
		int id = stock.getId();
		Optional<StockData> optional  = stockDataRepository.findById(stock.getId());
		if(optional.isPresent()){
			StockData originalStockData = tansformStockService.transform(stock);
			originalStockData.setCreated(optional.get().getCreated());
			StockData stockData = stockDataRepository.save(originalStockData);
			updatedStock = tansformStockService.transform(stockData);
		}
		else {
			logger.error("Stock record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedStock;
	}

	@Override
	public Stock get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Stock stock = null;
		Optional<StockData> optional = stockDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			stock = tansformStockService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return stock;
	}
	@Override
	public void delete(Integer id) {
		Stock stock = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<StockData> optional = stockDataRepository.findById(id);
		if( optional.isPresent()) {
			StockData stockDatum = optional.get();
			stockDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Stock record with id: " + Integer.toString(id));
			stock = tansformStockService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate Stock with id:" +  Integer.toString(id));
		}
	}
}
