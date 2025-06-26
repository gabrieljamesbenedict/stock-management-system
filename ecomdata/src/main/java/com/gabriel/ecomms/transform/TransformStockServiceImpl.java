package com.gabriel.ecomms.transform;
import com.gabriel.ecomms.entity.StockData;
import com.gabriel.ecomms.model.Stock;
import org.springframework.stereotype.Service;
@Service
public class TransformStockServiceImpl implements TransformStockService {
	@Override
	public StockData transform(Stock stock){
		StockData stockData = new StockData();
		stockData.setId(stock.getId());
		stockData.setName(stock.getName());
		stockData.setDescription(stock.getDescription());
		stockData.setProductId(stock.getProductId());
		stockData.setProductName(stock.getProductName());
		stockData.setCategoryId(stock.getCategoryId());
		stockData.setCategoryName(stock.getCategoryName());
		stockData.setStatusId(stock.getStatusId());
		stockData.setStatusName(stock.getStatusName());
		stockData.setQuantityAvailable(stock.getQuantityAvailable());
		stockData.setInventoryId(stock.getInventoryId());
		stockData.setInventoryName(stock.getInventoryName());
		stockData.setPrice(stock.getPrice());
		return stockData;
	}
	@Override

	public Stock transform(StockData stockData){;
		Stock stock = new Stock();
		stock.setId(stockData.getId());
		stock.setName(stockData.getName());
		stock.setDescription(stockData.getDescription());
		stock.setProductId(stockData.getProductId());
		stock.setProductName(stockData.getProductName());
		stock.setCategoryId(stockData.getCategoryId());
		stock.setCategoryName(stockData.getCategoryName());
		stock.setStatusId(stockData.getStatusId());
		stock.setStatusName(stockData.getStatusName());
		stock.setQuantityAvailable(stockData.getQuantityAvailable());
		stock.setInventoryId(stockData.getInventoryId());
		stock.setInventoryName(stockData.getInventoryName());
		stock.setPrice(stockData.getPrice());
		stock.setCreated(stockData.getCreated());
		stock.setLastUpdated(stockData.getLastUpdated());
		return stock;
	}
}
