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

			boolean check = parseAndCompareAgain(data.get(i), data.get(i+1));
			order.add(check);
	
		}

	}

	private boolean parseAndCompareAgain(String firstString, String SecondString){
		
		StringBuilder first = new StringBuilder(firstString);
		StringBuilder second = new StringBuilder(SecondString);

		int min = 0;

		if (first.length() < second.length()){
			min = first.length();
		} else {
			min = second.length();
		}

		 for (int i = 0; i < min; i++){

			char checkF = first.charAt(i);
			char checkS = second.charAt(i);

			if (checkF == checkS){
				continue;
			}

			switch (checkF){

				case '[':

					if (Character.isDigit(checkS)){

						int end = i;

						while (Character.isDigit(second.charAt(end))){
							end++;
						}
						
						second.insert(end, "]");
						second.insert(i, "[");
						i--;

					} else if (checkS == ','){
						return false;
					} else if (checkS == ']'){
						return false;
					}

					break;

				case ']':

					if (Character.isDigit(checkS)){
						return true;
					} else if (checkS == ','){
						return true;
					} else if (checkS == '['){
						return true;
					}

					break;

				case ',':

					break;

				default:

					// is digit

					if (Character.isDigit(checkS)){

						int end = i;

						while (Character.isDigit(first.charAt(end))){
							end++;
						}

						int a = Integer.parseInt(first.substring(i, end));

						end = i;

						while (Character.isDigit(second.charAt(end))){
							end++;
						}

						int b = Integer.parseInt(second.substring(i, end));

						if (a < b){
							return true;
						} else {
							return false;
						}

					} else if (checkS == '['){

						int end = i;

						while (Character.isDigit(first.charAt(end))){
							end++;
						}
						
						first.insert(end, "]");
						first.insert(i, "[");
						i--;
						
						break;

					} else if (checkS == ']'){
						return false;

					} else if (checkS == ','){
						return false;

					}

					break;

			}
			
		 }

		 if (min == first.length()){
			return true; 
		 } else {
			return false;
		 }

	}

	private void part2(ArrayList<String> data){

		ArrayList<String> packets = new ArrayList<>();

		String packet1 = "[[2]]";
		String packet2 = "[[6]]";

		packets.add(packet1);
		packets.add(packet2);

		parsePart2(data, packets);

		int decoder  = findDecoder(packets, packet1, packet2);

		System.out.println("The decoder key for the packets is: " + decoder);


	}

	private void parsePart2(ArrayList<String> data, ArrayList<String> packets){

		for (String packet : data){

			if (packet.equals("")){
				continue;
			}

			boolean smaller = false;

			for (int i = 0; i < packets.size(); i++){

				String compare = packets.get(i);
				 smaller = parseAndCompareAgain(packet, compare);

				if (smaller){
					packets.add(i, packet);
					break;
				}

			}

			if (smaller == false){
				packets.add(packet);
			}
	
		}

	}

	private int findDecoder(ArrayList<String> packets, String packet1, String packet2){

		int i1 = packets.indexOf(packet1) +1;
		int i2 = packets.indexOf(packet2) +1;

		return i1*i2;

	}

}
