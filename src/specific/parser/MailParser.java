// CC_VERSIONS

/**
 * MailParser.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  May 27, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */

package specific.parser;

import exception.ParsingMessageException;
import tools.Trace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.lang.reflect.Method;


public abstract class MailParser implements CommonParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================

   protected InputStream   _bodyData               = null;

   protected Pattern  []   _lstSectionPattern      = null;
   protected int      []   _lstSectionIndex        = null;
   protected ArrayList[]   _lstDatasList           = null;
   protected Method   []   _lstCallbacks           = null;
   protected Class    []   _lstCallbackParamClass  = null;


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public MailParser()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public void setData(InputStream p_bodyData)
   {
      _bodyData = p_bodyData;
   }


   public void parse() throws ParsingMessageException
   {
      Trace.enterFunction("MailParser::parse()");

      //----------------- check if the Body buffer is valid ------------------

      if ( _bodyData == null )
      {
         throw new ParsingMessageException();
      }

      //---------------- delete previous parsed kingdom list -----------------

      cleanParsedDatasList();

      BufferedReader l_reader = new BufferedReader(
                                            new InputStreamReader(_bodyData));

      int     l_section      = SECTION_UNKNOWN;
      int     l_subSection   = SECTION_UNKNOWN;


      try
      {
         String l_line = new String();

         while ( l_reader.ready() )
         {
            l_line = l_reader.readLine();
            Trace.println("Line: ", l_line);

            //========== analyse the current line for a new section ==========

            int l_newSection = checkSection(l_line, l_section);

            if ( l_newSection == SECTION_UNKNOWN )
            {
               //==== check if the current section has some sub sections =====

               int l_newSubSection = checkSubSection(l_line, l_section);

               if ( l_newSubSection != SECTION_UNKNOWN )
               {
                  l_subSection = l_newSubSection;
               };
            }
            else
            {
               l_section = l_newSection;
            }

            //=============== check if a section has been found ==============

            if ( l_section    != SECTION_UNKNOWN &&
                 l_newSection == SECTION_UNKNOWN    )
            {
               addLineSectionParsedInfo(l_line, l_section, l_subSection);
            };
         }

         _bodyData.close();
         _bodyData = null;
      }
      catch ( IOException e )
      {
         throw new ParsingMessageException();
      }

      Trace.exitFunction("MailParser::parse()");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   protected int checkSection(String p_line,
                              int    p_iCurSection)
   {
      Trace.enterFunction("MailParser::checkSection()");

      int l_foundNewSection = SECTION_UNKNOWN;

      for ( int i = 0 ; i < _lstSectionPattern.length ; i++ )
      {
         if ( _lstSectionPattern[i].matcher(p_line).matches() )
         {
            l_foundNewSection = _lstSectionIndex[i];
            break;
         }
      }

      Trace.exitFunction("MailParser::checkSection()",
                         Integer.toString(l_foundNewSection));

      return l_foundNewSection;
   }


   protected int checkSubSection(String  p_line,
                                 int     p_iCurSection)
   {
      return SECTION_UNKNOWN;
   }


   protected ArrayList getDatasList(int p_section)
   {
      Trace.enterFunction("MailParser::getDatasList()");

      ArrayList l_datasList = null;

      //================ check if the section index is valid =================

      int l_pos = getSectionIndex(p_section);

      if ( l_pos != -1 )
      {
         l_datasList = _lstDatasList[l_pos];
      }

      Trace.exitFunction("MailParser::getDatasList()",
                         l_datasList.toString());

      return l_datasList;
   }


   protected Method getCallback(int p_section)
   {
      Trace.enterFunction("MailParser::getCallback()");

      Method l_method = null;

      //================ check if the section index is valid =================

      int l_pos = getSectionIndex(p_section);

      if ( l_pos != -1 )
      {
         l_method = _lstCallbacks[l_pos];
      }

      Trace.exitFunction("MailParser::getCallback()",
                         l_method.toString());

      return l_method;
   }


   protected Pattern getSectionPattern(int p_section)
   {
      Trace.enterFunction("MailParser::getSectionPattern()");

      Pattern l_sectionPattern = null;

      //================ check if the section index is valid =================

      int l_pos = getSectionIndex(p_section);

      if ( l_pos != -1 )
      {
         l_sectionPattern = _lstSectionPattern[l_pos];
      }

      Trace.exitFunction("MailParser::getSectionPattern()",
                         l_sectionPattern.toString());

      return l_sectionPattern;
   }


   protected void cleanParsedDatasList()
   {
      Trace.enterFunction("MailParser::cleanParsedDatasList()");

      for ( int i = 0 ; i < _lstDatasList.length ; i++ )
      {
         _lstDatasList[i] = new ArrayList();
      }

      Trace.exitFunction("MailParser::cleanParsedDatasList()");
   }


   protected abstract void addLineSectionParsedInfo(String l_line,
                                                    int    l_section,
                                                    int    l_subSection);



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private int getSectionIndex(int p_section)
   {
      int l_pos = -1;

      for ( int i = 0 ; i < _lstSectionIndex.length ; i++ )
      {
         if ( _lstSectionIndex[i] == p_section )
         {
            l_pos = i;

            break;
         }
      }

      return l_pos;
   }
}

//*** EOF ************************************************************ EOF ***