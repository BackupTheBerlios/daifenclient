// CC_VERSIONS

/**
 * SocialParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 9, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser.sections;

import specific.data.info.SocialInfo;
import specific.data.api.SocialAPI;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SocialParser extends    SectionParser
                          implements KingdomParserConstants, SocialAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private Pattern      _patternRegexp = Pattern.compile(CST_SOCIAL_REGEXP);

   private SocialInfo   _currentInfo   = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public SocialParser()
   {
      _sectionPattern       = Pattern.compile(CST_SOCIAL);

      _lstSubSectionPattern = new Pattern[] {
                                            };

      _lstSubSectionIndex   = new int[]     {
                                            };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public void init()
   {
      super.init();

      //----------------- add the unique SocialInfo Instance -----------------

      _currentInfo = new SocialInfo();
      _parsedData.add(_currentInfo);
   }


   //*************************   API IMPLEMENTATION   ************************

   public String getRank()
   {
      SocialInfo l_info = (SocialInfo) _parsedData.get(0);

      return l_info.getRank();
   }

   public int getPoints()
   {
      SocialInfo l_info = (SocialInfo) _parsedData.get(0);

      return l_info.getPoints();
   }

   public String getInfo()
   {
      SocialInfo l_info = (SocialInfo) _parsedData.get(0);

      return l_info.getInfo();
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   protected void specificParseLineData(String p_line)
   {
      Matcher l_matcher = _patternRegexp.matcher(p_line);

      if ( l_matcher.matches() )
      {
         String   l_rank   = l_matcher.group(1);
         int      l_points = Integer.parseInt(l_matcher.group(2));

         Trace.println("---------------------------------");
         Trace.println("   Rank   : ", l_rank);
         Trace.println("   points : ", Integer.toString(l_points));

         _currentInfo.setRank(l_rank);
         _currentInfo.setPoints(l_points);
      }

      //----------- always add the current social information line -----------

      _currentInfo.appendInfo(p_line);
   }
}


//*** EOF ************************************************************ EOF ***