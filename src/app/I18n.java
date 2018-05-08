package app;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n
{

    public static ResourceBundle resourceBundle;

    public static void i18nSetup(Locale locale) {
        //String lang = "de";
        //String coutry = "DE";
        //if (locale.equals("english")){
        //    lang = "en";
        //    coutry = "US";
        //}

        //File test =  new File("res\\i18n");
        //System.out.println(test.getAbsolutePath());

        //Locale l = new Locale(lang, coutry);
        resourceBundle = ResourceBundle.getBundle("i18n/res", locale);
    }

    public static String getText(String key)
    {
        try {
            return resourceBundle.getString(key);
        }catch (Exception e){
            System.out.println(key);
        }
        return resourceBundle.getString(key);
    }
}
