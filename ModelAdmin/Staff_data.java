package ModelAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Authentication.Login;
import Dao.Staff;
import Dao.User;
import Dao.UserDAO;
import Dao.items;
import Dao.itemsDAO;
import Dao.report;
import Dao.reportDAO;
import Logic.StaffService;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Staff_data extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textStaff;
	private JTextField textPassword;
	private JTable table;
	private JTextField textName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_data frame = new Staff_data();
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
	public Staff_data() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 505);
		JPanel forPane = new JPanel();
		forPane.setForeground(new Color(255, 255, 255));
		forPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forPane);
		forPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		forPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel titled1 = new JLabel("INVENTORY MANAGEMENT");
		titled1.setHorizontalAlignment(SwingConstants.CENTER);
		titled1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(titled1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("FOOTWEAR INDUSTRY");
		lblNewLabel.setForeground(new Color(0, 64, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		forPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton Dashboard = new JButton("Dashboard");
		Dashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard board = new Dashboard();
				board.setVisible(true);
				board.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(Dashboard);
		
		JButton stock = new JButton("Stock Items");
		stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock_items items = new Stock_items();
				items.setVisible(true);
				items.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		stock.setAlignmentX(2.0f);
		stock.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.add(stock);
		
		JButton stckOut = new JButton("Stock Out");
		stckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock_out out = new Stock_out();
				out.setVisible(true);
				out.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		panel_1.add(stckOut);
		
		JButton report = new JButton("Staff Data");
		panel_1.add(report);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Staff Data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		forPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_2 = new JButton("Staff");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewData();
			}
		});
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reports");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_Report report = new Staff_Report();
				report.setVisible(true);
				report.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		panel_3.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Staff", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JTextPane txtpnNameStaff = new JTextPane();
		txtpnNameStaff.setEditable(false);
		txtpnNameStaff.setText("Name Staff:");
		panel_5.add(txtpnNameStaff);
		
		textStaff = new JTextField();
		panel_5.add(textStaff);
		textStaff.setColumns(10);
		
		JTextPane textpsswrd = new JTextPane();
		textpsswrd.setEditable(false);
		textpsswrd.setText("NIP:");
		panel_5.add(textpsswrd);
		
		textPassword = new JTextField();
		panel_5.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textStaff.getText();
					String password = textPassword.getText();
					if(name.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					StaffService staffservice = new StaffService();
					staffservice.insertDataStaff(name, password);
					JOptionPane.showMessageDialog(null, "Staff data has been successfully added", null, JOptionPane.INFORMATION_MESSAGE);
					viewData();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Failed to add data, Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_5.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_6, BorderLayout.SOUTH);
		
		JTextPane txtpnFind = new JTextPane();
		txtpnFind.setText("Find:");
		panel_6.add(txtpnFind);
		
		textName = new JTextField();
		panel_6.add(textName);
		textName.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Submit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				if(name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a staff name to search.", null, JOptionPane.WARNING_MESSAGE);
				}
				viewDataByName(name);
			}
		});
		panel_6.add(btnNewButton_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		forPane.add(panel_7, BorderLayout.EAST);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_5 = new JButton("Edit Staff\r\n");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStaff edit = new EditStaff();
				edit.setVisible(true);
			}
		});
		panel_7.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Delete");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStaff del = new DeleteStaff();
				del.setVisible(true);
			}
		});
		panel_7.add(btnNewButton_6);
		viewData();
	}
	
	public void viewData() {
		String[] atribut = {"Staff Name", "NIP", "Role"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		
		try {
			UserDAO dao = new UserDAO();
			List<User> itemlist = dao.findAll();
			
			for(User usr : itemlist) {
				Object[] rowData = {
						usr.getUsername(),
						usr.getPassword(),
						usr.getRole()
				};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to load staff data.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}
	
	public void viewDataByName(String name) {
		String[] atribut = {"Staff Name", "NIP", "Role"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		
		try {
			UserDAO dao = new UserDAO();
			List<User> itemlist = dao.findbyName(name);
			
			for(User usr : itemlist) {
				Object[] rowData = {
						usr.getUsername(),
						usr.getPassword(),
						usr.getRole()
				};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "failed to load data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}
}
