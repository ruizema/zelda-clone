import java.util.ArrayList;
import java.util.Arrays;

public abstract class Entity {

    private String name;
    private int nbLives;
    private int x;
    private int y;
    private int strength;
    private Level level;
    private boolean isDead;

    /*
    private void findEntity() {
        //not to sure here because I'm not sure what your object table in level contains, if its a table
        //that just seperated ":" with this and idk ..
        for (int i = 0; i < Level.getNbObjects(); i++) {
            if(Level.getObject(i) instanceof Entity) {//If object is an entity we store it in new object[]
                this.object[i] = Level.getObject(i);
                //I believe this is not good because it seems to only get the new zoe..and not the position and item
            }
        }
    }
    public Object[] getEntities(){
        findEntity();
        return this.object;//the object table is now updated with entities
    }
    public Entity getEntity(int idx){
        return (Entity) object[idx];//casting it as an entity
    }
    private boolean[][] walls=new boolean[Level.getMapWidth()][Level.getMapHeight()];//x,y
    private void findWalls(){
        for(int i=0;i<Level.getMapHeight();i++){
            for(int j=0; j< Level.getMapWidth();j++){
                this.walls[j][i]=Level.getWall(j,i);//store all the walls in the variable walls
            }
        }
    }
    public boolean[][] getWallTable(){
        findWalls();
        return this.walls;
    }
    public boolean wallExist(int x, int y ){
        getWallTable();
        return this.walls[x][y];
    }
    public void setWall( int x, int y) {
        if (wallExist(x, y)) {
            walls[y][x] = false;
        }
    }
     */

    // Accessor functions

    public int getNbLives() {
        return nbLives;
    }

    public int[] getPosition() { return new int[]{x, y}; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public boolean getIsDead() { return isDead; }

    // Action functions

    public void attack() {
        Entity[] adjacentEntities = getAdjacentEntities();
        for (int i = 0; i < adjacentEntities.length; i++) {
            Entity entity = adjacentEntities[i];
            Class currentClass = this.getClass();
            if (entity.getClass() != this.getClass() && !(entity.getIsDead())) {
                entity.attacked(this.strength);
            }
        }
    }

    public abstract void move(int x, int y);

    public void attacked(int damage) {
        nbLives = nbLives - damage;
        if (nbLives <= 0) {
            isDead = true;
        }
    }

    // Helper functions for the level

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

    public Entity[] getAdjacentEntities() {

        int[][] adjacentCoordinates = getAdjacentCoordinates();
        ArrayList<Entity> adjacentEntities = new ArrayList<>();

        for (int i = 0; i < adjacentCoordinates.length; i++) {
            for (int j = 0; j < level.getNbObjects(); j++) {
                if
            }
        }

        for (int i = 0; i < level.getNbObjects(); i++) {
            Object object = level.getObject(i);
            if (object instanceof Entity) {
                Entity entity = (Entity) object;
                for (int j = 0; j < adjacentCoordinates.length; j++) {
                    if (Arrays.equals(entity.getPosition(), adjacentCoordinates[j])) { adjacentEntities.add(entity); }
                }
            }
        }

        return adjacentEntities.toArray(Entity[]::new);
    }

    /*
    
    //return the empty cells not containing walls, so possible moves for entities
    public int[][] checkAdjctWalls(int x,int y){
        int[][] potentialNeighbours = getAdjacentCoordinates(x,y);
        int[][] realNeighbours= new int[9][2];
        for(int i=0;i<potentialNeighbours.length;i++){
            int xVariable=potentialNeighbours[i][0];
            int yVariable=potentialNeighbours[i][1];
            if(!wallExist(xVariable,yVariable)){//if their isn't a wall add them in real neighbours
                for(int j=0;j<potentialNeighbours[0].length;j++){
                    realNeighbours[i][j]=potentialNeighbours[i][j];//add only valid neighbours coordinates
                }
            }
        }
        return realNeighbours;
    }
    //the direction variable will depend of the SCANNER in the legendOfZoe class
    public void move(char direction) {
        int[] coordinates=getPosition();//get actual position
        int[][] allowedMouvement=checkAdjctWalls(coordinates[0],coordinates[1]);
        int[] newCoordinates =new int[2];
        if(direction=='w') {//move  up
            newCoordinates[0]= coordinates[0];
            newCoordinates[1]=coordinates[1]-1;
        }else if (direction=='a'){//move to the left
            newCoordinates[0]= coordinates[0]-1;
            newCoordinates[1]=coordinates[1];
        }else if (direction=='s'){//move down
            newCoordinates[0]= coordinates[0];
            newCoordinates[1]=coordinates[1]+1;
        }else if(direction=='d'){//move right
            newCoordinates[0]= coordinates[0]+1;
            newCoordinates[1]=coordinates[1];
        }
        //find if the new coordinates is a possible move
        for(int i=0;i<allowedMouvement.length;i++) {
            int allowedX=allowedMouvement[i][0];
            int allowedY=allowedMouvement[i][0];
            if ((newCoordinates[0]==allowedX)&&(newCoordinates[1]==allowedY)){//check if contained in possible moves
                setPosition(newCoordinates[0],newCoordinates[1]);//Allow movement
            }else{
                System.out.println("Invalid move, try again in next turn!");
            }
        }
    }
    
     */

}