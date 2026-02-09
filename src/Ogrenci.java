import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



public class Ogrenci extends Kullanici {
	
	private String dogumtarihi;
	private String bolum;
	private String sinif;
	private ArrayList<String> alinandersler;
	private Map<String, String> notlar = new HashMap<String, String>();

	public Ogrenci(String tckimlik , String sifre , String adsoyad , String dogumtarihi , String bolum , String sinif)  {
		super(tckimlik, sifre , adsoyad);
		this.dogumtarihi=dogumtarihi;
		this.bolum=bolum;
		this.sinif=sinif;
	}

	public String getDogumtarihi() {
		return dogumtarihi;
	}

	public void setDogumtarihi(String dogumtarihi) {
		this.dogumtarihi = dogumtarihi;
	}

	public String getBolum() {
		return bolum;
	}

	public void setBolum(String bolum) {
		this.bolum = bolum;
	}

	public String getSinif() {
		return sinif;
	}

	public void setSinif(String sinif) {
		this.sinif = sinif;
	}
	
	@Override
	public String toString() {
	    return getTc() + "," + getSifre() + "," + getAdSoyad() + "," + dogumtarihi + "," + bolum + "," + sinif;
	}
	
	public static Ogrenci fromString(String data) {
	    String[] parts = data.split(",");
	    return new Ogrenci(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5] );
	}

	public ArrayList<String> getAlinandersler() {
		return alinandersler;
	}

	public void setAlinandersler(List<String> dersler) {
        if (this.alinandersler == null) {
            this.alinandersler = new ArrayList<>();
        }
        this.alinandersler.clear(); 
        this.alinandersler.addAll(dersler); 
    }

	public Map<String, String> getNotlar() {
		return notlar;
	}

	public void setNotlar(Map<String, String> notlar) {
		this.notlar = notlar;
	}
	
	 public void addNot(String ders, String not) {
	        notlar.put(ders, not); 
	    }
}
