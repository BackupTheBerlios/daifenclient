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

import specific.data.info.ContactInfo;
import specific.data.info.AttackInfo;
import specific.data.api.ContactAPI;
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


   //*************************   API IMPLEMENTATION   ************************

   public String getKingdom()
   {
      String      l_result = "";
      ContactInfo l_info   = getCurrentIterInfo();

      if ( l_info != null )
      {
         l_result = l_info.getKingdom();
      }

      return l_result;
   }

   public String getSpecies()
   {
      String      l_result = "";
      ContactInfo l_info   = getCurrentIterInfo();

      if ( l_info != null )
      {
         l_result = l_info.getSpecies();
      }

      return l_result;
   }

   public String getEmail()
   {
      String      l_result = "";
      ContactInfo l_info   = getCurrentIterInfo();

      if ( l_info != null )
      {
         l_result = l_info.getEmail();
      }

      return l_result;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private ContactInfo getCurrentIterInfo()
   {
      return (ContactInfo) getCurrentIterObj();
   }


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
}


//*** EOF ************************************************************ EOF ***