// CC_VERSIONS

/**
 * ContactInfo.java
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


public class ContactInfo extends Info
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _kingdom = new String();
   private String _species = new String();
   private String _email   = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContactInfo() {}

   public ContactInfo(String p_kingdom, String p_species, String p_email)
   {
      _kingdom = p_kingdom;
      _species = p_species;
      _email   = p_email;
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public String getKingdom() { return _kingdom; };
   public String getSpecies() { return _species; };
   public String getEmail()   { return _email; };

   public void setKingdom(String p_kingdom)
   {
      _kingdom = p_kingdom;
   }

   public void setSpecies(String p_species)
   {
      _species = p_species;
   }

   public void setEmail(String p_email)
   {
      _email = p_email;
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