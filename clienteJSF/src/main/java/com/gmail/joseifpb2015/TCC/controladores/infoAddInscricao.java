/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import com.gmail.joseifpb2015.TCC.clientrest.InscricaoClientRest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose
 */
@WebServlet(name = "infoAddInscricao", urlPatterns = {"/infoAddInscricao"})
public class infoAddInscricao extends HttpServlet {
    @Inject
    private InscricaoClientRest clientRest;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int  key = request.getParameterMap().keySet().size();
            
            Object[] keySet = request.getParameterMap().keySet().stream().toArray();
            Inscricao inscricao= new Inscricao();
            inscricao.setIdEvento(request.getParameter("keyevento"));
            
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            
            System.err.println("id do usuario "+usuario.getId());
            inscricao.setTituloEvento((String) request.getSession().getAttribute("tevento"));
            
            request.getSession().removeAttribute("tenvento");
            inscricao.setEmailUsuario(usuario.getEmail());
            List<InfoAdicional> ia = new ArrayList<>();
            request.removeAttribute("keyevento");
            for(int i=0;i<key;i++)
              if(request.getParameter( keySet[1].toString())!="keyevento")
                ia.add(new InfoAdicional<>(request.getParameter( keySet[1].toString()), keySet[i].toString()));
            /* TODO output your page here. You may use following sample code. */
            inscricao.setOutros(ia);
            clientRest.salvar(inscricao);
            response.sendRedirect("in.xhtml");
            
           
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
