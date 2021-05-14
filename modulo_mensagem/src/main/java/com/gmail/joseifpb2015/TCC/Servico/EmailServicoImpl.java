/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;
import com.gmail.joseifpb2015.TCC.email.EmailSimples;
import com.gmail.joseifpb2015.TCC.email.Mensagem;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class EmailServicoImpl implements EmailServico{
     
   
    public EmailServicoImpl() {
        
    }

    public String enviarEmail(Mensagem mensagem) {
        try {
           EmailSimples A = new EmailSimples();
           A.enviar(mensagem);
            return "código enviado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
                    
        }
       // return "Erro: Verifique se o email está correto e reenvie!";
    }

   
        
    }

    
    

