import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public interface ButtonStyle {

	static void grayButtonStyle(JButton button) {
		button.setBackground(new Color(105,105,105));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(true);
		
	    button.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        button.setBackground(new Color(169, 169, 169)); 
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        button.setBackground(new Color(105,105,105)); 
		    }
		});
	}
}
