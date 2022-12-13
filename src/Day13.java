import java.util.ArrayList;

public class Day13 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day13(ArrayList<String> testinput, ArrayList<String> input) {

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

		int sum = parseData(data);

		System.out.println("The sum of indicies is " + sum);

	}

	private int parseData(ArrayList<String> data){

		int sum = 0;
		ArrayList<Boolean> order = new ArrayList<>();

		checkLists(data, order);

		for (int i = 0; i < order.size(); i++){

			boolean list = order.get(i);
			
			if (list == true){
				sum += i+1;
			}

		}

		return sum;

	}

	private void checkLists(ArrayList<String> data, ArrayList<Boolean> order){

		for (int i = 0; i < data.size(); i+=3){

			boolean check = parseAndCompare(data.get(i), data.get(i+1));
			order.add(check);

		}

	}

	private boolean parseAndCompare(String firstString, String secondString){

		StringBuilder first = new StringBuilder(firstString);
		StringBuilder second = new StringBuilder(secondString);

		while (first.length() > 0 && second.length() > 0){

			if (first.charAt(0) == '[') {
				
				if (second.charAt(0) == '['){

					removeBrackets(first);
					removeBrackets(second);

				} else if (Character.isDigit(second.charAt(0))){

					addBrackets(second);

				} else if (second.charAt(0) == ','){

					return true;

				}
			
			} else if (second.charAt(0) == '['){

				if (Character.isDigit(first.charAt(0))){

					addBrackets(first);

				} else if (first.charAt(0) == ','){

					return false;

				}
				
			} else {

				// should have two digits, or two comma

				removeComma(first);
				removeComma(second);

				if (Character.isDigit(first.charAt(0)) && Character.isDigit(second.charAt(0))){

					int a = getDigit(first);
					int b = getDigit(second);

					if (a < b){
						return true;
					} else if (b < a){
						return false;
					} 

				}

			}

		}

		// got all the way here so we've been equal so far

		if (first.length() >= second.length()){
			return false;
		}

		return true;

	}

	private int getDigit(StringBuilder string) {

		int index = 0;

		while (index < string.length() && Character.isDigit(string.charAt(index))){
			index++;
		}

		int value = Integer.parseInt(string.substring(0, index));
		string.delete(0, index+1);

		return value;
	}

	private void removeComma(StringBuilder string) {

		if (string.charAt(0) == ',') { 
			string.delete(0,1); 
		}

	}

	private void addBrackets(StringBuilder string){

		string.insert(0, "[");

		int check = string.indexOf(",[");

		if (check == -1){
			string.append("]");

		} else {
			string.insert(check, "]");
		}

	}

	private void removeBrackets(StringBuilder string){

		int counter = 1;

		string = string.delete(0, 1);

		for (int i = 0; i < string.length(); i++){

			if (string.charAt(i) == '[') { counter++; }
			if (string.charAt(i) == ']') { counter--; }

			if (counter == 0){

				string.delete(i, i+1);
				break;

			}

		}

	}


	private void part2(ArrayList<String> data){

	}

}
