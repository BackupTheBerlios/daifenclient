public class Batiment {
	private String Nom             = null;
	private int Resistance         = 0;
	private int Cout	           = 0;
	private int ApportOr           = 0;
	private int ApportIntellect	   = 0;
	private String ConnaissRequise = null;
	private String Race            = null; // contient null SI pour toutes les races
	
	public int getApportIntellect() {
		return ApportIntellect;
	}

	public void setApportIntellect(int apportIntellect) {
		ApportIntellect = apportIntellect;
	}

	public int getApportOr() {
		return ApportOr;
	}

	public void setApportOr(int apportOr) {
		ApportOr = apportOr;
	}

	public String getConnaissRequise() {
		return ConnaissRequise;
	}

	public void setConnaissRequise(String connaissRequise) {
		ConnaissRequise = connaissRequise;
	}

	public int getCout() {
		return Cout;
	}

	public void setCout(int cout) {
		Cout = cout;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getRace() {
		return Race;
	}

	public void setRace(String race) {
		Race = race;
	}

	public int getResistance() {
		return Resistance;
	}

	public void setResistance(int resistance) {
		Resistance = resistance;
	}
}
