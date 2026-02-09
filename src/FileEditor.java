import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileEditor {

	public static void saveData(Map<String, Ogrenci> ogrenciler,
            Map<String, Ogretmen> ogretmenler,
            Map<String, Idare> idareciler) {
   try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ENES\\Desktop\\userdata.txt"))) {

   for (Ogrenci ogrenci : ogrenciler.values()) {
    writer.write("Ogrenci," + ogrenci.toString());
    writer.newLine();
}


   for (Ogretmen ogretmen : ogretmenler.values()) {
    writer.write("Ogretmen," + ogretmen.toString());
    writer.newLine();
}


   for (Idare idare : idareciler.values()) {
    writer.write("Idare," + idare.toString());
    writer.newLine();
}
}  catch (IOException e) {
    e.printStackTrace();
}
}
	
	
	
	public static void loadData(Map<String, Ogrenci> ogrenciler,
            Map<String, Ogretmen> ogretmenler,
            Map<String, Idare> idareciler) {
try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ENES\\Desktop\\userdata.txt"))) {
String line;
while ((line = reader.readLine()) != null) {
String[] parts = line.split(",", 2);  
if (parts.length >= 2) {  
String userType = parts[0];
String userData = parts[1];

switch (userType) {
   case "Ogrenci":
       Ogrenci ogrenci = Ogrenci.fromString(userData);
       ogrenciler.put(ogrenci.getTc(), ogrenci);
       break;
   case "Ogretmen":
       Ogretmen ogretmen = Ogretmen.fromString(userData);
       ogretmenler.put(ogretmen.getTc(), ogretmen);
       break;
   case "Idare":
       Idare idare = Idare.fromString(userData);
       idareciler.put(idare.getTc(), idare);
       break;
}
} else {
System.err.println("Satır ayrılmış değil, kullanıcı tipi bulunamadı: " + line);
}
}
} catch (IOException e) {
System.err.println("Dosya yüklenemedi, başlangıç değerleri yüklenecek: " + e.getMessage());
}
}
}

