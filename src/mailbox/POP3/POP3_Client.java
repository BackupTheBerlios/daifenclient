// CC_VERSIONS

/**
 * POP3_Client.java
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

package mailbox.POP3;

import mailbox.MailMessage;
import netscape.messaging.pop3.IPOP3Sink;
import netscape.messaging.pop3.POP3Client;

import java.io.IOException;


public class POP3_Client extends POP3Client
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private POP3_Sink _pop3SinkRef = null;

   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public POP3_Client(IPOP3Sink p_ipop3Sink)
   {
      super(p_ipop3Sink);

      _pop3SinkRef = (POP3_Sink) p_ipop3Sink;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public MailMessage getMailMessage()
   {
      return _pop3SinkRef.getMailMessage();
   }

   public MailMessage getMailBody()
   {
      return _pop3SinkRef.getMailMessage();
   }

   //============================ API Implementation =========================


   public synchronized void user(String s) throws IOException
   {
      super.user(s);
      processResponses();
   }

   public synchronized void pass(String s) throws IOException
   {
      super.pass(s);
      processResponses();
   }

   public synchronized void connect(String s) throws IOException
   {
      super.connect(s);
      processResponses();
   }

   public synchronized void disconnect() throws IOException
   {
      super.quit();
      processResponses();
   }

   public synchronized void stat() throws IOException
   {
      super.stat();
      processResponses();
   }

   public synchronized void retrieve(int i) throws IOException
   {
      super.retrieve(i);
      processResponses();
   }

   public synchronized void top(int i, int i1) throws IOException
   {
      super.top(i, i1);
      processResponses();
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***