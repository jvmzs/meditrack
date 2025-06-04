package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Medico extends JFrame {

    JPanel sidebarPanel, contentPanel, ENFPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JTextField inputPres;
    JLabel labeliconLogo;
    JLabel labeliconHome;
    JLabel labeliconPacientes;
    JLabel labeliconLogOut;
    JLabel labelInputOBS, labelInpuFreq, labelIconLine;
    JTextField inputFreq, inputAlergias;
    JLabel labelInputPeso, labelInputTitulo, labelInputPres, labelInputResult;
    JTextField inputPeso, inputResult;
    JButton btnCadastrar, btnHome, btnPacientes, btnLogOut, btnCalcular;
    JTextArea inputObs;

    public Medico(){

        setTitle("Médico");
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

        contentPanel = new JPanel();
        contentPanel.setBounds(100, 42, 1300, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);

        // Linha decorativa
        iconLine = new ImageIcon(getClass().getResource("/img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(270, 100, 1019, 32);
        contentPanel.add(labelIconLine);

        iconLine = new ImageIcon(getClass().getResource("/img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(270, 530, 1019, 32);
        contentPanel.add(labelIconLine);

        ENFPanel = new RoundedPanel(30);
        ENFPanel.setBounds(361,200, 330, 315);
        ENFPanel.setBackground(UIvariables.COLOR_ENFPanel);
        ENFPanel.setLayout(null);
        contentPanel.add(ENFPanel);

        labelInputPeso = new JLabel("Peso:");
        labelInputPeso.setBounds(43, 30, 80, 40);
        labelInputPeso.setForeground(UIvariables.BLACK_COLOR);
        labelInputPeso.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputPeso);

        inputPeso = new JTextField();
        inputPeso.setBounds(150, 30, 150, 40);
        inputPeso.setFont(UIvariables.FONT_15);
        inputPeso.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputPeso);

        labelInputPres = new JLabel("Prescrição:");
        labelInputPres.setBounds(43, 95, 110, 40);
        labelInputPres.setForeground(UIvariables.BLACK_COLOR);
        labelInputPres.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputPres);

        labelInputPres = new JLabel("(mg/ml):");
        labelInputPres.setBounds(43, 110, 110, 40);
        labelInputPres.setForeground(UIvariables.BLACK_COLOR);
        labelInputPres.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputPres);

        inputPres = new JTextField();
        inputPres.setBounds(150, 100, 150, 40);
        inputPres.setFont(UIvariables.FONT_15);
        inputPres.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputPres);

        labelInputResult = new JLabel("Dose:");
        labelInputResult.setBounds(43, 170, 110, 40);
        labelInputResult.setForeground(UIvariables.BLACK_COLOR);
        labelInputResult.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputResult);

        inputResult = new JTextField();
        inputResult.setBounds(150, 170, 150, 40);
        inputResult.setFont(UIvariables.FONT_15);
        inputResult.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputResult);

        labelInpuFreq = new JLabel("Frequencia:");
        labelInpuFreq.setBounds(43, 235, 110, 40);
        labelInpuFreq.setForeground(UIvariables.BLACK_COLOR);
        labelInpuFreq.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInpuFreq);

        inputFreq = new JTextField();
        inputFreq.setBounds(150, 235, 150, 40);
        inputFreq.setFont(UIvariables.FONT_15);
        inputFreq.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputFreq);


        labelInputOBS = new JLabel("Hipotese Diagnostica:");
        labelInputOBS.setBounds(770, 260, 380, 40);
        labelInputOBS.setForeground(UIvariables.BLACK_COLOR);
        labelInputOBS.setFont(UIvariables.FONT_INPUT);
        contentPanel.add(labelInputOBS);

        inputObs = new JTextArea();
        inputObs.setLineWrap(true);
        inputObs.setWrapStyleWord(true);
        inputObs.setFont(UIvariables.FONT_INPUT);
        inputObs.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollObs = new JScrollPane(inputObs);
        scrollObs.setBounds(770, 305, 400, 150);
        contentPanel.add(scrollObs);

        btnCadastrar = new JButton("Finalizar");
        btnCadastrar.setBounds(1100, 580, 150, 60);
        btnCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnCadastrar.setForeground(UIvariables.WHITE_COLOR);
        btnCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setOpaque(true);
        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        contentPanel.add(btnCadastrar);

        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        contentPanel.add(sidebarPanel);

        iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds(114, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        iconHome = new ImageIcon(getClass().getResource("/img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labeliconHome);

        btnHome = new JButton("Home");
        btnHome.setBounds(32, 168, 200, 40);
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(false);
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
        sidebarPanel.add(btnHome);

        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Cadastro");
        btnPacientes.setBounds(23, 235, 246, 65);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FuncionarioFrame FuncionarioFrame = new FuncionarioFrame();
                FuncionarioFrame.setVisible(true);
                SwingUtilities.getWindowAncestor(btnPacientes).dispose();
            }
        });

        btnPacientes.setBorderPainted(false);
        btnPacientes.setContentAreaFilled(false);
        btnPacientes.setFocusPainted(false);
        btnPacientes.setOpaque(false);
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

        iconLogOut = new ImageIcon(getClass().getResource("/img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);
        sidebarPanel.add(labeliconLogOut);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(56, 455, 120, 40);
        btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setOpaque(false);
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
        sidebarPanel.add(btnLogOut);

        labelInputTitulo = new JLabel("Médico");
        labelInputTitulo.setBounds(350, 40, 350, 40);
        labelInputTitulo.setForeground(UIvariables.BLACK_COLOR);
        labelInputTitulo.setFont(UIvariables.FONT_TITLE);
        contentPanel.add(labelInputTitulo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Medico frame = new Medico();
            frame.setVisible(true);
        });
    }
}
