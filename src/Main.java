// CC_VERSIONS

import exception.*;
import mailbox.MailBoxAccess;
import mailbox.MailHeader;
import mailbox.MailMessage;
import mailbox.MailerFactory;
import tools.Trace;

import java.io.File;
import java.util.Iterator;

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

      MailerFactory l_factory = new MailerFactory();

      try
      {
         MailBoxAccess l_mailbox = l_factory.createMailerClient("POP3");

         Trace.println("Connecting to yahoo...");
         l_mailbox.connect("pop.mail.yahoo.fr");

         l_mailbox.user("stollvor");
         l_mailbox.password("Skywalker");

         String l_nbMsg = new Integer(l_mailbox.getNbMessages()).toString();
         Trace.println("NB Messages = ", l_nbMsg);

         // GET MESSAGE
         MailHeader l_msg = l_mailbox.getMessage(1);

         l_msg.printOn();

         // ITERATOR
         Trace.println("Using the Iterator");

         Iterator l_iter = l_mailbox.iterator();

         while ( l_iter.hasNext() )
         {
            l_msg = (MailHeader) l_iter.next();

            Trace.println("Message Header = ", l_msg.toString());
         }

         //FIND MESSAGES
//         MailMessage[] l_arrMsg = l_mailbox.findMessages("Daifen");
         MailMessage[] l_arrMsg = l_mailbox.findMessages(
            "\\[Daifen\\.com\\]\\s+Tour\\s+(\\d+)\\s-\\s+(\\w+)");

         for ( int i = 0 ; i < l_arrMsg.length ; i++)
         {
            System.out.println("Message[" + i + "] = " +
                               l_arrMsg[i].getSubject());
         }

         Trace.println("Disconnecting from yahoo...");
         l_mailbox.disconnect();
      }
      catch ( UnknownMailerClientException e )
      {
         Trace.println("EXCEPTION: ", "UnknownMailerClientException...");
      }
      catch ( ConnectionException e )
      {
         e.printStackTrace();
         Trace.println("EXCEPTION: ", "Problem with the mailer connection");
      }
      catch ( LoginException e )
      {
         Trace.println("EXCEPTION: ", "Problem with the login user name");
      }
      catch ( PasswordException e )
      {
         Trace.println("EXCEPTION: ", "Problem with the password user name");
      }
      catch ( MessageException e )
      {
         Trace.println("EXCEPTION: ", "MessageException...");
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