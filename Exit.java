public class Exit {

    private int x;
    private int y;

    public Exit(int x , int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean canAccessExit(Zoe zoe) { return zoe.getHasCurrentHexaforce(); }

}
