/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.exceptions;

import javax.faces.context.ExceptionHandler;

/**
 *
 * @author jose2
 */
//import java.io.PrintWriter;
//import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

//Inicialmente devemos implementar a classe CustomExceptionHandler que extende a classe ExceptionHandlerWrapper
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    //Obtém uma instância do FacesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();

    //Obtém um mapa do FacesContext
    final Map requestMap = facesContext.getExternalContext().getRequestMap();

    //Obtém o estado atual da navegação entre páginas do JSF
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

    //Declara o construtor que recebe uma exceptio do tipo ExceptionHandler como parâmetro
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }

    //Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    //Sobrescreve o método handle que é responsável por manipular as exceções do JSF
    @Override
    public void handle() throws FacesException {

        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            // Recupera a exceção do contexto
            Throwable exception = context.getException();
            int f = exception.getCause().getCause().hashCode();
            System.err.println("meu erro 33"+f);
                
            // Aqui tentamos tratar a exeção
            try {

                FacesMessage msg = new FacesMessage("Erro verifique os dados e tente novalente");
                
              
 
//                // Aqui você poderia por exemploinstanciar as classes StringWriter e PrintWriter
//                StringWriter stringWriter = new StringWriter();
//                // PrintWriter printWriter = new PrintWriter(stringWriter);
//                // exception.printStackTrace(printWriter);
//                // Por fim você pode converter a pilha de exceções em uma String
//                String message = stringWriter.toString();
//
//                // Aqui você poderia enviar um email com a StackTrace
//                // em anexo para a equipe de desenvolvimento
//
//                // e depois imprimir a stacktrace no log
//                exception.printStackTrace();
                // Coloca uma mensagem de exceção no mapa da request
                requestMap.put("exceptionMessage", exception.getMessage());
                //meu erro
                String erro404,erro500,erro403,pageErro=null,ismailsenha=null;
                
                erro403 = "403?faces-redirect=true";
                erro404 = "404?faces-redirect=true";
                erro500 = "500?faces-redirect=true";
                ismailsenha = "index?faces-redirect=true";
               if(exception != null && exception.getCause()!=null){
                Throwable erro = exception.getCause();
                String c = erro.toString();
                if(c.contains("java.net.ConnectException: Conexão recusada"))
                    pageErro = erro404;
                if(c.contains("java.lang.IndexOutOfBoundsException: Connection refused"))
                    pageErro = erro403;
                else
                if(c.contains("NotFoundException"))
                    pageErro = erro500;
                
                else pageErro = erro404;
             
             //pageErro =null;
             
               }
               else
                   //fim do teste   
                   pageErro = erro404;
                 FacesContext.getCurrentInstance().addMessage("erro",msg);
                //==========================================
                // Avisa o usuário do erro
               
                // Tranquiliza o usuário para que ele continue usando o sistema
               // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));

                // Seta a navegação para uma página padrão.
                
             //   navigationHandler.handleNavigation(facesContext, null, "pageError/erro?faces-redirect=true");
               navigationHandler.handleNavigation(facesContext, null, pageErro);


                // Renderiza a pagina de erro e exibe as mensagens
                facesContext.renderResponse();
            } finally {
                // Remove a exeção da fila
                iterator.remove();
            }
        }
        // Manipula o erro
        getWrapped().handle();
    }
}
