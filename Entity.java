
public class Entity {
    private int nbLives; // The entity's number of lives
    private int xPosition; // the x coordinate of the entity on the map
    private int yPosition; // the y coordinate of the entity on the map
    private int Strength;//get the strength of the entity with formula


    public int getNbLives() {
        return nbLives;
    } // Returns the entity's number of lives

    public int[] getPosition() {
        int[] coordinate= {xPosition,yPosition};
        return coordinate;



    }   //returns the coordinates of the entity in the level
    public void setxPosition(int x){
        this.xPosition=x;
    }
    public void setyPosition(int y){
        this.yPosition=y;
    }
    public int getStrength(Entity attacker){

        if((boolean) attacker.equals("Monster")){
            int formula=  ;//acces getLevel from level.java
             return formula;
            //return formule(what is max?, do we have to do a max method and if yes what does (x,1) the one have to do with it)
        }else if(attacker.equals("Zoe")){
             return 1;//zoe enleve seulement une vie a la fois
        }

    }


    public void move(char direction) {
        //must check if the cell is a potentionnal position and is not a wall, it can be a monster(changes l'affichage)
        //on veut avoir acces a Paire <walls,..> pour savoir si a la coordonne il y a un block ou non pour attacker
        if(direction=='w') {//move  up
            setyPosition((this.yPosition - 1));
        }else if (direction=='a'){//move to the left
            setxPosition((this.xPosition-1));
        }else if (direction=='s'){//move down
            setyPosition((this.yPosition+1));

        }else if(direction=='d'){//move right
            setxPosition((this.xPosition+1));
        }

    }

    //checks if there is a block in the direction of movement, then allow movement

    public void attack() {
        //if a neighbour , call method lose health for the other entity at that position

    }

   // allows attacking adjacent enemy entities

    public void loseHealth(Entity attacker) {
       int force= getStrength(attacker);//attacks according to the strength zoe=1 monsters= ?
       this.nbLives-=force;
    }

    //Lose life points from being attacked, depending on the attacker

    public void die() {


    }

    //Kills the entity if it has 0 lives

    public int[][] getAdjacentCoordinates() {//mettre conditions pour si ya un mur c'est pas une case voisine
        int[] position = getPosition();
        int x = position[0];
        int y = position[1];
        int [][] neighbours= new int[9][2];//9 rangee 2 colonne
        for(var r =0 ;r<neighbours.length;r++) {
            for (var i = -1; i <= 1; i++) {
                for (var j = -1; j <= 1; j++) {
                    neighbours[r][0] =x + j;
                    neighbours[r][1]=y + i;
                }
            }
        }
        return neighbours;
        //use getPosition and return all the neighbours
    } //return an array containing 9 arrays of 2 ints each, representing the coordinates of the 9 adjacent tiles
}