package Uygulama;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Baglanti.Database;
import Baglanti.Helper;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

import com.mysql.jdbc.Driver;
import java.awt.Color;
 

 


public class GirisArayuz extends JFrame {

	private JPanel contentPane;
	private JTextField hasta_tcno;
	private JTextField doktor_tcno;
	private JPasswordField doktor_sifre;
	
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	static final String BASLIK="Hastane Randevu Sistemi";
	

private Database conn= new Database();
private JPasswordField hasta_pass;

 
	
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisArayuz frame = new GirisArayuz();
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
	public GirisArayuz() {
		setTitle(BASLIK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HASTANE RANDEVU SÝSTEMÝNE HOÞGELDÝNÝZ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel.setBounds(49, 56, 373, 34);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(37, 151, 408, 199);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Hasta Giriþi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTcKimlikNo = new JLabel("TC Kimlik No : ");
		lblTcKimlikNo.setBounds(22, 37, 127, 19);
		panel.add(lblTcKimlikNo);
		lblTcKimlikNo.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		hasta_tcno = new JTextField();
		hasta_tcno.setBounds(159, 38, 192, 20);
		panel.add(hasta_tcno);
		hasta_tcno.setColumns(10);
		
		JLabel lblifre = new JLabel("\u015Eifre : ");
		lblifre.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblifre.setBounds(22, 67, 104, 19);
		panel.add(lblifre);
		
		JButton giris_hasta = new JButton("Giriþ Yap");
		giris_hasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(hasta_tcno.getText().length()==0 || hasta_pass.getText().length()==0) {
					
					Helper.Goster("hata");
				}else {
					
					
					boolean key=true;
					
					   try{
	
							 
						   java.sql.Connection con = conn.connDb(); //connDb database.java classýnýn içinde
							
						     
							
							  java.sql.Statement st = con.createStatement();
							 
							 
							String sorgu="SELECT*FROM user";

							 
							
							ResultSet rs= ((java.sql.Statement) st).executeQuery(sorgu);
							
							
							  
							while(rs.next()) {
								
								if(hasta_tcno.getText().equals(rs.getString("tcno")) && hasta_pass.getText().equals(rs.getString("password")))
										
										{
									
									if(rs.getString("type").equals("hasta")) {
										
										Hasta hasta= new Hasta();
										
										hasta.setId(rs.getInt("id"));
										hasta.setPassword("password");
										hasta.setTcno(rs.getString("tcno"));
										hasta.setName(rs.getString("name"));
										hasta.setType(rs.getString("type"));

										HastaGUI hGUI=new HastaGUI(hasta);
										hGUI.setVisible(true);
										dispose();
										
										key=false;
										
									}
								 
									 
								
								}
								
							}
							
						} catch (SQLException e2) {
							 
						}
					   
					   if(key) {
						   
						   Helper.Goster("Böyle Bir Hasta Bulunamadý!!! \n Lütfen Kayýt Olunuz...");
					   }
					
					
				}
				
				
			}
		});
		giris_hasta.setBounds(262, 105, 89, 23);
		panel.add(giris_hasta);
		
		JButton yenikayit_hasta = new JButton("Yeni Kay\u0131t");
		yenikayit_hasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				RegisterGUI rGUI=new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		yenikayit_hasta.setBounds(159, 105, 89, 23);
		panel.add(yenikayit_hasta);
		panel.setLayout(null);
		
		hasta_pass = new JPasswordField();
		hasta_pass.setBounds(159, 69, 192, 20);
		panel.add(hasta_pass);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Doktor Giriþi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("TC Kimlik No : ");
		label.setFont(new Font("Arial Black", Font.BOLD, 12));
		label.setBounds(32, 38, 127, 19);
		panel_1.add(label);
		
		doktor_tcno = new JTextField();
		doktor_tcno.setColumns(10);
		doktor_tcno.setBounds(169, 39, 192, 20);
		panel_1.add(doktor_tcno);
		
		JLabel label_1 = new JLabel("\u015Eifre : ");
		label_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		label_1.setBounds(32, 68, 104, 19);
		panel_1.add(label_1);
		
		JButton giris_doktor = new JButton("Giri\u015F Yap");
		giris_doktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
if(doktor_tcno.getText().length()==0 || doktor_sifre.getText().length()==0) {
	
	Helper.Goster("hata"); }

else
{
	
	
    
		 
	        try{
	        	
	        	
	        
	      
		 
		 
		java.sql.Connection con = conn.connDb(); //connDb database.java classýnýn içinde
		
		     
		
		  java.sql.Statement st = con.createStatement();
		 
		 
		String sorgu="SELECT*FROM user ";
		
		ResultSet rs= st.executeQuery(sorgu);
		
 
		  
		while(rs.next()) {
			
			 
			
			  if(doktor_tcno.getText().equals(rs.getString("tcno")) && doktor_sifre.getText().equals(rs.getString("password")))
					
					{
				
				if(rs.getString("type").equals("bashekim")) {
					
					Bashekim bhekim= new Bashekim();
					
					bhekim.setId(rs.getInt("id"));
					bhekim.setPassword("password");
					bhekim.setTcno(rs.getString("tcno"));
					bhekim.setName(rs.getString("name"));
					bhekim.setType(rs.getString("type"));

					BashekimArayuz bArayuz=new BashekimArayuz(bhekim);
					bArayuz.setVisible(true);
					dispose();
					
				}
				 
				
				if(rs.getString("type").equals("doktor")) {
					
					Doctor doctor=new Doctor();
					
					doctor.setId(rs.getInt("id"));
					doctor.setPassword("password");
					doctor.setTcno(rs.getString("tcno"));
					doctor.setName(rs.getString("name"));
					doctor.setType(rs.getString("type"));

					DoctorArayuz dArayuz=new DoctorArayuz(doctor);
					dArayuz.setVisible(true);
					dispose();
					
					
				}
				
			
				
			
			}
			  

		
			
			
		}
		 
		
		  
		 
		
		
	
		  
		
		
		
	} catch (SQLException e2) {
		 
	}
	        
	
		 
	  
	
}
				
			}
		});
		giris_doktor.setBounds(169, 106, 192, 23);
		panel_1.add(giris_doktor);
		
		doktor_sifre = new JPasswordField();
		doktor_sifre.setBounds(169, 68, 192, 20);
		panel_1.add(doktor_sifre);
		
	 
	}
}
