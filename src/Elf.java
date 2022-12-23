
public class Elf {
    
    private int x = 0;
    private int y = 0;
    String propose = "";
    boolean canMove = true;

    Elf(int x, int y){
        setLocation(x, y);
    }

    protected int getX(){
        return x;
    }

    protected int getY(){
        return y;
    }

    protected void setLocation(int x, int y){
         this.x = x;
        this.y = y;
    }

    protected String getPropose() {
        return propose;
    }

    protected void setPropose(String propose) {
        this.propose = propose;
    }

    protected boolean canMove(){
        return canMove;
    }

    protected void setMove(boolean canMove){
        this.canMove = canMove;
    }

}
