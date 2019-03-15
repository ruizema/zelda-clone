public class Entity {
    private int levelNb=Level.getLevelNumber();//needs to be staitc but isn't
    private String name;
    private int nbLives;
    private int x;
    private int y;
    private int strength;
    private boolean isDead= isDead();


//FROM HERE

    //how to make it static without instantiating a new level.
    private  Object[] object=new Object[Level.getNbObjects()];
    private void  findEntity() {
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
//TO HERE , is where I am confused on how to get the info on entities from level.java



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

    public Entity(){}//default constructor or else it doesn't let me have contructors for my extended classes

    public int getNbLives() {
        return nbLives;
    }

    public void setNbLives(int nbLives){
        this.nbLives=nbLives;
    }

    public int[] getPosition() {
        int[] coordinate= {x,y};
        return coordinate;
    }

    public void setPosition(int x, int y){
        int[] coordinates = getPosition();//or {x,y}? because I want to modify the entities actual position
        coordinates[0]=x;
        coordinates[1]=y;
    }

    public void setX(int newX){
        int[] coordinates= getPosition();
        coordinates[0]=newX;
    }

    public void setY(int newY){
        int[] coordinates= getPosition();
        coordinates[1]=newY;
    }

    public int getStrength(Entity attacker){//the attacker is the current entity
        int strength=0;

        if((attacker.name).equals("Monster")){
            int formula= (int) Math.max(0.4 * (levelNb),1);
            strength=formula;

        }else if((attacker.name).equals("Zoe")){
            strength= 1;//zoe removes one life at a time
        }
        return strength;
    }

    public int[][] getAdjacentCoordinates(int x, int y) {
        int[] position = new int[2];
        x = position[0];
        y = position[1];//x,y in parameters are put in 1D table
        int [][] neighbours= new int[9][2];//9 rows 2 col

        for(var r =0 ;r<neighbours.length;r++) {
            for (var i = -1; i <= 1; i++) {
                for (var j = -1; j <= 1; j++) {
                    neighbours[r][0] =x + j;
                    neighbours[r][1]=y + i;
                }
            }
        }
        return neighbours;

    }

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


    //method is called if SCANNER is "x"
    public void attack() {
        int[][] attackerNeighbours= getAdjacentCoordinates(this.x,this.y);
        //call loseHealth()
        //...
    }

    public void loseHealth(Entity attacked) {
        int force= getStrength(attacked);//attacks according to the strength zoe=1 monsters= ?
        attacked.nbLives-=force;
    }

    public boolean isDead() {//check if dead
        if(nbLives==0) {
            return true;
        }else {
            return false;
        }
    }

}