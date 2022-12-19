import java.util.ArrayList;
import java.util.Collections;
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
		part1(input, 2000000);

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		part2(testinput, 20);
		part2(input, 4000000);

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
			int bY = (int) b.getY();

			int dist = Math.abs(sY - y);
			
			if (dist <= manhattan){

				int rest = manhattan-dist;

				for (int i = sX-rest; i <= sX+rest; i++){

					Point temp = new Point(i, y);
					
					if (map.containsKey(temp) == false){
						map.put(temp, "#");
					}
					
				}

				if (sY == y){
				
					if (map.containsKey(s) == false){
						map.put(s, "S");
					} else {
						map.replace(s, "S");
					}
				
				}

				if (bY == y){
				
					if (map.containsKey(b) == false){
						map.put(b, "B");
					} else {
						map.replace(b, "B");
					}
				
				}

			}

		}	
	}

	private long countBlocked(HashMap<Point, String> map){

		long sum = 0;

		for(Map.Entry<Point, String> entry : map.entrySet()) {

			String value = entry.getValue();

            if (value.equals("#")){
                sum++;
            }

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

	private void part2(ArrayList<String> data, int max){

		int min = 0;
		long mul = 4000000;
		long tuning = 0;

		ArrayList<Sensor> sensors = new ArrayList<>();
		ArrayList<int[]> ranges = new ArrayList<>();

		parseSensors(data, sensors);
		
		for (int i = min; i <= max; i++){

			ranges.clear();

			findRanges(sensors, i, ranges, min, max);
			boolean end = combineRanges(ranges, min, max);
				
			if (end){
				long hole = getHole(ranges);
				tuning = (hole*mul) + i;
				break;
			}	
		}

		System.out.println("The tuning frequency is: " + tuning);

	}

	private void findRanges(ArrayList<Sensor> sensors, int y, ArrayList<int[]> ranges, int min, int max) {

		for (Sensor sensor : sensors) {
			
			Point s = sensor.getSensor();
			int manhattan = sensor.getManhattan();

			int sX = (int) s.getX();
			int sY = (int) s.getY();

			int dist = Math.abs(sY - y);
			
			if (dist <= manhattan){

				int rest = manhattan-dist;
				int xMin = sX - rest;
				int xMax = sX + rest;

				if (xMax > min && xMin < max){

					int[] temp = new int[2];

					temp[0] = xMin;
					temp[1] = xMax;
	
					if (temp[0] < min) { temp[0] = min; }
					if (temp[1] > max) { temp[1] = max; }
	
					ranges.add(temp);

				}

			}

		}

	}

	private boolean combineRanges(ArrayList<int[]> ranges, int min, int max){

		ArrayList<Integer> removal = new ArrayList<>();

		findContained(ranges, removal);
		remove(ranges, removal);

		findOverlap(ranges, removal);
		remove(ranges, removal);

		findOverlap(ranges, removal);
		remove(ranges, removal);

		int sum = 0;

		for (int i = 0; i < ranges.size(); i++){

			int[] temp = ranges.get(i);

			sum += temp[1] - temp[0];

		}

		if (sum == max){
			return false;
		} else {
			return true;
		}

	}

	private void remove(ArrayList<int[]> ranges, ArrayList<Integer> removal){

		Collections.sort(removal);
		Collections.reverse(removal);

		for (int i = 0; i < removal.size(); i++){
			ranges.remove((int)removal.get(i));
		}

		removal.clear();

	}

	private void findOverlap(ArrayList<int[]> ranges, ArrayList<Integer> removal){

		for (int i = 0; i < ranges.size(); i++){
			for (int j = i+1; j < ranges.size(); j++){	

				int[] first = ranges.get(i);
				int[] second = ranges.get(j);

				int a = first[0];
				int b = first[1];
				int c = second[0];
				int d = second[1];

				if ((a <= c) && (b >= c) && (b <= d)){
					
					first[0] = a;
					first[1] = d;
					ranges.set(i, first);
					if (! removal.contains(j)){
						removal.add(j);	
					}
				
				} else if ((a >= c) && (a <= d) && (b >= d)){
					
					first[0] = c;
					first[1] = b;

					ranges.set(i, first);

					if (! removal.contains(j)){
						removal.add(j);	
					}
					
				}

			}
		}

	}

	private void findContained(ArrayList<int[]> ranges, ArrayList<Integer> removal){

		for (int i = 0; i < ranges.size(); i++){
			for (int j = i+1; j < ranges.size(); j++){

				int[] first = ranges.get(i);
				int[] second = ranges.get(j);

				if (first[0] >= second[0] && first[1] <= second[1]){

					if (! removal.contains(i)){
						removal.add(i);	
					}
					
				} else if (second[0] > first[0] && second[1] < first[1]){
					if (! removal.contains(j)){
						removal.add(j);	
					}
				}

			}

		}


	}

	private long getHole(ArrayList<int[]> ranges){

		long hole = 0;

		if (ranges.size() > 1){

			long a = ranges.get(0)[1];
			long b = ranges.get(1)[0];

			hole = (a+b) / 2;

		}

		return hole;

	}

}
