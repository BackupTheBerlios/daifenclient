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

package specific.data.info;


public class KingdomInfo extends Info
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

   public KingdomInfo() {}

   public KingdomInfo(String p_kingdom,
                      String p_species,
                      String p_continent)
   {
      _kingdom   = p_kingdom;
      _species   = p_species;
      _continent = p_continent;
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public String getKingdom  () { return _kingdom; }
   public String getSpecies  () { return _species; }
   public String getContinent() { return _continent; }

   public void setKingdom(String p_kingdom)
   {
      _kingdom = p_kingdom;
   }

   public void setSpecies(String p_species)
   {
      _species = p_species;
   }

   public void setContinent(String p_continent)
   {
      _continent = p_continent;
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