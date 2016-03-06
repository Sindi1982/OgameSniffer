package sample;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Se7en on 14-02-2016.
 */
public class logicath implements Runnable {


    Integer pm;
    Integer pos;

    public logicath(Integer pm,Integer pos) {
        this.pm=pm;
        this.pos=pos;
    }



    @Override
    public void run() {
  List<String> lista_inativos = new ArrayList<>();


        get_xml apanha = null;
        try {
            apanha = new get_xml();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Document doc = apanha.get_specficic_xml("http://s124-pt.ogame.gameforge.com/api/players.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
//print_elements.print_by_tag(doc,"player");
        try {
            print_elements.inativos_military(pm,pos);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
