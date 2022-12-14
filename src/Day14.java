import java.util.ArrayList;

public class Day14 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day14(ArrayList<String> testinput, ArrayList<String> input) {

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

		int sum = parseData(data, false);

		System.out.println("The amount of sand that comes to rest is: " + sum);

	}

	private int parseData(ArrayList<String> data, boolean part2){

		Cave cave = new Cave();
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		int maxX = 0;

		for (String line : data){

			String[] times = line.split(" -> ");

			for (int i = 0; i < times.length-1; i++){

				String[] first = times[i].split(",");
				String[] second = times[i+1].split(",");

				int x1 = Integer.parseInt(first[0]);
				int y1 = Integer.parseInt(first[1]);
				int x2 = Integer.parseInt(second[0]);
				int y2 = Integer.parseInt(second[1]);

				if (x1 > x2){

					int x3 = x2;
					x2 = x1;
					x1 = x3;
	
				}

				if (y1 > y2){

					int y3 = y2;
					y2 = y1;
					y1 = y3;
	
				}

				if (y2 > maxY) { maxY = y2; }
				if (x1 < minX) { minX = x1; }
				if (x2 > maxX) { maxX = x2; }

				cave.addRock(x1, y1, x2, y2);

			}

		}

		if (part2) {cave.addFloor(maxY, minX-10, maxX+10); }

		return cave.sandStorm(part2);

	}

	private void part2(ArrayList<String> data){

		int sum = parseData(data, true);

		System.out.println("The amount of sand that comes to rest is: " + sum);

	}

}
