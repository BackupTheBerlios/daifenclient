package infoClass;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 29, 2004
 * Time: 10:21:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Lord {

    private String login;
    private String passwordDaifen;
    private String passwordYahoo;
    private String eMail;
    private String race;


     public Lord(String login, String passwordDaifen, String passwordYahoo, String eMail, String race) {
        this.login = login;
        this.passwordDaifen = passwordDaifen;
        this.passwordYahoo = passwordYahoo;
        this.eMail = eMail;
        this.race = race;
    }

    public Lord(String login, String passwordDaifen, String passwordYahoo, String race) {
        this.login = login;
        this.passwordDaifen = passwordDaifen;
        this.passwordYahoo = passwordYahoo;
        this.race = race;
    }

    public Lord(String login, String passwordDaifen, String passwordYahoo) {
        this.login = login;
        this.passwordDaifen = passwordDaifen;
        this.passwordYahoo = passwordYahoo;
    }

    public Lord(String login, String eMail) {
        this.login = login;
        this.eMail = eMail;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordDaifen() {
        return passwordDaifen;
    }

    public String getPasswordYahoo() {
        return passwordYahoo;
    }

    public String getEMail() {
          return eMail;
      }

      public void setEMail(String eMail) {
          this.eMail = eMail;
      }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }


}
