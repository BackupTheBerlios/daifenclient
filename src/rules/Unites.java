public class Unites {
	String Nom         = null;
	int Att            = 0;
	int Def            = 0;
	int Int	           = 0;
	int Res	           = 0;
	String BonusContre = null;
	int Cout           = 0;
	String Generateur  = null;
	String ConnaissRequise = null;
	String Race            = null; // pour toutes les races SI null
	
	
	public int getAtt() {
		return Att;
	}

	public void setAtt(int att) {
		Att = att;
	}

	public String getBonusContre() {
		return BonusContre;
	}

	public void setBonusContre(String bonusContre) {
		BonusContre = bonusContre;
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

	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def;
	}

	public String getGenerateur() {
		return Generateur;
	}

	public void setGenerateur(String generateur) {
		Generateur = generateur;
	}

	public int getInt() {
		return Int;
	}

	public void setInt(int int1) {
		Int = int1;
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

	public int getRes() {
		return Res;
	}

	public void setRes(int res) {
		Res = res;
	}

}
