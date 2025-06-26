package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Ecommerce;
public interface EcommerceService {
	Ecommerce[] getAll() throws Exception;
	Ecommerce get(Integer id) throws Exception;
	Ecommerce create(Ecommerce ecommerce) throws Exception;
	Ecommerce update(Ecommerce ecommerce) throws Exception;
	void delete(Integer id) throws Exception;
}
