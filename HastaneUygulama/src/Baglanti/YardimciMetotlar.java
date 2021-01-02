package Baglanti;

import javax.swing.UIManager;

public class YardimciMetotlar {
	
	
	public static void optionPaneChangeButtonText() {
		
		UIManager.put("OptionPane.cancelButtonText","Ýptal");
		UIManager.put("OptionPane.noButtonText","Hayýr");
		UIManager.put("OptionPane.yesButtonText","Evet");
		UIManager.put("OptionPane.okButtonText","Tamam");
	}
	

}
