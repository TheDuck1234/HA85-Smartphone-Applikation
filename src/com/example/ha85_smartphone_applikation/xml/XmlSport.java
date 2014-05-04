package com.example.ha85_smartphone_applikation.xml;

/**
 * Created by Anders
 */
public class XmlSport implements XmlFile {

    private String navn;
    private String tTid;
    private String tSted;
    private String tDag;
    private String tStart;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String gettTid() {
        return tTid;
    }

    public void settTid(String tTid) {
        this.tTid = tTid;
    }

    public String gettSted() {
        return tSted;
    }

    public void settSted(String tSted) {
        this.tSted = tSted;
    }

    public String gettDag() {
        return tDag;
    }

    public void settDag(String tDag) {
        this.tDag = tDag;
    }

    public String gettStart() {
        return tStart;
    }

    public void settStart(String tStart) {
        this.tStart = tStart;
    }
}
