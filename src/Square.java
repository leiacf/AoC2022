public class Square {
    
    Square up = null;
    Square left = null;
    Square right = null;
    Square down = null;

    Boolean visited = false;
    int height = 0;
    char name = '\0';
    int distance = Integer.MAX_VALUE;

    Square(Square up, Square left, Square right, Square down, char name){

        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;

        this.height = name;
        this.name = name;

        if (name == 'S'){
            this.height = 'a';
        } else if (name == 'E'){
            this.height = 'z';
        }

        
    }

    protected boolean getVisited(){
        return visited;
    }

    protected void setVisited(boolean value){
        visited = value;
    }

    protected int getDistance(){
        return distance;
    }

    protected void setDistance(int distance){
        this.distance = distance;
    }

    protected char getName(){
        return name;
    }

    protected int getHeight(){
        return height;
    }

    protected Square getUp(){
        return up;        
    }

    protected void setUp(Square up){
        this.up = up;
    }

    protected Square getLeft(){
        return left;
    }

    protected void setLeft(Square left){
        this.left = left;
    }

    protected Square getDown(){
        return down;
    }

    protected void setDown(Square down){
        this.down = down;
    }

    protected Square getRight(){
        return right;
    }

    protected void setRight(Square right){
        this.right = right;
    }

}
