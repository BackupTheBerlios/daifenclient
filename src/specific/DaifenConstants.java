// CC_VERSIONS

/**
 * POP3_Constants.java
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

package specific;


public interface DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

//   public final String     CST_ = "";

   public final String     CST_USERNAME         = "UserName";
   public final String     CST_CONTINENT        = "Continent";
   public final String     CST_MOON             = "Moon";


   //========================= MIME section type =============================

   public final int BASE64 = 0;
   public final int QP     = 1;
   public final int BINARY = 2;
   public final int E7BIT  = 3;
   public final int E8BIT  = 4;


   ///////////////////////// Daifen MIME Mail section \\\\\\\\\\\\\\\\\\\\\\\\

   //------------------------ Continents information -------------------------

   //.. regexp or string corresponding to specific part of the Daifen bilan ..

   public final String CST_DESTROYED_KINGDOM    = "Royaumes d.truits :";
   public final String CST_ELIMINATED_KINGDOM   = "Royaumes .limin.s :";
   public final String CST_KINGDOM_REGEXP       = " - ([\\w ]+) \\((\\w+) - ([\\w -]+)\\)";

   //.............. flag that indicates in which section we are ..............

   public final int SECTION_UNKNOWN             = 0;
   public final int SECTION_DESTROYED_KIGNDOM   = 1;
   public final int SECTION_ELIMINATED_KINGDOM  = 2;


   //-------------------------- Kingdom information --------------------------

   //.. regexp or string corresponding to specific part of the Daifen bilan ..

   public final String CST_RUMOUR               = "Rumeurs +\\(. .\\)";
   public final String CST_RUMOUR_BEGIN_REGEXP  = "A propos de ([\\w ]+) : *";
   public final String CST_RUMOUR_MESG          = "(.+)";
   public final String CST_RUMOUR_END           = " +--- *";

   //.............. flag that indicates in which section we are ..............

   public final int SECTION_RUMOUR              = 1;
   public final int SECTION_RUMOUR_BEGIN_REGEXP = 2;
   public final int SECTION_RUMOUR_MESG         = 3;
   public final int SECTION_RUMOUR_END          = 4;

}

//*** EOF ************************************************************ EOF ***