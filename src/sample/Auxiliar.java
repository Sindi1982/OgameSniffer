package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by Se7en on 15-02-2016.
 */
public class Auxiliar {


    public static int readlines() {
        int conta = 0;
        System.out.println("Le file");
        String thisLine = null;
        String[] parts_aux = null;
        ArrayList<String> nova = new ArrayList<>();
        String[] parts_aux_aux = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            while ((thisLine = br.readLine()) != null) {
                parts_aux = thisLine.split(":");
                if (!parts_aux[0].contains("2")) // checkbox galaxia
                    continue;

                nova.add(parts_aux[1] + ":" + parts_aux[2]);
                System.out.println(parts_aux[0] + ":" + parts_aux[1] + ":" + parts_aux[2]);

                conta++;
            }

            FileWriter writer = new FileWriter("coordenadas_floatattack.txt");
            for (String str : nova) {
                writer.write(str + "\n");

            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conta;
    }

    public static int readlinesg2() {
        int conta = 0;
        System.out.println("Le file");
        String thisLine = null;
        String[] parts_aux = null;
        ArrayList<String> nova = new ArrayList<>();
        String[] parts_aux_aux = null;
        try {

            BufferedReader br = new BufferedReader(new FileReader("output.txt"));

            while ((thisLine = br.readLine()) != null) {

                conta++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conta;
    }


}

