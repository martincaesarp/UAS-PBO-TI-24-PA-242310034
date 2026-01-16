package ModelAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import Authentication.Login;
import Dao.itemsDAO;
import Dao.items_outDAO;
import Dao.reportDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 505);
		mainPane = new JPanel();
		mainPane.setForeground(new Color(255, 255, 255));
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPane.add(panel, BorderLayout.NORTH);
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
		mainPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton Dashboard = new JButton("Dashboard");
		Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(Dashboard);
		
		JButton stock = new JButton("Stock Items");
		stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock_items item = new Stock_items();
				item.setVisible(true);
				item.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
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
		panel_2.setBorder(new TitledBorder(null, "Dashboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, -250));
		
		JTextPane txtpnItems = new JTextPane();
		txtpnItems.setEditable(false);
		txtpnItems.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnItems.setText("Items:");
		panel_3.add(txtpnItems, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(0, 64, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			itemsDAO dao = new itemsDAO();
			int totalItems = dao.countItems();
			lblNewLabel_1.setText(String.valueOf(totalItems));
		} catch (Exception e) {
			e.printStackTrace();
		    lblNewLabel_1.setText("0");
		}
		panel_3.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, -250));
		
		JTextPane txtpnItemsOut = new JTextPane();
		txtpnItemsOut.setEditable(false);
		txtpnItemsOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnItemsOut.setText("Items Out:");
		panel_4.add(txtpnItemsOut, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(0, 64, 128));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		try {
			items_outDAO dao = new items_outDAO();
			int totalItems = dao.countItems();
			lblNewLabel_2.setText(String.valueOf(totalItems));
		} catch (Exception e) {
			e.printStackTrace();
		    lblNewLabel_2.setText("0");
		}
		panel_4.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, -250));
		
		JTextPane txtpnReports = new JTextPane();
		txtpnReports.setEditable(false);
		txtpnReports.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnReports.setText("Reports:");
		panel_5.add(txtpnReports, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(new Color(0, 64, 128));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		try {
			reportDAO dao = new reportDAO();
			int totalItems = dao.countReport();
			lblNewLabel_3.setText(String.valueOf(totalItems));
		} catch (Exception e) {
			e.printStackTrace();
		    lblNewLabel_2.setText("0");
		}
		panel_5.add(lblNewLabel_3, BorderLayout.CENTER);

	}
}
