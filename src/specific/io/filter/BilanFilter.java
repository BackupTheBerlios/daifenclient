// CC_VERSIONS

/**
 *
 * BilanFilter.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 30, 2004
 *
 * HOW TO USE:
 *
 *
 */

package specific.io.filter;

import tools.Trace;

import java.io.File;
import java.util.regex.Matcher;


public class BilanFilter extends RegexpFilter
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private int     _startingMoon = 0;


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public BilanFilter(String p_regexp)
   {
      super(p_regexp);
   }


   public BilanFilter(String p_regexp, int p_startingMoon)
   {
      super(p_regexp);

      _startingMoon = p_startingMoon;
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************



   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public boolean accept(File p_dir, String p_name)
   {
      boolean l_result = false;

      if ( ! new File(p_dir + "/" + p_name).isDirectory() )
      {
         Matcher l_matcher = _pattern.matcher(p_name);

         l_result = l_matcher.matches();

         if ( l_result )
         {
            //------------------- extract the moon number --------------------

            int l_bilanMoon = Integer.parseInt(l_matcher.group(1));

            //----- check if the moon number is valid for this continent -----
            // a valid moon number is defined like this:
            // the moon number must be defined in the range [_startingMoon..n[

            if ( l_bilanMoon < _startingMoon )
               l_result = false;
         }
      }

      Trace.println("p_name = ", p_name + " (" + l_result + ")");

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