<<<<<<< HEAD
package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class EditarPacienteFrame extends JFrame {

    private JTextField txtNome, txtTelefone, txtNascimento;
    private final String cpfPaciente;
    private final Runnable onPacienteEditado; // callback para atualizar lista no frame principal

    public EditarPacienteFrame(String nome, String telefone, String nascimento, String cpf, Runnable callback) {
        this.cpfPaciente = cpf;
        this.onPacienteEditado = callback;

        setTitle("Editar Paciente");
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

        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setBounds(30, 130, 100, 30);
        add(lblNascimento);

        txtNascimento = new JTextField(nascimento);
        txtNascimento.setBounds(130, 130, 220, 30);
        add(txtNascimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(130, 200, 100, 35);
        btnSalvar.setBackground(UIvariables.BACKGROUND_PANEL_BLUE);
        btnSalvar.setForeground(UIvariables.WHITE_COLOR);
        add(btnSalvar);

        btnSalvar.addActionListener(this::salvarPaciente);
    }

    private void salvarPaciente(ActionEvent e) {
        String novoNome = txtNome.getText().trim();
        String novoTelefone = txtTelefone.getText().trim();
        String novaData = txtNascimento.getText().trim();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
             PreparedStatement pstmt = conn.prepareStatement("UPDATE paciente_ SET Nome = ?, numero_telefone = ?, data_nascimento = ? WHERE cpf = ?")) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, novoTelefone);
            pstmt.setString(3, novaData);
            pstmt.setString(4, cpfPaciente);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");
                onPacienteEditado.run(); // atualiza lista
                dispose(); // fecha a janela
            } else {
                JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar paciente.");
        }
    }
}
=======
package ui;

import constants.UIvariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class EditarPacienteFrame extends JFrame {

    private JTextField txtNome, txtTelefone, txtNascimento;
    private final String cpfPaciente;
    private final Runnable onPacienteEditado; // callback para atualizar lista no frame principal

    public EditarPacienteFrame(String nome, String telefone, String nascimento, String cpf, Runnable callback) {
        this.cpfPaciente = cpf;
        this.onPacienteEditado = callback;

        setTitle("Editar Paciente");
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

        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setBounds(30, 130, 100, 30);
        add(lblNascimento);

        txtNascimento = new JTextField(nascimento);
        txtNascimento.setBounds(130, 130, 220, 30);
        add(txtNascimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(130, 200, 100, 35);
        btnSalvar.setBackground(UIvariables.BACKGROUND_PANEL_BLUE);
        btnSalvar.setForeground(UIvariables.WHITE_COLOR);
        add(btnSalvar);

        btnSalvar.addActionListener(this::salvarPaciente);
    }

    private void salvarPaciente(ActionEvent e) {
        String novoNome = txtNome.getText().trim();
        String novoTelefone = txtTelefone.getText().trim();
        String novaData = txtNascimento.getText().trim();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmeditrack", "root", "admin");
             PreparedStatement pstmt = conn.prepareStatement("UPDATE paciente_ SET Nome = ?, numero_telefone = ?, data_nascimento = ? WHERE cpf = ?")) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, novoTelefone);
            pstmt.setString(3, novaData);
            pstmt.setString(4, cpfPaciente);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");
                onPacienteEditado.run(); // atualiza lista
                dispose(); // fecha a janela
            } else {
                JOptionPane.showMessageDialog(this, "Paciente não encontrado.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar paciente.");
        }
    }
}
>>>>>>> 5960a5e (primeiro commit)
