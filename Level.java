public class Level {

    private int levelNumber;
    private boolean[][] walls;
    private Object[] objects;
    private Exit exit;

    public Level(int levelNumber) {

        // Basic level generation
        Paire<boolean[][], String[]> generated = LevelGenerator.generateLevel(levelNumber);
        this.levelNumber = levelNumber;
        this.walls = generated.getKey();

        // Parsing string of objects
        String[] generatedObjects = generated.getValue();
        this.objects = new Object[generatedObjects.length];

        for (int i = 0; i < generatedObjects.length; i++) {
            String[] object = generatedObjects[i].split(":");
            String objectType = generatedObjects[0];
            String item;
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
                    objects[i] = new Zoe(this, x, y);
                    if (levelNumber == 1) {
                        Zoe.changeNbLives(5);
                    }
                    break;
            }
        }

    }

    // Accessor functions

    public int getLevelNumber() { return levelNumber; }

    public void setLevelNumber(int levelNumber) { this.levelNumber = levelNumber; }

    public boolean getWall(int x, int y) {
        return walls[y][x];
    }

    public void setWall(boolean bool, int x, int y) {
        walls[y][x] = bool;
    }

    public Object getObject(int idx) {
        return objects[idx];
    }

    public int[] getExitPosition() { return exit.getPosition(); }

    public boolean checkExit(Zoe zoe) { return exit.canAccessExit(zoe); }

    // Used for iterating through the map & the array of objects

    public int getNbObjects() { return objects.length; }

    public int getMapHeight() {
        return walls.length;
    }

    public int getMapWidth() {
        return walls[0].length;
    }

}
