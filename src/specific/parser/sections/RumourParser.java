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

import specific.parser.KingdomParserConstants;
import specific.data.RumourInfo;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import tools.Trace;


public class RumourParser extends    SectionParser
                          implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   //-------------------- define specific sub section pattern ----------------

   private Pattern _patternRumourBegin = null;
   private Pattern _patternRumourEnd   = null;

   private String    _strTmpKingdom    = new String();
   private String    _strTmpRumour     = new String();


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



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void extractSpecificSubSectionData(String p_line)
   {
      Trace.enterFunction("KingdomParser::extractSpecificSubSectionData()");

      if ( _subSection == SECTION_RUMOUR_BEGIN )
      {
         Matcher l_matcher   = _patternRumourBegin.matcher(p_line);

         if ( l_matcher.matches() )
         {
            _strTmpKingdom = l_matcher.group(1);

            Trace.println("   Kingdom  : ", _strTmpKingdom);
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
               _strTmpRumour += p_line + "\n";

               break;
            }

         case SECTION_RUMOUR_END:
            {
               if ( _strTmpRumour.length() != 0 )
               {
                  _parsedData.add(new RumourInfo(_strTmpKingdom,
                                                 _strTmpRumour));
               }

               _strTmpKingdom = "";
               _strTmpRumour  = "";

               break;
            }
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}


//*** EOF ************************************************************ EOF ***