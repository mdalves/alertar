/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.dao;

import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Substancia;
import com.senai.alertar.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mari
 */
public class GrupoDAO implements GenericDAO {

    @Override
    public boolean inserir(Connection conn, DataBaseObject dataBaseObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<? extends DataBaseObject> listar(Connection conn, DataBaseObject dataBaseObject) {
        PreparedStatement statement = null;
        List<? extends DataBaseObject> lstData = null;
        List<Grupo> lstGrupo = new ArrayList<Grupo>();
        Grupo grupo = (Grupo) dataBaseObject;
        try {
            String sql = "SELECT gru_id, gru_topic, gru_descricao, gru_codigo FROM ALERTAR.grupo WHERE gru_id = ?";
            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setInt(1, grupo.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                grupo = new Grupo();
                grupo.setId(Integer.parseInt(resultSet.getString("gru_id")));
                grupo.setTopic(resultSet.getString("gru_topic"));
                grupo.setDescricao(resultSet.getString("gru_descricao"));
                
                if (grupo != null) {
                    lstGrupo.add(grupo);
                }
            }
            lstData = lstGrupo;
            return lstData;

        } catch (SQLException ex) {
            return lstData;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
