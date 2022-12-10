import java.util.ArrayList;

public class Day10 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day10(ArrayList<String> testinput, ArrayList<String> input) {

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

	/*
	 * addx V takes two cycles to complete. After two cycles, the X register is increased by the value V. (V can be negative.)
	 * noop takes one cycle to complete. It has no other effect.
	 * 
	 * during the 20th cycle and every 40 cycles after that 
	 * (that is, during the 20th, 60th, 100th, 140th, 180th, and 220th cycles).
	 * 
	 * During the 20th cycle, register X has the value 21, so the signal strength is 20 * 21 = 420.
	 */

	private void part1(ArrayList<String> data){

		long sum = parseData(data);
		System.out.println("The sum of the signal strengths are " + sum);

	}

	private long parseData(ArrayList<String> data){

		int cycle = 0;
		int registry = 1;
		long sum = 0;

		for (String line : data ){

			String[] split = line.split(" ");
			String instruction = split[0];
			int number = 0;

			if (split.length > 1 ){
				number = Integer.parseInt(split[1]);
			}

			switch (instruction){

				case "noop":
					
					cycle++;
					sum += checkNumber(cycle, registry);

					break;

				case "addx":

					cycle++;
					sum += checkNumber(cycle, registry);

					cycle++;
					sum += checkNumber(cycle, registry);

					registry += number;

					break;

				default:

					break;

			}

		}
		
		return sum;

	}

	private long checkNumber(int cycle, int registry){

		long sum = 0;
		ArrayList<Integer> checks = new ArrayList<>();

		checks.add(20); checks.add(60); checks.add(100); checks.add(140); checks.add(180); checks.add(220);

		if (checks.contains(cycle)){
			sum = cycle*registry;
		}

		return sum;

	}

	private void part2(ArrayList<String> data){

		ArrayList<String[]> screen = getScreen();

		parsePart2(data, screen);

		printScreen(screen);

	}

	private void parsePart2(ArrayList<String> data, ArrayList<String[]> screen){

		int cycle = 0;
		int registry = 1;

		for (String line : data ){

			String[] split = line.split(" ");
			String instruction = split[0];
			int number = 0;

			if (split.length > 1 ){
				number = Integer.parseInt(split[1]);
			}

			switch (instruction){

				case "noop":
					
					cycle = drawPixels(cycle, registry, screen,  1);

					break;

				case "addx":

					cycle = drawPixels(cycle, registry, screen, 2);
					registry += number;

					break;

				default:

					break;

			}

		}

	}

	private int drawPixels(int cycle, int registry, ArrayList<String[]> screen, int cycles){

		int line = cycle / 40;
		int position = (registry % 40);
		String[] current = screen.get(line);

		for (int i = 0; i < cycles; i++){

			int pixel = cycle % 40;

			if (pixel == position || pixel -1 == position || pixel +1 == position) {
				current[pixel] = "#";
			}

			cycle++;

		}

		screen.set(line, current);

		return cycle;

	}

	private ArrayList<String[]> getScreen(){

		ArrayList<String[]> screen = new ArrayList<>();

		for (int i = 0; i < 6; i++){

			String[] temp = new String[40];

			for (int j = 0; j < temp.length; j++){
				temp[j] = ".";
			}

			screen.add(temp);

		}

		return screen;

	}

	private void printScreen(ArrayList<String[]> screen){

		for (int i = 0; i < screen.size(); i++){

			String[] temp = screen.get(i);

			for (int j = 0; j < temp.length; j++){

				if (temp[j] == "#"){
					System.out.print(temp[j]);
				} else {
					System.out.print(" ");
				}	
			}

			System.out.println();

		}

	}

}
