// CC_VERSIONS

/**
 * MailBody.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Apr 1, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package mailbox;


public interface MailBody
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

//   public abstract String getBody();
   public abstract byte[] getBody();

   public abstract void printOn();
}

//*** EOF ************************************************************ EOF ***