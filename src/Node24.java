import java.util.ArrayList;

public class Node24 {
    
    private char value;
    private Node24 up;
    private Node24 left;
    private Node24 down;
    private Node24 right;
    private int x = 0;
    private int y = 0;
    private ArrayList<Blizzard> blizzards = new ArrayList<>();
    private ArrayList<Blizzard> newBlizzards = new ArrayList<>();

    Node24(char value, Node24 up, Node24 left, Node24 down, Node24 right, int x, int y){
        
        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;
        this.x = x;
        this.y = y;

        if (value == '.' || value == '#'){
            this.value = value;
        } else {
            this.value = '.';
        }

    }

    protected int getX(){
        return x;
    }

    protected int getY(){
        return y;
    }

    protected Node24 getUp(){
        return up;
    }

    protected void setUp(Node24 up){
        this.up = up;
    }

    protected Node24 getLeft(){
        return left;
    }

    protected void setLeft(Node24 left){
        this.left = left;
    }

    protected Node24 getDown(){
        return down;
    }

    protected void setDown(Node24 down){
        this.down = down;
    }

    protected Node24 getRight(){
        return right;
    }

    protected void setRight(Node24 right){
        this.right = right;
    }

    protected char getValue(){
        return value;
    }

    protected boolean isWall(){
        return value == '#';
    }

    protected void addBlizzard(Blizzard blizzard){
        blizzards.add(blizzard);
    }

    protected boolean hasBlizzard(){
        return blizzards.size() != 0;
    }

    protected Blizzard getBlizzard(){
        
        if (blizzards.isEmpty()){
            return null;
        }

        return blizzards.remove(0);
    }

    protected ArrayList<Blizzard> blizzards(){
        return blizzards;
    }

    protected void addNewBlizzard(Blizzard blizzard){
        newBlizzards.add(blizzard);
    }

    protected void changeBlizzardList(){

        blizzards = newBlizzards;
        newBlizzards = new ArrayList<>();

    }

}
