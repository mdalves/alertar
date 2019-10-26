/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.model;

/**
 *
 * @author Mari
 */
public class Substancia extends DataBaseObject {

    private Integer Id;
    private String Nome;
    private String ClasseRisco;
    private Integer NumeroONU;
    private Integer LimiteToleranciaPPM;
    
    public static Substancia getNewInstance(Integer id, String nome, String classeRisco, Integer numeroONU, Integer limiteToleranciaPPM){
        
        Substancia subs = new Substancia();
        subs.setId(id);
        subs.setNome(nome);
        subs.setClasseRisco(classeRisco);
        subs.setNumeroONU(numeroONU);
        subs.setLimiteToleranciaPPM(limiteToleranciaPPM);
        
        return subs;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getClasseRisco() {
        return ClasseRisco;
    }

    public void setClasseRisco(String ClasseRisco) {
        this.ClasseRisco = ClasseRisco;
    }

    public Integer getNumeroONU() {
        return NumeroONU;
    }

    public void setNumeroONU(Integer NumeroONU) {
        this.NumeroONU = NumeroONU;
    }

    public Integer getLimiteToleranciaPPM() {
        return LimiteToleranciaPPM;
    }

    public void setLimiteToleranciaPPM(Integer LimiteToleranciaPPM) {
        this.LimiteToleranciaPPM = LimiteToleranciaPPM;
    }
}
