/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.validadores;
import com.gmail.joseifpb2015.TCC.controladores.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class UserValidation {

    private final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserValidation() {
    }

    public Map<String, String> execute(Usuario usuario) {
        System.err.println("validado" + usuario.toString());

        Map<String, String> resultado = new HashMap<>();

        if (usuario == null) {
            return null;
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()
                || usuario.getNome().length()<10 ||usuario.getNome().length()>60) {
            resultado.put("nome", "Nome deve conter de 10 a 60 caracteres.");
        } else if (!usuario.getNome().matches("[A-Za-zÀ-ú0-9 ]+")) {
            resultado.put("nome", "Nome não deve conter sibolos especiais (% - $ _ # @, por exemplo).");
        }
        
        

        if (usuario.getEmail() == null
                || usuario.getEmail().trim().isEmpty()
                || !REGEX_EMAIL_VALIDO.matcher(usuario.getEmail()).find()) {
            resultado.put("email", "Informe um email válido: exemplo fulano@gmail.com .");

        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()
                || usuario.getSenha().length() < 6 || usuario.getSenha().length() > 14) {
            resultado.put("senha", "A senha deve conter no minimo 6 e no maximo 14 digitos.");
        }


        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }

}
