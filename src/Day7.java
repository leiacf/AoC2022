import java.util.ArrayList;

public class Day7 {

	ArrayList<String> testinput;
	ArrayList<String> input;
	Directory root = new Directory("/", null);

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

		for (int i = 0; i < data.size(); i++) {

			current = parseData(data.get(i), current);
			
		}
;
	}

	private Directory parseData(String line, Directory current) {

		switch (line.charAt(0)){

			case '$':			// command

				break;

			case 'd':			// directory

				break;

			default:			// file

				break;

		}

		return current;

	}

	private void part2(ArrayList<String> data){
		
	}
}
