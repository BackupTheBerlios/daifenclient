// CC_VERSIONS

/**
 * SectionParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Jun 3, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser.sections;

import specific.parser.CommonParserConstants;
import tools.Trace;

import java.util.ArrayList;
import java.util.regex.Pattern;


public abstract class SectionParser implements CommonParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================

   protected ArrayList  _parsedData             = new ArrayList();

   protected Pattern    _sectionPattern         = null;
   protected Pattern[]  _lstSubSectionPattern   = new Pattern[] {};
   protected int[]      _lstSubSectionIndex     = new int    [] {};

   protected boolean    _isSectionHeader        = false;
   protected int        _subSection             = SECTION_UNKNOWN;


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************



   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public void init()
   {
      _parsedData = new ArrayList();
   }


   public void startSection()
   {
      Trace.enterFunction("SectionParser::startSection()");

      _isSectionHeader     = false;
      _subSection          = SECTION_UNKNOWN;

      Trace.exitFunction("SectionParser::startSection()");
   }

   public Pattern getSectionPattern()
   {
      return _sectionPattern;
   }


   public void parse(String p_line)
   {
      Trace.enterFunction("SectionParser::parse()");

      //======== check if the current section has some sub sections ==========

      boolean l_foundSubSection = checkSubSection(p_line);

      //----- do not try to parse the section or sub-section header line -----

      if ( ! ( l_foundSubSection && isSubSectionHeader(l_foundSubSection) ) )
      {
         specificParseLineData(p_line);
      };

      Trace.exitFunction("SectionParser::parse()");
   }


   protected boolean isSubSectionHeader(boolean p_subSection)
   {
      return false;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected boolean checkSubSection(String p_line)
   {
      Trace.enterFunction("KingdomParser::checkSubSection()");

      boolean l_foundSubSection = false;

      for ( int i = 0 ; i < _lstSubSectionPattern.length ; i++ )
      {
         if ( _lstSubSectionPattern[i].matcher(p_line).matches() )
         {
            l_foundSubSection = true;
            _subSection       = _lstSubSectionIndex[i];

            //------ when a specific section has been found possibility ------
            //------------ to extract specific information from it -----------

            extractSpecificSubSectionData(p_line);

            break;
         }
      }

      Trace.exitFunction("KingdomParser::checkSubSection()");

      return l_foundSubSection;
   }


   protected void extractSpecificSubSectionData(String p_line)
   {
      // nothing to do
      //
      // everything must be performed in the inherited classes
   }

   protected void specificParseLineData(String p_line)
   {
      // nothing to do
      //
      // everything must be performed in the inherited classes
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}


//*** EOF ************************************************************ EOF ***