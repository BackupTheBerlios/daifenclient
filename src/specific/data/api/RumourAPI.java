// CC_VERSIONS

/**
 * RumourAPI.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 10, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.data.api;

import specific.data.info.RumourInfo;


public interface RumourAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void           createIterator();
   boolean        hasNext();
   RumourInfo     next();
}


//*** EOF ************************************************************ EOF ***