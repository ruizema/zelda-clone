import java.util.Arrays;

public class Monster extends Entity {

    private String item;

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

    // Accessor functions

    public String getItem() { return item; }

    // AIs

    public int[] movementAI(Boolean bonus) {

        int[] movement = new int[]{0, 0};
        Zoe zoe;

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
            return movement;
        }

        return movement;

    }

    // Action functions

    public void move(int x, int y) {
        if (canMoveBy(x, y)) {
            this.changeX(x);
            this.changeY(y);
        }
    }

    // Passive functions

    @Override
    public void die() {
        super.die();
        this.item = "";
    }

}
