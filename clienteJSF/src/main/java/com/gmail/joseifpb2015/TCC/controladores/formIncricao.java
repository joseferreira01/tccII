/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
import com.gmail.joseifpb2015.TCC.clientrest.FormClientRest;
import com.gmail.joseifpb2015.TCC.entidades.Campo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.ws.rs.client.Entity.json;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jose
 */
@WebServlet(name = "formIncricao", urlPatterns = {"/formIncricao"})
public class formIncricao extends HttpServlet {

    @Inject
    private EventoClientRest ecr;
    @Inject
    private FormClientRest formClientRest;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Campo c = (Campo) request.getSession().getAttribute("campo");
            Campo text = new Campo();
            text.setOpcoes(Collections.EMPTY_LIST);
            text.setId("t1");
            text.setLabel("nome");
            text.setNome("nome");
            text.setTipo("text");
            text.setDica("Digite nome");
            Campo text2 = new Campo();
            text2.setOpcoes(Collections.EMPTY_LIST);
            text2.setId("t2");
            text2.setLabel("nome2");
            text2.setNome("nome2");
            text2.setTipo("text");
            text2.setDica(" campo dois");

            Campo CPF = new Campo();
            CPF.setOpcoes(Collections.EMPTY_LIST);
            CPF.setId("CPF");
            CPF.setLabel("CPF");
            CPF.setNome("CPF");
            CPF.setTipo("text");
            CPF.setDica("000.000.000-00");
            CPF.setPattern("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");

            Campo date = new Campo();
            text.setOpcoes(Collections.EMPTY_LIST);
            date.setId("date");
            date.setLabel("date");
            date.setNome("date");
            date.setTipo("date");
            date.setDica("Digite date");

            Campo number = new Campo();
            number.setOpcoes(Collections.EMPTY_LIST);
            number.setId("number");
            number.setLabel("number");
            number.setNome("number");
            number.setTipo("number");
            number.setDica("Digite number");

            Campo radio = new Campo();
            radio.setOpcoes(Collections.EMPTY_LIST);
            radio.setId("radio");
            radio.setLabel("radio");
            radio.setNome("radio");
            radio.setTipo("radio");

            Campo checkbox = new Campo();
            checkbox.setOpcoes(Collections.EMPTY_LIST);
            checkbox.setId("checkbox");
            checkbox.setLabel("checkbox");
            checkbox.setNome("checkbox");
            checkbox.setTipo("checkbox");
            checkbox.setDica("Digite nome");

            Campo textarea = new Campo();
            textarea.setOpcoes(Collections.EMPTY_LIST);
            textarea.setId("t1");
            textarea.setLabel("descrição");
            textarea.setNome("textarea");
            textarea.setTipo("textarea");
            textarea.setDica("Digite a descrição");

            List<String> opcoes = new ArrayList<>();
            opcoes.add("PB");
            opcoes.add("SP");
            opcoes.add("RJ");

            Campo endereco = new Campo();
            endereco.setOpcoes(opcoes);
            endereco.setId("date");
            endereco.setLabel("endereco");
            endereco.setNome("endereco");
            endereco.setTipo("endereco");
            endereco.setDica("Digite date");

            //select
            Campo select = new Campo();
            select.setOpcoes(opcoes);
            select.setId("t");
            select.setLabel("idade");
            select.setNome("idade");
            select.setTipo("select");
            List<Campo> campos = new ArrayList<>();
            campos.add(text);
            campos.add(text2);
            campos.add(textarea);
            campos.add(select);
            campos.add(checkbox);
            campos.add(radio);
            campos.add(date);
            campos.add(number);
            campos.add(endereco);
            campos.add(CPF);
            //  buscarCampos(request);
            campos = buscarCampos(request);
          

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <meta charset=\"utf-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<title>Informações solicidadas pela organização</title>");
            out.println("<link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css integrity=sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T crossorigin=anonymous> ");

            out.println("</head>");

            out.println("<body>");

            out.println("<div class=container-fluid >");
            out.println("<form action=/clienteJSF/infoAddInscricao?keyevento="+request.getParameter("idevento")+" method=post>");
            // orp9irrri
            
            System.err.println("camps de cast " + campos.get(0));
             
            for (Campo c: campos) {
               // Campo c = new Gson().fromJson(campos.get(i).toString(), Campo.class);
                System.out.println("campo " + c.gerarHtml());
                if (c.getTipo().equalsIgnoreCase("radio") || c.getTipo().equalsIgnoreCase("checkbox")) {
                    out.println("<label for=" + c.getId() + ">" + c.getLabel() + "</label>");

                    out.println(c.gerarHtml());

                } else {
                    out.println(c.gerarHtml());
                }
            }
//  out.println(" "+"form iscri "+request.getParameter("idevento")+"");
            out.println("<br /> <input type=submit value=Salvar  class=\"btn btn-success btn-lg btn-block \" /> <br/> <br/>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("<script src=https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js integrity=sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM crossorigin=anonymous> </script> ");

            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(formIncricao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(formIncricao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Campo> buscarCampos(HttpServletRequest request) throws JSONException {
        
    
            Evento evento = ecr.eventoKey(request.getParameter("idevento"));
            request.setAttribute("evento", evento.getId());
              request.getSession().setAttribute("tevento",evento.getTitulo() );
           return formClientRest.buscarPorId(evento.getForm()).getCampos();
           
            
            
           
           
//        try {
//            //        HttpSession session = request.getSession();
////        Evento evento = (Evento) session.getAttribute("evento");
//            Evento evento = ecr.eventoKey(request.getParameter("idevento"));
//            List<InfoAdicional> infoAdicionais = evento.getInfoAdicionais();
//            for (InfoAdicional e : infoAdicionais) {
//                List<Campo> l = new ArrayList();
//                if (e.getKey().equalsIgnoreCase("form")) {
//
//                    return (List<Campo>) e.getInfo();
//                }
//
//            }
//            return null;
      
    
} 
}
    
    
