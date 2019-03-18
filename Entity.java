import java.util.ArrayList;
import java.util.Arrays;

/**
 * The abstract Entity class
 */
public abstract class Entity {

    protected String name;
    protected int nbLives;
    protected int x;
    protected int y;
    protected int strength;
    protected Level level;
    protected boolean isDead;

    /**
     * Getter for the number of lives
     * @return number of lives remaining
     */

    public int getNbLives() { return nbLives; }

    /**
     * Setter that changes the value of the number of lives left to a specific value
     * @param nbLives is the new value for the number of lives
     */
    public void changeNbLives(int nbLives) { this.nbLives = this.nbLives + nbLives; }

    /**
     * Getter for the position of the entity
     * @return new table of the coordinates
     */
    public int[] getPosition() { return new int[]{x, y}; }

    /**
     * Method used to change the x coordinate of the entity by adding x
     * @param x is the value added to the x coordinate of the entity
     */
    public void changeX(int x) { this.x = this.x + x; }
    /**
     * Method used to change the y coordinate of the entity by adding y
     * @param y is the value added to the y coordinate of the entity
     */
    public void changeY(int y) { this.y = this.y + y; }

    /**
     * Getter for the attribute isDead that verifies if the entity is dead
     * @return boolean of if it is dead (true) or not(false)
     */
    public boolean getIsDead() { return isDead; }

    /**
     * Abstract method for the movement of the entity at a certain position
     * @param x the coordinates of the position the entity will move
     * @param y
     */
    public abstract void move(int x, int y);

    /**
     * The attack method is used to allow an entity to attack another entity by removing a certain number of lives.
     * If Zoe is attacking a Monster and she kills him , she can open the item contained in the Monster.
     */
    public void attack() {
        Entity[] adjacentEntities = getAdjacentEntities();
        for (int i = 0; i < adjacentEntities.length; i++) {
            Entity entity = adjacentEntities[i];
            if (entity.getClass() != this.getClass() && !(entity.getIsDead())) {
                entity.attacked(this.strength);
                if (entity.getIsDead() && entity instanceof Monster) {
                    ((Zoe) this).gainItem(((Monster) entity).getItem());
                }
            }
        }
    }

    /**
     * This method will determines the number of lives removed to the entity being attacked. If the attacked entity
     * has no more lives , it dies.
     * @param damage the strength of the entity attacking(Zoe's strength=1 but Monsters strength varies with the level
     *               number==level difficulty)
     */
    public void attacked(int damage) {
        nbLives = nbLives - damage;
        if (nbLives <= 0) {
            die();
        }
    }

    /**
     * Method that change the isDead attribut of the entity from alive (false) to dead(true).
     */
    public void die() { isDead = true; }

    /**
     * The canMoveBy method is used verify if the entity can move to a certain position. It checks if the position
     * is a wall , an getCanExit or a treasure. The method will return false if one of those condition is true because
     * the entity is not allowed to move there.
     * @param x coordinates of the position the entity is trying to move to
     * @param y
     * @return a boolean of if the entity can make a move (true)to the specified position else he can't move (false)
     */

    public boolean canMoveBy(int x, int y) {

        x = this.getPosition()[0] + x;
        y = this.getPosition()[1] + y;

        boolean isWall = level.getWall(x, y);
        boolean isExit = Arrays.equals(level.getExitPosition(), new int[]{x, y});
        boolean isTreasure = false;

        for (int i = 0; i < level.getNbObjects(); i++) {
            Object object = level.getObject(i);
            if (object instanceof Treasure && Arrays.equals(((Treasure) object).getPosition(), new int[]{x, y})) {
                isTreasure = true;
            }
        }

        return !(isExit || isWall || isTreasure);

    }

    /**
     * This method returns all 9 of the neighbours of a certain position in a 2D table
     * @return 2D table of all the neighbours of an entity's position
     */
    public int[][] getAdjacentCoordinates() {

        int [][] neighbours= new int[9][2];
        int count = 0;

        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                neighbours[count][0] = x + i;
                neighbours[count][1] = y + j;
                count++;
            }
        }

        return neighbours;

    }

    /**
     * This method allows to get a list of all the object in the neighbour cells of the entities position
     * @return Array list of all the object in the adjacent cells
     */
    public ArrayList[] getAdjacentObjects() {

        int[][] adjacentCoordinates = getAdjacentCoordinates();

        ArrayList<Entity> adjacentEntities = new ArrayList<>();
        ArrayList<Treasure> adjacentTreasures = new ArrayList<>();

        for (int i = 0; i < level.getNbObjects(); i++) {

            Object object = level.getObject(i);

            if (object instanceof Entity) {

                Entity entity = (Entity) object;

                for (int j = 0; j < adjacentCoordinates.length; j++) {
                    if (Arrays.equals(entity.getPosition(), adjacentCoordinates[j])) {
                        adjacentEntities.add(entity);
                    }
                }

            } else if (object instanceof Treasure) {

                Treasure treasure = (Treasure) object;

                for (int j = 0; j < adjacentCoordinates.length; j++) {
                    if (Arrays.equals(treasure.getPosition(), adjacentCoordinates[j])) {
                        adjacentTreasures.add(treasure);
                    }
                }

            }

        }

        return new ArrayList[]{adjacentEntities, adjacentTreasures};

    }

    /**
     * This method finds all the entities surrounding the entity (that are in the neighbour cells)
     * @return new table of Entity around the current entity playing
     */
    public Entity[] getAdjacentEntities() {

        return (Entity[]) getAdjacentObjects()[0].toArray(new Entity[0]);

    }

    /**
     * This method allows to find all the walls in the entities neighbour cells
     * @return 2D table of the coordinates of the existing walls in the entities nearby cells
     */
    public int[][] getAdjacentWalls() {

        int[][] adjacentCoordinates = getAdjacentCoordinates();
        ArrayList<int[]> adjacentWalls = new ArrayList<>();

        for (int i = 0; i < adjacentCoordinates.length; i++) {
            if (level.getWall(adjacentCoordinates[i][0], adjacentCoordinates[i][1])) {
                adjacentWalls.add(adjacentCoordinates[i]);
            }
        }

        return adjacentWalls.toArray(new int[0][]);

    }

}