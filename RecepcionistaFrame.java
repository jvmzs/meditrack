package ui;

import constants.UIvariables;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.MaskFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.ClassNotFoundException;
import java.sql.*;


public class RecepcionistaFrame extends JFrame {

    // criação das variaveis de componentes
    JPanel sidebarPanel, contentPanel, formsPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelTitle, labelIconLine, labelInputCPF, labelInputNome, labelInputSobrenome, labelInputDataNasc, labelInputNumeroTelefone, labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;
    JFormattedTextField dateField;
    JTextField inputCPF, inputNome, inputSobrenome, inputNumeroTelefone;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;

    // variaveis para a animação
    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    public RecepcionistaFrame() {
        setTitle("Cadastrar Paciente");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE); // necessário para o fundo
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);


        // definir o icon do logo
        URL iconUrl = getClass().getResource("../img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        } else {
            System.err.println("Icone do logo não encontrado!");
        }

        // criação do painel de conteudo
        contentPanel = new JPanel();
        contentPanel.setBounds(100, 42, 1300, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        //


        // criação do sidebar
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);

        // logo
        iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds(114, 34, 54, 54);

        // icones + botoes
        iconHome = new ImageIcon(getClass().getResource("../img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40);
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
<<<<<<< HEAD
=======
        btnHome.addActionListener(e -> {
            dispose();
            new RecepcionistaFrame();
        });


>>>>>>> 5960a5e (primeiro commit)

        //remover a estilização do botão
        btnHome.setBorderPainted(false); // Remove a borda
        btnHome.setContentAreaFilled(false); // Remove o preenchimento
        btnHome.setFocusPainted(false); // Remove o destaque ao focar
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

        //remover a estilização do botão
        btnPacientes.setBorderPainted(false); // Remove a borda
        btnPacientes.setContentAreaFilled(false); // Remove o preenchimento
        btnPacientes.setFocusPainted(false); // Remove o destaque ao focar
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

        //remover a estilização do botão
        btnLogOut.setBorderPainted(false); // Remove a borda
        btnLogOut.setContentAreaFilled(false); // Remove o preenchimento
        btnLogOut.setFocusPainted(false); // Remove o destaque ao focar
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

        //remover a estilização do botão
        btnSeta.setBorderPainted(false); // Remove a borda
        btnSeta.setContentAreaFilled(false); // Remove o preenchimento
        btnSeta.setFocusPainted(false); // Remove o destaque ao focar
        btnSeta.setOpaque(false);

        btnSeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });


        btnSeta.addActionListener(e -> toggleSidebar());

        formsPanel = new JPanel();
        formsPanel.setBounds(346, 0, 1154, 800);
        formsPanel.setBackground(UIvariables.WHITE_COLOR);
        formsPanel.setLayout(null);

        labelTitle = new JLabel("Cadastrar Paciente");
        labelTitle.setBounds(0, 40, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TITLE);


        labelInputCPF = new JLabel("CPF");
        labelInputCPF.setBounds(0, 160, 80, 40);
        labelInputCPF.setForeground(UIvariables.BLACK_COLOR);
        labelInputCPF.setFont(UIvariables.FONT_INPUT);

        inputCPF = new JTextField();
        inputCPF.setBounds(0, 194, 330, 40);
        inputCPF.setFont(UIvariables.FONT_INPUT);
        inputCPF.setForeground(UIvariables.BLACK_COLOR);

        labelInputNome = new JLabel("Nome");
        labelInputNome.setBounds(0, 300, 80, 40);
        labelInputNome.setForeground(UIvariables.BLACK_COLOR);
        labelInputNome.setFont(UIvariables.FONT_INPUT);

        inputNome = new JTextField();
        inputNome.setBounds(0, 334, 330, 40);
        inputNome.setFont(UIvariables.FONT_INPUT);
        inputNome.setForeground(UIvariables.BLACK_COLOR);

        labelInputSobrenome = new JLabel("Sobrenome");
        labelInputSobrenome.setBounds(500, 300, 330, 40);
        labelInputSobrenome.setForeground(UIvariables.BLACK_COLOR);
        labelInputSobrenome.setFont(UIvariables.FONT_INPUT);

        inputSobrenome = new JTextField();
        inputSobrenome.setBounds(500, 334, 330, 40);
        inputSobrenome.setForeground(UIvariables.BLACK_COLOR);
        inputSobrenome.setFont(UIvariables.FONT_INPUT);

        labelInputNumeroTelefone = new JLabel("Número de telefone");
        labelInputNumeroTelefone.setBounds(0, 444, 250, 40);
        labelInputNumeroTelefone.setForeground(UIvariables.BLACK_COLOR);
        labelInputNumeroTelefone.setFont(UIvariables.FONT_INPUT);

        inputNumeroTelefone = new JTextField();
        inputNumeroTelefone.setBounds(0, 478, 330, 40);
        inputNumeroTelefone.setForeground(UIvariables.BLACK_COLOR);
        inputNumeroTelefone.setFont(UIvariables.FONT_INPUT);

        labelInputDataNasc = new JLabel("Data de nascimento");
        labelInputDataNasc.setBounds(500, 444, 250, 40);
        labelInputDataNasc.setForeground(UIvariables.BLACK_COLOR);
        labelInputDataNasc.setFont(UIvariables.FONT_INPUT);

        try {
            MaskFormatter dateMask = new MaskFormatter("   ##/##/####"); // DD/MM/YYYY
            dateMask.setPlaceholderCharacter('_');
            dateField = new JFormattedTextField(dateMask);
            dateField.setColumns(10);

            dateField.setBounds(500, 478, 330, 50);
            dateField.setFont(UIvariables.FONT_INPUT);
            formsPanel.add(dateField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //criação da linha e do botao

        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        Image scalediconLine = iconLine.getImage().getScaledInstance(904, 20, Image.SCALE_SMOOTH);
        labelIconLine = new JLabel(new ImageIcon(scalediconLine));
        labelIconLine.setBounds(0, 560, 904, 4);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(760, 600, 150, 60);
        btnCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnCadastrar.setForeground(UIvariables.WHITE_COLOR);
        btnCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);

        // Cor de fundo
        btnCadastrar.setFocusPainted(false); // Remove o destaque ao focar
        btnCadastrar.setContentAreaFilled(false); // Remove o preenchimento padrão
        btnCadastrar.setOpaque(true);

        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query;
                String SUrl, SUser, Spass;
                String cpf, nomeCompleto, nome, sobrenome, numeroTelefone;
                java.sql.Date dataNascimento = null;

                SUrl = "jdbc:mysql://localhost:3306/dbmeditrack";
                SUser = "root";
                Spass = "admin";

                if ("".equals(inputCPF.getText()) || "".equals(inputNome.getText()) || "".equals(inputSobrenome.getText()) || "".equals(inputNumeroTelefone.getText()) || dateField.getText().contains("_")) {
                    System.out.println("Erro: campos vazios ou data incompleta");
                    return;
                } else {
                    nome = inputNome.getText();
                    sobrenome = inputSobrenome.getText();
                    cpf = inputCPF.getText();
                    numeroTelefone = inputNumeroTelefone.getText();
                    nomeCompleto = nome + " " + sobrenome;

                    String dataDigitada = dateField.getText().replaceAll("[\\s_]", "");
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        java.util.Date dataUtil = formato.parse(dataDigitada);
                        dataNascimento = new java.sql.Date(dataUtil.getTime());
                    } catch (ParseException ex) {
                        System.err.println("Erro ao formatar a data: " + ex.getMessage());
                        ex.printStackTrace();
                        return; // Interrompe a ação se a data for inválida
                    }

                    query = "INSERT INTO paciente_ (Nome, cpf, numero_telefone, data_nascimento) VALUES (?, ?, ?, ?)";

                    try (Connection con = DriverManager.getConnection(SUrl, SUser, Spass); PreparedStatement pstmt = con.prepareStatement(query)) {

                        pstmt.setString(1, nomeCompleto);
                        pstmt.setString(2, cpf);
                        pstmt.setString(3, numeroTelefone);
                        pstmt.setDate(4, dataNascimento);

                        int linhasAfetadas = pstmt.executeUpdate();

                        if (linhasAfetadas > 0) {
<<<<<<< HEAD
                            System.out.println("Paciente cadastrado com sucesso!");
=======
>>>>>>> 5960a5e (primeiro commit)
                            JOptionPane.showMessageDialog(RecepcionistaFrame.this, // Use 'this' se estiver dentro da classe RecepcionistaFrame
                                    "Paciente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);


                            inputCPF.setText("");
                            inputNome.setText("");
                            inputSobrenome.setText("");
                            inputNumeroTelefone.setText("");
                            dateField.setText("");
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


        add(contentPanel);
        contentPanel.add(sidebarPanel);
        contentPanel.add(formsPanel);
        formsPanel.add(labelTitle);
        formsPanel.add(labelInputCPF);
        formsPanel.add(inputCPF);
        formsPanel.add(labelInputNome);
        formsPanel.add(inputNome);
        formsPanel.add(labelInputNumeroTelefone);
        formsPanel.add(inputNumeroTelefone);
        formsPanel.add(labelInputSobrenome);
        formsPanel.add(inputSobrenome);
        formsPanel.add(labelInputDataNasc);
        formsPanel.add(labelIconLine);
        formsPanel.add(btnCadastrar);
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
        final int[] startX = {formsPanel.getX()}; // Posição inicial do formsPanel

        // Controlar visibilidade dos textos dos botões
        toggleSidebarElements(!sidebarExpanded);

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calcula o próximo passo dinamicamente
                int currentWidth = sidebarPanel.getWidth();
                int step = Math.min(30, Math.abs(currentWidth - targetWidth)); // Evita ultrapassar o targetWidth

                // Ajusta a largura com base no estado da sidebar
                int newWidth = currentWidth + (sidebarExpanded ? -step : step);
                int newX = startX[0] + (sidebarExpanded ? -96 : 96); // Move o formsPanel gradualmente

                if (newWidth == targetWidth) {
                    // Finaliza a animação quando atingir o tamanho esperado
                    animationTimer.stop();
                    sidebarExpanded = !sidebarExpanded;
                    newX = startX[0] + (sidebarExpanded ? +96 : -96); // Ajusta a posição final do formsPanel
                }

                // Atualiza o tamanho da sidebar
                sidebarPanel.setBounds(0, 0, newWidth, 670);

                // Atualiza a posição do formsPanel
                formsPanel.setBounds(newX, formsPanel.getY(), formsPanel.getWidth(), formsPanel.getHeight());

                // Reposiciona os ícones e botões com base na nova largura
                updateSidebarComponents(newWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                formsPanel.revalidate();
                formsPanel.repaint();
            }
        });
        animationTimer.start();
    }

    private void updateSidebarComponents(int sidebarWidth) {
        // Verifica se a sidebar está minimizada ou expandida
        boolean isMinimized = sidebarWidth == SIDEBAR_WIDTH_MINIMIZED;
        int logoWidth = 54;

        // Centraliza os ícones horizontalmente na sidebar minimizada
        int iconX = isMinimized ? sidebarWidth / 2 - 16 : 58; // Ícones têm largura de 32px
        int inconxLogo = isMinimized ? sidebarWidth / 2 - 16 : 114; //ajusta a magem para o original
        int iconW = isMinimized ? sidebarWidth / 2 - 8 : logoWidth;
        int iconH = isMinimized ? 60 : 34;


        // Atualiza as posições dos ícones
        labeliconLogo.setBounds(inconxLogo, iconH, iconW, iconW);
        labeliconHome.setBounds(iconX, 170, 32, 32);
        labeliconPacientes.setBounds(iconX, 250, 32, 32);
        labeliconLogOut.setBounds(iconX, 460, 32, 32);

        // Ajusta os botões para acompanhar os ícones

        int buttonWidth = isMinimized ? 0 : sidebarWidth - 100; // Largura do botão no estado expandido

        btnHome.setBounds(45, 170, buttonWidth, 40);
        btnPacientes.setBounds(57, 250, buttonWidth, 40);
        btnLogOut.setBounds(35, 460, buttonWidth, 40);

        // Reposiciona o botão da seta
        btnSeta.setBounds(sidebarWidth - 40, 16, 32, 32);
    }


    private void toggleSidebarElements(boolean visible) {
        // Ajusta a visibilidade dos textos dos botões, mas mantém os ícones visíveis
        btnHome.setText(visible ? "Home" : "");
        btnPacientes.setText(visible ? "Pacientes" : "");
        btnLogOut.setText(visible ? "Sair" : "");

        // Os ícones permanecem visíveis
        labeliconHome.setVisible(true);
        labeliconPacientes.setVisible(true);
        labeliconLogOut.setVisible(true);
    }

    public static void main(String[] args) {
        new RecepcionistaFrame();
    }


}
