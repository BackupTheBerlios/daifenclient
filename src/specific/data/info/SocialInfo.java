// CC_VERSIONS

/**
 * SocialInfo.java
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

package specific.data.info;


public class SocialInfo extends Info
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private String _rank    = new String();
   private int    _points  = 0;

   private String _info    = new String();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public SocialInfo() {}


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public String  getRank  () { return _rank; }
   public int     getPoints() { return _points; }
   public String  getInfo  () { return _info; }


   public void setRank(String p_rank)
   {
      _rank = p_rank;
   }

   public void setPoints(int p_points)
   {
      _points = p_points;
   }

   public void appendInfo(String p_line)
   {
      _info += p_line + "\n";
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************



   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}


//*** EOF ************************************************************ EOF ***