// CC_VERSIONS

/**
 * KingdomParserConstants.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 27, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser;




public interface KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************


   ///////////////////////// Daifen MIME Mail section \\\\\\\\\\\\\\\\\\\\\\\\

   //-------------------------- Kingdom information --------------------------

   //.. regexp or string corresponding to specific part of the Daifen bilan ..

   public final String CST_RUMOUR         = "Rumeurs +\\(. .\\)";
   public final String CST_RUMOUR_BEGIN   = "A propos de (.+) : *";
   public final String CST_RUMOUR_MESG    = "(.+)";
   public final String CST_RUMOUR_END     = " +--- *";
   public final String CST_ECONOMIE       = "Economie +\\(. .\\)";
   public final String CST_MILITAIRE      = "Militaire +\\(. .\\)";
   public final String CST_INV_TROUPES    = "Inventaire des troupes :";
   public final String CST_INV_BATIMENTS  = "Inventaire des B.timents :";
   public final String CST_CONNAISSANCES  = "Connaissances acquises :";
   public final String CST_CONTACTS       = "Royaumes Connus :";
   public final String CST_SOCIAL         = "Social +\\(. .\\)";

   public final String CST_ECONOMIE_REGEXP       = "\\| Total Fin du tour +\\| (\\d+) +\\| (\\d+) +\\|";
   public final String CST_INV_TROUPES_REGEXP    = "\\| (.+\\w) +\\| (\\d+) +\\| +\\|";
   public final String CST_CONNAISSANCES_REGEXP  = "- (.+\\w) \\(Tour (\\d+)\\)";
   public final String CST_CONTACTS_REGEXP       = "- (.+\\w) \\((\\w+)\\) Contact : ([\\w@\\.\\-]+)";


   //.............. flag that indicates in which section we are ..............

   public final int SECTION_RUMOUR        = 21;
   public final int SECTION_RUMOUR_BEGIN  = 22;
   public final int SECTION_RUMOUR_MESG   = 23;
   public final int SECTION_RUMOUR_END    = 24;
   public final int SECTION_ECONOMIE      = 30;
   public final int SECTION_MILITAIRE     = 35;
   public final int SECTION_INV_TROUPES   = 40;
   public final int SECTION_INV_BATIMENTS = 45;
   public final int SECTION_CONNAISSANCES = 50;
   public final int SECTION_CONTACTS      = 55;
   public final int SECTION_SOCIAL        = 60;
}

//*** EOF ************************************************************ EOF ***