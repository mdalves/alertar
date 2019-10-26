/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.dao;

import com.senai.alertar.model.DataBaseObject;
import java.util.List;
import java.sql.Connection;

/**
 *
 * @author MARI
 */
public interface GenericDAO {

    public boolean inserir(Connection conn, DataBaseObject dataBaseObject);

    public boolean atualizar(Connection conn, DataBaseObject dataBaseObject);

    public boolean excluir(Connection conn, DataBaseObject dataBaseObject);

    public List<? extends DataBaseObject> listar(Connection conn, DataBaseObject dataBaseObject);
}
