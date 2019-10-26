/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.model;

import java.util.Date;

/**
 *
 * @author Mari
 */
public class Grupo extends DataBaseObject {

    private Integer Id;
    private String Topic;
    private String Descricao;

    public static Grupo getNewInstance(String Topic, String Descricao){
        Grupo grupo = new Grupo();
        grupo.setTopic(Topic);
        grupo.setDescricao(Descricao);
        return grupo;
    }
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
}
