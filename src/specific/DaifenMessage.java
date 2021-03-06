// CC_VERSIONS

/**
 * DaifenMessage.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Apr 9, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific;

import exception.ParsingMessageException;
import mailbox.MailBody;
import mailbox.MailMessage;
import netscape.messaging.mime.*;
import specific.data.api.DataContinentsAPI;
import specific.data.api.DataKingdomAPI;
import specific.parser.ContinentsParser;
import specific.parser.KingdomParser;
import specific.parser.MailParser;
import tools.Trace;

import java.io.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class DaifenMessage implements DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private MailMessage  _msg              = null;

   private boolean      _mailParsed       = false;

   MailParser           _continentParser  = new ContinentsParser();
   MailParser           _kingdomParser    = new KingdomParser();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public DaifenMessage(MailMessage p_msg)
   {
      if ( p_msg == null )
      {
         throw new NullPointerException();
      }

      _msg = p_msg;
   }


   public DaifenMessage(String p_file) throws FileNotFoundException
   {
      //==================== check if the file name exist ====================

      FileInputStream l_in = new FileInputStream(p_file);

      XMLDecoder l_decoder = new XMLDecoder(l_in);

      readObject(l_decoder);

      _mailParsed = true;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public DataKingdomAPI getKingdomDataAPI() throws ParsingMessageException
   {
      if ( isParsingMessageNeeded() ) parseMail();

      return (DataKingdomAPI) _kingdomParser;
   }

   public DataContinentsAPI getContinentsDataAPI() throws ParsingMessageException
   {
      if ( isParsingMessageNeeded() ) parseMail();

      return (DataContinentsAPI) _continentParser;
   }


   public void writeObject(String p_file) throws FileNotFoundException
   {
      createSpecificDirectory(p_file);

      XMLEncoder l_encoder = new XMLEncoder(new FileOutputStream(p_file));

      l_encoder.writeObject(new Boolean(_mailParsed));
      l_encoder.writeObject(_continentParser);
      l_encoder.writeObject(_kingdomParser);

      l_encoder.close();
   }


   public void parseMail() throws ParsingMessageException
   {
      Trace.enterFunction("DaifenMessage::parseMail()");

      _mailParsed = false;

      MailBody l_mailBody  = _msg;
      byte[]   l_bufBody      = _msg.getBody();

      ByteArrayInputStream l_buf = new ByteArrayInputStream(l_bufBody);

      MIMEParser l_MIMEparser = new MIMEParser();
      
      try
      {
         MIMEMessage l_MIMEmsg = l_MIMEparser.parseEntireMessage(l_buf);

         Trace.println("***************************************************");
         Trace.println(l_MIMEmsg.getHeader("Subject"));
         Trace.println(l_MIMEmsg.getContentType());
         Trace.println(l_MIMEmsg.getContentSubType());
         Trace.println(l_MIMEmsg.getContentTypeParams());

//         showObject(l_MIMEmsg);

         extractMailData(l_MIMEmsg);
      }
      catch ( MIMEException e )
      {
         e.printStackTrace();

         throw new ParsingMessageException();
      }

      _mailParsed = true;

      Trace.exitFunction("DaifenMessage::parseMail()");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void readObject(XMLDecoder p_decoder)
   {
      _mailParsed      = ((Boolean) p_decoder.readObject()).booleanValue();
      _continentParser = (MailParser) p_decoder.readObject();
      _kingdomParser   = (MailParser) p_decoder.readObject();
   }


   //@@@@@@@@@@@@@@@@@@@@   TEMPORARY USEFUL FUNCTION   @@@@@@@@@@@@@@@@@@@@@@


   void showObject (Object o)
   {
      byte[] b = new byte[100000];
      int m_nFileNo = 0;

      InputStream in = null;

      if  (o == null)
         return;

      if  (o instanceof MIMEMessage)
      {
         Trace.println("--------------- Start Mime Message ---------------");

         MIMEMessage m = (MIMEMessage) o;

         try
         {
            Header[] h = m.getAllHeaders();

            if (h != null)
               for  (int i=0; h != null && i < h.length; i++)
               {
                  Trace.println("message header = [" + h[i].getLine());
               }

            Trace.println("* message body type = " + m.getBodyType());
            Trace.println("* message content type = " + m.getContentType());
            Trace.println("* message content subtype = " + m.getContentSubType());
            Trace.println("* message content type param = " + m.getContentTypeParams());

            showObject (m.getBody(false));
            Trace.println("---------------- End Mime Message -----------------");
         }
         catch (MIMEException e)
         {
            Trace.println(e.getMessage());
         }
      }
      else if  (o instanceof MIMEBasicPart)
      {
         Trace.println("------------------- Start Mime BasicPart -------------------");

         try
         {
            MIMEBasicPart m = (MIMEBasicPart) o;
            in = m.getBodyData();

            if  (in != null)
            {
               int len = 0;

               if  (m.getMessageDataLen() < 100000)
                  len = in.read (b);

               Header[] h = m.getAllHeaders();

               if (h != null)
                  for  (int i=0; h != null && i < h.length; i++)
                  {
                     Trace.println("basic header = [" + h[i].getLine() );
                  }

               Trace.println("* basicpart content type = " + m.getContentType());
               Trace.println("* basicpart content subtype = " + m.getContentSubType());
               Trace.println("* basicpart content type param = " + m.getContentTypeParams());
               Trace.println("* basicpart content ID = " + m.getContentID());
               Trace.println("* basicpart content Disposition = " + m.getContentDisposition());
               Trace.println("* basicpart content Disposition params = " + m.getContentDispParams());
               Trace.println("* basicpart content Description = " + m.getContentDescription());
               Trace.println("* basicpart content MD5 = " + m.getContentMD5());
               Trace.println("* basicpart getbodysize() = " + m.getSize());
               Trace.println("* basicpart content encoding = " + m.getContentEncoding());

               Trace.println(">>>>>>>>>>>>>>>>>>>> start data >>>>>>>>>>>>>>>>>>");

               // base64
               if  (m.getContentEncoding() == 0)
                  Trace.println("[BASE64 BINARY DATA]");
               // QP
               else if  (m.getContentEncoding() == 1)
                  Trace.println("[QP DATA]");
               // text
               else
                  Trace.println(b.toString());

               Trace.println(">>>>>>>>>>>>>>>>>>>> end data >>>>>>>>>>>>>>>>>>>>");

               // base64
               if  (m.getContentEncoding() == 1 && m.getMessageDataLen() < 100000)
               {
//                  FileOutputStream out = new FileOutputStream ("bodydata" + m_nFileNo++ + ".out");
//                  out.write (b, 0, m.getMessageDataLen());
//                  out.close();
                  Trace.println(b.toString());
               }

               in.close();
            }
         }
         catch (MIMEException e)
         {
            Trace.println(e.getMessage());
         }
         catch (IOException e)
         {
            Trace.println(e.getMessage());
         }

         Trace.println("------------------- End Mime BasicPart -------------------");
      }
      else if  (o instanceof MIMEMultiPart)
      {
         Trace.println("------------------- Start Mime MultiPart  -------------------");

         MIMEMultiPart m = (MIMEMultiPart) o;
         int count = m.getBodyPartCount();

         // debug
         Trace.println("* multipart content type = " + m.getContentType());
         Trace.println("* multipart content subtype = " + m.getContentSubType());
         Trace.println("* multipart content type param = " + m.getContentTypeParams());
         Trace.println("* multipart content ID = " + m.getContentID());
         Trace.println("* multipart content Disposition = " + m.getContentDisposition());
         Trace.println("* multipart content Disposition params = " + m.getContentDispParams());
         Trace.println("* multipart content Description = " + m.getContentDescription());

         if (count > 0)
            for  (int i = 0; i < count; i++)
            {
               try
               {
                  Object part = m.getBodyPart(i, false);
                  showObject (part);
               }
               catch (MIMEException e)
               {
                  Trace.println(e.getMessage());
               }
            }

         Trace.println("------------------- End Mime MultiPart ------------------");
      }

      else if  (o instanceof MIMEMessagePart)
      {
         Trace.println("------------------- Start Mime MessagePart ----------------");

         MIMEMessagePart m = (MIMEMessagePart) o;

         try
         {
            MIMEMessage part = m.getMessage(false);

            Header[] h = m.getAllHeaders();

            if (h != null)
               for  (int i=0; h != null && i < h.length; i++)
               {
                  Trace.println("messagepart header = [" + h[i].getLine() );
               }

            Trace.println("* messagepart content type = " + m.getContentType());
            Trace.println("* messagepart content subtype = " + m.getContentSubType());
            Trace.println("* messagepart content type param = " + m.getContentTypeParams());
            Trace.println("* messagepart content ID = " + m.getContentID());
            Trace.println("* messagepart content Disposition = " + m.getContentDisposition());
            Trace.println("* messagepart content Disposition params = " + m.getContentDispParams());
            Trace.println("* messagepart content Description = " + m.getContentDescription());
            Trace.println("* messagepart content encoding = " + m.getContentEncoding());

            showObject (part);
         }
         catch (MIMEException e)
         {
            Trace.println(e.getMessage());
         }

         Trace.println("------------------- End Mime MessagePart  -----------------");
      }
   }

   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private void extractMailData(MIMEMessage p_MIMEmsg) throws MIMEException,
                                                              ParsingMessageException
   {
      Trace.enterFunction("DaifenMessage::parseMainPart()");

      //================= check if the mail message exist ====================

      if ( p_MIMEmsg == null )
      {
         throw new NullPointerException();
      }

      //========= check if the createIterator part of the message is =========
      //======================== type of MIMEMultiPart =======================

      MIMEMultiPart l_multiPart = null;

      if ( p_MIMEmsg.getContentType().equals("MultiPart") )
      {
         l_multiPart = (MIMEMultiPart) p_MIMEmsg.getBody(false);
      }
      else
      {
         throw new ParsingMessageException();
      }

      //========== check the mail message contains at least 2 parts ==========

      if ( l_multiPart.getBodyPartCount() < 2 )
      {
         throw new ParsingMessageException();
      }

      //========= This createIterator part corresponds to the list of ========
      //=================== eliminated and destroyed kingdom =================

      Object l_firstPart = l_multiPart.getBodyPart(0, false);

      if ( l_firstPart instanceof MIMEBasicPart )
      {
         MIMEBasicPart l_continents = (MIMEBasicPart) l_firstPart;

         if ( l_continents.getContentEncoding() != E7BIT )
         {
            throw new ParsingMessageException();
         }

         //----------------- parse the Continents datas ----------------------

         try
         {
            _continentParser.setData(l_continents.getBodyData());

            _continentParser.parse();
         }
         catch ( IOException e )
         {
            throw new ParsingMessageException();
         }
      }
      else
      {
         throw new ParsingMessageException();
      }

      //====== The second part corresponds to the BASE64 attached file =======
      //================== that contains the kingdom outcome =================

      Object l_secondPart = l_multiPart.getBodyPart(1, false);

      if ( l_secondPart instanceof MIMEBasicPart )
      {
         MIMEBasicPart l_kingdom = (MIMEBasicPart) l_secondPart;

         if ( l_kingdom.getContentEncoding() != BASE64 )
         {
            throw new ParsingMessageException();
         }

         try
         {
            _kingdomParser.setData(l_kingdom.getBodyData());

            _kingdomParser.parse();
         }
         catch ( IOException e )
         {
            throw new ParsingMessageException();
         }
      }
      else
      {
         throw new ParsingMessageException();
      }

      Trace.exitFunction("DaifenMessage::parseMainPart()");
   }


   private void createSpecificDirectory(String p_file)
   {
      File     l_file = new File(p_file);
      String   l_path = l_file.getParent();

      if ( l_path != null )
      {
         new File(l_path).mkdirs();
      }
   }


   private boolean isParsingMessageNeeded()
   {
      return _mailParsed == false;
   }
}

//*** EOF ************************************************************ EOF ***