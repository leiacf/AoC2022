import java.util.ArrayList;

public class Day4 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day4(ArrayList<String> testinput, ArrayList<String> input) {

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

		int sum = 0;

		for (String input : data) {
			
			String[] temp = input.split(",");

			if (numberVersion(temp)) { sum++; }

		}

		System.out.println("The amount of sections fully contained are " + sum);

	}

	private boolean numberVersion(String[] input){

		String[] first = input[0].split("-");
		String[] second = input[1].split("-");

		int a = Integer.parseInt(first[0]);
		int b = Integer.parseInt(first[1]);
		int c = Integer.parseInt(second[0]);
		int d = Integer.parseInt(second[1]);

		if (a <= c && b >= d) { return true; }
		if (c <= a && d >= b) { return true; }

		return false;

	}

	private void part2(ArrayList<String> data){

		int sum = 0;

		for (String input : data) {
			
			String[] temp = input.split(",");

			if (overlap(temp)) { sum++; }

		}

		System.out.println("The amount of sections overlapping are " + sum);

	}

	private boolean overlap(String[] input){

		String[] first = input[0].split("-");
		String[] second = input[1].split("-");

		int a = Integer.parseInt(first[0]);
		int b = Integer.parseInt(first[1]);
		int c = Integer.parseInt(second[0]);
		int d = Integer.parseInt(second[1]);

		if (a <= c && c <= b) { return true; }
		if (a <= d && d <= b) { return true; }
		if (c <= a && a <= d) { return true; }
		if (c <= b && b <= d) { return true; }

		return false;

	}

}