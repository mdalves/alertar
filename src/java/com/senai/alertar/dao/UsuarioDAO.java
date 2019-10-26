/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.dao;

import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Usuario;
import com.senai.alertar.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MARI
 */
public class UsuarioDAO implements GenericDAO {

//    public boolean inserir(Connection conn, Usuario usuario) {
//        PreparedStatement statement = null;
//        boolean action = false;
//        try {
//            String sql = "INSERT INTO ALERTAR.USUARIO "
//                    + "("
//                    + " USU_ID,    "
//                    + " USU_NOME,  "
//                    + " USU_EMAIL, "
//                    + " USU_LOGIN, "
//                    + " USU_SENHA  "
//                    + ") VALUES (  "
//                    + "NEXTVAL"
//                    + "('alertar.\"usuario_usu_id_seq\"'), ? ,?, ?, ?)";
//            statement = conn.prepareStatement(sql);
//            statement.setString(1, usuario.getNome());
//            statement.setString(2, usuario.getEmail());
//            statement.setString(3, usuario.getLogin());
//            statement.setString(4, usuario.getSenha());
//
//            int row = statement.executeUpdate();
//            if (row > 0) {
//                action = true;
//            }
//            return action;
//
//        } catch (SQLException ex) {
//            return action;
//        } finally {
//            try {
//                statement.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    public Usuario buscarPorUsuario(Connection conn, Usuario usuario) {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT usu_id, usu_nome, usu_email FROM alertar.usuario WHERE usu_login = ? AND usu_senha = ?;";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                usuario.setNome(resultSet.getString("usu_nome"));
                usuario.setId(Integer.parseInt(resultSet.getString("usu_id")));
                usuario.setEmail(resultSet.getString("usu_email"));
            }

            return usuario;

        } catch (SQLException ex) {
            usuario = new Usuario();
            return usuario;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean inserir(Connection conn, DataBaseObject dataBaseObject) {
        PreparedStatement statement = null;
        Usuario usuario = (Usuario) dataBaseObject;
        boolean action = false;
        try {
            String sql = "INSERT INTO ALERTAR.USUARIO "
                    + "("
                    + " USU_ID,    "
                    + " USU_NOME,  "
                    + " USU_EMAIL, "
                    + " USU_LOGIN, "
                    + " USU_SENHA  "
                    + ") VALUES (  "
                    + "NEXTVAL"
                    + "('alertar.\"usuario_usu_id_seq\"'), ? ,?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getSenha());

            int row = statement.executeUpdate();
            if (row > 0) {
                action = true;
            }
            return action;

        } catch (SQLException ex) {
            return action;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean atualizar(Connection conn, DataBaseObject dataBaseObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Connection conn, DataBaseObject dataBaseObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DataBaseObject> listar(Connection conn, DataBaseObject dataBaseObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
