import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;

public class DaifenDataHtml {
	
	private static String URL_STARTSESSION = "http://www.daifen.com/index.php3?act=connect";
	private static String URL_CLOSESESSION = "http://www.daifen.com/index.php3?act=deconnect";
	
	private String user      = null;
	private String password  = null;
	private String idSession = null;
	
	private String getIdSession () {
		return idSession;
	}
	
	private void setIdSession ( String idSession ) {
		this.idSession = idSession;
	}
	
	DaifenDataHtml ( String user, String password ) {
		this.user = user;
		this.password = password;
	}
	
	DaifenDataHtml () {
	}
	
	private BufferedReader getDataHtml ( String url ) {
		BufferedReader br = null;
		
		try {
			URLConnection uc = new URL(url).openConnection();
			uc.setDoInput(true);
			InputStream is = uc.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		} catch ( Exception e ) {}
		
		return br;
	}
	
	private void connect () {
		if ( user != null && password != password ) {
			Pattern p = null;
			try {
				String s = "idsession=([\\d]+)"; 
				p = Pattern.compile (s);
			} catch ( Exception e ) {}

			String urlConnect = URL_STARTSESSION + "&nom=" + user + "&passw=" + password;
			BufferedReader br = getDataHtml(urlConnect);
			try {
				Matcher m = p.matcher("");
				String line = null;
				while ((line = br.readLine()) != null) {
					m.reset(line);
					if ( m.find() == true ) {
						setIdSession (m.group(1));
						break;
					}
				}
			} catch ( Exception e ) {}
		}
	}
	
	private void disconnect () {	
		if ( user != null && password != password ) {
			String urlDisconnect = URL_CLOSESESSION + "&idsession=" + getIdSession();
			BufferedReader br = getDataHtml(urlDisconnect);
		}
	}
	
	public BufferedReader getPageHtml ( String url ) {
				
		connect ();
		String urlPageHtml = url;		
		if ( user != null && password != null ) {
			urlPageHtml = "&idsession=" + getIdSession();
		}		
		BufferedReader br = getDataHtml(urlPageHtml);
		disconnect();
		
		return br;
	}
	
	private void postDataHtml ( String url, String s ) {

		try {
		
			URLConnection uc = new URL(url).openConnection();
			uc.setDoOutput(true);
			
			OutputStream raw = uc.getOutputStream( );
			OutputStream buffered = new BufferedOutputStream(raw);
			OutputStreamWriter out = new OutputStreamWriter(buffered, "ASCII");
			out.write(s);
			out.write("\r\n");
			out.flush();
			out.close();
		
		} catch ( Exception e ) {
			
		}
		
	}
	
	public void postPageHtml ( String url, ArrayList al ) {
		
		connect();
		
		StringBuffer sb = new StringBuffer ();
		sb.append ("?idsession=" + getIdSession());
		sb.append ("&name=" + user );
		sb.append ("&passw=" + password );
		
		KeyValue key = null;
		for ( int i = 0 ; i < al.size() ; i++ ) {
			key = (KeyValue)al.get(i);
			sb.append ( "&" + key.getKey() + "=" + key.getValue() );
		}
		
		postDataHtml ( url , sb.toString() );
		
		disconnect();
		
	}
	
}
