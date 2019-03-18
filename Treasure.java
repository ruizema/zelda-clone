/**
 * The Treasure class
 */
public class Treasure {

    private String item;
    private int x;
    private int y;
    private boolean isOpen;

    /**
     * Treasure constructor that stores information on the treasure
     * @param item that is contained in the treasure
     * @param x coordinates of the treasure's position in the map of the game
     * @param y
     */
    public Treasure(String item,int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
        this.isOpen = false;
    }

    /**
     * Getter for the item contained int the treasure. This methods also indicates that the theasure is now opened
     * @return String of the item ( example: hexaforce
     */
    public String getItem() {
        isOpen = true;
        return item;
    }

    /**
     * Getter for the position of the treasure in the map
     * @return new int[] of the coordinates
     */
    public int[] getPosition() {
        return new int[]{x, y};
    }

    /**
     * Getter that indicates if the treasure is open (true) or not.
     * @return
     */
    public boolean getIsOpen(){
        return isOpen;
    }
    /**
     * Overrides the toString method to allow the display of the treasures in the map not opened and opened
     * @return a string representing the treasure's status
     */
    @Override
    public String toString() {
        if (isOpen) {
            return "_";
        } else {
            return "$";
        }
    }

}
