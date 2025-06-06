package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Prontuario extends JFrame {
    private JPanel panelContent, panelDadosPessoais, panelEstadoClinico;
    private JLabel labelTitulo, labelIcon, labelIcon2;
    private ImageIcon icon, icon2;

    public Prontuario(String cpfPaciente) {
        setTitle("Prontuário");
        setSize(1500, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230));

        panelContent = new JPanel();
        panelContent.setBackground(new Color(240, 248, 255));
        panelContent.setBounds(100, 100, 1300, 550);
        panelContent.setLayout(null);
        add(panelContent);

        icon = new ImageIcon(getClass().getResource("../img/assets/icon-prontuario.png"));
        labelIcon = new JLabel(icon);
        labelIcon.setBounds(20, 5, 80, 80);
        panelContent.add(labelIcon);

        labelTitulo = new JLabel("Prontuário");
        labelTitulo.setFont(UIvariables.FONT_TITLE);
        labelTitulo.setBounds(80, 33, 300, 40);
        panelContent.add(labelTitulo);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 70, 1260, 1);
        panelContent.add(separator);

        panelDadosPessoais = new JPanel(new GridLayout(6, 1, 10, 10));
        panelDadosPessoais.setBounds(250, 130, 300, 300);
        panelDadosPessoais.setBackground(null);
        panelContent.add(panelDadosPessoais);

        JLabel labelDados = new JLabel("Dados Pessoais");
        labelDados.setFont(UIvariables.FONT_BUTTON);
        labelDados.setForeground(UIvariables.BLACK_COLOR);
        panelDadosPessoais.add(labelDados);

        panelEstadoClinico = new JPanel();
        panelEstadoClinico.setLayout(new BoxLayout(panelEstadoClinico, BoxLayout.Y_AXIS));
        panelEstadoClinico.setBounds(700, 120, 500, 300);
        panelEstadoClinico.setBackground(null);
        panelContent.add(panelEstadoClinico);

        JLabel estadoClinico = new JLabel("Estado Clínico");
        estadoClinico.setFont(UIvariables.FONT_BUTTON);
        estadoClinico.setForeground(UIvariables.BLACK_COLOR);
        panelEstadoClinico.add(estadoClinico);

        icon2 = new ImageIcon(getClass().getResource("../img/assets/icon-line-vertical.png"));
        labelIcon2 = new JLabel(icon2);
        labelIcon2.setBounds(500, 100, 100, 400);
        panelContent.add(labelIcon2);

        carregarDadosDoBanco(cpfPaciente);

        setVisible(true);
    }

    private void carregarDadosDoBanco(String cpf) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM paciente_ WHERE cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String temperatura = rs.getString("Temperatura_corpo");
                String peso = rs.getString("peso") + " kg";
                String altura = rs.getString("altura");
                String idade = rs.getString("idade");
                String sintomas = rs.getString("sintomas");
                String alergias = rs.getString("Alergia");
                String obs = rs.getString("obs");

                panelDadosPessoais.add(createInfoLabel("CPF:", cpf));
                panelDadosPessoais.add(createInfoLabel("Temperatura:", temperatura));
                panelDadosPessoais.add(createInfoLabel("Peso:", peso));
                panelDadosPessoais.add(createInfoLabel("Altura:", altura));
                panelDadosPessoais.add(createInfoLabel("Idade:", idade));

                panelEstadoClinico.add(createTextArea("Sintomas:", sintomas));
                panelEstadoClinico.add(createTextArea("Alergias:", alergias));
                panelEstadoClinico.add(createTextArea("Obs:", obs));
            } else {
                JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados.");
        }
    }

    private JPanel createInfoLabel(String titulo, String valor) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(null);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(UIvariables.FONT_INPUT);
        labelTitulo.setForeground(UIvariables.BLACK_COLOR);

        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(UIvariables.FONT_TITLE2);

        panel.add(labelTitulo);
        panel.add(labelValor);

        return panel;
    }

    private JPanel createTextArea(String titulo, String valor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(UIvariables.FONT_INPUT);
        labelTitulo.setForeground(UIvariables.BLACK_COLOR);

        JTextArea textArea = new JTextArea(valor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(null);
        textArea.setFont(UIvariables.FONT_TITLE2);
        textArea.setBorder(null);
        textArea.setMaximumSize(new Dimension(500, 100));
        textArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(labelTitulo);
        panel.add(textArea);

        return panel;
    }

    public static void main(String[] args) {
        new Prontuario("47100065652");
    }
}