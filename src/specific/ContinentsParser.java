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

package specific;

import exception.ParsingMessageException;
import tools.Trace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;


public class ContinentsParser implements DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   InputStream _bodyData;

   ArrayList   _lstDestroyedKingdom  = null;
   ArrayList   _lstEliminatedKingdom = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public ContinentsParser()
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
      Trace.enterFunction("ContinentsParser::parse()");

      //----------------- check if the Body buffer is valid ------------------

      if ( _bodyData == null )
      {
         throw new ParsingMessageException();
      }

      //---------------- delete previous parsed kingdom list -----------------

      _lstDestroyedKingdom  = new ArrayList();
      _lstEliminatedKingdom = new ArrayList();

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
                        addKingdomInfo(_lstDestroyedKingdom,
                                       l_patternKD, l_line);
                     }

                  case SECTION_ELIMINATED_KINGDOM:
                     {
                        addKingdomInfo(_lstEliminatedKingdom,
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

      Trace.exitFunction("ContinentsParser::parse()");
   }



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private KingdomInfo extractKingdomInfo(Pattern p_patternKD, String p_line)
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


   private void addKingdomInfo(ArrayList p_lstKingdom,
                               Pattern p_patternKD, String p_line)
   {
      KingdomInfo l_kdInfo = extractKingdomInfo(p_patternKD, p_line);

      if ( l_kdInfo != null )
      {
         p_lstKingdom.add(l_kdInfo);
      }
   }
}

//*** EOF ************************************************************ EOF ***