package specific;

// CC_VERSIONS

import exception.*;
import mailbox.*;
import specific.io.filter.BilanFilter;
import tools.Trace;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * specific.DaifenManager.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Mar 29, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */


public class DaifenManager implements DaifenConstants
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   MailBoxAccess _mailBox           = null;
   int           _startingMoon      = 0;

   boolean       _online            = false;

   ArrayList     _lstAvailableBilan = new ArrayList();


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public DaifenManager() {}


   //*************************************************************************
   //***                               ACCESSOR                            ***
   //*************************************************************************

   public void setStartingMoon(int p_startingMoon)
   {
      _startingMoon = p_startingMoon;
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public boolean isOnline()
   {
      return _online;
   }


   public void connect(String p_mailerType,
                       String p_server,
                       String p_user,
                       String p_password) throws UnknownMailerClientException,
                                                 ConnectionException,
                                                 LoginException,
                                                 PasswordException
   {
      Trace.enterFunction("DaifenManager::connect()");

      MailerFactory l_factory = new MailerFactory();

      _mailBox = l_factory.createMailerClient(p_mailerType);

      _mailBox.connect(p_server);

      _mailBox.user(p_user);

      _mailBox.password(p_password);

      _online = true;

      Trace.exitFunction("DaifenManager::connect()");
   }


   public void disconnect() throws ConnectionException
   {
      Trace.enterFunction("DaifenManager::disconnect()");

      _mailBox.disconnect();

      _online = false;

      Trace.exitFunction("DaifenManager::disconnect()");
   }


   public void synchronizePersitenceDB() throws MessageException,
                                                IOException,
                                                ParsingMessageException
   {
      Trace.enterFunction("DaifenManager::synchronizePersitenceDB()");

      //------------- retrieve the list of already saved bilan ---------------

      loadLocalDB();

      //----------- retrieve the remaining bilan not already saved -----------

      if ( _online )
      {
         _mailBox.refreshDownloadedHeadersList();

         //------ extract the bilan mails from the pop server mail list ------

         MailMessage[] l_lstPopBilanMoon = retrieveAvailableBilanMoon();

         //---------- for each retrieved mail fron the mail server -----------
         //---------- check if it already exist in the persitent DB ----------

         String l_moon = "";

         for ( int i = 0 ; i < l_lstPopBilanMoon.length ; i++ )
         {
            l_moon = extractMoonNumber(l_lstPopBilanMoon[i]);

            if ( _lstAvailableBilan.indexOf(l_moon) == -1 )
            {
               //......... add the unread message on the mail server .........

               MailBody l_body = getBody(l_lstPopBilanMoon[i]);
               DaifenMessage l_msg = new DaifenMessage((MailMessage) l_body);
               l_msg.parseMail();
               l_msg.writeObject(PATH_DATA_BILAN + "/" + l_moon + EXT_XML);

               _lstAvailableBilan.add(l_moon);
            }
         }
      }

      Trace.exitFunction("DaifenManager::synchronizePersitenceDB()");
   }


//   public MailMessage getLastBilan() throws MessageException
//   {
//      Trace.enterFunction("DaifenManager::getLastBilan()");
//
//      MailMessage l_msg = _mailBox.findLastMessage(
//         "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+(\\w+)");
//
//      Trace.exitFunction("DaifenManager::getLastBilan()");
//
//      return l_msg;
//   }

//   public MailMessage getBilan(String p_moon) throws MessageException
//   {
//      Trace.enterFunction("DaifenManager::getLastBilan()");
//
//      MailMessage l_msg = _mailBox.findLastMessage(
//         "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+" + p_moon);
//
//      Trace.exitFunction("DaifenManager::getLastBilan()");
//
//      return l_msg;
//   }
   public DaifenMessage getBilan(String p_moon) throws MessageException,
                                                       FileNotFoundException
   {
      Trace.enterFunction("DaifenManager::getLastBilan()");

      DaifenMessage l_msg = null;

      //==== check if the parameter moon exist in the persitence database ====

      if ( _lstAvailableBilan.indexOf(p_moon) != -1 )
      {
         l_msg = new DaifenMessage(PATH_DATA_BILAN + "/" + p_moon + EXT_XML);
      }

      Trace.exitFunction("DaifenManager::getLastBilan()");

      return l_msg;
   }

   public MailBody getBody(MailMessage p_msg) throws MessageException,
                                                        IOException
   {
      Trace.enterFunction("DaifenManager::getBody()");

      if ( p_msg == null )
      {
         throw new NullPointerException();
      }

      MailBody l_mailBody = null;

      l_mailBody = _mailBox.getMessageBody(p_msg);

      Trace.exitFunction("DaifenManager::getBody()");

      return l_mailBody;
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************

   public String extractMoonNumber(MailMessage p_msg)
   {
      String   l_result    = "";
      Pattern  l_pattern   = Pattern.compile(
                             "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+(\\w+)");
      Matcher  l_matcher   = l_pattern.matcher(p_msg.getSubject());

      if ( l_matcher.matches() )
      {
         l_result = l_matcher.group(1);
      }

      return l_result;
   }


   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************

   private void loadLocalDB()
   {
      Trace.enterFunction("DaifenManager::loadLocalDB()");

      String[] l_lstBilan = new File(PATH_DATA_BILAN)
                                        .list(new BilanFilter("(\\d+)\\.xml",
                                                              _startingMoon));

      //-------------- extract moon number from available bilan --------------

      _lstAvailableBilan = new ArrayList();

      Pattern l_pattern = Pattern.compile("(\\d+)\\.xml");

      for ( int i = 0 ; i < l_lstBilan.length ; i++ )
      {
         Matcher l_matcher = l_pattern.matcher(l_lstBilan[i]);

         if ( l_matcher.matches() )
         {
            String l_moon = l_matcher.group(1);

            _lstAvailableBilan.add(l_moon);
         }
      }

      Trace.exitFunction("DaifenManager::loadLocalDB()");
   }


   private MailMessage[] retrieveAvailableBilanMoon()
   {
      Trace.enterFunction("DaifenManager::retrieveAvailableBilanMoon()");

      ArrayList      l_lstResult = new ArrayList();
      MailMessage[]  l_result    = null;
      Matcher        l_matcher   = null;
      Pattern        l_pattern   = Pattern.compile(
                             "\\[Daifen\\]\\s+Bilan\\s+du\\s+Tour\\s+(\\w+)");

      MailHeader[]   l_headers   = _mailBox.getHeaders();

      for ( int i = 0 ; i < l_headers.length ; i++ )
      {
         l_matcher = l_pattern.matcher(l_headers[i].getSubject());

         if ( l_matcher.matches() )
         {
            int l_moon = Integer.parseInt(l_matcher.group(1));

            if ( l_moon >= _startingMoon )
            {
               l_lstResult.add(l_headers[i]);
            }
         }
      }

      l_result = new MailMessage[l_lstResult.size()];
      l_result = (MailMessage[]) l_lstResult.toArray(l_result);

      Trace.exitFunction("DaifenManager::retrieveAvailableBilanMoon()");

      return l_result;
   }
}

//*** EOF ************************************************************ EOF ***