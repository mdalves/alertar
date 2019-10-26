/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.senai.alertar.dao.AlertaDAO;
import com.senai.alertar.dao.GrupoDAO;
import com.senai.alertar.dao.MensagemDAO;
import com.senai.alertar.dao.SubstanciaDAO;
import com.senai.alertar.model.Alerta;
import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Mensagem;
import com.senai.alertar.model.Substancia;
import com.senai.alertar.util.ConnectionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mari
 */
public class MensagemController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Connection conn = ConnectionFactory.getConnection();
            List<? extends DataBaseObject> lstSubstancia = null;
            List<? extends DataBaseObject> lstGrupo = null;
            //Dados
            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo(request.getParameter("titulo"));
            mensagem.setDescricao(request.getParameter("descricao"));
            mensagem.setNivelAlerta(Integer.parseInt(request.getParameter("nivel")));
            mensagem.setAtivo(Boolean.TRUE);

            //SUBSTÂNCIA
            Substancia substancia = new Substancia();
            substancia.setNome(request.getParameter("substancia"));
            SubstanciaDAO substanciaDAO = new SubstanciaDAO();
            lstSubstancia = substanciaDAO.listar(conn, substancia);
            //Adicionanr todos os dados do objeto SUBSTÂNCIA
            for (DataBaseObject dataBaseObject : lstSubstancia) {
                substancia = (Substancia) dataBaseObject;
            }
            mensagem.setSubstancia(substancia);

            //GRUPO
            Grupo grupo = new Grupo();
            grupo.setId(1); //teste
            GrupoDAO grupoDAO = new GrupoDAO();
            lstGrupo = grupoDAO.listar(conn, grupo);

            for (DataBaseObject dataBaseObject : lstGrupo) {
                grupo = (Grupo) dataBaseObject;
            }
            mensagem.setGrupo(grupo);
            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(mensagem);
            switch (request.getParameter("action")) {
                case "cadastrar":
                    gson = new Gson();
                    jsonElement = gson.toJsonTree(mensagem);
                    if (inserirMensagem(conn, mensagem)) {
                        jsonElement.getAsJsonObject().addProperty("status", "sucesso");
                    } else {
                        jsonElement.getAsJsonObject().addProperty("status", "erro");
                    }
                    break;
                case "listar":
                    List<? extends DataBaseObject> lstMensagem = listarMensagem(conn, mensagem);
                    for (DataBaseObject dataBaseObject : lstMensagem) {
                        mensagem = (Mensagem) dataBaseObject;
                    }
                    if (mensagem != null) {
                        //Alerta alerta = new Alerta();
                        Alerta alerta = Alerta.getNewInstance(mensagem,
                                null,
                                grupo, 
                                null,
                                substancia,
                                request.getParameter("titulo"),
                                request.getParameter("descricao"),
                                "1",
                                mensagem.getNivelAlerta()); //Mensagem existe? SIM -> Usar. NÃO -> Cadastrar.

                        gson = new Gson();
                        AlertaDAO alertaDao = new AlertaDAO();
                        int codigo = alertaDao.inserirRetornandoId(conn, alerta);
                        alerta.setId(codigo);
                        jsonElement = gson.toJsonTree(mensagem);
                        if (enviarAlerta(alerta) == 200) {
                            jsonElement.getAsJsonObject().addProperty("mensagem", "sucesso");                            
                        } else {
                            jsonElement.getAsJsonObject().addProperty("mensagem", "erro");
                        }
                    }
                    break;
                case "enviar":
                    break;
                default:
                    break;
            }

            String json = gson.toJson(jsonElement);
            response.getWriter().write(json);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MensagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public boolean inserirMensagem(Connection conn, Mensagem mensagem) {
        MensagemDAO mensagemDAO = new MensagemDAO();
        return mensagemDAO.inserir(conn, mensagem);
    }

    public List<? extends DataBaseObject> listarMensagem(Connection conn, Mensagem mensagem) {
        MensagemDAO mensagemDAO = new MensagemDAO();
        List<? extends DataBaseObject> lstMensagens = mensagemDAO.listar(conn, mensagem);
        return lstMensagens;
    }

    public Integer enviarAlerta(Alerta alerta) throws MalformedURLException, IOException {

        // Method to send Notifications from server to client end.
        //Dados do Firebase
        String authAPIKey = "AAAAaAl0vZc:APA91bF6ijpcEMDaZaBK4bYDfDsYRL25A1-DW04LAP4q1fZf7YbqivLT-BOxt5q5j36L7kDZ92o47y8NbnVOJMpFKe3h1odgRyHrWH2zDVRyLBMQb8FQWxMRZijN_922q53F7drjlRXl"; // Your FCM AUTH key
        String FCMurl = "https://fcm.googleapis.com/fcm/send";

        //Conexão com Firebase
        URL url = new URL(FCMurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authAPIKey);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        //Conteúdo da mensagem destinada ao Topics
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        JsonObject content = (JsonObject) Json.createObjectBuilder()
                .add("title", alerta.getMensagem() == null ? alerta.getTitulo() : alerta.getMensagem().getTitulo())
                .add("body", alerta.getMensagem() == null ? alerta.getCorpo() : alerta.getMensagem().getDescricao())
                .add("id", alerta.getId())
                .add("solve", false)
                .add("nivel", alerta.getNivelAlerta())
                .add("dateTime", dateFormat.format(date)).build();
        //Acrescentar parâmetro "local" com o bairro 

        JsonObject jsonPackage = Json.createObjectBuilder()
                .add("to", "/topics/" + (alerta.getMensagem() == null ? alerta.getGrupo().getTopic() : alerta.getMensagem().getGrupo().getTopic())) //colocar no plural quando for mensagem de grupo
                .add("data", content).build();

        System.out.print(jsonPackage.toString());

        //Envio da resposta
        OutputStreamWriter outPutStreamWriter = new OutputStreamWriter(conn.getOutputStream(), Charset.forName("UTF-8"));
        outPutStreamWriter.write(jsonPackage.toString());
        outPutStreamWriter.flush();
        outPutStreamWriter.close();

        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return responseCode;
    }

}
