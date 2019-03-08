public class Level {
    private int levelNumber;//=LegendOfZoe.getLevel;//How to get it from the legend class so that it inherit the level this line doesnt work
    Paire<boolean[][], String[]> actualLevel= LevelGenerator.generateLevel(levelNumber);
    private boolean Block[][]=actualLevel.getKey(); // the positions of blocks in the level
    private String Entity[] =actualLevel.getValue();;// an array of all the treasures of entities in the level

    //Do we need a constructor for the level ? Because we need the level information in the entity and Item class , so we instanciate them
    //from this constructor
    public Level(int levelNumber,boolean Block[][],String Entity[]){
        this.levelNumber=levelNumber;
        this.Block=Block;
        this.Entity=Entity;
    }

    public int getLevel(){
        return this.levelNumber;
    }
    public boolean[][] getBlock(){
        return this.Block;
    }
    public String[] getEntity(){
            return this.Entity;
    }



}
