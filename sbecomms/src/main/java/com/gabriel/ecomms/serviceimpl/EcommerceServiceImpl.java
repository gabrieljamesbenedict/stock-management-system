package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.EcommerceData;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.repository.EcommerceDataRepository;
import com.gabriel.ecomms.service.EcommerceService;
import com.gabriel.ecomms.transform.TransformEcommerceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EcommerceServiceImpl implements EcommerceService {
	Logger logger = LoggerFactory.getLogger(EcommerceServiceImpl.class);
	@Autowired
	EcommerceDataRepository ecommerceDataRepository;
	@Autowired
	TransformEcommerceService tansformEcommerceService;
	@Override
	public Ecommerce[] getAll() {
		List<EcommerceData> ecommercesData = new ArrayList<>();
		List<Ecommerce> ecommerces = new ArrayList<>();
		ecommerceDataRepository.findAll().forEach(ecommercesData::add);
		Iterator<EcommerceData> it = ecommercesData.iterator();
		while(it.hasNext()) {
			EcommerceData ecommerceData = it.next();
			Ecommerce ecommerce = tansformEcommerceService.transform(ecommerceData);
			ecommerces.add(ecommerce);
		}
		Ecommerce[] array = new Ecommerce[ecommerces.size()];
		for  (int i=0; i<ecommerces.size(); i++){
			array[i] = ecommerces.get(i);
		}
		return array;
	}
	@Override
	public Ecommerce create(Ecommerce ecommerce) {
		logger.info(" add:Input " + ecommerce.toString());
		EcommerceData ecommerceData = tansformEcommerceService.transform(ecommerce);
		ecommerceData = ecommerceDataRepository.save(ecommerceData);
		logger.info(" add:Input " + ecommerceData.toString());
			Ecommerce newEcommerce = tansformEcommerceService.transform(ecommerceData);
		return newEcommerce;
	}

	@Override
	public Ecommerce update(Ecommerce ecommerce) {
		Ecommerce updatedEcommerce = null;
		int id = ecommerce.getId();
		Optional<EcommerceData> optional  = ecommerceDataRepository.findById(ecommerce.getId());
		if(optional.isPresent()){
			EcommerceData originalEcommerceData = tansformEcommerceService.transform(ecommerce);
			originalEcommerceData.setCreated(optional.get().getCreated());
			EcommerceData ecommerceData = ecommerceDataRepository.save(originalEcommerceData);
			updatedEcommerce = tansformEcommerceService.transform(ecommerceData);
		}
		else {
			logger.error("Ecommerce record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedEcommerce;
	}

	@Override
	public Ecommerce get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Ecommerce ecommerce = null;
		Optional<EcommerceData> optional = ecommerceDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			ecommerce = tansformEcommerceService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return ecommerce;
	}
	@Override
	public void delete(Integer id) {
		Ecommerce ecommerce = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<EcommerceData> optional = ecommerceDataRepository.findById(id);
		if( optional.isPresent()) {
			EcommerceData ecommerceDatum = optional.get();
			ecommerceDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Ecommerce record with id: " + Integer.toString(id));
			ecommerce = tansformEcommerceService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate ecommerce with id:" +  Integer.toString(id));
		}
	}
}
