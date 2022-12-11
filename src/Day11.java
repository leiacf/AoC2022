import java.util.ArrayList;
import java.util.Collections;

public class Day11 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day11(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Monkey> monkeys = parseInput(data);
 		long monkeybusiness = 0;

		for (int i = 0; i < 20; i++){
			playRound(monkeys, true);
		}

		monkeybusiness = getMonkeybusiness(monkeys);

		System.out.println("The amount of monkeybusiness is " + monkeybusiness);

	}

	private long getMonkeybusiness(ArrayList<Monkey> monkeys) {

		ArrayList<Long> monkeybusiness = new ArrayList<>();

		for (Monkey monkey : monkeys) {
			monkeybusiness.add(monkey.getInspections());
		}

		Collections.sort(monkeybusiness);
		Collections.reverse(monkeybusiness);

		return monkeybusiness.get(0) * monkeybusiness.get(1);
	}

	private void playRound(ArrayList<Monkey> monkeys, boolean part1) {

		for (Monkey monkey : monkeys) {
			
			int to = 0;
			Monkey receiver = null;

			monkey.inspect(part1);

			while (monkey.hasItems()){

				long item = monkey.throwItem();
				to = monkey.test(item);
				receiver = monkeys.get(to);

				receiver.addItem(item);

			}

		}

	}

	private ArrayList<Monkey> parseInput(ArrayList<String> data) {

		ArrayList<Monkey> monkeys = new ArrayList<>();
		
		for  (int i = 0; i < data.size(); i +=7){

			String line = data.get(i);
			
			line = line.replace("Monkey ", "");
			line = line.replace(":", "");
			
			int number = Integer.parseInt(line);

			line = data.get(i+1);
			line = line.replace("  Starting items: ", "");
			line = line.replace(" ", "");

			String[] items = line.split(",");

			line = data.get(i+2);
			line = line.replace("  Operation: new = old ", "");

			String[] operations = line.split(" ");

			line = data.get(i+3);
			line = line.replace("  Test: divisible by ", "");

			String[] test = new String[3];
			test[0] = line.strip();

			line = data.get(i+4);
			line = line.replace("If true: throw to monkey ", "");

			test[1] = line.strip();

			line = data.get(i+5);
			line = line.replace("If false: throw to monkey ", "");

			test[2] = line.strip();

			Monkey temp = new Monkey(number, items, operations, test);

			monkeys.add(temp);

		}

		return monkeys;
	}

	private void part2(ArrayList<String> data){

		ArrayList<Monkey> monkeys = parseInput(data);
		long monkeybusiness = 0L;

		// PROBLEM: Item worry levels are too high, items go to the wrong monkeys :(

	   for (int i = 0; i < 10000; i++){
		   playRound(monkeys, false);
	   }

	   monkeybusiness = getMonkeybusiness(monkeys);

	   System.out.println("The amount of monkeybusiness is " + monkeybusiness);

	}

}
