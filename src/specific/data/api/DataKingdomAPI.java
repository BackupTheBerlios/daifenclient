// CC_VERSIONS

/**
 * DataKingdomAPI.java
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

package specific.data.api;


public interface DataKingdomAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   AttackAPI               getAttackAPI();
   ContactAPI              getContactAPI();
   EconomyAPI              getEconomyAPI();
   KnowledgeAPI            getKnowledgeAPI();
   TroupesAPI              getTroupesAPI();
   TroupesAPI              getBatimentsAPI();
   SocialAPI               getSocialAPI();
   RumourAPI               getRumourAPI();
}


//*** EOF ************************************************************ EOF ***