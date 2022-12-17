import java.awt.Point;

public class Sensor {

    private Point self = new Point();
    private Point beacon = new Point();
    private int manhattan = 0;

    Sensor(int x, int y, int bX, int bY){

        self.setLocation(x, y);
        beacon.setLocation(bX, bY);

        manhattan(x, y, bX, bY);

    }

    protected Point getSensor(){
        return self;
    }

    protected Point getBeacon(){
        return beacon;
    }

    protected int getManhattan(){
        return manhattan;
    }

	private void manhattan(int x, int y, int bX, int bY){

		//(p1, p2) and (q1, q2) = |p1-q1| + |p2-q2|

		manhattan = Math.abs(x-bX) + Math.abs(y-bY);

	}




    
}
