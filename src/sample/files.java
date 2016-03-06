package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Se7en on 14-02-2016.
 */
public class files {

    public static void guarda_ficheiro(Map<Integer,ArrayList<String>> mapa) throws IOException {


        ArrayList<String> coord=new ArrayList<>();

        ArrayList<String> yourarraylist ;



        Iterator it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            System.out.println("vai");


             yourarraylist =(ArrayList) mapa.get(pair.getKey());



            for(String valuessaved2key : yourarraylist){
                System.out.println("->"+valuessaved2key);
                coord.add(valuessaved2key);
            }




            it.remove(); // avoids a ConcurrentModificationException
        }

        FileWriter writer = new FileWriter("output.txt");
        for(String str: coord) {
            writer.write(str+"\n");
        }
        writer.close();




    }




}
