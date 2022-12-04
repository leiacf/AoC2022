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
		//part1(input);

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

			if (unpackSections(temp)) { sum++;}

		}

		System.out.println("The amount of sections fully contained are " + sum);

	}

	private boolean unpackSections(String[] input){

		String[] first = input[0].split("-");
		String[] second = input[1].split("-");

		StringBuilder sectionOne = new StringBuilder();
		StringBuilder sectionTwo = new StringBuilder();

		for (int i = Integer.parseInt(first[0]); i <= Integer.parseInt(first[1]); i++){

			sectionOne.append(i);

		}

		for (int i = Integer.parseInt(second[0]); i <= Integer.parseInt(second[1]); i++){

			sectionTwo.append(i);

		}

		return contains(sectionOne, sectionTwo);
			
	}

	private boolean contains(StringBuilder sectionOne, StringBuilder sectionTwo){

		return sectionOne.toString().contains(sectionTwo.toString()) || sectionTwo.toString().contains(sectionOne.toString());

	}

	private void part2(ArrayList<String> data){

	}

}
