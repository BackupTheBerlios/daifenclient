// CC_VERSIONS

/**
 * TroupesParser.java
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

import specific.data.TroupesInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TroupesParser extends    SectionParser
                           implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   Pattern _patternRegexp = Pattern.compile(CST_INV_TROUPES_REGEXP);


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public TroupesParser(String p_kind)
   {
      _sectionPattern       = Pattern.compile(p_kind);

      _lstSubSectionPattern = new Pattern[] {
                                            };

      _lstSubSectionIndex   = new int[]     {
                                            };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   protected void specificParseLineData(String p_line)
   {
      Matcher l_matcher = _patternRegexp.matcher(p_line);

      if ( l_matcher.matches() )
      {
         String   l_str       = null;
         String   l_unit      = new String();
         int      l_quantity  = 0;

         l_unit      = new String(l_matcher.group(1));
         l_str       = new String(l_matcher.group(2));
         l_quantity  = Integer.parseInt(l_str);

         TroupesInfo l_info = new TroupesInfo(l_unit, l_quantity);

         Trace.println("---------------------------------");
         Trace.println("   Unit     : ", l_unit);
         Trace.println("   Quantity : ", Integer.toString(l_quantity));

         _parsedData.add(l_info);
      }
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***