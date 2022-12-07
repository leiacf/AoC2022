import java.util.ArrayList;

public class Day7 {

	ArrayList<String> testinput;
	ArrayList<String> input;
	Directory root = new Directory("/", null);
	int index = 0;

	Day7(ArrayList<String> testinput, ArrayList<String> input) {

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

	/* 
	 * $ command
	 * 
	 * cd x  -> in
	 * cd .. -> out
	 * cd /  -> all the way out to /.
	 * 
	 * ls -> prints current
	 * 
	 * 123 abc -> size 123
	 * dir xyz -> directory named xyz
	 * 
	 * Test: 4 dirs
	 * Total Size: 584
	*/

	private void part1(ArrayList<String> data){

		createStructure(data);
		long size = root.getSize();

		// Total Size
		System.out.println("The total size of all directories is " + size);
		
		// The 4 largest directories??
	
	}

	private void createStructure(ArrayList<String> data) {

		Directory current = root;

		while (index < data.size()) {

			current = parseData(data.get(index), current, data);
			
		}
;
	}

	private Directory parseData(String line, Directory current, ArrayList<String> data) {

		if (line.charAt(0) == '$'){
			current = parseCommand(line, current, data);
		}

		index++;
		return current;

	}


	private Directory parseCommand(String line, Directory current, ArrayList<String> data){

		String command = line.substring(2);

		switch (command){

			case "cd \\":

				current = root;
				break;

			case "cd ..":

				if (current.getParent() != null){
					current = current.getParent();
				}
				break;

			case "ls":

				// list files
				// add to current directory
				// update index

				break;

			default:

				// if we're here we're entering a directory
				// cd X

				break;

		}


		return current;

	}


	private void part2(ArrayList<String> data){
		
	}
}
