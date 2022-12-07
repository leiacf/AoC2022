import java.util.ArrayList;
import java.util.HashMap;

public class Day7 {

	ArrayList<String> testinput;
	ArrayList<String> input;

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

		Directory structure = getStructure(data);

		long size = structure.getSize();

		System.out.println("The total size of all directories is " + size);
	
	}

	private Directory getStructure(ArrayList<String> data) {

		Directory structure = new Directory("/");

		for (int i = 0; i < data.size(); ) {

			i = parseData(data, structure, i);
			
		}

		return structure;
	}

	private int parseData(ArrayList<String> data, Directory structure, int i) {
		
		String command = data.get(i);
		String directory = null;
		String line = null;

		i++;

		switch (command.charAt(2)){

			case 'c':

					if (command.contains("/")){
						// go to root directory

					} else if (command.contains("cd ")){
						directory = command.substring(3);
					}

				break;

			case 'l':

				line = data.get(i);

				while (i < data.size() && line.charAt(0) != '$'){
					line = data.get(i);

					i++;
				}

				break;

			default:
				// isn't a command?
				System.exit(-1);	

		}

		return i;

	}

	private void part2(ArrayList<String> data){
		
	}
}
