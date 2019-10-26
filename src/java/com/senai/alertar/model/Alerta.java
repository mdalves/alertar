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
public class Alerta extends DataBaseObject {

    private Integer Id;
    private Date DataCriacao;
    private Mensagem Msg;
    private Sensor Sensor;
    private Grupo Grp;
    private Usuario Usuario;
    private Substancia Subs;
    private String Titulo;
    private String Corpo;
    private String Status;
    private Integer NivelAlerta;

    public static Alerta getNewInstance(Mensagem mensagem, Sensor sensor, Grupo grupo, Usuario usuario, Substancia substancia, String Titulo, String Corpo, String Status, Integer Nivel) {
        Alerta alerta = new Alerta();
        alerta.setDataCriacao(new Date());
        alerta.setMensagem(mensagem);
        alerta.setSensor(sensor);
        alerta.setGrupo(grupo);
        alerta.setUsuario(usuario);
        alerta.setSubstancia(substancia);
        alerta.setTitulo(Titulo);
        alerta.setCorpo(Corpo);
        alerta.setStatus(Status);
        alerta.setNivelAlerta(Nivel);
        return alerta;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date DataCriacao) {
        this.DataCriacao = DataCriacao;
    }

    public Mensagem getMensagem() {
        if(Msg == null){
            Msg = Mensagem.getNewInstance(-1, null, null, null, false, null, null);
        }
        return Msg;
    }

    public void setMensagem(Mensagem Mensagem) {
        this.Msg = Mensagem;
    }

    public Sensor getSensor() {
        return Sensor;
    }

    public void setSensor(Sensor Sensor) {
        this.Sensor = Sensor;
    }

    public Grupo getGrupo() {
        if (Grp == null){
            Grp = Grupo.getNewInstance(null, null);
            Grp.setId(-1);
        }
        return Grp;
    }

    public void setGrupo(Grupo Grupo) {
        this.Grp = Grupo;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Substancia getSubstancia() {
        if(Subs == null){
            Subs = Substancia.getNewInstance(-1, null, null, null, null);
        }
        return Subs;
    }

    public void setSubstancia(Substancia Substancia) {
        this.Subs = Substancia;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getCorpo() {
        return Corpo;
    }

    public void setCorpo(String Corpo) {
        this.Corpo = Corpo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Integer getNivelAlerta() {
        return NivelAlerta;
    }

    public void setNivelAlerta(Integer NivelAlerta) {
        this.NivelAlerta = NivelAlerta;
    }

}
