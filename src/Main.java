// CC_VERSIONS

import tools.Trace;

import javax.swing.JButton;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.beans.PersistenceDelegate;
import java.beans.DefaultPersistenceDelegate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.awt.Point;

import specific.data.info.AttackInfo;
import specific.data.info.TroupesInfo;
import specific.DaifenMessage;

/**
 * Main.java
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

class Essai implements Serializable
{
   private String  _name    = new String("Toto");
   public int      _value   = 123;

   public String getName()
   {
      return _name;
   }

   public int getValue()
   {
      return _value;
   }

   public void setName(String p_name)
   {
      _name = p_name;
   }

   public void setValue(int p_value)
   {
      _value = p_value;
   }
//   public void essai()
//   {
//      System.out.println("Execution de la fonction essai()...");
//   }
//
//   public void essai2(int i)
//   {
//      System.out.println("Execution de la fonction essai2(" + i + ")...");
//   }
}

class Stuff {
   private int k = 1;
   private String s = "hello";

   public Stuff() {}
   public int getK() {return k;}
   public String getS(){return s;}
   public void setK(int i) {k=i;}
   public void setS(String t){s=t;}
}

public class Main
{
   //*************************************************************************
   //***                          MEMBER DECLARATION                       ***
   //*************************************************************************

   //================================   PRIVATE   ============================


   //===============================   PROTECTED   ===========================



   //*************************************************************************
   //***                       CONSTRUCTOR DECLARATION                     ***
   //*************************************************************************

   public Main()
   {
   }


   //*************************************************************************
   //***                         PUBLIC DECLARATION                        ***
   //*************************************************************************

   public static void main(String[] args)
   {
      Trace.setTraces((new File("./TRACES")).isFile());

      Trace.println("Main starts...");

      /*try
      {
         AttackInfo l_info = new AttackInfo();
         l_info.setAttackedKingdom("Stollvor");
         l_info.setAttackStatus(1);
         l_info.getLstAttackers().add("Stollvor") ;
         l_info.getLstAttackers().add("Portekwa") ;
         l_info.getLstAttackers().add("Argawaen") ;
         l_info.getLstAttackTroups().add(new TroupesInfo("Shaman", 12));
         l_info.getLstAttackTroups().add(new TroupesInfo("Eluros", 85));

         TroupesInfo l_troupes = new TroupesInfo("Firmir", 60);
         l_troupes.setUnit("Orc");
         l_troupes.setQuantity(123);


         String filename = "Test.xml";
         XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));

         encoder.writeObject(l_info);
         encoder.writeObject(l_info.getLstAttackers());
         encoder.writeObject(l_info.getArrAttackTroups());

         encoder.close();



//         XMLDecoder decoder = new XMLDecoder(
//            new FileInputStream(filename));
//         AttackInfo x =  (AttackInfo)decoder.readObject();
//         x.setLstAttackers((List) decoder.readObject());
//         System.out.println(
//            "k=" +  x.getAttackedKingdom() + " s=" + x.getAttackStatus());
      }
      catch (FileNotFoundException e)
      {
         System.out.println("not found");
      }*/


      String l_file = "347.xml";

      try
      {
         DaifenMessage l_msg = new DaifenMessage(l_file);
      }
      catch ( FileNotFoundException e )
      {
         System.out.println("FileNotFoundException encountered...");
      }


      System.out.println("Main is finished.");
   }


   //*************************************************************************
   //***                        PROTECTED DECLARATION                      ***
   //*************************************************************************



   //*************************************************************************
   //***                         PRIVATE DECLARATION                       ***
   //*************************************************************************


}

//*** EOF ************************************************************ EOF ***