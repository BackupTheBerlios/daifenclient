
/**
 * Created by IntelliJ IDEA.
 * User: pfouche
 * Date: Mar 22, 2004
 * Time: 1:40:30 PM
 * To change this template use File | Settings | File Templates.
 */

/* TODO
class Race {
    String name;
    int id;

    public static final Race Unknow     = new Race( "Unknow", -1 );
    public static final Race humain     = new Race( "Humain", 0 );
    public static final Race elfe       = new Race( "Elfe", 1 );
    public static final Race nain       = new Race( "Nain", 0 );
    public static final Race orc        = new Race( "Orc", 3 );
    public static final Race mortVivant = new Race( "Mort vivant", 4 );

   public Race (String name, int id) {
       this.name = name;
       this.id = id;
   }

}
       */
public class Royaume {

    private Lord lord;

    private Ressource ressource;
    private Ressource tmpRessource;

    private Connaissance connaissances[];
    private int nbKnowlegde = 0;

    private Building buildings[];
    private int nbBuildingType = 0;

    private Trooper troopers[];
    private int nbTrooperType = 0;

    public void addBuilding( int buildingType, int nbNew ){
        int i;
        boolean knowReqFind = false;
        int numFind = -1;
/*????????
        // Test de faisabilite
        for ( i=0; i < nbKnowlegde; i++){
            if ( InfoReader.getBuildingKnowReq(buildingType) == knowledges[i]){
                knowReqFind = true;
            }
        }

        if ( knowReqFind
                && InfoReader.getBuildingPrice(buildingType) * nbNew < tresor
                && (   (InfoReader.getBuildingForRaceOnly(buildingType) == Race.Unknow.id)
                || (InfoReader.getBuildingForRaceOnly(buildingType) == race.id) ) ) {


            for ( i=0; i < nbBuildingType; i++ ){
                if ( buildings[i].id == buildingType ){
                    numFind = i;
                }
            }

            if ( numFind > 0){
                //?????????????????????
                buildings[nbBuildingType] = new Building(InfoReader.getBuildname(buildingType),
                        InfoReader.getBuildingId(buildingType),
                        nbNew,
                        InfoReader.getBuildingR(buildingType),
                        InfoReader.getBuildingPrice(buildingType),
                        InfoReader.getBuildingMoreF(buildingType),
                        InfoReader.getBuildingMoreI(buildingType),
                        InfoReader.getBuildingKnowReq(buildingType),
                        InfoReader.getBuildingForRaceOnly(buildingType));

                nbBuildingType++;
            }
            else{
                buildings[numFind].nb += nbNew;
            }


        }
        else{
            //???????? Afficher une dialogue box pour dire qu'on ne peut pas ajouter la building

        }
        */
    }

    public void destroyBuilding( int buildingType, int nbDestroy ) {
        int i;
        int numFind = -1;

        for ( i=0; i < nbBuildingType; i++ ){
            if ( buildings[i].id == buildingType ){
                buildings[i].nb -= nbDestroy;

                // on degage l'enregistrement du tableau si il n'y en a plus
                if ( buildings[i].nb <= 0 ){
                    int j;
                    for ( j = i; j < nbBuildingType-1; j++){
                        buildings[j] = buildings[j+1];
                    }
                    buildings[j] = null;
                    nbBuildingType--;
                }
            }
        }
    }

    public void addTrouper( int trooperType, int nbNew ){
        int i;
        boolean knowReqFind = false;
        boolean generatorFind = false;
        int numFind = -1;

/*?????????
        // Test de faisabilite
        for ( i=0; i < nbKnowlegde; i++){
            if ( (InfoReader.getTrooperKnowReq(trooperType) == knowledges[i].id)
                || (InfoReader.getTrooperKnowReq(trooperType) == Knowledge.Unknow.id) ){
                knowReqFind = true;
            }
        }


        for ( i=0 ; i < nbBuildingType; i++){
            if ( (InfoReader.getTrooperGenerator(trooperType) == buildings[i].id)
                    || (InfoReader.getTrooperGenerator(trooperType) == Building.Unknow.id) ){
                generatorFind = true;
            }
        }

        if ( knowReqFind && generatorFind
                && InfoReader.getTrooperPrice(trooperType) * nbNew < tresor
                && (   (InfoReader.getTrooperForRaceOnly(trooperType) == Race.Unknow.id)
                || (InfoReader.getTrooperRaceOnly(trooperType) == race.id) ) ) {


            for ( i=0; i < nbTrooperType; i++ ){
                if ( troopers[i].id == trooperType ){
                    numFind = i;
                }
            }

            if ( numFind > 0){
                //?????????????????????
                troopers[nbTrooperType] = new Trooper(InfoReader.getTrooperName(trooperType),
                        InfoReader.getTrooperId(trooperType),
                        nbNew,
                        InfoReader.getTrooperA(trooperType),
                        InfoReader.getTrooperD(trooperType),
                        InfoReader.getTrooperI(trooperType),
                        InfoReader.getTrooperR(trooperType),
                        InfoReader.getTrooperPrice(trooperType),
                        InfoReader.getTrooperGenerator(trooperType),
                        InfoReader.getTrooperKnowReq(trooperType),
                        InfoReader.getTrooperForRaceOnly(trooperType));

                nbTrooperType++;
            }
            else{
                buildings[numFind].nb += nbNew;
            }


        }
        else{
            //???????? Afficher une dialogue box pour dire qu'on ne peut pas ajouter la building

        }
     */
    }


    public void destroyTrooper( int trooperType, int nbDestroy ) {
        int i;

        for ( i=0; i < nbTrooperType; i++ ){
            if ( troopers[i].id == trooperType ){
                troopers[i].nb -= nbDestroy;

                // on degage l'enregistrement du tableau si il n'y en a plus
                if ( troopers[i].nb <= 0 ){
                    int j;
                    for ( j = i; j < nbTrooperType-1; j++){
                        troopers[j] = troopers[j+1];
                    }
                    troopers[j] = null;
                    nbTrooperType--;
                }
            }
        }
    }

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public Ressource getTmpRessource() {
        return tmpRessource;
    }

    public void setTmpRessource(Ressource tmpRessource) {
        this.tmpRessource = tmpRessource;
    }

    public Connaissance[] getConnaissances() {
        return connaissances;
    }

    public void setConnaissances(Connaissance[] connaissances) {
        this.connaissances = connaissances;
    }

    public int getNbKnowlegde() {
        return nbKnowlegde;
    }

    public void setNbKnowlegde(int nbKnowlegde) {
        this.nbKnowlegde = nbKnowlegde;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }

    public int getNbBuildingType() {
        return nbBuildingType;
    }

    public void setNbBuildingType(int nbBuildingType) {
        this.nbBuildingType = nbBuildingType;
    }

    public Trooper[] getTroopers() {
        return troopers;
    }

    public void setTroopers(Trooper[] troopers) {
        this.troopers = troopers;
    }

    public int getNbTrooperType() {
        return nbTrooperType;
    }

    public void setNbTrooperType(int nbTrooperType) {
        this.nbTrooperType = nbTrooperType;
    }
}


