/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.controller;

import com.google.gson.Gson;
import com.senai.alertar.dao.UsuarioDAO;
import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Usuario;
import com.senai.alertar.util.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MARI
 */
//@WebServlet("/Login")
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Connection conn = ConnectionFactory.getConnection();

        Usuario usuario = Usuario.getNewInstance(request.getParameter("userName"),
                request.getParameter("userEmail"),
                request.getParameter("userLogin"),
                request.getParameter("userPassword"));

        //Buscar usuário/senha
        UsuarioDAO login = new UsuarioDAO();
        usuario = login.buscarPorUsuario(conn, usuario);

        //Validar
        //OK? -> Redirecionar
        //NOK? -> Manter página
        String json = new Gson().toJson(usuario);
        response.getWriter().write(json); //usar como retorno da requisição
        
        //Acrescentar na estrutura JSON a URL que 
        
        conn.close();
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>Sucesso</body></html>");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // try {
            //processRequest(request, response);
            RequestDispatcher requestDispatcher;
            requestDispatcher = request.getRequestDispatcher("alerta.jsp");
            requestDispatcher.forward(request, response);
        //} catch (SQLException ex) {
       //     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    private void cadastrarUsuario(Connection conn, Usuario usuario) {
        UsuarioDAO login = new UsuarioDAO();
        //DataBaseObject dataBaseObject = (Usuario) usuario;
        login.inserir(conn, usuario);
        //usuario = UsuarioDAO.inserir(usuario);
    }

}
