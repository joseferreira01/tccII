/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
import com.gmail.joseifpb2015.TCC.clientrest.UserClientRest;
import com.gmail.joseifpb2015.TCC.utilitario.DadosPessoais;
import com.gmail.joseifpb2015.TCC.utilitario.Formacao;
import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import com.gmail.joseifpb2015.TCC.utilitario.Sexo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jose
 */
@Named
@SessionScoped
public class UserCotroladorLogado implements Serializable {

    @Inject
    private UserClientRest servico;
    @Inject
    private Mensagem msg;
    @Inject
    private EventoClientRest servicoEveRest;
    private Map<String, String> sexo;
    private Map<String, String> estados;
    private List<Convite> covites;
    private DadosPessoais dadosPessoais;
    private Formacao formacao;
    private Usuario usuario;
    private boolean isconvite = false;
    private boolean is = false;
    private int cont = 0;
    private Part part;
    private Endereco endereco;

    @PostConstruct
    public void posCon() {
        covites = new ArrayList<>();
        dadosPessoais = new DadosPessoais();
        formacao = new Formacao();
        sexo = new HashMap<>();
        endereco = new Endereco();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        //usuario = new Usuario();
        carregarConvites();
        carregaEstados();
        carregarConvites();
        carregarSexo();
    }

    public String atualizar() throws IOException {
        System.err.println("atualizando contro 2" + usuario);
        System.out.println("usuario " + usuario);
        servico.aualizarUsuario(usuario);
        msg.addMessage("Dados atualizados com sucesso!");
        return "/index?faces-redirect=true";

    }

    public String delete() throws IOException {

        servico.deleteUsuario(usuario.getId());
        msg.addMessage("Conta apagada com sucesso!");
        return "index?faces-redirect=true";

    }

    public String editar() {

        return "perfil?faces-redirect=true";

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public Map<String, String> getSexo() {
        return sexo;
    }

    public void setSexo(Map<String, String> sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String salveFoto() {
        try {
            byte[] arquivoByte = toByteArrayUsingJava(part.getInputStream());
            usuario.setFoto(base64(arquivoByte));
            servico.aualizarUsuario(usuario);
            msg.addMessage("Foto atualizada");
        } catch (IOException ex) {
            Logger.getLogger(UserCotroladorLogado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String aceitarConvite(String idConvite) {
        try {
            System.out.println("entre no controle acita convite");
            servicoEveRest.aceitarConvite(idConvite);
            carregarConvites();
            msg.addMessage("Convite aceito");

        } catch (JSONException ex) {
            msg.addMessage("erro");
        }
        return null;
    }

    public String recusarConvite(String idConvite) {
        try {
            servicoEveRest.regeitarConvite(idConvite);
            carregarConvites();
            msg.addMessage("Convite recusado");

        } catch (JSONException ex) {
            msg.addMessage("erro ");

        }
        return null;
    }

    public String salvarFormacao() {
        InfoAdicional infoAdicional = new InfoAdicional(formacao, "formacao");
        usuario.addInfo(infoAdicional);
        try {
            servico.aualizarUsuario(usuario);
        } catch (IOException ex) {
            msg.addMessage("erro dados pessoais!");
            return null;
        }
        msg.addMessage("salvo com sucesso!");
        return null;
    }

    public String salvarEndereco() {
        InfoAdicional infoAdicional = new InfoAdicional(endereco, "endereco");
        usuario.addInfo(infoAdicional);
        try {
            servico.aualizarUsuario(usuario);
        } catch (IOException ex) {
            msg.addMessage("erro dados pessoais!");
            return null;
        }
        msg.addMessage("salvo com sucesso!");
        return null;
    }

    public String salvarInfo() {
        InfoAdicional infoAdicional = new InfoAdicional(dadosPessoais, "dadosPessoais");
        usuario.addInfo(infoAdicional);
        try {
            servico.aualizarUsuario(usuario);
        } catch (IOException ex) {
            msg.addMessage("erro dados pessoais!");
            return null;
        }
        msg.addMessage("salvo com sucesso!");
        return null;
    }

    private void criaArquivo(byte[] bytes, String arquivo) {
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(arquivo);
            fos.write(bytes);

            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }
    

    public void carregaEstados() {
        estados = new HashMap<>();
        estados.put("PB", "Paraíba");
        estados.put("CE", "Ceará");
        estados.put("RN", "Rio grande do norte");

    }

    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public boolean isIsconvite() {
        return isconvite;
    }

    public void setIsconvite(boolean isconvite) {
        this.isconvite = isconvite;
    }

    public List<Convite> getCovites() {
        return covites;
    }

    public void setCovites(List<Convite> covites) {
        this.covites = covites;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    private List<Convite> convites(String dados) {
        try {
            System.err.println("convertendo convite "+ dados);
            List<Convite> convite = new ArrayList<>();
            JSONArray jsonArr = new JSONArray(dados);
            Gson gson = new GsonBuilder().create();
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                convite.add(gson.fromJson(jsonArr.getString(i), Convite.class));

            }
            System.err.println(convite);
            return convite;
        } catch (JSONException ex) {
            Logger.getLogger(UserCotroladorLogado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;

    }

    private void carregarConvites() {
        //System.out.println("chamando os convete "+usuario.getEmail());
        if(usuario==null){
            covites =Collections.EMPTY_LIST;
            return;
          
        }
            
        this.covites = convites(servicoEveRest.convites(usuario.getEmail()));
        if (covites.size() > 0) {
            this.isconvite = true;
            this.cont = covites.size();
        } else {
            this.cont = 0;
            this.isconvite = false;
        }
    }

    private void carregarSexo() {
        this.sexo.put(Sexo.MASCULINO.name(), Sexo.MASCULINO.name());
        this.sexo.put(Sexo.FEMININO.name(), Sexo.FEMININO.name());
        this.sexo.put(Sexo.OUTRO.name(), Sexo.OUTRO.name());
    }

    public byte[] toByteArrayUsingJava(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while (reads != -1) {
            baos.write(reads);
            reads = is.read();
        }
        return baos.toByteArray();
    }

    public String base64(byte[] bs) {
        return Base64.getEncoder().encodeToString(bs);
    }

    public byte[] conBytes(String code) {
        if (code != null) {
            return Base64.getDecoder().decode(code);
        } else {
            return null;
        }
    }

    public String decoder(String base64Image) {
        if (base64Image == null) {
            return null;
        }

        File dir = new File(System.getProperty("java.io.tmpdir"), usuario.getId());
        try (FileOutputStream imageOutFile = new FileOutputStream(dir.getPath())) {
            // Converting a Base64 String into Image byte array

            imageOutFile.write(conBytes(base64Image));

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        String resultado = dir.getPath();
        return resultado;

    }

    public String img() {
        System.err.println("voltando img");
        String s = decoder(usuario.getFoto());
        System.err.println("camin " + s);
        return s + ".jpeg";
    }
}
