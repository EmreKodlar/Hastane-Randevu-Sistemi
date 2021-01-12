package Uygulama;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Doctor;
import Model.Hasta;
import Model.User;

import Baglanti.Database;
import Baglanti.Helper;
import Baglanti.SifreUyari;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SifreGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tcno;
	private JPasswordField eski_sifre;
	private JPasswordField yeni_sifre;
	
	Statement st=null;
	ResultSet rs=null;
	Database conn= new Database();
	Connection con=conn.connDb();
	PreparedStatement preparedStatement= null;
	 
	private static int id;
	 
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SifreGUI frame = new SifreGUI(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 


	 
	public SifreGUI(int id ) throws SQLException {
		
		 
		 
			
		setResizable(false);
		setTitle("\u015Eifre De\u011Fi\u015Ftirme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JLabel label = new JLabel("TC No");
		label.setFont(new Font("Arial Black", Font.BOLD, 12));
		label.setBounds(30, 22, 104, 19);
		contentPane.add(label);
		
		
		
		
		
		
		tcno = new JTextField();
		tcno.setColumns(10);
		tcno.setBounds(30, 44, 250, 20);
		contentPane.add(tcno);
		
		JLabel lblEskiifre = new JLabel("Eski \u015Eifre");
		lblEskiifre.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblEskiifre.setBounds(30, 75, 104, 19);
		contentPane.add(lblEskiifre);
		
		eski_sifre = new JPasswordField();
		eski_sifre.setBounds(30, 98, 250, 20);
		contentPane.add(eski_sifre);
		
		JButton btnifreDeitir = new JButton("\u015E\u0130FRE DE\u011E\u0130\u015ET\u0130R");
		btnifreDeitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tcno.getText().length()==0 || eski_sifre.getText().length()==0 || yeni_sifre.getText().length()==0) {
					
					Helper.Goster("hata");
					
					 
				}
				
				else if(yeni_sifre.getText().length()<6 ) {
					
					Helper.Goster("Þifre 6 karakterden az olamaz!\nTekrar Deneyiniz...");
				}
				
				else {
					
					
					
				try { 
					
					Statement st=null;
					ResultSet rs=null;
					st = con.createStatement();
					rs=st.executeQuery("SELECT*FROM user WHERE id=" + id);
					
					while(rs.next()) {
					 
					if(tcno.getText().equals(rs.getString("tcno")) && eski_sifre.getText().equals(rs.getString("password"))) {
						
						String query="UPDATE user SET password=? WHERE id=?" ;
						
					 
						
						preparedStatement=con.prepareStatement(query);
						
						preparedStatement.setString(1, yeni_sifre.getText());
						preparedStatement.setInt(2, id);
					
					 
						preparedStatement.executeUpdate();
				
						Helper.Goster("Þifreniz Baþarýyla Deðiþti\n Yeni þifreniz: " + yeni_sifre.getText() );
					 
						
						dispose();
										
					}
					
					
					else {
						
						SifreUyari uyari=new SifreUyari();
						
						
						uyari.Goster("Girdiðiniz Bilgiler Hatalý!");
						
						
						
					}
					
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				
				
				
				}
				
			}
		});
		btnifreDeitir.setBounds(30, 200, 250, 35);
		contentPane.add(btnifreDeitir);
		
		JLabel lblYeniifreen = new JLabel("Yeni \u015Eifre (En az 6 Haneli)");
		lblYeniifreen.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblYeniifreen.setBounds(30, 135, 216, 19);
		contentPane.add(lblYeniifreen);
		
		yeni_sifre = new JPasswordField();
		yeni_sifre.setBounds(30, 158, 250, 20);
		contentPane.add(yeni_sifre);
	}


	 
}
