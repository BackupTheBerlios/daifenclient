// CC_VERSIONS

import exception.*;
import mailbox.MailBody;
import mailbox.MailMessage;
import specific.DaifenManager;
import specific.DaifenMessage;
import specific.data.api.*;
import specific.data.info.*;
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
                  DataKingdomAPI l_kdApi = l_DaifenMsg.getKingdomDataAPI();

                  //########################   ATTACK   ######################

                  {
                     AttackAPI l_api = l_kdApi.getAttackAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        AttackInfo l_info = l_api.next();

                        String        l_str;
                        int           l_int;
                        String[]      l_lst1;
                        TroupesInfo[] l_lst2;

                        l_str    = l_info.getAttackedKingdom();
                        l_int    = l_info.getAttackStatus();
                        l_lst1   = l_info.getArrAttackers();
                        l_lst2   = l_info.getArrAttackTroups();
                        l_lst2   = l_info.getArrDeadAttackTroups();
                        l_lst2   = l_info.getArrDeadDefenseTroups();
                        l_lst2   = l_info.getArrDefenseTroups();
                        l_lst2   = l_info.getArrDestroyedBuilding();
                     }
                  }

                  //#######################   CONTACTS   #####################

                  {
                     ContactAPI l_api = l_kdApi.getContactAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        ContactInfo l_info = l_api.next();

                        String        l_str;

                        l_str    = l_info.getKingdom();
                        l_str    = l_info.getSpecies();
                        l_str    = l_info.getEmail();
                     }
                  }

                  //#######################   ECONOMY   ######################

                  {
                     EconomyAPI l_api = l_kdApi.getEconomyAPI();

                     int           l_int;

                     l_int = l_api.getGold();
                     l_int = l_api.getIntellect();
                  }

                  //######################   KNOWLEDGE   #####################

                  {
                     KnowledgeAPI l_api = l_kdApi.getKnowledgeAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        KnowledgeInfo l_info = l_api.next();

                        String        l_str;
                        int           l_int;

                        l_str    = l_info.getknowledge();
                        l_int    = l_info.getTurn();
                     }
                  }

                  //#######################   RUMOUR   #######################

                  {
                     RumourAPI l_api = l_kdApi.getRumourAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        RumourInfo l_info = l_api.next();

                        String        l_str;

                        l_str    = l_info.getKingdom();
                        l_str    = l_info.getRumour();
                     }
                  }

                  //#####################   INV_TROUPES   ####################

                  {
                     TroupesAPI l_api = l_kdApi.getTroupesAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        TroupesInfo l_info = l_api.next();

                        String   l_str;
                        int      l_int;

                        l_str    = l_info.getUnit();
                        l_int    = l_info.getQuantity();
                     }
                  }

                  //####################   INV_BATIMENTS   ###################

                  {
                     TroupesAPI l_api = l_kdApi.getBatimentsAPI();

                     l_api.createIterator();
                     while ( l_api.hasNext() )
                     {
                        TroupesInfo l_info = l_api.next();

                        String   l_str;
                        int      l_int;

                        l_str    = l_info.getUnit();
                        l_int    = l_info.getQuantity();
                     }
                  }

                  //########################   SOCIAL   ######################

                  {
                     SocialAPI l_api = l_kdApi.getSocialAPI();

                     String l_str    = null;
                     int    l_points = 0;

                     l_str    = l_api.getRank();
                     l_str    = l_api.getInfo();
                     l_points = l_api.getPoints();
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