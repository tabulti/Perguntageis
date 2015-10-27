package com.joaopaulo.cap3.domain;

/**
 * Created by João Paulo on 20/10/2015.
 */
public class Usuario {

    private String login;
    private String senha;
    private String email;
    private int imgCode;

    public Usuario(String login, String senha, String email, int imgCode) {
        this.senha = senha;
        this.login = login;
        this.email = email;
        this.imgCode = imgCode;
    }

}
