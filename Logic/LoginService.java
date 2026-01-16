package Logic;

import javax.swing.JOptionPane;

import Dao.User;
import Dao.UserDAO;
import ModelAdmin.Dashboard;
import ModelStaff.ItemsView;
import ModelStaff.ReportForm;

public class LoginService {
	UserDAO dao = new UserDAO();
	
	public void validation(String username, String password) {
		try {
			User user = dao.login(username, password);
			
			if(user == null) {
				JOptionPane.showMessageDialog(null, "Username or Password false!");
                return;
			}
			
			if(user.getRole().equals("ADMIN")) {
				JOptionPane.showMessageDialog(null, "Login succes!, as a ADMIN!");
				Dashboard dashboard = new Dashboard();
				dashboard.setVisible(true);
			} else if (user.getRole().equals("STAFF")) {
				JOptionPane.showMessageDialog(null, "Login succes!, as a STAFF!");
				ItemsView view = new ItemsView(user);
				view.setVisible(true);
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		
		
	}
}
