package com.joaopaulo.cap3;

import android.text.Editable;

/**
 * Created by João Paulo on 20/10/2015.
 */
public class Usuario {

    private String login;
    private String senha;
    private String email;
    private String urlImg;

    public Usuario(String login, String senha, String email) {
        this.senha = senha;
        this.login = login;
        this.email = email;
        this.urlImg = urlImg;
    }

}
