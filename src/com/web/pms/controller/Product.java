package com.web.pms.controller;

import com.web.pms.model.*;
import com.web.pms.pojo.*;
import java.util.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Product")
public class Product extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Resource(name="postgres/pms")
	private static DataSource dataSource;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		new ProductDao(dataSource);	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{   
		if(request.isRequestedSessionIdValid())
		{
			String val = request.getParameter("val");
			switch(val)
			{
				case "viewProduct":
					viewProduct(request, response);
					break;

				case "getEditProduct":
					editProduct(request, response, val);
					break;

				case "deleteProduct":
					deleteProduct(request, response);
					break;
			}	
		}
		else
		{
			response.sendRedirect("login.jsp");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.isRequestedSessionIdValid())
		{
			String val= request.getParameter("val");
			switch (val)
			{
				case "addProduct":
					addProduct(request, response);
					break;
					
				case "searchProduct":
					searchProduct(request, response);
					break;
					
				case "postEditProduct":
					editProduct(request, response, val);
					break;	
			}
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}
	protected void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		try 
		{
			String productName = request.getParameter("prodName");
			String productCategory = request.getParameter("prodCategory");
			int productPrice = Integer.parseInt(request.getParameter("prodPrice"));

			ProductInfo product = new ProductInfo(productName,productCategory,productPrice);
			
			int status = ProductDao.addProduct(product);
			if(status == 1)
			{
				viewProduct(request, response); 
			}
			else
			{
				
				response.sendRedirect("addProduct.jsp"); 
			}	
		}
		catch (NullPointerException e) 
		{
			System.out.println("Please start your database connection");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Model class not found");
		} 
		catch (SQLException e) 
		{
			System.out.println("Please check your SQL query");
		}	
	}
	
	//Suppress the warning generated by complier
	@SuppressWarnings("null")
	protected void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			String productId = request.getParameter("prodId");
			if(productId != null && !productId.isEmpty() && productId.matches("^[0-9]*$"))
			{
				ProductInfo product = ProductDao.getProductById(productId);
				if(product != null)
				{
					request.getSession(false).setAttribute("product", product);
					response.sendRedirect("searchProduct.jsp");	
				}
				else
				{
					String message = "Product with the requested Id doesn't exist";
					request.getSession(false).setAttribute("message", message);
					response.sendRedirect("searchProduct.jsp");
				}	
			}
			else
			{
				String message = "Product Id can't be empty or invalid.";
				request.getSession(false).setAttribute("message", message);
				response.sendRedirect("searchProduct.jsp");	
			}	
		}
		catch (ClassNotFoundException e) 
		{	
			System.out.println("Model class not found");
		} 
		catch (SQLException e) 
		{
			System.out.println("Please check your SQL query");
		}	
	}
	
	protected void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			List<ProductInfo> productList = ProductDao.getAllProducts();
			request.getSession(false).setAttribute("productList", productList);
			response.sendRedirect("viewProduct.jsp");
		} 
		catch (NullPointerException e) 
		{
			System.out.println("Please start your database connection");
		} 
		catch (ClassNotFoundException e) 
		{	
			System.out.println("Model class not found");
		} 
		catch (SQLException e) 
		{
			System.out.println("Please check your SQL query");
		}
	}
	
	protected void editProduct(HttpServletRequest request, HttpServletResponse response, String val ) throws ServletException, IOException
	{
		System.out.println("i am inside edit product method");
		//int productId = Integer.parseInt(request.getParameter("productId"));
	
		try 
		{
			if(val.equals("getEditProduct"))
			{
				String productId = request.getParameter("prodId");
				System.out.println(productId);
				ProductInfo product = ProductDao.getProductById(productId);
				request.getSession(false).setAttribute("product", product);
				response.sendRedirect("editProduct.jsp");	
			}
			
			else if(val.equals("postEditProduct"))
			{
				int productId = Integer.parseInt(request.getParameter("productId"));
				String productName = request.getParameter("prodName");
				String productCategory = request.getParameter("prodCategory");
				int productPrice = Integer.parseInt(request.getParameter("prodPrice"));

				ProductInfo product = new ProductInfo(productId,productName,productCategory,productPrice);

				int status = ProductDao.updateProduct(product);
				if(status == 1)
				{
					viewProduct(request, response);
				}
				else
				{
					response.sendRedirect("home.jsp"); 
				}
			}
			else
			{
				System.out.println("Runtime Parameter is empty");
			}
			
		}
		catch (NullPointerException e) 
		{
			System.out.println("Please start your database connection");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Model class not found");
		} 
		catch (SQLException e) 
		{
			System.out.println("Please check your SQL query");
		}
	}
	
	protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			int productId = Integer.parseInt(request.getParameter("prodId"));
			
			int status = ProductDao.deleteProduct(productId);
			if(status == 1)
			{
				viewProduct(request, response);
			}
			else
			{
				response.sendRedirect("home.jsp"); 
			}
			
		}
		
		catch (NullPointerException e) 
		{
			System.out.println("Please start your database connection");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Model class not found");
		} 
		catch (SQLException e) 
		{
			System.out.println("Please check your SQL query");
		}

	}

}