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

package specific;

import tools.Trace;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

import exception.ParsingMessageException;


public class KingdomParser implements DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   InputStream _bodyData   = null;

   ArrayList   _lstRumour  = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KingdomParser()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public void setData(InputStream p_bodyData)
   {
      _bodyData = p_bodyData;
   }


   public void parse() throws ParsingMessageException
   {
      Trace.enterFunction("KingdomParser::parse()");

      //----------------- check if the Body buffer is valid ------------------

      if ( _bodyData == null )
      {
         throw new ParsingMessageException();
      }

      //---------------- delete previous parsed kingdom list -----------------

      _lstRumour  = new ArrayList();

      BufferedReader l_reader = new BufferedReader(
                                            new InputStreamReader(_bodyData));

      int            l_section       = SECTION_UNKNOWN;
      Pattern        l_patternDestKD = Pattern.compile(CST_DESTROYED_KINGDOM);
      Pattern        l_patternElemKD = Pattern.compile(CST_ELIMINATED_KINGDOM);
      Pattern        l_patternKD     = Pattern.compile(CST_KINGDOM_REGEXP);

      try
      {
         String l_line = new String();

         while ( l_reader.ready() )
         {
            l_line = l_reader.readLine();
            Trace.println("Line: ", l_line);

            //=============== check if a section has been found ==============

            if ( l_section != SECTION_UNKNOWN )
            {
               switch( l_section )
               {
                  case SECTION_DESTROYED_KIGNDOM:
                     {
                        addListInfo(_lstRumour,
                                       l_patternKD, l_line);
                     }

                  case SECTION_ELIMINATED_KINGDOM:
                     {
                        addListInfo(_lstRumour,
                                       l_patternKD, l_line);
                     }
               }
            }

            //========== analyse the current line for a new section ==========

            if ( l_patternDestKD.matcher(l_line).matches() )
            {
               l_section = SECTION_DESTROYED_KIGNDOM;
            }
            else
            {
               if ( l_patternElemKD.matcher(l_line).matches() )
               {
                  l_section = SECTION_ELIMINATED_KINGDOM;
               };
            }
         }
      }
      catch ( IOException e )
      {
         throw new ParsingMessageException();
      }

      Trace.exitFunction("KingdomParser::parse()");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private KingdomInfo extractRumour(Pattern p_patternKD, String p_line)
   {
      String      l_kd        = new String();
      String      l_species   = new String();
      String      l_continent = new String();
      KingdomInfo l_kdInfo    = null;

      Matcher l_matcher   = p_patternKD.matcher(p_line);

      if ( l_matcher.matches() )
      {
         l_kd        = l_matcher.group(1);
         l_species   = l_matcher.group(2);
         l_continent = l_matcher.group(3);

         Trace.println("---------------------------------");
         Trace.println("   Kingdom  : ", l_kd);
         Trace.println("   Species  : ", l_species);
         Trace.println("   Continent: ", l_continent);

         l_kdInfo = new KingdomInfo(l_kd, l_species, l_continent);
      }

      return l_kdInfo;
   }


   private void addListInfo(ArrayList p_lstKingdom,
                            Pattern p_patternKD, String p_line)
   {
      KingdomInfo l_kdInfo = extractRumour(p_patternKD, p_line);

      if ( l_kdInfo != null )
      {
         p_lstKingdom.add(l_kdInfo);
      }
   }

}

//*** EOF ************************************************************ EOF ***