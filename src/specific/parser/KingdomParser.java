// CC_VERSIONS

/**
 * KingdomParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 19, 2004
 *    @version       v0.1
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser;

import specific.parser.sections.*;


public class KingdomParser extends    MailParser
                           implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KingdomParser()
   {
      _lstSectionsParsers = new SectionParser[]
                                    {
                                       new RumourParser(),
                                       new EconomyParser(),
                                       new TroupesParser(CST_INV_TROUPES),
                                       new TroupesParser(CST_INV_BATIMENTS),
                                       new KnowledgeParser(),
                                       new ContactParser(),
                                    };
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