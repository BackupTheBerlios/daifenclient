// CC_VERSIONS

/**
 * ContactAPI.java
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

import specific.data.info.ContactInfo;


public interface ContactAPI
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   void        createIterator();
   boolean     hasNext();
   ContactInfo next();
}


//*** EOF ************************************************************ EOF ***