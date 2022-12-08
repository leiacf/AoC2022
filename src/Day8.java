import java.util.ArrayList;
import java.util.Collections;

public class Day8 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day8(ArrayList<String> testinput, ArrayList<String> input) {

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

		int sum = 0;
		int[][] grid = parseData(data);

		//print(grid);

		sum = countHiddenTrees(grid);

		System.out.println("The sum of hidden trees are: " + sum);

	}

	private int[][] parseData(ArrayList<String> data){

		int y = data.size();
		int x = data.get(0).length();

		int[][] grid = new int[y][x];

		for (int i = 0;  i < data.size(); i++) {
			
			String line = data.get(i);

			for (int j = 0; j < line.length(); j++){

				grid[i][j] = Character.getNumericValue(line.charAt(j));

			}

		}

		return grid;

	}

	private int countHiddenTrees(int[][] grid){

		int sum = grid.length*grid[0].length;

		for (int i = 1; i < grid.length-1; i++){

			for (int j = 1; j < grid[0].length-1; j++){

				boolean[] hidden = new boolean[4];

				int check = grid[i][j];

				// up

				for (int k = i-1; k >= 0; k--){

					if (grid[k][j] >= check){
						hidden[0] = true;
						break;
					}

				}

				// left

				for (int k = j-1; k >= 0; k--){

					if (grid[i][k] >= check){
						hidden[1] = true;
						break;
					}

				}

				// right

				for (int k = j+1; k < grid[0].length; k++){

					if (grid[i][k] >= check){
						hidden[2] = true;
						break;
					}

				}

				// down

				for (int k = i+1; k < grid.length; k++){

					if (grid[k][j] >= check){
						hidden[3] = true;
						break;
					}

				}

				if ( (hidden[0] && hidden[1] && hidden[2] && hidden[3])){

					sum--;
	
				}

			}
		
		}

		return sum;

	}

	private void part2(ArrayList<String> data){

		int sum = 0;
		int[][] grid = parseData(data);

		sum = calculateScenicScore(grid);

		System.out.println("The scenic score is: " + sum);

	}	

	private int calculateScenicScore(int[][] grid){

		ArrayList<Integer> scores = new ArrayList<>();

		for (int i = 1; i < grid.length-1; i++){

			for (int j = 1; j < grid[0].length-1; j++){

				int check = grid[i][j];

				int up = 0;
				int down = 0;
				int left = 0;
				int right = 0;

				// up

				for (int k = i-1; k >= 0; k--){

					up++;

					if (grid[k][j] >= check){
						break;
					}

				}

				// left

				for (int k = j-1; k >= 0; k--){

					left++;

					if (grid[i][k] >= check){
						break;
					}

				}

				// right

				for (int k = j+1; k < grid[0].length; k++){

					right++;

					if (grid[i][k] >= check){
						break;
					}

				}

				// down

				for (int k = i+1; k < grid.length; k++){

					down++;

					if (grid[k][j] >= check){
						break;
					}

				}

				scores.add(up*left*right*down);

			}
		
		}

		return Collections.max(scores);

	}

}