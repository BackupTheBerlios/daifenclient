// CC_VERSIONS

/**
 * RumourParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 3, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser.sections;

import specific.data.api.RumourAPI;
import specific.data.info.RumourInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RumourParser extends    SectionParser
                          implements KingdomParserConstants, RumourAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   //-------------------- define specific sub section pattern ----------------

   private Pattern _patternRumourBegin = null;
   private Pattern _patternRumourEnd   = null;

   private RumourInfo _currentInfo     = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public RumourParser()
   {
      _sectionPattern       = Pattern.compile(CST_RUMOUR);

      _patternRumourBegin   = Pattern.compile(CST_RUMOUR_BEGIN);
      _patternRumourEnd     = Pattern.compile(CST_RUMOUR_END);

      _lstSubSectionPattern = new Pattern[] {
                                              _patternRumourBegin,       //  0
                                              _patternRumourEnd,         //  1
                                            };

      _lstSubSectionIndex   = new int[]     {
                                              SECTION_RUMOUR_BEGIN,      //  0
                                              SECTION_RUMOUR_END,        //  1
                                            };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public RumourInfo next()
   {
      return (RumourInfo) getIter().next();
   }
   

   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void extractSpecificSubSectionData(String p_line)
   {
      Trace.enterFunction("KingdomParser::extractSpecificSubSectionData()");

      if ( _subSection == SECTION_RUMOUR_BEGIN )
      {
         _currentInfo = new RumourInfo();
         _parsedData.add(_currentInfo);

         Matcher l_matcher   = _patternRumourBegin.matcher(p_line);

         if ( l_matcher.matches() )
         {
            String l_str = l_matcher.group(1);
            _currentInfo.setKingdom(l_str);

            Trace.println("   Kingdom  : ", l_str);
         }
      }

      Trace.exitFunction("KingdomParser::extractSpecificSubSectionData()");
   }


   protected boolean isSubSectionHeader(boolean p_subSection)
   {
      boolean l_headerStart = false;

      if ( p_subSection )
      {
         switch ( _subSection )
         {
            case SECTION_RUMOUR_BEGIN:
               {
                  l_headerStart = true;

                  break;
               }
         }
      }

      return l_headerStart;
   }


   protected void specificParseLineData(String p_line)
   {
      switch ( _subSection )
      {
         case SECTION_RUMOUR_BEGIN:
            {
               _currentInfo.appendRumour(p_line + "\n");

               break;
            }
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}


//*** EOF ************************************************************ EOF ***