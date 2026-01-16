package Logic;

import java.sql.*;

import javax.swing.JOptionPane;

import Dao.items;
import Database.ConnectionDB;

public class AutoRestock {
	public static void autorestock(int itemId) {
		try {
			String selectedItem = "SELECT quantity, Rop, safety_stock FROM items WHERE items_id=?";
			Connection c = new ConnectionDB().connect();
			PreparedStatement ps = c.prepareStatement(selectedItem);
			ps.setInt(1, itemId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int rop = rs.getInt("Rop");
				int quantity = rs.getInt("quantity");
				int safetyStock = rs.getInt("safety_stock");
				
				if (quantity <= rop && safetyStock > 0) {
					quantity = quantity + safetyStock;
					safetyStock = 0;
				}
				
				String UpdateQuantity = "UPDATE items SET quantity=?, safety_stock=? WHERE items_id=?";
				PreparedStatement psU = c.prepareStatement(UpdateQuantity);
				psU.setInt(1, quantity);
				psU.setInt(2, safetyStock);
				psU.setInt(3, itemId);
				
				psU.executeUpdate();
				psU.close();
			} 
			rs.close();
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Data error");
		}
	}
}
