//chiara
package it.unibo.model;

import javax.swing.*;
import it.unibo.model.interfaces.MenuInterface;
import java.awt.*;

public class Menu extends JPanel implements MenuInterface {
    private final JButton startButton = new JButton("Inizia Gioco");
    private final JButton controlsButton = new JButton("Comandi");
    private final JComboBox<String> levelsDropdown;
    private Image backgroundImage; // Immagine di sfondo

    public Menu(String[] levels) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(28, 28, 28));

        // Caricamento dell'immagine di sfondo
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/puyomenu.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'immagine");
            e.printStackTrace();
        }
        
        // Pannello centrale con layout verticale
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Titolo
        JLabel titleLabel = new JLabel("PUYO POP");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 80));
        titleLabel.setForeground(new Color(255, 60, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dropdown dei livelli
        levelsDropdown = new JComboBox<>(levels);
        levelsDropdown.setFont(new Font("Roboto", Font.PLAIN, 20));
        levelsDropdown.setMaximumSize(new Dimension(250, 40));
        levelsDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelsDropdown.setBackground(new Color(255, 255, 255));
        levelsDropdown.setForeground(new Color(50, 50, 50));

        JLabel levelLabel = new JLabel("Seleziona Livello:");
        levelLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        styleButton(startButton, new Color(60, 180, 100));
        styleButton(controlsButton, new Color(50, 130, 255));

        // Componenti nel pannello centrale
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(levelLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(levelsDropdown);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(startButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(controlsButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Roboto", Font.BOLD, 24));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(300, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setMargin(new Insets(20, 40, 20, 40));

        // Effetto hover per i pulsanti
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        button.setContentAreaFilled(false); // Rendi trasparente
    }

    // Override del metodo paintComponent per disegnare l'immagine di sfondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            drawBackground(g, getWidth(), getHeight());
        }
    }

    // Metodo per disegnare lo sfondo
    private void drawBackground(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, this);
    }

    @Override
    public JButton getStartButton() {
        return startButton;
    }

    @Override
    public JButton getControlsButton() {
        return controlsButton;
    }

    @Override
    public String getSelectedLevel() {
        return (String) levelsDropdown.getSelectedItem();
    }
}
