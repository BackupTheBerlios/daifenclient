// CC_VERSIONS

/**
 * EconomyInfo.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 4, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.data.info;


public class EconomyInfo extends Info
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private int _gold       = -1;
   private int _intellect  = -1;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public EconomyInfo() {}

   public EconomyInfo(int p_gold, int p_intellect)
   {
      _gold       = p_gold;
      _intellect  = p_intellect;
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public int getGold     () { return _gold; }
   public int getIntellect() { return _intellect; }

   public void setGold(int p_gold)
   {
      _gold = p_gold;
   }

   public void setIntellect(int p_intellect)
   {
      _intellect = p_intellect;
   }


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