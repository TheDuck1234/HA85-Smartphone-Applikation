package com.example.ha85_smartphone_applikation.html;


public class NyhedsFile {

    public NyhedsFile(){}

    private String dateHeader;
    private String linkHeader;
    private String link;
    private String text;

    public String getDateHeader() {
        return dateHeader;
    }

    public void setDateHeader(String header) {
        dateHeader = header;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLinkHeader() {
        return linkHeader;
    }

    public void setLinkHeader(String linkHeader) {
        this.linkHeader = linkHeader;
    }
}
