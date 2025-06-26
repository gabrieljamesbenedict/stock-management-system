package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.CategoryData;
import com.gabriel.ecomms.model.Category;
import com.gabriel.ecomms.repository.CategoryDataRepository;
import com.gabriel.ecomms.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CategoryServiceImpl implements CategoryService {
	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	CategoryDataRepository categoryDataRepository;
	@Autowired
	@Override
	public Category[] getAll() {
		List<CategoryData> categorysData = new ArrayList<>();
		List<Category> categorys = new ArrayList<>();
		categoryDataRepository.findAll().forEach(categorysData::add);
		Iterator<CategoryData> it = categorysData.iterator();
		while(it.hasNext()) {
			CategoryData categoryData = it.next();
			Category category = new Category();
			category.setId(categoryData.getId());
			category.setName(categoryData.getName());
			categorys.add(category);
		}
		Category[] array = new Category[categorys.size()];
		for  (int i=0; i<categorys.size(); i++){
			array[i] = categorys.get(i);
		}
		return array;
	}
	@Override
	public Category create(Category category) {
		logger.info(" add:Input " + category.toString());
		CategoryData categoryData = new CategoryData();
		categoryData.setName(category.getName());
		categoryData = categoryDataRepository.save(categoryData);
		logger.info(" add:Input " + categoryData.toString());
			Category newCategory = new Category();
			newCategory.setId(categoryData.getId());
			newCategory.setName(categoryData.getName());
		return newCategory;
	}

	@Override
	public Category update(Category category) {
		Category updatedCategory = null;
		int id = category.getId();
		Optional<CategoryData> optional  = categoryDataRepository.findById(category.getId());
		if(optional.isPresent()){
			CategoryData originalCategoryData = new CategoryData();
			originalCategoryData.setId(category.getId());
			originalCategoryData.setName(category.getName());
			originalCategoryData.setCreated(optional.get().getCreated());
			CategoryData categoryData = categoryDataRepository.save(originalCategoryData);
			updatedCategory = new Category();
			updatedCategory.setId(categoryData.getId());
			updatedCategory.setName(categoryData.getName());
			updatedCategory.setCreated(categoryData.getCreated());
			updatedCategory.setLastUpdated(categoryData.getLastUpdated());
		}
		else {
			logger.error("Category record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedCategory;
	}

	@Override
	public Category get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Category category = null;
		Optional<CategoryData> optional = categoryDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			category = new Category();
			category.setId(optional.get().getId());
			category.setName(optional.get().getName());
			category.setCreated(optional.get().getCreated());
			category.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return category;
	}
	@Override
	public void delete(Integer id) {
		Category category = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<CategoryData> optional = categoryDataRepository.findById(id);
		if( optional.isPresent()) {
			CategoryData categoryDatum = optional.get();
			categoryDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Category record with id: " + Integer.toString(id));
			category = new Category();
			category.setId(optional.get().getId());
			category.setName(optional.get().getName());
			category.setCreated(optional.get().getCreated());
			category.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate category with id:" +  Integer.toString(id));
		}
	}
}
