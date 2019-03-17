public class Treasure {

    private String item;
    private int x;
    private int y;
    private boolean isOpen;

    public Treasure(String item,int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
        this.isOpen = false;
    }

    public String getItem() {
        isOpen = true;
        Entity lol = new Monster(1, 1, 1);
        System.out.println(lol.x);
        return item;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean getIsOpen(){
        return isOpen;
    }

}
