// CC_VERSIONS

/**
 * DaifenMessage.java
 *
 * DESCRIPTION:
 *
 *    @author        J-P HILAIRE  -  Apr 9, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific;

import exception.ParsingMessageException;
import mailbox.MailMessage;
import mailbox.MailBody;
import netscape.messaging.mime.MIMEException;
import netscape.messaging.mime.MIMEMessage;
import netscape.messaging.mime.MIMEParser;
import tools.Trace;

import java.io.ByteArrayInputStream;
import java.util.HashMap;


public class DaifenMessage implements DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private MailMessage  _msg              = null;

   private HashMap      _messageContent   = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public DaifenMessage(MailMessage p_msg)
   {
      _msg = p_msg;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public String getUserName() throws ParsingMessageException
   {
      if ( isParsingMessageNeeded() ) parseMail();

      String l_userName = new String();

      if ( _messageContent.containsKey(CST_USERNAME) )
      {
         l_userName = _messageContent.get(CST_USERNAME).toString();
      }

      return l_userName;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void parseMail() throws ParsingMessageException
   {
      _messageContent = new HashMap();

      MailBody l_mailBody  = _msg;
      String   l_text      = _msg.getBody();

      ByteArrayInputStream l_buf = new ByteArrayInputStream(l_text.getBytes());

      MIMEParser l_MIMEparser = new MIMEParser();
      try
      {
         MIMEMessage l_MIMEmsg = l_MIMEparser.parseEntireMessage(l_buf);

         Trace.println("***************************************************");
         Trace.println(l_MIMEmsg.getHeader("Subject"));
         Trace.println(l_MIMEmsg.getContentType());
         Trace.println(l_MIMEmsg.getContentSubType());
         Trace.println(l_MIMEmsg.getContentTypeParams());

//MIMEMessagePart l_multi =
      }
      catch ( MIMEException e )
      {
         e.printStackTrace();
      }


   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private boolean isParsingMessageNeeded()
   {
      return _messageContent == null;
   }
}

//*** EOF ************************************************************ EOF ***