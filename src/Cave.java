import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Cave {
    
    private HashMap<Point, String> structure = new HashMap<>();
    private Point entry = new Point(500, 0);
 
    Cave(){

    }

    protected void addRock(int x1, int y1, int x2, int y2){

        if (y1 == y2){

            for (int i = x1; i <= x2; i++){
                Point temp = new Point(i, y1);
                structure.put(temp, "#");
            }

        } else if (x1 == x2) {

            for (int i = y1; i <= y2; i++){
                Point temp = new Point(x1, i);
                structure.put(temp, "#");
            }

        }

    }

    protected void addFloor(int floor, int x1, int x2){

        floor = floor+2;

        for (int i = x1; i <= x2; i++){
            Point temp = new Point(i, floor);
            structure.put(temp, "#");
        }

    }


    protected int sandStorm(boolean part2){

        boolean room = true;

        while (room){

            room = fall(part2);
            
        }

        return findSand();

    }

    private boolean fall(boolean part2){

        int y = (int)entry.getY();
        int x = (int)entry.getX();

        Point sand = new Point(x, y);
   
        while (true){

            if ((int)sand.getY() == 1000){
                return false;
            }

            if (structure.get(sand) == null){
                
                y++;
                sand.setLocation(x, y);

            } else {
                
                // blocked, try left

                x--;
                sand.setLocation(x, y);

                if (structure.get(sand) != null){
                
                    // blocked, try right
                    
                    x+=2;
                    sand.setLocation(x, y);

                    if (structure.get(sand) != null){

                        // blocked, add sand
                        
                        x--;
                        y--;

                        sand.setLocation(x, y);

                        if (sand.equals(entry)){
                            // cant add more sand
                            structure.put(sand, "o");
                            return false;
                        }
                        
                        structure.put(sand, "o");

                        return true;

                    }
                    
                }

            }

        }

    }

    private int findSand(){

        int sum = 0;

        for(Map.Entry<Point, String> entry : structure.entrySet()) {
            
            String value = entry.getValue();
        
            if (value.equals("o")){
                sum++;
            }
        }

        return sum;

    }

    

}
