// CC_VERSIONS

import exception.*;
import mailbox.MailBody;
import mailbox.MailMessage;
import specific.DaifenManager;
import specific.DaifenMessage;
import specific.data.api.AttackAPI;
import specific.data.api.KingdomDataAPI;
import specific.data.api.SocialAPI;
import specific.data.info.AttackInfo;
import specific.data.info.TroupesInfo;
import tools.Trace;

import java.io.File;
import java.io.IOException;

/**
 * Daifen.java
 *
 * DESCRIPTION:
 *
 *    @author        STOLLVOR  -  Apr 2, 2004
 *    @version       v0.1          
 *
 * HOW TO USE:
 *
 *
 */


public class Daifen
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================

   private static String _mailerType    = "POP3";
   private static String _server        = "pop.mail.yahoo.fr";
   private static String _user          = "stollvor";
   private static String _password      = "";


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public Daifen()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public static void main(String[] args)
   {
      // activate traces

      Trace.setTraces((new File("./TRACES")).isFile());

      try
      {
         DaifenManager l_daifen = new DaifenManager(_mailerType, _server,
                                                    _user, _password);


         try
         {
            MailMessage l_msg = l_daifen.getBilan("344");
//            MailMessage l_msg = l_daifen.getLastBilan();

            if ( l_msg != null )
            {
               MailBody l_body = l_daifen.getBody(l_msg);

               DaifenMessage l_DaifenMsg = new DaifenMessage((MailMessage)l_body);

               try
               {
                  KingdomDataAPI l_kdApi     = l_DaifenMsg.getKingdomDataAPI();

                  //########################   SOCIAL   ######################

                  SocialAPI      l_socialApi = l_kdApi.getSocialAPI();

                  {
                     String l_str    = null;
                     int    l_points = 0;

                     l_str    = l_socialApi.getRank();
                     l_str    = l_socialApi.getInfo();
                     l_points = l_socialApi.getPoints();
                  }

                  //########################   ATTACK   ######################

                  AttackAPI      l_attackApi = l_kdApi.getAttackAPI();

                  {
                     l_attackApi.createIterator();
                     while ( l_attackApi.hasNext() )
                     {
                        AttackInfo l_info = l_attackApi.next();

                        String        l_str;
                        int           l_status;
                        String[]      l_lst1;
                        TroupesInfo[] l_lst2;

                        l_str    = l_info.getAttackedKingdom();
                        l_status = l_info.getAttackStatus();
                        l_lst1   = l_info.getArrAttackers();
                        l_lst2   = l_info.getArrAttackTroups();
                        l_lst2   = l_info.getArrDeadAttackTroups();
                        l_lst2   = l_info.getArrDeadDefenseTroups();
                        l_lst2   = l_info.getArrDefenseTroups();
                        l_lst2   = l_info.getArrDestroyedBuilding();
                     }
                  }

               }
               catch ( ParsingMessageException e )
               {
                  System.out.println("Exception Error: " +
                                     "Unable to retrieve the last bilan.");
               }
            }
         }
         catch ( MessageException e )
         {
            System.out.println("Exception Error: " +
                               "Unable to retrieve the last bilan.");
         }
         catch ( IOException e )
         {
            System.out.println("Exception Error: " +
                               "Unable to retrieve the bilan body.");
         }

      }
      catch ( UnknownMailerClientException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
      catch ( ConnectionException e )
      {
         System.out.println("Exception Error: Problem to connect to .");
      }
      catch ( LoginException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
      catch ( PasswordException e )
      {
         System.out.println("Exception Error: Unknown POP3 mailer.");
      }
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***