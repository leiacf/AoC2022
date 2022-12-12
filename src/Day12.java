import java.util.ArrayList;

public class Day12 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day12(ArrayList<String> testinput, ArrayList<String> input) {

		this.testinput = testinput;
		this.input = input;

	}

	public void start() {

		System.out.println("Part 1: ");
		System.out.println();

		part1(testinput);
		//part1(input);

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		part2(testinput);
		part2(input);

	}

	private void part1(ArrayList<String> data){

		int path = 0;

		path = parseData(data);

		System.out.println("The shortest path is: " + path);

	}

	private int parseData(ArrayList<String> data){

		int x = data.get(0).length();
		int y = data.size();
		int travel = Integer.MAX_VALUE;

		int[][] grid = Library.initArray(x, y);
		int[][] path = Library.initArray(x, y);

		fillGrids(grid, path, x, y, data);
		
		printGrids(grid, path, x, y);

		travel = findShortestPath(grid, path, x, y);

		return travel;

	}

	private void fillGrids(int[][] grid, int[][] path, int x, int y, ArrayList<String> data){

		for (int i = 0; i < y; i++){

			String line = data.get(i);

			for (int j = 0; j < x; j++){
				grid[i][j] = line.charAt(j);
				path[i][j] = '.';
			}

		}

	}

	private void printGrids(int[][] grid, int[][] path, int x, int y){

		System.out.println();

		for (int i = 0; i < y; i++){

			for (int j = 0; j < x; j++){
				System.out.print ((char)grid[i][j]);
			}

			System.out.println();
		}

		System.out.println();

		for (int i = 0; i < y; i++){

			for (int j = 0; j < x; j++){
				System.out.print ((char)path[i][j]);
			}

			System.out.println();
		}

		System.out.println();

	}

	private int findShortestPath(int [][] grid, int[][] path, int x, int y){

		int travel = findPathRecursive(grid, path, x, y);

		return travel;

	}

	private int findPathRecursive(int [][] grid, int[][] path, int x, int y){

		int travel = Integer.MAX_VALUE;

		if (travel > 0){
			return 0;
		}

		travel = findPathRecursive(grid, path, x, y);

		return travel;
	}

	private void part2(ArrayList<String> data){

	}

}
