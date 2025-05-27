package ui;

import constants.UIvariables;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.net.URL;

public class Crud extends JFrame {

    JPanel sidebarPanel, contentPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;
    JLabel labelIconLine;
    JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;
    JLabel labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;

    private final int SIDEBAR_WIDTH_EXPANDED = 280;

    public Crud() {
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

        // Cabeçalhos com linhas visuais
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
        // Remove os dados anteriores
        Component[] componentes = contentPanel.getComponents();
        for (Component comp : componentes) {
            if (comp instanceof JLabel && comp != labelIconLine &&
                    !comp.getBounds().equals(new Rectangle(70, 30, 400, 60)) &&
                    !comp.getBounds().equals(new Rectangle(250, 30, 400, 60)) &&
                    !comp.getBounds().equals(new Rectangle(570, 30, 400, 60)) &&
                    !comp.getBounds().equals(new Rectangle(870, 30, 400, 60))) {
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
                lblNome.setBounds(70, y, 200, 40);
                lblNome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblNome);

                JLabel lblTelefone = new JLabel(telefone);
                lblTelefone.setBounds(250, y, 200, 40);
                lblTelefone.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblTelefone);

                JLabel lblData = new JLabel(dataNascimento);
                lblData.setBounds(570, y, 200, 40);
                lblData.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                contentPanel.add(lblData);

                JButton btnEditar = new JButton("Editar");
                btnEditar.setBounds(750, y, 100, 30);
                contentPanel.add(btnEditar);
                btnEditar.addActionListener(ev -> {
                    new EditarPacienteFrame(nome, telefone, dataNascimento, cpf, this::carregarPacientes).setVisible(true);
                });

                JButton btnExcluir = new JButton("Excluir");
                btnExcluir.setBounds(870, y, 100, 30);
                contentPanel.add(btnExcluir);

                String finalCpf = cpf;
                btnExcluir.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Deseja excluir o paciente com CPF: " + finalCpf + "?",
                            "Confirmar exclusão",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            Connection connDel = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
                            PreparedStatement pstmt = connDel.prepareStatement("DELETE FROM paciente_ WHERE cpf = ?");
                            pstmt.setString(1, finalCpf);
                            int deleted = pstmt.executeUpdate();
                            connDel.close();

                            if (deleted > 0) {
                                JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!");
                                carregarPacientes(); // Atualiza a visualização
                            } else {
                                JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
                            }

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente.");
                        }
                    }
                });

                y += 50;
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
            Crud frame = new Crud();
            frame.setVisible(true);
        });
    }
}
