
/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 22, 2004
 * Time: 4:17:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Connaissance {
    
    private String name = null;
    private int id = -1;
    private int knowReq = -1;

    public Connaissance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKnowReq() {
        return knowReq;
    }

    public void setKnowReq(int knowReq) {
        this.knowReq = knowReq;
    }
}
