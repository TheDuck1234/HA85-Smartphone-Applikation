package com.example.ha85_smartphone_applikation.xml;

/**
 * Created by Anders Petersen
 */
public class XmlNyhed implements XmlFile {

    public XmlNyhed(){}

    private String linkheader;
    private String link;
    private String nyhedheader;
    private String nyhedtext;

    public String getLinkheader() {
        return linkheader;
    }

    public void setLinkheader(String linkheader) {
        this.linkheader = linkheader;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNyhedheader() {
        return nyhedheader;
    }

    public void setNyhedheader(String nyhedheader) {
        this.nyhedheader = nyhedheader;
    }

    public String getNyhedtext() {
        return nyhedtext;
    }

    public void setNyhedtext(String nyhedtext) {
        this.nyhedtext = nyhedtext;
    }
}
