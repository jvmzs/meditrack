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


public class Triagem extends JFrame {

    // criação das variaveis de componentes
    JPanel sidebarPanel, contentPanel, formsPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelTitle;
    JLabel labelIconLine;
    JLabel labelInputCPF;
    JTextField inputTEMP;
    JLabel labelInputNome;
    JLabel labelInputSobrenome;
    JLabel labelInputDataNasc;
    JLabel labelInputNumeroTelefone;
    JLabel labeliconLogo;
    JLabel labeliconHome;
    JLabel labeliconPacientes;
    JLabel labeliconLogOut;
    JLabel labelInputTemperatura, labelInputAltura, labelInputAlergias, labelInputSintomas, labelInputObs;
    JLabel labelInputAge;
    JTextField inputAge, inputAltura, inputAlergias;
    JLabel labelInputPeso, labelInputTitulo;
    JTextField inputPeso, inputSintomas, inputObs;
    JFormattedTextField dateField;
    JTextField inputCPF, inputNome, inputSobrenome, inputNumeroTelefone;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;

    // variaveis para a animação
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

        // criação do painel e conteudo
        contentPanel = new JPanel();
        contentPanel.setBounds(100, 42, 1300, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);
//titulo

        labelInputTitulo = new JLabel("Cadastrar Triagem");
        labelInputTitulo.setBounds(350, 40, 350, 40); // +50px
        labelInputTitulo.setForeground(UIvariables.BLACK_COLOR);
        labelInputTitulo.setFont(UIvariables.FONT_TITLE);
        contentPanel.add(labelInputTitulo);

// campo de nome
        labelInputNome = new JLabel("Nome:");
        labelInputNome.setBounds(350, 135, 200, 40); // +50px
        labelInputNome.setForeground(UIvariables.BLACK_COLOR);
        labelInputNome.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputNome);

        inputNome = new JTextField();
        inputNome.setBounds(350, 170, 350, 40); // +50px
        inputNome.setFont(UIvariables.FONT_INPUT);
        inputNome.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputNome);

//campo de peso
        labelInputPeso = new JLabel("Peso (kg)");
        labelInputPeso.setBounds(350, 340, 250, 40); // +50px
        labelInputPeso.setForeground(UIvariables.BLACK_COLOR);
        labelInputPeso.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputPeso);

        inputPeso = new JTextField();
        inputPeso.setBounds(350, 300, 120, 40); // +50px
        inputPeso.setFont(UIvariables.FONT_INPUT);
        inputPeso.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputPeso);

//campo de temperatura
        labelInputTemperatura = new JLabel("Temperatura (°C)");
        labelInputTemperatura.setBounds(350, 260, 200, 40); // +50px
        labelInputTemperatura.setForeground(UIvariables.BLACK_COLOR);
        labelInputTemperatura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputTemperatura);

        inputTEMP = new JTextField();
        inputTEMP.setBounds(350, 380, 120, 40); // +50px
        inputTEMP.setFont(UIvariables.FONT_TITLE2);
        inputTEMP.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputTEMP);

        //campo de idade

        labelInputAge = new JLabel("Idade:");
        labelInputAge.setBounds(550, 260, 200, 40); // +50px
        labelInputAge.setForeground(UIvariables.BLACK_COLOR);
        labelInputAge.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAge);

        inputAge = new JTextField();
        inputAge.setBounds(550, 300, 120, 41); // +50px
        inputAge.setFont(UIvariables.FONT_INPUT);
        inputAge.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputAge);

        //campo de altura

        labelInputAltura = new JLabel("Altura:");
        labelInputAltura.setBounds(550, 340, 200, 40);
        labelInputAltura.setForeground(UIvariables.BLACK_COLOR);
        labelInputAltura.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAltura);

        inputAltura = new JTextField();
        inputAltura.setBounds(550, 380, 120, 40);
        inputAltura.setFont(UIvariables.FONT_INPUT);
        inputAltura.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputAltura);

        //campo de alergias

        labelInputAlergias = new JLabel("Alergias");
        labelInputAlergias.setBounds(750, 135, 250, 40); // +50px
        labelInputAlergias.setForeground(UIvariables.BLACK_COLOR);
        labelInputAlergias.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputAlergias);

        inputAlergias = new JTextField();
        inputAlergias.setBounds(750, 170, 470, 100); // +50px
        inputAlergias.setFont(UIvariables.FONT_INPUT);
        inputAlergias.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputAlergias);

        //campo de sintomas

        labelInputSintomas = new JLabel("Sintomas");
        labelInputSintomas.setBounds(750, 285, 250, 40); // +50px
        labelInputSintomas.setForeground(UIvariables.BLACK_COLOR);
        labelInputSintomas.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputSintomas);

        inputSintomas = new JTextField();
        inputSintomas.setBounds(750, 320, 470, 100); // +50px
        inputSintomas.setFont(UIvariables.FONT_INPUT);
        inputSintomas.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputSintomas);
        //campo de obs

        labelInputObs = new JLabel("Obs:");
        labelInputObs.setBounds(350, 420, 250, 40); // +50px
        labelInputObs.setForeground(UIvariables.BLACK_COLOR);
        labelInputObs.setFont(UIvariables.FONT_TITLE2);
        contentPanel.add(labelInputObs);

        inputObs = new JTextField();
        inputObs.setBounds(350, 461, 700, 150); // +50px
        inputObs.setFont(UIvariables.FONT_INPUT);
        inputObs.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(inputObs);

        // criação do sidebar
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

        // logo
        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds((SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        // botões
        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Cadastro");
        btnPacientes.setBounds(23, 235, 246, 65);
        configurarBotaoSidebar(btnPacientes);
        sidebarPanel.add(btnPacientes);

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

        //remover a estilização do botão
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

        labelTitle = new JLabel("Triagem");
        labelTitle.setBounds(0, 40, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TITLE);



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




    // SideBar
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
        SwingUtilities.invokeLater(() -> {
            Triagem frame = new Triagem();
            frame.setVisible(true);
        });
    }
}
