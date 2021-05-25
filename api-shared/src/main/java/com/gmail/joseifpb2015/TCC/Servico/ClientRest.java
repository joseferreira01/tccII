/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.json.JSONException;
import org.springframework.stereotype.Service;

/**
 *
 * @author laerton
 */
@Service
public class ClientRest<T> implements Serializable, ClientRestFull<T> {

    @Override
    public String enviarMensagem(T obj, Class<T> type, String uri) throws IOException, JSONException {
        URL url = new URL(uri);
        System.out.println("url emiS "+url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(obj);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String dados = rd.readLine();
//        System.err.println("lendo dados red " + dados);

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
//        System.err.println("usuari log " + obj.toString());

        return dados;
    }
    @Override
     public T buscarPorId(String id,String uri, Class<T> type ) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(uri + id);
            System.err.println("host " + uri+ id);
            String dados = responseBuffer.readLine();
            return (T) converte(dados, type);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T) new Object();

    }
     private static BufferedReader getMetodo(String urlcomple) throws MalformedURLException, IOException {
        URL url = new URL(urlcomple);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
        return new BufferedReader(new InputStreamReader((connection.getInputStream())));

    }

    private String converte(String dados, Class<T> type) throws JSONException {
        System.err.println("dado do conve ");

     
       
        Gson gson = new GsonBuilder().create();
        

        
        return gson.fromJson(dados, String.class);

    }

}
