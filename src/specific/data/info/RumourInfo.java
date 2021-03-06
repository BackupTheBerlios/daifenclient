// CC_VERSIONS

/**
 * RumourInfo.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 28, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.data.info;


public class RumourInfo extends Info
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _kingdom   = new String();
   private String _rumour    = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public RumourInfo() {}


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public String getKingdom() { return _kingdom; }
   public String getRumour () { return _rumour; }

   public void setKingdom  (String p_kingdom) { _kingdom  = p_kingdom; }
   public void setRumour   (String p_rumour)  { _rumour   = p_rumour; }
   public void appendRumour(String p_rumour)  { _rumour  += p_rumour; }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   

   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***