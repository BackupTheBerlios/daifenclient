import java.util.*;

public class BatimentsRules {
	private final static String url = "http://www.daifen.com/regles/batiments.php3";
	private final static String localFile = "batimentsRules.ser";
	
	private Hashtable h = null;
	
	public BatimentsRules () {
		init();
	}

	private static final int POSITION_NOM = 0;
	private static final int POSITION_RES = 1;
	private static final int POSITION_COU = 2;
	private static final int POSITION_APO = 3;
	private static final int POSITION_API = 4;
	private static final int POSITION_CON = 5;
	private static final int POSITION_RAC = 6;
	private void init () {
		ArrayList al = (new DataRules (url,localFile)).getRules();

		h = new Hashtable ();
		
		for ( int i = 0 ; i < al.size() ; i++ ) {
			ArrayList a = (ArrayList)al.get(i);
			
			Batiment b = new Batiment ();
			b.setNom((String)a.get(POSITION_NOM));
			b.setResistance((new Integer((String)a.get(POSITION_RES))).intValue());
			b.setCout((new Integer((String)a.get(POSITION_COU))).intValue());
			
			String s = (String)a.get(POSITION_APO);
			if ( s.matches("&nbsp;") ) {
				b.setApportOr(0);
			} else {
				b.setApportOr((new Integer(s).intValue()));
			}
			
			s = (String)a.get(POSITION_API);
			if ( s.matches("&nbsp;") ) {
				b.setApportIntellect(0);
			} else {
				b.setApportIntellect((new Integer(s).intValue()));
			}
			
			s = (String)a.get(POSITION_CON);
			if ( s.matches("&nbsp;") ) {
				b.setConnaissRequise("");
			} else {
				b.setConnaissRequise(s);
			}
			
			s = (String)a.get(POSITION_RAC);
			if ( s.matches("&nbsp;") ) {
				b.setRace("");
			} else {
				b.setRace(s);
			}
			
			h.put((String)a.get(POSITION_NOM), b);
		}
	}
	
	public String getConnaissanceRequise ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getConnaissRequise();
	}
	
	public int getApportOr ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getApportOr();
	}
	
	public int getApportIntellect ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getApportIntellect();
	}
	
	public int getResistance ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getResistance();
	}
	
	public int getCout ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getCout();
	}
	
	public String getRace ( String s ) {
		Batiment b = (Batiment)h.get(s);
		return b.getRace();
	}
}
