import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ReglesReader {

	ArrayList _listOfRows = null;
	BufferedReader _br    = null;
	
	public ReglesReader ( BufferedReader br ) {
		_br         = br;
		_listOfRows = new ArrayList ();
		
		init();
	}
	
	private void init() {
		String sDebutTR  = "<tr><td>";
		String sFinTR    = "</tr>";
		String sTD       = "(.+)</td>$";
		Pattern pDebutTR = Pattern.compile(sDebutTR);
		Pattern pFinTR   = Pattern.compile(sFinTR);
		Pattern pTD      = Pattern.compile(sTD);
		
		Matcher m = null;
		
		String line = null;
		try {
			
			ArrayList a = null;
			boolean isInsideTR = false;
			while ((line = _br.readLine()) != null) {
				
				if ( isInsideTR == false ) {

					m = pDebutTR.matcher(line);
					if ( m.find() == true ) {
						isInsideTR = true;
						a = new ArrayList ();
					}
					
				} else {
					m = pFinTR.matcher (line);
					if ( m.find() == true ) {
						isInsideTR = false;
						_listOfRows.add(a);
					} else {
						m = pTD.matcher (line);
						if ( m.find() == true ) {
							a.add(m.group(1));
						}
					}
				}
			}
			
		} catch (Exception e ) {
			
		}
		
	}
	
	public ArrayList getNextRow () {
		ArrayList a = null;
		
		if ( _listOfRows.isEmpty() == false ) {
			a = (ArrayList)_listOfRows.get(0);
			_listOfRows.remove(0);
		}
		
		return a;
	}
	
}
