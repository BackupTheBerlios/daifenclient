// CC_VERSIONS

/**
 * KingdomInfo.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 26, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.data;


public class KingdomInfo
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _kingdom   = new String();
   private String _species   = new String();
   private String _continent = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KingdomInfo(String p_kingdom,
                      String p_species,
                      String p_continent)
   {
      _kingdom   = p_kingdom;
      _species   = p_species;
      _continent = p_continent;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public String getKingdom  () { return _kingdom; }
   public String getSpecies  () { return _species; }
   public String getContinent() { return _continent; }



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***