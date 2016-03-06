package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Se7en on 13-02-2016.
 */
public class print_elements {


    public static void print_by_tag(Document doc, String tag) {
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName(tag);

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                System.out.println("Player id : " + eElement.getAttribute("id"));
                System.out.println("Name : " + eElement.getAttribute("name"));
                System.out.println("Status : " + eElement.getAttribute("status"));
            }


        }


    }

    public static void get_inativos(Document doc, String tag, List<String> id_inativos) {

        NodeList nList = doc.getElementsByTagName(tag);

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                if (((eElement.getAttribute("status").contains("i") || eElement.getAttribute("status").contains("I")) && !eElement.getAttribute("status").contains("v"))) {

                    id_inativos.add(eElement.getAttribute("id"));

                }
            }


        }
    }


    //MÉTODO PRINCIPAL-----
    public static void inativos_military(Integer pm, Integer pos) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<String> lista_id_inativos = new ArrayList<>();
        ArrayList<String> lista_coord_palista = new ArrayList<>();
        ArrayList<String> bons_farms = new ArrayList<>();
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        Map<Integer, ArrayList<String>> bons_farms_map = new HashMap<>();
        get_xml apanha = new get_xml();
        Document doc = apanha.get_specficic_xml("http://s124-pt.ogame.gameforge.com/api/players.xml");
        Document player_xml;
        NodeList nList;
        NodeList nList_coord;
        Node nNode;
        Node nNode_coord;
        Element eElement;
        Element eElement_coord;
        get_inativos(doc, "player", lista_id_inativos);


        //  for(int g=0;g<2;g++) {

        for (int g = 0; g < lista_id_inativos.size(); g++) {

            try {

                player_xml = apanha.get_specficic_xml("http://s124-pt.ogame.gameforge.com/api/playerData.xml?id=" + lista_id_inativos.get(g));
            } catch (FileNotFoundException e) {
                continue;
            }
            nList = player_xml.getElementsByTagName("position");
            nList_coord = player_xml.getElementsByTagName("planet");

            nNode = nList.item(3); // type=3 Pontos Militares

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;




                lista_coord_palista = new ArrayList<>();

                if (Integer.parseInt(eElement.getAttribute("score")) < pm && Integer.parseInt(eElement.getTextContent()) < pos) {// PM e posição geral
                    //   bons_farms.add(lista_id_inativos.get(g));
                    System.out.println("\nO jogador com o ID " + lista_id_inativos.get(g) + " tem os planetas: ");
                    for (int j = 0; j < nList_coord.getLength(); j++) {
                        nNode_coord = nList_coord.item(j);
                        eElement_coord = (Element) nNode_coord;
                        lista_coord_palista.add(eElement_coord.getAttribute("coords"));
                          System.out.println("ID:" + eElement_coord.getAttribute("coords"));

                    }


                    bons_farms_map.put(Integer.parseInt(lista_id_inativos.get(g)), lista_coord_palista);

                }


            }
        }
        files.guarda_ficheiro(bons_farms_map);
        System.out.println("acabou");




    }


}
