// CC_VERSIONS

/**
 * KnowledgeInfo.java
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

package specific.data.info;


public class KnowledgeInfo extends Info
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _knowledge = new String();
   private int    _turn      = 0;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public KnowledgeInfo() {}

   public KnowledgeInfo(String p_knowledge, int p_turn)
   {
      _knowledge = p_knowledge;
      _turn      = p_turn;
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public String getknowledge() { return _knowledge; }
   public int    getTurn     () { return _turn; }

   public void setKnowledge(String p_knowledge)
   {
      _knowledge = p_knowledge;
   }

   public void setTurn(int p_turn)
   {
      _turn = p_turn;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***