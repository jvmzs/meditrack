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
import java.text.ParseException;

public class FuncionarioFrame extends JFrame {
    //declarando as variaveis
    JPanel  painelMaior, sidebarPanel, rightPainel ;
    JLabel textoTitulo, labelNome, labelSobrenome, labelCPF,  labelNuTelefone, labelDataNasc, labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;;
    JButton botaoCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;;
    JTextField campoTNome, campoTSobrenome, campoCPF, campoNuTelefone, campoDataNasc;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;


    // variaveis para a animação
    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;


    //criação metódo construtor
    public FuncionarioFrame() {

        setTitle("Cadastrar Funcionario");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);



        //criação do painel maior que vai conter os outros dois paineis e labels
        painelMaior = new JPanel();
        painelMaior.setBounds(100, 42, 1300, 670);
        painelMaior.setBackground(UIvariables.WHITE_COLOR);
        painelMaior.setLayout(null);


        //criação do sidepainel no caso o painel esquerdo
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

        btnPacientes = new JButton("Funcionarios");
        btnPacientes.setBounds(65, 250, 200, 40);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);

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


        //criação painel dos campos de texto vulgo "lado direito"
        rightPainel = new JPanel();
        rightPainel.setBounds(346, 0, 1154, 800);
        rightPainel.setBackground(UIvariables.WHITE_COLOR);
        rightPainel.setLayout(null);



        //criação texto superior no caso o label superior com "cadastrar paciente"
        textoTitulo = new JLabel("Cadastrar funcionário");
        textoTitulo.setBounds(0, 40, 400, 60);
        textoTitulo.setForeground(UIvariables.BLACK_COLOR);
        textoTitulo.setFont(UIvariables.FONT_TITLE);

        //criação label nome
        labelNome = new JLabel("Nome");
        labelNome.setBounds(0, 160, 80, 40);
        labelNome.setForeground(UIvariables.BLACK_COLOR);
        labelNome.setFont(UIvariables.FONT_INPUT);

        //campo de texto do nome
        campoTNome= new JTextField();
        campoTNome.setBounds(0, 194, 330, 40);
        campoTNome.setFont(UIvariables.FONT_INPUT);
        campoTNome.setForeground(UIvariables.BLACK_COLOR);

        //criação label sobrenome
        labelSobrenome = new JLabel("Sobrenome");
        labelSobrenome.setBounds(500, 160, 200, 40);
        labelSobrenome.setForeground(UIvariables.BLACK_COLOR);
        labelSobrenome.setFont(UIvariables.FONT_INPUT);

        //campo de texto do sobrenome
        campoTSobrenome= new JTextField();
        campoTSobrenome.setBounds(500, 194, 330, 40);
        campoTSobrenome.setFont(UIvariables.FONT_INPUT);
        campoTSobrenome.setForeground(UIvariables.BLACK_COLOR);

        //criação label CPF
        labelCPF = new JLabel("CPF");
        labelCPF.setBounds(0, 300, 80, 40);
        labelCPF.setForeground(UIvariables.BLACK_COLOR);
        labelCPF.setFont(UIvariables.FONT_INPUT);

        //campo texto Cpf
        campoCPF = new JTextField();
        campoCPF.setBounds(0, 334, 330, 40);
        campoCPF.setFont(UIvariables.FONT_INPUT);
        campoCPF.setForeground(UIvariables.BLACK_COLOR);

        //criação label numero de telefone
        labelNuTelefone = new JLabel("Número de telefone");
        labelNuTelefone .setBounds(500, 300, 330, 40);
        labelNuTelefone .setForeground(UIvariables.BLACK_COLOR);
        labelNuTelefone .setFont(UIvariables.FONT_INPUT);

        //campo texto número de telefone
        campoNuTelefone = new JTextField();
        campoNuTelefone.setBounds(500, 334, 330, 40);
        campoNuTelefone.setForeground(UIvariables.BLACK_COLOR);
        campoNuTelefone.setFont(UIvariables.FONT_INPUT);

        //criação label data nascimento
        labelDataNasc = new JLabel("Data de nascimento");
        labelDataNasc.setBounds(0, 444, 250, 40);
        labelDataNasc.setForeground(UIvariables.BLACK_COLOR);
        labelDataNasc.setFont(UIvariables.FONT_INPUT);

        //campo texto data de nascimento
        campoDataNasc = new JTextField();
        campoDataNasc.setBounds(0, 478, 330, 40);
        campoDataNasc.setForeground(UIvariables.BLACK_COLOR);
        campoDataNasc.setFont(UIvariables.FONT_INPUT);


        JCheckBox checkMedico = new JCheckBox("Médico");
        checkMedico.setBounds(500, 420, 200, 40);
        checkMedico.setFont(UIvariables.FONT_INPUT);
        checkMedico.setForeground(UIvariables.BLACK_COLOR);
        checkMedico.setBackground(UIvariables.WHITE_COLOR);

        JCheckBox checkEnfermeiro = new JCheckBox("Enfermeiro");
        checkEnfermeiro.setBounds(500, 460, 200, 40);
        checkEnfermeiro.setFont(UIvariables.FONT_INPUT);
        checkEnfermeiro.setForeground(UIvariables.BLACK_COLOR);
        checkEnfermeiro.setBackground(UIvariables.WHITE_COLOR);

        JCheckBox checkEnfermeiroTriagem = new JCheckBox("Enf. Triagem");
        checkEnfermeiroTriagem.setBounds(500, 500, 250, 40);
        checkEnfermeiroTriagem.setFont(UIvariables.FONT_INPUT);
        checkEnfermeiroTriagem.setForeground(UIvariables.BLACK_COLOR);
        checkEnfermeiroTriagem.setBackground(UIvariables.WHITE_COLOR);

        JCheckBox checkRecepcionista = new JCheckBox("Recepcionista");
        checkRecepcionista.setBounds(500, 540, 200, 40);
        checkRecepcionista.setFont(UIvariables.FONT_INPUT);
        checkRecepcionista.setForeground(UIvariables.BLACK_COLOR);
        checkRecepcionista.setBackground(UIvariables.WHITE_COLOR);



        // criando o ButtonGroup para permitir apenas um checkbox selecionado por vez
        ButtonGroup grupoCheckBoxes = new ButtonGroup();
        grupoCheckBoxes.add(checkMedico);
        grupoCheckBoxes.add(checkEnfermeiro);
        grupoCheckBoxes.add(checkEnfermeiroTriagem);
        grupoCheckBoxes.add(checkRecepcionista);







        //criando botão cadastrar
        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(760, 40, 150, 60);
        botaoCadastrar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        botaoCadastrar.setForeground(UIvariables.WHITE_COLOR);
        botaoCadastrar.setBackground(UIvariables.COLOR_SIDEBAR);

        // Cor de fundo
        botaoCadastrar.setFocusPainted(false); // Remove o destaque ao focar
        botaoCadastrar.setContentAreaFilled(false); // Remove o preenchimento padrão
        botaoCadastrar.setOpaque(true);

        botaoCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        rightPainel.add( campoNuTelefone);
        rightPainel.add(labelDataNasc);
        rightPainel.add(campoDataNasc);
        rightPainel.add(botaoCadastrar);
        rightPainel.add(checkMedico);
        rightPainel.add(checkEnfermeiro);
        rightPainel.add(checkEnfermeiroTriagem);
        rightPainel.add(checkRecepcionista);
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
        final int[] startX = {rightPainel.getX()}; // Posição inicial do formsPanel

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
                rightPainel.setBounds(newX, rightPainel.getY(), rightPainel.getWidth(), rightPainel.getHeight());

                // Reposiciona os ícones e botões com base na nova largura
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
        btnPacientes.setText(visible ? "Funcionarios" : "");
        btnLogOut.setText(visible ? "Sair" : "");

        // Os ícones permanecem visíveis
        labeliconHome.setVisible(true);
        labeliconPacientes.setVisible(true);
        labeliconLogOut.setVisible(true);
    }

    public static void main(String[]args){
        new FuncionarioFrame();
    }


}