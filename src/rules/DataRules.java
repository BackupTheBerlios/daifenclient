import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataRules {

	String _url = null;
	String _localFile = null;
	
	DataRules ( String url, String localFile) {
		set_url(url);
		set_localFile(localFile);
	}
	
	private ArrayList loadLocal ( String s ) {
		System.out.println ("loadLocal");
		ArrayList al = null;
		try {
			FileInputStream fis = new FileInputStream(get_localFile());
			ObjectInputStream ois = new ObjectInputStream(fis);
			al = (ArrayList)ois.readObject();
			ois.close();
			fis.close();
		} catch ( Exception e ) {}
		return al;
	}
	
	private void saveLocal ( ArrayList al , String s) {
		try {
			FileOutputStream fos = new FileOutputStream(s);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(al);
			oos.close();
			fos.close();
		} catch ( Exception e ) {}
	}
	
	private ArrayList loadWeb ( String s ) {
		System.out.println ("loadWeb");
		DaifenDataHtml ddh = new DaifenDataHtml ();
		BufferedReader br = ddh.getPageHtml(s);		
		ReglesReader rr = new ReglesReader (br);
		
		ArrayList al = new ArrayList ();
		ArrayList a = null;
		while ( ( a = rr.getNextRow() ) != null ) {			
			al.add(a);
		}		
		return al;
	}
	
	public ArrayList getRules () {		
		ArrayList al = null;
		
		if ( new File (get_localFile()).canRead() ) {
			al = loadLocal (get_localFile());
		} else {
			al = loadWeb (_url);
			saveLocal ( al, get_localFile());
		}
		
		return al;
	}

	private String get_localFile() {
		return _localFile;
	}

	private void set_localFile(String file) {
		_localFile = file;
	}

	private String get_url() {
		return _url;
	}

	private void set_url(String _url) {
		this._url = _url;
	}

}
