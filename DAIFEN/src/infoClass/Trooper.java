package infoClass;

/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 22, 2004
 * Time: 2:32:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Trooper {
    protected String name;
    protected int id;
    protected int nb;
    protected int A;
    protected int D;
    protected int I;
    protected int R;
    protected int price;
    protected int generator;
    protected int knowReq;
    protected int forRaceOnly;

    public Trooper( String name,
                    int id,
                    int nb,
                    int A,
                    int D,
                    int I,
                    int R,
                    int price,
                    int generator,
                    int knowReq,
                    int forRaceOnly){
        this.name = name;
        this.id = id;
        this.nb = nb;
        this.A = A;
        this.D = D;
        this.I = I;
        this.R = R;
        this.price = price;
        this.generator = generator;
        this.knowReq = knowReq;
        this.forRaceOnly = forRaceOnly;
    }

    public Trooper(String name) {
        this.name = name;
    }

    public int getForRaceOnly() {
        return forRaceOnly;
    }

    public void setForRaceOnly(int forRaceOnly) {
        this.forRaceOnly = forRaceOnly;
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

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
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

    public int getGenerator() {
        return generator;
    }

    public void setGenerator(int generator) {
        this.generator = generator;
    }

    public int getKnowReq() {
        return knowReq;
    }

    public void setKnowReq(int knowReq) {
        this.knowReq = knowReq;
    }

}
