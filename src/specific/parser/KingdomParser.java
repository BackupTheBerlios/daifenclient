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

import specific.parser.sections.*;
import specific.data.api.AttackAPI;
import specific.data.api.SocialAPI;
import specific.data.api.KingdomDataAPI;


public class KingdomParser extends    MailParser
                           implements KingdomParserConstants, KingdomDataAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private SectionParser _rumourParser       = new RumourParser();
   private SectionParser _economyParser      = new EconomyParser();
   private SectionParser _invTroupesParser   = new TroupesParser(CST_INV_TROUPES);
   private SectionParser _invBatimentsParser = new TroupesParser(CST_INV_BATIMENTS);
   private SectionParser _knowledgeParser    = new KnowledgeParser();
   private SectionParser _contactParser      = new ContactParser();
   private SectionParser _attackParser       = new AttackParser();
   private SectionParser _socialParser       = new SocialParser();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KingdomParser()
   {
      _lstSectionsParsers = new SectionParser[]
                                    {
                                       _rumourParser,
                                       _economyParser,
                                       _invTroupesParser,
                                       _invBatimentsParser,
                                       _knowledgeParser,
                                       _contactParser,
                                       _attackParser,
                                       _socialParser,
                                    };
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public AttackAPI getAttackAPI()
   {
      return (AttackAPI) _attackParser;
   }

   public SocialAPI getSocialAPI()
   {
      return (SocialAPI) _socialParser;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***