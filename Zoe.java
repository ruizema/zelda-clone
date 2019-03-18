import java.util.Arrays;

/**
 * The Zoe class that is a class extending from the Entity class
 */
public class Zoe extends Entity {

    private boolean hasCurrentHexaforce;
    private int nbHexaforces;

    /**
     * Construtor for Zoe that will store the information concerning her status throughout the game's level
     * @param level keeps track of the level Zoe is in
     * @param x coordinates of her position in the map
     * @param y
     */
    public Zoe(Level level, int x, int y, int nbLives) {

        this.name = "Zoe";
        this.nbLives = nbLives;
        this.x = x;
        this.y = y;
        this.strength = 1;
        this.level = level;
        this.isDead = false;

        this.hasCurrentHexaforce = false;
        this.nbHexaforces = 0;

    }

    /**
     * Getter for the number of hexaforce taken by Zoe throughout the levels
     * @return number of hexaforce (0 to 6)
     */
    public int getNbHexaforce(){
        return nbHexaforces;
    }

    /**
     * Getter that verifies if Zoe has obtained the hexaforce in the current level.
     * @return true if she has it , else false
     */
    public boolean getHasCurrentHexaforce() { return hasCurrentHexaforce; }

    /**
     * Method that moves Zoe in the map and verifies if she's allowed to move there.
     *
     * @param x coodinates of the position where Zoe wants to move
     * @param y
     */

    public void move(int x, int y) {

        if (canMoveBy(x, y)) {

            if (x != 0) {
                this.changeX(x);
            } else {
                this.changeY(y);
            }

            for (int i = 0; i < 9; i++) {
                if (Arrays.equals(getAdjacentCoordinates()[i], level.getExitPosition())) {
                    level.exit();
                }
            }

        }
    }

    /**
     * Method that allows Zoe to open a treasure and use it.
     */
    public void open() {

        Treasure[] adjacentTreasures = getAdjacentTreasures();

        for (int i = 0; i < adjacentTreasures.length; i++) {
            gainItem(adjacentTreasures[i].getItem());
        }

    }

    /**
     * Method that allows Zoe to dig walls situated around her (8 neighbours possible). This will change if a wall exist
     *
     */
    public void dig() {

        int[][] adjacentWalls = getAdjacentWalls();

        for (int i = 0; i < adjacentWalls.length; i++) {
            level.setWall(false, adjacentWalls[i][0], adjacentWalls[i][1]);
        }

    }

    /**
     * this method is used to locate all the treasures around a specific treasure that is being opened by zoe.
     * This allows Zoe to open more than one treasure situated in her neighbour cells.
     * @return an array of treasures
     */

    public Treasure[] getAdjacentTreasures() {

        return (Treasure[]) getAdjacentObjects()[1].toArray(new Treasure[0]);

    }

    /**
     * This method allows Zoe tu use the item in the treasure that is being opened
     * @param item contained inside , if she gets a heart her number of lives will increase.
     */
    public void gainItem(String item) {
        System.out.println("Utilisé - " + item);
        switch (item) {
            case "coeur":
                if (getNbLives() < 5) {
                    changeNbLives(1);
                }
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
    /**
     * Overrides the toString method to allow the display of Zoe on the map of the game
     * @return the string representing Zoe
     */
    @Override
    public String toString() { return "&"; }

}

