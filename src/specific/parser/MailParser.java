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
import specific.parser.sections.SectionParser;
import tools.Trace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;


public abstract class MailParser implements CommonParserConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================

   protected InputStream   _bodyData               = null;

   protected SectionParser[] _lstSectionsParsers   = null;


   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public MailParser()
   {
   }


   //*************************************************************************
   //***                              ACCESSOR                             ***
   //*************************************************************************

   public SectionParser[] getLstSectionsParsers()
   {
      return _lstSectionsParsers;
   }

   public void setLstSectionsParsers(SectionParser[] p_lstSectionsParsers)
   {
      _lstSectionsParsers = p_lstSectionsParsers;
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

      init();

      BufferedReader l_reader = new BufferedReader(
                                            new InputStreamReader(_bodyData));

      int            l_section              = SECTION_UNKNOWN;
      SectionParser  l_currentSectionParser = null;


      try
      {
         String l_line = new String();

         while ( l_reader.ready() )
         {
            l_line = l_reader.readLine();
            Trace.println("Line: ", l_line);

            //========== analyse the current line for a new section ==========

            SectionParser l_newSectionParser = checkSection(l_line, l_section);

            if ( l_newSectionParser != null )
            {
               l_currentSectionParser = l_newSectionParser;

               l_currentSectionParser.startSection(l_line);
            }

            //=============== check if a section has been found ==============

            if ( l_currentSectionParser != null &&
                 l_newSectionParser     == null    )
            {
               l_currentSectionParser.parse(l_line);
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

   protected SectionParser checkSection(String p_line,
                                        int    p_iCurSection)
   {
      Trace.enterFunction("MailParser::checkSection()");

      SectionParser l_foundNewSection = null;

      for ( int i = 0 ; i < _lstSectionsParsers.length ; i++ )
      {
         Pattern l_pattern = _lstSectionsParsers[i].getSectionPattern();

         if ( l_pattern.matcher(p_line).matches() )
         {
            l_foundNewSection = _lstSectionsParsers[i];
            break;
         }
      }

      Trace.exitFunction("MailParser::checkSection()");

      return l_foundNewSection;
   }


   protected void init()
   {
      Trace.enterFunction("MailParser::init()");

      for ( int i = 0 ; i < _lstSectionsParsers.length ; i++ )
      {
         _lstSectionsParsers[i].init();
      }

      Trace.exitFunction("MailParser::init()");
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

}

//*** EOF ************************************************************ EOF ***