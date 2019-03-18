/**
 * The getCanExit class
 */
public class Exit {

    private int x;
    private int y;

    /**
     * Exit constructor containing the getCanExit's position in the level being played
     * @param x Coordinates of the position on the map of the getCanExit
     * @param y
     */
    public Exit(int x , int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the position of the getCanExit
     * @return int[] of the coordinates
     */
    public int[] getPosition() {
        return new int[]{x, y};
    }

    /**
     * Method that checks if the entity Zoe can acces the getCanExit . This is done by verifying if Zoe has
     * obtain the hexaforce in the level .
     * @param zoe
     * @return True if Zoe has the level's hexaforce , False if Zoe doesn't
     */
    public boolean canAccessExit(Zoe zoe) {
        return zoe.getHasCurrentHexaforce();
    }
    /**
     * Overrides the toString method to display the exit in the map
     * @return the string that represents an exit in the game
     */
    @Override
    public String toString() { return "E"; }

}