// CC_VERSIONS

/**
 * ContinentsParserConstants.java
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




interface ContinentsParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************


   ///////////////////////// Daifen MIME Mail section \\\\\\\\\\\\\\\\\\\\\\\\

   //------------------------ Continents information -------------------------

   //.. regexp or string corresponding to specific part of the Daifen bilan ..

   public final String CST_DESTROYED_KINGDOM    = "Royaumes d.truits :";
   public final String CST_ELIMINATED_KINGDOM   = "Royaumes .limin.s :";
   public final String CST_KINGDOM_REGEXP       = " - (.+) \\((\\w+) - (.+)\\)";

   //.............. flag that indicates in which section we are ..............

   public final int SECTION_DESTROYED_KIGNDOM   = 11;
   public final int SECTION_ELIMINATED_KINGDOM  = 12;
}

//*** EOF ************************************************************ EOF ***