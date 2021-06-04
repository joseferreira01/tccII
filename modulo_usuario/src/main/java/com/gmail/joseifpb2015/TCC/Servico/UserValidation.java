package com.gmail.joseifpb2015.TCC.Servico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import com.gmail.joseifpb2015.TCC.entidades.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 *
 * @author jose2
 */
public class UserValidation {
    
    private final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
   // private final Pattern REGEX_SENHA_VALIDO = Pattern.compile("(([a-z])([A-Z])([@#$%])(\\d)){8,20})", Pattern.CASE_INSENSITIVE);

    public UserValidation() {
    }
       

    public Map<String, String> execute(Usuario usuario) {
        System.err.println("validado" + usuario.toString());

        Map<String, String> resultado = new HashMap<>();

        if (usuario == null) {
            return null;
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()
                || usuario.getNome().length()<7 ||usuario.getNome().length()>60) {
            resultado.put("nome", "Nome deve conter de 7 a 60 caracteres.");
        } else if (!usuario.getNome().matches("[A-Za-zÀ-ú0-9 ]+")) {
            resultado.put("nome", "Nome não deve conter sibolos especiais (% - $ _ # @, por exemplo).");
        }
       
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()
                || usuario.getSenha().length() < 8 &&  usuario.getSenha().matches(" ((\\\\d)*([a-z])*([A-Z])*([@#$%])*){8,20}")) {
            resultado.put("senha", "A senha deve conter no minimo 8 com letras numeros e caracteres especiais "
                    + "");
        }

//        if (resultado.isEmpty()) {
//            resultado = isEmailSenha = dao.isEmailSenha(usuario.getEmail(), usuario.getSenha());
//           
//       }
//        if (!resultado.get("email").isEmpty()) {
//            resultado.put("email", " email já cadastrado");
//        }
//        if (!isEmailSenha.get("senha").isEmpty()) {
//            resultado.put("senha", "senha invalida");
//        }
        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }

}
