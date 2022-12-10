import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Day9 {

	ArrayList<String> testinput;
	ArrayList<String> input;

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

		int sum = parseData(data, 2);
		System.out.println("Number of places visited at least once: " + sum);

	}

	private int parseData(ArrayList<String> data, int knots){

		ArrayList<Point> positions = new ArrayList<>();
		ArrayList<Point> points = new ArrayList<>();

		for (int i = 0; i < knots; i++){
			points.add(new Point(0, 0));
		}


		for (String line : data) {

			parseLine(line, positions, points);

		}

		return positions.size();

	}

	private void parseLine(String line, ArrayList<Point> positions, ArrayList<Point> points){

		int number = getNumber(line);

		Point head = points.get(0);
		Point tail = points.get(points.size()-1);

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

					tail.setLocation(tX, tY);
					head.setLocation(hX, hY);

					Point temp = new Point(tail.getLocation());

					if (positions.indexOf(temp) == -1){
						positions.add(temp);
					}

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

					tail.setLocation(tX, tY);
					head.setLocation(hX, hY);

					Point temp = new Point(tail.getLocation());

					if (positions.indexOf(temp) == -1){
						positions.add(temp);
					}

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

					tail.setLocation(tX, tY);
					head.setLocation(hX, hY);

					Point temp = new Point(tail.getLocation());

					if (positions.indexOf(temp) == -1){
						positions.add(temp);
					}

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

					tail.setLocation(tX, tY);
					head.setLocation(hX, hY);

					Point temp = new Point(tail.getLocation());

					if (positions.indexOf(temp) == -1){
						positions.add(temp);
					}
					
				}
			
				break;

			default:
			
				break;

		}

		points.set(0, head);
		points.set(points.size()-1, tail);

	}

	private int getNumber(String line){

		String[] split = line.split(" ");

		return Integer.parseInt(split[1]);

	}

	private void part2(ArrayList<String> data){

	}

}
