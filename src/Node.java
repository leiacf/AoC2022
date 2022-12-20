public class Node {
    
    long value;
    Node next;
    Node previous;
    boolean moved = false;

    Node(long value, Node previous, Node next){

        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    protected Node getNext(){
        return next;
    }

    protected void setNext(Node next){
        this.next = next;
    }

    protected Node getPrevious(){
        return previous;
    }

    protected void setPrevious(Node previous){
        this.previous = previous;
    }

    protected long getValue(){
        return value;
    }

    protected void setMoved(){
        moved = true;
    }

    protected boolean hasMoved(){
        return moved;
    }

}
