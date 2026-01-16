package ModelStaff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Authentication.Login;
import Dao.User;
import Dao.items;
import Dao.itemsDAO;
import Logic.ReportService;
import Logic.LoginService;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;

public class ReportForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDescription;
	private JTextField textDate;
	private User user;
	private JComboBox comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User usr = new User();
					ReportForm frame = new ReportForm(usr);
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
	public ReportForm(User user) {
		this.user = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("INVENTORY MANAGEMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("FOOTWEAR INDUSTRY");
		lblNewLabel_1.setForeground(new Color(0, 64, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome: " + user.getUsername()); 
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 15));
		
		JTextPane txtpnItemName = new JTextPane();
		txtpnItemName.setEditable(false);
		txtpnItemName.setText("Item Name:");
		panel_4.add(txtpnItemName);
		
		comboBox = new JComboBox();
		panel_4.add(comboBox);
		loadItemToCombo();
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setText("Description:");
		txtpnDescription.setEditable(false);
		panel_4.add(txtpnDescription);
		
		textDescription = new JTextField();
		panel_4.add(textDescription);
		textDescription.setColumns(10);
		
		JTextPane txtpnDate = new JTextPane();
		txtpnDate.setText("Date:");
		txtpnDate.setEditable(false);
		panel_4.add(txtpnDate);
		
		textDate = new JTextField();
		panel_4.add(textDate);
		textDate.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_4.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					items selected = (items) comboBox.getSelectedItem();
					int idItem = selected.getItem_id();
					String description = textDescription.getText();
					String date = textDate.getText();
					int idStaff = user.getUserId(); 
					
					if(description.isEmpty() || date.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					ReportService service = new ReportService();
					service.insertReport(idItem, description, date, idStaff);
					JOptionPane.showMessageDialog(null, "Report data has been successfully Added", null, JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Failed to add data, Please try again", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_4.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Stock Items");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemsView view = new ItemsView(user);
				view.setVisible(true);
				dispose();
			}
		});
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Report");
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Log Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respone = JOptionPane.showConfirmDialog(null, "Are you sure want Log Out?", null, JOptionPane.YES_NO_OPTION);
				if(respone == JOptionPane.YES_OPTION) {
					Login login = new Login();
					login.setVisible(true);
					dispose();
				} else {
					return;
				}
			}
		});
		panel_3.add(btnNewButton_3);
	}
	
	private void loadItemToCombo() {
		try {
			itemsDAO itemdao = new itemsDAO();
			List<items> list = itemdao.findAll();
			
			comboBox.removeAllItems();
			for (items itm : list) {
				comboBox.addItem(itm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
