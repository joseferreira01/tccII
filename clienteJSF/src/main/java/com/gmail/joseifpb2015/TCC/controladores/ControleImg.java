///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gmail.joseifpb2015.TCC.controladores;
//
//import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
//import com.google.gson.Gson;
//import java.io.IOException;
//import javax.enterprise.context.RequestScoped;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.json.JSONException;
//import org.primefaces.model.UploadedFile;
//
///**
// *
// * @author jose
// */
//@Named
//@RequestScoped
//public class ControleImg {
//     private UploadedFile file;
//     @Inject
//     private EventoClientRest servico;
//     private Evento evento;
//     public void pos(){
//         evento = new Evento();
//          evento = (Evento) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//            System.err.println("evento da sesao "+evento);
//    }
//
// 
//    public UploadedFile getFile() {
//        return file;
//    }
// 
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
//     
//    public void upload() {
//        if(file != null) {
//            byte[] foto = file.getContents();
//            System.err.println("FOTO UP"+foto.length);
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//     
//    public void handleFileUpload(FileUploadEvent event) throws IOException {
//         UploadedFile uploadedFile = event.getFile();
//        System.err.println("FOTO "+uploadedFile.getFileName());
//        FacesMessage msg = new FacesMessage("Succesful",
//                event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        
//
//        
//            
//            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//            //System.err.println("usuario da sesao "+usuario);
//           
//            //System.out.println("evento criado "+evento.getEndereco().getUF());
//            evento.setCapa(event.getFile().getContents());
//            Gson g = new Gson();
//            StringBuffer json = new StringBuffer();
//            json.append(g.toJson(evento));
//            System.out.println("json evento -> " + json);
//            servico.salvarEvento(json);
//            
//    }
//    
//    
//    
//
//  
//}
