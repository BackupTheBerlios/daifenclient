// CC_VERSIONS

/**
 * AttackAPI.java
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

import specific.data.info.TroupesInfo;


public interface AttackAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void           createIterator();
   boolean        hasNext();
   void           next();

   String         getAttackedKingdom();
   String[]       getLstAttackers();
   TroupesInfo[]  getLstAttackTroups();
   TroupesInfo[]  getLstDefenseTroups();
   TroupesInfo[]  getLstDeadAttackTroups();
   TroupesInfo[]  getLstDeadDefenseTroups();
   TroupesInfo[]  getLstDestroyedBuilding();

   int            getAttackStatus();
}


//*** EOF ************************************************************ EOF ***