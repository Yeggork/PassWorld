package com.example.passworld.non;

import java.io.Serializable;
import java.util.Collection;

public class Passworddd implements Serializable {

    private int id;
    private String textpassword;
    private String idpas;

    public Passworddd(String textpassword) {
        this.textpassword = textpassword;
        this.idpas = textpassword;
    }

    public Passworddd() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextpassword() {
        return textpassword;
    }

    public void setTextpassword(String textpassword) {
        this.textpassword = textpassword;
    }

    public String getIdpas() {
        return idpas;
    }

    public void setIdpas(String idpas) {
        this.idpas = idpas;
    }

    public Collection<Object> toLowerCase() {
        return null;
    }

    public int length() {
        return textpassword.length();
    }

    public char charAt(int i) {
        return textpassword.charAt(i);
    }
}
