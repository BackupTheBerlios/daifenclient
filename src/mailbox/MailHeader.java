// CC_VERSIONS

/**
 * MailHeader.java
 *
 * DESCRIPTION:
 *
 *    @author        J-P HILAIRE  -  Apr 1, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package mailbox;


public interface MailHeader
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   public abstract String getFrom   ();
   public abstract String getTo     ();
   public abstract String getDate   ();

   public abstract String getSubject();

   public abstract void printOn     ();
}

//*** EOF ************************************************************ EOF ***