package com.web.pms.controller;
import com.web.pms.pojo.*;
import com.web.pms.model.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/User")
public class User extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private LoginInfo userdetails;
	boolean status;
	HttpSession session;
	RequestDispatcher rd;
	String message;
	
	@Resource(name="postgres/pms")
	private static DataSource dataSource;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		new LoginDao(dataSource);	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String logout = request.getParameter("val");
		if(logout.equals("logout"))
		{
			request.getSession(false).removeAttribute("product");
			request.getSession(false).removeAttribute("userName");
			request.getSession(false).invalidate();
			response.sendRedirect("login.jsp");	
		}	
//temp code
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		session= request.getSession();
		
		try 
		{
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
	
			userdetails = new LoginInfo(username, password);
			status= LoginDao.isUserValid(userdetails);
			
			if(status)
			{
				
				session.setAttribute("userName", username);
				session.setMaxInactiveInterval(20);
				response.sendRedirect("home.jsp");
				//rd = request.getRequestDispatcher("home.jsp");
				//rd.forward(request, response);	
			}
			else
			{
				message = "Please enter correct usename and password.";
				session.setAttribute("message", message);
				response.sendRedirect("login.jsp");
				/*
				 * Another way to do the same but in this servlet name will appear in the address bar and stays on that 
				 * because RequestDispatcher does not create new request.
				 */
				//request.setAttribute("message", message);
				//rd = request.getRequestDispatcher("/login.jsp");
				//rd.forward(request, response);	
			} 
		}
			catch (ClassNotFoundException e) 
			{
				System.out.println(e.getClass() +"not found.");	
			} 
			catch (SQLException e) 
			{
				System.out.println("Please check the SQL again.");
			}
	} 
	}


