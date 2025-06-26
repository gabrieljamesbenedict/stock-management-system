package com.gabriel.ecomms.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
//@Table(name = "ecommerce_data")
@Table(name = "stock_data")
public class StockData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastUpdated;


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date created;

}
