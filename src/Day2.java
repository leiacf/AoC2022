import java.util.ArrayList;

public class Day2 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day2(ArrayList<String> testinput, ArrayList<String> input) {

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

		int score = play(data);
		
		System.out.println("The total score is: " + score);

	}


	private int play(ArrayList<String> data){

		int sum = 0;

		for (String string : data) {

			String[] game = string.split(" ");

			sum += calculate(game[0], game[1]);

		}

		return sum;
	}

	private int calculate(String elf, String me){

		// A = Rock
		// B = Paper
		// C = Scissors

		// X = Rock
		// Y = Paper
		// Z = Scissors

		int result = 0;

		if (me.equals("X")){
			result += 1;
		} else if (me.equals("Y")){
			result += 2;
		} else {
			result += 3;
		}

		switch (elf){

			case "A":

				if (me.equals("X")){
					result += 3;
				} else if (me.equals("Y")){
					result += 6;
				} 

				break;

			case "B":

				if (me.equals("Y")){
					result += 3;
				} else if (me.equals("Z")){
					result += 6;
				}

				break;

			case "C":

				if (me.equals("Z")){
					result += 3;
				} else if (me.equals("X")){
					result += 6;
				}

				break;

			default:
			
				System.exit(-1);

		}

		return result;

	}

	private void part2(ArrayList<String> data){

		int score = playAgain(data);
		
		System.out.println("The total score is: " + score);

	}

	private int playAgain(ArrayList<String> data){

		int sum = 0;

		for (String string : data) {

			String[] game = string.split(" ");

			String need = findCorrect(game[0], game[1]);

			sum += calculate(game[0], need);

		}

		return sum;
	}

	private String findCorrect(String elf, String me) {

		String result = "win";

		switch (me){

			case "X":		// lose

				if (elf.equals("A")) {	
					result = "Z"; 
				} 
				
				else if (elf.equals("B"))	{	
					result = "X";  
				}

				else { 
					result = "Y"; 
				}

				break;

			case "Y":		// draw

			if (elf.equals("A")){

				result = "X";

			} else if (elf.equals("B")){

				result = "Y";

			} else {
				 result = "Z";
			}

				break;

			case "Z":		// win

			if (elf.equals("A")){
				result = "Y";

			} else if (elf.equals("B")){
				result = "Z";

			} else {
				result = "X";
				
			}
			
				break;

			default:

			System.exit(-1);

		}

		return result;
	}

	private int calculateAgain(String elf, String me){

		// A = Rock
		// B = Paper
		// C = Scissors

		// X = Rock
		// Y = Paper
		// Z = Scissors

		int result = 0;

		if (me.equals("X")){
			result += 1;
		} else if (me.equals("Y")){
			result += 2;
		} else {
			result += 3;
		}

		switch (elf){

			case "A":

				if (me.equals("X")){
					result += 3;
				} else if (me.equals("Y")){
					result += 6;
				} 

				break;

			case "B":

				if (me.equals("Y")){
					result += 3;
				} else if (me.equals("Z")){
					result += 6;
				}

				break;

			case "C":

				if (me.equals("Z")){
					result += 3;
				} else if (me.equals("X")){
					result += 6;
				}

				break;

			default:
			
				System.exit(-1);

		}

		return result;

	}

}
