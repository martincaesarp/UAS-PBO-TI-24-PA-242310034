package ModelAdmin;
import Dao.itemsDAO;
import Dao.items_out;
import Dao.items_outDAO;
import Logic.AutoRestock;
import Logic.StockOutService;
import Dao.items;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Stock_out extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDate;
	private JComboBox<items> comboitem;
	private JTextField textquantity;
	private JTable table;
	private JTextField textFind;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_out frame = new Stock_out();
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
	public Stock_out() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 505);
		JPanel thirdPane = new JPanel();
		thirdPane.setForeground(new Color(255, 255, 255));
		thirdPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(thirdPane);
		thirdPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		thirdPane.add(panel, BorderLayout.NORTH);
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
		thirdPane.add(panel_1, BorderLayout.WEST);
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
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Stock Out", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JTextPane txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText("Item Name:");
		panel_3.add(txtName);
		
		comboitem = new JComboBox<>();
		panel_3.add(comboitem);
		loadItemToCombo();
		
		JLabel label = new JLabel("");
		panel_3.add(label);
		
		JLabel label_3 = new JLabel("");
		panel_3.add(label_3);
		
		JLabel label_1 = new JLabel("");
		panel_3.add(label_1);
		
		JTextPane txtQuantity = new JTextPane();
		txtQuantity.setEditable(false);
		txtQuantity.setText("Quantity:");
		panel_3.add(txtQuantity);
		
		textquantity = new JTextField();
		textquantity.setColumns(10);
		panel_3.add(textquantity);
		
		JLabel label_2 = new JLabel("");
		panel_3.add(label_2);
		
		JLabel label_2_1 = new JLabel("");
		panel_3.add(label_2_1);
		
		JLabel label_2_1_1 = new JLabel("");
		panel_3.add(label_2_1_1);
		
		JTextPane txtDate = new JTextPane();
		txtDate.setEditable(false);
		txtDate.setText("Date:");
		panel_3.add(txtDate);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		panel_3.add(textDate);
		
		JLabel label_2_1_1_1 = new JLabel("");
		panel_3.add(label_2_1_1_1);
		
		JLabel label_2_1_1_1_1 = new JLabel("");
		panel_3.add(label_2_1_1_1_1);
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					items selected = (items) comboitem.getSelectedItem(); 
					String date = textDate.getText();
					String qty = textquantity.getText();
					if(date.isEmpty() || qty.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.");
						return;
					}
					int idbarang = selected.getItem_id();
					int quantityvalue = Integer.parseInt(qty);
					
					StockOutService service = new StockOutService();
					service.insertData(idbarang, date, quantityvalue);
					JOptionPane.showMessageDialog(null, "Stock Out Data has been successfully Added.", null, JOptionPane.INFORMATION_MESSAGE);
					viewDataOut();
		        } catch (NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(null,  "Quantity must be a valid number", "Invalid Input", JOptionPane.WARNING_MESSAGE);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Failed to process data, Please try again", null, JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		panel_3.add(btnNewButton_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(0, 7, 0, 0));
		
		JTextPane txtpnSearch = new JTextPane();
		txtpnSearch.setText("Search:");
		txtpnSearch.setEditable(false);
		panel_4.add(txtpnSearch);
		
		textFind = new JTextField();
		panel_4.add(textFind);
		textFind.setColumns(10);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String find = textFind.getText();
				viewDatabyNameDate(find);
			}
		});
		panel_4.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_4.add(lblNewLabel_1);
		
		JTextPane txtpnFilterDataBy = new JTextPane();
		txtpnFilterDataBy.setText("Filter data by:");
		panel_4.add(txtpnFilterDataBy);
		
		String filter[] = {"All", "A", "B", "C"};
		JComboBox comboBox = new JComboBox<>(filter);
		panel_4.add(comboBox);
		
		JButton btnFilter = new JButton("Submit");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = comboBox.getSelectedItem().toString();
				viewDatabyClass(select);
			}
		});
		panel_4.add(btnFilter);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		thirdPane.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Edit Stock Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditOut editout = new EditOut(Stock_out.this);
				editout.setVisible(true);
			}
		});
		panel_6.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteOutItems delOutitems = new DeleteOutItems(Stock_out.this);
				delOutitems.setVisible(true);
			}
		});
		panel_6.add(btnNewButton_4);
		viewDataOut();
	}
	
	private void viewDataOut() {
		String[] atributOut = {"ID", "Item Name", "Date", "Quantity", "Classification"};
		DefaultTableModel tableModel = new DefaultTableModel(atributOut, 0);
		 try {
			 items_outDAO outdao = new items_outDAO();
			 List<items_out> outList = outdao.findAllOut();
			 
			 for(items_out itmout : outList) {
				 Object[] rowData = {
					itmout.getItemOut(),        
					itmout.getItemName(),       
					itmout.getDateOut(),        
					itmout.getQuantityOut(),    
					itmout.getClassification()  
				 };
				 tableModel.addRow(rowData);
			 }
			 table.setModel(tableModel);
		 } catch (Exception e) {
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "failed to load data", null, JOptionPane.ERROR_MESSAGE);
		 }
		
	}
	
	private void loadItemToCombo() {
		try {
			itemsDAO itemdao = new itemsDAO();
			List<items> list = itemdao.findAll();
			
			comboitem.removeAllItems();
			for (items itm : list) {
				comboitem.addItem(itm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewDatabyClass(String classification) {
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Classification"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		tablemodel.setRowCount(0);
		
		try {
			items_outDAO outdao = new items_outDAO();
			List<items_out> itemlist;
			
			if(classification.equals("All")) {
				itemlist = outdao.findAllOut();
			} else {
				itemlist = outdao.findbyCLassification(classification);
			}
			
			for(items_out itmout : itemlist) {
				Object[] rowData = {
						itmout.getItemOut(),
						itmout.getItemName(),
						itmout.getDateOut(),
						itmout.getQuantityOut(),
						itmout.getClassification()
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
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Classification"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		tablemodel.setRowCount(0);
		
		try {
			items_outDAO dao = new items_outDAO();
			List<items_out> itemlist = dao.findbyNameDate(keyword);
			
			if (itemlist.isEmpty()) { 
	            JOptionPane.showMessageDialog(null, "No records found matching: \"" + keyword + "\". Please try another keyword.");
	            table.setModel(tablemodel); 
	            viewDataOut();
	            return;
	        }
			
			for(items_out itmout : itemlist) {
				Object[] rowData = {
						itmout.getItemOut(),        
						itmout.getItemName(),       
						itmout.getDateOut(),        
						itmout.getQuantityOut(),    
						itmout.getClassification() 
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
	
	public void refreshTable() {
		viewDataOut();
	}

}
