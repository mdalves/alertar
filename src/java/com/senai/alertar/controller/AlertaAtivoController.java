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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet("/resolverAlerta")
public class AlertaAtivoController extends HttpServlet {

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
        //Conexão ao banco de dados
        Connection conn = ConnectionFactory.getConnection();

        //DAO responsavel
        AlertaDAO alertaDAO = new AlertaDAO();

        //obtendo o ID baseado na escolha do usuario
        int ale_id = Integer.parseInt(request.getParameter("rad_alerta"));

        //Buscando o alerta correto no banco de dados
        Alerta alerta = alertaDAO.buscarID(conn, ale_id);

        //verificando se o alerta foi encontrado
        if (alerta == null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");
                out.println("<h1>Alerta não encontrado no banco!</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }

        //alerta foi encontrado, então ajustar seu status e atualizar o banco
        alerta.setStatus("0"); //status "0" para dizer que ele está inativo agora

        //fazendo update no banco e recebendo variável boolean de controle
        boolean sucesso = alertaDAO.atualizarAtivo(conn, alerta);

        //direcionando a resposta baseado no controle
        if (sucesso) {

            //caso o update no banco tenha sido bem sucedido, notificar usuarios de que este alerta foi resolvido
            int cod = enviarAlerta(alerta);

            if (cod == 200) {
//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(alerta);
//                jsonElement.getAsJsonObject().addProperty("mensagem", "Alerta resolvido!");
//                String json = gson.toJson(jsonElement);
//                response.getWriter().write(json);
                //Caso tudo saia como planejado, gera uma página tosca de resposta
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h1>Alerta resolvido!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h1>Erro ao enviar dados para os clientes android!</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");
                out.println("<h1>Erro ao atualizar no banco de dados!</h1>");
                out.println("</body>");
                out.println("</html>");
            }

        }
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
        //AQUI O CONTEUDO É ADEQUADO PARA QUE O CLIENTE ANDROID ENTENDA QUE ISSO É UMA RESOLUÇÃO DE ALERTA
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        
        GrupoDAO grupoDAO = new GrupoDAO();
        List<? extends DataBaseObject> lst = grupoDAO.listar(ConnectionFactory.getConnection(), alerta.getGrupo());
        
        //Assumindo que não haverá (e não deve haver) id duplicado no DB
        Grupo grp = (Grupo) lst.get(0);
        alerta.setGrupo(grp);
        
        JsonObject content = (JsonObject) Json.createObjectBuilder()
                .add("title", alerta.getTitulo())
                .add("body", alerta.getCorpo())
                .add("id", alerta.getId())
                .add("solve", true)
                .add("nivel", alerta.getNivelAlerta())
                .add("dateTime", dateFormat.format(date)).build();
        //Acrescentar parâmetro "local" com o bairro 

        JsonObject jsonPackage = Json.createObjectBuilder()
                //.add("to", "/topics/alertar")
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
