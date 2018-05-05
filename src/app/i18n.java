package app;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class i18n {

    public static ResourceBundle r;

    public static void i18nSetup(String eingabe) {
        String lang = "de";
        String coutry = "DE";
        if (eingabe.equals("english")){
            lang = "en";
            coutry = "US";
        }

        File test =  new File("res\\i18n");
        System.out.println(test.getAbsolutePath());

        Locale l = new Locale(lang, coutry);
        r = ResourceBundle.getBundle("res/i18n/Bundle", l);
    }
}
