package Logic;

import Dao.Staff;
import Dao.UserDAO;
import ModelAdmin.Stock_items;

public class StaffService {
	Staff staff = new Staff();
	UserDAO dao = new UserDAO();

	public void insertDataStaff(String name, String password) throws Exception {
		staff.setUsername(name);
		staff.setPassword(password);
		
		dao.insertData(staff);	
	}
	
	public void editDataStaff(int item_id, String name, String password) throws Exception {
		staff.setUsername(name);
		staff.setPassword(password);
		staff.setUserId(item_id);
		
		dao.editData(staff);		
	}
	
	public void deleteData(String username) throws Exception {
		staff.setUsername(username);
		
		dao.delete(staff);
	}
}
