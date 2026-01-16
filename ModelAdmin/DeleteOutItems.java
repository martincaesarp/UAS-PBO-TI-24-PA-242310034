package ModelAdmin;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.items_out;
import Dao.items_outDAO;
import Logic.StockOutService;

import java.awt.GridLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteOutItems extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private Stock_out parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_out out = new Stock_out();
					DeleteOutItems frame = new DeleteOutItems(out);
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
	public DeleteOutItems(Stock_out parent) {
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
		txtpnItemId.setText("Item ID:");
		contentPane.add(txtpnItemId);
		
		textID = new JTextField();
		contentPane.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String itemoutID = textID.getText();
					if(itemoutID.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields before submitting.", null, JOptionPane.WARNING_MESSAGE);
						return;
					}
					int idItemOut = Integer.parseInt(itemoutID);
					
					StockOutService service = new StockOutService();
					service.deleteData(idItemOut);
					JOptionPane.showMessageDialog(null, "Item successfully deleted!", null, JOptionPane.INFORMATION_MESSAGE);
					dispose();
					if(parent != null) {
						parent.refreshTable();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Item ID must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		contentPane.add(btnNewButton);
	}
}
