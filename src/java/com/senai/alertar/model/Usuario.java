/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.model;

/**
 *
 * @author MARI
 */
public class Usuario extends DataBaseObject {

    private Integer Id;
    private String Nome;
    private String Email;
    private String Login;
    private String Senha;

    public static Usuario getNewInstance(String Nome, String Email, String Login, String Senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(Nome);
        usuario.setEmail(Email);
        usuario.setLogin(Login);
        usuario.setSenha(Senha);
        return usuario;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
}
