import java.util.ArrayList;

public class Day25 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day25(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Long> numbers = new ArrayList<>();

		parseData(data, numbers);

		long sum = 0L;
		
		for (Long num : numbers) {
			sum += num;
		}
		
		String result = convertDecSNAFU(sum);

		System.out.println("The fuel input is: " + result);

	}

	private void parseData(ArrayList<String> data, ArrayList<Long> numbers){

		for (String SNAFU : data) {

			numbers.add(convertSNAFUDec(SNAFU));
			
		}

	}

	private long convertSNAFUDec(String SNAFU){

		long number = 0L;
		long result = 0L;
		long pow = 0L;

		for (int i = SNAFU.length()-1; i >= 0; i--){

			char ch = SNAFU.charAt(i);

			switch (ch){

				case '2': 
					number = 2L;
					break;

				case '1': 

					number = 1L;
					break;

				case '0': 

					number = 0L;
					break;


				case '-':
					number = -1L;
					break;

				case '=':

					number = -2L;
					break;


				default:

			}
			
			result += number*(Math.pow(5L, pow));
			pow++;

		}

		return result;

	}

	private String convertDecSNAFU(long number){

		StringBuilder SNAFU = new StringBuilder();
		ArrayList<Long> snafuList = new ArrayList<>();

		while (number > 0L){

			number = number + 2L;

			long remainder = number % 5L;
			number = number / 5L;
			snafuList.add(remainder);
	
		}

		for (int i = snafuList.size()- 1 ; i >= 0; i--) {
	
				long current = snafuList.get(i);
				String insert = "";

				switch ((int)current){
	
					case 4: 
						insert = "2";
						break;
	
					case 3: 
	
						insert = "1";
						break;
	
					case 2: 
	
						insert = "0";
						break;
	
	
					case 1:

						insert = "-";
						break;
	
					case 0:
	
						insert = "=";
						break;
	
					default:

						break;
	
				}

				SNAFU.append(insert);
		}

		return SNAFU.toString();

	}

	private void part2(ArrayList<String> data){

		System.out.println("Merry Christmas!");
		
	}

}
