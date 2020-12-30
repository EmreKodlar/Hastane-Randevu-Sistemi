package Uygulama;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import Model.*;
import Baglanti.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField c_adi;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		c_adi = new JTextField();
		c_adi.setColumns(10);
		c_adi.setBounds(10, 37, 178, 20);
		c_adi.setText(clinic.getName());
		contentPane.add(c_adi);
		
		JLabel label = new JLabel("Poliklinlik Ad\u0131");
		label.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label.setBounds(10, 11, 133, 18);
		contentPane.add(label);
		
		JButton btnDzenle = new JButton("Düzenle");
		btnDzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Helper.confim("sure")) {
					try {
						clinic.updateClinic(clinic.getId(),c_adi.getText());
						Helper.Goster("basari");
						dispose();
					} catch (SQLException err) {
						 
						err.printStackTrace();
					}
				}
			}
		});
		btnDzenle.setBounds(10, 59, 178, 23);
		contentPane.add(btnDzenle);
	}

}
