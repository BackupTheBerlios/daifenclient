// CC_VERSIONS

/**
 * AttackParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 7, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser.sections;

import specific.data.api.AttackAPI;
import specific.data.info.AttackInfo;
import specific.data.info.TroupesInfo;
import specific.parser.KingdomParserConstants;
import tools.Trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AttackParser extends    SectionParser
                          implements KingdomParserConstants, AttackAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   //-------------------- define specific sub section pattern ----------------

   private Pattern      _patternAttackDe              = null;
   private Pattern      _patternDefenseDe             = null;
   private Pattern      _patternAttackMort            = null;
   private Pattern      _patternDefenseMort           = null;
   private Pattern      _patternAttackPerce           = null;
   private Pattern      _patternAttackRepoussee       = null;
   private Pattern      _patternRoyaumeDetruit        = null;

   private Pattern      _patternTroupesRegexp        = null;
//   private Pattern      _patternDefenseDeRegexp       = null;
//   private Pattern      _patternAttackMortRegexp      = null;
//   private Pattern      _patternDefenseMortRegexp     = null;
//   private Pattern      _patternAttackPerceRegexp     = null;
   private Pattern      _patternDefenseProtectRegexp  = null;

   private AttackInfo   _currentInfo                  = null;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public AttackParser()
   {
      _sectionPattern         = Pattern.compile(CST_ATTACK);

      _patternAttackDe        = Pattern.compile(CST_ATTACK_DE);
      _patternDefenseDe       = Pattern.compile(CST_DEFENSE_DE);
      _patternAttackMort      = Pattern.compile(CST_ATTACK_MORT);
      _patternDefenseMort     = Pattern.compile(CST_DEFENSE_MORT);
      _patternAttackPerce     = Pattern.compile(CST_ATTACK_PERCE);
      _patternAttackRepoussee = Pattern.compile(CST_ATTACK_REPOUSSEE);
      _patternRoyaumeDetruit  = Pattern.compile(CST_ROYAUME_DETRUIT);

      _lstSubSectionPattern = new Pattern[] {
                                               _patternAttackDe,
                                               _patternDefenseDe,
                                               _patternAttackMort,
                                               _patternDefenseMort,
                                               _patternAttackPerce,
                                               _patternAttackRepoussee,
                                               _patternRoyaumeDetruit,
                                            };

      _lstSubSectionIndex   = new int[]     {
                                               SECTION_ATTACK_DE,
                                               SECTION_DEFENSE_DE,
                                               SECTION_ATTACK_MORT,
                                               SECTION_DEFENSE_MORT,
                                               SECTION_ATTACK_PERCE,
                                               SECTION_ATTACK_REPOUSSEE,
                                               SECTION_ROYAUME_DETRUIT,
                                            };

      _patternTroupesRegexp        = Pattern.compile(CST_ATTACK_DE_REGEXP);
//      _patternDefenseDeRegexp      = Pattern.compile(CST_DEFENSE_DE_REGEXP);
//      _patternAttackMortRegexp     = Pattern.compile(CST_ATTACK_MORT_REGEXP);
//      _patternDefenseMortRegexp    = Pattern.compile(CST_DEFENSE_MORT_REGEXP);
//      _patternAttackPerceRegexp    = Pattern.compile(CST_ATTACK_PERCE_REGEXP);
      _patternDefenseProtectRegexp = Pattern.compile(CST_DEFENSE_PROTECT_REGEXP);
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public AttackInfo next()
   {
      return (AttackInfo) getIter().next();
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected void extractSpecificSectionData(String p_line)
   {
      Trace.enterFunction("AttackParser::extractSpecificSectionData()");

      //================= create a new attack structure info =================

      _currentInfo = new AttackInfo();
      _parsedData.add(_currentInfo);

      //=================== retrieve the attacked kingdom ====================

      Matcher l_matcher = _sectionPattern.matcher(p_line);

      if ( l_matcher.matches() )
      {
         //-------------------- retrieve all the attackers -------------------

         String l_str = l_matcher.group(1);

         Trace.println("   Attacked Kingdom: ", l_str);

         _currentInfo.setAttackedKingdom(l_str);
      }

      Trace.exitFunction("AttackParser::extractSpecificSectionData()");
   }


   protected void extractSpecificSubSectionData(String p_line)
   {
      Trace.enterFunction("AttackParser::extractSpecificSubSectionData()");

      switch ( _subSection )
      {
         case SECTION_ATTACK_DE:
            {
               //============= retrieve the kingdom attack list ==============

               Matcher l_matcher = _patternAttackDe.matcher(p_line);

               if ( l_matcher.matches() )
               {
                  //--------------- retrieve all the attackers ---------------

                  String l_str = l_matcher.group(1);

                  Trace.println("   All Attackers : ", l_str);

                  //------ extract all kingdom from the extracted list -------

                  String[] l_lstKingdom = l_str.split(", ");

                  _currentInfo.setLstAttackers(Arrays.asList(l_lstKingdom));
               }
               break;
            }
      }

      Trace.exitFunction("AttackParser::extractSpecificSubSectionData()");
   }


   protected boolean isSubSectionHeader(boolean p_subSection)
   {
      boolean l_headerStart = false;

      if ( p_subSection )
      {
         switch ( _subSection )
         {
            case SECTION_ATTACK_DE:
            case SECTION_DEFENSE_DE:
            case SECTION_ATTACK_MORT:
            case SECTION_DEFENSE_MORT:
            case SECTION_ATTACK_PERCE:
            case SECTION_ATTACK_REPOUSSEE:
            case SECTION_ROYAUME_DETRUIT:
               {
                  l_headerStart = true;

                  break;
               }
         }
      }

      return l_headerStart;
   }


   protected void specificParseLineData(String p_line)
   {
      switch ( _subSection )
      {
         case SECTION_ATTACK_DE:
            {
               parseTroupInfo(p_line, _currentInfo.getLstAttackTroups());

               break;
            }

         case SECTION_DEFENSE_DE:
            {
               parseTroupInfo(p_line, _currentInfo.getLstDefenseTroups());

               break;
            }

         case SECTION_ATTACK_MORT:
            {
               parseTroupInfo(p_line, _currentInfo.getLstDeadAttackTroups());

               break;
            }

         case SECTION_DEFENSE_MORT:
            {
               parseTroupInfo(p_line, _currentInfo.getLstDeadDefenseTroups());

               break;
            }

         case SECTION_ATTACK_PERCE:
            {
               parseTroupInfo(p_line, _currentInfo.getLstDestroyedBuilding());

               break;
            }

         case SECTION_ATTACK_REPOUSSEE:
            {
               _currentInfo.setAttackStatus(AttackInfo.ATTACK_REPOUSSEE);

               break;
            }

         case SECTION_ROYAUME_DETRUIT:
            {
               _currentInfo.setAttackStatus(AttackInfo.ATTACK_ROYAUME_DETRUIT);

               break;
            }
      }
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private TroupesInfo extractTroupInfo(String p_line)
   {
      Matcher     l_matcher = _patternTroupesRegexp.matcher(p_line);
      TroupesInfo l_troup   = null;

      if ( l_matcher.matches() )
      {
         //------------ retrieve the quantity and kind of troups -------------

         int    l_nb   = Integer.parseInt(l_matcher.group(1));
         String l_unit = l_matcher.group(2);

         Trace.println("   Troup : ", Integer.toString(l_nb) +
                                      " " + l_unit);

         l_troup = new TroupesInfo(l_unit, l_nb);
      }
      else
      {
         //----- chech if it is some protectin that have been destroyed ------

         l_matcher = _patternDefenseProtectRegexp.matcher(p_line);

         if ( l_matcher.matches() )
         {
            //------------ retrieve the quantity and kind of troups -------------

            int    l_nb   = Integer.parseInt(l_matcher.group(1));
            String l_unit = l_matcher.group(2);

            Trace.println("   Protection : ", Integer.toString(l_nb) +
                                              " " + l_unit);

            l_troup = new TroupesInfo(l_unit, l_nb);
         }
      }

      return l_troup;
   }


   private void parseTroupInfo(String p_line, ArrayList p_troupList)
   {
      TroupesInfo l_info = extractTroupInfo(p_line);

      if ( l_info != null )
      {
         p_troupList.add(l_info);
      }
   }
}

//*** EOF ************************************************************ EOF ***