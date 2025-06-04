package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;

public class CrudTRI extends JFrame {

    JPanel sidebarPanel, contentPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelIconLine;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;
    JLabel labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;
    JLabel labelNome, labelTelefone, labelNascimento;


    private final int SIDEBAR_WIDTH_EXPANDED = 280;

    public CrudTRI() {
        setTitle("CRUD");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        // Configurações visuais globais
        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);

        // Ícone da aplicação
        URL iconUrl = getClass().getResource("/img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        }

        // Painel principal
        contentPanel = new JPanel();
        contentPanel.setBounds(380, 42, 1000, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);

        // Linha decorativa
        iconLine = new ImageIcon(getClass().getResource("/img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(0, 100, 1019, 32);
        contentPanel.add(labelIconLine);


        // Sidebar
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(100, 42, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        add(sidebarPanel);

        // Logo
        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds((SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        // Ícones e botões da sidebar
        iconHome = new ImageIcon(getClass().getResource("/img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labeliconHome);

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40);
        configurarBotaoSidebar(btnHome);
        sidebarPanel.add(btnHome);


        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Triagem");
        btnPacientes.setBounds(20, 235, 246, 65);
        configurarBotaoSidebar(btnPacientes);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Triagem Triagem = new Triagem();
                Triagem.setVisible(true);

                SwingUtilities.getWindowAncestor(btnPacientes).dispose();
            }
        });


        iconLogOut = new ImageIcon(getClass().getResource("/img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);
        sidebarPanel.add(labeliconLogOut);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(56, 455, 120, 40);
        configurarBotaoSidebar(btnLogOut);
        sidebarPanel.add(btnLogOut);

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        carregarPacientes();
    }

    private void configurarBotaoSidebar(JButton botao) {
        botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        botao.setForeground(UIvariables.WHITE_COLOR);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
                botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
            }
        });
    }

    private void carregarPacientes() {


        labelNome = new JLabel("Nome");
        labelNome.setBounds(150, 30, 400, 60);
        labelNome.setFont(UIvariables.FONT_TEXT);
        labelNome.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelNome);

        labelTelefone = new JLabel("Número de telefone");
        labelTelefone.setBounds(450, 30, 400, 60);
        labelTelefone.setFont(UIvariables.FONT_TEXT);
        labelTelefone.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelTelefone);

        labelNascimento = new JLabel("Data Nascimento");
        labelNascimento.setBounds(770, 30, 400, 60);
        labelNascimento.setFont(UIvariables.FONT_TEXT);
        labelNascimento.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelNascimento);


        // Remove os dados anteriores
        Component[] componentes = contentPanel.getComponents();
        for (Component comp : componentes) {
            if (comp instanceof JLabel &&
                    comp != labelIconLine &&
                    comp != labelNome &&
                    comp != labelTelefone &&
                    comp != labelNascimento) {
                contentPanel.remove(comp);
            }
            if (comp instanceof JButton) contentPanel.remove(comp);
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Nome, numero_telefone, cpf, data_nascimento FROM paciente_");


            int y = 140;
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String telefone = rs.getString("numero_telefone");
                String dataNascimento = rs.getString("data_nascimento");
                String cpf = rs.getString("cpf");

                JLabel lblNome = new JLabel(nome);
                lblNome.setBounds(150, y, 200, 40);
                lblNome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblNome);

                JLabel lblTelefone = new JLabel(telefone);
                lblTelefone.setBounds(450, y, 200, 40);
                lblTelefone.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblTelefone);

                JLabel lblData = new JLabel(dataNascimento);
                lblData.setBounds(770, y, 200, 40);
                lblData.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblData);


            }

            conn.close();
            contentPanel.revalidate();
            contentPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrudTRI frame = new CrudTRI();
            frame.setVisible(true);
        });
    }
}
