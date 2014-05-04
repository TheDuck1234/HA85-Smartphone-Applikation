package com.example.ha85_smartphone_applikation.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anders Petersen
 */
public class  HA85Loader {

    private  String Nyhed = "http://www.ha85.dk/?id=100001&c=Nyheder";
    /**
     * @author Anders Petersen
     */
    public  ArrayList<String> NewHeader(){
        Document doc = null;
        try {
            doc = Jsoup.connect(Nyhed).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements select = null;
        if (doc != null) {
            select = doc.select(".newsdate");
        }
        ArrayList<String> data = new ArrayList<String>();
        if (select != null) {
            for(Element element : select){
                data.add(element.text());
            }
        }
        return  data;
    }
    /**
     * @author Anders Petersen
     */
    public  ArrayList<String> Newlink(){
        Document doc = null;
        try {
            doc = Jsoup.connect(Nyhed).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements select = null;
        if (doc != null) {
            select = doc.select(".newsLink");
        }
        ArrayList<String> data = new ArrayList<String>();
        if (select != null) {
            for(Element element:select){
                String linkHref = element.attr("href");
                String test[] = linkHref.split(" ");
                data.add(test[0]);
            }
        }

        return data;

    }
    /**
     * @author Anders Petersen
     */
    public   String NewlinkHeader(String link){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.ha85.dk/"+link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            return doc.select(".tagline").get(0).text();
        }
        return null;
    }
    /**
     * @author Anders Petersen
     */
    public  String NewText2(String link){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.ha85.dk/"+link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            return doc.select("div#contentstyle").get(0).text();
        }
        return null;
    }
    /**
     * @author Anders Petersen
     */
    public  ArrayList<NyhedsFile> GetNyhder(){
        ArrayList<NyhedsFile> nyhedsFiles = new ArrayList<NyhedsFile>();
        try{
        ArrayList<String> header = NewHeader();
        ArrayList<String> link = Newlink();
        int y = 0;
        for(int x = 0; x < 20; x=x+2){
            NyhedsFile nyhedsFile = new NyhedsFile();
            nyhedsFile.setDateHeader(header.get(y));
            nyhedsFile.setLinkHeader(NewlinkHeader(link.get(x+1)));
            nyhedsFile.setLink(link.get(x+1));
            nyhedsFile.setText(NewText2(link.get(x+1)));
            nyhedsFiles.add(nyhedsFile);
            y++;
        }
        }
        catch (Exception ignored){

        }
        return nyhedsFiles;
    }
}
