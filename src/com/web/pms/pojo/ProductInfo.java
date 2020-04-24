package com.web.pms.pojo;

public class ProductInfo implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int prod_id;
	private String prod_name;
	private String prod_category;
	private int prod_price;
	
	public ProductInfo(int prod_id, String prod_name, String prod_category,int prod_price)
	{
		this.prod_id =prod_id;
		this.prod_name= prod_name;
		this.prod_category= prod_category;
		this.prod_price=prod_price;
	}
	
	public ProductInfo(String prod_name, String prod_category,int prod_price)
	{
		
		this.prod_name= prod_name;
		this.prod_category= prod_category;
		this.prod_price=prod_price;
	}


	public int getProd_id() {
		return prod_id;
	}


	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}


	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_category() {
		return prod_category;
	}

	public void setProd_category(String prod_category) {
		this.prod_category = prod_category;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	
}
