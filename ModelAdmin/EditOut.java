package ModelAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.items;
import Dao.itemsDAO;
import Dao.items_out;
import Dao.items_outDAO;
import Logic.StockOutService;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditOut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDate;
	private JTextField textQuantity;
	private JComboBox combobox;
	private Stock_out parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_out out = new Stock_out();
					EditOut frame = new EditOut(out);
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
	public EditOut(Stock_out parent) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 25));
		
		JTextPane txtpnItemName = new JTextPane();
		txtpnItemName.setEditable(false);
		txtpnItemName.setText("Item Name:");
		contentPane.add(txtpnItemName);
		
		combobox = new JComboBox();
		contentPane.add(combobox);
		loadItemToCombo();
		
		JTextPane txtpnDateOut = new JTextPane();
		txtpnDateOut.setEditable(false);
		txtpnDateOut.setText("Date Out:");
		contentPane.add(txtpnDateOut);
		
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
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					items_out selected = (items_out) combobox.getSelectedItem();
					String dateOut = textDate.getText();
					String quantityValue = textQuantity.getText();
					int idItemOut = selected.getItemOut();
					if(dateOut.isEmpty() || quantityValue.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					int quantity = Integer.parseInt(quantityValue);
					
					StockOutService service = new StockOutService();
					service.editData(idItemOut, dateOut, quantity);
					JOptionPane.showMessageDialog(null, "Stock Out data has been successfully updated.", null, JOptionPane.INFORMATION_MESSAGE);
					dispose();
					if(parent != null) {
						parent.refreshTable();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Quantity must be a numeric value.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null,  "An error occurred while updating Stock Out data.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnNewButton);
	}
	
	private void loadItemToCombo() {
		try {
			items_outDAO outDao = new items_outDAO();
			List<items_out> list = outDao.findAllOut();
			
			combobox.removeAllItems();
			for (items_out itm : list) {
				combobox.addItem(itm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
