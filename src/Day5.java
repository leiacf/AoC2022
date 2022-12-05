import java.util.ArrayList;

public class Day5 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day5(ArrayList<String> testinput, ArrayList<String> input) {

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

		int numberOfStacks = getStackNumber(data);
		int firstLine = getFirstLine(data);
		ArrayList<ArrayList<String>> stacks = createStacks(numberOfStacks);
		
		fillStacks(data, stacks);
		moveCrates(stacks, data, firstLine, false);

		String output = getfinal(stacks);
		System.out.println("The final word is: " + output);

	}

	private int getStackNumber(ArrayList<String> data) {


		for (String string : data) {
			
			if (string.startsWith(" 1")){

				String[] numbers = string.split(" ");
				return Integer.parseInt(numbers[numbers.length -1]);

			}

		}

		return 0;
	}

	private ArrayList<ArrayList<String>> createStacks(int number){

		ArrayList<ArrayList<String>> stacks = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < number; i++){
			
			stacks.add(new ArrayList<String>());

		}

		return stacks;
	}

	private void fillStacks(ArrayList<String> data, ArrayList<ArrayList<String>> stacks) {

		int i = 0;
		String temp = data.get(i);

		while (! temp.startsWith(" 1")){

			i++;
			temp = data.get(i);

		}

		for (int j = 0; j < i; j++){

			temp = data.get(j);
			int index = 0;

			for (int k = 1; k < temp.length(); k+=4){

				String substring = temp.substring(k, k+1);

				if (! substring.equals(" ")){
					ArrayList<String> list = stacks.get(index);
					list.add(substring);
				}

				index++;

			}

		}

	}

	private int getFirstLine (ArrayList<String> data) {
		
		int i = 0;

		while (! data.get(i).isBlank()){
			i++;
		}

		i++;

		return i;

	}

	private void moveCrates(ArrayList<ArrayList<String>> stacks, ArrayList<String> data, int line, boolean part2) {

		for (; line < data.size(); line++){

			String temp = data.get(line);

			temp = temp.replaceAll("move |from |to ", "").trim();

			String[] instructions = temp.split(" ");

			int amount = Integer.parseInt(instructions[0]);
			int from = Integer.parseInt(instructions[1]);
			int to = Integer.parseInt(instructions[2]);

			ArrayList<String> fromList = stacks.get(from-1);
			ArrayList<String> toList = stacks.get(to-1);

			if (part2 == false ){
				for (int i = 0; i < amount; i++){
					String crate = fromList.remove(0);
					toList.add(0, crate);
				}
			} else {
				for (int i = amount-1; i >= 0; i--){
					String crate = fromList.remove(i);
					toList.add(0, crate);
				}
			}

		}

	}

	private String getfinal(ArrayList<ArrayList<String>> stacks) {
		
		StringBuilder result = new StringBuilder();

		for (ArrayList<String> arrayList : stacks) {
		
			result.append(arrayList.get(0));
			
		}

		return result.toString();

	}

	private void part2(ArrayList<String> data){

		int numberOfStacks = getStackNumber(data);
		int firstLine = getFirstLine(data);
		ArrayList<ArrayList<String>> stacks = createStacks(numberOfStacks);
		
		fillStacks(data, stacks);
		moveCrates(stacks, data, firstLine, true);

		String output = getfinal(stacks);
		System.out.println("The final word is: " + output);

	}

}
