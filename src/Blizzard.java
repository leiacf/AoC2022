public class Blizzard {
    
    private char direction;

    Blizzard(char direction, int x, int y){
        this.direction = direction;
    }

    protected char getDirection(){
        return direction;
    }

    protected void setDirection(char direction){
        this.direction = direction;
    }

}
