// CC_VERSIONS

import exception.*;
import mailbox.MailBody;
import mailbox.MailMessage;
import specific.DaifenManager;
import specific.DaifenMessage;
import tools.Trace;

import java.io.File;
import java.io.IOException;

/**
 * Daifen.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Apr 2, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */


public class Daifen
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private static String _mailerType    = "POP3";
   private static String _server        = "pop.mail.yahoo.fr";
   private static String _user          = "stollvor";
   private static String _password      = "";


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public Daifen()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public static void main(String[] args)
   {
      // activate traces

      Trace.setTraces((new File("./TRACES")).isFile());

      try
      {
         DaifenManager l_daifen = new DaifenManager(_mailerType, _server,
                                                    _user, _password);


         try
         {
            MailMessage l_msg = l_daifen.getBilan("327");
//            MailMessage l_msg = l_daifen.getLastBilan();

//            System.out.println(l_msg.getSubject());


            // TODO TEMPORAIRE
            MailBody l_body = l_daifen.getBody(l_msg);

//            l_body.printOn();
            // TODO TEMPORAIRE

            DaifenMessage l_DaifenMsg = new DaifenMessage((MailMessage)l_body);

            try
            {
               Trace.println("User Name = ", l_DaifenMsg.getUserName());
            }
            catch ( ParsingMessageException e )
            {
               System.out.println("Exception Error: " +
                                  "Unable to retrieve the User Name.");
            }
         }
         catch ( MessageException e )
         {
            System.out.println("Exception Error: " +
                               "Unable to retrieve the last bilan.");
         }
         catch ( IOException e )
         {
            System.out.println("Exception Error: " +
                               "Unable to retrieve the bilan body.");
         }

      }
      catch ( UnknownMailerClientException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
      catch ( ConnectionException e )
      {
         System.out.println("Exception Error: Problem to connect to .");
      }
      catch ( LoginException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
      catch ( PasswordException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***