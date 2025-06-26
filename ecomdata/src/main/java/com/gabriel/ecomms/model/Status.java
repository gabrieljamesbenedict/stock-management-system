package com.gabriel.ecomms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Status{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
}
