package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Timer;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FuncionarioFrame extends JFrame {
    JPanel painelMaior, sidebarPanel, rightPainel;
    JLabel textoTitulo, labelNome, labelSobrenome, labelCPF, labelNuTelefone, labelDataNasc, labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;
    ;
    JButton botaoCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;
    ;
    JTextField campoTNome, campoTSobrenome, campoCPF, campoNuTelefone, campoDataNasc;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;


    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;


    public FuncionarioFrame() {

        setTitle("Cadastrar Funcionario");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        URL iconUrl = getClass().getResource("/img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        }


        painelMaior = new JPanel();
        painelMaior.setBounds(100, 42, 1300, 670);
        painelMaior.setBackground(UIvariables.WHITE_COLOR);
        painelMaior.setLayout(null);


        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);

        iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds(114, 34, 54, 54);

        iconHome = new ImageIcon(getClass().getResource("../img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40);
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
        btnHome.addActionListener(e -> {
            dispose();
            new FuncionarioFrame();
        });

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

        btnHome.addActionListener(e -> {
            dispose();
            new FuncionarioFrame().setVisible(true);
        });

        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);

        btnPacientes = new JButton("Funcionários");
        btnPacientes.setBounds(65, 248, 200, 40);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);

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

        btnPacientes.addActionListener(e -> {
            dispose();
            new CrudFuncionario().setVisible(true);
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

        btnLogOut.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
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


        btnSeta.addActionListener(e -> toggleSidebar());


        rightPainel = new JPanel();
        rightPainel.setBounds(346, 0, 1154, 800);
        rightPainel.setBackground(UIvariables.WHITE_COLOR);
        rightPainel.setLayout(null);


        textoTitulo = new JLabel("Cadastrar funcionário");
        textoTitulo.setBounds(0, 40, 400, 60);
        textoTitulo.setForeground(UIvariables.BLACK_COLOR);
        textoTitulo.setFont(UIvariables.FONT_TITLE);

        labelNome = new JLabel("Nome");
        labelNome.setBounds(0, 160, 80, 40);
        labelNome.setForeground(UIvariables.BLACK_COLOR);
        labelNome.setFont(UIvariables.FONT_INPUT);

        campoTNome = new JTextField();
        campoTNome.setBounds(0, 194, 330, 40);
        campoTNome.setFont(UIvariables.FONT_INPUT);
        campoTNome.setForeground(UIvariables.BLACK_COLOR);

        labelSobrenome = new JLabel("Sobrenome");
        labelSobrenome.setBounds(500, 160, 200, 40);
        labelSobrenome.setForeground(UIvariables.BLACK_COLOR);
        labelSobrenome.setFont(UIvariables.FONT_INPUT);

        campoTSobrenome = new JTextField();
        campoTSobrenome.setBounds(500, 194, 330, 40);
        campoTSobrenome.setFont(UIvariables.FONT_INPUT);
        campoTSobrenome.setForeground(UIvariables.BLACK_COLOR);

        labelCPF = new JLabel("CPF");
        labelCPF.setBounds(0, 300, 80, 40);
        labelCPF.setForeground(UIvariables.BLACK_COLOR);
        labelCPF.setFont(UIvariables.FONT_INPUT);

        campoCPF = new JTextField();
        campoCPF.setBounds(0, 334, 330, 40);
        campoCPF.setFont(UIvariables.FONT_INPUT);
        campoCPF.setForeground(UIvariables.BLACK_COLOR);

        labelNuTelefone = new JLabel("Número de telefone");
        labelNuTelefone.setBounds(500, 300, 330, 40);
        labelNuTelefone.setForeground(UIvariables.BLACK_COLOR);
        labelNuTelefone.setFont(UIvariables.FONT_INPUT);

        campoNuTelefone = new JTextField();
        campoNuTelefone.setBounds(500, 334, 330, 40);
        campoNuTelefone.setForeground(UIvariables.BLACK_COLOR);
        campoNuTelefone.setFont(UIvariables.FONT_INPUT);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(0, 434, 80, 40);
        labelEmail.setForeground(UIvariables.BLACK_COLOR);
        labelEmail.setFont(UIvariables.FONT_INPUT);
        rightPainel.add(labelEmail);

        JTextField campoEmail = new JTextField();
        campoEmail.setBounds(0, 468, 330, 40);
        campoEmail.setForeground(UIvariables.BLACK_COLOR);
        campoEmail.setFont(UIvariables.FONT_INPUT);
        rightPainel.add(campoEmail);

        labelDataNasc = new JLabel("Data de nascimento");
        labelDataNasc.setBounds(0, 566, 250, 40);
        labelDataNasc.setForeground(UIvariables.BLACK_COLOR);
        labelDataNasc.setFont(UIvariables.FONT_INPUT);


        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            campoDataNasc = new JFormattedTextField(dateMask);
            campoDataNasc.setColumns(10);

            campoDataNasc.setBounds(0, 600, 330, 40);
            campoDataNasc.setFont(UIvariables.FONT_INPUT);
            painelMaior.add(campoDataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        JRadioButton radioMedico = new JRadioButton("Médico");
        radioMedico.setBounds(500, 420, 200, 40);
        radioMedico.setFont(UIvariables.FONT_INPUT);
        radioMedico.setForeground(UIvariables.BLACK_COLOR);
        radioMedico.setBackground(UIvariables.WHITE_COLOR);

        final boolean[] medico = {false};
        radioMedico.addActionListener(e -> {
            if (radioMedico.isSelected()) {
                medico[0] = true;
                System.out.println("Eu sou medico");

            } else {
                medico[0] = false;
            }

        });


        JRadioButton radioEnfermeiro = new JRadioButton("Enfermeiro");
        radioEnfermeiro.setBounds(500, 460, 200, 40);
        radioEnfermeiro.setFont(UIvariables.FONT_INPUT);
        radioEnfermeiro.setForeground(UIvariables.BLACK_COLOR);
        radioEnfermeiro.setBackground(UIvariables.WHITE_COLOR);

        final boolean[] enfermeiro = {false};
        radioEnfermeiro.addActionListener(e -> {
            if (radioEnfermeiro.isSelected()) {
                enfermeiro[0] = true;
                System.out.println("Eu sou enfermeiro");
            } else {
                enfermeiro[0] = false;
            }
        });

        JRadioButton radioEnfermeiroTriagem = new JRadioButton("Enf. Triagem");
        radioEnfermeiroTriagem.setBounds(500, 500, 250, 40);
        radioEnfermeiroTriagem.setFont(UIvariables.FONT_INPUT);
        radioEnfermeiroTriagem.setForeground(UIvariables.BLACK_COLOR);
        radioEnfermeiroTriagem.setBackground(UIvariables.WHITE_COLOR);

        final boolean[] enfermeiroTriagem = {false};
        radioEnfermeiroTriagem.addActionListener(e -> {
            if (radioEnfermeiroTriagem.isSelected()) {
                enfermeiroTriagem[0] = true;
                System.out.println("Eu sou enfermeiro triagem");
            } else {
                enfermeiroTriagem[0] = false;
            }
        });

        JRadioButton radioRecepcionista = new JRadioButton("Recepcionista");
        radioRecepcionista.setBounds(500, 540, 200, 40);
        radioRecepcionista.setFont(UIvariables.FONT_INPUT);
        radioRecepcionista.setForeground(UIvariables.BLACK_COLOR);
        radioRecepcionista.setBackground(UIvariables.WHITE_COLOR);

        final boolean[] recepcionista = {false};
        radioRecepcionista.addActionListener(e -> {
            if (radioRecepcionista.isSelected()) {
                recepcionista[0] = true;
                System.out.println("Eu sou recepcionista");
            } else {
                recepcionista[0] = false;
            }
        });


        ButtonGroup grupoCheckBoxes = new ButtonGroup();
        grupoCheckBoxes.add(radioMedico);
        grupoCheckBoxes.add(radioEnfermeiro);
        grupoCheckBoxes.add(radioEnfermeiroTriagem);
        grupoCheckBoxes.add(radioRecepcionista);


        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(720, 40, 150, 60);
        botaoCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        botaoCadastrar.setForeground(UIvariables.WHITE_COLOR);
        botaoCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);

        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setContentAreaFilled(false);
        botaoCadastrar.setOpaque(true);

        botaoCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String query;
                String SUrl, SUser, Spass;
                String cpf, nomeCompleto, nome, sobrenome, numeroTelefone, email , checkboxMedico, checkboxEnfermeiro, checkboxEnfermeiroTriagem, checkboxRecepcionista;
                java.sql.Date dataNacimento = null;

                SUrl = "jdbc:mysql://localhost:3306/dbmeditrack";
                SUser = "root";
                Spass = "admin";

                if ("".equals(campoCPF.getText()) || "".equals(campoTNome.getText()) || "".equals(campoEmail.getText()) || "".equals(campoTSobrenome.getText()) || "".equals(campoNuTelefone.getText()) || campoDataNasc.getText().contains("_")) {
                    System.out.println("Erro: campos vazios ou data incompleta");
                    return;
                } else if (!(radioMedico.isSelected() ||
                        radioEnfermeiro.isSelected() ||
                        radioEnfermeiroTriagem.isSelected() ||
                        radioRecepcionista.isSelected())) {

                    JOptionPane.showMessageDialog(null, "Você precisa ao menos selecionar um funcionário");


                } else {
                    nome = campoTNome.getText();
                    sobrenome = campoTSobrenome.getText();
                    cpf = campoCPF.getText();
                    numeroTelefone = campoNuTelefone.getText();
                    nomeCompleto = nome + " " + sobrenome;
                    checkboxMedico = radioMedico.isSelected() ? "1" : "0";
                    checkboxEnfermeiro = radioEnfermeiro.isSelected() ? "1" : "0";
                    checkboxEnfermeiroTriagem = radioEnfermeiroTriagem.isSelected() ? "1" : "0";
                    checkboxRecepcionista = radioRecepcionista.isSelected() ? "1" : "0";
                    email = campoEmail.getText();


                    String dataDigitada = campoDataNasc.getText().replaceAll("[\\s_]", "");
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        java.util.Date dataUtil = formato.parse(dataDigitada);
                        dataNacimento = new java.sql.Date(dataUtil.getTime());
                    } catch (ParseException ex) {
                        System.err.println("Erro ao formatar a data: " + ex.getMessage());
                        ex.printStackTrace();
                        return;
                    }

                    query = "INSERT INTO funcionario_ (Nome, cpf, Medico, Enfermeiro, Enfermeiro_triagem, recepcionista, numero_telefone, data_nacimento, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    try (Connection con = DriverManager.getConnection(SUrl, SUser, Spass); PreparedStatement pstmt = con.prepareStatement(query)) {

                        pstmt.setString(1, nomeCompleto);
                        pstmt.setString(2, cpf);
                        pstmt.setString(3, checkboxMedico);
                        pstmt.setString(4, checkboxEnfermeiro);
                        pstmt.setString(5, checkboxEnfermeiroTriagem);
                        pstmt.setString(6, checkboxRecepcionista);
                        pstmt.setString(7, numeroTelefone);
                        pstmt.setDate(8, dataNacimento);
                        pstmt.setString(9, email);


                        int linhasAfetadas = pstmt.executeUpdate();

                        if (linhasAfetadas > 0) {
                            System.out.println("Funcionário cadastrado com sucesso!");
                            campoCPF.setText("");
                            campoTNome.setText("");
                            campoTSobrenome.setText("");
                            campoNuTelefone.setText("");
                            campoDataNasc.setText("");
                            campoEmail.setText("");
                            JOptionPane.showMessageDialog(FuncionarioFrame.this,
                                    "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            try {
                                ConviteService.enviarConvite(email, cpf);
                                System.out.println("E-mail enviado com sucesso para: " + email);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(FuncionarioFrame.this,
                                        "Funcionário foi cadastrado, mas houve erro ao enviar o e-mail.",
                                        "Erro de envio", JOptionPane.ERROR_MESSAGE);
                            }

                            campoCPF.setText("");
                            campoTNome.setText("");
                            campoTSobrenome.setText("");
                            campoNuTelefone.setText("");
                            campoDataNasc.setText("");
                            campoEmail.setText("");
                        } else {
                            System.out.println("Erro ao cadastrar o paciente.");
                        }

                    } catch (SQLException ex) {
                        System.err.println("Erro de SQL: " + ex.getMessage());
                        ex.printStackTrace();
                    }

                }
            }
        });


        add(painelMaior);
        painelMaior.add(sidebarPanel);
        painelMaior.add(rightPainel);
        rightPainel.add(textoTitulo);
        rightPainel.add(labelNome);
        rightPainel.add(campoTNome);
        rightPainel.add(labelSobrenome);
        rightPainel.add(campoTSobrenome);
        rightPainel.add(labelCPF);
        rightPainel.add(campoCPF);
        rightPainel.add(labelNuTelefone);
        rightPainel.add(campoNuTelefone);
        rightPainel.add(labelDataNasc);
        rightPainel.add(campoDataNasc);
        rightPainel.add(botaoCadastrar);
        rightPainel.add(radioMedico);
        rightPainel.add(radioEnfermeiro);
        rightPainel.add(radioEnfermeiroTriagem);
        rightPainel.add(radioRecepcionista);
        sidebarPanel.add(labeliconLogo);
        sidebarPanel.add(labeliconHome);
        sidebarPanel.add(btnHome);
        sidebarPanel.add(labeliconPacientes);
        sidebarPanel.add(btnPacientes);
        sidebarPanel.add(labeliconLogOut);
        sidebarPanel.add(btnLogOut);
        sidebarPanel.add(btnSeta);


        setVisible(true);


    }

    private void toggleSidebar() {
        int targetWidth = sidebarExpanded ? SIDEBAR_WIDTH_MINIMIZED : SIDEBAR_WIDTH_EXPANDED;
        final int[] startWidth = {sidebarPanel.getWidth()};
        final int[] startX = {rightPainel.getX()};

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

                rightPainel.setBounds(newX, rightPainel.getY(), rightPainel.getWidth(), rightPainel.getHeight());

                updateSidebarComponents(newWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                rightPainel.revalidate();
                rightPainel.repaint();
            }
        });
        animationTimer.start();
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

    public static void main(String[] args) {
        new FuncionarioFrame().setVisible(true);
    }


}