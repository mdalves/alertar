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
public class Mensagem extends DataBaseObject {

    private Integer Id;
    private String Titulo;
    private String Descricao;
    private Integer NivelAlerta;
    private Boolean Ativo;
    private Substancia Substancia;
    private Grupo Grupo;

    
    public static Mensagem getNewInstance(Integer id, String titulo, String descricao, Integer nivelAlerta, boolean ativo, Substancia substancia, Grupo grupo){
        
        Mensagem msg = new Mensagem();
        msg.setId(id);
        msg.setTitulo(titulo);
        msg.setDescricao(descricao);
        msg.setNivelAlerta(nivelAlerta);
        msg.setAtivo(ativo);
        msg.setSubstancia(substancia);
        msg.setGrupo(grupo);
        
        return msg;
    }
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Integer getNivelAlerta() {
        return NivelAlerta;
    }

    public void setNivelAlerta(Integer NivelAlerta) {
        this.NivelAlerta = NivelAlerta;
    }

    public Boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(Boolean Ativo) {
        this.Ativo = Ativo;
    }

    public Substancia getSubstancia() {
        return Substancia;
    }

    public void setSubstancia(Substancia Substancia) {
        this.Substancia = Substancia;
    }

    public Grupo getGrupo() {
        return Grupo;
    }

    public void setGrupo(Grupo Grupo) {
        this.Grupo = Grupo;
    }

}
