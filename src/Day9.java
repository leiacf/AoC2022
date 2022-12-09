import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Day9 {

	ArrayList<String> testinput;
	ArrayList<String> input;
	Point head = new Point(0, 0);
	Point tail = new Point(0,0);

	Day9(ArrayList<String> testinput, ArrayList<String> input) {

		this.testinput = testinput;
		this.input = input;

	}

	public void start() {

		System.out.println("Part 1: ");
		System.out.println();

		part1(testinput);
		part1(input);

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		part2(testinput);
		part2(input);

	}

	private void part1(ArrayList<String> data){

		int sum = parseData(data);

		System.out.println("Number of places visited at least once: " + sum);

	}

	private int parseData(ArrayList<String> data){

		HashMap<Point, Boolean> headMap = new HashMap<>();
		HashMap<Point, Boolean> tailMap = new HashMap<>();

		headMap.put(head, true);
		tailMap.put(tail, true);

		for (String line : data) {

			parseLine(line, headMap, tailMap);

		}

		return tailMap.size();

	}

	private void parseLine(String line, HashMap<Point, Boolean> headMap, HashMap<Point, Boolean> tailMap){

		int number = getNumber(line);

		int hX = (int)head.getX();
		int hY = (int)head.getY();
		int tX = (int)tail.getX();
		int tY = (int)tail.getY();

		switch (line.charAt(0)){

			case 'U':

				for (int i = 0; i < number; i++){

					hY++;

					if (hY - tY >= 2){

						tY++;

						if (hX < tX){
							tX--;
						} else if (hX > tX){
							tX++;
						}

					}

					tail = new Point(tX, tY);
					head = new Point(hX, hY);
			
					headMap.put(head, true);
					tailMap.put(tail, true);

				}

				break;

			case 'L':

				for (int i = 0; i < number; i++){

					hX--;

					if (tX - hX >= 2){

						tX--;

						if (hY < tY){
							tY--;
						} else if (hY > tY){
							tY++;
						}

					}

					tail = new Point(tX, tY);
					head = new Point(hX, hY);
			
					headMap.put(head, true);
					tailMap.put(tail, true);

				}
			
				break;

			case 'D':

				for (int i = 0; i < number; i++){

					hY--;

					if (tY - hY >= 2){

						tY--;

						if (hX < tX){
							tX--;
						} else if (hX > tX){
							tX++;
						}

					}

					tail = new Point(tX, tY);
					head = new Point(hX, hY);
			
					headMap.put(head, true);
					tailMap.put(tail, true);

				}
			
				break;

			case 'R':

				for (int i = 0; i < number; i++){

					hX++;

					if (hX - tX >= 2){

						tX++;

						if (hY < tY){
							tY--;
						} else if (hY > tY){
							tY++;
						}

					}

					tail = new Point(tX, tY);
					head = new Point(hX, hY);
			
					headMap.put(head, true);
					tailMap.put(tail, true);

				}
			
				break;

			default:
			
				break;

		}

	}

	private int getNumber(String line){

		String[] split = line.split(" ");

		return Integer.parseInt(split[1]);

	}

	private void part2(ArrayList<String> data){

	}

}
