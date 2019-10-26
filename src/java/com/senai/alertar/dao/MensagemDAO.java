/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.dao;

import com.senai.alertar.model.DataBaseObject;
import com.senai.alertar.model.Grupo;
import com.senai.alertar.model.Mensagem;
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
public class MensagemDAO implements GenericDAO {

    @Override
    public boolean inserir(Connection conn, DataBaseObject dataBaseObject) {
        Mensagem mensagem = (Mensagem) dataBaseObject;
        PreparedStatement statement = null;
        boolean action = false;
        try {
            String sql = "INSERT INTO ALERTAR.MENSAGEM"
                    + "("
                    + "    MSG_ID,"
                    + "    MSG_TITULO,"
                    + "    MSG_DESCRICAO,"
                    + "    MSG_ATIVO,"
                    + "    MSG_NIVEL_ALERTA,"
                    + "    SUB_ID,"
                    + "    GRU_ID"
                    + ") VALUES ("
                    + "    NEXTVAL('alertar.mensagem_msg_id_seq'),"
                    + "    ?,"
                    + "    ?,"
                    + "    ?,"
                    + "    ?,"
                    + "    ?,"
                    + "    ?"
                    + ")";

            statement = conn.prepareStatement(sql);
            statement.setString(1, mensagem.getTitulo());
            statement.setString(2, mensagem.getDescricao());
            statement.setInt(3, 1);
            statement.setInt(4, mensagem.getNivelAlerta());
            statement.setInt(5, mensagem.getSubstancia().getId());
            statement.setInt(6, mensagem.getGrupo().getId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rs.getInt(1);
            }
            return action;

        } catch (SQLException ex) {
            return action;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        PreparedStatement statement = null;
        List<? extends DataBaseObject> lstData = null;
        List<Mensagem> lstMensagem = new ArrayList<Mensagem>();
        Mensagem mensagem = (Mensagem) dataBaseObject;
        try {
            String sql = "SELECT "
                    + "msg_id, "
                    + "msg_titulo, "
                    + "msg_descricao,"
                    + "msg_nivel_alerta, "
                    + "msg_ativo, "
                    + "sub.sub_id,"
                    + "sub.sub_nome, "
                    + "sub.sub_classe_risco, "
                    + "sub.sub_numero_onu, "
                    + "sub.sub_limite_tolerancia_ppm, "
                    + "gru.gru_id, "
                    + "gru.gru_topic, "
                    + "gru.gru_descricao, "
                    + "gru.gru_codigo "
                    + "FROM ALERTAR.mensagem msg "
                    + "INNER JOIN ALERTAR.substancia sub ON sub.sub_id = msg.sub_id "
                    + "INNER JOIN ALERTAR.grupo gru ON gru.gru_id = msg.gru_id "
                    + "WHERE msg_ativo = 1 "
                    + "AND UPPER(sub.sub_nome) = UPPER(?) "
                    + "AND msg.msg_nivel_alerta = ?";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setString(1, mensagem.getSubstancia().getNome());
            statement.setInt(2, mensagem.getNivelAlerta());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                mensagem = new Mensagem();
                mensagem.setId(Integer.parseInt(resultSet.getString("msg_id")));
                mensagem.setTitulo(resultSet.getString("msg_titulo"));
                mensagem.setDescricao(resultSet.getString("msg_descricao"));
                mensagem.setNivelAlerta(resultSet.getInt("msg_nivel_alerta"));
                mensagem.setAtivo(Boolean.TRUE);
               
                Grupo grupo = new Grupo();
                grupo.setId(Integer.parseInt(resultSet.getString("gru_id")));
                grupo.setTopic(resultSet.getString("gru_topic"));
                grupo.setDescricao(resultSet.getString("gru_descricao"));

                Substancia substancia = new Substancia();
                substancia.setId(Integer.parseInt(resultSet.getString("sub_id")));
                substancia.setNome(resultSet.getString("sub_nome"));
                substancia.setClasseRisco(resultSet.getString("sub_classe_risco"));
                substancia.setNumeroONU(resultSet.getInt("sub_numero_onu"));
                substancia.setLimiteToleranciaPPM(resultSet.getInt("sub_limite_tolerancia_ppm"));

                mensagem.setGrupo(grupo);
                mensagem.setSubstancia(substancia);
                lstMensagem.add(mensagem);
            }
            lstData = lstMensagem;
            return lstData;

        } catch (SQLException ex) {
            return lstMensagem;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
