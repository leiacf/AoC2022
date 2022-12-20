import java.util.ArrayList;

public class Day18 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day18(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<int[]> cubes = new ArrayList<>();

		parseData(data, cubes);
	
		int surface = getAllSurface(cubes);

		System.out.println("The total surface area is: " + surface);

	}

	private void parseData(ArrayList<String> data, ArrayList<int[]> cubes){
		
		for (String string : data) {
			
			String[] strings = string.split(",");
			int[] temp = new int[4];

			temp[0] = Integer.parseInt(strings[0]);
			temp[1] = Integer.parseInt(strings[1]);
			temp[2] = Integer.parseInt(strings[2]);
			temp[3] = 6;

			cubes.add(temp);

		}

	}

	private int getAllSurface(ArrayList<int[]> cubes){

		for (int i = 0; i < cubes.size(); i++){

			for (int j = i; j < cubes.size(); j++){

				int[] first = cubes.get(i);
				int[] second = cubes.get(j);

				if (first[0] == second[0] && first[1] == second[1]){
					if (Math.abs(first[2]-second[2]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				if (first[0] == second[0] && first[2] == second[2]){
					if (Math.abs(first[1]-second[1]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				if (first[2] == second[2] && first[1] == second[1]){
					if (Math.abs(first[0]-second[0]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				cubes.set(i, first);
				cubes.set(j, second);

			}

		}

		int sum = 0;

		for (int[] cube : cubes){

			sum+= cube[3];

		}

		return sum;

	}

	private void part2(ArrayList<String> data){

		ArrayList<int[]> cubes = new ArrayList<>();

		parseData(data, cubes);
	
		int surface = getOuterSurface(cubes);

		System.out.println("The total surface area is: " + surface);

	}

	private int getOuterSurface(ArrayList<int[]> cubes){

		for (int i = 0; i < cubes.size(); i++){

			for (int j = i; j < cubes.size(); j++){

				int[] first = cubes.get(i);
				int[] second = cubes.get(j);

				if (first[0] == second[0] && first[1] == second[1]){
					if (Math.abs(first[2]-second[2]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				if (first[0] == second[0] && first[2] == second[2]){
					if (Math.abs(first[1]-second[1]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				if (first[2] == second[2] && first[1] == second[1]){
					if (Math.abs(first[0]-second[0]) == 1){
						first[3]--;
						second[3]--;
					}
				}

				cubes.set(i, first);
				cubes.set(j, second);

			}

		}

		int sum = 0;

		for (int[] cube : cubes){

			sum+= cube[3];

		}

		return sum;

	}

}
