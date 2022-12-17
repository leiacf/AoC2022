import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Day15 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day15(ArrayList<String> testinput, ArrayList<String> input) {

		this.testinput = testinput;
		this.input = input;

	}

	public void start() {

		System.out.println("Part 1: ");
		System.out.println();

		part1(testinput, 10);
		//part1(input, 20000);

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		part2(testinput);
		part2(input);

	}

	private void part1(ArrayList<String> data, int y){

		long sum = parseData(data, y);

		System.out.println("The sum on y=" + y + " is " + sum );

	}

	private long parseData(ArrayList<String> data, int y){

		ArrayList<Sensor> sensors = new ArrayList<>();
		HashMap<Point, String> map = new HashMap<>();

		parseSensors(data, sensors);
				
		findBlocked(sensors, y, map);
		
		return countBlocked(map);
	}

	private void findBlocked(ArrayList<Sensor> sensors, int y, HashMap<Point, String> map) {

		for (Sensor sensor : sensors) {
			
			Point s = sensor.getSensor();
			Point b = sensor.getBeacon();
			int manhattan = sensor.getManhattan();

			int sX = (int) s.getX();
			int sY = (int) s.getY();


			// get nice loop here
			

		}	
	}

	private long countBlocked(HashMap<Point, String> map){

		long sum = 0;

		for(Map.Entry<Point, String> entry : map.entrySet()) {
        
			Point key = entry.getKey();
			String value = entry.getValue();

            if (value.equals("#")){
                sum++;
            }

			System.out.println("Key: " + key.getX() + "," + key.getY() + " Value: " + value);

        }


		return sum;
	}

	private void parseSensors(ArrayList<String> data, ArrayList<Sensor> sensors){

		for (String line : data){

			String[] coords = Library.getNumbersFromString(line);

			int x = Integer.parseInt(coords[0]);
			int y = Integer.parseInt(coords[1]);
			int bX = Integer.parseInt(coords[2]);
			int bY = Integer.parseInt(coords[3]);

			sensors.add(new Sensor(x, y, bX, bY));
			
		}

	}

	private void part2(ArrayList<String> data){

	}

}
