package ui;

import constants.UIvariables;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;

public class EditarFuncionario extends JFrame {

    private JTextField txtNome, txtTelefone;
    private JFormattedTextField txtNascimento;
    private final String cpfFuncionario;
    private final Runnable onFuncionarioEditado;

    public EditarFuncionario(String nome, String telefone, String cpf, Runnable callback) {
        this.cpfFuncionario = cpf;
        this.onFuncionarioEditado = callback;

        setTitle("Editar Usuário");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(UIvariables.WHITE_COLOR);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 100, 30);
        add(lblNome);

        txtNome = new JTextField(nome);
        txtNome.setBounds(130, 30, 220, 30);
        add(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(30, 80, 100, 30);
        add(lblTelefone);

        txtTelefone = new JTextField(telefone);
        txtTelefone.setBounds(130, 80, 220, 30);
        add(txtTelefone);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(130, 200, 100, 35);
        btnSalvar.setBackground(UIvariables.BACKGROUND_PANEL_BLUE);
        btnSalvar.setForeground(UIvariables.WHITE_COLOR);
        add(btnSalvar);

        btnSalvar.addActionListener(this::salvarFuncionario);
    }

    private void salvarFuncionario(ActionEvent e) {
        String novoNome = txtNome.getText().trim();
        String novoTelefone = txtTelefone.getText().trim();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
             PreparedStatement pstmt = conn.prepareStatement("UPDATE funcionario_ SET Nome = ?, numero_telefone = ? WHERE cpf = ?")) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, novoTelefone);
            pstmt.setString(3, cpfFuncionario);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
                onFuncionarioEditado.run();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar Funcionário.");
        }
    }
}