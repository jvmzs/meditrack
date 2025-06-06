package ui;

import constants.UIvariables;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RecepcionistaFrame extends JFrame {

    // UI Components
    private JPanel sidebarPanel, contentPanel, formsPanel;
    private JLabel labelTitle, labelIconLine, labelInputCPF, labelInputNome,
            labelInputSobrenome, labelInputDataNasc, labelInputNumeroTelefone,
            labelIconLogo, labelIconHome, labelIconPacientes, labelIconLogOut;
    private JFormattedTextField dateField;
    private JTextField inputCPF, inputNome, inputSobrenome, inputNumeroTelefone;
    private JButton btnCadastrar, btnSeta, btnHome, btnPacientes, btnLogOut;

    // Image Icons
    private ImageIcon iconLogo, iconHome, iconPacientes, iconLogOut, iconSeta, iconLine;

    // Animation Variables
    private boolean sidebarExpanded = true;
    private final int SIDEBAR_WIDTH_EXPANDED = 280;
    private final int SIDEBAR_WIDTH_MINIMIZED = 80;
    private Timer animationTimer;

    // Database Credentials (consider externalizing for production)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbmeditrack";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin";

    public RecepcionistaFrame() {
        setTitle("Cadastrar Paciente");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.BACKGROUND_RECEPCIONISTA_FRAME);

        // Global UI Manager settings for consistency
        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", UIvariables.BLACK_COLOR);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", UIvariables.BACKGROUND_PANEL_BLUE);
        UIManager.put("Button.foreground", UIvariables.WHITE_COLOR);

        // Set frame icon
        URL iconUrl = getClass().getResource("../img/img-logo.png");
        if (iconUrl != null) {
            iconLogo = new ImageIcon(iconUrl);
            setIconImage(iconLogo.getImage());
        } else {
            System.err.println("Logo icon not found!");
        }

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setBounds(100, 42, 1300, 670);
        contentPanel.setBackground(UIvariables.WHITE_COLOR);
        contentPanel.setLayout(null);
        add(contentPanel);

        // Sidebar Panel
        sidebarPanel = new JPanel();
        sidebarPanel.setBounds(0, 0, SIDEBAR_WIDTH_EXPANDED, 670);
        sidebarPanel.setBackground(UIvariables.COLOR_SIDEBAR);
        sidebarPanel.setLayout(null);
        contentPanel.add(sidebarPanel);

        // Sidebar Logo
        ImageIcon sidebarLogoIcon = new ImageIcon(getClass().getResource("../img/img-logo.png"));
        Image scaledSidebarLogo = sidebarLogoIcon.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        labelIconLogo = new JLabel(new ImageIcon(scaledSidebarLogo));
        labelIconLogo.setBounds(114, 34, 54, 54);
        sidebarPanel.add(labelIconLogo);

        // Home Button and Icon
        iconHome = new ImageIcon(getClass().getResource("../img/assets/icon-home.png"));
        labelIconHome = new JLabel(iconHome);
        labelIconHome.setBounds(58, 170, 32, 32);
        sidebarPanel.add(labelIconHome);

        btnHome = new JButton("Home");
        btnHome.setBounds(70, 170, 120, 40);
        btnHome.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnHome.setForeground(UIvariables.WHITE_COLOR);
        styleButton(btnHome); // Apply consistent styling
        btnHome.addActionListener(e -> {
            dispose();
            new RecepcionistaFrame().setVisible(true); // Navigate back to this frame
        });
        sidebarPanel.add(btnHome);

        // Patients Button and Icon
        iconPacientes = new ImageIcon(getClass().getResource("../img/assets/icon-pacientes.png"));
        labelIconPacientes = new JLabel(iconPacientes);
        labelIconPacientes.setBounds(58, 250, 32, 32);
        sidebarPanel.add(labelIconPacientes);

        btnPacientes = new JButton("Pacientes");
        btnPacientes.setBounds(50, 248, 200, 40);
        btnPacientes.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnPacientes.setForeground(UIvariables.WHITE_COLOR);
        styleButton(btnPacientes); // Apply consistent styling
        btnPacientes.addActionListener(e -> {
            new Crud().setVisible(true); // Navigate to Crud frame
            SwingUtilities.getWindowAncestor(btnPacientes).dispose(); // Close current window
        });
        sidebarPanel.add(btnPacientes);

        // Logout Button and Icon
        iconLogOut = new ImageIcon(getClass().getResource("../img/assets/icon-logOut.png"));
        labelIconLogOut = new JLabel(iconLogOut);
        labelIconLogOut.setBounds(58, 460, 32, 32);
        sidebarPanel.add(labelIconLogOut);

        btnLogOut = new JButton("Sair");
        btnLogOut.setBounds(45, 455, 150, 40);
        btnLogOut.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
        btnLogOut.setForeground(UIvariables.WHITE_COLOR);
        styleButton(btnLogOut); // Apply consistent styling
        btnLogOut.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true); // Navigate to Login frame
        });
        sidebarPanel.add(btnLogOut);

        // Sidebar Toggle Button
        iconSeta = new ImageIcon(getClass().getResource("../img/assets/icon-sideBar.png"));
        btnSeta = new JButton(iconSeta);
        btnSeta.setBounds(240, 16, 32, 32);
        styleButton(btnSeta); // Apply consistent styling
        btnSeta.addActionListener(e -> toggleSidebar());
        sidebarPanel.add(btnSeta);

        // Forms Panel
        formsPanel = new JPanel();
        formsPanel.setBounds(346, 0, 1154, 800);
        formsPanel.setBackground(UIvariables.WHITE_COLOR);
        formsPanel.setLayout(null);
        contentPanel.add(formsPanel);

        // Form Title
        labelTitle = new JLabel("Cadastrar Paciente");
        labelTitle.setBounds(0, 40, 400, 60);
        labelTitle.setForeground(UIvariables.BLACK_COLOR);
        labelTitle.setFont(UIvariables.FONT_TITLE);
        formsPanel.add(labelTitle);

        // CPF Input
        labelInputCPF = new JLabel("CPF");
        labelInputCPF.setBounds(0, 160, 80, 40);
        labelInputCPF.setForeground(UIvariables.BLACK_COLOR);
        labelInputCPF.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(labelInputCPF);

        inputCPF = new JTextField();
        inputCPF.setBounds(0, 194, 330, 40);
        inputCPF.setFont(UIvariables.FONT_INPUT);
        inputCPF.setForeground(UIvariables.BLACK_COLOR);
        formsPanel.add(inputCPF);

        // Name Input
        labelInputNome = new JLabel("Nome");
        labelInputNome.setBounds(0, 300, 80, 40);
        labelInputNome.setForeground(UIvariables.BLACK_COLOR);
        labelInputNome.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(labelInputNome);

        inputNome = new JTextField();
        inputNome.setBounds(0, 334, 330, 40);
        inputNome.setFont(UIvariables.FONT_INPUT);
        inputNome.setForeground(UIvariables.BLACK_COLOR);
        formsPanel.add(inputNome);

        // Last Name Input
        labelInputSobrenome = new JLabel("Sobrenome");
        labelInputSobrenome.setBounds(500, 300, 330, 40);
        labelInputSobrenome.setForeground(UIvariables.BLACK_COLOR);
        labelInputSobrenome.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(labelInputSobrenome);

        inputSobrenome = new JTextField();
        inputSobrenome.setBounds(500, 334, 330, 40);
        inputSobrenome.setForeground(UIvariables.BLACK_COLOR);
        inputSobrenome.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(inputSobrenome);

        // Phone Number Input
        labelInputNumeroTelefone = new JLabel("Número de telefone");
        labelInputNumeroTelefone.setBounds(0, 444, 250, 40);
        labelInputNumeroTelefone.setForeground(UIvariables.BLACK_COLOR);
        labelInputNumeroTelefone.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(labelInputNumeroTelefone);

        inputNumeroTelefone = new JTextField();
        inputNumeroTelefone.setBounds(0, 478, 330, 40);
        inputNumeroTelefone.setForeground(UIvariables.BLACK_COLOR);
        inputNumeroTelefone.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(inputNumeroTelefone);

        // Date of Birth Input with Mask
        labelInputDataNasc = new JLabel("Data de nascimento");
        labelInputDataNasc.setBounds(500, 444, 250, 40);
        labelInputDataNasc.setForeground(UIvariables.BLACK_COLOR);
        labelInputDataNasc.setFont(UIvariables.FONT_INPUT);
        formsPanel.add(labelInputDataNasc);

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####"); // DD/MM/YYYY
            dateMask.setPlaceholderCharacter('_');
            dateField = new JFormattedTextField(dateMask);
            dateField.setColumns(10);
            dateField.setBounds(500, 478, 330, 50);
            dateField.setFont(UIvariables.FONT_INPUT);
            formsPanel.add(dateField);
        } catch (ParseException e) {
            System.err.println("Error creating date mask: " + e.getMessage());
            e.printStackTrace();
        }

        // Decorative Line
        iconLine = new ImageIcon(getClass().getResource("../img/assets/icon-line.png"));
        Image scaledIconLine = iconLine.getImage().getScaledInstance(904, 4, Image.SCALE_SMOOTH);
        labelIconLine = new JLabel(new ImageIcon(scaledIconLine));
        labelIconLine.setBounds(0, 560, 904, 4);
        formsPanel.add(labelIconLine);

        // Register Button
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(760, 600, 150, 60);
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
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPatient();
            }
        });
        formsPanel.add(btnCadastrar);

        setVisible(true);
    }

    /**
     * Applies common styling to JButtons.
     * @param button The JButton to style.
     */
    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (button != btnSeta) { // Don't change font size for the toggle button
                    button.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != btnSeta) {
                    button.setFont(UIvariables.FONT_INPUT_RECEPCIONISTA);
                }
            }
        });
    }

    /**
     * Toggles the sidebar's expanded/minimized state with an animation.
     */
    private void toggleSidebar() {
        int targetWidth = sidebarExpanded ? SIDEBAR_WIDTH_MINIMIZED : SIDEBAR_WIDTH_EXPANDED;
        final int initialSidebarWidth = sidebarPanel.getWidth();
        final int initialFormsPanelX = formsPanel.getX();
        final int formsPanelWidth = formsPanel.getWidth();
        final int formsPanelHeight = formsPanel.getHeight();
        final int formsPanelY = formsPanel.getY();

        toggleSidebarElements(!sidebarExpanded); // Adjust text visibility immediately

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(15, new ActionListener() {
            private int currentStep = 0;
            private final int totalSteps = 10; // Number of animation steps for smoother movement

            @Override
            public void actionPerformed(ActionEvent e) {
                currentStep++;
                double progress = (double) currentStep / totalSteps;

                if (progress > 1.0) {
                    progress = 1.0;
                }

                int newSidebarWidth = (int) (initialSidebarWidth + (targetWidth - initialSidebarWidth) * progress);
                sidebarPanel.setBounds(0, 0, newSidebarWidth, 670);

                // Calculate formsPanel's new X position based on sidebar width
                int newFormsPanelX;
                if (sidebarExpanded) { // If currently expanded, moving to minimized
                    newFormsPanelX = (int) (initialFormsPanelX - (initialFormsPanelX - (SIDEBAR_WIDTH_MINIMIZED + 66)) * progress);
                } else { // If currently minimized, moving to expanded
                    newFormsPanelX = (int) ((SIDEBAR_WIDTH_MINIMIZED + 66) + (initialFormsPanelX - (SIDEBAR_WIDTH_MINIMIZED + 66)) * (1 - progress));
                }

                formsPanel.setBounds(newFormsPanelX, formsPanelY, formsPanelWidth, formsPanelHeight);

                updateSidebarComponents(newSidebarWidth);

                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                formsPanel.revalidate();
                formsPanel.repaint();

                if (progress >= 1.0) {
                    animationTimer.stop();
                    sidebarExpanded = !sidebarExpanded;
                    // Ensure final positions are exactly target
                    sidebarPanel.setBounds(0, 0, targetWidth, 670);
                    formsPanel.setBounds(sidebarExpanded ? SIDEBAR_WIDTH_EXPANDED + 66 : SIDEBAR_WIDTH_MINIMIZED + 66, formsPanelY, formsPanelWidth, formsPanelHeight);
                    updateSidebarComponents(targetWidth); // Final update for perfect alignment
                }
            }
        });
        animationTimer.start();
    }

    /**
     * Updates the positions and visibility of sidebar components during animation.
     * @param sidebarWidth The current width of the sidebar.
     */
    private void updateSidebarComponents(int sidebarWidth) {
        boolean isMinimized = sidebarWidth <= (SIDEBAR_WIDTH_MINIMIZED + SIDEBAR_WIDTH_EXPANDED) / 2; // Heuristic for transition point
        int logoMarginX = 114;
        int iconMarginX = 58;

        // Animate icon positions smoothly
        int newLogoX = (int) (logoMarginX + (isMinimized ? (SIDEBAR_WIDTH_MINIMIZED / 2 - labelIconLogo.getWidth() / 2 - logoMarginX) * ((double)(SIDEBAR_WIDTH_EXPANDED - sidebarWidth) / (SIDEBAR_WIDTH_EXPANDED - SIDEBAR_WIDTH_MINIMIZED)) : 0));
        int newIconX = (int) (iconMarginX + (isMinimized ? (SIDEBAR_WIDTH_MINIMIZED / 2 - labelIconHome.getWidth() / 2 - iconMarginX) * ((double)(SIDEBAR_WIDTH_EXPANDED - sidebarWidth) / (SIDEBAR_WIDTH_EXPANDED - SIDEBAR_WIDTH_MINIMIZED)) : 0));

        labelIconLogo.setBounds(newLogoX, 34, 54, 54);
        labelIconHome.setBounds(newIconX, 170, 32, 32);
        labelIconPacientes.setBounds(newIconX, 250, 32, 32);
        labelIconLogOut.setBounds(newIconX, 460, 32, 32);

        // Adjust button text visibility during transition
        btnHome.setText(isMinimized ? "" : "Home");
        btnPacientes.setText(isMinimized ? "" : "Pacientes");
        btnLogOut.setText(isMinimized ? "" : "Sair");

        // Adjust button bounds
        int buttonTextXOffset = 30; // Offset for button text relative to icon
        btnHome.setBounds(newIconX + (isMinimized ? 0 : buttonTextXOffset), 170, 150, 40);
        btnPacientes.setBounds(newIconX + (isMinimized ? 0 : buttonTextXOffset - 10), 248, 150, 40);
        btnLogOut.setBounds(newIconX + (isMinimized ? 0 : buttonTextXOffset), 455, 150, 40);

        // Reposition the toggle button
        btnSeta.setBounds(sidebarWidth - 40, 16, 32, 32);
    }

    /**
     * Controls the visibility of sidebar button texts.
     * @param visible True to show text, false to hide.
     */
    private void toggleSidebarElements(boolean visible) {
        btnHome.setText(visible ? "Home" : "");
        btnPacientes.setText(visible ? "Pacientes" : "");
        btnLogOut.setText(visible ? "Sair" : "");

        labelIconHome.setVisible(true);
        labelIconPacientes.setVisible(true);
        labelIconLogOut.setVisible(true);
    }

    /**
     * Handles the patient registration process, including validation and database insertion.
     */
    private void registerPatient() {
        String cpf = inputCPF.getText().trim();
        String nome = inputNome.getText().trim();
        String sobrenome = inputSobrenome.getText().trim();
        String numeroTelefone = inputNumeroTelefone.getText().trim();
        String dataDigitada = dateField.getText().replaceAll("[\\s_]", "").trim();

        // Input validation
        if (cpf.isEmpty() || nome.isEmpty() || sobrenome.isEmpty() || numeroTelefone.isEmpty() || dataDigitada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date dataNascimento = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date utilDate = formato.parse(dataDigitada);
            dataNascimento = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato DD/MM/YYYY.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

        String nomeCompleto = nome + " " + sobrenome;
        String query = "INSERT INTO paciente_ (Nome, cpf, numero_telefone, data_nascimento) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, nomeCompleto);
            pstmt.setString(2, cpf);
            pstmt.setString(3, numeroTelefone);
            pstmt.setDate(4, dataNascimento);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Clear fields after successful registration
                inputCPF.setText("");
                inputNome.setText("");
                inputSobrenome.setText("");
                inputNumeroTelefone.setText("");
                dateField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o paciente. Nenhuma linha afetada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) { // SQLState for integrity constraint violation (e.g., duplicate CPF)
                JOptionPane.showMessageDialog(this, "Erro: CPF já cadastrado.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + ex.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
            }
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecepcionistaFrame().setVisible(true));
    }
}