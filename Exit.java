public class Exit {
    private Level level;
    private int levelNb=level.getNbObjects();
    private int xExit;
    private int yExit;
    public Exit(int x , int y){
        this.xExit=x;
        this.yExit=y;
    }
    public int[] getPosition() {
        return new int[]{xExit, yExit};
    }
    public int[] getExit() {
        int[] exitPosition;
        for (int i = 0; i < level.getNbObjects(); i++) {
            Object object = level.getObject(i);
            if (object instanceof Exit) {
                Exit exit = (Exit) object;
                exitPosition= exit.getPosition();//get the position of the exit
            }
        }
        return exitPosition;
    }
    public void checkZoeExit(int x, int y){//parameters correspond to the position of zoe
        int[] exitPosition= getExit();
        if((exitPosition.equals((x,y))){
            level.setLevelNumber(levelNb);//update the level
        }
    }


}
