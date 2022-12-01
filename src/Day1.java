import java.util.ArrayList;
import java.util.Collections;

public class Day1 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day1(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Integer> food = new ArrayList<>();
		int sum = 0;

		for (String string : data) {
			if (string.equals("")){
				food.add(sum);
				sum = 0;

			} else {
				sum += Integer.valueOf(string);
			}
		}

		food.add(sum);

		Integer max = Collections.max(food);

		System.out.println("The elf carrying the most food is carrying " + max + " food");

	}

	private void part2(ArrayList<String> data){

		ArrayList<Integer> food = new ArrayList<>();
		int sum = 0;

		for (String string : data) {
			if (string.equals("")){
				food.add(sum);
				sum = 0;

			} else {
				sum += Integer.valueOf(string);
			}
		}

		food.add(sum);

		Collections.sort(food);
		Collections.reverse(food);

		int result = food.get(0) + food.get(1) + food.get(2);

		System.out.println("The top three elves are carryig " + result + " food");

	}

}
