package Uygulama;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Baglanti.Helper;
import Baglanti.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Doctor;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame implements InterFace{

	private JPanel w_pane;
	
	private static Hasta hasta=new Hasta();
	
	private Clinic clinic=new Clinic();
	
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData= null;
	
	
	private JTable table_whours;
	
	private Whour whour=new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData= null;

	private int selectDoctorID=0;
	private String selectDoctorName=null;
	
	 
	
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData= null;
	private Appointment appoint =new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) throws SQLException {
		
		
		
		doctorModel= new DefaultTableModel();
		Object[] colDoctor=new Object[2];
		colDoctor[0]="ID";
		colDoctor[1]="Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData=new Object[2];
	 
		
		
		
		whourModel= new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
	 
		
		
		appointModel= new DefaultTableModel();
		Object[] colAppoint=new Object[3];
		colAppoint[0]="ID";
		colAppoint[1]="Doktor";
		colAppoint[2]="Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData=new Object[3];
		
		for(int i = 0; i < appoint.getHastaList(hasta.getId()).size(); i++) {
			
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
	
			appointModel.addRow(appointData);
		}
	 
		
		
		
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Hasta Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel(this.karsilamaMesaji + hasta.getName());
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
		button.setBounds(608, 26, 120, 23);
		w_pane.add(button);
		
		
		
		JButton button_sifre = new JButton("Þifre Deðiþtir");
		button_sifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				
				SifreGUI sGUI;
				try {
					sGUI = new SifreGUI(hasta.getId());
				    
					sGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
				
				 
				 
				
				
			}
		});
		button_sifre.setBounds(608, 66, 120, 23);
		w_pane.add(button_sifre);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 83, 724, 377);
		w_pane.add(tabbedPane);
		
		JPanel w_appointment = new JPanel();
		tabbedPane.addTab("Randevu Al", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		
		JScrollPane doctorlar = new JScrollPane();
		doctorlar.setBounds(10, 42, 235, 296);
		w_appointment.add(doctorlar);
		
		table_doctor = new JTable(doctorModel);
		doctorlar.setViewportView(table_doctor);
		
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(261, 42, 151, 20);
		
		select_clinic.addItem(" Klinik Seçiniz ");
		
		for(int i = 0; i < clinic.getList().size(); i++) {
			
			
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				if(select_clinic.getSelectedIndex()!=0) {
					
					JComboBox c= (JComboBox) e.getSource();
					
					Item item= (Item) c.getSelectedItem();
					
					DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
					
					clearModel.setRowCount(0);
					
					try {
						for(int i=0 ; i < clinic.getClinicDoctorList(item.getKey()).size() ; i ++) {
							
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							
							doctorModel.addRow(doctorData);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
					
					clearModel.setRowCount(0);
					
				}
				
			}
		});
		
		
		w_appointment.add(select_clinic);
		
		JLabel label_1 = new JLabel("Doktor Listesi");
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label_1.setBounds(10, 13, 136, 18);
		w_appointment.add(label_1);
		
		JLabel label_2 = new JLabel("Poliklinikler");
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		
		
		
		JLabel lblDoktorSe = new JLabel("Doktor Se\u00E7");
		lblDoktorSe.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDoktorSe.setBounds(261, 104, 133, 18);
		w_appointment.add(lblDoktorSe);
		
		JButton btn_selDoctor = new JButton("Seç");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row=table_doctor.getSelectedRow();
				
				if(row>=0) {
					
					String value=table_doctor.getModel().getValueAt(row,0).toString();
					
					int id=Integer.parseInt(value);
					
DefaultTableModel clearModel= (DefaultTableModel) table_whours.getModel();
					
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i< whour.getWhourList(id).size() ; i++) {
							
							whourData[0]=whour.getWhourList(id).get(i).getId();
							whourData[1]=whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
							
							
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 table_whours.setModel(whourModel);
					 
					 selectDoctorID=id;
					 
					 selectDoctorName=table_doctor.getModel().getValueAt(row, 1).toString();
					
					
				}else {
					
					Helper.Goster("Lütfen bir doktor seçiniz!");
				}
				
				
			}
		});
		
		btn_selDoctor.setBounds(261, 133, 151, 23);
		w_appointment.add(btn_selDoctor);
		
		JLabel lblRandevuSaatleri = new JLabel("Randevu Saatleri");
		lblRandevuSaatleri.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblRandevuSaatleri.setBounds(446, 11, 263, 18);
		w_appointment.add(lblRandevuSaatleri);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 40, 263, 296);
		w_appointment.add(scrollPane);
		
		table_whours = new JTable(whourModel);
		scrollPane.setViewportView(table_whours);
		
		table_whours.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		
		
		
		JLabel lblDoktorSerr = new JLabel("Randevu Oluþtur");
		lblDoktorSerr.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDoktorSerr.setBounds(261, 181, 133, 18);
		w_appointment.add(lblDoktorSerr);
		
		
		
		JButton btn_addApp = new JButton("Tarih ve Saat Seç");
		btn_addApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=table_whours.getSelectedRow();
				if(selRow>=0) {
					
					String date=table_whours.getModel().getValueAt(selRow,1).toString();
				
				try {
					boolean control=hasta.addAppoinment(selectDoctorID, hasta.getId(), selectDoctorName, hasta.getName(), date);
				
					if(control) {
						Helper.Goster("basari");
						hasta.updateWhourStatus(selectDoctorID, date);
						updateWhourModel(selectDoctorID);
						updateAppointModel(hasta.getId());
						
					}else {
						Helper.Goster("error");
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else {
					Helper.Goster("Lütfen Geçerli bir tarih seçiniz!");
				}
			}
		});
		btn_addApp.setBounds(261, 210, 151, 23);
		w_appointment.add(btn_addApp);
		
		JPanel w_appoint = new JPanel();
		tabbedPane.addTab("Randevularým", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 561, 350);
		w_appoint.add(scrollPane_1);
		
		table_appoint = new JTable(appointModel);
		scrollPane_1.setViewportView(table_appoint);
		
		
		

		JButton btnNewButton = new JButton("Randevu Sil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int selRow=table_appoint.getSelectedRow();
				if(selRow>=0) {
					
					if(Helper.confim("sure")) {
					
					String selectRow=table_appoint.getModel().getValueAt(selRow,0).toString();
					
					int selID=Integer.parseInt(selectRow);
					
					String tarih=table_appoint.getModel().getValueAt(selRow,2).toString();
					
					 
					
					try {
						boolean control=hasta.deleteRandevu(selID);
						if(control) {
							Helper.Goster("basari");
							updateAppointModel(hasta.getId());
							hasta.updateWhourStatusA(selectDoctorID, tarih);
						}
						else {
							Helper.Goster("error");
						}
					} catch (SQLException e11) {
						 
						e11.printStackTrace();
					}
				}
					
				}
					else {
					Helper.Goster("Lütfen Bir Tarih Seçiniz!");
				}
				
				
			}
			
		});
		
		btnNewButton.setBounds(571, 11, 138, 23);
		w_appoint.add(btnNewButton);
		
		 
 
		
	}
	
	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_whours.getModel();
	clearModel.setRowCount(0);
	for(int i=0 ; i < whour.getWhourList(doctor_id).size() ; i ++) {
		
		whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
		whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
		
		whourModel.addRow(whourData);
		
	}
	}
	
	
	
	
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_appoint.getModel();
	clearModel.setRowCount(0);
	for(int i=0 ; i < appoint.getHastaList(hasta_id).size() ; i ++) {
		
		appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
		appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
		appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
		 
		
		appointModel.addRow(appointData);
		
	}
}
	
	
 
	
}
