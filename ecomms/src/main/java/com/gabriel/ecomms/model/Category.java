package com.gabriel.ecomms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Category{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
	@Override
	public String toString(){
		return name;
	}
}
