package Dao;

import Database.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reportDAO {
	private String insert = "INSERT INTO report(item_id_report, report_description, report_date, employees_id_report) VALUES(?,?,?,?)";
	private String view = "SELECT items.item_name AS items_name, report.report_description, report.report_date, employees.name_employees " +
            "FROM report " +
            "JOIN items ON report.item_id_report = items.items_id " +
            "JOIN employees ON report.employees_id_report = employees.employees_id";
	private String viewbyname = "SELECT items.item_name AS items_name, report.report_description, report.report_date, employees.name_employees " +
            "FROM report " +
            "JOIN items ON report.item_id_report = items.items_id " +
            "JOIN employees ON report.employees_id_report = employees.employees_id " +
            "WHERE employees.name_employees=?";
	private String viewItems = "select * from items"; 
	private String viewSearch = "SELECT * FROM items WHERE item_name=? OR date_in=?";
	private String count = "SELECT COUNT(*) AS total FROM report";	 
	
	public void insertData(report Report) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(insert);
		
		ps.setInt(1, Report.getItemId());
		ps.setString(2, Report.getDescription());
		ps.setString(3, Report.getReportDate());
		ps.setInt(4, Report.getEmployeesId());
		
		ps.executeUpdate();
		c.close();
	}
	
	public List<report> findAll() throws Exception {
	    List<report> list = new ArrayList<>();
	    Connection c = new ConnectionDB().connect();
	    PreparedStatement ps = c.prepareStatement(view);
	    ResultSet rs = ps.executeQuery(); 

	    while (rs.next()) {
    		report r = konversiResultSet(rs);
    		list.add(r);
    	}
	    
    	c.close();
    	return list;
	}
	
	public List<items> findAllItems() throws Exception {
    	List<items> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(viewItems);
    	ResultSet rs = pCariproduk.executeQuery();    	
    	
    	while (rs.next()) {
    		items item = konversiResultSetItems(rs);
    		hasil.add(item);
    		
    	}
    	c.close();
    	return hasil;
    }
	
	public List<items> findbyNameDate(String keyword) throws Exception {
    	List<items> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(viewSearch);
    	pCariproduk.setString(1, keyword);
    	pCariproduk.setString(2, keyword);
    	ResultSet rs = pCariproduk.executeQuery();
    	
    	while (rs.next()) {
    		items item = konversiResultSetItems(rs);
    		hasil.add(item);
    	}
    	c.close();
    	return hasil;
    }
	
	public List<report> findbyName(String name) throws Exception {
    	List<report> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement ps = c.prepareStatement(viewbyname);
    	ps.setString(1, name);
    	ResultSet rs = ps.executeQuery();
    	
    	while (rs.next()) {
    		report r = konversiResultSet(rs);
    		hasil.add(r);
    	}
    	c.close();
    	return hasil;
    }
	
	private report konversiResultSet(ResultSet rs) throws Exception {
	    report r = new report();
	    r.setItemName(rs.getString("items_name")); 
	    r.setDescription(rs.getString("report_description"));
	    r.setReportDate(rs.getString("report_date"));
	    r.setEmployeesName(rs.getString("name_employees"));
	    return r;
	}
	
	private items konversiResultSetItems(ResultSet rs) throws SQLException {
		items itm = new items();
		itm.setItem_id(rs.getInt("items_id"));
		itm.setItem_name(rs.getString("item_name"));
		itm.setDate_in(rs.getString("date_in"));
		itm.setQuantity(rs.getInt("quantity"));
		itm.setSafety(rs.getInt("safety_stock"));
		itm.setClassification(rs.getString("classification"));
		itm.setRop(rs.getInt("Rop"));
		itm.setDemand(rs.getInt("demand"));
	    itm.setLeadTime(rs.getInt("lead_time"));
		return itm;
	}
	
	 public int countReport() throws Exception{
	    	int total = 0;
	    	Connection c = new ConnectionDB().connect();
	    	PreparedStatement pCount = c.prepareStatement(count);
	    	ResultSet rs = pCount.executeQuery();
	    	
	    	if(rs.next()) {
	    		total = rs.getInt("total");
	    	}
	    	
	    	rs.close();
	    	pCount.close();
	    	c.close();
	    	
			return total;
	    }
}
