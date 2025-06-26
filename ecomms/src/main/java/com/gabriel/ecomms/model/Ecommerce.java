package com.gabriel.ecomms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Ecommerce{
	private int id;
	private String name;
	private String description;
	private int productId;
	private String productName;
	private int categoryId;
	private String categoryName;
	private int statusId;
	private String statusName;
	private double quantityAvailable;
	private int inventoryId;
	private String inventoryName;
	private double price;
	private Date lastUpdated;
	private Date created;
	@Override
	public String toString(){
		return name;
	}
}
