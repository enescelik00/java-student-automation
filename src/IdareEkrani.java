import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class IdareEkrani extends Ekran implements CikisYap , SifreDegis {
	private static final long serialVersionUID = 1L;
	Idare idare;
	
    public IdareEkrani(Idare idare) {
        super("İdare Ekranı");
        this.idare=idare;
    }

    @Override
    protected void addMenuButtons(JPanel menuBarPanel) { 
    	menuBarPanel.add(createStyledButton("Öğretmen Ekle-Sil", e -> setDynamicPanelContent(createOgretmenEkleSilPanel())));
        menuBarPanel.add(createStyledButton("Öğrenci Ekle-Sil", e -> setDynamicPanelContent(createOgrenciEkleSilPanel())));
        menuBarPanel.add(createStyledButton("Ders Programı Ekle", e -> setDynamicPanelContent(createDersProgramiEklePanel())));
        menuBarPanel.add(createStyledButton("Öğretmen Ara", e -> setDynamicPanelContent(createOgretmenAraPanel())));
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
    
    
    public static void updateTableFromMap(DefaultTableModel tableModel, Map<String, ?> dataMap) {
        tableModel.setRowCount(0); 
        for (Map.Entry<String, ?> entry : dataMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Ogrenci) {
                Ogrenci ogrenci = (Ogrenci) value;
                tableModel.addRow(new Object[]{ogrenci.getTc(), ogrenci.getAdSoyad(), ogrenci.getDogumtarihi(), ogrenci.getBolum(), ogrenci.getSinif()});
            } else if (value instanceof Ogretmen) {
                Ogretmen ogretmen = (Ogretmen) value;
                tableModel.addRow(new Object[]{ogretmen.getTc(), ogretmen.getAdSoyad(), ogretmen.getAlan()});
            }
        }
    }
    
    
    private JPanel createEkleSilPanel(String[] columnNames, String title, Map<String, ?> dataMap, Class<?> dataType) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

       
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        
        JPanel inputPanel = new JPanel(new GridLayout(columnNames.length + 1, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(title));

        
        JTextField[] textFields = new JTextField[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            inputPanel.add(new JLabel(columnNames[i] + ":"));
            textFields[i] = new JTextField();
            inputPanel.add(textFields[i]);
        }

        
        JButton ekleButton = new JButton("Ekle");
        JButton silButton = new JButton("Sil");
        
        ButtonStyle.grayButtonStyle(ekleButton);
        ButtonStyle.grayButtonStyle(silButton);

        inputPanel.add(ekleButton);
        inputPanel.add(silButton);

        
        updateTableFromMap(tableModel, dataMap);

       
        ekleButton.addActionListener(e -> {
            String[] rowData = new String[columnNames.length];
            boolean allFieldsFilled = true;

            for (int i = 0; i < columnNames.length; i++) {
                rowData[i] = textFields[i].getText();
                if (rowData[i].isEmpty()) {
                    allFieldsFilled = false;
                    break;
                }
            }

            if (allFieldsFilled) {
                try {
                    if (dataType == Ogrenci.class) {
                        Ogrenci ogrenci = new Ogrenci(rowData[0], "password", rowData[1], rowData[2], rowData[3], rowData[4]);
                        UserData.addOgrenci(rowData[0], ogrenci);
                    } else if (dataType == Ogretmen.class) {
                        Ogretmen ogretmen = new Ogretmen(rowData[0], "password", rowData[1], rowData[2]);
                        UserData.addOgretmen(rowData[0], ogretmen);
                    }
                    updateTableFromMap(tableModel, dataMap);
                    for (JTextField field : textFields) {
                        field.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Veri eklenirken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        
        silButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String key = (String) tableModel.getValueAt(selectedRow, 0);
                if (dataType == Ogrenci.class) {
                    UserData.removeOgrenci(key);
                } else if (dataType == Ogretmen.class) {
                    UserData.removeOgretmen(key);
                }
                updateTableFromMap(tableModel, dataMap);
            } else {
                JOptionPane.showMessageDialog(panel, "Lütfen silmek istediğiniz satırı seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        
        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }
    	    
    	    
            private JPanel createOgretmenEkleSilPanel() {
              return createEkleSilPanel(
              new String[]{"TC", "Ad Soyad", "Branş"},
              "Öğretmen Bilgileri",
               UserData.getOgretmenler(),
              Ogretmen.class
            );
      }
    	    
    	    private JPanel createOgrenciEkleSilPanel() {
    	        return createEkleSilPanel(
    	            new String[]{"TC", "Ad Soyad", "Doğum Tarihi", "Bölüm", "Sınıf"},
    	            "Öğrenci Bilgileri",
    	            UserData.getOgrenciler(),
    	            Ogrenci.class
    	        );
    	    }


    	    public static JPanel createAramaPanel(String[] columnNames, String title, Map<String, ?> dataMap, Class<?> dataType) {
    	        JPanel panel = new JPanel(new BorderLayout(10, 10));

    	        
    	        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    	        JTable table = new JTable(tableModel);
    	        JScrollPane tableScrollPane = new JScrollPane(table);

    	        
    	        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    	        searchPanel.setBorder(BorderFactory.createTitledBorder(title));

    	        JLabel searchLabel = new JLabel("TC Kimlik No:");
    	        JTextField searchField = new JTextField();
    	        JButton searchButton = new JButton("Ara");
    	        JButton resetButton = new JButton("Sıfırla");
    	        
    	        ButtonStyle.grayButtonStyle(resetButton);
    	        ButtonStyle.grayButtonStyle(searchButton);

    	        searchPanel.add(searchLabel);
    	        searchPanel.add(searchField);
    	        searchPanel.add(searchButton);
    	        searchPanel.add(resetButton);

    	       
    	        updateTableFromMap(tableModel, dataMap);

    	        
    	        searchButton.addActionListener(e -> {
    	            String searchKey = searchField.getText().trim();
    	            if (searchKey.isEmpty()) {
    	                JOptionPane.showMessageDialog(panel, "Lütfen bir TC Kimlik No girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
    	                return;
    	            }

    	            tableModel.setRowCount(0); 
    	            Object result = dataMap.get(searchKey);

    	            if (result != null) {
    	                if (dataType == Ogrenci.class) {
    	                    Ogrenci ogrenci = (Ogrenci) result;
    	                    tableModel.addRow(new Object[]{ogrenci.getTc(), ogrenci.getAdSoyad(), ogrenci.getDogumtarihi(), ogrenci.getBolum(), ogrenci.getSinif()});
    	                } else if (dataType == Ogretmen.class) {
    	                    Ogretmen ogretmen = (Ogretmen) result;
    	                    tableModel.addRow(new Object[]{ogretmen.getTc(), ogretmen.getAdSoyad(), ogretmen.getAlan()});
    	                }
    	            } else {
    	                JOptionPane.showMessageDialog(panel, "Aranan TC Kimlik No ile eşleşen kayıt bulunamadı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        });

    	        
    	        resetButton.addActionListener(e -> {
    	            searchField.setText("");
    	            updateTableFromMap(tableModel, dataMap);
    	        });

    	        
    	        panel.add(tableScrollPane, BorderLayout.CENTER);
    	        panel.add(searchPanel, BorderLayout.SOUTH);

    	        return panel;
    	    }

    	    private JPanel createOgrenciAraPanel() {
    	        return createAramaPanel(
    	            new String[]{"TC", "Ad Soyad", "Doğum Tarihi", "Bölüm", "Sınıf"},
    	            "Öğrenci Arama",
    	            UserData.getOgrenciler(),
    	            Ogrenci.class
    	        );
    	    }
    	    
    	    private JPanel createOgretmenAraPanel() {
    	        return createAramaPanel(
    	            new String[]{"TC", "Ad Soyad", "Branş"},
    	            "Öğretmen Arama",
    	            UserData.getOgretmenler(),
    	            Ogretmen.class
    	        );
    	    }

    	    private JPanel createDersProgramiEklePanel() {
    	        JPanel panel = new JPanel(new BorderLayout());

    	        String[] columnNames = { "Saatler", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma" };
    	        String[][] emptyProgram = {
    	            { "10.00-10.50", "", "", "", "", "" },
    	            { "11.00-11.50", "", "", "", "", "" },
    	            { "12.00-12.50", "", "", "", "", "" },
    	            { "14.00-14.50", "", "", "", "", "" },
    	            { "15.00-15.50", "", "", "", "", "" },
    	            { "16.00-16.50", "", "", "", "", "" }
    	        };

    	        DefaultTableModel tableModel = new DefaultTableModel(emptyProgram, columnNames);
    	        JTable table = new JTable(tableModel);
    	        JScrollPane scrollPane = new JScrollPane(table);

    	        JPanel inputPanel = new JPanel(new GridBagLayout());
    	        GridBagConstraints gbc = new GridBagConstraints();
    	        gbc.insets = new Insets(10, 10, 10, 10);

    	        gbc.gridx = 0; gbc.gridy = 0;
    	        inputPanel.add(new JLabel("Bölüm:"), gbc);

    	        gbc.gridx = 1; gbc.gridy = 0;
    	        JTextField bolumField = new JTextField(20);
    	        inputPanel.add(bolumField, gbc);

    	        gbc.gridx = 1; gbc.gridy = 1;
    	        JButton ekleButton = new JButton("Kaydet");
    	        inputPanel.add(ekleButton, gbc);
    	        ButtonStyle.grayButtonStyle(ekleButton);

    	        ekleButton.addActionListener(e -> {
    	            String bolum = bolumField.getText().trim();
    	            if (!bolum.isEmpty()) {
    	                int rowCount = tableModel.getRowCount();
    	                int colCount = tableModel.getColumnCount();

    	                String[][] program = new String[rowCount][colCount];
    	                for (int i = 0; i < rowCount; i++) {
    	                    for (int j = 0; j < colCount; j++) {
    	                        program[i][j] = tableModel.getValueAt(i, j).toString();
    	                    }
    	                }

    	                UserData.addDersProgrami(bolum, program);
    	                JOptionPane.showMessageDialog(panel, "Ders programı başarıyla kaydedildi.");
    	                
    	                
    	                for (int i = 0; i < rowCount; i++) {
    	                    for (int j = 1; j < colCount; j++) { 
    	                        tableModel.setValueAt("", i, j);
    	                    }
    	                }
    	                
    	                bolumField.setText("");
    	            } else {
    	                JOptionPane.showMessageDialog(panel, "Lütfen bölüm adını girin.");
    	            }
    	        });

    	        panel.add(inputPanel, BorderLayout.NORTH);
    	        panel.add(scrollPane, BorderLayout.CENTER);
    	        return panel;
    	    }





  

    

  

 
    
   
    
    @Override
    public boolean sifreDegistir(String eskiSifre, String yeniSifre) {
        
        String dogruEskiSifre = idare.getSifre();
        
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
	        	idare.setSifre(yeniSifre);
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
