/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author  Carlos Ortigoza
 */
public class User {

    private int id;
    private String name;
    private String identification;
    private String mail;
    private String pass;

    public User() {
    }
    
    public User(String d) {
        identification = d;
    }

    public User(int i, String n, String d, String m, String p) {
        id = i;
        name = n;
        identification = d;
        mail = m;
        pass = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
