// CC_VERSIONS

/**
 * POP3_Sink.java
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
import netscape.messaging.pop3.POP3Sink;
import tools.Trace;

import java.util.HashMap;


public class POP3_Sink extends POP3Sink
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private int             _nbMsg         = 0;
   private int             _MBoxSize      = 0;

   private POP3_Message    _tmpMessage    = null;

   private HashMap         _tmpHeaderMap  = null;
   private byte[]          _tmpBody       = null;

   private int             _index         = -1;
   private String          _key           = new String();
   private String          _value         = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public POP3_Sink()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public int getNBMessage()
   {
      return _nbMsg;
   }


   public int getMBoxSize()
   {
      return _MBoxSize;
   }


   public MailMessage getMailMessage()
   {
      return _tmpMessage;
   }


   public byte[] getMailBody()
   {
      return _tmpBody;
   }


   //============================= API implementation ========================

   public void stat(int i, int i1)
   {
      super.stat(i, i1);

      _nbMsg      = i;
      _MBoxSize   = i1;
   }

   public void topStart(int i)
   {
      super.topStart(i);

      _index      = i;
      _key        = "";
      _value      = "";
      _tmpHeaderMap  = new HashMap();
   }

   public void top(StringBuffer stringBuffer)
   {
      super.top(stringBuffer);

      String l_line = new String(stringBuffer);
      Trace.println("-----------------------------------------------");
      Trace.println("Line: " + l_line);

      // check if the line is a valid key line

      if ( l_line.length() != 0 )
      {
         if ( l_line.charAt(0) == '\t' || l_line.charAt(0) == ' ' )
         {
            _value = _value + "\n" + l_line;
         }
         else
         {
            if ( _key.length() != 0 )
            {
               Trace.println("Key  : " + _key);
               Trace.println("Value: " + _value);

               _tmpHeaderMap.put(_key, _value);
            }

            _key   = l_line.substring(0, l_line.indexOf(": "));
            _value = l_line.substring(l_line.indexOf(": ") + 2,
                    l_line.length());
         }
      }
   }

   public void topComplete()
   {
      super.topComplete();

      if ( _key.length() != 0 )
      {
         Trace.println("Key  : " + _key);
         Trace.println("Value: " + _value);

         _tmpHeaderMap.put(_key, _value);
      }

      // generate the corresponding MailMessage

      _tmpMessage = new POP3_Message(_index, _tmpHeaderMap);

      Trace.println("Subject: ",_tmpHeaderMap.get("Subject").toString());
   }


   public void retrieveStart(int i, int i1)
   {
      super.retrieveStart(i, i1);

      _tmpBody = null;

      Trace.println("1111111111111111111111111111111111111111111111111");
   }

   public void retrieve(byte[] p_bytes)
   {
      super.retrieve(p_bytes);

      byte[] tmp    = null;

      if ( _tmpBody == null )
      {
         _tmpBody = new byte[p_bytes.length];

         for ( int i = 0 ; i < p_bytes.length ; i++ )
         {
            _tmpBody[i] = p_bytes[i];
         }
      }
      else
      {
         tmp   = new byte[_tmpBody.length + p_bytes.length];

         for ( int i = 0 ; i < _tmpBody.length ; i++ )
         {
            tmp[i] = _tmpBody[i];
         }
         for ( int i = 0 ; i < p_bytes.length ; i++ )
         {
            tmp[i + _tmpBody.length] = p_bytes[i];
         }

         _tmpBody = tmp;
      }
   }

   public void retrieveComplete()
   {
      super.retrieveComplete();

      Trace.println("2222222222222222222222222222222222222222222222222");
   }



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***