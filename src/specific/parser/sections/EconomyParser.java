// CC_VERSIONS

/**
 * EconomyParser.java
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

import specific.data.EconomyInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EconomyParser extends    SectionParser
                           implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   Pattern _patternRegexp = Pattern.compile(CST_ECONOMIE_REGEXP);

   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public EconomyParser()
   {
      _sectionPattern       = Pattern.compile(CST_ECONOMIE);

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
         String   l_str       = null;
         int      l_gold      = -1;
         int      l_intellect = -1;

         l_str       = new String(l_matcher.group(1));
         l_gold      = Integer.parseInt(l_str);
         l_str       = new String(l_matcher.group(2));
         l_intellect = Integer.parseInt(l_str);

         EconomyInfo l_info = new EconomyInfo(l_gold, l_intellect);

         Trace.println("---------------------------------");
         Trace.println("   Gold      : ", Integer.toString(l_gold));
         Trace.println("   Intellect : ", Integer.toString(l_intellect));

         _parsedData.add(l_info);
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***