package Uygulama;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Baglanti.Helper;
import Model.Hasta;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private JButton btn_return;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Kayýt Ekraný");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad");
		lblAdSoyad.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAdSoyad.setBounds(10, 22, 104, 19);
		contentPane.add(lblAdSoyad);
		
		fld_name = new JTextField();
		fld_name.setBounds(10, 44, 250, 20);
		contentPane.add(fld_name);
		fld_name.setColumns(10);
		
		JLabel lblTcNo = new JLabel("TC No");
		lblTcNo.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblTcNo.setBounds(10, 75, 104, 19);
		contentPane.add(lblTcNo);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 97, 250, 20);
		contentPane.add(fld_tcno);
		
		JLabel lblifreOlutur = new JLabel("\u015Eifre Olu\u015Ftur");
		lblifreOlutur.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblifreOlutur.setBounds(10, 128, 104, 19);
		contentPane.add(lblifreOlutur);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 151, 250, 20);
		contentPane.add(fld_pass);
		
		JButton btn_register = new JButton("KAYIT OL");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0 || fld_name.getText().length()==0) {
					
					Helper.Goster("hata");
					
				}
				
				else
				{
	try {
		boolean control=hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
		if (control) {
			
			GirisArayuz login=new GirisArayuz();
			login.setVisible(true);
			dispose();
		}else {
			Helper.Goster("error");
		}
		
	} catch (SQLException e1) {
			e1.printStackTrace();
	}
					
					
				}
			}
		});
		btn_register.setBounds(10, 193, 250, 35);
		contentPane.add(btn_register);
		
		btn_return = new JButton("GER\u0130 D\u00D6N");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				GirisArayuz login=new GirisArayuz();
				login.setVisible(true);
				dispose();
			}
		});
		btn_return.setBounds(10, 237, 250, 35);
		contentPane.add(btn_return);
	}
}
