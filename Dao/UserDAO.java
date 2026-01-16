package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionDB;

public class UserDAO {
	private String insert = "INSERT INTO employees(employees_id, name_employees, password, role) VALUE(?,?,?,?)";
	private String view = "SELECT * FROM employees WHERE role='STAFF'";
	private String login = "SELECT * FROM employees WHERE name_employees=? AND password=?";
	private String edit = "UPDATE employees SET name_employees=?, password=? WHERE employees_id=?";
	private String delete = "DELETE FROM employees WHERE name_employees=?";
	private String viewbyname = "SELECT * FROM employees WHERE name_employees=?";
	
	public void insertData(User user) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(insert);
		
		ps.setInt(1, user.getUserId());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getRole());
		
		ps.executeUpdate();
		c.close();
	}
	
	public void editData(User user) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(edit);
		
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getUserId());
		
		ps.executeUpdate();
		c.close();
	}
	
	public void delete(User user) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(delete);
		
		ps.setString(1, user.getUsername());
		
		ps.executeUpdate();
		c.close();
	}
	
	public List<User> findAll() throws Exception {
    	List<User> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement ps = c.prepareStatement(view);
    	ResultSet rs = ps.executeQuery();    	
    	
    	while (rs.next()) {
    		User user = konversiResultSet(rs);
    		hasil.add(user);
    		
    	}
    	c.close();
    	return hasil;
    }
	
	public List<User> findbyName(String name) throws Exception {
    	List<User> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement ps = c.prepareStatement(viewbyname);
    	ps.setString(1, name);
    	ResultSet rs = ps.executeQuery();
    	
    	while (rs.next()) {
    		User user = konversiResultSet(rs);
    		hasil.add(user);
    	}
    	c.close();
    	return hasil;
    }
	
	private User konversiResultSet(ResultSet rs) throws Exception {
		User user = new User();
		user.setUserId(rs.getInt("employees_id"));
		user.setUsername(rs.getString("name_employees"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		return user;
	}
	
	public User login(String username, String password) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(login);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
	        User user = konversiResultSet(rs);
	        c.close();
	        return user; 
	    } else {
	        c.close();
	        return null; 
	    }
	}
}
