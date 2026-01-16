	package Dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Database.ConnectionDB;

public class items_outDAO {
	private String insert = "insert into items_out(date_out, quantity_out, item_id) values(?,?,?)";
	private String view =
		    "SELECT " +
		    "items_out.item_out_id, " +
		    "items.item_name, " +
		    "items_out.date_out, " +
		    "items_out.quantity_out, " +
		    "items.classification " +
		    "FROM items_out " +
		    "JOIN items ON items_out.item_id = items.items_id";
	private String editData = "UPDATE items_out SET date_out=?, quantity_out=? WHERE item_out_id=?";
	private String delData = "DELETE FROM items_out WHERE item_out_id=?";
	private String viewByClass =
			"SELECT " +
			"items_out.item_out_id, " +
			"items.item_name, " +
			"items_out.date_out, " +
			"items_out.quantity_out, " +
			"items.classification " +
			"FROM items_out " +
			"JOIN items ON items_out.item_id = items.items_id " +
			"WHERE items.classification=?";
	private String count = "SELECT COUNT(*) AS total_itemsOut FROM items_out";
	private String search = 
			"SELECT " +
			"items_out.item_out_id, " +
			"items.item_name, " +
			"items_out.date_out, " +
			"items_out.quantity_out, " +
			"items.classification " +
			"FROM items_out " +
			"JOIN items ON items_out.item_id = items.items_id " +
			"WHERE items.item_name=? OR items_out.date_out=?";
	
	public void insertData (items_out itemout) throws Exception {
        Connection c = new ConnectionDB().connect();
        PreparedStatement in = c.prepareStatement(insert);
        
        in.setString(1, itemout.getDateOut());
        in.setInt(2, itemout.getQuantityOut());
        in.setInt(3, itemout.getItemId());
        
        in.executeUpdate();
        c.close();
    }
	
	public void editData (items_out itemout) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(editData);
		
		ps.setString(1, itemout.getDateOut());     
		ps.setInt(2, itemout.getQuantityOut());     
		ps.setInt(3, itemout.getItemOut()); 
		
		ps.executeUpdate();
		c.close();
	}
	
	public void deleteData(items_out itemout) throws Exception {
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(delData);
		
		ps.setInt(1, itemout.getItemOut());
		
		ps.executeUpdate();
		c.close();
	}
	
	//melihat data
    public List<items_out> findAllOut() throws Exception {
    	List<items_out> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(view);
    	ResultSet rs = pCariproduk.executeQuery();    	
    	
    	while (rs.next()) {
    		items_out out = konversiOutSet(rs);
    		hasil.add(out);
    		
    	}
    	c.close();
    	return hasil;
    }
    
    public List<items_out> findbyCLassification(String classification) throws Exception {
    	List<items_out> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(viewByClass);
    	pCariproduk.setString(1, classification);
    	ResultSet rs = pCariproduk.executeQuery();
    	
    	while (rs.next()) {
    		items_out out = konversiOutSet(rs);
    		hasil.add(out);
    	}
    	c.close();
    	return hasil;
    }
    
    public List<items_out> findbyNameDate(String keyword) throws Exception {
    	List<items_out> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(search);
    	pCariproduk.setString(1, keyword);
    	pCariproduk.setString(2, keyword);
    	ResultSet rs = pCariproduk.executeQuery();
    	
    	while (rs.next()) {
    		items_out itemOut = konversiOutSet(rs);
    		hasil.add(itemOut);
    	}
    	c.close();
    	return hasil;
    }
    
    private items_out konversiOutSet(ResultSet rs) throws SQLException {
        items_out itmOut = new items_out();

        itmOut.setItemOut(rs.getInt("item_out_id"));
        itmOut.setItemName(rs.getString("item_name"));
        itmOut.setDateOut(rs.getString("date_out"));
        itmOut.setQuantityOut(rs.getInt("quantity_out"));
        itmOut.setClassification(rs.getString("classification"));

        return itmOut;
    }
    
    public int countItems() throws Exception{
    	int total = 0;
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCount = c.prepareStatement(count);
    	ResultSet rs = pCount.executeQuery();
    	
    	if(rs.next()) {
    		total = rs.getInt("total_itemsOut");
    	}
    	
    	rs.close();
    	pCount.close();
    	c.close();
    	
		return total;
    }
}
