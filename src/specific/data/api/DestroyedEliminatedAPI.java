// CC_VERSIONS

/**
 * DestroyedEliminatedAPI.java
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

import specific.data.info.KingdomInfo;


public interface DestroyedEliminatedAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void        createIterator();
   boolean     hasNext();
   KingdomInfo next();
}


//*** EOF ************************************************************ EOF ***