// CC_VERSIONS

/**
 * POP3_MailBoxAccess.java
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

package mailbox.POP3;

import exception.ConnectionException;
import exception.LoginException;
import exception.MessageException;
import exception.PasswordException;
import mailbox.MailBody;
import mailbox.MailBoxAccess;
import mailbox.MailMessage;
import tools.Trace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;


public class POP3_MailBoxAccess extends MailBoxAccess
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private POP3_Sink   _pop3Sink    = null;
   private POP3_Client _pop3Client  = null;


   //===============================   PROTECTED   ===========================


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public POP3_MailBoxAccess()
   {
      _pop3Sink   = new POP3_Sink();
      _pop3Client = new POP3_Client(_pop3Sink);
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public void user(String p_login) throws LoginException
   {
      Trace.enterFunction("POP3_MailBoxAccess::user()");

      try
      {
         setLogin(p_login);
         _pop3Client.user(getLogin());
      }
      catch ( IOException e )
      {
         throw new LoginException();
      }

      Trace.exitFunction("POP3_MailBoxAccess::user()");
   }

   public void password(String p_password) throws PasswordException
   {
      Trace.enterFunction("POP3_MailBoxAccess::password()");

      try
      {
         setPassword(p_password);
         _pop3Client.pass(getPassword());
      }
      catch ( IOException e )
      {
         throw new PasswordException();
      }

      Trace.exitFunction("POP3_MailBoxAccess::password()");
   }

   public void connect(String p_server) throws ConnectionException
   {
      Trace.enterFunction("POP3_MailBoxAccess::connect()");

      try
      {
         setServer(p_server);
         _pop3Client.connect(getServer());
      }
      catch (IOException e)
      {
         throw new ConnectionException();
      }

      Trace.exitFunction("POP3_MailBoxAccess::connect()");
   }

   public void disconnect() throws ConnectionException
   {
      Trace.enterFunction("POP3_MailBoxAccess::disconnect()");

      try
      {
         _pop3Client.disconnect();
      }
      catch (IOException e)
      {
         throw new ConnectionException();
      }
      
      Trace.exitFunction("POP3_MailBoxAccess::disconnect()");
   }

   public void refreshDownloadedHeadersList() throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::refreshDownloadedHeadersList()");

      //========== check if they are some new message on the server ==========

      int l_nbMsgOnSrv = getNbMessages();

      if ( getLstMsg().size() < l_nbMsgOnSrv )
      {
         //------------ they are some new messages on the server -------------
         //....................... clean the cached list .....................

         Iterator l_iter = getLstMsg().iterator();

         while ( l_iter.hasNext() )
            l_iter.remove();

         //................ download the server messages list ................

         for ( int i = 1 ; i <= l_nbMsgOnSrv ; i++ )
         {
            MailMessage l_msg = downloadMessage(i);

            getLstMsg().add(l_msg);
         }
      }

      Trace.exitFunction("POP3_MailBoxAccess::refreshDownloadedHeadersList()");
   }

   public int getNbMessages() throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::getNbMessages()");

      int l_nbMessage = 0;

      try
      {
         _pop3Client.stat();

         l_nbMessage = _pop3Sink.getNBMessage();
      }
      catch ( IOException e )
      {
         throw new MessageException();
      }

      Trace.exitFunction("POP3_MailBoxAccess::getNbMessages()",
                         new Integer(l_nbMessage).toString());

      return l_nbMessage;
   }

   public MailMessage getMessage(int i) throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::getMessage()");

      refreshDownloadedHeadersList();

      //----------- check if the index message is not out of range -----------

      MailMessage l_msg    = null;
      int         l_index  = i + 1;             //.......... index starts at 1

      if ( l_index > 0 && l_index <= getLstMsg().size() )
      {
         l_msg = getLstMsg().getMail(l_index);
      }

      Trace.exitFunction("POP3_MailBoxAccess::getMessage()");

      return l_msg;
   }

   public MailBody getMessageBody(MailMessage p_msg) throws MessageException,
                                                            IOException
   {
      Trace.enterFunction("POP3_MailBoxAccess::getMessageBody()");

      int l_index = p_msg.getIndex();

      _pop3Client.retrieve(l_index);

      //--------------------- set the message body ---------------------------

      MailMessage l_msg = getLstMsg().getMail(l_index);

      l_msg.setBody(_pop3Sink.getMailBody());

      Trace.exitFunction("POP3_MailBoxAccess::getMessageBody()");

      return p_msg;
   }

   public MailMessage[] findMessages(String p_regExp) throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::findMessages()");

      refreshDownloadedHeadersList();

      //================ get the list of the matching messages ===============

      ArrayList      l_lstMsg = new ArrayList();
      MailMessage[]  l_arrMsg = null;

      //--------------- generate the regular expression pattern --------------

      Pattern l_pattern = Pattern.compile(p_regExp);

      //---------- check if each cached messaged matches the pattern ---------

      Iterator l_iter   = getLstMsg().iterator();
      int      l_index  = 0;

      while ( l_iter.hasNext() )
      {
         MailMessage l_msg = (MailMessage) l_iter.next();

         if ( l_pattern.matcher(l_msg.getSubject()).matches() )
         {
            l_lstMsg.add(l_msg);

            l_index++;
            Trace.println("Message[" + l_index + "] = ", l_msg.getSubject());
         }
      }

      //-------------------- create the returned object ----------------------

      l_arrMsg = new MailMessage[l_lstMsg.size()];
      l_arrMsg = (MailMessage[]) l_lstMsg.toArray(l_arrMsg);

      Trace.exitFunction("POP3_MailBoxAccess::findMessages()");

      return l_arrMsg;
   }

   public MailMessage findLastMessage(String p_regExp) throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::findLastMessage()");

      MailMessage[] l_lstMsg = findMessages(p_regExp);
      MailMessage   l_msg    = null;

      if ( l_lstMsg != null )
      {
         l_msg = l_lstMsg[l_lstMsg.length-1];
      }

      Trace.exitFunction("POP3_MailBoxAccess::findLastMessage()");

      return l_msg;
   }

   public Iterator iterator() throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::iterator()");

      refreshDownloadedHeadersList();

      Iterator l_iter = getLstMsg().iterator();

      Trace.exitFunction("POP3_MailBoxAccess::iterator()");

      return l_iter;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected MailMessage downloadMessage(int i) throws MessageException
   {
      Trace.enterFunction("POP3_MailBoxAccess::downloadMessage()");

      MailMessage l_message = null;

      try
      {
         _pop3Client.top( i, 0 );

         l_message = _pop3Client.getMailMessage();
      }
      catch ( IOException e )
      {
         throw new MessageException();
      }

      Trace.exitFunction("POP3_MailBoxAccess::downloadMessage()");

      return l_message;
   }



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***