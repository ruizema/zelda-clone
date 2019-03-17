public class Zoe extends Entity {

    private boolean hasCurrentHexaforce;
    private int nbHexaforces;

    public Zoe(Level level, int x, int y) {

        this.name = "Zoe";
        this.nbLives = 5;
        this.x = x;
        this.y = y;
        this.strength = 1;
        this.level = level;
        this.isDead = false;

        this.hasCurrentHexaforce = false;
        this.nbHexaforces = 0;

    }

    public int getNbHexaforce(){
        return nbHexaforces;
    }

    public boolean getHasCurrentHexaforce() { return hasCurrentHexaforce; }

    // Player action functions

    public void move(int x, int y) {
        if (canMoveBy(x, y)) {
            if (x != 0) {
                this.changeX(x);
            } else {
                this.changeY(y);
            }
        }
    }

    public void open() {

        Treasure[] adjacentTreasures = getAdjacentTreasures();

        for (int i = 0; i < adjacentTreasures.length; i++) {
            gainItem(adjacentTreasures[i].getItem());
        }

    }

    public void dig() {

        int[][] adjacentWalls = getAdjacentWalls();

        for (int i = 0; i < adjacentWalls.length; i++) {
            level.setWall(false, adjacentWalls[i][0], adjacentWalls[i][1]);
        }

    }

    // Helper functions

    public Treasure[] getAdjacentTreasures() {

        return (Treasure[]) getAdjacentObjects()[1].toArray(new Treasure[0]);

    }

    public void gainItem(String item) {
        switch (item) {
            case "coeur":
                changeNbLives(1);
                break;
            case "potion de vie":
                changeNbLives(5 - this.getNbLives());
                break;
            case "hexaforce":
                nbHexaforces++;
                hasCurrentHexaforce = true;
                break;
        }
    }

}
