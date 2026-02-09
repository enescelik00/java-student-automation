

public class OgrenciİsleriOtomasyonu {

	
	public static void main(String[] args) {
		
		
		
		FileEditor.loadData(UserData.getOgrenciler(), 
                UserData.getOgretmenler(), 
                UserData.getIdareciler());
		
		UserData.initializeDefaultDataIfEmpty();
		
		
		new GirisEkrani().setVisible(true);
		
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	        FileEditor.saveData(UserData.getOgrenciler(), 
	                                 UserData.getOgretmenler(), 
	                                 UserData.getIdareciler());
	    }));
		
	}
}


