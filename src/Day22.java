import java.util.ArrayList;

public class Day22 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day22(ArrayList<String> testinput, ArrayList<String> input) {

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

		Node22 start = parseData(data);
		String path = data.get(data.size()-1);
		
		long sum = travel(path, start);

		System.out.println("The final password is: " + sum);

	}

	private long travel(String path, Node22 start){
	
		long sum = 0;
		long row = 0;
		long column = 0;
		long facing = 0;

		System.out.println(path);
		System.out.println(start.getValue());
		System.out.println(start.getRight().getValue());
		System.out.println(start.getLeft().getValue());

		sum = (1000*row) + (4 * column) + (facing);

		return sum;

	}

	private Node22 parseData(ArrayList<String> data){

		Node22 start = null;
		int maxY = data.size()-2;
		int maxX = 0;

		for (String string : data) {
			if (string.length() > maxX) { maxX = string.length();}
		}
		
		Node22[][] map = new Node22[maxY][maxX];
	
		// populate map
		for (int i = 0; i < maxY; i++){

			String line = data.get(i);
			Node22 temp = null;
						
			for (int j = 0; j < line.length(); j++){

				char check = line.charAt(j);

				if (check == '.' || check == '#'){

					temp = new Node22(check, null, null, null, null);
					map[i][j] = temp;

				}

			}

		}

		// find right left
		for (int i = 0; i < maxY; i++){

			for (int j = 0; j < maxX-1; j++){
		
				if (map[i][j] != null && map[i][j+1] != null){

					Node22 left = map[i][j];
					Node22 right = map[i][j+1];

					left.setRight(right);
					right.setLeft(left);

				}

			}

		}

		// find up down
		for (int i = 0; i < maxY-1; i++){
			for (int j = 0; j < maxX; j++ ){

				if (map[i][j] != null && map[i+1][j] != null){

					Node22 up = map[i][j];
					Node22 down = map[i+1][j];

					up.setDown(down);
					down.setUp(up);

				}

			}
		}

		//find first last
		for (int i = 0; i < maxY; i++){

			Node22 first = null;
			Node22 last = null;

			for (int j = 0; j < maxX; j++){

				if (map[i][j] != null){
					first = map[i][j];
					break;
				}

			}

			for (int j = maxX-1; j >= 0; j--){

				if (map[i][j] != null){
					last = map[i][j];
					break;
				}
			}

			if ((first != null) && (last != null)){
				first.setLeft(last);
				last.setRight(first);
			}

		}

		//find high low
		for (int j = 0; j < maxX; j++){

			Node22 high = null;
			Node22 low = null;

			for (int i = 0; i < maxY; i++){

				if (map[i][j] != null){
					high = map[i][j];
					break;
				}

			}

			for (int i = maxY-1; i >= 0; i--){

				if (map[i][j] != null){
					low = map[i][j];
					break;
				}

			}

			if (high != null && low != null){
				high.setUp(low);
				low.setDown(high);
			}

		}

		//find start
		for (int i = 0; i < maxX; i++){

			if (map[0][i] != null){

				start = map[0][i];

				if (start.isWall() == false){
					break;
				}

			}
		}

		return start;
	}

	private void part2(ArrayList<String> data){

	}

}
