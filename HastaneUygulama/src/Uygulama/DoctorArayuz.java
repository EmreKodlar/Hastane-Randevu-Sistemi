package Uygulama;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Baglanti.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorArayuz extends JFrame implements InterFace {

	private JPanel w_pane;
	
	private static Doctor doctor=new Doctor();
	
	
	
	private JTable table_Whour;
	
	private DefaultTableModel whourModel;
	private Object[] whourData=null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorArayuz frame = new DoctorArayuz(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorArayuz(Doctor doctor) throws SQLException {
		
		
	 
		
		whourModel= new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		for(int i=0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		
		setResizable(false);
		setTitle("Doktor Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
	
		
		//Polymorphism
		
		User kullanici=new Doctor();

		// için türetildi
		
		
		JLabel label = new JLabel(this.karsilamaMesaji + kullanici.isimYaz(doctor.getName()));
		
		
		
		
		
		label.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label.setBounds(10, 27, 302, 18);
		w_pane.add(label);
		
		JButton button = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GirisArayuz login=new GirisArayuz();
				login.setVisible(true);
				dispose();
			}
		});
		button.setBounds(602, 26, 120, 23);
		w_pane.add(button);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 119, 712, 341);
		w_pane.add(w_tab);
		
		JPanel w_hour = new JPanel();
		w_hour.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, w_hour, null);
		w_hour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 130, 32);
		w_hour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"}));
		select_time.setBounds(161, 11, 60, 32);
		w_hour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				
				String date="";
				
				try {
					
					 date=sdf.format(select_date.getDate());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
					if(date.length()==0) {
					
					Helper.Goster("Lütfen Geçerli bir tarih giriniz!");
					
						}
					
					else {
						
						
						String time=" "+ select_time.getSelectedItem().toString() + ":00";
						
						String selectDate=date+time;
						
						try {
							boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
							
							if(control) {
								Helper.Goster("basari");
								updateWhourModel(doctor);
								
							}else {
								Helper.Goster("Error!");
							}
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
			
				
			
				
				
			}
		});
		btn_addWhour.setBounds(238, 11, 89, 32);
		w_hour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 65, 687, 237);
		w_hour.add(w_scrollWhour);
		
		table_Whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_Whour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selRow=table_Whour.getSelectedRow();
				if(selRow>=0) {
					
					String selectRow=table_Whour.getModel().getValueAt(selRow,0).toString();
					int selID=Integer.parseInt(selectRow);
					try {
						boolean control=doctor.deleteWhour(selID);
						if(control) {
							Helper.Goster("basari");
							updateWhourModel(doctor);
						}
						else {
							Helper.Goster("error");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					Helper.Goster("Lütfen Bir Tarih Seçiniz!");
				}
				
				
			}
		});
		btn_deleteWhour.setBounds(608, 15, 89, 32);
		w_hour.add(btn_deleteWhour);
		
		
		JButton button_sifre = new JButton("Þifre Deðiþtir");
		button_sifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				
				SifreGUI sGUI;
				
				
				
				try {
					sGUI = new SifreGUI(doctor.getId());
									    
					sGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
				
				 
				 
				
				
			}
		});
		button_sifre.setBounds(602, 67, 120, 23);
		w_pane.add(button_sifre);
		
		 
		
	}
	
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_Whour.getModel();
	
		clearModel.setRowCount(0);
		
for(int i=0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
}
