package dao;

import app.Nutzer;
import app.Feld;
import pflanzen.FeldPflanzen;
import pflanzen.Mais;
import pflanzen.PflanzenArten;
import pflanzen.Weizen;

import java.io.*;
import java.util.ArrayList;

public class LesenUndSchreibenLernen {

    static ArrayList<Nutzer> Nutzer_inhalt = new ArrayList<>();
    static ArrayList<Feld> Felder_inhalt = new ArrayList<>();

    public static void felderSchreiben(){

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("res\\index\\index.csv"));
            for (Feld feld : Felder_inhalt) {
                pw.println(feld.getName()+";"+feld.getPflanzenArten()+     ";"+feld.getPflanzenAnz()+";"+feld.getGehoertZuNutzer());
                pflanzenSchreiben(feld.getPflanzenliste(),feld.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pw!=null){
                pw.close();
            }
        }
    }

    public static ArrayList felderLesen() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("res\\index\\index.csv")));

            String line = null;
            while ((line = br.readLine())!=null){

                PflanzenArten temp=null;
                String[] objekte = line.split(";");
                if (objekte[1].equals("Mais")){
                    temp =  PflanzenArten.Mais;
                }
                if (objekte[1].equals("Weizen")){
                    temp = PflanzenArten.Weizen;
                }
                Felder_inhalt.add(new Feld(objekte[0],temp,pflanzenLesen(objekte[0]),Integer.parseInt(objekte[2]),objekte[3]));
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return Felder_inhalt;
    }

    private static void pflanzenSchreiben(ArrayList<FeldPflanzen> inhalt, String dateiName){

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("res\\felder\\"+dateiName+".csv"));
            for (FeldPflanzen pflanze : inhalt) {
                pw.println(pflanze.getName()+";"+pflanze.getHoehe()+";"+pflanze.getPflanzenArten());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pw!=null){
                pw.close();
            }
        }

    }

    private static ArrayList pflanzenLesen(String dateiName) {
        ArrayList<FeldPflanzen> pflanzenArtenArrayList = new ArrayList<>();

        try {
            System.out.println(new File("res\\felder\\"+dateiName+".csv").getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(new File("res\\felder\\"+dateiName+".csv")));

            String line = null;
            while ((line = br.readLine())!=null){

                PflanzenArten temp = null;
                String[] objekte = line.split(";");
                if (objekte[2].equals("Mais")) {
                    temp =  PflanzenArten.Mais;
                    pflanzenArtenArrayList.add(new Mais(objekte[0], Double.parseDouble(objekte[1]),temp));
                }
                if (objekte[2].equals("Weizen")) {
                    temp =  PflanzenArten.Weizen;
                    pflanzenArtenArrayList.add(new Weizen(objekte[0], Double.parseDouble(objekte[1]),temp));
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return pflanzenArtenArrayList;
    }


    public static void nutzerSchreiben(){

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("res\\index\\nutzer.csv"));
            for (Nutzer nutzer : Nutzer_inhalt) {
                pw.println(nutzer.getName()+";"+nutzer.getPasswort());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pw!=null){
                pw.close();
            }
        }
    }

    public static ArrayList nutzerLesen() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("res\\index\\nutzer.csv")));

            String line = null;
            while ((line = br.readLine())!=null){

            String[] objekte = line.split(";");
            Nutzer_inhalt.add(new Nutzer(objekte[0],objekte[1]));
        }
        br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return Nutzer_inhalt;
    }

    public static ArrayList<Nutzer> getNutzer_inhalt() { return Nutzer_inhalt; }
    public static ArrayList<Feld> getFelder_inhalt(){return Felder_inhalt;}
}