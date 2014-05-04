package com.example.ha85_smartphone_applikation.xml;

import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Anders Petersen
 */
public class ParserXml {

    public ParserXml(){}

    /**
     * @author Anders Petersen
     */
    public void makeXml2(InputStream in) throws IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        Document dom = null;
        if (db != null) {
            dom = db.parse(in);
        }
        Element docEle = null;
        if (dom != null) {
            docEle = dom.getDocumentElement();
        }

        NodeList nl = docEle != null ? docEle.getElementsByTagName("nyhed") : null;
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength(); i++) {
                Element nyhed = (Element)nl.item(i);
                Element linkheader = (Element)nyhed.getElementsByTagName("linkheader").item(0);
                Element nyhedheader = (Element)nyhed.getElementsByTagName("nyhedheader").item(0);
                Element nyhedtext = (Element)nyhed.getElementsByTagName("nyhedtext").item(0);
            }}

    }
    /**
     * @author Anders Petersen
     */
    public String makeXml(ArrayList<XmlNyhed> nyheder){
        XmlSerializer serializer = Xml.newSerializer();
        try {
            StringWriter writer = new StringWriter();
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8",true);
            serializer.startTag("", "nyheder");
            for (XmlNyhed ny: nyheder){
                serializer.startTag("", "nyhed");
                serializer.startTag("", "linkheader");
                if(ny.getLinkheader()!=null) {
                    serializer.text(ny.getLinkheader());
                }
                serializer.endTag("", "linkheader");
                serializer.startTag("", "nyhedheader");
                if(ny.getNyhedheader()!=null) {
                    serializer.text(ny.getNyhedheader());
                }
                serializer.endTag("", "nyhedheader");
                serializer.startTag("", "nyhedtext");
                if(ny.getNyhedtext()!=null) {
                    serializer.text(ny.getNyhedtext());
                }
                serializer.endTag("", "nyhedtext");
                serializer.endTag("", "nyhed");
            }
            serializer.endTag("", "nyheder");
            serializer.endDocument();
            serializer.flush();
            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @author Anders Petersen
     */
    public ArrayList<? extends XmlFile> XmlFactory(InputStream streamType, String type,String typesport){
        XmlPullParserFactory pullParserFactory;
        ArrayList<? extends XmlFile> xml = null;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

            parser.setInput(streamType, null);
            if(type.equals("info")) {
                if (typesport.equals("fodboldt")) {
                    xml = InfoParseXML(parser, typesport);
                } else if (typesport.equals("haendboldt")) {
                    xml = InfoParseXML(parser, typesport);
                } else if (typesport.equals("badminton")) {
                    xml = InfoParseXML(parser, typesport);
                } else if (typesport.equals("loebe")) {
                    xml = InfoParseXML(parser, typesport);
                }
            }
            else if (type.equals("nyhed")) {
                    xml = NyhedParseXML(parser);
                }
            else if(type.equals("sport")){
                if (typesport.equals("fodboldt")) {
                    xml = SportParseXML(parser, typesport);
                } else if (typesport.equals("haendboldt")) {
                    xml = SportParseXML(parser, typesport);
                } else if (typesport.equals("badminton")) {
                    xml = SportParseXML(parser, typesport);
                } else if (typesport.equals("loebe")) {
                    xml = SportParseXML(parser, typesport);
                }
            }

        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return xml;
    }
    //Getting Nyhed XMl data
    /**
     * @author Anders Petersen
     */
    private  ArrayList<XmlNyhed> NyhedParseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {
        ArrayList<XmlNyhed> nyheder = null;
        int eventType = parser.getEventType();
        XmlNyhed currentNyhed = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    nyheder = new ArrayList<XmlNyhed>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("nyhed")){
                        currentNyhed = new XmlNyhed();
                    } else if (currentNyhed != null){
                        if (name.equals("linkheader")){
                            currentNyhed.setLinkheader(parser.nextText());
                        }
                        else if (name.equals("nyhedheader")){
                            currentNyhed.setNyhedheader(parser.nextText());
                        }
                        else if (name.equals("nyhedtext")) {
                            currentNyhed.setNyhedtext(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("nyhed") && currentNyhed != null){
                        if (nyheder != null) {
                            nyheder.add(currentNyhed);
                        }
                    }
            }
            eventType = parser.next();
        }
        return nyheder ;
    }
    //Getting Info XMl data
    /**
     * @author Anders Petersen
     */
    protected ArrayList<XmlSport> SportParseXML(XmlPullParser parser, String type) throws XmlPullParserException,IOException
    {
        ArrayList<XmlSport> sport = null;
        int eventType = parser.getEventType();
        XmlSport currentSport = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    sport = new ArrayList<XmlSport>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals(type)){
                        currentSport = new XmlSport();
                    } else if (currentSport != null){
                        if (name.equals("navn")){
                            currentSport.setNavn(parser.nextText());
                        }
                        else if (name.equals("ttid")){
                            currentSport.settTid(parser.nextText());
                        }
                        else if (name.equals("tdag")) {
                            currentSport.settDag(parser.nextText());
                        }
                        else if (name.equals("tsted")) {
                            currentSport.settSted(parser.nextText());
                        }
                        else if (name.equals("tstart")) {
                            currentSport.settStart(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase(type) && currentSport != null){
                        if (sport != null) {
                            sport.add(currentSport);
                        }
                        currentSport = null;
                    }
            }
            eventType = parser.next();
        }
        return sport ;
    }
    //Getting Info XMl data
    /**
     * @author Anders Petersen
     */
    private  ArrayList<XmlInfo> InfoParseXML(XmlPullParser parser,String type) throws XmlPullParserException,IOException
    {
        ArrayList<XmlInfo> nyheder = null;
        int eventType = parser.getEventType();
        XmlInfo currentInfo = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    nyheder = new ArrayList<XmlInfo>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals(type)){
                        currentInfo = new XmlInfo(type);
                    }
                    else if (currentInfo != null){
                        if (name.equals("hold")){
                            currentInfo.setHold(parser.nextText());
                        }
                        else if (name.equals("navn")){
                            currentInfo.setNavn(parser.nextText());
                        }
                        else if (name.equals("telefon")) {
                            currentInfo.setTelefon(parser.nextText());
                        }
                        else if (name.equals("email")) {
                            currentInfo.setEmail(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase(type) && currentInfo != null){
                        if (nyheder != null) {
                            nyheder.add(currentInfo);
                        }
                        currentInfo = null;
                    }
            }
            eventType = parser.next();
        }
        return nyheder ;
    }


}
