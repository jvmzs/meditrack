<<<<<<< HEAD
package ui;

import javax.swing.text.*;
import constants.UIvariables;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Triagem extends JFrame {

    JPanel sidebarPanel, contentPanel, formsPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelTitle;
    JLabel labelIconLine;
    JTextField inputTEMP;
    JLabel labelInputCPF;
    JLabel labeliconLogo;
    JLabel labeliconHome;
    JLabel labeliconPacientes;
    JLabel labeliconLogOut;
    JLabel labelInputTemperatura, labelInputAltura, labelInputAlergias, labelInputSintomas, labelInputObs;
    JLabel labelInputAge;
    JTextField inputAge, inputAltura;
    JTextArea inputAlergias;
    JTextArea inputSintomas, inputObs;
    JLabel labelInputPeso, labelInputTitulo;
    JTextField inputPeso;
    JTextField inputCPF;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;

    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    public Triagem(){
        setTitle("Triagem");
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

        labelInputTitulo = new JLabel("Cadastrar Triagem");
        labelInputTitulo.setBounds(350, 40, 350, 40);
        labelInputTitulo.setForeground(UIvariables.BLACK_COLOR);
        labelInputTitulo.setFont(UIvariables.FONT_TITLE);
        contentPanel.add(labelInputTitulo);

        labelInputCPF = new JLabel("CPF:");
        labelInputCPF.setBounds(350, 135, 200, 40);
        labelInputCPF.setForeground(UIvariables.BLACK_COLOR);
        labelInputCPF.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputCPF);

        inputCPF = new JTextField();
        inputCPF.setBounds(350, 170, 350, 40);
        inputCPF.setFont(UIvariables.FONT_INPUT);
        inputCPF.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputCPF);


        labelInputPeso = new JLabel("Peso (kg)");
        labelInputPeso.setBounds(350, 340, 250, 40);
        labelInputPeso.setForeground(UIvariables.BLACK_COLOR);
        labelInputPeso.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputPeso);

        inputTEMP = new JTextField();
        inputTEMP.setBounds(350, 300, 120, 40);
        inputTEMP.setFont(UIvariables.FONT_INPUT);
        inputTEMP.setForeground(UIvariables.BLACK_COLOR);
        // APLICANDO O DoubleFilter para peso
        ((AbstractDocument) inputTEMP.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputTEMP);


        labelInputTemperatura = new JLabel("Temperatura (°C)");
        labelInputTemperatura.setBounds(350, 260, 200, 40);
        labelInputTemperatura.setForeground(UIvariables.BLACK_COLOR);
        labelInputTemperatura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputTemperatura);

        inputPeso = new JTextField();
        inputPeso.setBounds(350, 380, 120, 40);
        inputPeso.setFont(UIvariables.FONT_INPUT);
        inputPeso.setForeground(UIvariables.BLACK_COLOR);
        // APLICANDO O DoubleFilter para temperatura
        ((AbstractDocument) inputPeso.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputPeso);

        labelInputAge = new JLabel("Idade:");
        labelInputAge.setBounds(550, 260, 200, 40);
        labelInputAge.setForeground(UIvariables.BLACK_COLOR);
        labelInputAge.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAge);

        inputAge = new JTextField();
        inputAge.setBounds(550, 300, 120, 41);
        inputAge.setFont(UIvariables.FONT_INPUT);
        inputAge.setForeground(UIvariables.BLACK_COLOR);
        ((AbstractDocument) inputAge.getDocument()).setDocumentFilter(new NumericFilter());
        contentPanel.add(inputAge);

        labelInputAltura = new JLabel("Altura (m):");
        labelInputAltura.setBounds(550, 340, 200, 40);
        labelInputAltura.setForeground(UIvariables.BLACK_COLOR);
        labelInputAltura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAltura);

        inputAltura = new JTextField();
        inputAltura.setBounds(550, 380, 120, 40);
        inputAltura.setFont(UIvariables.FONT_INPUT);
        inputAltura.setForeground(UIvariables.BLACK_COLOR);
        ((AbstractDocument) inputAltura.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputAltura);

        labelInputAlergias = new JLabel("Alergias");
        labelInputAlergias.setBounds(750, 135, 250, 40);
        labelInputAlergias.setForeground(UIvariables.BLACK_COLOR);
        labelInputAlergias.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAlergias);

        inputAlergias = new JTextArea();
        inputAlergias.setLineWrap(true);
        inputAlergias.setWrapStyleWord(true);
        inputAlergias.setFont(UIvariables.FONT_INPUT);
        inputAlergias.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollAlergias = new JScrollPane(inputAlergias);
        scrollAlergias.setBounds(750, 170, 470, 100);
        contentPanel.add(scrollAlergias);

        labelInputSintomas = new JLabel("Sintomas");
        labelInputSintomas.setBounds(750, 285, 250, 40);
        labelInputSintomas.setForeground(UIvariables.BLACK_COLOR);
        labelInputSintomas.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputSintomas);

        inputSintomas = new JTextArea();
        inputSintomas.setLineWrap(true);
        inputSintomas.setWrapStyleWord(true);
        inputSintomas.setFont(UIvariables.FONT_INPUT);
        inputSintomas.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollSintomas = new JScrollPane(inputSintomas);
        scrollSintomas.setBounds(750, 320, 470, 100);
        contentPanel.add(scrollSintomas);

        labelInputObs = new JLabel("Obs:");
        labelInputObs.setBounds(350, 460, 250, 40);
        labelInputObs.setForeground(UIvariables.BLACK_COLOR);
        labelInputObs.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputObs);

        inputObs = new JTextArea();
        inputObs.setLineWrap(true);
        inputObs.setWrapStyleWord(true);
        inputObs.setFont(UIvariables.FONT_INPUT);
        inputObs.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollObs = new JScrollPane(inputObs);
        scrollObs.setBounds(350, 500, 700, 150);
        contentPanel.add(scrollObs);

        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        add(sidebarPanel);
        contentPanel.add(sidebarPanel);

        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        Image scalediconLine = iconLine.getImage().getScaledInstance(904, 20, Image.SCALE_SMOOTH);
        labelIconLine = new JLabel(new ImageIcon(scalediconLine));
        labelIconLine.setBounds(0, 560, 904, 4);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(1100, 550, 150, 60);
        btnCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnCadastrar.setForeground(UIvariables.WHITE_COLOR);
        btnCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);
        contentPanel.add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = inputCPF.getText();
                String peso = inputPeso.getText();
                String Temperatura_corpo = inputTEMP.getText();
                String idade = inputAge.getText();
                String altura = inputAltura.getText();
                String Alergia = inputAlergias.getText();
                String obs = inputObs.getText();
                String sintomas = inputSintomas.getText();

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");

                    // Verifica se o nome existe
                    String sqlBusca = "SELECT * FROM paciente_ WHERE cpf = ?";
                    PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
                    stmtBusca.setString(1, cpf);
                    ResultSet rs = stmtBusca.executeQuery();

                    if (rs.next()) {
                        // Nome existe, faz o UPDATE
                        String sqlUpdate = "UPDATE paciente_ SET peso = ?, Temperatura_corpo = ?, idade = ?, altura = ?, Alergia = ?, obs = ?, sintomas = ? WHERE cpf = ?";
                        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
                        stmtUpdate.setString(1, peso);
                        stmtUpdate.setString(2, Temperatura_corpo);
                        stmtUpdate.setString(3, idade);
                        stmtUpdate.setString(4, altura);
                        stmtUpdate.setString(5, Alergia);
                        stmtUpdate.setString(6, obs);
                        stmtUpdate.setString(7, sintomas);
                        stmtUpdate.setString(8, cpf);

                        stmtUpdate.executeUpdate();
                        stmtUpdate.close();
JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
                    } else {
JOptionPane.showMessageDialog(null, "CPF não encontrado.");
                    }

                    rs.close();
                    stmtBusca.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });




        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds((SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(23, 235, 246, 65);
        configurarBotaoSidebar(btnPacientes);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CrudTRI().setVisible(true);
                dispose();
            }
        });
        btnPacientes.setBorderPainted(false); // Remove a borda
        btnPacientes.setContentAreaFilled(false); // Remove o preenchimento
        btnPacientes.setFocusPainted(false); // Remove o destaque ao focar
        btnPacientes.setOpaque(false);

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

        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(50, 248, 200, 40);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Crud Crud = new Crud();
                Crud.setVisible(true);

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

        iconLogOut = new ImageIcon(getClass().getResource("../img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(45, 455, 150, 40);
        btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnLogOut.setForeground(UIvariables.WHITE_COLOR);

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

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        iconSeta = new ImageIcon(getClass().getResource("../img/assets/icon-sideBar.png"));
        btnSeta = new JButton(iconSeta);
        btnSeta.setBounds(240, 16, 32, 32);

        btnSeta.setBorderPainted(false);
        btnSeta.setContentAreaFilled(false);
        btnSeta.setFocusPainted(false);
        btnSeta.setOpaque(false);

        btnSeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });


        formsPanel = new JPanel();
        formsPanel.setBounds(346, 0, 1154, 800);
        formsPanel.setBackground(UIvariables.WHITE_COLOR);
        formsPanel.setLayout(null);

        labelTitle = new JLabel("Triagem");
        labelTitle.setBounds(0, 40, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TITLE);
    }

    private void toggleSidebar() {
        int targetWidth = sidebarExpanded ? SIDEBAR_WIDTH_MINIMIZED : SIDEBAR_WIDTH_EXPANDED;
        final int[] startWidth = {sidebarPanel.getWidth()};
        final int[] startX = {formsPanel.getX()};

        toggleSidebarElements(!sidebarExpanded);

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentWidth = sidebarPanel.getWidth();
                int step = Math.min(30, Math.abs(currentWidth - targetWidth));

                int newWidth = currentWidth + (sidebarExpanded ? -step : step);
                int newX = startX[0] + (sidebarExpanded ? -96 : 96);

                if (newWidth == targetWidth) {
                    animationTimer.stop();
                    sidebarExpanded = !sidebarExpanded;
                    newX = startX[0] + (sidebarExpanded ? +96 : -96);
                }

                sidebarPanel.setBounds(0, 0, newWidth, 670);

                formsPanel.setBounds(newX, formsPanel.getY(), formsPanel.getWidth(), formsPanel.getHeight());

                updateSidebarComponents(newWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                formsPanel.revalidate();
                formsPanel.repaint();
            }
        });
        animationTimer.start();
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

    private void updateSidebarComponents(int sidebarWidth) {
        boolean isMinimized = sidebarWidth == SIDEBAR_WIDTH_MINIMIZED;
        int logoWidth = 54;

        int iconX = isMinimized ? sidebarWidth / 2 - 16 : 58;
        int inconxLogo = isMinimized ? sidebarWidth / 2 - 16 : 114;
        int iconW = isMinimized ? sidebarWidth / 2 - 8 : logoWidth;
        int iconH = isMinimized ? 60 : 34;


        labeliconLogo.setBounds(inconxLogo, iconH, iconW, iconW);
        labeliconHome.setBounds(iconX, 170, 32, 32);
        labeliconPacientes.setBounds(iconX, 250, 32, 32);
        labeliconLogOut.setBounds(iconX, 460, 32, 32);

        int buttonWidth = isMinimized ? 0 : sidebarWidth - 100;

        btnHome.setBounds(45, 170, buttonWidth, 40);
        btnPacientes.setBounds(57, 250, buttonWidth, 40);
        btnLogOut.setBounds(35, 460, buttonWidth, 40);

        btnSeta.setBounds(sidebarWidth - 40, 16, 32, 32);
    }


    private void toggleSidebarElements(boolean visible) {
        btnHome.setText(visible ? "Home" : "");
        btnPacientes.setText(visible ? "Pacientes" : "");
        btnLogOut.setText(visible ? "Sair" : "");

        labeliconHome.setVisible(true);
        labeliconPacientes.setVisible(true);
        labeliconLogOut.setVisible(true);
    }

    public class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) return;
            if (text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public class DoubleFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;

            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

            // Permite dígitos, um único ponto ou uma única vírgula
            if (newText.matches("\\d*([.,]?\\d*)?")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) return;

            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

            // Permite dígitos, um único ponto ou uma única vírgula
            if (newText.matches("\\d*([.,]?\\d*)?")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Triagem frame = new Triagem();
            frame.setVisible(true);
        });
    }
=======
package ui;

import javax.swing.text.*;
import constants.UIvariables;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Triagem extends JFrame {

    JPanel sidebarPanel, contentPanel, formsPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelTitle;
    JLabel labelIconLine;
    JTextField inputTEMP;
    JLabel labelInputCPF;
    JLabel labeliconLogo;
    JLabel labeliconHome;
    JLabel labeliconPacientes;
    JLabel labeliconLogOut;
    JLabel labelInputTemperatura, labelInputAltura, labelInputAlergias, labelInputSintomas, labelInputObs;
    JLabel labelInputAge;
    JTextField inputAge, inputAltura;
    JTextArea inputAlergias;
    JTextArea inputSintomas, inputObs;
    JLabel labelInputPeso, labelInputTitulo;
    JTextField inputPeso;
    JTextField inputCPF;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;

    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    public Triagem(){
        setTitle("Triagem");
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

        labelInputTitulo = new JLabel("Cadastrar Triagem");
        labelInputTitulo.setBounds(350, 40, 350, 40);
        labelInputTitulo.setForeground(UIvariables.BLACK_COLOR);
        labelInputTitulo.setFont(UIvariables.FONT_TITLE);
        contentPanel.add(labelInputTitulo);

        labelInputCPF = new JLabel("CPF:");
        labelInputCPF.setBounds(350, 135, 200, 40);
        labelInputCPF.setForeground(UIvariables.BLACK_COLOR);
        labelInputCPF.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputCPF);

        inputCPF = new JTextField();
        inputCPF.setBounds(350, 170, 350, 40);
        inputCPF.setFont(UIvariables.FONT_INPUT);
        inputCPF.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputCPF);


        labelInputPeso = new JLabel("Peso (kg)");
        labelInputPeso.setBounds(350, 340, 250, 40);
        labelInputPeso.setForeground(UIvariables.BLACK_COLOR);
        labelInputPeso.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputPeso);

        inputTEMP = new JTextField();
        inputTEMP.setBounds(350, 300, 120, 40);
        inputTEMP.setFont(UIvariables.FONT_INPUT);
        inputTEMP.setForeground(UIvariables.BLACK_COLOR);
        // APLICANDO O DoubleFilter para peso
        ((AbstractDocument) inputTEMP.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputTEMP);


        labelInputTemperatura = new JLabel("Temperatura (°C)");
        labelInputTemperatura.setBounds(350, 260, 200, 40);
        labelInputTemperatura.setForeground(UIvariables.BLACK_COLOR);
        labelInputTemperatura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputTemperatura);

        inputPeso = new JTextField();
        inputPeso.setBounds(350, 380, 120, 40);
        inputPeso.setFont(UIvariables.FONT_INPUT);
        inputPeso.setForeground(UIvariables.BLACK_COLOR);
        // APLICANDO O DoubleFilter para temperatura
        ((AbstractDocument) inputPeso.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputPeso);

        labelInputAge = new JLabel("Idade:");
        labelInputAge.setBounds(550, 260, 200, 40);
        labelInputAge.setForeground(UIvariables.BLACK_COLOR);
        labelInputAge.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAge);

        inputAge = new JTextField();
        inputAge.setBounds(550, 300, 120, 41);
        inputAge.setFont(UIvariables.FONT_INPUT);
        inputAge.setForeground(UIvariables.BLACK_COLOR);
        ((AbstractDocument) inputAge.getDocument()).setDocumentFilter(new NumericFilter());
        contentPanel.add(inputAge);

        labelInputAltura = new JLabel("Altura (m):");
        labelInputAltura.setBounds(550, 340, 200, 40);
        labelInputAltura.setForeground(UIvariables.BLACK_COLOR);
        labelInputAltura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAltura);

        inputAltura = new JTextField();
        inputAltura.setBounds(550, 380, 120, 40);
        inputAltura.setFont(UIvariables.FONT_INPUT);
        inputAltura.setForeground(UIvariables.BLACK_COLOR);
        ((AbstractDocument) inputAltura.getDocument()).setDocumentFilter(new DoubleFilter());
        contentPanel.add(inputAltura);

        labelInputAlergias = new JLabel("Alergias");
        labelInputAlergias.setBounds(750, 135, 250, 40);
        labelInputAlergias.setForeground(UIvariables.BLACK_COLOR);
        labelInputAlergias.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAlergias);

        inputAlergias = new JTextArea();
        inputAlergias.setLineWrap(true);
        inputAlergias.setWrapStyleWord(true);
        inputAlergias.setFont(UIvariables.FONT_INPUT);
        inputAlergias.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollAlergias = new JScrollPane(inputAlergias);
        scrollAlergias.setBounds(750, 170, 470, 100);
        contentPanel.add(scrollAlergias);

        labelInputSintomas = new JLabel("Sintomas");
        labelInputSintomas.setBounds(750, 285, 250, 40);
        labelInputSintomas.setForeground(UIvariables.BLACK_COLOR);
        labelInputSintomas.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputSintomas);

        inputSintomas = new JTextArea();
        inputSintomas.setLineWrap(true);
        inputSintomas.setWrapStyleWord(true);
        inputSintomas.setFont(UIvariables.FONT_INPUT);
        inputSintomas.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollSintomas = new JScrollPane(inputSintomas);
        scrollSintomas.setBounds(750, 320, 470, 100);
        contentPanel.add(scrollSintomas);

        labelInputObs = new JLabel("Obs:");
        labelInputObs.setBounds(350, 460, 250, 40);
        labelInputObs.setForeground(UIvariables.BLACK_COLOR);
        labelInputObs.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputObs);

        inputObs = new JTextArea();
        inputObs.setLineWrap(true);
        inputObs.setWrapStyleWord(true);
        inputObs.setFont(UIvariables.FONT_INPUT);
        inputObs.setForeground(UIvariables.BLACK_COLOR);
        JScrollPane scrollObs = new JScrollPane(inputObs);
        scrollObs.setBounds(350, 500, 700, 150);
        contentPanel.add(scrollObs);

        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        add(sidebarPanel);
        contentPanel.add(sidebarPanel);

        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        Image scalediconLine = iconLine.getImage().getScaledInstance(904, 20, Image.SCALE_SMOOTH);
        labelIconLine = new JLabel(new ImageIcon(scalediconLine));
        labelIconLine.setBounds(0, 560, 904, 4);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(1100, 550, 150, 60);
        btnCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnCadastrar.setForeground(UIvariables.WHITE_COLOR);
        btnCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);
        contentPanel.add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = inputCPF.getText();
                String peso = inputPeso.getText();
                String Temperatura_corpo = inputTEMP.getText();
                String idade = inputAge.getText();
                String altura = inputAltura.getText();
                String Alergia = inputAlergias.getText();
                String obs = inputObs.getText();
                String sintomas = inputSintomas.getText();

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");

                    // Verifica se o nome existe
                    String sqlBusca = "SELECT * FROM paciente_ WHERE cpf = ? AND isTriagem = FALSE";
                    PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
                    stmtBusca.setString(1, cpf);
                    ResultSet rs = stmtBusca.executeQuery();

                    if (rs.next()) {
                        // Nome existe, faz o UPDATE
                        String sqlUpdate = "UPDATE paciente_ SET peso = ?, Temperatura_corpo = ?, idade = ?, altura = ?, Alergia = ?, obs = ?, sintomas = ?, isTriagem = TRUE WHERE cpf = ?";
                        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
                        stmtUpdate.setString(1, peso);
                        stmtUpdate.setString(2, Temperatura_corpo);
                        stmtUpdate.setString(3, idade);
                        stmtUpdate.setString(4, altura);
                        stmtUpdate.setString(5, Alergia);
                        stmtUpdate.setString(6, obs);
                        stmtUpdate.setString(7, sintomas);
                        stmtUpdate.setString(8, cpf);

                        stmtUpdate.executeUpdate();
                        stmtUpdate.close();
JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
                    } else {
JOptionPane.showMessageDialog(null, "CPF não encontrado.");
                    }

                    rs.close();
                    stmtBusca.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });




        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds((SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(23, 235, 246, 65);
        configurarBotaoSidebar(btnPacientes);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CrudTRI().setVisible(true);
                dispose();
            }
        });
        btnPacientes.setBorderPainted(false); // Remove a borda
        btnPacientes.setContentAreaFilled(false); // Remove o preenchimento
        btnPacientes.setFocusPainted(false); // Remove o destaque ao focar
        btnPacientes.setOpaque(false);

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
        btnHome.addActionListener(e -> {
            dispose();
            new Triagem();
        });
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

        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);

        btnPacientes = new JButton("Cadastro");
        btnPacientes.setBounds(50, 248, 200, 40);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);

        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Crud Crud = new Crud();
                Crud.setVisible(true);

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

        iconLogOut = new ImageIcon(getClass().getResource("../img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(45, 455, 150, 40);
        btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnLogOut.setForeground(UIvariables.WHITE_COLOR);

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

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        iconSeta = new ImageIcon(getClass().getResource("../img/assets/icon-sideBar.png"));
        btnSeta = new JButton(iconSeta);
        btnSeta.setBounds(240, 16, 32, 32);

        btnSeta.setBorderPainted(false);
        btnSeta.setContentAreaFilled(false);
        btnSeta.setFocusPainted(false);
        btnSeta.setOpaque(false);

        btnSeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });


        formsPanel = new JPanel();
        formsPanel.setBounds(346, 0, 1154, 800);
        formsPanel.setBackground(UIvariables.WHITE_COLOR);
        formsPanel.setLayout(null);

        labelTitle = new JLabel("Triagem");
        labelTitle.setBounds(0, 40, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TITLE);
    }

    private void toggleSidebar() {
        int targetWidth = sidebarExpanded ? SIDEBAR_WIDTH_MINIMIZED : SIDEBAR_WIDTH_EXPANDED;
        final int[] startWidth = {sidebarPanel.getWidth()};
        final int[] startX = {formsPanel.getX()};

        toggleSidebarElements(!sidebarExpanded);

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentWidth = sidebarPanel.getWidth();
                int step = Math.min(30, Math.abs(currentWidth - targetWidth));

                int newWidth = currentWidth + (sidebarExpanded ? -step : step);
                int newX = startX[0] + (sidebarExpanded ? -96 : 96);

                if (newWidth == targetWidth) {
                    animationTimer.stop();
                    sidebarExpanded = !sidebarExpanded;
                    newX = startX[0] + (sidebarExpanded ? +96 : -96);
                }

                sidebarPanel.setBounds(0, 0, newWidth, 670);

                formsPanel.setBounds(newX, formsPanel.getY(), formsPanel.getWidth(), formsPanel.getHeight());

                updateSidebarComponents(newWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                formsPanel.revalidate();
                formsPanel.repaint();
            }
        });
        animationTimer.start();
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

    private void updateSidebarComponents(int sidebarWidth) {
        boolean isMinimized = sidebarWidth == SIDEBAR_WIDTH_MINIMIZED;
        int logoWidth = 54;

        int iconX = isMinimized ? sidebarWidth / 2 - 16 : 58;
        int inconxLogo = isMinimized ? sidebarWidth / 2 - 16 : 114;
        int iconW = isMinimized ? sidebarWidth / 2 - 8 : logoWidth;
        int iconH = isMinimized ? 60 : 34;


        labeliconLogo.setBounds(inconxLogo, iconH, iconW, iconW);
        labeliconHome.setBounds(iconX, 170, 32, 32);
        labeliconPacientes.setBounds(iconX, 250, 32, 32);
        labeliconLogOut.setBounds(iconX, 460, 32, 32);

        int buttonWidth = isMinimized ? 0 : sidebarWidth - 100;

        btnHome.setBounds(45, 170, buttonWidth, 40);
        btnPacientes.setBounds(57, 250, buttonWidth, 40);
        btnLogOut.setBounds(35, 460, buttonWidth, 40);

        btnSeta.setBounds(sidebarWidth - 40, 16, 32, 32);
    }


    private void toggleSidebarElements(boolean visible) {
        btnHome.setText(visible ? "Home" : "");
        btnPacientes.setText(visible ? "Pacientes" : "");
        btnLogOut.setText(visible ? "Sair" : "");

        labeliconHome.setVisible(true);
        labeliconPacientes.setVisible(true);
        labeliconLogOut.setVisible(true);
    }

    public class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) return;
            if (text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public class DoubleFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;

            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

            // Permite dígitos, um único ponto ou uma única vírgula
            if (newText.matches("\\d*([.,]?\\d*)?")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) return;

            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

            // Permite dígitos, um único ponto ou uma única vírgula
            if (newText.matches("\\d*([.,]?\\d*)?")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Triagem frame = new Triagem();
            frame.setVisible(true);
        });
    }
>>>>>>> 5960a5e (primeiro commit)
}