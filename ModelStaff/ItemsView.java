package ModelStaff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Authentication.Login;
import Dao.User;
import Dao.items;
import Dao.itemsDAO;
import Logic.ReportService;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ItemsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private User user;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User usr = new User();
					ItemsView frame = new ItemsView(usr);
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
	public ItemsView(User user) {
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
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_1, BorderLayout.NORTH);
		
		JTextPane txtpnSearch = new JTextPane();
		txtpnSearch.setText("Search:");
		panel_1.add(txtpnSearch);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String find = textField.getText();
				if(find.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Input data first!");
					return;
				}
				viewDatabyNameDate(find);
			}
		});
		panel_1.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Stock Items");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewData();
			}
		});
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Report");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportForm form = new ReportForm(user);
				form.setVisible(true);
				dispose();
			}
		});
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
		viewData();
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
	
	public void viewDatabyNameDate(String keyword) {
		String[] atribut = {"ID", "Item Name", "Date", "Quantity", "Safety Stock", "Classification", "ROP"};
		DefaultTableModel tablemodel = new DefaultTableModel(atribut, 0); 
		tablemodel.setRowCount(0);
		
		try {
			itemsDAO dao = new itemsDAO();
			List<items> itemlist = dao.findbyNameDate(keyword);
			
			if (itemlist.isEmpty()) { 
	            JOptionPane.showMessageDialog(null, "No data found for: " + keyword);
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
			JOptionPane.showMessageDialog(null, "failed to load data", null, JOptionPane.ERROR_MESSAGE);
		}
		setVisible(true);
	}

}
