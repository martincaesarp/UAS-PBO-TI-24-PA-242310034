package ModelAdmin;
import Dao.items;
import Dao.itemsDAO;
import Logic.ROP;
import Logic.StockService;

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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Authentication.Login;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Stock_items extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textDate;
	private JTextField textQuantity;
	private JTextField textSafety;
	private JComboBox<String> comboBox;
	private JTextField textDemand;
	private JTextField textLeadTime;
	private JTable table;
	private JTextField textFind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_items frame = new Stock_items();
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
	public Stock_items() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 505);
		JPanel secPane = new JPanel();
		secPane.setForeground(new Color(255, 255, 255));
		secPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(secPane);
		secPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		secPane.add(panel, BorderLayout.NORTH);
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
		secPane.add(panel_1, BorderLayout.WEST);
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
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff_data report = new Staff_data();
				report.setVisible(true);
				report.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
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
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Stock Items", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		secPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setEditable(false);
		txtpnName.setText("NAME:");
		panel_3.add(txtpnName);
		
		textName = new JTextField();
		panel_3.add(textName);
		textName.setColumns(10);
		
		JTextPane txtpnQuantity = new JTextPane();
		txtpnQuantity.setEditable(false);
		txtpnQuantity.setText("QUANTITY:");
		panel_3.add(txtpnQuantity);
		
		textQuantity = new JTextField();
		textQuantity.setToolTipText("");
		textQuantity.setColumns(10);
		panel_3.add(textQuantity);
		
		JTextPane txtpnClassification = new JTextPane();
		txtpnClassification.setEditable(false);
		txtpnClassification.setText("CLASSIFICATION:");
		panel_3.add(txtpnClassification);
		
		JTextPane txtpnDate = new JTextPane();
		txtpnDate.setEditable(false);
		txtpnDate.setText("DATE:");
		panel_3.add(txtpnDate);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		panel_3.add(textDate);
		
		JTextPane txtpnSafetyStock = new JTextPane();
		txtpnSafetyStock.setEditable(false);
		txtpnSafetyStock.setText("SAFETY STOCK:");
		panel_3.add(txtpnSafetyStock);
		
		textSafety = new JTextField();
		textSafety.setColumns(10);
		panel_3.add(textSafety);
		
		String classification[] = {"A", "B", "C"};
		comboBox = new JComboBox<>(classification);
		panel_3.add(comboBox);
		
		JTextPane txtpnDemand = new JTextPane();
		txtpnDemand.setEditable(false);
		txtpnDemand.setText("DEMAND:");
		panel_3.add(txtpnDemand);
		
		textDemand = new JTextField();
		textDemand.setColumns(10);
		panel_3.add(textDemand);
		
		JTextPane txtpnLeadTimeday = new JTextPane();
		txtpnLeadTimeday.setEditable(false);
		txtpnLeadTimeday.setText("LEAD TIME:");
		panel_3.add(txtpnLeadTimeday);
		
		textLeadTime = new JTextField();
		textLeadTime.setToolTipText("");
		textLeadTime.setColumns(10);
		panel_3.add(textLeadTime);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textName.getText();
					String date = textDate.getText();
					String qty = textQuantity.getText();
					String ss = textSafety.getText();
					String clss = comboBox.getSelectedItem().toString();
					String dmnd = textDemand.getText();
					String ld = textLeadTime.getText();
					if (name.isEmpty() || date.isEmpty() || qty.isEmpty() || ss.isEmpty() || clss.isEmpty() || dmnd.isEmpty() || ld.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					int quantity = Integer.parseInt(qty);
					int safetyStock = Integer.parseInt(ss);
					int demand = Integer.parseInt(dmnd);
					int leadTime = Integer.parseInt(ld);
					
					StockService service = new StockService();
					service.insertItems(name, date, quantity, safetyStock, clss, demand, leadTime);
					JOptionPane.showMessageDialog(null, "Item data has been successfully Added.", null, JOptionPane.INFORMATION_MESSAGE);
					viewData();
		        } catch (NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(null, "Please enter valid numbers for Quantity, Safety Stock, Demand, and Lead Time", "Invalid Input", JOptionPane.WARNING_MESSAGE);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Failed to add data, Please try again", null, JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		panel_3.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 8, 0, 0));
		
		JTextPane txtpnRestockItem = new JTextPane();
		txtpnRestockItem.setEditable(false);
		txtpnRestockItem.setText("Restock Item:");
		panel_5.add(txtpnRestockItem);
		
		int restockCount = 0;
		try {
		    itemsDAO dao = new itemsDAO();
		    restockCount = dao.countRestockItems();
		} catch (Exception ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		
		JButton btnNewButton = new JButton(String.valueOf(restockCount));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					itemsDAO dao = new itemsDAO();
				    int newCount = dao.countRestockItems();
				    btnNewButton.setText(String.valueOf(newCount));
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
				viewRestock();
			}
		});
		panel_5.add(btnNewButton);
		
		JTextPane txtpnFind = new JTextPane();
		txtpnFind.setText("Search:");
		txtpnFind.setEditable(false);
		panel_5.add(txtpnFind);
		
		textFind = new JTextField();
		panel_5.add(textFind);
		textFind.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Find");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String find = textFind.getText();
				if(find.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a keyword to search.");					
				} else {
					viewDatabyNameDate(find);
				}				
			}
		});
		panel_5.add(btnNewButton_6);
		
		JTextPane txtpnFilterDataBy = new JTextPane();
		txtpnFilterDataBy.setBackground(new Color(255, 255, 255));
		txtpnFilterDataBy.setText("Filter data by:");
		panel_5.add(txtpnFilterDataBy);
		
		String filter[] = {"All", "A", "B", "C", "NA"};
		JComboBox comboBox_filter = new JComboBox<>(filter);
		panel_5.add(comboBox_filter);
		
		JButton btnFilter = new JButton("Submit");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = comboBox_filter.getSelectedItem().toString();
				viewDatabyClass(select);
			}
		});
		panel_5.add(btnFilter);
		viewData();
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		secPane.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_2 = new JButton("Add Quantity");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdate update = new AddUpdate(Stock_items.this);
				update.setVisible(true);
			}
		});
		
		JButton btnNewButton_4 = new JButton("Edit Item");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUpdate edit = new EditUpdate(Stock_items.this);
				edit.setVisible(true);
			}
		});
		panel_4.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Edit ROP");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditROP rop = new EditROP(Stock_items.this);
				rop.setVisible(true);
			}
		});
		panel_4.add(btnNewButton_5);
		panel_4.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteItems delItem = new DeleteItems(Stock_items.this);
				delItem.setVisible(true);
			}
		});
		panel_4.add(btnNewButton_3);
	}
	
	public void viewData() {
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Safety Stock", "Classification", "ROP"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		
		try {
			itemsDAO dao = new itemsDAO();
			List<items> itemlist = dao.findAll();
			
			for(items itm : itemlist) {
				Object[] rowData = {
						itm.getItem_id(),
						itm.getItem_name(),
						itm.getDate_in(),
						itm.getQuantity(),
						itm.getSafety(),
						itm.getClassification(),
						itm.getRop()
				};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "failed to load data", null, JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}
	
	public void viewDatabyClass(String classification) {
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Safety Stock", "Classification", "ROP"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		tablemodel.setRowCount(0);
		
		try {
			itemsDAO dao = new itemsDAO();
			List<items> itemlist;
			
			if(classification.equals("All")) {
				itemlist = dao.findAll();
			} else {
				itemlist = dao.findbyCLassification(classification);
			}
			
			for(items itm : itemlist) {
				Object[] rowData = {
						itm.getItem_id(),
						itm.getItem_name(),
						itm.getDate_in(),
						itm.getQuantity(),
						itm.getSafety(),
						itm.getClassification(),
						itm.getRop()
				};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "failed to load data", null, JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}
	
	public void viewDatabyNameDate(String keyword) {
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Safety Stock", "Classification", "ROP"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		tablemodel.setRowCount(0);
		
		try {
			itemsDAO dao = new itemsDAO();
			List<items> itemlist = dao.findbyNameDate(keyword);
			
			if (itemlist.isEmpty()) { 
	            JOptionPane.showMessageDialog(null, "No records found for: \"" + keyword + "\". Please try another keyword.");
	            table.setModel(tablemodel); 
	            viewData();
	            return;
	        }
			
			for(items itm : itemlist) {
				Object[] rowData = {
						itm.getItem_id(),
						itm.getItem_name(),
						itm.getDate_in(),
						itm.getQuantity(),
						itm.getSafety(),
						itm.getClassification(),
						itm.getRop()
				};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to load data, Please try again", null, JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}
	
	public void viewRestock() {
	    String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Safety Stock", "Classification", "ROP"};
	    DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0);

	    try {
	        itemsDAO dao = new itemsDAO();
	        List<items> itemlist = dao.findRestockItems();

	        for (items itm : itemlist) {
	            Object[] rowData = {
	                itm.getItem_id(),
	                itm.getItem_name(),
	                itm.getDate_in(),
	                itm.getQuantity(),
	                itm.getSafety(),
	                itm.getClassification(),
	                itm.getRop()
	            };
	            tablemodel.addRow(rowData);
	        }

	        table.setModel(tablemodel);
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to load restock data", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void refreshTable() {
		viewData();
	}
}
