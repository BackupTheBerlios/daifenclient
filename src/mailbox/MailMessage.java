// CC_VERSIONS

/**
 * MailMessage.java
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

package mailbox;

import java.util.HashMap;


public abstract class MailMessage implements MailHeader, MailBody
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================

   protected int        _index   = -1;
   protected HashMap    _header  = null;

   protected byte[]     _bufBody    = null;


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public MailMessage(int p_index, HashMap p_header)
   {
      _index   = p_index;
      _header  = p_header;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public int getIndex()
   {
      return _index;
   }

   public String toString()
   {
      return _header.entrySet().toString();
   }

   public void printOn()
   {
      System.out.println("Message Header = " + _header.entrySet().toString());
   }

   public byte[] getBody()
   {
      return _bufBody;
   }

   public void setBody(byte[] p_body)
   {
      _bufBody = p_body;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***