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
import specific.data.api.DataContinentsAPI;
import specific.data.api.DestroyedEliminatedAPI;


public class ContinentsParser extends    MailParser
                              implements ContinentsParserConstants,
                                         DataContinentsAPI
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

   public DestroyedEliminatedAPI getDestroyedAPI()
   {
      return (DestroyedEliminatedAPI) _detroyedParser;
   }

   public DestroyedEliminatedAPI getEliminatedAPI()
   {
      return (DestroyedEliminatedAPI) _eliminatedParser;
   }
   

   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}

//*** EOF ************************************************************ EOF ***