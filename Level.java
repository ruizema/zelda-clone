public class Level {

    private int levelNumber;
    private boolean[][] walls;
    private Object[] objects;

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
                    objects[i] = new Treasure(item);
                    break;
                case "monstre":
                    objects[i] = new Monster((int) Math.max(0.6 * levelNumber, 1), x, y);
                    break;
                case "sortie":
                    objects[i] = new Exit(x, y);
                    break;
                case "zoe":
                    objects[i] = new Zoe(x, y);
                    if (levelNumber == 1) {
                        Zoe.setNbLives(5);
                    }
                    break;
            }
        }

    }

    // Getters & setters

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    // Accessing specific objects & walls

    public boolean getWall(int x, int y) {
        return walls[y][x];
    }

    public void setWall(boolean bool, int x, int y) {
        walls[y][x] = bool;
    }

    public Object getObject(int idx) {
        return objects[idx];
    }

    // Used for iterating through the map & the array of objects
    public int getNbObjects() {
        return objects.length;
    }

    public int getMapHeight() {
        return walls.length;
    }

    public int getMapWidth() {
        return walls[0].length;
    }

}
