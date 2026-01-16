package ModelAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.items;
import Dao.itemsDAO;
import Logic.ROP;
import Logic.StockService;
import ModelAdmin.Stock_items;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDate;
	private JTextField textQuantity;
	private JTextField textSafety;
	private JComboBox<items> comboitem;
	private Stock_items view;
	private Stock_items parent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_items itm = new Stock_items();
					AddUpdate frame = new AddUpdate(itm);
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
	public AddUpdate(Stock_items parent) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 15));
		
		JTextPane txtpnItem = new JTextPane();
		txtpnItem.setEditable(false);
		txtpnItem.setText("Item Name:");
		contentPane.add(txtpnItem);
		
		comboitem = new JComboBox();
		contentPane.add(comboitem);
		loadItemToCombo();
		
		JTextPane txtpnDate = new JTextPane();
		txtpnDate.setEditable(false);
		txtpnDate.setText("Date:");
		contentPane.add(txtpnDate);
		
		textDate = new JTextField();
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		JTextPane txtpnQuantity = new JTextPane();
		txtpnQuantity.setEditable(false);
		txtpnQuantity.setText("Quantity:");
		contentPane.add(txtpnQuantity);
		
		textQuantity = new JTextField();
		contentPane.add(textQuantity);
		textQuantity.setColumns(10);
		
		JTextPane txtpnSafetyStock = new JTextPane();
		txtpnSafetyStock.setEditable(false);
		txtpnSafetyStock.setText("Safety Stock:");
		contentPane.add(txtpnSafetyStock);
		
		textSafety = new JTextField();
		contentPane.add(textSafety);
		textSafety.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					items selected = (items) comboitem.getSelectedItem();
		            int idItem = selected.getItem_id();
		            int demand = selected.getDemand(); 
		            int leadtime = selected.getLeadTime();
		            int qtybefore = selected.getQuantity();
		            int ssbefore = selected.getSafety();
		            
		            String date = textDate.getText();
		            String qty = textQuantity.getText();
		            String ss = textSafety.getText();
		            if(date.isEmpty() || qty.isEmpty() || ss.isEmpty()) {
		            	JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
		            }
		            int quantity = Integer.parseInt(qty);
		            int safety = Integer.parseInt(ss);
		            
		            StockService service = new StockService();
		            service.AddStock(idItem, date, quantity, safety, demand, leadtime, qtybefore, ssbefore);
		            
		            JOptionPane.showMessageDialog(null, "Item data has been successfully updated.", null, JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		            if(parent != null) {
						parent.refreshTable();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while updating the item data.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnNewButton);

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

}
