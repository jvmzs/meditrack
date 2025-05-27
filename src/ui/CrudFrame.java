package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class CrudFrame extends JFrame {

    JPanel sidebarPanel, contentPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    Label labelTitle, labelIconLine;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;
    JLabel labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;

    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    public CrudFrame() {
        setTitle("CRUD");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        // Using null layout for absolute positioning, but consider using Layout Managers
        // for more flexible UIs.
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);

        URL iconUrl = getClass().getResource("../img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        } else {
            System.err.println("Icone do logo não encontrado!");
        }

        // Initialize contentPanel ONCE
        contentPanel = new JPanel();
        contentPanel.setBounds(380, 42, 1100, 670); // Adjusted x-coordinate to be next to sidebar
        contentPanel.setBackground(UIvariables.WHITE_COLOR); // Or BACKGROUND_PANEL_BLUE as per your last setup
        contentPanel.setLayout(null);
        add(contentPanel); // Add contentPanel to the JFrame

        // Initialize sidebarPanel ONCE
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(100, 42, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null); // Using null layout for sidebar as well
        add(sidebarPanel); // Add sidebarPanel directly to the JFrame

        // Load and scale the logo for the sidebar
        // Make sure the path is correct: "../img/img-logo.png" implies it's one level up from 'ui' and then in 'img'
        // If it's directly in 'img' relative to the root of your source folder, then "/img/img-logo.png" is correct.
        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        if (iconLogo.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Error loading sidebar logo image: /img/img-logo.png");
        }
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        // Set bounds relative to sidebarPanel
        labeliconLogo.setBounds( (SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54); // Centered horizontally

        // Add the logo to the sidebarPanel
        sidebarPanel.add(labeliconLogo);


        // --- Home Button and Icon ---
        iconHome = new ImageIcon(getClass().getResource("../img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labeliconHome); // Add to sidebarPanel

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40); // Adjust x if needed to align with icon
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(false);
        sidebarPanel.add(btnHome); // Add to sidebarPanel

        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
            }
        });

        // --- Pacientes Button and Icon ---
        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes); // Add to sidebarPanel

        // You'll likely need a btnPacientes too, similar to btnHome, and add it to sidebarPanel

        // Remove these lines as they were causing issues
        // add(contentPanel); // Already added above
        // contentPanel.add(sidebarPanel); // sidebarPanel should be a direct child of JFrame, not contentPanel
        // sidebarPanel.add(labeliconLogo); // This is good, just make sure it's done once and correctly positioned
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrudFrame frame = new CrudFrame();
            frame.setVisible(true);
        });
    }
}