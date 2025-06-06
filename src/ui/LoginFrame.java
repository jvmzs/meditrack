package ui;

import constants.UIvariables;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    JPanel leftPanel, rightPanel;
    JLabel logoLabel, cpfLabel, passwordLabel, imgLabelPanelLeft, imgLabelContentPanelLeft, imgLabelLogoPanelIcon2, errorMenssage;
    JTextField cpfField;
    JPasswordField passwordField;
    JButton btnLogin, btnEye;
    ImageIcon logoIcon, logoPanelIcon, logoPanelIcon2, contentRightImage, eyeClose, eyeOpen;

    public LoginFrame() {
        setTitle("Login");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        URL iconUrl = getClass().getResource("/img/img-logo.png");
        if (iconUrl != null) {
            logoIcon = new ImageIcon(iconUrl);
            setIconImage(logoIcon.getImage());
        } else {
            System.err.println("Icone do logo não encontrado!");
        }

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 750, 800);
        leftPanel.setBackground(UIvariables.BACKGROUND_PANEL_BLUE);
        leftPanel.setLayout(null);

        URL logoPanelUrl = getClass().getResource("/img/img-logo.png");
        if (logoPanelUrl != null) {
            logoPanelIcon = new ImageIcon(logoPanelUrl);
            Image scaledLogo = logoPanelIcon.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH);
            imgLabelPanelLeft = new JLabel(new ImageIcon(scaledLogo));
            imgLabelPanelLeft.setBounds(60, 45, 52, 52);
            leftPanel.add(imgLabelPanelLeft);
        } else {
            System.err.println("Logo para o painel esquerdo não encontrado!");
            imgLabelPanelLeft = new JLabel("Logo não encontrado");
            imgLabelPanelLeft.setBounds(60, 45, 150, 30);
            leftPanel.add(imgLabelPanelLeft);
        }

        logoLabel = new JLabel("MediTrack");
        logoLabel.setBounds(120, 50, 150, 40);
        logoLabel.setForeground(UIvariables.WHITE_COLOR);
        logoLabel.setFont(UIvariables.FONT_LOGO);

        contentRightImage = new ImageIcon(getClass().getResource("/img/img_Panel_Right.png"));
        Image scaledcontentRightImage = contentRightImage.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        imgLabelContentPanelLeft = new JLabel(contentRightImage);
        imgLabelContentPanelLeft.setBounds(125, 190, 500, 500);

        rightPanel = new JPanel();
        rightPanel.setBounds(750, 0, 750, 800);
        rightPanel.setBackground(UIvariables.WHITE_COLOR);
        rightPanel.setLayout(null);

        logoPanelIcon2 = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scaledlogoPanelIcon2 = contentRightImage.getImage().getScaledInstance(92, 92, Image.SCALE_SMOOTH);
        imgLabelLogoPanelIcon2 = new JLabel(logoPanelIcon2);
        imgLabelLogoPanelIcon2.setBounds(329, 94, 92, 92);

        cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(135, 250, 120, 40);
        cpfLabel.setFont(UIvariables.FONT_INPUT);
        cpfLabel.setForeground(UIvariables.BLACK_COLOR);

        cpfField = new JTextField();
        cpfField.setBounds(125, 290, 500, 60);
        cpfField.setFont(UIvariables.FONT_INPUT);

        passwordLabel = new JLabel("Senha");
        passwordLabel.setBounds(135, 370, 100, 40);
        passwordLabel.setFont(UIvariables.FONT_INPUT);
        passwordLabel.setForeground(UIvariables.BLACK_COLOR);

        passwordField = new JPasswordField();
        passwordField.setBounds(125, 410, 500, 60);
        passwordField.setFont(UIvariables.FONT_INPUT);

        errorMenssage = new JLabel("Senha ou CPF estão incorretos");
        errorMenssage.setBounds(125, 490, 500, 70);
        errorMenssage.setFont(UIvariables.FONT_ERROR);
        errorMenssage.setForeground(UIvariables.ERROR_LABEL);
        errorMenssage.setOpaque(true);
        errorMenssage.setHorizontalAlignment(SwingConstants.CENTER);
        errorMenssage.setBackground(UIvariables.ERRO_BOXLABEL);
        errorMenssage.setVisible(false);
        rightPanel.add(errorMenssage);

        eyeClose = new ImageIcon(getClass().getResource("/img/img-eye-close.png"));
        eyeOpen = new ImageIcon(getClass().getResource("/img/img-eye-open.png"));

        btnEye = new JButton(eyeClose);
        btnEye.setContentAreaFilled(false);
        btnEye.setBorderPainted(false);
        btnEye.setFocusPainted(false);
        btnEye.setBounds(630, 420, 36, 36);

        final boolean[] isEyeOpen = {false};

        btnEye.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEye.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEyeOpen[0]) {
                    btnEye.setIcon(eyeClose);
                    passwordField.setEchoChar('•');

                } else {
                    btnEye.setIcon(eyeOpen);
                    passwordField.setEchoChar((char) 0);
                }
                isEyeOpen[0] = !isEyeOpen[0];
            }
        });

        btnLogin = new JButton("Login");
        btnLogin.setBounds(110, 625, 530, 80);
        btnLogin.setBackground(UIvariables.BTN_COLOR);
        btnLogin.setForeground(UIvariables.WHITE_COLOR);
        btnLogin.setFont(UIvariables.FONT_BUTTON);

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String CPF = null, Password, query, passwordDatabase = null, IdDatabase = null;
                boolean isMedico = false, isRecepcionista = false, isEnfermeiro = false, isEnfermeiroTriagem = false, isRH = false;
                int found = 0;
                String SUrl, SUser, Spass;
                SUrl = "jdbc:mysql://localhost:3306/dbmeditrack";
                SUser = "root";
                Spass = "admin";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                    if ("".equals(cpfField.getText()) || "".equals(passwordField.getText())) {
                        System.out.println("Erro: campos vazios");
                        return;
                    }

                    CPF = cpfField.getText();
                    Password = passwordField.getText();

                    query = "SELECT * FROM funcionario_ WHERE cpf = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, CPF);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        passwordDatabase = rs.getString("Senha");
                        isMedico = rs.getBoolean("Medico");
                        isRecepcionista = rs.getBoolean("Recepcionista");
                        isEnfermeiro = rs.getBoolean("Enfermeiro");
                        isEnfermeiroTriagem = rs.getBoolean("Enfermeiro_triagem");
                        isRH = rs.getBoolean("RH");

                        found = 1;
                    }

                    if (found == 1 && Password.equals(passwordDatabase)) {
                        System.out.println("Login bem-sucedido!");
                        dispose();

                        if (isMedico) {
                            System.out.println("Sou Medico");
                            new CrudMedico().setVisible(true);
                            dispose();

                        } else if (isRecepcionista) {
                            new RecepcionistaFrame().setVisible(true);
                            System.out.println("Sou recepcionista");
                            dispose();
                        } else if (isEnfermeiro) {
                            new CrudEnfermeira().setVisible(true);
                            dispose();
                            System.out.println("Sou enfermeiro");
                        } else if (isEnfermeiroTriagem) {
                            new Triagem().setVisible(true);
                            dispose();
                            System.out.println("Sou enfermeiro de triagem");
                        } else if(isRH){
                            new FuncionarioFrame().setVisible(true);
                            dispose();
                        }
                    } else {
                        System.out.println("Senha ou nome incorretos.");
                        cpfField.setBorder(BorderFactory.createLineBorder(UIvariables.ERROR_COLOR, 2));
                        passwordField.setBorder(BorderFactory.createLineBorder(UIvariables.ERROR_COLOR, 2));
                        fadeInLabel(errorMenssage);

                    }

                    cpfField.setText("");
                    passwordField.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        add(leftPanel);
        leftPanel.add(logoLabel);
        leftPanel.add(imgLabelContentPanelLeft);
        add(rightPanel);
        rightPanel.add(imgLabelLogoPanelIcon2);
        rightPanel.add(cpfField);
        rightPanel.add(cpfLabel);
        rightPanel.add(passwordField);
        rightPanel.add(btnEye);
        rightPanel.add(passwordLabel);
        rightPanel.add(btnLogin);
        setVisible(true);
    }


    private void fadeInLabel(JLabel label) {
        label.setVisible(true);
        label.setForeground(new Color(200, 0, 0, 0));

        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha >= 255) {
                    timer.stop();
                } else {
                    alpha += 15;
                    label.setForeground(new Color(200, 0, 0, alpha));
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

}