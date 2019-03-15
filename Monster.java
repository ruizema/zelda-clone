public class Monster extends Entity {
    private int nbLives;
    private int x;
    private int y;
    private boolean isDead=isDead();//the monster if dead must realeas item,change layout
    private String itemInside;//need to create a method in entity that gets the items inside the monsters

    public Monster(int nbLives, int x, int y){
        super();
        this.nbLives=nbLives;
        this.x=x;
        this.y=y;
    }

    public void dead(){//once is dead =true
        //realeases item
        //changes layout from @ to x

    }
    //public void attack(){}

    //if zoe isn't in the monster's neighbours, this method is called so that it eventually tries to reach her
    public int[] getMoveDirection(int x,int y){//x,y here is the coordinates of zoe.Do we add zoe's name in parameters?
        int[] direction=new int[2];//[x,y] is the direction/move the monster must do.
        int[] monsterPosition=getPosition();//method from entity
        int xDifference=monsterPosition[0]-x;//value from monster to zoe position(on her position)
        int yDifference=monsterPosition[1]-y;

        if(xDifference>0){//positive x value difference,means monster needs to backup in x position
            direction[0]=monsterPosition[0]-1;
        }else if(xDifference<0) {//if difference is negative , monster must advance in x position
            direction[0] = monsterPosition[0] + 1;
        } else if (xDifference == 0) {
            direction[0]=monsterPosition[0];//dont change it
        }
        if(yDifference>0){
            direction[1]=monsterPosition[1]-1;
        }else if(yDifference<0){
            direction[1]=monsterPosition[1]+1;
        }else if(yDifference==0){
            direction[1]=monsterPosition[1];//dont change it
        }

        return direction;
    }

    public void move(){
        int[] desiredDirection= getMoveDirection(this.x,this.y);
        int x =desiredDirection[0];
        int y=desiredDirection[1];

        int[][] allowedMoves= checkAdjctWalls(this.x,this.y);

        for(int i=0; i< allowedMoves.length;i++){
            int xAllowed=allowedMoves[i][0];
            int yAllowed=allowedMoves[i][1];
            if((x==xAllowed)&&(y==yAllowed)){//it is contained
                setPosition(x,y);
            }//else no move allowed,monsters pass their turn.
        }
    }
}
