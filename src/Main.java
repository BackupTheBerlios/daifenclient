// CC_VERSIONS

import tools.Trace;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Main.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Mar 29, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

class Essai
{
   public void essai()
   {
      System.out.println("Execution de la fonction essai()...");
   }

   public void essai2(int i)
   {
      System.out.println("Execution de la fonction essai2(" + i + ")...");
   }
}

public class Main
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public Main()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public static void main(String[] args)
   {
      Trace.setTraces((new File("./TRACES")).isFile());

      Trace.println("Connecting to yahoo...");

      Essai    l_essai = new Essai();
      Method[] l_lst   = l_essai.getClass().getMethods();

      for ( int i = 0 ; i < l_lst.length ; i++ )
      {
         Method f = l_lst[i];

         if ( f.getName().compareTo("essai2") == 0 )
         {
            try
            {
               f.invoke(l_essai, new Object[0]);
//               Class[] l_type = f.getParameterTypes();
//               l_type.clone();
//               f.invoke(l_essai, new Class[] {Class.class. });
            }
            catch ( IllegalAccessException e )
            {
               // TODO: Meme si tu ne traites pas l'exception pour le moment
               //       met au moins une trace... Trace.println(...);
            }
            catch ( InvocationTargetException e )
            {
               // TODO: Meme si tu ne traites pas l'exception pour le moment
               //       met au moins une trace... Trace.println(...);
            }
         }
      }


      System.out.println("Main is finished.");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***