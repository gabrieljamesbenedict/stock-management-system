package com.gabriel.ecomms.transform;
import com.gabriel.ecomms.entity.StockData;
import com.gabriel.ecomms.model.Stock;
public interface TransformStockService {
	StockData transform(Stock stock);
	Stock transform(StockData stockData);
}
