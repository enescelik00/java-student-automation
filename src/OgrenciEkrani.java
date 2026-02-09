import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;




public class OgrenciEkrani extends Ekran implements CikisYap , SifreDegis{
	
	private static final long serialVersionUID = 1L;
	Ogrenci ogrenci;
	
	
	
	public OgrenciEkrani(Ogrenci ogrenci) {
        super("Öğrenci Ekranı");
        this.ogrenci=ogrenci;
    }
	
	@Override
	protected void addMenuButtons(JPanel menuBarPanel) {
		
		menuBarPanel.add(createStyledButton("Öğrenci Bilgileri", e -> setDynamicPanelContent(createOgrenciInfoPanel())));
        menuBarPanel.add(createStyledButton("Ders Programı", e -> setDynamicPanelContent(createOgrenciDersProgramiPanel(ogrenci.getTc()))));
        menuBarPanel.add(createStyledButton("Ders Alma", e -> setDynamicPanelContent(createOgrenciDersAlmaPanel(ogrenci))));
        menuBarPanel.add(createStyledButton("Aldığım Dersler", e -> setDynamicPanelContent(createAlınanDersPanel())));
        menuBarPanel.add(createStyledButton("Notlarım", e -> setDynamicPanelContent(createNotlarPanel())));
        menuBarPanel.add(createStyledButton("Şifre Değiştir", e -> setDynamicPanelContent(createSifreDegistirPanel())));
		menuBarPanel.add(createStyledButton("Çıkış Yap", e -> {
			if(CikisYap.cikisYap()) {
				dispose();
				new GirisEkrani().setVisible(true);
			}
		}
		
				));
	}
	
	
	
	
	
	  private JPanel createOgrenciInfoPanel() {
	        JPanel panel = new JPanel();
	        panel.add(new JLabel("Öğrenci Bilgileri Ekranı."));
	       
	        JPanel contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(null);
            
            JLabel lblNewLabel = new JLabel("Öğrenci Profili");
            lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblNewLabel.setBounds(225, 10, 112, 22);
            contentPane.add(lblNewLabel);
            
            JLabel lblNewLabel_1 = new JLabel("İsim Soyisim:");
            lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNewLabel_1.setBounds(59, 54, 88, 19);
            contentPane.add(lblNewLabel_1);
            
            JLabel lblogrenciismi = new JLabel(ogrenci.getAdSoyad());
            lblogrenciismi.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblogrenciismi.setBounds(157, 54, 150, 19);
            contentPane.add(lblogrenciismi);
            
            JLabel lblNewLabel_2 = new JLabel("Tc Kimlik No:");
            lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNewLabel_2.setBounds(57, 84, 88, 19);
            contentPane.add(lblNewLabel_2);
            
            JLabel lblogrencitc = new JLabel(ogrenci.getTc());
            lblogrencitc.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblogrencitc.setBounds(157, 84, 150, 19);
            contentPane.add(lblogrencitc);
            
            JLabel lblNewLabel_3 = new JLabel("Doğum Tarihi:");
            lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNewLabel_3.setBounds(59, 113, 97, 19);
            contentPane.add(lblNewLabel_3);
            
            JLabel lblogerncidogum = new JLabel(ogrenci.getDogumtarihi());
            lblogerncidogum.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblogerncidogum.setBounds(157, 113, 150, 19);
            contentPane.add(lblogerncidogum);
            
            JLabel lblNewLabel_4 = new JLabel("Bölümü:");
            lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNewLabel_4.setBounds(59, 142, 56, 19);
            contentPane.add(lblNewLabel_4);
            
            JLabel lblogrencibolum = new JLabel(ogrenci.getBolum());
            lblogrencibolum.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblogrencibolum.setBounds(157, 142, 150, 19);
            contentPane.add(lblogrencibolum);
            
            JLabel lblNewLabel_5 = new JLabel("Sınıfı:");
            lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNewLabel_5.setBounds(59, 171, 38, 19);
            contentPane.add(lblNewLabel_5);
            
            JLabel lblogrencisınıf = new JLabel(ogrenci.getSinif());
            lblogrencisınıf.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblogrencisınıf.setBounds(157, 176, 150, 19);
            contentPane.add(lblogrencisınıf);
            
            JLabel lblfoto = new JLabel();
            lblfoto.setBounds(337, 54, 183, 222);
            contentPane.add(lblfoto);

            try {
            	URL url = URI.create("https://instagram.fist6-3.fna.fbcdn.net/v/t51.2885-19/472428817_1858117404947071_6976770246241396438_n.jpg?stp=dst-jpg_s320x320_tt6&_nc_ht=instagram.fist6-3.fna.fbcdn.net&_nc_cat=107&_nc_ohc=naC3TjubFYEQ7kNvgFGgj7F&_nc_gid=a6dcf3b87faf4c459f4482c71b25b489&edm=AOQ1c0wBAAAA&ccb=7-5&oh=00_AYBQy38-wTptAwF1k31VSH8TUVGxEctH-SIMh3qUvBTS4g&oe=677B61C3&_nc_sid=8b3546").toURL();
                ImageIcon defaultIcon = new ImageIcon(url);

                lblfoto.setIcon(resizeImage(defaultIcon, lblfoto.getWidth(), lblfoto.getHeight()));
                lblfoto.setSize(200, 190);
            } catch (Exception e) {
                e.printStackTrace();
                lblfoto.setText("Resim yüklenemedi!");
            }

            return contentPane;
        }

        private static ImageIcon resizeImage(ImageIcon icon, int width, int height) {
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        
	        
	    }
	  
        
        
        
        
        
        
        
        

        private JPanel createOgrenciDersProgramiPanel(String ogrenciTc) {
            JPanel panel = new JPanel();
            panel.setLayout(null);

            
            Ogrenci ogrenci = UserData.getOgrenci(ogrenciTc);
            if (ogrenci == null) {
                JLabel hataLabel = new JLabel("Öğrenci bulunamadı.");
                hataLabel.setBounds(10, 10, 200, 25);
                panel.add(hataLabel);
                return panel;
            }

            String bolum = ogrenci.getBolum();
            String[][] dersProgrami = UserData.getDersProgrami(bolum);

            if (dersProgrami == null) {
                JLabel hataLabel = new JLabel("Bu bölüme ait ders programı bulunmamaktadır.");
                hataLabel.setBounds(10, 10, 300, 25);
                panel.add(hataLabel);
                return panel;
            }

            
            String[] baslik = {"Saatler", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma"};
            DefaultTableModel model = new DefaultTableModel(dersProgrami, baslik) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 10, 564, 340);
            panel.add(scrollPane);

            return panel;
        }

	    
	    
	    
	
	    
	    private void styleButton(JButton button) {
	        button.setBackground(new Color(60, 120, 180)); 
	        button.setForeground(Color.WHITE); 
	        button.setFont(new Font("Arial", Font.BOLD, 14));
	        button.setBorder(BorderFactory.createLineBorder(new Color(60, 120, 180))); 
	        button.setFocusPainted(false); 
	        button.setPreferredSize(new Dimension(100, 30)); 
	    }

	    public JPanel createOgrenciDersAlmaPanel(Ogrenci ogrenci) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout(10, 10));
	        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        
	        
	        List<String> mevcutDerslerList = UserData.getDerslerByBolum(ogrenci.getBolum());
	        DefaultListModel<String> mevcutDerslerModel = new DefaultListModel<>();
	        for (String ders : mevcutDerslerList) {
	            mevcutDerslerModel.addElement(ders);
	        }
	        JList<String> mevcutDerslerListComponent = new JList<>(mevcutDerslerModel);
	        mevcutDerslerListComponent.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        JScrollPane mevcutDerslerScrollPane = new JScrollPane(mevcutDerslerListComponent);
	        mevcutDerslerScrollPane.setPreferredSize(new Dimension(150, 200));
	        
	        DefaultListModel<String> secilenDerslerModel = new DefaultListModel<>();
	        JList<String> secilenDerslerList = new JList<>(secilenDerslerModel);
	        JScrollPane secilenDerslerScrollPane = new JScrollPane(secilenDerslerList);
	        secilenDerslerScrollPane.setPreferredSize(new Dimension(150, 200));
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(2, 1, 5, 5));
	        JButton ekleButton = new JButton(">>");
	        JButton cikarButton = new JButton("<<");
	        
	        
	        ekleButton.addActionListener(e -> {
	            for (String secilenDers : mevcutDerslerListComponent.getSelectedValuesList()) {
	                if (!secilenDerslerModel.contains(secilenDers)) {
	                    secilenDerslerModel.addElement(secilenDers);
	                }
	            }
	        });
	        
	       
	        cikarButton.addActionListener(e -> {
	            for (String secilenDers : secilenDerslerList.getSelectedValuesList()) {
	                secilenDerslerModel.removeElement(secilenDers);
	            }
	        });
	        
	        buttonPanel.add(ekleButton);
	        buttonPanel.add(cikarButton);
	        
	        JButton kaydetButton = new JButton("Kaydet");
	        kaydetButton.addActionListener(e -> {
	            if (secilenDerslerModel.isEmpty()) {
	                JOptionPane.showMessageDialog(panel, "Lütfen en az bir ders seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
	            } else {
	                List<String> secilenDersler = new ArrayList<>(Collections.list(secilenDerslerModel.elements()));
	                ogrenci.setAlinandersler(secilenDersler); 
	                UserData.saveStudentCourses(ogrenci); 
	                JOptionPane.showMessageDialog(panel, "Dersler başarıyla kaydedildi.");
	            }
	        });
	        
	        JPanel listsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
	        listsPanel.add(mevcutDerslerScrollPane);
	        listsPanel.add(buttonPanel);
	        listsPanel.add(secilenDerslerScrollPane);
	        
	        panel.add(listsPanel, BorderLayout.CENTER);
	        panel.add(kaydetButton, BorderLayout.SOUTH);
	        
	        styleButton(kaydetButton);
	        styleButton(cikarButton);
	        styleButton(ekleButton);
	        return panel;
	    }




	    
	    
	    
	    

	    private JPanel createAlınanDersPanel() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout(10, 10)); 
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        
	        ArrayList<String> dersler = ogrenci.getAlinandersler(); 
	        
	        JList<String> list;
	        
	        if (dersler == null || dersler.isEmpty()) {
	            
	            list = new JList<>(new String[] {"Henüz ders alınmadı"});
	        } else {
	            list = new JList<>(new DefaultListModel<>()); 
	            ((DefaultListModel<String>) list.getModel()).addAll(dersler); 
	        }

	        list.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
	        list.setSelectionBackground(new Color(173, 216, 230)); 
	        list.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 
	        list.setPreferredSize(new Dimension(150, 100));
	        
	       
	        
	        
	        JLabel lblNewLabel = new JLabel("Alınan Dersler:");
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18)); 
	        lblNewLabel.setForeground(Color.DARK_GRAY); 
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        
	       
	        panel.add(lblNewLabel, BorderLayout.NORTH); 
	        panel.add(new JScrollPane(list), BorderLayout.CENTER); 

	        return panel;
	    }
	    
	    
	    
	    

	    private JPanel createNotlarPanel() {
	        JPanel panel = new JPanel();
	        panel.setLayout(null);

	       
	        String[] baslik = {"Ders", "Not"};
	        Object[][] notlarArray = ogrenci.getNotlar().entrySet().stream()
	            .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
	            .toArray(Object[][]::new);  

	        DefaultTableModel model = new DefaultTableModel(notlarArray, baslik) {
	            private static final long serialVersionUID = 1L;

	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; 
	            }
	        };

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 10, 381, 243); 
	        panel.add(scrollPane);

	        return panel;
	    }

	    
	    
	    
	    
	    
	    
	    
	   @Override
	    public boolean sifreDegistir(String eskiSifre, String yeniSifre) {
	        
	        String dogruEskiSifre = ogrenci.getSifre();
	        
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
		        	ogrenci.setSifre(yeniSifre);
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