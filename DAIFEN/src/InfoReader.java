/*
 * Created on 18 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.net.*;
import java.io.*;
import java.util.Vector;
import org.xml.sax.*;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.*;

/**
 * @author david
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */



class HtmlData {
  private static String TABLE_START = "<table";
  private static String TABLE_END   = "</table>";
  private static String TR_START    = "<tr";
  private static String TR_END      = "</tr>";
  private static String TD_START    = "<td";
  private static String TD_END      = "</td>";
  private static String BALISE_START = "<";
  private static String BALISE_END   = ">";
	
  private String bufferOfTable        = null;
  private Vector currentLine          = null;
  private int currentPositionInBuffer = -1;
	
  public HtmlData ( String pattern, BufferedReader in ) {
    bufferOfTable = extractTable ( pattern, in );
    {
      System.out.println ( "["+ bufferOfTable + "] TABLE ( " + bufferOfTable.length() + " )");
    }
  }
	
  private void setCurrentPositionInBuffer ( int newPosition ) {
    this.currentPositionInBuffer = newPosition;
  }

  private int getCurrentPositionInBuffer () {
    return this.currentPositionInBuffer;
  }
	
  private String getData ( String s ) {
    StringBuffer sb = new StringBuffer ();
    int positionStart = 0;
    int positionEnd   = -1; 
    while ( ( positionEnd = s.indexOf ( BALISE_END, positionStart ) ) != -1 ) {
      positionStart = s.indexOf ( BALISE_START, positionEnd );
      if ( positionStart - positionEnd > 1 )
	break;
    }

    return s.substring( positionEnd+1, positionStart );
  }

  public Vector getLine () {
    return currentLine;
  }
	
  public boolean next () {
    currentLine = null;

    int positionStartTR = bufferOfTable.indexOf( TR_START , getCurrentPositionInBuffer() );
    int positionEndTR   = bufferOfTable.indexOf( TR_END   , positionStartTR );



    if ( positionStartTR != -1 & positionEndTR != -1 ) {
      currentLine = new Vector();
      {
	String sub = bufferOfTable.substring ( positionStartTR, positionEndTR );
	System.out.println ( "( " + positionStartTR + " ) TR ["+ sub + "] TR ( " + positionEndTR + " ) " );
      }
      // Prend tous les elements <TD>...>DATA<..</TD>
      int positionStartTD = positionStartTR;
      int positionEndTD   = positionStartTR;
      while ( ( positionStartTD = bufferOfTable.indexOf( TD_START , positionStartTD) ) != -1  &
	      ( positionEndTD   = bufferOfTable.indexOf( TD_END   , positionEndTD) )   != -1  &
	      positionStartTD < positionEndTR & positionEndTD < positionEndTR ) {


	{
	  String sub = bufferOfTable.substring ( positionStartTD, positionEndTD );
	  System.out.println ( "( " + positionStartTD + " ) TD ["+ sub + "] TD ( " + positionEndTD + " )");
	}

	currentLine.add( getData ( bufferOfTable.substring( positionStartTD+1, positionEndTD + TD_END.length() ) ) );
	//System.out.println ("==> <" + getData ( bufferOfTable.substring( positionStartTD, positionEndTD + TD_END.length() ))+">");
	positionEndTD += TD_END.length();
	positionStartTD = positionEndTD;
      }
      setCurrentPositionInBuffer (positionEndTR);
    }
		
    if ( currentLine == null )
      return false;
    return true;
  }
	
  private String extractTable ( String pattern, BufferedReader in ) {
    StringBuffer sb = new StringBuffer();;
    try {
      String inputLine = null;
			
      // Cherche le pattern identifiant le tableau
      while ( ( inputLine = in.readLine() ) != null &
	      ( inputLine.indexOf(pattern) != -1 ) );
			
      // Cherche le debut du tableau "<table>"
      while ( ( inputLine = in.readLine() ) != null ) {
	if ( inputLine.indexOf(pattern) != -1 ) {
	  sb.append (inputLine);
	  break;
	}
      }
			
      // Recopie tout le tableau jusqu a "</table>"
      while ( ( inputLine = in.readLine() ) != null ) {
	sb.append (inputLine);
	if ( inputLine.indexOf ( TABLE_END ) != -1 )
	  break;
      }
		
    } catch ( Exception e ) {
      System.out.println ( "KO : " + e );
    }
		
    return sb.toString();
  }
	
}


public class InfoReader {

  private Building[] buildings = null;
  private Trooper[] troopers = null;
  private NetworkDaifen nd = null;
  private Lord user = null;
  
  public InfoReader ( Lord user ) {
    this.user = user;
    nd = new NetworkDaifen ( user );
  }

  private String getIdSession () {
    return nd.getIdSession ();
  }

  public Vector getReglesUnites () {
    return nd.getReglesUnites ();
  }

  public String getContinent () {
    return "totodile";
  }
  
  public int getCurrentTour () {
    return new Integer ( nd.getCurrentTour ()).intValue();
  }
  
  public String getRace () {
    return nd.getRace ( user.getLogin() );
  }

  public String getRace ( String name ) {
    return nd.getRace ( name );
  }

  public Building[] getBuildingsArray ( int numero_du_tour ) {
    Vector v = nd.getBilan( "timents</a><br>" );
    if ( v.size() == 0 )
      return null;

    Building[] b = new Building[v.size()];
    for ( int i = 0; i < v.size(); i++ ) {
      CleValeur cv = (CleValeur)v.elementAt(i);
      b[i] = new Building ( cv.getCle() );
      b[i].setNb ( (new Integer(cv.getValeur())).intValue() );
    }

    return b;

  }

  public Trooper[] getTroopersArray ( int numero_du_tour ) {
    Vector v = nd.getBilan( "Troupes" );
    if ( v.size() == 0 )
      return null;


    Trooper[] b = new Trooper[v.size()];
    for ( int i = 0; i < v.size(); i++ ) {
      CleValeur cv = (CleValeur)v.elementAt(i);
      b[i] = new Trooper ( cv.getCle() );
      b[i].setNb ( (new Integer(cv.getValeur())).intValue() );
    }

    return b;

  }

  public Ressource getRessources ( int numero_du_tour ) {
    Vector v = nd.getBilan ("Ressources");
    if ( v.size() == 0 )
      return null;

    int or = -1;
    int intellect = -1;
    for ( int i = 0; i < v.size(); i++ ) {
      CleValeur cv = (CleValeur)v.elementAt(i);
      if ( cv.getCle().compareTo ( "Or" ) == 0 ) {
	or = new Integer(cv.getValeur()).intValue();
      } else  if ( cv.getCle().compareTo ( "Intellect" ) == 0 ) {
	intellect = new Integer(cv.getValeur()).intValue();
      }
    }

    return new Ressource ( or, intellect );
  }
  
  public Connaissance[] getConnaissances ( int numero_du_tour ) {
    Vector v = nd.getBilan ("Connaissances");
    if ( v.size() == 0)
      return null;

    Connaissance[] b = new Connaissance[v.size()];
    for ( int i = 0; i < v.size(); i++ ) {
      CleValeur cv = (CleValeur)v.elementAt(i);
      b[i] = new Connaissance ( cv.getCle() );
    }

    return b;
  }
  
  public String[] getRoyaumesConnus ( int numero_du_tour ) {
    Vector v = nd.getBilan ("Royaumes connus");
    if ( v.size() == 0 )
      return null;

    String[] b = new String[v.size()];
    for ( int i = 0; i < v.size(); i++ ) {
      CleValeur cv = (CleValeur)v.elementAt(i);
      b[i] = new String ( cv.getCle() );
    }

    return b;
  }
  
  public void disconnect () {
    nd.disconnect();
  }
  
}

class BilanConfig {}

class GameDataConfig {
		
  private Building buildings[];
  private int buildingIndice;
  private Trooper troopers[];
  private int trooperIndice;
	
  public GameDataConfig ( String gameDataConfigFileName ) throws SAXException, IOException {
    XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
    parser.setContentHandler(new DataReaderXML());
    parser.parse(gameDataConfigFileName);
  }

  public int getLengthBuildings () {
    return buildings.length;
  }
  
  public int getLengthTroopers () {
    return troopers.length;
  }
	
  public Building getNextBuilding () {
    if ( buildingIndice <= 0 ) {
      Building temp = buildings[buildingIndice];
      buildingIndice++;
      return temp;
    } else {
      return null;
    }
  }
	
  public Trooper getNextTrooper () {
    if ( trooperIndice <= 0 ) {
      Trooper temp = troopers[trooperIndice];
      trooperIndice++;
      return temp;
    } else {
      return null;
    }
  }
}

class DataReaderXML implements ContentHandler {

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#endDocument()
   */
  public void endDocument() throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#startDocument()
   */
  public void startDocument() throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#characters(char[], int, int)
   */
  public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
   */
  public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
   */
  public void endPrefixMapping(String arg0) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
   */
  public void skippedEntity(String arg0) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
   */
  public void setDocumentLocator(Locator arg0) {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
   */
  public void processingInstruction(String arg0, String arg1) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
   */
  public void startPrefixMapping(String arg0, String arg1) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
   */
  public void endElement(String arg0, String arg1, String arg2) throws SAXException {
    // TODO Auto-generated method stub
		
  }

  /* (non-Javadoc)
   * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
    System.out.println ("-->"+arg1);
		
		
  }
	
	
}

class CleValeur
{
  private String cle = null;
  private String valeur = null;

  public CleValeur ( String cle, String valeur ) {
    this.cle = cle;
    this.valeur = valeur;
  }

  public String getCle () { return this.cle; }
  public String getValeur () { return this.valeur; }

}

class ExtractData
{
  private BufferedReader in = null;
	
  public ExtractData ( BufferedReader in) {
    this.in = in;
  }

  public Vector getCleValeur ( StringBuffer sb ) {
    Vector cvs = new Vector();
    String cle = null;
    String valeur = null;
		
    int step = -1;
    int positionTrStart = 0;
    // Pour chaque TR
    while ( ( ( positionTrStart = sb.indexOf ( "<tr>", positionTrStart ) ) != -1 )  ) {
				
      positionTrStart += 4; // 4 pour la lgr de <tr>
      cle = null;
      valeur = "-1";
				
      int positionTrEnd = sb.indexOf ( "</tr>", positionTrStart );				
      int positionTitres = sb.indexOf ( "<td bgcolor=\"#BBBBBB\">", positionTrStart );
      int positionTH = sb.indexOf( "<th colspan", positionTrStart );
				
      if ( ( positionTitres > positionTrEnd | positionTitres == -1 ) &
	   ( positionTH > positionTrEnd | positionTH == -1 ) ) 
	{	
	  int positionDataStart = positionTrStart;
	  step = 0;
	  // Pour chaque TD (au plus 2)
	  while ( ( ( positionDataStart = sb.indexOf ( ">", positionDataStart ) ) != -1 ) &
		  ( positionDataStart < positionTrEnd ) & step < 2 )
	    {
	      positionDataStart += 1;
	      int positionDataEnd = sb.indexOf( "<", positionDataStart );
	      if ( positionDataEnd - positionDataStart > 0 ) {
		String s = sb.substring ( positionDataStart, positionDataEnd );
		if ( step == 0 ) {
		  cle = s;
		} else {
		  valeur = s;
		}
		step++;
	      }
	    }
	}
			
      if ( cle != null ) {				
	cvs.add ( new CleValeur ( cle, valeur ) );	
      }
    }
		
    return cvs;
  }
	
  public Vector getData ( String pattern )
  {
    String inputLine = null;
    StringBuffer sb = new StringBuffer();
    try {
			
      while ((inputLine = in.readLine()) != null)
	{
	  int position = inputLine.indexOf(pattern);
	  if ( position != -1 )
	    break;
	}

      while ((inputLine = in.readLine()) != null )
	{
	  sb.append (inputLine);
	  if ( inputLine.indexOf("</table>") != -1 )
	    break;
	} 

    } catch (Exception e) {
      System.out.println (e);
    }
		
    return this.getCleValeur(sb);

  }
	
  public void clean () {
    try {
      in.close();
    } catch ( Exception e ) {
      System.out.println ( e );
    }
  }
}

class NetworkDaifen
{

  private Lord user = null;
  private String idSession = null;
  private String currentTour = null;

  public NetworkDaifen ( Lord user ) {
    this.user = user;
    startSession();
  }

  private BufferedReader getDataFromDaifen ( String url ) {
    URLConnection uc = null;
    InputStream is   = null; 
    try {  		
      uc = new URL (url).openConnection();
      is = uc.getInputStream();
    } catch ( Exception e ) {
      System.out.println ("KO : " + e);
    }
	
    return new BufferedReader ( ( new InputStreamReader( is ) ) );
  }

  public Vector getReglesUnites () {

    BufferedReader bf = getDataFromDaifen ("http://www.daifen.com/regles/batiments.php3");
    HtmlData hd = new HtmlData ( "B‚timents disponibles", bf);

    Vector v = new Vector ();
    while ( hd.next() ) {
      v.add ( hd.getLine() );
    }
	
    return v;
  }



  private String extractFromLine ( String patternStart, String patternEnd, String whereToSearch ) {
    int positionPatternStart = whereToSearch.indexOf ( patternStart );
    int positionPatternEnd   = whereToSearch.indexOf ( patternEnd, positionPatternStart + patternStart.length() );
	
    if ( positionPatternStart != -1 & positionPatternEnd != -1 ) {
      return whereToSearch.substring( positionPatternStart + patternStart.length(),
				      positionPatternEnd );
    }
    return null;
  }
  
  public void startSession () {
    try {
      String urlDaifen = "http://www.daifen.com/index.php3?act=connect&nom=" +
	user.getLogin() + "&passw=" + user.getPasswordDaifen();
      		
      URL url = new URL( urlDaifen );
      URLConnection uc = url.openConnection();
      uc.setDoInput(true); // default value de toute facon mais bon...
      
      BufferedReader in = new BufferedReader( new InputStreamReader( uc.getInputStream() ) );

      String inputLine;
      while ( (inputLine = in.readLine()) != null )
	{
	  if ( idSession == null )
	    idSession = extractFromLine ( "idsession=","\"", inputLine );

	  if ( currentTour == null )
	    currentTour = extractFromLine ( "<b>","</b>eme Tour<br>",inputLine );
		
	  if ( idSession != null & currentTour != null )
	    break;
	}	

      in.close();
      
    } catch ( Exception e ) {
      System.out.println ("KO");
    }
    
  }

  public String getRace ( String name ) {

    char lettreStart;
    char lettreEnd;
		
    char firstChar = name.toLowerCase().charAt(0);
    if ( firstChar < 'f' ) {
      lettreStart = 'a';
      lettreEnd   = 'f';
    } else if ( firstChar < 'k' ) {
      lettreStart = 'f';
      lettreEnd   = 'k';
    } else if ( firstChar < 'p' ) {
      lettreStart = 'k';
      lettreEnd   = 'p';
    } else if ( firstChar < 'u' ) {
      lettreStart = 'p';
      lettreEnd   = 'u';
    } else {
      lettreStart = 'u';
      lettreEnd   = 'z';
    }

    int positionName = -1;
    int positionRace = -1;
    String inputLine = null;
	
    try {
	
      String urlDaifen = "http://www.daifen.com/infos/annu.php3?letd=" + lettreStart +
	"&letf=" + lettreEnd + "&idsession="+this.idSession;
  		
      URL url = new URL( urlDaifen );
      URLConnection uc = url.openConnection();
      uc.setDoInput(true); // default value de toute facon mais bon...

      BufferedReader in = new BufferedReader( new InputStreamReader( uc.getInputStream() ) );

      while ( (inputLine = in.readLine()) != null ) {
	if ( ( positionName = inputLine.indexOf( name ) ) != -1 )
	  break;
      }
      while ( (inputLine = in.readLine()) != null ) {
	if ( ( positionRace = inputLine.indexOf ("</font></td>") ) != -1 )
	  break;
      }
      in.close();
		
    } catch ( Exception e ) {
      System.out.println ("KO");
    }
	
    if ( positionName != -1 & positionRace != -1 ) 
      return inputLine.substring ( 0, positionRace );
		
    return null;

  }
  
  public String getCurrentTour () {
    return this.currentTour;
  }

  public String getIdSession () {
    return this.idSession;
  }
  
  public Vector getBilan ( String pattern) {
  	
    ExtractData  ed = null;
    try {
      String urlDaifen = "http://www.daifen.com/tour/index.php3?idsession="+this.idSession;
  		
      URL url = new URL( urlDaifen );
      URLConnection uc = url.openConnection();
      uc.setDoInput(true); // default value de toute facon mais bon...

      ed = new ExtractData ( new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) );
  
    } catch ( Exception e ) {
      System.out.println ("KO");
    }

    //ed.clean();
	
    return ed.getData ( pattern );
  }

  public void disconnect () {
    try {
      String urlDaifen = "http://www.daifen.com/index.php3?act=deconnect&idsession="+this.idSession;	      

      System.out.println ( urlDaifen );
  		
      URL url = new URL( urlDaifen );
      URLConnection uc = url.openConnection();
      uc.setDoInput(true); // default value de toute facon mais bon...

      ExtractData ed = new ExtractData ( new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) );

    } catch ( Exception e ) {
      System.out.println ("KO");
    }
	
  }


  public void sendOrdre () {
  }
}
