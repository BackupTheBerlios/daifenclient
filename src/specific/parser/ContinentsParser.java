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


public class ContinentsParser extends    MailParser
                              implements ContinentsParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //.......................... Continent Pattern ............................

//   private Pattern _patternDestKD = Pattern.compile(CST_DESTROYED_KINGDOM);
//   private Pattern _patternElemKD = Pattern.compile(CST_ELIMINATED_KINGDOM);
//
//   private Pattern _patternKD     = Pattern.compile(CST_KINGDOM_REGEXP);


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContinentsParser()
   {
      _lstSectionsParsers = new SectionParser[]
                     {
                        new DestroyedEliminatedParser(CST_DESTROYED_KINGDOM),
                        new DestroyedEliminatedParser(CST_ELIMINATED_KINGDOM),
                    };

//      _lstSectionPattern = new Pattern[]   {
//                                              _patternDestKD,            //  0
//                                              _patternElemKD,            //  1
//                                           };
//
//      _lstSectionIndex   = new int[]       {
//                                              SECTION_DESTROYED_KIGNDOM, //  0
//                                              SECTION_ELIMINATED_KINGDOM,//  1
//                                           };
//
//      _lstDatasList      = new ArrayList[] {
//                                              null,                      //  0
//                                              null,                      //  1
//                                           };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

//   protected void addLineSectionParsedInfo(String l_line,
//                                           int    l_section,
//                                           int    l_subSection)
//   {
//      ArrayList l_datasList = getDatasList(l_section);
//
//      if ( l_datasList != null )
//      {
//         addKingdomInfo(getDatasList(l_section), _patternKD, l_line);
//      }


//      switch( l_section )
//      {
//         case SECTION_DESTROYED_KIGNDOM:
//         case SECTION_ELIMINATED_KINGDOM:
//            {
//               addKingdomInfo(getDatasList(l_section), _patternKD,
//                              l_line);
//
//               break;
//            }
//      }
//   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

//   private void addKingdomInfo(ArrayList p_lstKingdom,
//                               Pattern p_patternKD, String p_line)
//   {
//      KingdomInfo l_kdInfo = extractKingdomInfo(p_patternKD, p_line);
//
//      if ( l_kdInfo != null )
//      {
//         p_lstKingdom.add(l_kdInfo);
//      }
//   }
//
//
//   private KingdomInfo extractKingdomInfo(Pattern p_patternKD, String p_line)
//   {
//      String      l_kd        = new String();
//      String      l_species   = new String();
//      String      l_continent = new String();
//      KingdomInfo l_kdInfo    = null;
//
//      Matcher l_matcher   = p_patternKD.matcher(p_line);
//
//      if ( l_matcher.matches() )
//      {
//         l_kd        = l_matcher.group(1);
//         l_species   = l_matcher.group(2);
//         l_continent = l_matcher.group(3);
//
//         Trace.println("---------------------------------");
//         Trace.println("   Kingdom  : ", l_kd);
//         Trace.println("   Species  : ", l_species);
//         Trace.println("   Continent: ", l_continent);
//
//         l_kdInfo = new KingdomInfo(l_kd, l_species, l_continent);
//      }
//
//      return l_kdInfo;
//   }
}

//*** EOF ************************************************************ EOF ***