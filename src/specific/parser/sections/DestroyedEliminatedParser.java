// CC_VERSIONS

/**
 * DestroyedEliminatedParser.java
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

import specific.data.api.DestroyedEliminatedAPI;
import specific.data.info.KingdomInfo;
import specific.parser.ContinentsParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DestroyedEliminatedParser extends    SectionParser
                                       implements ContinentsParserConstants,
                                                  DestroyedEliminatedAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private Pattern _patternRegexp = Pattern.compile(CST_KINGDOM_REGEXP);

   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public DestroyedEliminatedParser(String p_kind)
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

   public KingdomInfo next()
   {
      return (KingdomInfo) getIter().next();
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void specificParseLineData(String p_line)
   {
      Matcher l_matcher = _patternRegexp.matcher(p_line);

      if ( l_matcher.matches() )
      {
         String      l_kd        = new String(l_matcher.group(1));
         String      l_species   = new String(l_matcher.group(2));
         String      l_continent = new String(l_matcher.group(3));
         KingdomInfo l_kdInfo    = new KingdomInfo(l_kd,
                                                   l_species,
                                                   l_continent);

         Trace.println("---------------------------------");
         Trace.println("   Kingdom  : ", l_kd);
         Trace.println("   Species  : ", l_species);
         Trace.println("   Continent: ", l_continent);

         _parsedData.add(l_kdInfo);
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***