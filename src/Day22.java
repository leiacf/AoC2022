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

		part2(testinput, 4);
		part2(input, 50);

	}

	private void part1(ArrayList<String> data){

		Node22 start = parseData(data);
		String path = data.get(data.size()-1);
		
		long sum = travel(path, start);

		System.out.println("The final password is: " + sum);

	}

	private long travel(String path, Node22 current){
	
		long sum = 0;
		long row = 0;
		long column = 0;
		int facing = 0;
		ArrayList<String> travel = splitString(path);
		
		for (String string : travel) {

			//System.out.println("Current string is: " + string);
			
			if (Character.isDigit(string.charAt(0))){

				int number = Integer.parseInt(string);

				//System.out.println("\t Moving " + number + " facing " + facing + " from " + current.getX() + "," + current.getY());

				current = move(number, facing, current);

				//System.out.println("\t Current is now: " + current.getX() + "," + current.getY());
				//System.out.println();

			} else {

				//System.out.println("\t Turning " + string + " from facing " + facing);

				if (string.equals("L")){
					facing = facing + 90;
				} else if (string.equals("R")){
					facing = facing - 90;
				}

				if (facing < 0) {
					facing = facing + 360;
				} else if (facing == 360){
					facing = 0;
				}	

				//System.out.println("\t Facing is now " + facing);
				//System.out.println();
				
			}

		}

		if (facing == 90) { facing = 3;}
		else if (facing == 180) { facing = 2; }
		else if (facing == 270) { facing = 1; }

		row = current.getY() + 1;
		column = current.getX() + 1;

		sum = (1000*row) + (4 * column) + (facing);
		
		return sum;

	}

	private Node22 move(int number, int facing, Node22 current){

		switch (facing){

			case 0:

				for (int i = 0; i < number; i++){
					if (current.getRight().getValue() != '#'){
						current = current.getRight();
					} else {
						// hit the wall
						break;
					}

				}

				break;

			case 90:

				for (int i = 0; i < number; i++){
					if (current.getUp().getValue() != '#'){
						current = current.getUp();
					} else {
						// hit the wall
						break;
					}

				}

					break;

			case 180:

				for (int i = 0; i < number; i++){
					if (current.getLeft().getValue() != '#'){
						current = current.getLeft();
					} else {
						// hit the wall
						break;
					}

				}
				
					break;

			case 270:

				for (int i = 0; i < number; i++){
					if (current.getDown().getValue() != '#'){
						current = current.getDown();
					} else {
						// hit the wall
						break;
					}

				}
				
					break;

			default:
			
				System.out.println("I am ERROR!");
				System.exit(-1);
				break;

		}

		return current;

	}

	private ArrayList<String> splitString(String path){

		ArrayList<String> travel = new ArrayList<>();

		for (int i = 0; i < path.length(); i++){

			if (Character.isDigit(path.charAt(i))){

				int start = i;

				while (i < path.length() && Character.isDigit(path.charAt(i))){
					i++;
				}

				int end = i;

				String number = path.substring(start, end);
				travel.add(number);
				
				i--;

			} else {

				String direction = path.substring(i, i+1);
				travel.add(direction);

			}

		}

		return travel;

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

					temp = new Node22(check, null, null, null, null, j, i);
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

	private void part2(ArrayList<String> data, int size){

		Node22 start = parseCubeData(data, size);
		String path = data.get(data.size()-1);
		
		long sum = travel(path, start);

		System.out.println("The final password is: " + sum);

	}

	private Node22 parseCubeData(ArrayList<String> data, int size){
		
		Node22[][] one 		= new Node22[size][size];
		Node22[][] two 		= new Node22[size][size];
		Node22[][] three 	= new Node22[size][size];
		Node22[][] four 	= new Node22[size][size];
		Node22[][] five 	= new Node22[size][size];
		Node22[][] six 		= new Node22[size][size];

		// identify sections (from the top)

		for (int i = 0; i < data.size()-2; i++){

			String line = data.get(i);
			int maxX = line.length();
			int possibleX = maxX / size;

			System.out.println("Possible sections in this part: " + possibleX);

			for (int j = 0; j < maxX; j++){

			}
			

		}
		
		// fill section maps

		// connect section maps (use cubemap)

		// move around -> pay attention to directional changes

		return one[0][0];

	}

}
