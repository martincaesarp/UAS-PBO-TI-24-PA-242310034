package ModelAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.items;
import Dao.itemsDAO;
import Logic.ROP;
import Logic.StockService;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditROP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDemand;
	private JTextField textLead;
	private JComboBox<items> comboitem;
	private Stock_items parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_items itm = new Stock_items();
					EditROP frame = new EditROP(itm);
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
	public EditROP(Stock_items parent) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 30));
		
		JTextPane txtpnItemName = new JTextPane();
		txtpnItemName.setEditable(false);
		txtpnItemName.setText("Item Name:");
		contentPane.add(txtpnItemName);
		
		comboitem = new JComboBox<>();
		contentPane.add(comboitem);
		loadItemToCombo();
		
		JTextPane txtpnDemand = new JTextPane();
		txtpnDemand.setEditable(false);
		txtpnDemand.setText("Demand:");
		contentPane.add(txtpnDemand);
		
		textDemand = new JTextField();
		contentPane.add(textDemand);
		textDemand.setColumns(10);
		
		JTextPane txtpnLeadTime = new JTextPane();
		txtpnLeadTime.setEditable(false);
		txtpnLeadTime.setText("Lead Time:");
		contentPane.add(txtpnLeadTime);
		
		textLead = new JTextField();
		contentPane.add(textLead);
		textLead.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					items selected = (items) comboitem.getSelectedItem();
					String dmnd = textDemand.getText();
					String ldtm = textLead.getText();
					int idItem = selected.getItem_id();
					int safetyStock = selected.getSafety();
					if(dmnd.isEmpty() || ldtm.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					int demand = Integer.parseInt(dmnd);
					int leadTime = Integer.parseInt(ldtm);
					
					
					StockService service = new StockService();
					service.editRop(idItem, demand, leadTime, safetyStock);
					JOptionPane.showMessageDialog(null, "Reorder Point (ROP) data has been successfully updated.", null, JOptionPane.INFORMATION_MESSAGE);
					dispose();
					if(parent != null) {
						parent.refreshTable();
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,  "Demand and Lead Time must be numeric values.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null,  "An error occurred while updating ROP data.", "Error", JOptionPane.ERROR_MESSAGE);
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
