import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserData {
	
	
	private static Map<String, Ogrenci> ogrenciler = new HashMap<>();
    private static Map<String, Ogretmen> ogretmenler = new HashMap<>();
    private static Map<String, Idare> idareciler = new HashMap<>();
    private static Map<String, String[][]> dersProgramlari = new HashMap<>();
    
    private static Map<String, List<String>> bolumDersleri = new HashMap<>();
    
		
	
    
    
    public static void saveStudentCourses(Ogrenci ogrenci) {
        List<String> secilenDersler = ogrenci.getAlinandersler(); 
        if (secilenDersler != null) {
            for (String ders : secilenDersler) {
                String bolum = ogrenci.getBolum(); 
            
        }
        }}
    
    public static void addOgrenci(String tc, Ogrenci ogrenci) {
        ogrenciler.put(tc, ogrenci);
    }

    public static void removeOgrenci(String tc) {
        ogrenciler.remove(tc);
    }

    public static Ogrenci getOgrenci(String tc) {
        return ogrenciler.get(tc);
    }
    
    public static Map<String, Ogrenci> getOgrenciler() {
        return ogrenciler;
    }
    
    public static Map<String, String> getNotlarByOgrenci(String tc) {
        Ogrenci ogrenci = getOgrenci(tc);
        return ogrenci != null ? ogrenci.getNotlar() : null;
    }

    public static void addNot(String tc, String ders, String not) {
        Ogrenci ogrenci = getOgrenci(tc);
        if (ogrenci != null) {
            ogrenci.addNot(ders, not); 
        }
    }

    
    
    
    public static void addOgretmen(String tc, Ogretmen ogretmen) {
        ogretmenler.put(tc, ogretmen);
    }

    public static void removeOgretmen(String tc) {
        ogretmenler.remove(tc);
    }

    public static Ogretmen getOgretmen(String tc) {
        return ogretmenler.get(tc);
    }
    
    public static Map<String, Ogretmen> getOgretmenler() {
        return ogretmenler;
    }


   
    
    public static void addIdare(String tc, Idare idare) {
        idareciler.put(tc, idare);
    }

    public static void removeIdare(String tc) {
        idareciler.remove(tc);
    }

    public static Idare getIdare(String tc) {
        return idareciler.get(tc);
    }
    
    public static Map<String, Idare> getIdareciler() {
        return idareciler;
    }
    
    
    
    public static void addDersProgrami(String bolum, String[][] program) {
        dersProgramlari.put(bolum, program);
    }

    public static String[][] getDersProgrami(String bolum) {
        return dersProgramlari.get(bolum);
    }

    public static Map<String, String[][]> getDersProgramlari() {
        return dersProgramlari;
    }
    
    
    
    
  
    
    
    
    public static List<String> getDerslerByBolum(String bolum) {
        return bolumDersleri.getOrDefault(bolum, new ArrayList<>()); 
    }
    
    public static void addDers(String bolum, String ders) {
        bolumDersleri.computeIfAbsent(bolum, k -> new ArrayList<>()).add(ders); 
    }
    
    
 

    
 
    
    
    
    public static void initializeDefaultDataIfEmpty() {
        if (ogrenciler.isEmpty() && ogretmenler.isEmpty() && idareciler.isEmpty()) {
            addIdare("123", new Idare("123", "idare123", "Muammer Yönev"));
            addOgrenci("456", new Ogrenci("456", "ogrenci456", "Enes Çelik", "20.08.2004", "Bilgisayar", "2"));
            addOgretmen("789", new Ogretmen("789", "ogretmen789", "Turan Çaldıran", "Matematik"));
            
     
        }
    }

	public static Map<String, List<String>> getBolumDersleri() {
		return bolumDersleri;
	}

	public static void setBolumDersleri(Map<String, List<String>> bolumDersleri) {
		UserData.bolumDersleri = bolumDersleri;
	}    

}
