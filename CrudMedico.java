// CrudMedico.java
package ui;

import constants.UIvariables;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.net.URL;

public class CrudMedico extends JFrame {

    JPanel sidebarPanel, contentPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconLine;
    JLabel labelIconLine;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;
    JLabel labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;

    private final int SIDEBAR_WIDTH_EXPANDED = 280;

    public CrudMedico() {
        setTitle("CRUD"); // Define o título da janela.
        setSize(1500, 800); // Define o tamanho da janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Configura a operação padrão ao fechar.
        setResizable(false); // Impede redimensionamento.
        setLocationRelativeTo(null); // Centraliza a janela.
        setLayout(null); // Usa layout nulo.
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME); // Define a cor de fundo.

        // Configurações visuais globais.
        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);

        // Ícone da aplicação.
        URL iconUrl = getClass().getResource("/img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        }

        // Painel principal de conteúdo.
        contentPanel = new JPanel();
        contentPanel.setBounds(380, 42, 1000, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);

        // Cabeçalhos da tabela de pacientes.
        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(70, 30, 400, 60);
        labelNome.setFont(UIvariables.FONT_TEXT);
        labelNome.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelNome);

        JLabel labelTelefone = new JLabel("Número de telefone");
        labelTelefone.setBounds(250, 30, 400, 60);
        labelTelefone.setFont(UIvariables.FONT_TEXT);
        labelTelefone.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelTelefone);

        JLabel labelNascimento = new JLabel("Data Nascimento");
        labelNascimento.setBounds(570, 30, 400, 60);
        labelNascimento.setFont(UIvariables.FONT_TEXT);
        labelNascimento.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelNascimento);

        JLabel labelAcoes = new JLabel("Ações");
        labelAcoes.setBounds(870, 30, 400, 60);
        labelAcoes.setFont(UIvariables.FONT_TEXT);
        labelAcoes.setForeground(UIvariables.BLACK_COLOR);
        contentPanel.add(labelAcoes);

        // Linha decorativa.
        iconLine = new ImageIcon(getClass().getResource("/img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(0, 100, 1019, 32);
        contentPanel.add(labelIconLine);

        // Sidebar.
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(100, 42, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        add(sidebarPanel);

        // Logo na sidebar.
        iconLogo = new ImageIcon(getClass().getResource("/img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds((SIDEBAR_WIDTH_EXPANDED - 54) / 2, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        // Ícones e botões da sidebar.
        iconHome = new ImageIcon(getClass().getResource("/img/assets/icon-home.png"));
        labeliconHome = new JLabel(iconHome);
        labeliconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labeliconHome);

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40);
        configurarBotaoSidebar(btnHome); // Aplica estilo e comportamento padrão.
        btnHome.addActionListener(e -> {
            dispose();
            new CrudMedico().setVisible(true);
        });
        sidebarPanel.add(btnHome);
        // Ação para o botão Home (exemplo: tela inicial).

        // Ação para o botão Cadastro (navegar para RecepcionistaFrame).

        iconLogOut = new ImageIcon(getClass().getResource("/img/assets/icon-logOut.png"));
        labeliconLogOut = new JLabel(iconLogOut);
        labeliconLogOut.setBounds(58, 460, 32, 32);
        sidebarPanel.add(labeliconLogOut);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(56, 455, 120, 40);
        configurarBotaoSidebar(btnLogOut); // Aplica estilo e comportamento padrão.
        sidebarPanel.add(btnLogOut);

        // Ação para o botão Sair (navegar para LoginFrame).
        btnLogOut.addActionListener(e -> {
            dispose();
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });

        carregarPacientes(); // Carrega os pacientes do banco de dados e os exibe.
    }

    /**
     * Configura o estilo e os listeners de mouse para os botões da sidebar.
     * @param botao O JButton a ser configurado.
     */
    private void configurarBotaoSidebar(JButton botao) {
        botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA); // Define a fonte.
        botao.setForeground(UIvariables.WHITE_COLOR); // Define a cor do texto.
        botao.setBorderPainted(false); // Remove a borda.
        botao.setContentAreaFilled(false); // Remove o preenchimento de área.
        botao.setFocusPainted(false); // Remove o foco pintado.
        botao.setOpaque(false); // Torna o botão transparente.

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Altera o cursor ao passar o mouse.
                botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER); // Altera a fonte ao passar o mouse.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA); // Restaura a fonte ao sair o mouse.
            }
        });
    }

    /**
     * Carrega os pacientes do banco de dados e os exibe dinamicamente na interface.
     */
    private void carregarPacientes() {
        // Remove os componentes dinâmicos (labels e botões que listam pacientes) para evitar duplicatas.
        Component[] componentes = contentPanel.getComponents();
        for (Component comp : componentes) {
            // Verifica se o componente é um JLabel e está abaixo da linha decorativa.
            if (comp instanceof JLabel && comp != labelIconLine && comp.getY() > 100) {
                contentPanel.remove(comp);
            }
            // Remove todos os JButtons.
            if (comp instanceof JButton) contentPanel.remove(comp);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin"); // Estabelece a conexão.
             Statement stmt = conn.createStatement(); // Cria um statement.
             ResultSet rs = stmt.executeQuery("SELECT Nome, numero_telefone, cpf, data_nascimento FROM paciente_ WHERE isTriagem = TRUE")) { // Executa a query.

            int y = 140; // Posição Y inicial para os componentes dos pacientes.
            while (rs.next()) { // Itera sobre os resultados.
                String nome = rs.getString("Nome");
                String telefone = rs.getString("numero_telefone");
                String dataNascimento = rs.getString("data_nascimento");
                String cpf = rs.getString("cpf");

                // Cria e adiciona labels para Nome, Telefone e Data de Nascimento.
                JLabel lblNome = new JLabel(nome);
                lblNome.setBounds(70, y, 200, 40);
                lblNome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblNome);

                JLabel lblTelefone = new JLabel(telefone);
                lblTelefone.setBounds(300, y, 200, 40);
                lblTelefone.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblTelefone);

                JLabel lblData = new JLabel(dataNascimento);
                lblData.setBounds(570, y, 200, 40);
                lblData.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblData);

                // Botão "Editar" para abrir a tela Medico com o CPF do paciente.
                ImageIcon editIcon = new ImageIcon(getClass().getResource("../img/assets/icon-edit.png"));
                JButton btnEditar = new JButton(editIcon);
                btnEditar.setBounds(850, y, 40, 40);
                btnEditar.setBorderPainted(false);
                btnEditar.setContentAreaFilled(false);
                btnEditar.setFocusPainted(false);
                btnEditar.setOpaque(false);
                btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                contentPanel.add(btnEditar);

                String cpfSelecionado = cpf; // Captura o CPF para o ActionListener.
                btnEditar.addActionListener(e -> {
                    Medico medicoTela = new Medico(cpfSelecionado); // Cria uma nova tela Medico.
                    medicoTela.setVisible(true);
                    this.dispose(); // Fecha a tela atual de CRUD.
                });

                // Botão "Prontuário" para abrir a tela de prontuário.
                ImageIcon prontuarioIcon = new ImageIcon(getClass().getResource("../img/assets/prontuario-icon.png"));
                JButton btnProntuario = new JButton(prontuarioIcon);
                btnProntuario.setBounds(870, y, 100, 30);
                btnProntuario.setBorderPainted(false);
                btnProntuario.setContentAreaFilled(false);
                btnProntuario.setFocusPainted(false);
                btnProntuario.setOpaque(false);
                btnProntuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
                contentPanel.add(btnProntuario);
                btnProntuario.addActionListener(e -> {
                    new Prontuario(cpf); // Abre o prontuário com as informações do paciente clicado.
                    // Para o prontuário, geralmente você não fecha a tela atual, mas pode ser uma opção dependendo do fluxo.
                });

                y += 50; // Incrementa a posição Y para o próximo paciente.
            }

            contentPanel.revalidate(); // Revalida o layout do painel.
            contentPanel.repaint(); // Repinta o painel.

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrudMedico frame = new CrudMedico();
            frame.setVisible(true);
        });
    }
}