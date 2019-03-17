import java.util.ArrayList;
import java.util.Arrays;

public abstract class Entity {

    protected String name;
    protected int nbLives;
    protected int x;
    protected int y;
    protected int strength;
    protected Level level;
    protected boolean isDead;

    // Accessor functions

    public int getNbLives() { return nbLives; }

    public void changeNbLives(int nbLives) { this.nbLives = this.nbLives + nbLives; }

    public int[] getPosition() { return new int[]{x, y}; }

    public void changeX(int x) { this.x = this.x + x; }

    public void changeY(int y) { this.y = this.y + y; }

    public boolean getIsDead() { return isDead; }

    // Action functions

    public abstract void move(int x, int y);

    public void attack() {
        Entity[] adjacentEntities = getAdjacentEntities();
        for (int i = 0; i < adjacentEntities.length; i++) {
            Entity entity = adjacentEntities[i];
            if (entity.getClass() != this.getClass() && !(entity.getIsDead())) {
                entity.attacked(this.strength);
                if (entity.getIsDead() && entity instanceof Monster) { this.gainItem(((Monster) entity).getItem()); }
            }
        }
    }

    // Passive functions

    public void attacked(int damage) {
        nbLives = nbLives - damage;
        if (nbLives <= 0) {
            die();
        }
    }

    public void die() { isDead = true; }

    // Helper functions for the level

    public boolean canMoveBy(int x, int y) {

        x = this.getPosition()[0] + x;
        y = this.getPosition()[1] + y;

        boolean isExit = Arrays.equals(level.getExitPosition(), new int[]{x, y});
        boolean isWall = level.getWall(this.getPosition()[0] + x, this.getPosition()[1] + y);
        boolean isTreasure = false;

        for (int i = 0; i < level.getNbObjects(); i++) {
            Object object = level.getObject(i);
            if (object instanceof Treasure && Arrays.equals(((Treasure) object).getPosition(), new int[]{x, y})) {
                isTreasure = true;
            }
        }

        return !(isExit || isWall || isTreasure);

    }

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

    public Entity[] getAdjacentEntities() {

        return (Entity[]) getAdjacentObjects()[0].toArray(new Entity[0]);

    }

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