package com.gabriel.ecomms.transform;
import com.gabriel.ecomms.entity.EcommerceData;
import com.gabriel.ecomms.model.Ecommerce;
public interface TransformEcommerceService {
	EcommerceData transform(Ecommerce ecommerce);
	Ecommerce transform(EcommerceData ecommerceData);
}
