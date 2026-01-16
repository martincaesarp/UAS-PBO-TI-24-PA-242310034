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

public class Staff_Report extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_Report frame = new Staff_Report();
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
	public Staff_Report() {
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
				int respone = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
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
				Staff_data data = new Staff_data();
				data.setVisible(true);
				data.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reports");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				viewReports();
			}
		});
		panel_3.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
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
		
		JTextField textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Submit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				if (name.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter a name to search.");
		        } else {
		            viewDataByName(name);
		        }
			}
		});
		panel_6.add(btnNewButton_4);
		viewReports();
	}
	
	public void viewReports() {
	    String[] atribut = {"Item Name", "Description", "Date", "Staff Name"};
	    DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
	    
	    try {
	        reportDAO repDao = new reportDAO();
	        List<report> reportList = repDao.findAll();
	        
	        if (reportList.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No report data found.");
	            table.setModel(tablemodel);
	            return;
	        }
	        
	        for(report rep : reportList) {
	            Object[] rowData = {
	                 
	                rep.getItemName(),      
	                rep.getDescription(),
	                rep.getReportDate(),
	                rep.getEmployeesName()
	            };
	            tablemodel.addRow(rowData);
	        }
	        table.setModel(tablemodel);
	    } catch (Exception e){
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to load report data, Please try again", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void viewDataByName(String name) {
	    String[] atribut = {"Item Name", "Description", "Date", "Staff Name"};
	    DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
	    
	    try {
	        reportDAO dao = new reportDAO();
	        List<report> reportlist = dao.findbyName(name);
	        
	        if (reportlist.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No reports found for: \"" + name + "\"");
	            table.setModel(tablemodel);
	            viewReports();
	            return;
	        }
	        
	        for (report rep : reportlist) {
	            Object[] rowData = {
	                rep.getItemName(),
	                rep.getDescription(),
	                rep.getReportDate(),
	                rep.getEmployeesName()
	            };
	            tablemodel.addRow(rowData);
	        }

	        table.setModel(tablemodel); 

	    } catch (Exception e) {
	    	e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to load report data, Please try again", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
