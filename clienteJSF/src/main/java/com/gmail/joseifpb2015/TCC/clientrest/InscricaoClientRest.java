/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.clientrest;

import com.gmail.joseifpb2015.TCC.controladores.Inscricao;
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
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jose
 */
@Named
@ApplicationScoped
public class InscricaoClientRest implements Serializable {

    //private static final String urlSetting = getProp().getProperty("urluser");
    private static final String urlSetting = "http://localhost:8084/api/usuarios/inscricao";
   // private static final String urlSetting = "http://api-usuarios:8084/api/usuarios/inscricao";
    

    public List<Inscricao> todos() throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting);
            return converte(responseBuffer.readLine());

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(InscricaoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InscricaoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public Inscricao buscarPorId(String id) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + id);
            System.err.println("host " + urlSetting + id);
            String dados = responseBuffer.readLine();
            return converte(dados).get(0);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(InscricaoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InscricaoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Inscricao();

    }

    public static void salvar(Inscricao inscricao) throws MalformedURLException, IOException {
        conPost(inscricao, "");
        System.err.println("met sal rest user");
    }

    public static void conPost(Inscricao inscricao, String uri) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(inscricao);
        System.err.println("leu " + inscricao);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }

    }

    public static void conPut(Inscricao inscricao, String uri) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + uri);
        System.err.println("atualizand " + urlSetting + uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("PUT");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(inscricao);
        System.err.println("leu " + inscricao);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }

    }

    public static void aualizarUsuario(Inscricao inscricao) throws MalformedURLException, IOException {
        System.err.println("atualisando " + inscricao);
        conPut(inscricao, inscricao.getId());
    }

    public void deleteUsuario(String id) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = responseBuffer.readLine()) != null) {
            {
                sb.append(s);
            }
            System.err.println(sb.toString());

        }
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

    private List<Inscricao> converte(String dados) throws JSONException {

        List<Inscricao> inscricao = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(dados);
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            inscricao.add(gson.fromJson(jsonArr.getString(i), Inscricao.class));

        }
        return inscricao;

    }

  
   
  
}
