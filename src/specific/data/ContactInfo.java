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

package specific.data;


public class ContactInfo
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _king    = new String();
   private String _species = new String();
   private String _email   = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContactInfo(String p_king, String p_species, String p_email)
   {
      _king    = p_king;
      _species = p_species;
      _email   = p_email;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public String getKing()    { return _king; };
   public String getSpecies() { return _species; };
   public String getEmail()   { return _email; };


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***