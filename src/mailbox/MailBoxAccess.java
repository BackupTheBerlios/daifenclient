// CC_VERSIONS

/**
 * mailbox.java
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

package mailbox;

import exception.ConnectionException;
import exception.LoginException;
import exception.MessageException;
import exception.PasswordException;

import java.io.IOException;
import java.util.Iterator;


public abstract class MailBoxAccess
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String             _server     = new String();
   private String             _login      = new String();
   private String             _password   = new String();


   //===============================   PROTECTED   ===========================

   private MailMessagesList   _lstMsg     = new MailMessagesList();


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public abstract void user    (String p_login)     throws LoginException;
   public abstract void password(String p_password)  throws PasswordException;

   public abstract void connect   (String p_server)  throws ConnectionException;
   public abstract void disconnect()                 throws ConnectionException;

   public abstract int         getNbMessages ()      throws MessageException;
   public abstract MailMessage getMessage    (int i) throws MessageException;

   public abstract MailBody    getMessageBody(MailMessage p_msg)
      throws MessageException, IOException;

   public abstract void refreshDownloadedHeadersList() throws MessageException;

   public abstract MailMessage[] findMessages(String p_regExp)
                                                     throws MessageException;
   public abstract MailMessage   findLastMessage(String p_regExp)
                                                     throws MessageException;

   public abstract Iterator    iterator()            throws MessageException;

   public final    String      getServer()         { return _server;   }
   public final    String      getLogin()          { return _login;    }
   public final    String      getPassword()       { return _password; }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void setServer  (String p_server)   { _server   = p_server;   }
   protected void setLogin   (String p_login)    { _login    = p_login;    }
   protected void setPassword(String p_password) { _password = p_password; }

   protected final MailMessagesList getLstMsg() { return _lstMsg; }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***