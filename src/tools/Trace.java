package tools;

// CC_VERSIONS

/**
 * tools.Trace.java
 *
 * DESCRIPTION:
 *
 *    @author        J-P HILAIRE  -  Feb 5, 2004
 *    @version       v0.1
 *
 * HOW TO USE:
 *
 *
 */


public class Trace
{
   // Flag that indicate if the trace information display is activated
   static private boolean  __bDebug       = false;

   static private int      __iStackDepth  = 0;

   static private Trace    __instance     = null;


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   private Trace() {}


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   /**
    * This function disactivates the trace mode.
    */

   static public void disable()
   {
      instance().__bDebug = false;
   };


   /**
    * This function activates the trace mode.
    */

   static public void enable()
   {
      instance().__bDebug = true;
   };


   /**
    * This function indicates that the program run into a specified function.
    *
    * @param   p_sFunctionName   String name of the current function.
    */

   static public void enterFunction(String p_sFunctionName)
   {
      if ( instance().__bDebug )
      {
         System.out.print("[DEBUG] " + stackNumber() + indent() +
                          "\033[1m" + "===> " + "\033[m" +
                          "BEGIN " + "\033[1m" + p_sFunctionName +
                          "\033[m\n");
      }

      __iStackDepth++;
   };


   /**
    * This function indicates that the program run out a specified function.
    *
    * @param   p_sFunctionName   String name of the current function.
    */

   static public void exitFunction(String p_sFunctionName)
   {
      if (__iStackDepth > 0 ) __iStackDepth--;

      if ( instance().__bDebug )
      {
         System.out.print("[DEBUG] " + stackNumber() + indent() +
                          "\033[1m" + "---> " + "\033[m" +
                          "END   " + "\033[1m" + p_sFunctionName +
                          "\033[m\n");
      }
   };


   /**
    * This function indicates that the program run out a specified function.
    *
    * @param   p_sFunctionName   String name of the current function.
    * @param   p_sReturn         Return value of the current function.
    */

   static public void exitFunction(String p_sFunctionName, String p_sReturn)
   {
      if (__iStackDepth > 0 ) __iStackDepth--;

      if ( instance().__bDebug )
      {
         System.out.print("[DEBUG] " + stackNumber() + indent() +
                          "\033[1m" + "---> " + "\033[m" +
                          "END   " + "\033[1m" + p_sFunctionName + "\033[m" +
                          " -> return(" + "\033[1m" + p_sReturn + "\033[m" +
                          ")\n");
      }
   };


   /**
    * This function allows to check if the trace mode is activated.
    *
    * @return The current trace mode status (true/false)
    */

   static public boolean isActivated()
   {
      return instance().__bDebug;
   };


   /**
    * This function displays an output message if the trace mode is enabled.
    *
    * @param p_sMessage    Main message to display
    * @param p_sValue      Secondary message to display
    */

   static public void print(String p_sMessage,
                            String p_sValue)
   {
      if ( instance().__bDebug )
      {
         System.out.print("[DEBUG] " + stackNumber() + indent() +
                          "\033[1m" + p_sMessage +
                          "\033[m" + p_sValue);
      }
   }


   /**
    * This function displays an output message if the trace mode is enabled.
    *
    * @param p_sMessage    Main message to display
    * @param p_sValue      Secondary message to display
    */

   static public void println(String p_sMessage,
                              String p_sValue)
   {
      if ( instance().__bDebug )
      {
         System.out.println("[DEBUG] " + stackNumber() + indent() +
                            "\033[1m" + p_sMessage +
                            "\033[m" + p_sValue);
      }
   }


   /**
    * This function displays an output message if the trace mode is enabled.
    *
    * @param p_sMessage    Main message to display
    * @param p_sValue      Secondary message to display
    */

   static public void println(String p_sMessage,
                              String[] p_sValue)
   {
      if ( instance().__bDebug )
      {
         if ( p_sValue != null )
         {
            for (int i = 0 ; i < p_sValue.length ; i++)
            {
               System.out.println("[DEBUG] " + stackNumber() + indent() +
                                  "\033[1m" + p_sMessage +
                                  "[" + i + "] " + "\033[m" + p_sValue[i]);
            }
         }
      }
   }

   /**
    * This function displays an output message if the trace mode is enabled.
    *
    * @param p_sMessage    Main message to display
    */

   static public void println(String p_sMessage)
   {
      if ( instance().__bDebug )
      {
          System.out.println("[DEBUG] " + stackNumber() + indent() +
                             "\033[1m" + p_sMessage + "\033[m" );
      }
   }

   /**
    * This function displays an output message if the trace mode is enabled.
    *
    * @param p_sMessage    Main message to display
    * @param p_sValue      Secondary message to display
    */

   static public void warning(String p_sMessage,
                              String p_sValue)
   {
      if ( instance().__bDebug )
      {
         System.out.println("[DEBUG] " + stackNumber() + indent() +
                            "\033[1m" + "!!! Warning : " + p_sMessage +
                            "\033[m" + p_sValue);
      }
   }


   /**
    * This function activates or desactivates the trace mode.
    *
    * @param p_bVal true  : activates the trace mode<br>
    *               false : desactivates the trace mode
    */

   static public void setTraces(boolean p_bVal)
   {
      instance().__bDebug = p_bVal;
   };


   static public void reset()
   {
      instance().__iStackDepth = 0;
   }
   

   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   static protected Trace instance()
   {
      if ( __instance == null )
      {
         __instance = new Trace();
      }

      return __instance;
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   static private String indent()
   {
      String l_str = new String();

      for ( int i = 0 ; i < __iStackDepth ; i++)
      {
         l_str += "  ";
      }

      l_str += "| ";

      return l_str;
   }


   static private String stackNumber()
   {
      String l_str = new String();

      if ( __iStackDepth < 10 )
      {
         l_str += " ";
      }

      l_str += __iStackDepth + " ";

      return l_str;
   }
}

//*** EOF ************************************************************ EOF ***
