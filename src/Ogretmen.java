
public class Ogretmen extends Kullanici {
	private String alan;

	public Ogretmen(String tckimlikString , String sifre , String adsoyad , String alan) {
		super(tckimlikString, sifre , adsoyad);
		this.alan=alan;
}

	public String getAlan() {
		return alan;
	}

	public void setAlan(String alan) {
		this.alan = alan;
	}
	
	@Override
	public String toString() {
	    return getTc() + "," + getSifre() + "," + getAdSoyad() + "," + alan;
	}
	
	public static Ogretmen fromString(String data) {
	    String[] parts = data.split(",");
	    return new Ogretmen(parts[0], parts[1], parts[2], parts[3]);
	}
}