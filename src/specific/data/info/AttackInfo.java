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

public class AttackInfo extends Info implements AttackStatus
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

   public AttackInfo() {}


   //*************************************************************************
   //***                              ACCESSOR                             ***
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

   public void setLstAttackers(ArrayList p_lstAttackers)
   {
      _lstAttackers = p_lstAttackers;
   }

   public void setLstAttackTroups(ArrayList p_lstAttackTroups)
   {
      _lstAttackTroups = p_lstAttackTroups;
   }

   public void setLstDefenseTroups(ArrayList p_lstDefenseTroups)
   {
      _lstDefenseTroups = p_lstDefenseTroups;
   }

   public void setLstDeadAttackTroups(ArrayList p_lstDeadAttackTroups)
   {
      _lstDeadAttackTroups = p_lstDeadAttackTroups;
   }

   public void setLstDeadDefenseTroups(ArrayList p_lstDeadDefenseTroups)
   {
      _lstDeadDefenseTroups = p_lstDeadDefenseTroups;
   }

   public void setLstDestroyedBuilding(ArrayList p_lstDestroyedBuilding)
   {
      _lstDestroyedBuilding = p_lstDestroyedBuilding;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public String[] getArrAttackers()
   {
      String[] l_result;

      l_result = new String[_lstAttackers.size()];
      l_result = (String[]) _lstAttackers.toArray(l_result);

      return l_result;
   }

   public TroupesInfo[] getArrAttackTroups()
   {
      TroupesInfo[] l_result;

      l_result = new TroupesInfo[_lstAttackTroups.size()];
      l_result = (TroupesInfo[]) _lstAttackTroups.toArray(l_result);

      return l_result;
   }

   public TroupesInfo[] getArrDefenseTroups()
   {
      TroupesInfo[] l_result;

      l_result = new TroupesInfo[_lstDefenseTroups.size()];
      l_result = (TroupesInfo[]) _lstDefenseTroups.toArray(l_result);

      return l_result;
   }

   public TroupesInfo[] getArrDeadAttackTroups()
   {
      TroupesInfo[] l_result;

      l_result = new TroupesInfo[_lstDeadAttackTroups.size()];
      l_result = (TroupesInfo[]) _lstDeadAttackTroups.toArray(l_result);

      return l_result;
   }

   public TroupesInfo[] getArrDeadDefenseTroups()
   {
      TroupesInfo[] l_result;

      l_result = new TroupesInfo[_lstDeadDefenseTroups.size()];
      l_result = (TroupesInfo[]) _lstDeadDefenseTroups.toArray(l_result);

      return l_result;
   }

   public TroupesInfo[] getArrDestroyedBuilding()
   {
      TroupesInfo[] l_result;

      l_result = new TroupesInfo[_lstDestroyedBuilding.size()];
      l_result = (TroupesInfo[]) _lstDestroyedBuilding.toArray(l_result);

      return l_result;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***