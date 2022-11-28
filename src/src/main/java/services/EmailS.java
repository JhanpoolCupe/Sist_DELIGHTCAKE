package services;

import dao.Conexion;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.Cliente;

public class EmailS extends Conexion {

    private static void createEmail(String remitente,String clave,String destinatario, String asunto, String contenido) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage correo = new MimeMessage(session);
        try {  
            correo.setFrom(new InternetAddress(destinatario));
            correo.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            correo.setSubject(asunto);
            correo.setText(contenido);
            
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",remitente, clave);
            transport.sendMessage(correo, correo.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "Email SI Enviado", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
        } catch (AddressException ex) {
            Logger.getLogger("Error en configuracionEmail_s " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Email NO Enviado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void enviarEmail(Cliente cliente) throws Exception {
        String remitente = "jhanpoolx@gmail.com";
        String clave = "gjnvmxbawtflbdgd";

        String destinatario = cliente.getEMACLI();
        String asunto = "Bienvenido a DELIGHTCAKE";
        String contenido = "BUEN DÍA " + cliente.getNOMCLI() + " " + cliente.getAPEPATCLI()+ " " + cliente.getAPEMATCLI() + "\n"
                + "\n Su DNI es: " + cliente.getDNICLI() + "\n Muchas gracias por registrarse en DELIGHTCAKE";
        try {
            EmailS.createEmail(remitente,clave,destinatario,asunto,contenido);
        } catch (MessagingException ex) {
            System.out.println("Error en enviarEmail_S " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setEMACLI("cupejhanpool@gmail.com");
        cliente.setDNICLI("76473233");
        cliente.setNOMCLI("Jhanpool");
        cliente.setAPEPATCLI("Cupe");
        cliente.setAPEMATCLI("Perez");
        enviarEmail(cliente);
    }

}
