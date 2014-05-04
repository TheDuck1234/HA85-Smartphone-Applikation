package com.example.ha85_smartphone_applikation.xml;
/**
 * Created by Anders Petersen
 */
public class XmlInfo implements XmlFile {

    private String hold;
    private String navn;
    private String telefon;
    private String email;
    private String sport;

    public XmlInfo(String sporttype) {
        sport = sporttype;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
