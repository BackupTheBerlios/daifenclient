// CC_VERSIONS

/**
 * KnowledgeAPI.java
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

import specific.data.info.KnowledgeInfo;


public interface KnowledgeAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void           createIterator();
   boolean        hasNext();
   KnowledgeInfo  next();
}


//*** EOF ************************************************************ EOF ***