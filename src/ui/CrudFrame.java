package ui;

import constants.UIvariables;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class CrudFrame extends JFrame {

    JPanel sidebarPanel, contentPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelTitle;
    JLabel labelIconLine;
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
        //-------------------------------------------------------------------------------

        // Initialize contentPanel ONCE

        contentPanel = new JPanel();
        contentPanel.setBounds(380, 42, 1000, 670); // Adjusted x-coordinate to be next to sidebar
        contentPanel.setBackground(UIvariables.WHITE_COLOR); // Or BACKGROUND_PANEL_BLUE as per your last setup
        contentPanel.setLayout(null);
        add(contentPanel); // Add contentPanel to the JFrame

        labelTitle = new JLabel("Nome");
        labelTitle.setBounds(70, 30, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TEXT);
        contentPanel.add(labelTitle);

        labelTitle = new JLabel("Numero de telefone");
        labelTitle.setBounds(250, 30, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TEXT);
        contentPanel.add(labelTitle);

        labelTitle = new JLabel("Data Nascimento");
        labelTitle.setBounds(570, 30, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TEXT);
        contentPanel.add(labelTitle);

        labelTitle = new JLabel("Ações");
        labelTitle.setBounds(870, 30, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TEXT);
        contentPanel.add(labelTitle);

        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(-60, 100, 1019, 32);
        labeliconPacientes = new JLabel(iconLine);  //não sei pq caralhos ta "pacientes" mas ta funcionando, deixa assim!
        contentPanel.add(labelIconLine);

        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(150, 100, 1019, 32);
        labeliconPacientes = new JLabel(iconLine);  //não sei pq caralhos ta "pacientes" mas ta funcionando, deixa assim!
        contentPanel.add(labelIconLine);

        //-------------------------------------------------------------------------------
        //Conexão com o banco de dados começa agora

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Nome, numero_telefone, data_nascimento FROM paciente_");

            int y = 140;
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String telefone = rs.getString("numero_telefone");
                String dataNascimento = rs.getString("data_nascimento");

                JLabel lblNome = new JLabel(nome);
                lblNome.setBounds(125, y, 200, 40);
                lblNome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblNome);

                JLabel lblTelefone = new JLabel(telefone);
                lblTelefone.setBounds(350, y, 200, 40);
                lblTelefone.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblTelefone);

                JLabel lblData = new JLabel(dataNascimento);
                lblData.setBounds(670, y, 200, 40);
                lblData.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblData);

                JButton btnEditar = new JButton("Editar");
                btnEditar.setBounds(970, y, 100, 30);
                contentPanel.add(btnEditar);

                y += 50;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco.");
        }


        //-------------------------------------------------------------------------------

        //sidebarPanel
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(100, 42, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        add(sidebarPanel);


        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes); // Add to sidebarPanel

        btnPacientes = new JButton("Cadastro");
        btnPacientes.setBounds(23, 235, 246, 65); // Adjust x if needed to align with icon
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);
        btnPacientes.setBorderPainted(false);
        btnPacientes.setContentAreaFilled(false);
        btnPacientes.setFocusPainted(false);
        btnPacientes.setOpaque(false);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPacientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
            }
        });




        //-------------------------------------------------------------------------------


        iconLogOut = new ImageIcon(getClass().getResource("../img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);

        //-------------------------------------------------------------------------------

        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        if (iconLogo.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Error loading sidebar logo image: /img/img-logo.png");
        }
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));

        labeliconLogo.setBounds( (SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);


        sidebarPanel.add(labeliconLogo);


        // Home Button and Icon
        iconHome = new ImageIcon(getClass().getResource("../img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labeliconHome); // Add to sidebarPanel
        sidebarPanel.add(labeliconLogOut);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(56, 455, 120, 40); // Adjust x if needed to align with icon
        btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnLogOut.setForeground(UIvariables.WHITE_COLOR);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setOpaque(false);
        sidebarPanel.add(btnLogOut);

        btnLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
            }
        });

        //-------------------------------------------------------------------------------

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

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrudFrame frame = new CrudFrame();
            frame.setVisible(true);
        });
    }
}