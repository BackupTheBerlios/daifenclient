// CC_VERSIONS

/**
 *
 * RegexpFilter.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 29, 2004
 *
 * HOW TO USE:
 *
 *
 */

package specific.io.filter;

import tools.Trace;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;


public class RegexpFilter implements FilenameFilter
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================

   protected Pattern _pattern = Pattern.compile(".*");   // default: all files



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public RegexpFilter(String p_regexp)
   {
      _pattern = Pattern.compile(p_regexp);
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
         l_result = _pattern.matcher(p_name).matches();
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