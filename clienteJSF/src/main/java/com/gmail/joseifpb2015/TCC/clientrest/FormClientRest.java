/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.clientrest;

import com.gmail.joseifpb2015.TCC.controladores.Form;
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
public class FormClientRest implements Serializable {

    private static final String urlSetting = "http://localhost:8084/api/usuarios/forms/";

    public List<Form> todos() throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting);
            return converte(responseBuffer.readLine());

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public Form buscarPorId(String id) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + id);
            System.err.println("host " + urlSetting + id);
            String dados = responseBuffer.readLine();
            return converte(dados).get(0);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Form();

    }

    public String salvar(Form form) throws MalformedURLException, IOException, JSONException {
        String conPost = conPost(form, "");
        //return null;
        return conPost;

    }

    public static String conPost(Form form, String uri) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + uri);
        String dados = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json;");
       // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(form);

        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            dados = rd.readLine();

        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
           // System.out.println("gravado"+response.toString());
           dados= response.toString();
        }
        return dados;
      //   return null;
    }

    public static String conPut(Form form, String uri) throws MalformedURLException, IOException {
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
        String json = g.toJson(form);
        System.err.println("leu " + form);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String dados = rd.readLine();
        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
        return null;
    }

    public static void aualizarForm(Form usuario) throws MalformedURLException, IOException {
        System.err.println("atualisando " + usuario);
        conPut(usuario, usuario.getId());
    }

    public void deleteForm(String id) throws MalformedURLException, IOException {
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

    private List<Form> converte(String dados) throws JSONException {

        List<Form> forms = new ArrayList<>();
        
        JSONArray jsonArr = new JSONArray(dados);
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            forms.add(gson.fromJson(jsonArr.getString(i), Form.class));

        }
        return forms;

    }

}
