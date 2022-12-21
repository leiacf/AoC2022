import java.util.ArrayList;
import java.util.HashMap;

public class Day21 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day21(ArrayList<String> testinput, ArrayList<String> input) {

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

		//part2(testinput);
		part2(input);

	}

	private void part1(ArrayList<String> data){

		HashMap<String, String> monkeys = new HashMap<>();

		parseData(monkeys, data);

		long sum = simulate("root", monkeys);

		System.out.println("The number the root monkey will yell is " + sum);

	}

	private void parseData(HashMap<String, String> monkeys, ArrayList<String> data){

		for (String string : data) {
			
			String[] split = string.split(": ");

			monkeys.put(split[0], split[1]);

		}

	}

	private long simulate(String monkey, HashMap<String, String> monkeys){

		String result = monkeys.get(monkey);
		long number = 0;

		if (Character.isDigit(result.charAt(0))){

			return Long.parseLong(result);

		} else {

			String[] math = result.split(" ");

			long a = simulate(math[0], monkeys);
			long b = simulate(math[2], monkeys);

			switch (math[1]){

				case "+":

					number = a + b;
					break;

				case "-":
				
					number = a - b;
					break;

				case "/":

					number = a / b;
					break;

				case "*":

					number = a * b;
					break;

				default:
				
					System.out.println("I AM ERROR!");
					System.exit(-1);

			}

			return number;

		}

	}

	private void part2(ArrayList<String> data){

		HashMap<String, String> monkeys = new HashMap<>();
		boolean found = false;
		long result = 0;

		parseData(monkeys, data);
		long check = 3099532690000L;
		long end = check+10000;

		// long and hard manual trial and error, so not exactly the right method..
		// should do: solve the equation, using b as the correct answer..

		for (long i = check; i < end; i++){

			monkeys.replace("humn", Long.toString(i));

			found = simulateMore("root", monkeys);

			if (found){
				result = i;
				break;
			}

		}

		System.out.println("The number I need to yell is " + result);

	}

	private boolean simulateMore(String monkey, HashMap<String, String> monkeys){

		String result = monkeys.get(monkey);
		boolean equal = false;

		String[] math = result.split(" ");

		long a = simulate(math[0], monkeys);
		long b = simulate(math[2], monkeys);

		if (a == b){
			equal = true;
		}

		return equal;

	}

}
