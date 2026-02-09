import javax.swing.*;


public interface CikisYap{
	
	static boolean cikisYap() {
		 int option = JOptionPane.showConfirmDialog(null, 
		            "Çıkış yapmak istediğinize emin misiniz?", 
		            "Çıkış", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE);
		 return option == JOptionPane.YES_OPTION;
	}
	
}
