/**
 *The level class
 */
public class Level {

    private int levelNumber;
    private boolean[][] walls;
    private Object[] objects;
    private Exit exit;
    private boolean canExit;

    /**
     * Constructor for the game's level. This method uses the  Paire containing two elements
     * (2D array of boolean for th walls and an Array of strings describing the objects in the game.)
     * generated from the LevelGenerator class .This method allows to store all the information
     * (number of treasures , the position of entities etc..)for each level generated
     *
     * @param levelNumber (1 to 6)
     */
    public Level(int levelNumber, int zoeNbLives) {

        Paire<boolean[][], String[]> generated = LevelGenerator.generateLevel(levelNumber);
        this.levelNumber = levelNumber;
        this.walls = generated.getKey();

        String[] generatedObjects = generated.getValue();
        this.objects = new Object[generatedObjects.length];

        this.canExit = false;

        for (int i = 0; i < generatedObjects.length; i++) {
            String[] object = generatedObjects[i].split(":");
            String objectType = object[0];
            String item = "";
            int temp = 0;
            if (object.length == 4) {
                item = object[1];
                temp++;
            }
            int x = Integer.parseInt(object[1 + temp]);
            int y = Integer.parseInt(object[2 + temp]);
            switch (objectType) {
                case "tresor":
                    objects[i] = new Treasure(item, x, y);
                    break;
                case "monstre":
                    objects[i] = new Monster(this, (int) Math.max(0.6 * levelNumber, 1), item, x, y);
                    break;
                case "sortie":
                    objects[i] = new Exit(x, y);
                    this.exit = (Exit) objects[i];
                    break;
                case "zoe":
                    objects[i] = new Zoe(this, x, y, zoeNbLives);
                    break;
            }
        }

    }

    /**
     * Getter for the level number
     * @return the level number
     */

    public int getLevelNumber() { return levelNumber; }

    /**
     * Setter for the level number
     * @param levelNumber new level number
     */

    /**
     * Getter to verify if a wall exist at a certain position
     * @param x coordinate of the position we want to check
     * @param y coordinate of the position we want to check
     * @return a boolean ,True means their is a wall present at that position
     */
    public boolean getWall(int x, int y) {
        if (x >= this.getMapWidth() || x < 0 || y >= this.getMapHeight() || y < 0) {
            return true;
        }
        return walls[y][x];
    }

    /**
     * Setter to change if a wall exist at a certain position
     * @param bool indicates if eist or not with a boolean
     *
     * @param x coordinates of the wall being mutated
     * @param y
     */
    public void setWall(boolean bool, int x, int y) {
        if (!((x >= this.getMapWidth() || x < 0 || y >= this.getMapHeight() || y < 0))) {
            walls[y][x] = bool;
        }
    }

    /**
     * Getter for the object at idx in the array of objects
     * @param idx
     * @return the object
     */
    public Object getObject(int idx) {
        return objects[idx];
    }

    /**
     * Getter for the position of the getCanExit in the level
     * @return an int [] of the coordinates
     */
    public int[] getExitPosition() { return exit.getPosition(); }

    public void exit() {
        if (exit.canAccessExit(this.getZoe())) {
            canExit = true;
        }
    }

    /**
     * Getter of the number of object in the Array of object.
     * This method is use for iterating through the array of objects.
     * @return an int of the length of the object Array
     */

    public int getNbObjects() { return objects.length; }

    /**
     * Getter for the height of the map containing all the walls entities and treasure.
     * This method is used to iterate through the map
     * @return the length of the y distance of the map
     */
    public int getMapHeight() {
        return walls.length;
    }

    /**
     * Getter for the width of the map of the game.Method use to iterate through the map
     * @return the length of the x distance of the map
     */
    public int getMapWidth() {
        return walls[0].length;
    }
    /**
     * Getter for Zoe in the object Array
     * @return null
     */
    public Zoe getZoe() {
        for (int i = 0; i < getNbObjects(); i++) {
            if (getObject(i) instanceof Zoe) { return (Zoe) getObject(i); }
        }
        return null;
    }
    /**
     * Getter for the exit in the level
     * @return exit
     */
    public Exit getExit() {
        return exit;
    }
    /**
     * Method used to verify if Zoe can access the exit in the level
     * @return boolean true yes Zoe has the level's hexaforce she can use the exit , false if she doesn't have it
     */
    public boolean getCanExit() {
        return canExit;
    }
    /**
     * Overrides the toString method to allow the display of the game
     * @return the string of the display of the map
     */
    @Override
    public String toString() {

        String[][] display = new String[getMapHeight()][getMapWidth()];

        // Adding walls
        for (int i = 0; i < getMapHeight(); i++) {
            for (int j = 0; j < getMapWidth(); j++) {
                if (walls[i][j]) {
                    display[i][j] = "#";
                } else {
                    display[i][j] = " ";
                }
            }
        }

        // Adding objects
        for (int i = 0; i < objects.length; i++) {

            Object object = objects[i];
            int[] position = new int[2];

            if (object instanceof Entity) {
                Entity entity = (Entity) object;
                position = entity.getPosition();
            } else if (object instanceof Treasure) {
                Treasure treasure = (Treasure) object;
                position = treasure.getPosition();
            } else if (object instanceof Exit) {
                Exit exit = (Exit) object;
                position = exit.getPosition();
            }

            display[position[1]][position[0]] = object.toString();

        }

        // Converting to string
        String output = "";
        for (int i = 0; i < display.length; i++) {
            output += String.join("", display[i]);
            if (i != display.length - 1) { output += "\n"; }
        }

        return output;

    }

}