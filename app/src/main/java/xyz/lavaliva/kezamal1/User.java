package xyz.lavaliva.kezamal1;

import java.util.Calendar;

public class User {
    String familia, imia, otchestvo, telefon, parol ;
    Calendar rojdenie;
    boolean admin, dostup=false;
    String rost, obuv, odejda;

    public boolean isDostup() {
        return dostup;
    }

    public void setDostup(boolean dostup) {
        this.dostup = dostup;
    }

    public User() {

    }

    String userDoljnost = "";


    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getImia() {
        return imia;
    }

    public void setImia(String imia) {
        this.imia = imia;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public Calendar getRojdenie() {
        return rojdenie;
    }

    public void setRojdenie(Calendar rojdenie) {
        this.rojdenie = rojdenie;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRost() {
        return rost;
    }

    public void setRost(String rost) {
        this.rost = rost;
    }

    public String getObuv() {
        return obuv;
    }

    public void setObuv(String obuv) {
        this.obuv = obuv;
    }

    public String getOdejda() {
        return odejda;
    }

    public void setOdejda(String odejda) {
        this.odejda = odejda;
    }


    public String getUserDoljnost() {
        return userDoljnost;
    }

    public void setUserDoljnost(String userDoljnost) {
        this.userDoljnost = userDoljnost;
    }
}

