package Baglanti;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper extends YardimciMetotlar {
	
 
	
	public static void Goster(String str) {


		optionPaneChangeButtonText();
		
		String msg;
		
		switch (str) {
		case "hata":
			msg="Lütfen Boþ Alan Býrakmayýnýz!";
			break;
			
		case "basari":
			msg="Ýþlem Baþarýlý";
			break;
			
	case "error":
			msg="Bir Hata Gerçekleþti!";
			break;
			
			default:
				msg=str;
				break;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public static boolean confim(String str) {
		
		optionPaneChangeButtonText();
		
		String msg;
		
		switch (str) {
		case "sure":
			
			msg="Bu iþlemi gerçekleþtirmek istiyor musun?";
			break;
		 
			
			default:
				msg=str;
				break;
		}
		
		int res= JOptionPane.showConfirmDialog(null, msg, "Dikkat !!!", JOptionPane.YES_NO_OPTION);
		
		if(res==0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

}
