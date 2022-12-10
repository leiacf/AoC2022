import java.util.ArrayList;
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

		for (int i = 0; i < number; i++){
			switch (line.charAt(0)){

				case 'U':

					head.setLocation(head.getX(), head.getY() + 1);
					break;

				case 'L':

					head.setLocation(head.getX() - 1, head.getY());
					break;

				case 'D':

					head.setLocation(head.getX(), head.getY() - 1);
					break;

				case 'R':

					head.setLocation(head.getX() + 1, head.getY());
					break;

				default:
				
					break;

			}

			updatePositions(positions, points);

		}	
	}

	private void updatePositions(ArrayList<Point> positions, ArrayList<Point> points){

		Point first = null;
		Point second = null;

		for (int i = 0; i < points.size() -1 ; i++){

			first = points.get(i);
			second = points.get(i+1);

			int fY = (int) first.getY();
			int fX = (int) first.getX();

			int sY = (int) second.getY();
			int sX = (int) second.getX();

			int diffY = fY-sY;
			int diffX = fX-sX;

			if (Math.abs(diffY) >= 2){
				
				sY += diffY > 0 ? 1 : -1;

				if (fX < sX){
					sX--;
				} else if (fX > sX){
					sX++;
				}
					
			} else if (Math.abs(diffX) >= 2){

				sX += diffX > 0 ? 1 : -1;

				if (fY < sY){
					sY--;
				} else if (fY > sY){
					sY++;
				}

			}

			second.setLocation(sX, sY);

			if (points.get(points.size()-1) == second){

				Point temp = new Point(second.getLocation());

				if (positions.indexOf(temp) == -1){
					positions.add(temp);
				}

			}

		}

	}

	private int getNumber(String line){

		String[] split = line.split(" ");

		return Integer.parseInt(split[1]);

	}

	private void part2(ArrayList<String> data){

		int sum = parseData(data, 10);
		System.out.println("Number of places visited at least once: " + sum);

	}

}
