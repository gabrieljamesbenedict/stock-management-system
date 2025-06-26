package com.gabriel.ecomms.service;
import com.gabriel.ecomms.model.Category;
public interface CategoryService {
	Category[] getAll() throws Exception;
	Category get(Integer id) throws Exception;
	Category create(Category category) throws Exception;
	Category update(Category category) throws Exception;
	void delete(Integer id) throws Exception;
}
