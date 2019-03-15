public class Zoe extends Entity {
    private boolean hasCurrentHexaforce=false;
    private int nbLives;
    private int y;
    private int x;
    private int nbHexaforces=0;//she starts with zero hxforces
    private boolean isDead=isDead();//This method must always be checked, the second this is true , entitie dies

    public Zoe(int x, int y){//constructor of zoe
        super();
        this.x=x;
        this.y=y;

    }

    public int getNbHexaforce(){
        return nbHexaforces;
    }
    //IS THIS NECESSARY?
    public void gainLives(int nbLive) {
        int missingHealth = 5 - this.nbLives;//whats missing to have perfect nb of lives
        if ((nbLive == missingHealth) || (nbLive < missingHealth)) {
            this.nbLives = nbLives;//uses all the hearths
        } else if (nbLive > missingHealth) {
            this.nbLives += missingHealth;//so that the lives dont pass 5
        }
    }
    @Override//does it override the method ? I don't think so
    public void move(char direction){
        super.move(direction);//accesses the method of superclass

    }

    //public void attack(){}//need to figure this method in entity.java

    //public String open(){}//called in usedItem? to change the layout as an open treasure or dead monster ??

    public void dig(){//is called when "c" is in the scanner
        int[][] curentNeighbours=getAdjacentCoordinates(this.x,this.y);
        int xWall;
        int yWall;
        for(int i=0;i<curentNeighbours.length;i++) {//get all the neighbours coordinates
            xWall = curentNeighbours[i][0];
            yWall = curentNeighbours[i][1];

            if (wallExist(xWall, yWall)) {//if a wall, change it to not a wall
                setWall(this.x, this.y);//changes to false
            }
        }
    }
    public void useItem(String item){
        switch (item) {
            case "potionvie":
                gainLives(5);
                break;
            case "coeur":
                gainLives(1);
                break;
            case "hexaforce":
                hasCurrentHexaforce=true;
                nbHexaforces+=1;//update variable
                break;
            case "exit":
                if(hasCurrentHexaforce){
                    //call the exit method or something ?thats calls a new level
                }
        }
    }


}
