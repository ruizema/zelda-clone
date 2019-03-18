import java.util.Arrays;

/**
 * The Monster class
 */
public class Monster extends Entity {

    private String item;

    /**
     * Constructor for the Monsters in the game. This allows to have independant information for each monsters
     * in the game for each level.
     * @param level Keeps track of which level this monster belongs to
     * @param nbLives of the monster in the level
     * @param item contained in the monster once it dies
     * @param x coordinates of the monster in the map
     * @param y
     */
    public Monster(Level level, int nbLives, String item, int x, int y) {

        this.name = "Monstre";
        this.nbLives = (int) Math.max(0.6 * level.getLevelNumber(), 1);
        this.x = x;
        this.y = y;
        this.strength = (int) Math.max(0.4 * level.getLevelNumber(), 1);
        this.level = level;
        this.isDead = false;
        this.item = item;

    }

    /**
     * Getter for the item contained in the monster but will only be released when it has been defeated by Zoe.
     * @return the item's name
     */

    public String getItem() { return item; }

    /**
     * This method is the "artificial intelligence " of the monster, meaning it controls their actions when it's
     * their turn to play.It is not the player controlling their movements.
     * @param bonus
     * @return
     */

    public void movementAI(Boolean bonus) {

        int[] movement = new int[]{0, 0};
        Zoe zoe = level.getZoe();

        for (int i = 0; i < level.getNbObjects(); i++) {
            if (level.getObject(i) instanceof Zoe) {
                zoe = (Zoe) level.getObject(i);
            }
        }

        int[][] adjacent = getAdjacentCoordinates();
        int[] zoePosition = zoe.getPosition();
        boolean zoeIsAdjacent = false;

        for (int i = 0; i < adjacent.length; i++) {
            if (Arrays.equals(adjacent[i], zoePosition)) { zoeIsAdjacent = true; }
        }

        if (zoeIsAdjacent) {
            attack();
        } else if (!bonus) {
            for (int i = 0; i < 2; i++) {
                if (zoePosition[i] < this.getPosition()[i]) {
                    movement[i] = -1;
                } else if (zoePosition[i] > this.getPosition()[i]) {
                    movement[i] = 1;
                }
            }
        } else {
            // TODO : BONUS PATHFINDER AI
            System.out.println("TBD");
        }

        move(movement[0], movement[1]);

    }

    /**
     * The method move is used to verify if the monster can make a move to the desired position, meaning if their's
     * a wall on thst position his position will not change.
     * @param x coordinates of the desired cell in the monsters neighbours.
     * @param y
     */

    public void move(int x, int y) {
        if (canMoveBy(x, y)) {
            this.changeX(x);
            this.changeY(y);
        }
    }

    /**
     *Once the monster has been defeated by Zoe and has released his item , the monster must die .
     */

    @Override
    public void die() {
        super.die();
    }
    /**
     * Overrides the toString method to allow the display of the monster on the map when it is alive and dead
     * @return a string representing the monster's status
     */
    @Override
    public String toString() {
        if (isDead) {
            return "x";
        } else {
            return "@";
        }
    }

}
