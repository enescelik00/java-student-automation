
public class Idare extends Kullanici{
	
	public Idare(String tckimlik , String sifre , String adsoyad) {
		super(tckimlik, sifre, adsoyad);
	}
	
	@Override
	public String toString() {
	    return getTc() + "," + getSifre() + "," + getAdSoyad();
	}
	
	public static Idare fromString(String data) {
	    String[] parts = data.split(",");
	    return new Idare(parts[0], parts[1], parts[2]);
	}
}
