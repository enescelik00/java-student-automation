abstract class Kullanici {
	private String tckimlik;
	private String sifre;
	private String adsoyad;
	
	
	public Kullanici(String tckimlik , String sifre, String adsoyad ) {
		this.sifre=sifre;
		this.tckimlik=tckimlik;
		this.adsoyad= adsoyad;
	}
	


public String getTc() {
	return tckimlik;
}

public String getSifre() {
	return sifre;
}

public String getAdSoyad() {
	return adsoyad;
}

public void setSifre(String sifre) {
	this.sifre = sifre;
}

public void setAdsoyad(String adsoyad) {
	this.adsoyad = adsoyad;
}
}