/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.dao;

import com.senai.alertar.model.Alerta;
import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Mensagem;
import com.senai.alertar.model.Substancia;
//import com.senai.alertar.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mari
 */
public class AlertaDAO implements GenericDAO {

    @Override
    public boolean inserir(Connection conn, DataBaseObject dataBaseObject) {
        Alerta alerta = (Alerta) dataBaseObject;
        PreparedStatement statement = null;
        boolean action = false;
        try {
            String sql = "INSERT INTO ALERTAR.ALERTA "
                    + "("
                    + " ALE_ID,    "
                    + " ALE_DATA,  "
                    + " MSG_ID, "
                    + " SUB_ID,  "
                    + " GRU_ID,  "
                    + " ALE_TITULO,  "
                    + " ALE_DESCRICAO,  "
                    + " ALE_STATUS,  "
                    + " ALE_NIVEL_ALERTA "
                    //+ " ALE_XML  "
                    //+ " SEN_ID, "
                    //+ " USU_ID,  "
                    + ") VALUES (  "
                    + "NEXTVAL('alertar.\"alerta_ale_id_seq\"'), "
                    + "CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, alerta.getMensagem().getId());
            statement.setInt(2, alerta.getSubstancia().getId());
            //statement.setInt(3, alerta.getSensor().getId());
            statement.setInt(3, alerta.getGrupo().getId());
            //statement.setInt(5, alerta.getUsuario().getId());
            statement.setString(4, alerta.getTitulo());
            statement.setString(5, alerta.getCorpo());
            statement.setString(6, alerta.getStatus());
            statement.setInt(7, alerta.getNivelAlerta());            
            
            int row = statement.executeUpdate();
            if (row > 0) {
               action = true;
            }
            return action;

        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int inserirRetornandoId(Connection conn, DataBaseObject dataBaseObject) {
        Alerta alerta = (Alerta) dataBaseObject;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO ALERTAR.ALERTA "
                    + "("
                    + " ALE_ID,    "
                    + " ALE_DATA,  "
                    + " MSG_ID, "
                    + " SUB_ID,  "
                    + " GRU_ID,  "
                    + " ALE_TITULO,  "
                    + " ALE_DESCRICAO,  "
                    + " ALE_STATUS,  "
                    + " ALE_NIVEL_ALERTA "
                    //+ " ALE_XML  "
                    //+ " SEN_ID, "
                    //+ " USU_ID,  "
                    + ") VALUES (  "
                    + "NEXTVAL('alertar.\"alerta_ale_id_seq\"'), "
                    + "CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, alerta.getMensagem().getId());
            statement.setInt(2, (alerta.getMensagem() == null || alerta.getMensagem().getSubstancia() == null) ? 
                    alerta.getSubstancia().getId() : alerta.getMensagem().getSubstancia().getId());
            //statement.setInt(3, alerta.getSensor().getId());
            statement.setInt(3, (alerta.getMensagem() ==  null || alerta.getMensagem().getGrupo() == null) ? 
                    alerta.getGrupo().getId() : alerta.getMensagem().getGrupo().getId());
            //statement.setInt(5, alerta.getUsuario().getId());
            statement.setString(4, ((alerta.getMensagem() == null || alerta.getMensagem().getTitulo() == null) ? 
                    alerta.getTitulo() : alerta.getMensagem().getTitulo()));
            statement.setString(5, ((alerta.getMensagem() == null || alerta.getMensagem().getDescricao() == null) ? 
                    alerta.getCorpo() : alerta.getMensagem().getDescricao()));
            statement.setString(6, alerta.getStatus());
            statement.setInt(7, alerta.getNivelAlerta());            
            
            int row = statement.executeUpdate();
            int id = -1;
            if (row > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()){
                    id = keys.getInt(1);
                }
            }
            return id;

        } catch (SQLException ex) {
            return -1;
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
    public List<? extends DataBaseObject> listar(Connection conn, DataBaseObject dataBaseObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<? extends DataBaseObject> listarAtivo(Connection conn) {
        PreparedStatement statement = null;
        List<? extends DataBaseObject> lstData = null;
        List<Alerta> lstAlerta = new ArrayList<Alerta>();
        Alerta alt = null;
        try {
            String sql = "SELECT "
                    + " ALE_ID,    "
                    + " ALE_DATA,  "
                    + " MSG_ID, "
                    + " SUB_ID,  "
                    + " GRU_ID,  "
                    + " ALE_TITULO,  "
                    + " ALE_DESCRICAO,  "
                    + " ALE_STATUS,  "
                    + " ALE_NIVEL_ALERTA "
                    + "FROM ALERTAR.ALERTA "
                    + "WHERE ALE_STATUS = ?";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setString(1, "1");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               
                Mensagem msg = new Mensagem();
                msg.setId(resultSet.getInt("MSG_ID"));
                Grupo grp = new Grupo();
                grp.setId(resultSet.getInt("GRU_ID"));
                Substancia subs = new Substancia();
                subs.setId(resultSet.getInt("SUB_ID"));
                alt = Alerta.getNewInstance(msg,
                        null, 
                        grp, 
                        null, 
                        subs, 
                        resultSet.getString("ALE_TITULO"), 
                        resultSet.getString("ALE_DESCRICAO"), 
                        resultSet.getString("ALE_STATUS"), 
                        resultSet.getInt("ALE_NIVEL_ALERTA"));
                alt.setId(resultSet.getInt("ALE_ID"));
               
                lstAlerta.add(alt);
            }
            lstData = lstAlerta;
            return lstData;

        } catch (SQLException ex) {
            return lstAlerta;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public Alerta buscarID(Connection conn, int ale_id) {
        PreparedStatement statement = null;
        Alerta alt = null;
        try {
            String sql = "SELECT "
                    + " ALE_ID,    "
                    + " ALE_DATA,  "
                    + " MSG_ID, "
                    + " SUB_ID,  "
                    + " GRU_ID,  "
                    + " ALE_TITULO,  "
                    + " ALE_DESCRICAO,  "
                    + " ALE_STATUS,  "
                    + " ALE_NIVEL_ALERTA "
                    + "FROM ALERTAR.ALERTA "
                    + "WHERE ALE_ID = ?";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setInt(1, ale_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               
                Mensagem msg = new Mensagem();
                msg.setId(resultSet.getInt("MSG_ID"));
                Grupo grp = new Grupo();
                grp.setId(resultSet.getInt("GRU_ID"));
                Substancia subs = new Substancia();
                subs.setId(resultSet.getInt("SUB_ID"));
                alt = Alerta.getNewInstance(msg,
                        null, 
                        grp, 
                        null, 
                        subs, 
                        resultSet.getString("ALE_TITULO"), 
                        resultSet.getString("ALE_DESCRICAO"), 
                        resultSet.getString("ALE_STATUS"), 
                        resultSet.getInt("ALE_NIVEL_ALERTA"));
                alt.setId(resultSet.getInt("ALE_ID"));
            }
            return alt;

        } catch (SQLException ex) {
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public boolean atualizarAtivo(Connection conn, DataBaseObject dataBaseObject) {
        PreparedStatement statement = null;
        Alerta alt = (Alerta) dataBaseObject;
        try {
            String sql = "UPDATE "
                    + "ALERTAR.ALERTA "
                    + "SET "
                    + " ALE_STATUS = ? "
                    + "WHERE ALE_ID = ?";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setString(1, alt.getStatus());
            statement.setInt(2, alt.getId());

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
