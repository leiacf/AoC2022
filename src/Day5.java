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
		ArrayList<ArrayList<String>> stacks = fillStacks(data, numberOfStacks);

		moveCrates(stacks, data, firstLine);

		String output = getfinal(stacks);
		System.out.println("The final word is: " + output);

	}

	private int getStackNumber(ArrayList<String> data) {

		for (String string : data) {
			
			if (string.startsWith(" 1")){

				return Integer.parseInt(string.substring(string.length()-2, string.length()-1));

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

	private ArrayList<ArrayList<String>> fillStacks(ArrayList<String> data, int number) {

		int i = 0;
		String temp = data.get(i);

		ArrayList<ArrayList<String>> stacks = createStacks(number);

		while (! temp.startsWith(" 1")){

			i++;
			temp = data.get(i);

		}

		for (int k = 0; k < i; k++){

			temp = data.get(k);
			int index = 0;


			for (int j = 1; j < temp.length(); j+=4){

				String substring = temp.substring(j, j+1);

				if (! substring.equals(" ")){
					ArrayList<String> list = stacks.get(index);
					list.add(substring);
					
				}

				index++;

			}

		}

		return stacks;

	}

	private int getFirstLine (ArrayList<String> data) {
		
		int i = 0;

		while (! data.get(i).isBlank()){
			i++;
		}

		i++;

		return i;

	}

	private void moveCrates(ArrayList<ArrayList<String>> stacks, ArrayList<String> data, int line) {

		for (; line < data.size(); line++){

			String temp = data.get(line);

			temp = temp.replace("move ", "");
			temp = temp.replace("from ", "");
			temp = temp.replace("to ", "");

			String[] instructions = temp.split(" ");

			int amount = Integer.parseInt(instructions[0]);
			int from = Integer.parseInt(instructions[1]);
			int to = Integer.parseInt(instructions[2]);

			ArrayList<String> fromList = stacks.get(from-1);
			ArrayList<String> toList = stacks.get(to-1);
			ArrayList<String> removeAndAdd = new ArrayList<String>();

			for (int i = 0; i < amount; i++){
				removeAndAdd.add(fromList.get(i));
			}

			for (int i = 0; i < removeAndAdd.size(); i++){
				fromList.remove(0);
			}

			for (int i = 0; i < removeAndAdd.size(); i++){
				toList.add(0, removeAndAdd.get(i));
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

	private void printStacks(ArrayList<ArrayList<String>> stacks){

		for (ArrayList<String> arrayList : stacks) {

			for (int k = 0; k < arrayList.size(); k++){
				System.out.println(arrayList.get(k));
			}
			
			System.out.println();

		}

	}

	private void part2(ArrayList<String> data){

		int numberOfStacks = getStackNumber(data);
		int firstLine = getFirstLine(data);
		ArrayList<ArrayList<String>> stacks = fillStacks(data, numberOfStacks);

		moveCratesAgain(stacks, data, firstLine);

		String output = getfinal(stacks);
		System.out.println("The final word is: " + output);

	}


	private void moveCratesAgain(ArrayList<ArrayList<String>> stacks, ArrayList<String> data, int line) {

		for (; line < data.size(); line++){

			String temp = data.get(line);

			temp = temp.replace("move ", "");
			temp = temp.replace("from ", "");
			temp = temp.replace("to ", "");

			String[] instructions = temp.split(" ");

			int amount = Integer.parseInt(instructions[0]);
			int from = Integer.parseInt(instructions[1]);
			int to = Integer.parseInt(instructions[2]);

			ArrayList<String> fromList = stacks.get(from-1);
			ArrayList<String> toList = stacks.get(to-1);
			ArrayList<String> removeAndAdd = new ArrayList<String>();

			for (int i = 0; i < amount; i++){
				removeAndAdd.add(fromList.get(i));
			}

			for (int i = 0; i < removeAndAdd.size(); i++){
				fromList.remove(0);
			}

			for (int i = removeAndAdd.size()-1; i >=0; i--){
				toList.add(0, removeAndAdd.get(i));
			}

		}	

	}

}
