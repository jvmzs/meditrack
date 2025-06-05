package ui;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;

public class ConviteService {

    // Parâmetros de conexão — preencha com os valores reais!
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/dbmeditrack";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin";

    // Parâmetros SMTP — preencha com os seus!
    private static final String SMTP_HOST     = "smtp.gmail.com";
    private static final String SMTP_PORT     = "587";
    private static final String SMTP_USER     = "";
    private static final String SMTP_PASSWORD = "";

    public static void enviarConvite(String email, String cpf) throws Exception {
        // 1. Gera token e define expiração
        String token      = UUID.randomUUID().toString();
        LocalDateTime exp = LocalDateTime.now().plusDays(1);

        // 2. Carrega driver (opcional em versões recentes do JDBC)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 3. Insere o convite no banco
        String sqlInsert = "INSERT INTO convite (cpf, token, expiracao) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pst = conn.prepareStatement(sqlInsert)) {

            pst.setString(1, cpf);
            pst.setString(2, token);
            pst.setTimestamp(3, Timestamp.valueOf(exp));
            pst.executeUpdate();
        }

        // 4. Configura JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.ssl.trust", "*");

        Session mailSession = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
            }
        });

        // 5. Monta e envia o e-mail
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress("no-reply@MediTrack.com"));
        msg.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email, false)
        );
        msg.setSubject("Ative seu acesso");

        String link = "http://localhost:63342/MidTrack_/htmlPage/index.html?_ijt=gs5mapmet9nrlhg6jo76gcga4v&_ij_reload=RELOAD_ON_SAVE" + token;
        String body = new StringBuilder()
                .append("Olá! Somos da MediTrack\n\n")
                .append("Clique no link abaixo para criar sua senha:\n\n")
                .append(link).append("\n\n")
                .append("Token: ").append(token).append("\n\n")
                .append("Esse link expira em 24h.\n")
                .append("Crie já sua senha para poder acessar o aplicativo\n")
                .toString();

        msg.setText(body);
        Transport.send(msg);
    }
}
