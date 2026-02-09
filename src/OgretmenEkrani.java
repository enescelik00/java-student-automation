import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;


public class OgretmenEkrani extends Ekran implements CikisYap , SifreDegis {
	private static final long serialVersionUID = 1L;
	Ogretmen ogretmen;
	
    public OgretmenEkrani(Ogretmen ogretmen) {
        super("Öğretmen Ekranı");
        this.ogretmen=ogretmen;
    }

    @Override
    protected void addMenuButtons(JPanel menuBarPanel) { 
    	menuBarPanel.add(createStyledButton("Not Gir", e -> setDynamicPanelContent(createNotGirPanel())));
        menuBarPanel.add(createStyledButton("Ders Ekle", e -> setDynamicPanelContent(createDersEklePanel())));
        menuBarPanel.add(createStyledButton("Öğrenci Ara", e -> setDynamicPanelContent(createOgrenciAraPanel())));
        menuBarPanel.add(createStyledButton("Şifre Değiştir", e -> setDynamicPanelContent(createSifreDegistirPanel())));
        menuBarPanel.add(createStyledButton("Çıkış Yap", e -> {
			if(CikisYap.cikisYap()) {
				dispose();
				new GirisEkrani().setVisible(true);
			}
		}
		
				));
        
      
    }
    
    
    
    
    private JPanel createNotGirPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Öğrenci T.C.:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        JTextField tcField = new JTextField(20);
        panel.add(tcField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Bölüm:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JComboBox<String> bolumComboBox = new JComboBox<>(new String[]{"Bilgisayar Mühendisliği", "Elektrik Mühendisliği", "Makine Mühendisliği" , "Veterinerlik" , "İşletme" , "Psikoloji" , "Beden Eğitimi"});
        panel.add(bolumComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Ders:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JComboBox<String> dersComboBox = new JComboBox<>(UserData.getDerslerByBolum(bolumComboBox.getSelectedItem().toString()).toArray(new String[0]));
        panel.add(dersComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Not:"), gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        JTextField notField = new JTextField(20);
        panel.add(notField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton kaydetButton = new JButton("Kaydet");
        ButtonStyle.grayButtonStyle(kaydetButton);
        panel.add(kaydetButton, gbc);

        kaydetButton.addActionListener(e -> {
            String tc = tcField.getText();
            String ders = dersComboBox.getSelectedItem().toString(); 
            String not = notField.getText();

            if (UserData.getOgrenci(tc) == null) {
                JOptionPane.showMessageDialog(panel, "Öğrenci bulunamadı.");
            } else {
                UserData.addNot(tc, ders, not); 
                JOptionPane.showMessageDialog(panel, "Not başarıyla kaydedildi.");
                tcField.setText("");
                bolumComboBox.setSelectedIndex(0);
                dersComboBox.setModel(new DefaultComboBoxModel<>(UserData.getDerslerByBolum(bolumComboBox.getSelectedItem().toString()).toArray(new String[0])));
                notField.setText("");
            }
        });

        
        bolumComboBox.addActionListener(e -> {
            String selectedBolum = bolumComboBox.getSelectedItem().toString();
            dersComboBox.setModel(new DefaultComboBoxModel<>(UserData.getDerslerByBolum(selectedBolum).toArray(new String[0])));
        });

        return panel;
    }


    
    

    
    private JPanel createOgrenciAraPanel() {
        return IdareEkrani.createAramaPanel(
            new String[]{"TC", "Ad Soyad", "Doğum Tarihi", "Bölüm", "Sınıf"},
            "Öğrenci Arama",
            UserData.getOgrenciler(),
            Ogrenci.class
        );
    }



    private JPanel createDersEklePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Bölüm:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        JComboBox<String> bolumComboBox = new JComboBox<>(new String[]{"Bilgisayar Mühendisliği", "Elektrik Mühendisliği", "Makine Mühendisliği" , "Veterinerlik" , "İşletme" , "Psikoloji" , "Beden Eğitimi"});
        panel.add(bolumComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ders Adı:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JTextField dersField = new JTextField(20);
        panel.add(dersField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton ekleButton = new JButton("Dersi Ekle");
        ButtonStyle.grayButtonStyle(ekleButton);
        panel.add(ekleButton, gbc);

        ekleButton.addActionListener(e -> {
            String bolum = bolumComboBox.getSelectedItem().toString();
            String ders = dersField.getText();

            if (!ders.isEmpty()) {
                UserData.addDers(bolum, ders); 
                dersField.setText("");
                JOptionPane.showMessageDialog(panel, "Ders başarıyla eklendi.");
            } else {
                JOptionPane.showMessageDialog(panel, "Lütfen geçerli bir ders adı girin.");
            }
        });

        return panel;
    }


    

   
    @Override
    public boolean sifreDegistir(String eskiSifre, String yeniSifre) {
        
        String dogruEskiSifre = ogretmen.getSifre();
        
        if (eskiSifre.equals(dogruEskiSifre)) {
            
            return true; 
        } else {
            return false; 
        }
    }
   
   
   private JPanel createSifreDegistirPanel() {
	   JPanel panel = new JPanel();
	    panel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10); 

	  
	    gbc.gridx = 0; gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.EAST;
	    panel.add(new JLabel("Eski Şifre:"), gbc);

	    gbc.gridx = 1; gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    JPasswordField eskiSifreField = new JPasswordField(20);
	    panel.add(eskiSifreField, gbc);
	    eskiSifreField.setBorder(null);

	    
	    gbc.gridx = 0; gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    panel.add(new JLabel("Yeni Şifre:"), gbc);

	    gbc.gridx = 1; gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    JPasswordField yeniSifreField = new JPasswordField(20);
	    panel.add(yeniSifreField, gbc);
	    yeniSifreField.setBorder(null);

	    gbc.gridx = 1; gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.gridwidth = 2; 
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    JButton degistirButton = new JButton("Değiştir");
	    ButtonStyle.grayButtonStyle(degistirButton);
	    degistirButton.setPreferredSize(new Dimension(100 , 30));
	    degistirButton.addActionListener(e -> {
	        String eskiSifre = new String(eskiSifreField.getPassword());
	        String yeniSifre = new String(yeniSifreField.getPassword());

	        if (sifreDegistir(eskiSifre, yeniSifre)) {
	        	ogretmen.setSifre(yeniSifre);
	            JOptionPane.showMessageDialog(panel, "Şifre başarıyla değiştirildi.");
	            yeniSifreField.setText(null);
	            eskiSifreField.setText(null);
	            
	        } else {
	            JOptionPane.showMessageDialog(panel, "Eski şifre hatalı. Lütfen tekrar deneyin.");
	        }
	    });
	    panel.add(degistirButton, gbc);

	    return panel;
    }
}

