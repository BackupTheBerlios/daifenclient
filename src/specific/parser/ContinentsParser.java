// CC_VERSIONS

/**
 * ContinentsParser.java
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

import specific.parser.sections.DestroyedEliminatedParser;
import specific.parser.sections.SectionParser;


public class ContinentsParser extends    MailParser
                              implements ContinentsParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private SectionParser _detroyedParser   =
                         new DestroyedEliminatedParser(CST_DESTROYED_KINGDOM);
   private SectionParser _eliminatedParser =
                        new DestroyedEliminatedParser(CST_ELIMINATED_KINGDOM);


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContinentsParser()
   {
      _lstSectionsParsers = new SectionParser[]
                     {
                        _detroyedParser,
                        _eliminatedParser,
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