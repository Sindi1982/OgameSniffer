package sample;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Se7en on 13-02-2016.
 */
public class get_xml {


    DocumentBuilderFactory dbf ;
    DocumentBuilder db;


    public get_xml() throws ParserConfigurationException {

        this.dbf = DocumentBuilderFactory.newInstance();
       this.db = dbf.newDocumentBuilder();

    }

 public Document get_specficic_xml(String url) throws IOException, SAXException {
Document doc;
     doc = db.parse(new URL(url).openStream());
     doc.getDocumentElement().normalize();
    return  doc;

 }

}
