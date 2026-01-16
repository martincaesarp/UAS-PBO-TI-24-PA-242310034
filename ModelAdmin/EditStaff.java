package ModelAdmin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Dao.User;
import Dao.UserDAO;
import Dao.items;
import Dao.itemsDAO;
import Logic.StaffService;
import Logic.StockService;

public class EditStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboitem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStaff frame = new EditStaff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditStaff() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 30));
		
		JTextPane txtpnItemName = new JTextPane();
		txtpnItemName.setEditable(false);
		txtpnItemName.setText("Staff Name:");
		contentPane.add(txtpnItemName);
		
		comboitem = new JComboBox<>();
		contentPane.add(comboitem);
		loadItemToCombo();
		
		JTextPane txtpnDemand = new JTextPane();
		txtpnDemand.setEditable(false);
		txtpnDemand.setText("New Name:");
		contentPane.add(txtpnDemand);
		
		JTextField textName = new JTextField();
		contentPane.add(textName);
		textName.setColumns(10);
		
		JTextPane txtpnLeadTime = new JTextPane();
		txtpnLeadTime.setEditable(false);
		txtpnLeadTime.setText("NIP:");
		contentPane.add(txtpnLeadTime);
		
		JTextField textNIP = new JTextField();
		contentPane.add(textNIP);
		textNIP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User selected = (User) comboitem.getSelectedItem();
					int idUser = selected.getUserId();
					
					String name = textName.getText();
					String nip = textNIP.getText();
					if(name.isEmpty() || nip.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					StaffService service = new StaffService();
					service.editDataStaff(idUser, name, nip);
					JOptionPane.showMessageDialog(null, "Staff data has been successfully updated.", null, JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while updating staff data.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnNewButton);

	}
	
	private void loadItemToCombo() {
		try {
			UserDAO userdao = new UserDAO();
			List<User> list = userdao.findAll();
			
			comboitem.removeAllItems();
			for (User itm : list) {
				comboitem.addItem(itm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
