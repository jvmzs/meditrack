package ui;

import constants.UIvariables;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Enfermeira extends JFrame {

    private JLabel labelTitle, labelPaciente, labelPrescricao, labelDisponivel, labelFrequencia, labelDiluicao,
            labeliconLogo, labeliconHome, labeliconPacientes, labeliconLogOut;

    // JLabels para exibir os valores fixos do médico
    private JLabel labelPrescricaoValue, labelDisponivelValue, labelDiluicaoValue, labelFrequenciaValue;

    // Campos de texto e rótulo de resultado para o CALCPANEL
    private JTextField inputPrescricaoCalc, inputDisponivelCalc, inputDiluicaoCalc;
    private JLabel labelResultadoCalc;

    private JTextArea inputObs; // Obs continua sendo um JTextArea para a enfermeira poder adicionar observações
    private JPanel contentPanel, infoPanel, sidebarPanel, calcPanel; // calcPanel de volta
    private JButton btnFinalizar, btnSeta, btnHome, btnPacientes, btnLogOut, btnCalc; // btnCalc de volta

    private ImageIcon iconSeta;

    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    private String nomePaciente;
    private String cpfPaciente; // Armazena o CPF do paciente

    public Enfermeira(String cpf) {
        this.cpfPaciente = cpf; // Salva o CPF recebido
        this.nomePaciente = buscarNomePaciente(cpf);
        initComponents();
        labelPaciente.setText("Paciente: " + this.nomePaciente); // Atualiza o JLabel com o nome
        carregarDadosPaciente(cpf); // Carrega os dados do paciente (apenas para os JLabels de exibição e obs)
    }

    private String buscarNomePaciente(String cpf) {
        String nome = "Paciente Não Encontrado";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
             PreparedStatement stmt = conn.prepareStatement("SELECT Nome FROM paciente_ WHERE cpf = ?")) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nome = rs.getString("Nome");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar nome do paciente: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
        return nome;
    }

    // Método para carregar APENAS os dados do médico para os JLabels de exibição e as observações
    private void carregarDadosPaciente(String cpf) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
             PreparedStatement stmt = conn.prepareStatement("SELECT dose, frequencia, concentracao, obs FROM paciente_ WHERE cpf = ?")) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Preenche os JLabels com os dados existentes do médico
                labelPrescricaoValue.setText(rs.getString("concentracao") != null ? rs.getString("concentracao") + " mg" : "N/A");
                labelDiluicaoValue.setText(rs.getString("dose") != null ? rs.getString("dose") + " ml" : "N/A");
                labelFrequenciaValue.setText(rs.getString("frequencia") != null ? rs.getString("frequencia") : "N/A");
                inputObs.setText(rs.getString("obs") != null ? rs.getString("obs") : "");

                // Os campos do calcPanel NÃO são preenchidos aqui
            } else {
                // Se não houver dados, os JLabels ficam com "N/A" e o JTextArea fica vazio
                labelPrescricaoValue.setText("N/A");
                labelDiluicaoValue.setText("N/A");
                labelFrequenciaValue.setText("N/A");
                inputObs.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do paciente: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para salvar/atualizar APENAS as observações
    private void salvarOuAtualizarObservacoes() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin")) {
            String sql = "UPDATE paciente_ SET obs = ? WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            String observacoes = inputObs.getText();

            stmt.setString(1, observacoes);
            stmt.setString(2, cpfPaciente); // O CPF para identificar o paciente

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
            new CrudEnfermeira().setVisible(true);
            dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum paciente encontrado com o CPF: " + cpfPaciente, "Erro", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar observações do paciente: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {
        setSize(1500, 800);
        setTitle("Enfermeiro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        ImageIcon frameIcon = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        if (frameIcon != null) setIconImage(frameIcon.getImage());

        contentPanel = new JPanel();
        contentPanel.setBounds(100, 60, 1300, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);

        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setBounds(1100, 570, 150, 60);
        btnFinalizar.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnFinalizar.setForeground(UIvariables.WHITE_COLOR);
        btnFinalizar.setBackground(UIvariables.COLOR_SIDEBAR);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setContentAreaFilled(false);
        btnFinalizar.setOpaque(true);
        btnFinalizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        btnFinalizar.addActionListener(e -> salvarOuAtualizarObservacoes()); // Ação para salvar/atualizar observações
        contentPanel.add(btnFinalizar);


        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        contentPanel.add(sidebarPanel);

        ImageIcon iconLogo = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scalediconLogo = iconLogo.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labeliconLogo = new JLabel(new ImageIcon(scalediconLogo));
        labeliconLogo.setBounds(114, 34, 54, 54);

        labeliconHome = new JLabel(new ImageIcon(getClass().getResource("../img/assets/icon-home.png")));
        labeliconHome.setBounds(58, 170, 32, 32);

        btnHome = new JButton("Home");
        btnHome.setBounds(95, 170, 120, 40);
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.setOpaque(false);
        btnHome.addActionListener(e -> {
            dispose();
            new Enfermeira(cpfPaciente);
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


        labeliconPacientes = new JLabel(new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png")));
        labeliconPacientes.setBounds(58, 250, 32, 32);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(95, 250, 150, 40);
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
            new CrudEnfermeira().setVisible(true); // Alterado para CrudEnfermeira
        });


        labeliconLogOut = new JLabel(new ImageIcon(getClass().getResource("../img/assets/icon-logOut.png")));
        labeliconLogOut.setBounds(58, 460, 32, 32);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(95, 455, 120, 40);
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
                new LoginFrame().setVisible(true);
            }
        });

        iconSeta = new ImageIcon(getClass().getResource("../img/assets/icon-sideBar.png"));
        btnSeta = new JButton(iconSeta);
        btnSeta.setBounds(SIDEBAR_WIDTH_EXPANDED - 40, 16, 32, 32);
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


        infoPanel = new JPanel();
        infoPanel.setBounds(SIDEBAR_WIDTH_EXPANDED, 10, 1300 - SIDEBAR_WIDTH_EXPANDED - 50, 650);
        infoPanel.setLayout(null);
        infoPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.add(infoPanel);

        labelTitle = new JLabel("Enfermeiro");
        labelTitle.setFont(UIvariables.FONT_BUTTON);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setBounds(80, 10, 200, 60);
        infoPanel.add(labelTitle);

        labelPaciente = new JLabel("Paciente: ");
        labelPaciente.setFont(UIvariables.FONT_LOGO);
        labelPaciente.setForeground(UIvariables.BLACK_COLOR);
        labelPaciente.setBounds(40, 75, 500, 60);
        infoPanel.add(labelPaciente);

        // --- RÓTULOS E VALORES DE PRESCRIÇÃO (visualização) ---
        labelPrescricao = new JLabel("Prescricao:");
        labelPrescricao.setFont(UIvariables.FONT_TEXT);
        labelPrescricao.setForeground(UIvariables.BLACK_COLOR);
        labelPrescricao.setBounds(40, 155, 150, 60);
        infoPanel.add(labelPrescricao);

        labelPrescricaoValue = new JLabel("N/A"); // Valor será preenchido pelo banco
        labelPrescricaoValue.setFont(UIvariables.FONT_15);
        labelPrescricaoValue.setForeground(UIvariables.BLACK_COLOR);
        labelPrescricaoValue.setBounds(40, 270, 200, 60); // Posição ao lado do rótulo
        infoPanel.add(labelPrescricaoValue);


        // --- RÓTULOS E VALORES DE DILUIÇÃO (visualização) ---
        labelDiluicao = new JLabel("Frasco:");
        labelDiluicao.setFont(UIvariables.FONT_TEXT);
        labelDiluicao.setForeground(UIvariables.BLACK_COLOR);
        labelDiluicao.setBounds(40, 245, 150, 60);
        infoPanel.add(labelDiluicao);

        labelDiluicaoValue = new JLabel("N/A"); // Valor será preenchido pelo banco
        labelDiluicaoValue.setFont(UIvariables.FONT_15);
        labelDiluicaoValue.setForeground(UIvariables.BLACK_COLOR);
        labelDiluicaoValue.setBounds(40, 180, 200, 60); // Posição ao lado do rótulo
        infoPanel.add(labelDiluicaoValue);

        // --- RÓTULOS E VALORES DE FREQUÊNCIA (visualização) ---
        labelFrequencia = new JLabel("Frequência:");
        labelFrequencia.setFont(UIvariables.FONT_TEXT);
        labelFrequencia.setForeground(UIvariables.BLACK_COLOR);
        labelFrequencia.setBounds(40, 350, 150, 60); // Ajuste de posição
        infoPanel.add(labelFrequencia);

        labelFrequenciaValue = new JLabel("N/A"); // Valor será preenchido pelo banco
        labelFrequenciaValue.setFont(UIvariables.FONT_15);
        labelFrequenciaValue.setForeground(UIvariables.BLACK_COLOR);
        labelFrequenciaValue.setBounds(40, 380, 200, 60); // Posição ao lado do rótulo
        infoPanel.add(labelFrequenciaValue);


        // --- CALCPANEL ---
        calcPanel = new JPanel();
        calcPanel.setBounds(500, 90, 400, 400); // Posição ajustada
        calcPanel.setBackground(UIvariables.COLOR_CALC);
        calcPanel.setLayout(null);
        infoPanel.add(calcPanel);

        JLabel labelPrescricaoCalc = new JLabel("Prescrição(mg):");
        labelPrescricaoCalc.setBounds(20, 40, 120, 20);
        labelPrescricaoCalc.setFont(UIvariables.FONT_15);
        labelPrescricaoCalc.setForeground(UIvariables.BLACK_COLOR);
        calcPanel.add(labelPrescricaoCalc);

        inputPrescricaoCalc = new JTextField();
        inputPrescricaoCalc.setBounds(170, 30, 200, 40);
        inputPrescricaoCalc.setFont(UIvariables.FONT_15);
        inputPrescricaoCalc.setForeground(UIvariables.BLACK_COLOR);
        inputPrescricaoCalc.setBackground(Color.WHITE);
        inputPrescricaoCalc.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        // NÃO preenchemos inputPrescricaoCalc aqui
        calcPanel.add(inputPrescricaoCalc);

        JLabel labelDisponivelCalc = new JLabel("Frasco disponível: ");
        labelDisponivelCalc.setBounds(20, 120, 250, 20);
        labelDisponivelCalc.setFont(UIvariables.FONT_15);
        labelDisponivelCalc.setForeground(UIvariables.BLACK_COLOR);
        calcPanel.add(labelDisponivelCalc);

        inputDisponivelCalc = new JTextField();
        inputDisponivelCalc.setBounds(170, 110, 200, 40);
        inputDisponivelCalc.setFont(UIvariables.FONT_15);
        inputDisponivelCalc.setForeground(UIvariables.BLACK_COLOR);
        inputDisponivelCalc.setBackground(Color.WHITE);
        inputDisponivelCalc.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        // NÃO preenchemos inputDisponivelCalc aqui
        calcPanel.add(inputDisponivelCalc);

        JLabel labelDiluicaoCalc = new JLabel("Diluição(ml):");
        labelDiluicaoCalc.setBounds(20, 200, 120, 20);
        labelDiluicaoCalc.setFont(UIvariables.FONT_15);
        labelDiluicaoCalc.setForeground(UIvariables.BLACK_COLOR);
        calcPanel.add(labelDiluicaoCalc);

        inputDiluicaoCalc = new JTextField();
        inputDiluicaoCalc.setBounds(170, 200, 200, 40);
        inputDiluicaoCalc.setFont(UIvariables.FONT_15);
        inputDiluicaoCalc.setForeground(UIvariables.BLACK_COLOR);
        inputDiluicaoCalc.setBackground(Color.WHITE);
        inputDiluicaoCalc.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        // NÃO preenchemos inputDiluicaoCalc aqui
        calcPanel.add(inputDiluicaoCalc);

        labelResultadoCalc = new JLabel("Resultado(ml):");
        labelResultadoCalc.setBounds(20, 280, 300, 20);
        labelResultadoCalc.setFont(UIvariables.FONT_15);
        labelResultadoCalc.setForeground(UIvariables.BLACK_COLOR);
        calcPanel.add(labelResultadoCalc);

        btnCalc = new JButton("Calcular Dosagem");
        btnCalc.setBounds(110, 350, 200, 40);
        btnCalc.setBackground(UIvariables.COLOR_CALC_BUTTON);
        btnCalc.setFont(UIvariables.FONT_15);
        btnCalc.setForeground(UIvariables.WHITE_COLOR);
        btnCalc.setFocusPainted(false);
        btnCalc.setBorder(BorderFactory.createLineBorder(new Color(20, 100, 180)));
        btnCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCalc.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnCalc.setBackground(new Color(30, 130, 230));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCalc.setBackground(UIvariables.COLOR_CALC_BUTTON);
            }
        });
        btnCalc.addActionListener(e -> {
            try {
                double prescricao = Double.parseDouble(inputPrescricaoCalc.getText());
                double disponivel = Double.parseDouble(inputDisponivelCalc.getText());
                double diluicao = Double.parseDouble(inputDiluicaoCalc.getText());

                if (disponivel <= 0 || diluicao <= 0) {
                    JOptionPane.showMessageDialog(this, "Os valores de 'Disponível' e 'Diluição' devem ser maiores que zero.", "Erro de Cálculo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double resultado = (prescricao * diluicao) / disponivel;
                labelResultadoCalc.setText(String.format("Resultado(ml): %.2f ml", resultado));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos para Prescrição, Disponível e Diluição.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        });
        calcPanel.add(btnCalc);


        // --- CAMPO DE OBSERVAÇÕES (continua editável) ---
        JLabel labelObs = new JLabel("Obs:");
        labelObs.setFont(UIvariables.FONT_TEXT);
        labelObs.setBounds(40, 485, 100, 20); // Ajuste de posição
        infoPanel.add(labelObs);

        inputObs = new JTextArea();
        inputObs.setFont(UIvariables.FONT_15);
        inputObs.setBackground(UIvariables.GRAY_COLOR);
        inputObs.setLineWrap(true);
        inputObs.setWrapStyleWord(true);
        inputObs.setEditable(false);
        inputObs.setBorder(new LineBorder(Color.GRAY, 1));
        JScrollPane scrollObs = new JScrollPane(inputObs);
        scrollObs.setBounds(40, 515, 350, 120); // Ajuste de posição

        infoPanel.add(scrollObs);


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

    public static void main(String[] agrs) {
        // Exemplo de uso com um CPF de teste.
        // Para testar, certifique-se de ter um paciente com este CPF no seu banco
        // e que ele tenha valores para 'prescricao', 'concentracao', 'dose', 'frequencia', 'obs'.
        new Enfermeira("54472441888").setVisible(true); // Use um CPF válido para testes
    }

    private void toggleSidebar() {
        int targetWidth = sidebarExpanded ? SIDEBAR_WIDTH_MINIMIZED : SIDEBAR_WIDTH_EXPANDED;
        int initialSidebarWidth = sidebarPanel.getWidth();
        int initialInfoPanelX = infoPanel.getX();
        int infoPanelWidth = infoPanel.getWidth();

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(15, new ActionListener() {
            int currentStep = 0;
            final int totalSteps = 15;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentStep++;
                double progress = (double) currentStep / totalSteps;

                if (progress > 1) {
                    progress = 1;
                }

                int newWidth = initialSidebarWidth + (int) ((targetWidth - initialSidebarWidth) * progress);
                sidebarPanel.setBounds(0, 0, newWidth, 670);

                // Calcula o novo X para o infoPanel para que ele se movam de forma mais proporcional
                int newInfoPanelX = (int) (initialInfoPanelX + (newWidth - initialSidebarWidth) * (infoPanelWidth / (double)(SIDEBAR_WIDTH_EXPANDED - SIDEBAR_WIDTH_MINIMIZED)));

                infoPanel.setBounds(newWidth + 20, infoPanel.getY(), 1300 - newWidth - 50, infoPanel.getHeight());


                updateSidebarComponents(newWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                infoPanel.revalidate();
                infoPanel.repaint();

                if (progress == 1) {
                    animationTimer.stop();
                    sidebarExpanded = !sidebarExpanded;
                }
            }
        });
        animationTimer.start();
    }

    private void updateSidebarComponents(int sidebarWidth) {
        boolean isMinimized = sidebarWidth <= SIDEBAR_WIDTH_MINIMIZED + 5;
        int logoWidth = 54;

        int iconX = isMinimized ? sidebarWidth / 2 - 16 : 58;
        int inconxLogo = isMinimized ? sidebarWidth / 2 - (logoWidth / 2) : 114;

        labeliconLogo.setBounds(inconxLogo, 34, logoWidth, logoWidth);
        labeliconHome.setBounds(iconX, 170, 32, 32);
        labeliconPacientes.setBounds(iconX, 250, 32, 32);
        labeliconLogOut.setBounds(iconX, 460, 32, 32);

        btnHome.setBounds(isMinimized ? iconX + 40 : 95, 170, 120, 40);
        btnPacientes.setBounds(isMinimized ? iconX + 40 : 95, 250, 150, 40);
        btnLogOut.setBounds(isMinimized ? iconX + 40 : 95, 455, 120, 40);

        btnHome.setText(isMinimized ? "" : "Home");
        btnPacientes.setText(isMinimized ? "" : "Pacientes");
        btnLogOut.setText(isMinimized ? "" : "Sair");

        btnSeta.setBounds(sidebarWidth - 40, 16, 32, 32);
    }
}