// CC_VERSIONS

/**
 * POP3_Message.java
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

import mailbox.MailMessage;
import tools.Trace;

import java.util.HashMap;


public class POP3_Message extends MailMessage implements POP3_Constants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public POP3_Message(int p_index, HashMap p_header)
   {
      super(p_index, p_header);
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public String getFrom()
   {
      String l_text = _header.get(CST_FROM).toString();

      Trace.println("POP3_Message::getFrom() -> ", "return(" + l_text + ")");

      return l_text;
   }

   public String getTo()
   {
      String l_text = _header.get(CST_TO).toString();

      Trace.println("POP3_Message::getTo() -> ", "return(" + l_text + ")");

      return l_text;
   }

   public String getDate()
   {
      String l_text = _header.get(CST_DATE).toString();

      Trace.println("POP3_Message::getDate() -> ", "return(" + l_text + ")");

      return l_text;
   }

   public String getSubject()
   {
      String l_text = _header.get(CST_SUBJECT).toString();

      Trace.println("POP3_Message::getSubject() -> ", "return(" + l_text + ")");

      return l_text;
   }

   public String getBody()
   {
      String l_text = _body;

      Trace.println("POP3_Message::getBody() -> ", "return(" + l_text + ")");

      return l_text;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***