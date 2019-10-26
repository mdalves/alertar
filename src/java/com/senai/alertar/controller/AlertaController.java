/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.senai.alertar.dao.AlertaDAO;
import com.senai.alertar.model.Alerta;
import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Mensagem;
import com.senai.alertar.model.Substancia;
import com.senai.alertar.util.ConnectionFactory;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mari
 */
@WebServlet("/Alerta")
public class AlertaController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        Connection conn = ConnectionFactory.getConnection();

        String grupo_str = request.getParameter("grupo");
        String[] grupo_parts = grupo_str.split("-");
        
        Grupo grupo = new Grupo();
        grupo.setTopic(grupo_parts[0]);
        grupo.setId(Integer.parseInt(grupo_parts[1]));

        Alerta alerta = Alerta.getNewInstance(Mensagem.getNewInstance(-1, null, null, null, false, null, null),
                null,
                grupo, 
                null,
                Substancia.getNewInstance(-1, null, null, null, -1),
                request.getParameter("titulo"),
                request.getParameter("descricao"),
                "1",
                3); //Mensagem existe? SIM -> Usar. NÃO -> Cadastrar.

        AlertaDAO alertaDao = new AlertaDAO();
        int codigo = alertaDao.inserirRetornandoId(conn, alerta);
        alerta.setId(codigo);

        int responseCode = enviarAlerta(alerta);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(alerta);

        if (responseCode == 200) {
            jsonElement.getAsJsonObject().addProperty("mensagem", "sucesso");
        } else {
            jsonElement.getAsJsonObject().addProperty("mensagem", "erro");
        }
        String json = gson.toJson(jsonElement);
        response.getWriter().write(json);
        conn.close();
        //Inserir no banco de dados quando preencher para "enviar mensagem manual"

        //
        //Gravar log de envio. 200 = sucesso. Outros = erro.
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
                .add("title", alerta.getTitulo())
                .add("body", alerta.getCorpo())
                .add("id", alerta.getId())
                .add("solve", false)
                .add("nivel", alerta.getNivelAlerta())
                .add("dateTime", dateFormat.format(date)).build();
        //Acrescentar parâmetro "local" com o bairro 

        JsonObject jsonPackage = Json.createObjectBuilder()
                .add("to", "/topics/" + alerta.getGrupo().getTopic()) //colocar no plural quando for mensagem de grupo
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

    public void inserirAlerta(Connection conn, Alerta alerta) {
        AlertaDAO alertaDAO = new AlertaDAO();
        alertaDAO.inserir(conn, alerta);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlertaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlertaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
