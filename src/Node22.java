public class Node22 {
    
    char value;
    Node22 up;
    Node22 left;
    Node22 down;
    Node22 right;
    int x = 0;
    int y = 0;

    Node22(char value, Node22 up, Node22 left, Node22 down, Node22 right, int x, int y){

        this.value = value;
        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;
        this.x = x;
        this.y = y;
    }

    protected int getX(){
        return x;
    }

    protected int getY(){
        return y;
    }

    protected Node22 getUp(){
        return up;
    }

    protected void setUp(Node22 up){
        this.up = up;
    }

    protected Node22 getLeft(){
        return left;
    }

    protected void setLeft(Node22 left){
        this.left = left;
    }

    protected Node22 getDown(){
        return down;
    }

    protected void setDown(Node22 down){
        this.down = down;
    }

    protected Node22 getRight(){
        return right;
    }

    protected void setRight(Node22 right){
        this.right = right;
    }

    protected char getValue(){
        return value;
    }

    protected boolean isWall(){
        return value == '#';
    }

}
