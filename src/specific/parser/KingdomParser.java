// CC_VERSIONS

/**
 * KingdomParser.java
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

import specific.data.RumourInfo;
import tools.Trace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KingdomParser extends    MailParser
                           implements KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   //.............................. Rumour Pattern ...........................

   private Pattern _patternRumour      = Pattern.compile(CST_RUMOUR);
   private Pattern _patternRumourBegin = Pattern.compile(CST_RUMOUR_BEGIN);
   private Pattern _patternRumourEnd   = Pattern.compile(CST_RUMOUR_END);

   private Pattern _patternEco         = Pattern.compile(CST_ECONOMIE);
   private Pattern _patternTroupes     = Pattern.compile(CST_INV_TROUPES);
   private Pattern _patternBatiments   = Pattern.compile(CST_INV_BATIMENTS);
   private Pattern _patternConnaiss    = Pattern.compile(CST_CONNAISSANCES);
   private Pattern _patternContacts    = Pattern.compile(CST_CONTACTS);
   private Pattern _patternSocial      = Pattern.compile(CST_SOCIAL);


   private Pattern[] _lstRumourPattern = {
                                           _patternRumourBegin,          //  0
                                           _patternRumourEnd,            //  1
                                         };

   private int[]     _lstRumourIndex   = {
                                           SECTION_RUMOUR_BEGIN,         //  0
                                           SECTION_RUMOUR_END,           //  1
                                         };

   private String    _strTmpRumour  = new String();
   private String    _strTmpKingdom = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KingdomParser()
   {
      _lstSectionPattern = new Pattern[]   {
                                              _patternRumour,            //  0
                                              _patternEco,               //  1
                                              _patternTroupes,           //  2
                                              _patternBatiments,         //  3
                                              _patternConnaiss,          //  4
                                              _patternContacts,          //  5
                                              _patternSocial,            //  6
                                           };

      _lstSectionIndex   = new int[]       {
                                              SECTION_RUMOUR,            //  0
                                              SECTION_ECONOMIE,          //  1
                                              SECTION_INV_TROUPES,       //  2
                                              SECTION_INV_BATIMENTS,     //  3
                                              SECTION_CONNAISSANCES,     //  4
                                              SECTION_CONTACTS,          //  5
                                              SECTION_SOCIAL,            //  6
                                           };

      _lstDatasList      = new ArrayList[] {
                                              null,                      //  0
                                              null,                      //  1
                                              null,                      //  2
                                              null,                      //  3
                                              null,                      //  4
                                              null,                      //  5
                                              null,                      //  6
                                           };


      _lstCallbackParamClass = new Class[]  {ArrayList.class,
                                             String.class,
                                             Integer.class};
      try
      {
         _lstCallbacks = new Method[]    {

            KingdomParser.class.getMethod("addListRumour", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
            KingdomParser.class.getMethod("nothing", _lstCallbackParamClass),
         };
      }
      catch ( NoSuchMethodException e )
      {
         e.printStackTrace();
      }
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected int checkSubSection(String  p_line,
                                 int     p_iCurSection)
   {
      Trace.enterFunction("KingdomParser::checkSubSection()");

      int l_newSubSection = SECTION_UNKNOWN;

      switch ( p_iCurSection )
      {
         case SECTION_RUMOUR :
            {
               for ( int i = 0 ; i < _lstRumourPattern.length ; i++ )
               {
                  if ( _lstRumourPattern[i].matcher(p_line).matches() )
                  {
                     l_newSubSection = _lstRumourIndex[i];

                     //- when a specific section has been found possibility --
                     //------- to extract specific information from it -------

                     extractSpecificSectionData(p_line, l_newSubSection);

                     break;
                  }
               }

               break;
            }
      }

      Trace.exitFunction("KingdomParser::checkSubSection()",
                         Integer.toString(l_newSubSection));

      return l_newSubSection;
   }


   protected void addLineSectionParsedInfo(String l_line,
                                           int    l_section,
                                           int    l_subSection)
   {
      try
      {
         Method l_method = getCallback(l_section);

         if ( l_method != null )
         {
            Object[] l_lstParam = {
                                     getDatasList(l_section),
                                     l_line,
                                     new Integer(l_subSection)
                                  };

            l_method.invoke(this, l_lstParam);
         }
      }
      catch ( IllegalAccessException e )
      {
         e.printStackTrace();
      }
      catch ( InvocationTargetException e )
      {
         e.printStackTrace();
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


//   private void addListInfo(ArrayList p_lstInfo,
//                            Pattern   p_patternKD,
//                            String    p_line)
//   {
//      Trace.enterFunction("KingdomParser::addListInfo()");
//
//      KingdomInfo l_kdInfo = extractRumourKingdom(p_patternKD, p_line);
//
//      if ( l_kdInfo != null )
//      {
//         p_lstInfo.add(l_kdInfo);
//      }
//
//      Trace.exitFunction("KingdomParser::addListInfo()");
//   }


   public void nothing(ArrayList p_lstRumour,
                       String    p_line,
                       Integer   p_subSection)
   {
   }


   public void addListRumour(ArrayList p_lstRumour,
                              String    p_line,
                              Integer   p_subSection)
   {
      Trace.enterFunction("KingdomParser::addListRumour()");

      //=============== check in which part of the rumour we are =============

      switch ( p_subSection.intValue() )
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
                  p_lstRumour.add(new RumourInfo(_strTmpKingdom,
                                                 _strTmpRumour));
               }

               _strTmpKingdom = "";
               _strTmpRumour  = "";

               break;
            }
      }

      Trace.exitFunction("KingdomParser::addListRumour()");
   }


//   private KingdomInfo extractRumourKingdom(String p_line)
//   {
//      Trace.enterFunction("KingdomParser::extractRumourKingdom()");
//
//      String      l_kd        = new String();
//      String      l_species   = new String();
//      String      l_continent = new String();
//      KingdomInfo l_kdInfo    = null;
//
//      Matcher l_matcher   = _patternRumourBegin.matcher(p_line);
//
//      if ( l_matcher.matches() )
//      {
//         l_kd        = l_matcher.group(1);
//
//         Trace.println("---------------------------------");
//         Trace.println("   Kingdom  : ", l_kd);
//         Trace.println("   Species  : ", l_species);
//         Trace.println("   Continent: ", l_continent);
//
//         l_kdInfo = new KingdomInfo(l_kd, l_species, l_continent);
//      }
//
//      Trace.exitFunction("KingdomParser::extractRumourKingdom()");
//
//      return l_kdInfo;
//   }


   private void extractSpecificSectionData(String  p_line,
                                           int     p_iSubSection)
   {
      Trace.enterFunction("KingdomParser::extractSpecificSectionData()");

      if ( p_iSubSection == SECTION_RUMOUR_BEGIN )
      {
         Matcher l_matcher   = _patternRumourBegin.matcher(p_line);

         if ( l_matcher.matches() )
         {
            _strTmpKingdom = l_matcher.group(1);

            Trace.println("   Kingdom  : ", _strTmpKingdom);
         }
      }

      Trace.exitFunction("KingdomParser::extractSpecificSectionData()");
   }
}

//*** EOF ************************************************************ EOF ***