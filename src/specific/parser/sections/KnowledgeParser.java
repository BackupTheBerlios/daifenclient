// CC_VERSIONS

/**
 * KnowledgeParser.java
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

package specific.parser.sections;

import specific.data.KnowledgeInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KnowledgeParser extends    SectionParser
                             implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   Pattern _patternRegexp = Pattern.compile(CST_CONNAISSANCES_REGEXP);


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KnowledgeParser()
   {
      _sectionPattern       = Pattern.compile(CST_CONNAISSANCES);

      _lstSubSectionPattern = new Pattern[] {
                                            };

      _lstSubSectionIndex   = new int[]     {
                                            };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void specificParseLineData(String p_line)
   {
      Matcher l_matcher = _patternRegexp.matcher(p_line);

      if ( l_matcher.matches() )
      {
         String   l_str    = null;
         String   l_know   = new String();
         int      l_turn   = 0;

         l_know  = new String(l_matcher.group(1));
         l_str   = new String(l_matcher.group(2));
         l_turn  = Integer.parseInt(l_str);

         KnowledgeInfo l_info = new KnowledgeInfo(l_know, l_turn);

         Trace.println("---------------------------------");
         Trace.println("   Knowledge : ", l_know);
         Trace.println("   Turn      : ", Integer.toString(l_turn));

         _parsedData.add(l_info);
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***