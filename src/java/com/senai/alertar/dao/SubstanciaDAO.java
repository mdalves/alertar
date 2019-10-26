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
import java.util.Spliterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mari
 */
public class SubstanciaDAO implements GenericDAO {

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
        List<Substancia> lstSubstancia = new ArrayList<Substancia>();
        Substancia substancia = (Substancia) dataBaseObject;
        try {
            String sql = "SELECT sub_id, sub_nome, sub_classe_risco, sub_numero_onu, sub_limite_tolerancia_ppm FROM ALERTAR.substancia WHERE UPPER(sub_nome) = UPPER(?)";

            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = conn.prepareStatement(sql);
            statement.setString(1, substancia.getNome());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                substancia = new Substancia();
                substancia.setId(Integer.parseInt(resultSet.getString("sub_id")));
                substancia.setNome(resultSet.getString("sub_nome"));
                substancia.setClasseRisco(resultSet.getString("sub_classe_risco"));
                substancia.setNumeroONU(Integer.parseInt(resultSet.getString("sub_numero_onu")));
                substancia.setLimiteToleranciaPPM(Integer.parseInt(resultSet.getString("sub_limite_tolerancia_ppm")));

                if (substancia != null) {
                    lstSubstancia.add(substancia);
                }
            }
            lstData = lstSubstancia;
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
