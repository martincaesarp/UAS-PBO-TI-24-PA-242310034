package Dao;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import Database.ConnectionDB;

public class itemsDAO {
    private String insert = "insert into items(items_id, item_name, date_in, quantity, safety_stock, classification, Rop, demand, lead_time) values(?,?,?,?,?,?,?,?,?)";
    private String Addupdate = "UPDATE items SET date_in=?, quantity=?, safety_stock=?, rop=? WHERE items_id=?";
    private String Editupdate = "UPDATE items SET item_name=?, date_in=?, quantity=?, safety_stock=?, classification=?, rop=? WHERE items_id=?";
    private String EditROP = "UPDATE items SET demand=?, lead_time=?, Rop=? WHERE items_id=?";
    private String view = "select * from items"; 
    private String count = "SELECT COUNT(*) AS total_items FROM items";
    private String viewbyClass = "SELECT * from items WHERE classification=?";
    private String delete = "DELETE FROM items WHERE item_name=?";
    private String viewRestock = "SELECT * FROM items WHERE safety_stock=0";
    private String countRestock = "SELECT COUNT(*) AS total FROM items WHERE safety_stock=0";
    private String viewSearch = "SELECT * FROM items WHERE item_name=? OR date_in=?";
    
    //Memasukkan data
    public void insertData (items item) throws Exception {
        Connection c = new ConnectionDB().connect();
        PreparedStatement in = c.prepareStatement(insert);
        
        in.setInt(1, item.getItem_id());
        in.setString(2, item.getItem_name());
        in.setString(3, item.getDate_in());
        in.setInt(4, item.getQuantity());
        in.setInt(5, item.getSafety());
        in.setString(6, item.getClassification());
        in.setInt(7, item.getRop());
        in.setInt(8, item.getDemand());
        in.setInt(9, item.getLeadTime());
        
        in.executeUpdate();
        c.close();
    }
    
    //Update data
    public void AddupdateData(items item) throws Exception {
        Connection c = new ConnectionDB().connect();
        PreparedStatement up = c.prepareStatement(Addupdate);

        up.setString(1, item.getDate_in());
        up.setInt(2, item.getQuantity());
        up.setInt(3, item.getSafety());
        up.setInt(4, item.getRop());
        up.setInt(5, item.getItem_id());

        up.executeUpdate();
        c.close();
    }
    
    public void EditData(items item) throws Exception {
    	Connection c = new ConnectionDB().connect();
        PreparedStatement up = c.prepareStatement(Editupdate);
        
        up.setString(1, item.getItem_name());
        up.setString(2, item.getDate_in());
        up.setInt(3, item.getQuantity());
        up.setInt(4, item.getSafety());
        up.setString(5, item.getClassification());
        up.setInt(6, item.getRop());
        up.setInt(7, item.getItem_id());
        
        up.executeUpdate();
        c.close();
    }
    
    public void EditROP(items item) throws Exception {
    	Connection c = new ConnectionDB().connect();
        PreparedStatement up = c.prepareStatement(EditROP);
        
        up.setInt(1, item.getDemand());
        up.setInt(2, item.getLeadTime());
        up.setInt(3, item.getRop());
        up.setInt(4, item.getItem_id());
        
        up.executeUpdate();
        c.close();
    }
    
    public void deletedata(items item) throws Exception {
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement del = c.prepareStatement(delete);
    	
    	del.setString(1, item.getItem_name());
    	
    	del.executeUpdate();    	
    	c.close();
    }

    
    public List<items> findAll() throws Exception {
    	List<items> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(view);
    	ResultSet rs = pCariproduk.executeQuery();    	
    	
    	while (rs.next()) {
    		items item = konversiResultSet(rs);
    		hasil.add(item);
    		
    	}
    	c.close();
    	return hasil;
    }
    
    public List<items> findbyCLassification(String classification) throws Exception {
    	List<items> hasil = new ArrayList<>();
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCariproduk = c.prepareStatement(viewbyClass);
    	pCariproduk.setString(1, classification);
    	ResultSet rs = pCariproduk.executeQuery();
    	
    	while (rs.next()) {
    		items item = konversiResultSet(rs);
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
    		items item = konversiResultSet(rs);
    		hasil.add(item);
    	}
    	c.close();
    	return hasil;
    }
    
    public List<items> findRestockItems() throws Exception {
        List<items> hasil = new ArrayList<>();
        Connection c = new ConnectionDB().connect();
        PreparedStatement ps = c.prepareStatement(viewRestock);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            items item = konversiResultSet(rs);
            hasil.add(item);
        }

        rs.close();
        ps.close();
        c.close();
        return hasil;
    }

    
    private items konversiResultSet(ResultSet rs) throws SQLException {
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
    
    public int countItems() throws Exception{
    	int total = 0;
    	Connection c = new ConnectionDB().connect();
    	PreparedStatement pCount = c.prepareStatement(count);
    	ResultSet rs = pCount.executeQuery();
    	
    	if(rs.next()) {
    		total = rs.getInt("total_items");
    	}
    	
    	rs.close();
    	pCount.close();
    	c.close();
    	
		return total;
    }
    
    public int countRestockItems() throws Exception {
        int total = 0;
        Connection c = new ConnectionDB().connect();
        PreparedStatement ps = c.prepareStatement(countRestock);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            total = rs.getInt("total");
        }

        rs.close();
        ps.close();
        c.close();
        return total;
    }
}
