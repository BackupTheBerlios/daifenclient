// CC_VERSIONS

/**
 * KingdomParserConstants.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 27, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser;




public interface KingdomParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************


   ///////////////////////// Daifen MIME Mail section \\\\\\\\\\\\\\\\\\\\\\\\

   //-------------------------- Kingdom information --------------------------

   //.. regexp or string corresponding to specific part of the Daifen bilan ..

   public String CST_RUMOUR                  = "Rumeurs +\\(. .\\)";
   public String CST_RUMOUR_BEGIN            = "A propos de (.+) : *";
   public String CST_RUMOUR_MESG             = "(.+)";
   public String CST_RUMOUR_END              = " +--- *";

   public String CST_ECONOMIE                = "Economie +\\(. .\\)";
   public String CST_ECONOMIE_REGEXP         = "\\| Total Fin du tour +\\| (\\d+) +\\| (\\d+) +\\|";

   public String CST_MILITAIRE               = "Militaire +\\(. .\\)";
   public String CST_INV_TROUPES             = "Inventaire des troupes :";
   public String CST_INV_BATIMENTS           = "Inventaire des B.timents :";
   public String CST_INV_TROUPES_REGEXP      = "\\| (.+\\w) +\\| (\\d+) +\\| +\\|";

   public String CST_CONNAISSANCES           = "Connaissances acquises :";
   public String CST_CONNAISSANCES_REGEXP    = "- (.+\\w) \\(Tour (\\d+)\\)";

   public String CST_CONTACTS                = "Royaumes Connus :";
   public String CST_CONTACTS_REGEXP         = "- (.+\\w) \\((\\w+)\\) Contact : ([\\w@\\.\\-]+)";

   public String CST_SOCIAL                  = "Social +\\(. .\\)";
   public String CST_SOCIAL_REGEXP           = " Vous .tes class. (.+) sur votre continent, avec (\\d+) points";

   public String CST_ATTACK                  = "Vous avez attaqu. le royaume de (.+) :";
   public String CST_ATTACK_DE               = "Attaque de( (.)+,?) :";
   public String CST_ATTACK_DE_REGEXP        = "(\\d+) ([\\w\\W]+)";
   public String CST_DEFENSE_DE              = "D.fense de (.+) :";
   public String CST_DEFENSE_DE_REGEXP       = "(\\d+) ([\\w\\W]+)";
   public String CST_ATTACK_MORT             = "Attaquants :";
   public String CST_ATTACK_MORT_REGEXP      = "(\\d+) ([\\w\\W]+)";
   public String CST_DEFENSE_MORT            = "D.fense :";
   public String CST_DEFENSE_MORT_REGEXP     = "(\\d+) ([\\w\\W]+)";
   public String CST_DEFENSE_PROTECT_REGEXP  = "Protection : (\\d+) (.+)";
   public String CST_ATTACK_PERCE            = "L'attaque a perc. la d.fense et a d.truit :";
   public String CST_ATTACK_PERCE_REGEXP     = "(\\d+) ([\\w\\W]+)";
   public String CST_ATTACK_REPOUSSEE        = "L'attaque a .t. repouss.e\\.";
   public String CST_ROYAUME_DETRUIT         = "Tous les b.timents du royaume ont .t. d.truits !";



   //.............. flag that indicates in which section we are ..............

   public int SECTION_RUMOUR                 = 21;
   public int SECTION_RUMOUR_BEGIN           = 22;
   public int SECTION_RUMOUR_MESG            = 23;
   public int SECTION_RUMOUR_END             = 24;
   public int SECTION_ECONOMIE               = 30;
   public int SECTION_MILITAIRE              = 35;
   public int SECTION_INV_TROUPES            = 40;
   public int SECTION_INV_BATIMENTS          = 45;
   public int SECTION_CONNAISSANCES          = 50;
   public int SECTION_CONTACTS               = 55;
   public int SECTION_SOCIAL                 = 60;
   public int SECTION_ATTACK                 = 65;
   public int SECTION_ATTACK_DE              = 66;
   public int SECTION_DEFENSE_DE             = 67;
   public int SECTION_ATTACK_MORT            = 68;
   public int SECTION_DEFENSE_MORT           = 69;
   public int SECTION_ATTACK_PERCE           = 70;
   public int SECTION_ATTACK_REPOUSSEE       = 71;
   public int SECTION_ROYAUME_DETRUIT        = 72;
}

//*** EOF ************************************************************ EOF ***