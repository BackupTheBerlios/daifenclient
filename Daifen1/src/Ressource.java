
/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 29, 2004
 * Time: 2:23:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ressource {
    private int or;
    private int intellect;

    public Ressource(int or, int intellect) {
        this.or = or;
        this.intellect = intellect;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }
}
