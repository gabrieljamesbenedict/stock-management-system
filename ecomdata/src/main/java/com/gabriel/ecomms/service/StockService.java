package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Stock;
public interface StockService {
	Stock[] getAll() throws Exception;
	Stock get(Integer id) throws Exception;
	Stock create(Stock stock) throws Exception;
	Stock update(Stock stock) throws Exception;
	void delete(Integer id) throws Exception;
}
