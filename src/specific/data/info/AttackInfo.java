// CC_VERSIONS

/**
 * AttackInfo.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 8, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.data.info;

import java.util.ArrayList;
import java.util.List;

public class AttackInfo implements AttackStatus
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String    _attackedKingdom       = new String();
   private ArrayList _lstAttackers          = new ArrayList();
   private ArrayList _lstAttackTroups       = new ArrayList();
   private ArrayList _lstDefenseTroups      = new ArrayList();
   private ArrayList _lstDeadAttackTroups   = new ArrayList();
   private ArrayList _lstDeadDefenseTroups  = new ArrayList();
   private ArrayList _lstDestroyedBuilding  = new ArrayList();

   private int       _attackStatus          = ATTACK_UNKNOW_STATUS;


   //===============================   PROTECTED   ===========================


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public AttackInfo()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public int        getAttackStatus        () { return _attackStatus; }
   public String     getAttackedKingdom     () { return _attackedKingdom; }
   public ArrayList  getLstAttackers        () { return _lstAttackers; }
   public ArrayList  getLstAttackTroups     () { return _lstAttackTroups; }
   public ArrayList  getLstDefenseTroups    () { return _lstDefenseTroups; }
   public ArrayList  getLstDeadAttackTroups () { return _lstDeadAttackTroups; }
   public ArrayList  getLstDeadDefenseTroups() { return _lstDeadDefenseTroups; }
   public ArrayList  getLstDestroyedBuilding() { return _lstDestroyedBuilding; }

   public void setAttackStatus(int p_attackStatus)
   {
      _attackStatus = p_attackStatus;
   }

   public void setAttackedKingdom(String p_attackedKingdom)
   {
      _attackedKingdom = p_attackedKingdom;
   }

   public void setLstAttackers(List p_lstAttackers)
   {
      _lstAttackers.addAll(p_lstAttackers);
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***