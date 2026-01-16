package Authentication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import Logic.LoginService;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 240);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JTextPane txtpnLoginPage = new JTextPane();
		txtpnLoginPage.setEditable(false);
		txtpnLoginPage.setForeground(new Color(0, 64, 128));
		txtpnLoginPage.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnLoginPage.setText("Login Page");
		panel.add(txtpnLoginPage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBounds(21, 21, 70, 22);
		txtpnUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnUsername.setText("Username:");
		panel_1.add(txtpnUsername);
		
		textName = new JTextField();
		textName.setBounds(101, 23, 148, 20);
		panel_1.add(textName);
		textName.setColumns(10);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBounds(21, 74, 67, 22);
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnPassword.setText("Password:");
		panel_1.add(txtpnPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(101, 76, 148, 20);
		panel_1.add(passwordField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textName.getText();
					String password = passwordField.getText();
					if(name.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Input data first!");
						return;
					} 
					LoginService service = new LoginService();
					service.validation(name, password);
					dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		panel_2.add(btnNewButton_1);

	}
}
