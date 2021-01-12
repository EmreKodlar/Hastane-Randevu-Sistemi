package Uygulama;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;


 
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Baglanti.*;
import javax.swing.JComboBox;

public class BashekimArayuz extends JFrame implements InterFace {
	
	static Bashekim bashekim=new Bashekim();
	
	Clinic clinic=new Clinic();

	private JPanel contentPane;
	private JTextField textField;
	private JTextField doktor_name;
	private JTextField doktor_tcno;
	private JTextField doktor_password;
	private JTextField doktor_id;
	private JTable table_doctor;
	
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;
	
	private JTextField clinic_adi;
	private JTable table_clinic;
	private DefaultTableModel clinicModel=null;
	private Object[] clinicData=null;
	
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	
	private DefaultTableModel workerModel=null;
	private Object[] workerData=null;
	private JTextField textField_1;
	
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimArayuz frame = new BashekimArayuz(bashekim);
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
	
	
	
	
	public BashekimArayuz(Bashekim bashekim) throws SQLException {
		
		doctorModel= new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC No";
		colDoctorName[3]="Þifre";
		
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		for(int i=0; i<bashekim.getDoctorList().size(); i++) {
			
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
		
		
		
		
		clinicModel= new DefaultTableModel();
		Object[] colClinic=new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Poliklinik Adý";
		 
		
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData=new Object[2];
		for(int i=0; i<clinic.getList().size(); i++) {
			
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
		 
			clinicModel.addRow(clinicData);
			
		}
		
		
	 workerModel= new DefaultTableModel();
		Object[] colWorker=new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad Soyad";
		
		workerModel.setColumnIdentifiers(colWorker);
		workerData=new Object[2];
		
		
		setResizable(false);
		setTitle("Baþhekim Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(this.karsilamaMesaji + bashekim.getName());
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 302, 18);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GirisArayuz login=new GirisArayuz();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(608, 15, 120, 23);
		contentPane.add(btnNewButton);
		
		
		JButton button_sifre = new JButton("Þifre Deðiþtir");
		button_sifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				
				SifreGUI sGUI;
				try {
					sGUI = new SifreGUI(bashekim.getId());
				    
					sGUI.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
				
				 
				 
				
				
			}
		});
		button_sifre.setBounds(608, 45, 120, 23);
		contentPane.add(button_sifre);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 69, 712, 391);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad");
		lblAdSoyad.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblAdSoyad.setBounds(529, 29, 72, 18);
		panel.add(lblAdSoyad);
		
		doktor_name = new JTextField();
		doktor_name.setBounds(529, 55, 171, 20);
		panel.add(doktor_name);
		doktor_name.setColumns(10);
		
		JLabel lblTcNo = new JLabel("TC No");
		lblTcNo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTcNo.setBounds(529, 86, 72, 18);
		panel.add(lblTcNo);
		
		doktor_tcno = new JTextField();
		doktor_tcno.setColumns(10);
		doktor_tcno.setBounds(529, 112, 171, 20);
		panel.add(doktor_tcno);
		
		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblifre.setBounds(529, 143, 72, 18);
		panel.add(lblifre);
		
		doktor_password = new JTextField();
		doktor_password.setColumns(10);
		doktor_password.setBounds(529, 169, 171, 20);
		panel.add(doktor_password);
		
		JButton doktor_ekle = new JButton("Doktor Ekle");
		doktor_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(doktor_name.getText().length()==0 || doktor_tcno.getText().length()==0 || doktor_password.getText().length()==0 ) {
					
					Helper.Goster("hata");
					
				}
				
				else
				{
					try {
						boolean control = bashekim.addDoctor(doktor_tcno.getText(), doktor_password.getText(), doktor_name.getText());
					
					if(control==true)
					{
						Helper.Goster("basari");
						doktor_tcno.setText(null);
						doktor_password.setText(null);
						doktor_name.setText(null);
						updateDoctorModel();
					}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		doktor_ekle.setBounds(529, 209, 171, 23);
		panel.add(doktor_ekle);
		
		JLabel lblKullancId = new JLabel("Kullan\u0131c\u0131 ID");
		lblKullancId.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblKullancId.setBounds(529, 260, 118, 18);
		panel.add(lblKullancId);
		
		doktor_id = new JTextField();
		doktor_id.setColumns(10);
		doktor_id.setBounds(529, 286, 171, 20);
		panel.add(doktor_id);
		
		JButton sil = new JButton("Sil");
		sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(doktor_id.getText().length()==0) {
					
					Helper.Goster("Lütfen Geçerli bir doktor seçiniz");
				}
				else
				{
					if(Helper.confim("sure")) {
						
						int selectID=Integer.parseInt(doktor_id.getText());
						
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.Goster("basari");
								doktor_id.setText(null);
								updateDoctorModel();
								
							}
						} catch (SQLException e2) {
						 
						}
					}
					
				}
				
			}
		});
		sil.setBounds(529, 317, 171, 23);
		panel.add(sil);
		
		
		
		
		
		
		JScrollPane scroll_doktor = new JScrollPane();
		scroll_doktor.setBounds(10, 52, 495, 290);
		panel.add(scroll_doktor);
		
		
		
		
		//ARAMA BURDAN BAÞLIYOR
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 11, 400, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		
		
		JButton btnNewButton_1 = new JButton("  ARA  ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
				
				clearModel.setRowCount(0);
				
 
					String arananKelime=textField_1.getText(); //Textfield içindeki alaný alýyoruz

					Database conn= new Database();
					Statement st=null;
					ResultSet rs=null;
					Connection con=conn.connDb();
					PreparedStatement preparedStatement= null;
					
						
						try {
							
							
							String query="SELECT * FROM user WHERE name LIKE '%"+ arananKelime +"%' AND type='doktor'";
							
							st=con.createStatement();
							preparedStatement=con.prepareStatement(query);
							rs=st.executeQuery(query);
							
							while(rs.next()) /*Dönen sonuc kadar döngüye sokarýz*/
							{
								Object[] colDoctorName=new Object[4];
								colDoctorName[0]=rs.getInt("id");
								colDoctorName[1]=rs.getString("name");
								colDoctorName[2]=rs.getString("tcno");
								colDoctorName[3]=rs.getString("password");
								
								 
									doctorModel.addRow(colDoctorName);
									
								}
							
							 
							
						table_doctor.setModel(doctorModel); 
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					
					 
				
				
			}
		});
		btnNewButton_1.setBounds(416, 10, 89, 20);
		panel.add(btnNewButton_1);
		
		
	
		//BURAYA KADAAAR
		
		table_doctor = new JTable(doctorModel);
		scroll_doktor.setViewportView(table_doctor);
		table_doctor = new JTable(doctorModel);
		scroll_doktor.setViewportView(table_doctor);
				
				
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {


				try {
					doktor_id.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}
				
			}
			
			
		});
		
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {


				if(e.getType()==TableModelEvent.UPDATE) {
					
					int selectID=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
					
					String selectName=table_doctor.getValueAt(table_doctor.getSelectedRow(),1).toString();
					String selectTcno=table_doctor.getValueAt(table_doctor.getSelectedRow(),2).toString();
					String selectPass=table_doctor.getValueAt(table_doctor.getSelectedRow(),3).toString();
					
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
					
						if(control) {
							Helper.Goster("basari");
						}
						
					} catch (Exception e2) {

								e2.printStackTrace();
					}
				}
				
			}
			
			
		});
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Poliklinikler", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane Klinikler = new JScrollPane();
		Klinikler.setBounds(10, 11, 287, 341);
		panel_1.add(Klinikler);
		
		clinicMenu= new JPopupMenu();
		JMenuItem updateMenu=new JMenuItem("Güncelle");
		JMenuItem deleteMenu=new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				int selID= Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString());
				
				Clinic selectClinic = clinic.getFetch(selID);
				
				UpdateClinicGUI updateGUI= new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				
				updateGUI.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
					
				
				
			}
			
		});
				
				
				
				
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					if(Helper.confim("sure")) {
						
						int selID= Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(),0).toString());
						
						try {
							if(clinic.deleteClinic(selID)) {
								Helper.Goster("basari");
								updateClinicModel();
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
		
		
		table_clinic = new JTable(clinicModel);
		
		table_clinic.setComponentPopupMenu(clinicMenu);
		
		table_clinic.addMouseListener(new MouseAdapter(){
			
			@Override
			
			public void mousePressed(MouseEvent ee) {
				
				Point point = ee.getPoint();
				
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
				
			
			
				
			}
			
		});
		
		Klinikler.setViewportView(table_clinic);
		
	
		
		JLabel lblPoliklinlikAd = new JLabel("Poliklinlik Adý");
		lblPoliklinlikAd.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblPoliklinlikAd.setBounds(307, 11, 133, 18);
		panel_1.add(lblPoliklinlikAd);
		

		clinic_adi = new JTextField();
		clinic_adi.setColumns(10);
		clinic_adi.setBounds(307, 37, 122, 20);
		panel_1.add(clinic_adi);
		
		JButton p_ekle = new JButton("Ekle");
		p_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(p_ekle.getText().length()==0) {
				Helper.Goster("hata");	
				}else {
					
					try {
						if (clinic.addClinic(clinic_adi.getText())) {

							Helper.Goster("basari");
							clinic_adi.setText(null);
							updateClinicModel();
							
						} 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		p_ekle.setBounds(307, 59, 122, 23);
		panel_1.add(p_ekle);
		
		JScrollPane sirala = new JScrollPane();
		sirala.setBounds(447, 37, 250, 315);
		panel_1.add(sirala);
		
		table_worker = new JTable();
		sirala.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(305, 296, 133, 20);
		for(int i=0; i<bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e-> {
			
			JComboBox c= (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + ":" + item.getValue());
		});
		
		
		panel_1.add(select_doctor);
		
		JButton work_ekle = new JButton("Ekle");
		work_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
					
					String selClinic=table_clinic.getModel().getValueAt(selRow, 0).toString();
					
					int selClinicID=Integer.parseInt(selClinic);
					
					Item doctorItem=(Item) select_doctor.getSelectedItem();
					
					try {
						boolean control= bashekim.addWorker(doctorItem.getKey(), selClinicID);
					
						if(control) {
							Helper.Goster("basari");
							
							DefaultTableModel clearModel=(DefaultTableModel) table_worker.getModel();
							
							clearModel.setRowCount(0);
							
							for(int i=0; i< bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								
								workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							
							table_worker.setModel(workerModel);
						}
						else
						{
							Helper.Goster("Error!");
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					Helper.Goster("Lütfen Bir Poliklinik Seçiniz!");
				}
					
				
			}
		});
		work_ekle.setBounds(307, 327, 133, 23);
		panel_1.add(work_ekle);
		
		JLabel label = new JLabel("Poliklinlik Ad\u0131");
		label.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label.setBounds(307, 157, 133, 18);
		panel_1.add(label);
		
		JButton p_sec = new JButton("Se\u00E7");
		p_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
					
					String selClinic=table_clinic.getModel().getValueAt(selRow, 0).toString();
					
					int selClinicID=Integer.parseInt(selClinic);
					
					DefaultTableModel clearModel=(DefaultTableModel) table_worker.getModel();
					
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i< bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							
							workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					table_worker.setModel(workerModel);
					
				}else {
					Helper.Goster("Lütfen Bir Poliklinik Seçiniz !");
				}
				
			}
		});
		p_sec.setBounds(307, 186, 122, 23);
		panel_1.add(p_sec);
		
		JLabel lblSeiliPoliklinieAtanan = new JLabel("Se\u00E7ili Poliklini\u011Fe Atanan Doktorlar");
		lblSeiliPoliklinieAtanan.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblSeiliPoliklinieAtanan.setBounds(450, 9, 247, 18);
		panel_1.add(lblSeiliPoliklinieAtanan);
		
			
			
		
		
	 
	}
	
	
	
	
	
	
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
	
		clearModel.setRowCount(0);
		
for(int i=0; i<bashekim.getDoctorList().size(); i++) {
			
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
	}
	
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
	
		clearModel.setRowCount(0);
		
for(int i=0; i<clinic.getList().size(); i++) {
			
	clinicData[0]=clinic.getList().get(i).getId();
	clinicData[1]=clinic.getList().get(i).getName();
			 
	clinicModel.addRow(clinicData);
			
		}
	}
}
