package infoClass;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 22, 2004
 * Time: 2:40:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Building {
    protected String name;
    protected int id;
    protected int nb;
    protected int R;
    protected int price;
    protected int moreF;
    protected int moreI;
    protected int knowReq;
    protected int forRaceOnly;

    public Building( String name,
                     int id,
                     int nb,
                     int R,
                     int price,
                     int moreF,
                     int moreI,
                     int knowReq,
                     int forRaceOnly ) {
        this.name = name;
        this.id = id;
        this.nb = nb;
        this.R = R;
        this.price = price;
        this.moreF = moreF;
        this.moreI = moreI;
        this.knowReq = knowReq;
        this.forRaceOnly = forRaceOnly;
    }

    public Building(String name) {
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

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMoreF() {
        return moreF;
    }

    public void setMoreF(int moreF) {
        this.moreF = moreF;
    }

    public int getMoreI() {
        return moreI;
    }

    public void setMoreI(int moreI) {
        this.moreI = moreI;
    }

    public int getKnowReq() {
        return knowReq;
    }

    public void setKnowReq(int knowReq) {
        this.knowReq = knowReq;
    }

    public int getForRaceOnly() {
        return forRaceOnly;
    }

    public void setForRaceOnly(int forRaceOnly) {
        this.forRaceOnly = forRaceOnly;
    }

}
