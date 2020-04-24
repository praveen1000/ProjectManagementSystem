package com.web.pms.model;
import java.sql.*;
import javax.sql.DataSource;
import com.web.pms.pojo.*;

public class LoginDao 
{
	private static Connection con;
	private static PreparedStatement psmt;
	private static ResultSet rs;
	private static boolean validStatus = false;
	private static DataSource datasource;
	
	public LoginDao(DataSource datasource)
	{
		//variable is static so need to access at class level
		LoginDao.datasource = datasource;
	}
	public static boolean isUserValid(LoginInfo userdetails) throws ClassNotFoundException, SQLException
	{
		con = datasource.getConnection();
		
		psmt = con.prepareStatement("select * from public.login_info where username=? and password=?");
		psmt.setString(1, userdetails.getUsername());
		psmt.setString(2, userdetails.getPassword());
		rs= psmt.executeQuery();
		while(rs.next())
		{
			validStatus = true;	
		}

		con.close();
		psmt.close();
		rs.close();
		
		return validStatus;
	
	}

}
