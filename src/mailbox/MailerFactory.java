// CC_VERSIONS

/**
 * MailerFactory.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Mar 30, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package mailbox;

import exception.UnknownMailerClientException;
import tools.Trace;


public class MailerFactory
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

//   String[] _availableMailerList = { "POP3" };


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public MailerFactory()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public MailBoxAccess createMailerClient(String p_mailerType)
      throws UnknownMailerClientException
   {
      Trace.enterFunction("MailerFactory::createMailerClient()");

      MailBoxAccess l_mailerClient = null;

      try
      {
         Class l_class = Class.forName("mailbox." + p_mailerType + "." +
                                       p_mailerType + "_MailBoxAccess");

         l_mailerClient = (MailBoxAccess) l_class.newInstance();
      }
      catch ( ClassNotFoundException e )
      {
         e.printStackTrace();

         Trace.println("EXCEPTION: ", "ClassNotFoundException...");

         throw new UnknownMailerClientException();
      }
      catch ( InstantiationException e )
      {
         e.printStackTrace();

         Trace.println("EXCEPTION: ", "InstantiationException...");

         throw new UnknownMailerClientException();
      }
      catch ( IllegalAccessException e )
      {
         e.printStackTrace();

         Trace.println("EXCEPTION: ", "IllegalAccessException...");

         throw new UnknownMailerClientException();
      }


      Trace.exitFunction("MailerFactory::createMailerClient()",
                         l_mailerClient.toString());

      return l_mailerClient;
   }

   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***