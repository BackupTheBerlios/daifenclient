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

import specific.data.info.AttackInfo;


public interface AttackAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void           createIterator();
   boolean        hasNext();
   AttackInfo     next();
}


//*** EOF ************************************************************ EOF ***