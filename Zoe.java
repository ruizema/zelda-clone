public class Zoe extends Entity {
    private boolean hasCurrentHexaforce = false;
    private static int nbLives = 5;
    private int y;
    private int x;

    public Zoe(int nbLives, int x, int y){//constructeur de zoe
        this.nbLives=nbLives;
        this.x=x;
        this.y=y;

    }
    public int getNbHexaforce(){
        if (hasCurrentHexaforce) {
            return 1;
        }else {
            return 0;
        }

    }
    public void gainLives(int nbLive) {
        int missingHealth = 5 - this.nbLives;//whts missing to have perfect nb of lives
        if ((nbLive == missingHealth) || (nbLive < missingHealth)) {
            this.nbLives = nbLives;//uses all the hearths
        } else if (nbLive > missingHealth) {
            this.nbLives = missingHealth;//so that the lives dont pass 5
        }
        // public void move(){}
        //public String open(){}
        //public void dig(){}
        //public void useItem(){}
    }
}
