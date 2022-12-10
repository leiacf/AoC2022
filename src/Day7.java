import java.util.ArrayList;
import java.util.Collections;

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

		root = new Directory("/", null);
		index = 0;
		
		part1(input);

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		root = new Directory("/", null);
		index = 0;

		part2(testinput);

		root = new Directory("/", null);
		index = 0;

		part2(input);

	}

	private void part1(ArrayList<String> data){

		createStructure(data);
		long totalSize = root.getSize();
		long answerSize = findSizes();

		// Total Size
		System.out.println("The total size of all directories is " + totalSize);
		
		// Sum of sizes < 100 000
		System.out.println("The total size of all directories with size < 100 000 is  " + answerSize);
	
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
	
		return current;

	}

	private Directory parseCommand(String line, Directory current, ArrayList<String> data){

		String command = line.substring(2);

		switch (command){

			case "cd \\":

				current = root;
				index++;
				break;

			case "cd ..":

				if (current.getParent() == null){
					System.out.println("I AM ERROR");
					System.exit(-1);
					
				}

				current = current.getParent();

				index++;

				break;

			case "ls":

				index++;
				String temp = data.get(index);

				while (temp.charAt(0) != '$'){

					String[] split = temp.split(" ");
					char test = temp.charAt(0);
					String name = split[1];

					if (Character.isDigit(test)){

						String size = split[0];

						if (current.hasFile(name) == false){
							current.addFile(name, Integer.parseInt(size));
						}	

					} else if (test == 'd' ){

						// got directory

						if (current.hasChild(name) == false){
							current.addChild(name);
						}

					}

					index++;

					if (index >= data.size()){
						break;
					}

					temp = data.get(index);

				}

				break;

			default:

				// if we're here we're entering a directory
				// cd X

				String[] split = command.split(" ");

				String directory = split[1];

				if (current.hasChild(directory) == false){
					current.addChild(directory);
				} 

				current = current.getChild(directory);

				index++;

				break;

		}

		return current;

	}

	private long findSizes(){

		long sum = 0;
		ArrayList<Directory> directories = new ArrayList<>();

		findSizeRecursive(root, directories);

		for (Directory directory : directories) {
			sum+= directory.getSize();
		}

		return sum;
	}

	private void findSizeRecursive(Directory current, ArrayList<Directory> directories){

		if (current.getSize() <= 100000){
			directories.add(current);
		}

		ArrayList<Directory> children = current.getChildren(); 
		
		for (Directory directory : children) {

			findSizeRecursive(directory, directories);
			
		}

	}

	private void part2(ArrayList<String> data){

		long total = 70000000;
		long update = 30000000;

		createStructure(data);
		
		long unused = total - root.getSize();
		long need = update - unused;

		long answer = findDeletionSize(need);

		System.out.println("The size of the directory to delete is: " + answer);

	}

	private long findDeletionSize(long need){

		ArrayList<Long> sizes = new ArrayList<>();
		ArrayList<Directory> directories = new ArrayList<>();
		Long sum = 0L;

		findAllSizeRecursive(root, directories);

		for (Directory directory : directories) {
			sizes.add(directory.getSize());
		}

		Collections.sort(sizes);

		for (Long size : sizes) {

			if (size > need){
				sum = size;
				break;
			}
			
		}

		return sum;

	}

	private void findAllSizeRecursive(Directory current, ArrayList<Directory> directories){

		directories.add(current);

		ArrayList<Directory> children = current.getChildren(); 
		
		for (Directory directory : children) {

			findAllSizeRecursive(directory, directories);
			
		}

	}
}
