package Baglanti;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper extends YardimciMetotlar {
	
 
	
	public static void Goster(String str) {


		optionPaneChangeButtonText();
		
		String msg;
		
		switch (str) {
		case "hata":
			msg="L�tfen Bo� Alan B�rakmay�n�z!";
			break;
			
		case "basari":
			msg="��lem Ba�ar�l�";
			break;
			
	case "error":
			msg="Bir Hata Ger�ekle�ti!";
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
			
			msg="Bu i�lemi ger�ekle�tirmek istiyor musun?";
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
