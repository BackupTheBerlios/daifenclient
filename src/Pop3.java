// CC_VERSIONS


import mailbox.POP3.POP3_Sink;
import netscape.messaging.pop3.POP3Client;
import tools.Trace;

import java.io.File;
import java.io.IOException;

/**
 * Pop3.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Mar 25, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */


public class Pop3
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private static String _password = "";


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public Pop3()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public static void main(String[] args)
   {
      // activate traces

      Trace.setTraces((new File("./TRACES")).isFile());

      // Create sink first as described in Creating a Response Sink

      POP3_Sink l_pop3Sink = new POP3_Sink();

      POP3Client l_client = new POP3Client( l_pop3Sink );

      try
      {
         // After Creating a Response Sink and Creating a Client
         Trace.println("Connecting to Yahoo...");
         l_client.connect("pop.mail.yahoo.fr");
         l_client.processResponses();
         Trace.println("Connected to Yahoo !!!");

         // User id sequence
         Trace.println("Setting Yahoo user...");
         l_client.user( "stollvor" );
         l_client.processResponses();

         // Password sequence
         Trace.println("Setting Yahoo password...");

         l_client.pass( _password );
         l_client.processResponses();

         // Getting Message Count
         l_client.stat();
         l_client.processResponses();
         Trace.println("NB message : " + l_pop3Sink.getNBMessage());

         // Listing Messages
//         l_client.list();
//         l_client.processResponses();

         // Retrieving Message Headers
         for ( int i = 1 ; i <= l_pop3Sink.getNBMessage() ; i++ )
         {
            System.out.println("---------------------------------------");
            l_client.top( i, 0 );
            l_client.processResponses();
         }

         // Disconnect
         Trace.println("Disonnecting from Yahoo...");
         l_client.quit();
         l_client.processResponses();
         Trace.println("Disonnected from Yahoo !!!");
      }
      catch (IOException e)
      {
         System.out.println("ERROR: Problem to connect to Yahoo!");
      }

      System.out.println("Pop3 is finished.");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***