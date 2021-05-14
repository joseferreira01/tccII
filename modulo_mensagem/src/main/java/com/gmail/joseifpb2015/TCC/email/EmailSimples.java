/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author jose
 */
public class EmailSimples {
   
	public void enviar(Mensagem mensagem) {
	Properties props = new Properties();
    /** Parâmetros de conexão com servidor Gmail */
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", 
    "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
 
    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() 
           {
                 return new PasswordAuthentication("joseferreiravieira123@gmail.com", 
                 "jose12345");
           }
      });
 
    /** Ativa Debug para sessão */
    session.setDebug(true);
 
    try {
 
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mensagem.getRemetente())); 
      //Remetente
 
      Address[] toUser = InternetAddress //Destinatário(s)
                 .parse(mensagem.getDestinatario());
      message.setRecipients(Message.RecipientType.TO, toUser);
      message.setSubject(mensagem.getAssunto()); //Assunto
      message.setText(mensagem.getCorpo());
      /**Método para enviar a mensagem criada*/
      Transport.send(message);
 
      System.out.println("Feito!!!");
 
     } catch (MessagingException e) {
        throw new RuntimeException(e);
    }	
            
	}

    
}
