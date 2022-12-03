import java.util.ArrayList;

public class Day3 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day3(ArrayList<String> testinput, ArrayList<String> input) {

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

		for (String string : data) { sum += findCommon(string); }

		System.out.println("The sum of priorities is " + sum);

	}

	private int findCommon(String rucksack){

		int split = rucksack.length() / 2;

		String first = rucksack.substring(0, split);
		String second = rucksack.substring(split);

		char common = ' ';

		for (int i = 0; i < first.length(); i++){

			if (second.indexOf(first.charAt(i)) != -1){

				common = first.charAt(i);
				break;

			}

		}

		return findValue(common);
	}

	private int findValue(char common){

		int ascii = common;

		if (Character.isUpperCase(common)){
			ascii = ascii - 65 + 27;
		} else {
			ascii = ascii - 97 + 1;
		}

		return ascii;

	}

	private void part2(ArrayList<String> data){

		int sum = 0;

		for (int i = 0; i < data.size(); i += 3){

			sum += findCommonThree(data.get(i), data.get(i+1), data.get(i+2));

		}

		System.out.println("The sum of all badges is " + sum);

	}

	private int findCommonThree(String first, String second, String third) {

		char common = ' ';

		for (int i = 0; i < first.length(); i++){

			if (second.indexOf(first.charAt(i)) != -1 && third.indexOf(first.charAt(i)) != -1 ){

				common = first.charAt(i);
				break;

			}

		}

		return findValue(common);

	}
	
}