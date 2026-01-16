package ModelAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.items;
import Dao.itemsDAO;
import Logic.StockService;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteItems extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private Stock_items parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_items itm = new Stock_items();
					DeleteItems frame = new DeleteItems(itm);
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
	public DeleteItems(Stock_items parent) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 20));
		
		JTextPane txtpnItemId = new JTextPane();
		txtpnItemId.setEditable(false);
		txtpnItemId.setText("Item Name:");
		contentPane.add(txtpnItemId);
		
		textName = new JTextField();
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textName.getText();
					if(name.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					StockService service = new StockService();
					service.deleteStock(name);
					JOptionPane.showMessageDialog(null, "Items has been successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					if(parent != null) {
						parent.refreshTable();
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Item ID must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		contentPane.add(btnNewButton);
	}
}
