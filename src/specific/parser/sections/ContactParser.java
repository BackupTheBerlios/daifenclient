// CC_VERSIONS

/**
 * ContactParser.java
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

import specific.data.api.ContactAPI;
import specific.data.info.ContactInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactParser extends    SectionParser
                           implements KingdomParserConstants, ContactAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private Pattern _patternRegexp = Pattern.compile(CST_CONTACTS_REGEXP);


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContactParser()
   {
      _sectionPattern       = Pattern.compile(CST_CONTACTS);

      _lstSubSectionPattern = new Pattern[] {
                                            };

      _lstSubSectionIndex   = new int[]     {
                                            };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public ContactInfo next()
   {
      return (ContactInfo) getIter().next();
   }



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void specificParseLineData(String p_line)
   {
      Matcher l_matcher = _patternRegexp.matcher(p_line);

      if ( l_matcher.matches() )
      {
         String   l_king    = new String();
         String   l_species = new String();
         String   l_email   = new String();

         l_king    = new String(l_matcher.group(1));
         l_species = new String(l_matcher.group(2));
         l_email   = new String(l_matcher.group(3));

         ContactInfo l_info = new ContactInfo(l_king, l_species, l_email);

         Trace.println("---------------------------------");
         Trace.println("   King    : ", l_king);
         Trace.println("   Species : ", l_species);
         Trace.println("   Email   : ", l_email);

         _parsedData.add(l_info);
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}


//*** EOF ************************************************************ EOF ***