import java.util.ArrayList;

public class Day6 {
	
	ArrayList<String> testinput;
	ArrayList<String> input;

	Day6(ArrayList<String> testinput, ArrayList<String> input) {

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

		for (String string : data) {
			sum = findMarker(string, 4);
			System.out.println("The first marker is found at place " + sum);
		}

	}

	private void part2(ArrayList<String> data){

		int sum = 0;

		for (String string : data) {
			sum = findMarker(string, 14);
			System.out.println("The first marker is found at place " + sum);
		}

	}

	private int findMarker(String string, int size) {

		ArrayList<Character> characters = new ArrayList<>();

		int i = 0;

		for (; i < string.length(); i++){

			char temp = string.charAt(i);

			if (characters.contains(temp)){

				int remove = characters.indexOf(temp);

				for (int j = remove; j >= 0 ; j--){
					characters.remove(j);
				}

			}

			characters.add(temp);

			if (characters.size() == size){
				break;
			}

		}

		i++;

		return i;
		
	}

}
