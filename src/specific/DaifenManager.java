package specific;

// CC_VERSIONS

import exception.*;
import mailbox.MailBody;
import mailbox.MailBoxAccess;
import mailbox.MailMessage;
import mailbox.MailerFactory;
import tools.Trace;

import java.io.IOException;

/**
 * specific.DaifenManager.java
 *
 * DESCRIPTION:
 *
 *    @author        J-P HILAIRE  -  Mar 29, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */


public class DaifenManager
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   MailBoxAccess _mailBox = null;

   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public DaifenManager(String p_mailerType,
                        String p_server,
                        String p_user,
                        String p_password) throws UnknownMailerClientException,
                                                  ConnectionException,
                                                  LoginException,
                                                  PasswordException
   {
      Trace.enterFunction("specific.DaifenManager::specific.DaifenManager()");

      MailerFactory l_factory = new MailerFactory();

      _mailBox = l_factory.createMailerClient(p_mailerType);

      _mailBox.connect(p_server);

      _mailBox.user(p_user);

      _mailBox.password(p_password);

      Trace.exitFunction("specific.DaifenManager::specific.DaifenManager()");
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public MailMessage getLastBilan() throws MessageException
   {
      Trace.enterFunction("specific.DaifenManager::getLastBilan()");

      MailMessage l_msg = _mailBox.findLastMessage(
         "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+(\\w+)");

      Trace.exitFunction("specific.DaifenManager::getLastBilan()");

      return l_msg;
   }

   public MailMessage getBilan(String p_moon) throws MessageException
   {
      Trace.enterFunction("specific.DaifenManager::getLastBilan()");

      MailMessage l_msg = _mailBox.findLastMessage(
         "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+" + p_moon);

      Trace.exitFunction("specific.DaifenManager::getLastBilan()");

      return l_msg;
   }

   public MailBody getBody(MailMessage p_msg) throws MessageException,
                                                        IOException
   {
      Trace.enterFunction("specific.DaifenManager::getBody()");

//      String l_body = new String();

      MailBody l_mailBody = _mailBox.getMessageBody(p_msg);

//      MIMEParser l_parser = new MIMEParser();
//
//
//      l_parser.parseEntireMessage();

      Trace.exitFunction("specific.DaifenManager::getBody()");

      return l_mailBody;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***