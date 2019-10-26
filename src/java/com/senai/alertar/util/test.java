/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.util;

import com.senai.alertar.controller.LoginController;
import com.senai.alertar.dao.AlertaDAO;
import com.senai.alertar.model.Alerta;
import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Mensagem;
import com.senai.alertar.model.Substancia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MARI
 */
public class test {
    
    public static void main (String[] args){
        
        Connection conn = null;
        conn = ConnectionFactory.getConnection();
        AlertaDAO alertaDao = new AlertaDAO();
        List<? extends DataBaseObject> lista = alertaDao.listarAtivo(conn);
        for (Object o : lista){
            Alerta al = (Alerta) o;
            System.out.println(al.getId());
            al.setStatus("0");
            boolean bool = alertaDao.atualizarAtivo(conn, al);
            System.out.println(bool);
        }
        
        lista = alertaDao.listarAtivo(conn);
        
        if(lista.isEmpty())
            System.out.println("yeah");
     
//        @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Connection conn = ConnectionFactory.getConnection();
//            System.out.println("Conexão aberta!");
//            conn.close();
////            String str = String.valueOf(conn == null);
////            PrintWriter p = response.getWriter();
////            p.println("<html><body>" + str + "</body></html>");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IOException ex) {
////            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
        
    }
    
}
