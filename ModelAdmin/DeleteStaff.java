package ModelAdmin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Logic.StaffService;
import Logic.StockService;

public class DeleteStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStaff frame = new DeleteStaff();
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
	public DeleteStaff() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 20));
		
		JTextPane txtpnItemId = new JTextPane();
		txtpnItemId.setEditable(false);
		txtpnItemId.setText("Staff Name:");
		contentPane.add(txtpnItemId);
		
		JTextField textName = new JTextField();
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
					StaffService service = new StaffService();
					service.deleteData(name);
					JOptionPane.showMessageDialog(null, "Staff has been successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch(Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while deleting the staff.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnNewButton);
	}

}


