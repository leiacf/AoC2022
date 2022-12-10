import java.util.ArrayList;
import java.util.Collections;

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

	private void part1(ArrayList<String> data){

		Directory root = new Directory("/", null);
		createStructure(data, root);
		
		long totalSize = root.getSize();
		long answerSize = findSizes(root);

		// Total Size
		System.out.println("The total size of all directories is " + totalSize);
		
		// Sum of sizes < 100 000
		System.out.println("The total size of all directories with size < 100 000 is  " + answerSize);
	
	}

	private void createStructure(ArrayList<String> data, Directory current){

		for (String line : data){

			String[] split = line.split(" ");
			String temp = split[0];
			String name = split[1];

			if (line.contains("$ cd ")){
				
				String dir = split[2];

				switch (dir){

					case "..":

						current = current.getParent();
						break;

					case "/":

						while (current.getName().equals("/") == false){
							current = current.getParent();
						}

						break;

					default:
						
						if (current.hasChild(dir) == false){
							current.addChild(dir);							
						}

						current = current.getChild(dir);
						break;

				}

			} else {

				char test = line.charAt(0);
	
				if (Character.isDigit(test)){

					int size = Integer.parseInt(temp);

					if (current.hasFile(name) == false){
						current.addFile(name, size);
					}

				} else if (test == 'd'){

					if (current.hasChild(name) == false){
						current.addChild(name);
					}

				}

			}

		}

	}

	private long findSizes(Directory current){

		long sum = 0;
		ArrayList<Directory> directories = new ArrayList<>();

		findSizeRecursive(current, directories);

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

		Directory root = new Directory("/", null);
		long total = 70000000;
		long update = 30000000;

		createStructure(data, root);
		
		long unused = total - root.getSize();
		long need = update - unused;

		long answer = findDeletionSize(need, root);

		System.out.println("The size of the directory to delete is: " + answer);

	}

	private long findDeletionSize(long need, Directory current){

		ArrayList<Long> sizes = new ArrayList<>();
		ArrayList<Directory> directories = new ArrayList<>();
		Long sum = 0L;

		findAllSizeRecursive(current, directories);

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
