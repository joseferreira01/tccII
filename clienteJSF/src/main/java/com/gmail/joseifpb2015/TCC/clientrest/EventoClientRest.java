/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.clientrest;

import com.gmail.joseifpb2015.TCC.controladores.Atividade;
import com.gmail.joseifpb2015.TCC.controladores.Convite;
import com.gmail.joseifpb2015.TCC.controladores.Evento;
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
public class EventoClientRest implements Serializable {

    private static final String urlSetting = "http://api-eventos:8081/api/";

    public List<Evento> todos() throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos");
            // System.err.println("volta capa "+responseBuffer.readLine());
            StringBuffer dados = new StringBuffer(responseBuffer.readLine());
            return converte(dados);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public List<Convite> convitesOrganizado(String idEvento) throws JSONException {
        System.err.println("dados convite");
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos/convite/remetente/" + idEvento);
            return converteConvite(responseBuffer.readLine());

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public String aceitarConvite(String idEvento) throws JSONException {
        System.err.println("dados convite");
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos/convite/aceitar/" + idEvento);
            return responseBuffer.readLine();

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
        return null;
    }

    public String regeitarConvite(String idEvento) throws JSONException {
        System.err.println("dados convite");
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos/convite/recusar/" + idEvento);
            return responseBuffer.readLine();

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
        return null;
    }

    public List<Evento> eventosPorOrganizador(String idOrganizador, int pag) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos/" + idOrganizador + "?page=" + pag);
            StringBuffer dados = new StringBuffer(responseBuffer.readLine());
            return converte(dados);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public String convites(String email) {
        System.err.println("lslslsls e");
        String dados = null;
        try {
            URL url = new URL(urlSetting + "eventos/convite/convidado/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Define que a conexão pode enviar informações e obtê-las de volta:
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            // connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.connect();
            Gson g = new Gson();
            String json = g.toJson(email);
            try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
                //outputStream.write(json.getBytes("UTF-8"));
                outputStream.write(json);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            dados = rd.readLine();

        } catch (IOException e) {

        }

        return dados;

    }

    public List<Atividade> listAtividades(String iDevento, int pag) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "atividades/" + iDevento);
            return converteAt(responseBuffer.readLine());

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        return Collections.EMPTY_LIST;
    }

    public Evento buscarPorId(String id) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos" + id);
            System.err.println("host " + urlSetting + id);
            StringBuffer dados = new StringBuffer(responseBuffer.readLine());
            return converte(dados).get(0);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Evento();

    }
    public Evento eventoKey(String id) throws JSONException {

        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "eventos/key/" + id);
            System.err.println("host " + urlSetting + id);
            StringBuffer dados = new StringBuffer(responseBuffer.readLine());
            return converte(dados).get(0);

//           
        } catch (MalformedURLException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventoClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Evento();

    }

    public void salvarEvento(StringBuffer json) throws MalformedURLException, IOException {
        conPost(json, "eventos/");
    }

    public void salvarAtividade(StringBuffer json) throws MalformedURLException, IOException {
        conPost(json, "atividades/");
    }

    public static void conPost(StringBuffer json, String uri) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.connect();

        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            System.err.println("jso " + json.toString());
            outputStream.write(json.toString());
            System.err.println("json client " + json.toString());
        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }

    }

    public void aualizarevento(Evento evento) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting +"eventos/"+ evento.getId());
        System.err.println("atualizand " + urlSetting + evento.getId());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Define que a conexão pode enviar informações e obtê-las de volta:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("PUT");
        connection.connect();
        Gson g = new Gson();
        String json = g.toJson(evento);
        System.err.println("leu " + evento);
        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
            //outputStream.write(json.getBytes("UTF-8"));
            outputStream.write(json);
        }
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
        }
    }

    public void delete(String caminho, String id) throws MalformedURLException, IOException {
        URL url = new URL(urlSetting + caminho + id);
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

    private List<Evento> converte(StringBuffer dados) throws JSONException {

        List<Evento> eventos = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(dados.toString());
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            eventos.add(gson.fromJson(jsonArr.getString(i), Evento.class));

        }
        return eventos;

    }

    private List<Atividade> converteAt(String dados) throws JSONException {

        List<Atividade> atividades = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(dados);
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            atividades.add(gson.fromJson(jsonArr.getString(i), Atividade.class));

        }
        return atividades;

    }

    private List<Convite> converteConvite(String dados) throws JSONException {
        System.err.println("convertendo convite");
        List<Convite> convite = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(dados);
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            convite.add(gson.fromJson(jsonArr.getString(i), Convite.class));

        }
        System.err.println(convite);
        return convite;

    }

//    public Usuario logar(Usuario u,String uri) throws IOException, JSONException {
//        URL url = new URL(urlSetting+uri);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // Define que a conexão pode enviar informações e obtê-las de volta:
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestProperty("Content-Type", "application/json");
//        // connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestMethod("POST");
//        connection.connect();
//        Gson g = new Gson();
//        String json = g.toJson(u);
//        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
//            //outputStream.write(json.getBytes("UTF-8"));
//            outputStream.write(json);
//        }
//        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String dados = rd.readLine();
//        System.err.println("lendo dados red "+dados);
//          
//       
//        if (connection.getResponseCode() != 200) {
//            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
//        }
//        System.err.println("usuari log "+u.toString());
//
//        return  converte(dados).get(0);
//    }
//    public String reeviarEvail(Usuario u,String uri) throws IOException, JSONException {
//        URL url = new URL(urlSetting+uri);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // Define que a conexão pode enviar informações e obtê-las de volta:
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestProperty("Content-Type", "application/json");
//        // connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestMethod("POST");
//        connection.connect();
//        Gson g = new Gson();
//        String json = g.toJson(u);
//        try (OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream())) {
//            //outputStream.write(json.getBytes("UTF-8"));
//            outputStream.write(json);
//        }
//        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String dados = rd.readLine();
//        System.err.println("lendo dados red "+dados);
//          
//       
//        if (connection.getResponseCode() != 200) {
//            throw new RuntimeException("HTTP GET erro code: " + connection.getResponseCode());
//        }
//        System.err.println("usuari log "+u.toString());
//
//        return dados;
//    }
//
//    public void habilitar(Usuario u) throws IOException, JSONException {
//        
//        System.err.println("to no ha "+urlSetting+"habilitar");
//        logar(u,"habilitar");
//       // conPost(u, urlSetting+"habilitar");
//    }
//
//   
    public void apagarAtividade(String id) throws IOException {
        delete("atividades/", id);

    }

    public void salvarConvite(StringBuffer json) throws IOException {
        conPost(json, "eventos/convite");
    }
}
