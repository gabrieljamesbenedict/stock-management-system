package com.gabriel.ecomms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Inventory{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
}
