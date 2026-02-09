import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class Ekran extends JFrame {
	private static final long serialVersionUID = 1L;

    private JPanel contentPane; 
    private JPanel dynamicPanel; 
    
    public Ekran(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 150, 800, 600);
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

       
        JPanel menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new GridLayout(0, 1)); 
        menuBarPanel.setPreferredSize(new Dimension(160, 0));
        contentPane.add(menuBarPanel, BorderLayout.WEST);

       
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BorderLayout());
        contentPane.add(dynamicPanel, BorderLayout.CENTER);

      
        addMenuButtons(menuBarPanel);

       
        initializeDynamicPanel();
    }

   
    protected abstract void addMenuButtons(JPanel menuBarPanel);

   
    protected void setDynamicPanelContent(Component component) {
        dynamicPanel.removeAll();
        dynamicPanel.add(component, BorderLayout.CENTER);
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    
    protected void initializeDynamicPanel() {
        JLabel defaultLabel = new JLabel("Lütfen bir işlem seçin.", SwingConstants.CENTER);
        defaultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        setDynamicPanelContent(new JPanel().add(defaultLabel));
    }
    
    
    protected JButton createStyledButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);

        
        Color defaultBackground = new Color(70, 130, 180); 
        Color hoverBackground = new Color(100, 149, 237); 
        Color pressedBackground = new Color(30, 90, 150); 

        button.setPreferredSize(new Dimension(180, 40));
        button.setBackground(defaultBackground);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

     
        button.addActionListener(action);

      
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultBackground);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.contains(e.getPoint())) {
                    button.setBackground(hoverBackground);
                } else {
                    button.setBackground(defaultBackground);
                }
            }
        });

        return button;
    }

}
