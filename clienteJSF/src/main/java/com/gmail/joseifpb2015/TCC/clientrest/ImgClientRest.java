/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.clientrest;
import com.gmail.joseifpb2015.TCC.controladores.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.json.JsonException;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jose
 */
@Named
@ApplicationScoped
public class ImgClientRest implements Serializable {

    private static final String urlSetting = "http://localhost:8084/api/usuarios/";

  
   

    public String img(String nameImg,String uri) throws IOException, JSONException {
        URL url = new URL(urlSetting+uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(nameImg);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String dados = rd.readLine();
        System.err.println("lendo dados red "+dados);
          
       
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
        System.err.println("usuari log "+nameImg.toString());

        return  dados;
    }
   
}
