// CC_VERSIONS

/**
 * TroupesAPI.java
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

import specific.data.info.TroupesInfo;


public interface TroupesAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void           createIterator();
   boolean        hasNext();
   TroupesInfo    next();
}


//*** EOF ************************************************************ EOF ***