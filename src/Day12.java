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
		part1(input);

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

		Square start = null;
		Square end = null;
		Square[][] squares = new Square[data.size()][data.get(0).length()];

		populate(squares, data);

		for (int i = 0; i < data.size(); i++){
			
			for (int j = 0; j < data.get(0).length(); j++){

				Square temp = squares[i][j]; 

				if (temp.getName() == 'S'){
					start = temp;
					start.setDistance(0);
				} else if (temp.getName() == 'E'){
					end = temp;
				}

			}
			
		}

		djikstra(start, end);

		return end.getDistance();

	}

	private void djikstra(Square start, Square end) {

		ArrayList<Square> toProcess = new ArrayList<>();
		ArrayList<Square> done = new ArrayList<>();

		toProcess.add(start);

		while (toProcess.isEmpty() == false){

			Square temp = getLowestDistance(toProcess);
			
			toProcess.remove(temp);
			done.add(temp);

			checkNeighbours(temp, toProcess, done);

		}
	
	}

	private Square getLowestDistance(ArrayList<Square> toProcess) {

		Square lowest = toProcess.get(0);

		for (Square square : toProcess) {
			
			if (square.getDistance() < lowest.getDistance()){
				lowest = square;
			}
		}

		return lowest;

	}

	private void checkNeighbours(Square current, ArrayList<Square> toProcess, ArrayList<Square> done) {

		Square up = current.getUp();
		Square left = current.getLeft();
		Square down = current.getDown();
		Square right = current.getRight();

		int newDistance = current.getDistance()+1;

		check(up, newDistance, toProcess, done, current);
		check(left, newDistance, toProcess, done, current);
		check(down, newDistance, toProcess, done, current);
		check(right, newDistance, toProcess, done, current);

	}

	private void check(Square current, int newDistance, ArrayList<Square> toProcess, ArrayList<Square> done, Square previous){

		if (current != null && (done.contains(current) == false) && (current.getHeight() - previous.getHeight() < 2)){

			if (current.getDistance() > newDistance){
				current.setDistance(newDistance);
				toProcess.add(current);
			}

		}

	}

	private void populate(Square[][] squares, ArrayList<String> data){

		for (int i = 0; i < data.size(); i++){

			String line = data.get(i);
			
			for (int j = 0; j < line.length(); j++){

				Square temp = new Square(null, null, null, null, line.charAt(j));

				squares[i][j] = temp; 

			}
			
		}

		for (int i = 0; i < data.size(); i++){

			for (int j = 0; j < data.get(0).length(); j++){

				Square current = squares[i][j];

				if (i != 0){
					Square up = squares[i-1][j];
					current.setUp(up);
					up.setDown(current);
				}
				
				if (j != 0){
					Square left = squares[i][j-1];
					current.setLeft(left);
					left.setRight(current);
				}

				if (i < data.size()-1){
					Square down = squares[i+1][j];
					current.setDown(down);
					down.setUp(current);
				}

				if (j < data.get(0).length()-1){
					Square right = squares[i][j+1];
					current.setRight(right);
					right.setLeft(current);
				}
				
			}

		}

	}

	private void part2(ArrayList<String> data){

		int path = 0;

		path = parseDataPart2(data);

		System.out.println("The shortest path is: " + path);

	}

	private int parseDataPart2(ArrayList<String> data){

		Square end = null;
		Square[][] squares = new Square[data.size()][data.get(0).length()];
		ArrayList<Square> startlist = new ArrayList<>();

		populate(squares, data);

		for (int i = 0; i < data.size(); i++){
			
			for (int j = 0; j < data.get(0).length(); j++){

				Square temp = squares[i][j]; 

				if (temp.getName() == 'S' || temp.getName() == 'a'){
						startlist.add(temp);
					} else if (temp.getName() == 'E'){
					end = temp;
				}

			}
			
		}

		int lowest = Integer.MAX_VALUE;

		for (Square square : startlist) {

			flush(squares);
			square.setDistance(0);
			djikstra(square, end);

			if (end.getDistance() < lowest){
				lowest = end.getDistance();
			}

		}

		return lowest;

	}

	private void flush(Square[][] squares){

		for (int i = 0; i < squares.length; i++){
			
			for (int j = 0; j < squares[0].length; j++){

				Square temp = squares[i][j];
				temp.setDistance(Integer.MAX_VALUE);

			}
			
		}

	}

}
