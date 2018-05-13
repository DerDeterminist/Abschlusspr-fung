package app;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n
{

   private static ResourceBundle resourceBundle;

   public static void i18nSetup(Locale locale)
   {
      resourceBundle = ResourceBundle.getBundle("i18n/res", locale);
   }

   public static String getText(String key)
   {
      try
      {
         return resourceBundle.getString(key);
      }
      catch(Exception e)
      {
         System.out.println(key);
      }
      return resourceBundle.getString(key);
   }
}
