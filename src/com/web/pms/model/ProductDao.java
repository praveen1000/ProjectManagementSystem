package com.web.pms.model;
import com.web.pms.pojo.*;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class ProductDao 
{
	private static Connection con;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	private static DataSource datasource;
	private static int status = 0;
	private static String sql; 
	
	public ProductDao(DataSource datasource)
	{
		//variable is static so need to access at class level
		ProductDao.datasource = datasource;
	}
	
	public static List<ProductInfo> getAllProducts() throws ClassNotFoundException, SQLException
	{
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		con = datasource.getConnection();
		sql ="Select * from public.product";
		psmt = con.prepareStatement(sql);
		rs = psmt.executeQuery();
		while (rs.next())
		{
			ProductInfo product = new ProductInfo(rs.getInt("prod_id"), rs.getString("prod_name"), rs.getString("prod_category"), rs.getInt("prod_price"));
			productList.add(product);
		}
		
		closeConnection();
		closeStatement();
		closeResultSet();
		return productList;
	}
	
	public static int addProduct(ProductInfo product) throws SQLException, ClassNotFoundException
	{	
		sql = "insert into public.product (prod_name, prod_category, prod_price) VALUES(?,?,?)";
		con = datasource.getConnection();
		psmt= con.prepareStatement(sql);
		psmt.setString(1, product.getProd_name());
		psmt.setString(2, product.getProd_category());
		psmt.setInt(3, product.getProd_price());
		status = psmt.executeUpdate();
		
		closeConnection();
		closeStatement();
		return status;
	}
	
	public static ProductInfo getProductById(String productId) throws ClassNotFoundException, SQLException
	{
		ProductInfo product = null;
		sql = "select * from public.product WHERE prod_id = ?";
		con = datasource.getConnection();
		psmt= con.prepareStatement(sql);
		psmt.setInt(1, Integer.parseInt(productId));
		rs = psmt.executeQuery();
		while(rs.next())
		{
			product = new ProductInfo(rs.getInt("prod_id"),rs.getString("prod_name"),rs.getString("prod_category"),rs.getInt("prod_price"));
		}
		
		closeConnection();
		closeStatement();
		closeResultSet();
		return product;
	}
	
	public static int updateProduct(ProductInfo product) throws ClassNotFoundException, SQLException
	{
		sql ="update public.product set prod_name=?, prod_category=?, prod_price=? where prod_id=?";
		con = datasource.getConnection();
		psmt= con.prepareStatement(sql);
		psmt.setString(1, product.getProd_name());
		psmt.setString(2, product.getProd_category());
		psmt.setInt(3, product.getProd_price());
		psmt.setInt(4, product.getProd_id());
		status = psmt.executeUpdate();
		
		closeConnection();
		closeStatement();
		return status;
		
	}
	
	public static int deleteProduct(int productId) throws ClassNotFoundException, SQLException
	{
		sql ="delete from public.product where prod_id = ?";
		con = datasource.getConnection();
		psmt= con.prepareStatement(sql);
		psmt.setInt(1, productId);
		status = psmt.executeUpdate();
		
		closeConnection();
		closeStatement();
		return status;
	}
	
	public void closeConnection()
	{
		con.close();
	}
	public void closeResultSet()
	{
		rs.close();
	}
	public void closeStatement()
	{
		psmt.close();
	}
	
}
