package Logic;

import Dao.itemsDAO;
import Dao.items_out;
import Dao.items_outDAO;

public class StockOutService {
	items_out out = new items_out();
	items_outDAO dao = new items_outDAO();
	itemsDAO itemdao = new itemsDAO();
	
	public void insertData(int item_id, String date, int quantity) throws Exception {
		out.setDateOut(date);
		out.setQuantityOut(quantity);
		out.setItemId(item_id);

		dao.insertData(out);
		
		AutoRestock autorestock = new AutoRestock();
		autorestock.autorestock(item_id);
	}
	
	public void deleteData(int item_id) throws Exception {
		out.setItemOut(item_id);
		
		dao.deleteData(out);
	}
	
	public void editData(int item_id, String date, int quantity) throws Exception {
		out.setDateOut(date);
		out.setQuantityOut(quantity);
		out.setItemOut(item_id);
		
		dao.editData(out);
	}
}
