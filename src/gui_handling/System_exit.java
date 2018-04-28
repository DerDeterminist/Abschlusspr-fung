package gui_handling;

import dao.LesenUndSchreibenLernen;

import javax.swing.*;

public class System_exit {

    public static void exit(){

        int rueckgabewert = JOptionPane.showConfirmDialog(null,"beenden?");
        if (rueckgabewert==0){
            LesenUndSchreibenLernen.felderSchreiben();
            LesenUndSchreibenLernen.nutzerSchreiben();
            System.exit(0);
        }
    }
}
