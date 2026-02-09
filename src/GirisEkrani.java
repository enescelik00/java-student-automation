import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;

public class GirisEkrani extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordField;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GirisEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Öğrenci İşleri Otomasyonu - Giriş");
	

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TC Kimlik No:");
		lblNewLabel.setBounds(89, 67, 99, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Şifre:");
		lblNewLabel_1.setBounds(89, 109, 99, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Giriş");
		btnNewButton.setBounds(195, 167, 96, 51);
		contentPane.add(btnNewButton);
		btnNewButton.setBorder(null);
	
		
		
		
		textField = new JTextField();
		textField.setBounds(198, 64, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		
	
		passwordField = new JPasswordField();
        passwordField.setBounds(198, 106, 96, 19);
        contentPane.add(passwordField);
        passwordField.setBorder(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("İdare");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(180, 26, 58, 21);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setActionCommand("İdare");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Öğretmen");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(240, 26, 87, 21);
		contentPane.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setActionCommand("Öğretmen");
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Öğrenci");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(329, 26, 71, 21);
		contentPane.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setActionCommand("Öğrenci");
		
		JLabel lblNewLabel_2 = new JLabel("Kullanıcı Tipi:");
		lblNewLabel_2.setBounds(89, 30, 85, 13);
		contentPane.add(lblNewLabel_2);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String tckimlik = textField.getText();
            	String sifre = new String(passwordField.getPassword());
            	String userType = getSelectedUserType();
            	
                if (tckimlik.isEmpty() || sifre.isEmpty() || userType==null) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun ve kullanıcı tipi seçin.");
                    return;
                } 
                
                if (userType.equals("Öğrenci")) {
                    Ogrenci ogrenci = UserData.getOgrenci(tckimlik); 
                    if (ogrenci == null) {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    } else if (ogrenci.getSifre().equals(sifre)) {
                        new OgrenciEkrani(ogrenci).setVisible(true); 
                        dispose();  
                    } else {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    }
                } 
                else if (userType.equals("Öğretmen")) {
                    Ogretmen ogretmen = UserData.getOgretmen(tckimlik);  
                    if (ogretmen == null) {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    } else if (ogretmen.getSifre().equals(sifre)) {
                        new OgretmenEkrani(ogretmen).setVisible(true);  
                        dispose();  
                    } else {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    }
                } 
                else if (userType.equals("İdare")) {
                    Idare idare = UserData.getIdare(tckimlik);  
                    if (idare == null) {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    } else if (idare.getSifre().equals(sifre)) {
                        new IdareEkrani(idare).setVisible(true);  
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Hatalı TC , şifre veya kullanıcı tipi");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen geçerli bir kullanıcı tipi seçin.");
                }
            }
        });

        getRootPane().setDefaultButton(btnNewButton);
        
        
    }

	private String getSelectedUserType() {
	    if(buttonGroup.getSelection() != null) {
	        return buttonGroup.getSelection().getActionCommand();
	    }
	    return null;
	}
	}
