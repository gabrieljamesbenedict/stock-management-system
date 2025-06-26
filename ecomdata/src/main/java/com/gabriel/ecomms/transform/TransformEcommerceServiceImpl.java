package com.gabriel.ecomms.transform;
import com.gabriel.ecomms.entity.EcommerceData;
import com.gabriel.ecomms.model.Ecommerce;
import org.springframework.stereotype.Service;
@Service
public class TransformEcommerceServiceImpl implements TransformEcommerceService {
	@Override
	public EcommerceData transform(Ecommerce ecommerce){
		EcommerceData ecommerceData = new EcommerceData();
		ecommerceData.setId(ecommerce.getId());
		ecommerceData.setName(ecommerce.getName());
		ecommerceData.setDescription(ecommerce.getDescription());
		ecommerceData.setProductId(ecommerce.getProductId());
		ecommerceData.setProductName(ecommerce.getProductName());
		ecommerceData.setCategoryId(ecommerce.getCategoryId());
		ecommerceData.setCategoryName(ecommerce.getCategoryName());
		ecommerceData.setStatusId(ecommerce.getStatusId());
		ecommerceData.setStatusName(ecommerce.getStatusName());
		ecommerceData.setQuantityAvailable(ecommerce.getQuantityAvailable());
		ecommerceData.setInventoryId(ecommerce.getInventoryId());
		ecommerceData.setInventoryName(ecommerce.getInventoryName());
		ecommerceData.setPrice(ecommerce.getPrice());
		return ecommerceData;
	}
	@Override

	public Ecommerce transform(EcommerceData ecommerceData){;
		Ecommerce ecommerce = new Ecommerce();
		ecommerce.setId(ecommerceData.getId());
		ecommerce.setName(ecommerceData.getName());
		ecommerce.setDescription(ecommerceData.getDescription());
		ecommerce.setProductId(ecommerceData.getProductId());
		ecommerce.setProductName(ecommerceData.getProductName());
		ecommerce.setCategoryId(ecommerceData.getCategoryId());
		ecommerce.setCategoryName(ecommerceData.getCategoryName());
		ecommerce.setStatusId(ecommerceData.getStatusId());
		ecommerce.setStatusName(ecommerceData.getStatusName());
		ecommerce.setQuantityAvailable(ecommerceData.getQuantityAvailable());
		ecommerce.setInventoryId(ecommerceData.getInventoryId());
		ecommerce.setInventoryName(ecommerceData.getInventoryName());
		ecommerce.setPrice(ecommerceData.getPrice());
		ecommerce.setCreated(ecommerceData.getCreated());
		ecommerce.setLastUpdated(ecommerceData.getLastUpdated());
		return ecommerce;
	}
}
