// Medico.java
package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Medico extends JFrame {

    JPanel sidebarPanel, contentPanel, ENFPanel;
    ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconLine;
    JTextField inputPres;
    JLabel labeliconLogo;
    JLabel labeliconHome;
    JLabel labeliconPacientes;
    JLabel labeliconLogOut;
    JLabel labelInputOBS, labelInpuFreq, labelIconLine;
    JTextField inputFreq;
    JLabel labelInputPeso, labelInputTitulo, labelInputPres, labelInputResult;
    JTextField inputPeso, inputResult;
    JButton btnCadastrar, btnHome, btnPacientes, btnLogOut;
    JTextArea inputObs;

    private String cpfPaciente;
    JLabel labelInputNome;

    // Configurações do banco (ajuste conforme seu banco e usuário)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbmeditrack";
    private static final String DB_USER = "root"; // Altere para seu usuário do MySQL
    private static final String DB_PASS = "admin"; // Altere para sua senha do MySQL

    public Medico(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;

        setTitle("Médico"); // Define o título da janela.
        setSize(1500, 800); // Define o tamanho da janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Configura a operação padrão ao fechar a janela.
        setResizable(false); // Impede que a janela seja redimensionada.
        setLocationRelativeTo(null); // Centraliza a janela na tela.
        setLayout(null); // Usa layout nulo para posicionamento manual dos componentes.
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME); // Define a cor de fundo do painel de conteúdo.

        // Configurações visuais globais para OptionPane e botões.
        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);

        // Ícone da janela.
        iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        if (iconLogo != null) setIconImage(iconLogo.getImage());

        contentPanel = new JPanel(); // Cria o painel de conteúdo principal.
        contentPanel.setBounds(100, 42, 1300, 670); // Define a posição e tamanho do painel de conteúdo.
        contentPanel.setBackground(UIvariables.WHITE_COLOR); // Define a cor de fundo do painel de conteúdo.
        contentPanel.setLayout(null); // Usa layout nulo para o painel de conteúdo.
        add(contentPanel); // Adiciona o painel de conteúdo ao frame.

        labelInputNome = new JLabel(); // Cria o label para o nome do paciente.
        labelInputNome.setBounds(350, 130, 400, 40); // Define a posição e tamanho do label.
        labelInputNome.setForeground(UIvariables.BLACK_COLOR); // Define a cor do texto do label.
        labelInputNome.setFont(UIvariables.FONT_TITLE2); // Define a fonte do texto do label.
        contentPanel.add(labelInputNome); // Adiciona o label ao painel de conteúdo.

        // Busca nome paciente no banco e atualiza label.
        String nomePaciente = buscarNomePacientePorCpf(cpfPaciente);
        labelInputNome.setText("Nome: " + nomePaciente);

        // Linhas decorativas.
        iconLine = new ImageIcon(getClass().getResource("/img/assets/icon-line.png"));
        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(270, 100, 1019, 32);
        contentPanel.add(labelIconLine);

        labelIconLine = new JLabel(iconLine);
        labelIconLine.setBounds(270, 530, 1019, 32);
        contentPanel.add(labelIconLine);

        ENFPanel = new RoundedPanel(30); // Cria um painel arredondado para inputs.
        ENFPanel.setBounds(361, 200, 330, 315); // Define a posição e tamanho.
        ENFPanel.setBackground(UIvariables.COLOR_ENFPanel); // Define a cor de fundo.
        ENFPanel.setLayout(null); // Usa layout nulo.
        contentPanel.add(ENFPanel); // Adiciona ao painel de conteúdo.

        // Componentes para Peso.
        labelInputPeso = new JLabel("Peso:");
        labelInputPeso.setBounds(40, 30, 80, 40);
        labelInputPeso.setForeground(UIvariables.BLACK_COLOR);
        labelInputPeso.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputPeso);

        inputPeso = new JTextField();
        inputPeso.setBounds(150, 30, 150, 40);
        inputPeso.setFont(UIvariables.FONT_15);
        inputPeso.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputPeso);

        // Componentes para Concentração (Prescrição).
        labelInputPres = new JLabel("Concentração");
        labelInputPres.setBounds(40, 95, 110, 40);
        labelInputPres.setForeground(UIvariables.BLACK_COLOR);
        labelInputPres.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputPres);

        JLabel labelPresUnidade = new JLabel("(mg/ml):");
        labelPresUnidade.setBounds(40, 110, 110, 40);
        labelPresUnidade.setForeground(UIvariables.BLACK_COLOR);
        labelPresUnidade.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelPresUnidade);

        inputPres = new JTextField();
        inputPres.setBounds(150, 100, 150, 40);
        inputPres.setFont(UIvariables.FONT_15);
        inputPres.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputPres);

        // Componentes para Dose.
        labelInputResult = new JLabel("Dose:");
        labelInputResult.setBounds(40, 165, 110, 40);
        labelInputResult.setForeground(UIvariables.BLACK_COLOR);
        labelInputResult.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInputResult);

        JLabel labelDoseUnidade = new JLabel("(mg):");
        labelDoseUnidade.setBounds(40, 180, 110, 40);
        labelDoseUnidade.setForeground(UIvariables.BLACK_COLOR);
        labelDoseUnidade.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelDoseUnidade);

        inputResult = new JTextField(); // Este campo pode ser para exibir um resultado de cálculo de dose.
        inputResult.setBounds(150, 170, 150, 40);
        inputResult.setFont(UIvariables.FONT_15);
        inputResult.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputResult);

        // Componentes para Frequência.
        labelInpuFreq = new JLabel("Frequencia:");
        labelInpuFreq.setBounds(40, 235, 110, 40);
        labelInpuFreq.setForeground(UIvariables.BLACK_COLOR);
        labelInpuFreq.setFont(UIvariables.FONT_15);
        ENFPanel.add(labelInpuFreq);

        inputFreq = new JTextField();
        inputFreq.setBounds(150, 235, 150, 40);
        inputFreq.setFont(UIvariables.FONT_15);
        inputFreq.setForeground(UIvariables.BLACK_COLOR);
        ENFPanel.add(inputFreq);

        // Componentes para Hipótese Diagnóstica (Diagnóstico).
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

        // Carrega os dados existentes do paciente ao inicializar a tela.
        carregarDadosPaciente(cpfPaciente);

        // Botão Finalizar (Atualizar Dados).
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
        // Adiciona o ActionListener para atualizar os dados do paciente.
        btnCadastrar.addActionListener(e -> {
            atualizarDadosPaciente();
        });
        contentPanel.add(btnCadastrar);

        // Sidebar Panel.
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, 280, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        contentPanel.add(sidebarPanel);

        // Logo na sidebar.
        iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds(114, 34, 54, 54);
        sidebarPanel.add(labeliconLogo);

        // Botão Home na sidebar.
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
            new CrudMedico();
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
        // Ação para o botão Home (exemplo: navegar para a tela principal).
        sidebarPanel.add(btnHome);

        // Botão Pacientes na sidebar.
        iconPacientes = new ImageIcon(getClass().getResource("/img/assets/icon-pacientes.png"));
        labeliconPacientes = new JLabel(iconPacientes);
        labeliconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labeliconPacientes);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(23, 235, 246, 65);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        sidebarPanel.add(btnPacientes);

        btnPacientes.addActionListener(e -> {
            // Navega para a tela CrudMedico.
            CrudMedico crud = new CrudMedico();
            crud.setVisible(true);
            SwingUtilities.getWindowAncestor(btnPacientes).dispose(); // Fecha a tela atual.
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

        // Botão Sair na sidebar.
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
        // Ação para o botão Sair (exemplo: navegar para a tela de login).
        btnLogOut.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Deslogar (a ser implementado, ex: new LoginScreen().setVisible(true)).");
            // Exemplo: new LoginFrame().setVisible(true);
            // dispose();
        });
        sidebarPanel.add(btnLogOut);

        // Título da tela.
        labelInputTitulo = new JLabel("Médico");
        labelInputTitulo.setBounds(350, 40, 350, 40);
        labelInputTitulo.setForeground(UIvariables.BLACK_COLOR);
        labelInputTitulo.setFont(UIvariables.FONT_TITLE);
        contentPanel.add(labelInputTitulo);
    }

    /**
     * Busca o nome do paciente no banco de dados pelo CPF.
     * @param cpf O CPF do paciente.
     * @return O nome do paciente, ou "Paciente não encontrado" se não for localizado.
     */
    private String buscarNomePacientePorCpf(String cpf) {
        String nome = "Paciente não encontrado";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC do MySQL.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // Estabelece a conexão com o banco.

            String sql = "SELECT nome FROM paciente_ WHERE cpf = ?"; // Query SQL para buscar o nome.
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf); // Define o parâmetro CPF.

            rs = stmt.executeQuery(); // Executa a query.
            if (rs.next()) {
                nome = rs.getString("nome"); // Obtém o nome do resultado.
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Driver JDBC do MySQL não encontrado.", "Erro de Driver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ou buscar paciente: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeResources(conn, stmt, rs); // Fecha os recursos do banco de dados.
        }
        return nome;
    }

    /**
     * Carrega os dados existentes do paciente (peso, prescricao, diagnostico, frequencia)
     * e preenche os campos da interface.
     * @param cpf O CPF do paciente.
     */
    private void carregarDadosPaciente(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT peso, diagnostico, frequencia FROM paciente_ WHERE cpf = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                inputPeso.setText(rs.getString("peso"));
                inputObs.setText(rs.getString("diagnostico"));
                inputFreq.setText(rs.getString("frequencia"));
            } else {
                // Inicializa os campos se os dados do paciente não forem encontrados
                inputPeso.setText("");
                inputPres.setText("");
                inputObs.setText("");
                inputFreq.setText("");
                JOptionPane.showMessageDialog(this, "Dados existentes do paciente não encontrados. Preencha e salve.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Driver JDBC do MySQL não encontrado.", "Erro de Driver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do paciente: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    /**
     * Atualiza os dados do paciente (peso, prescricao, diagnostico, frequencia) no banco de dados.
     */
    private void atualizarDadosPaciente() {
        String pesoStr = inputPeso.getText().trim();
        String diagnostico = inputObs.getText().trim();
        String frequencia = inputFreq.getText().trim();
        String concentracao = inputPres.getText().trim();
        String prescricao = inputObs.getText().trim();
        String dose = inputResult.getText().trim();



        // Validação de campos vazios.
        if (pesoStr.isEmpty() || diagnostico.isEmpty() || concentracao.isEmpty() || prescricao.isEmpty() || dose.isEmpty() || frequencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos: Peso, Concentração, Frequência e Hipótese Diagnóstica.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(pesoStr); // Tenta converter o peso para double.
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Peso' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "UPDATE paciente_ SET peso = ?, diagnostico = ?, frequencia = ? , concentracao = ?, dose = ?, prescricao = ? WHERE cpf = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, peso);
            stmt.setString(2, diagnostico);
            stmt.setString(3, frequencia);
            stmt.setString(4, concentracao);
            stmt.setString(5, dose);
            stmt.setString(6, prescricao);
            stmt.setString(7, cpfPaciente);

            int rowsAffected = stmt.executeUpdate(); // Executa a atualização.

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Dados do paciente atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Paciente com CPF " + cpfPaciente + " não encontrado para atualização.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Driver JDBC do MySQL não encontrado.", "Erro de Driver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar dados do paciente: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {

            inputResult.setText("");
            inputObs.setText("");
            inputFreq.setText("");
            inputPres.setText("");
            inputPeso.setText("");

            new CrudMedico().setVisible(true);
            dispose();

            closeResources(conn, stmt, null); // Fecha os recursos.
        }
    }

    /**
     * Fecha os recursos do banco de dados (Connection, PreparedStatement, ResultSet).
     * @param conn A conexão.
     * @param stmt O statement.
     * @param rs O ResultSet.
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao fechar recursos do banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method para testar a interface.
    public static void main(String[] args) {
        // Exemplo de uso: substitua "123.456.789-00" por um CPF válido do seu banco de dados.
        SwingUtilities.invokeLater(() -> new Medico("123.456.789-00").setVisible(true));
    }
}