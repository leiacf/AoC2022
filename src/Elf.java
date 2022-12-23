
public class Elf {
    
    private int x = 0;
    private int y = 0;
    private int px = Integer.MAX_VALUE;
    private int py = Integer.MAX_VALUE;
    String proposed = " ";
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

    protected int getPX(){
        return px;
    }

    protected int getPY(){
        return py;
    }

    protected void setProposedLocation(int px, int py){
        this.px = px;
        this.py = py;
    }

    protected String getProposed() {
        return proposed;
    }

    protected void setProposed(String proposed) {
        this.proposed = proposed;
    }

    protected boolean canMove(){
        return canMove;
    }

    protected void setMove(boolean canMove){
        this.canMove = canMove;
    }

    protected void move(){

        setLocation(px, py);
        setProposedLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setProposed(" ");

    }

}