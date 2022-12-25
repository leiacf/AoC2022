import java.util.ArrayList;

public class Day17 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day17(ArrayList<String> testinput, ArrayList<String> input) {

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

		//int times = 2022;
		int times = 5;
		int width = 7;
		int edge = 2;
		int index = 0;
		ArrayList<String> tower = new ArrayList<>();

		StringBuilder floor = new StringBuilder();

		for (int i = 0; i < width; i++){
			floor.append("_");
		}

		tower.add(floor.toString());

		String jetstreams = data.get(0);

		for (int i = 0; i < times; i++){
			index = playTetris(tower, width, edge, jetstreams, index, i);
		}

		 
		for (String string : tower){

			System.out.println(string);

		}
		
		System.out.println("The tower height is = " + tower.size());

	}

	private int playTetris(ArrayList<String> tower, int width, int edge, String jetstreams, int index, int times){

		ArrayList<String> shape = getShape(times, width, edge);
		StringBuilder empty = new StringBuilder();

		for (int i = 0; i < width; i++){
			empty.append(" ");
		}

		for (int i = 0; i < 3; i++){
			tower.add(0, empty.toString());
		}

		for (int i = shape.size()-1; i >= 0; i--){
			tower.add(0, shape.get(i));
		}
		
		index = fallUntilRested(tower, jetstreams, index, width, shape);

		return index;

	}

	private int fallUntilRested(ArrayList<String> tower, String jetstreams, int index, int width, ArrayList<String> shape) {

		boolean rested = false;

		if (index >= jetstreams.length()){
			index = 0;
		}

		while (rested == false){

			rested = moveAndFall(tower, jetstreams.charAt(index), width, shape);
			
			index++;

		}

		return index;

	}

	private boolean moveAndFall(ArrayList<String> tower, char jetstream, int width, ArrayList<String> shape) {

		boolean rested = false;

		String check = "@";

		switch (jetstream){

			case '<':

				checkAndMoveLeft(tower, shape, check);
				break;

			case '>':
				checkAndMoveRight(tower, shape, check, width);

				break;

			default:
			
				System.out.println("I am ERROR!");
				System.exit(-1);
	
		}

		 
		for (String string : tower){
			System.out.println(string);
		}
		
		System.out.println();
		System.out.println();


		rested = falling(tower, shape, check);
		
		return rested;

	}

	private boolean falling(ArrayList<String> tower, ArrayList<String> shape, String check){

		// start falling

		boolean rested = false;

		int i = 0;
		String line = tower.get(i);

		while (line.contains(check) == false){
			i++;
			line = tower.get(i);
		}

		i = i + shape.size();
		line = tower.get(i);

		// first check if we should rest
		// if not, then check if there is room to fall further

		// rest?

		// under == floor or under == shape

		if (line.contains("#") || line.contains("_")) {	

			String bottom = tower.get(i-1);

			for (int j = 0; j < bottom.length(); j++){

				if ((bottom.charAt(j) == '@') && ((line.charAt(j) == '#') || (line.charAt(j) == '_'))){
					
					i = i - shape.size();

					for ( ; i < shape.size(); i++){

						StringBuilder temp = new StringBuilder(tower.get(i));

						while (temp.indexOf(check) != -1){
							int start = temp.indexOf(check);
							temp.replace(start, start+1, "#");
						}

						tower.set(i, temp.toString());

					}
					
					rested = true;
					return rested;
		
				}
			}	

		} 
		
		// if we can't rest, fall further

		if (line.contains("#") == false && line.contains("_") == false){			// empty? (easy!)
			tower.remove(i);
		} else if (line.contains("#") || line.contains("_")) {					// not empty?? hard

			String bottom = tower.get(i-1);

			for (int j = 0; j < bottom.length(); j++){

				if ((bottom.charAt(j) == '@') && ((line.charAt(j) == '#') || (line.charAt(j) == '_'))){
					
				}	

			}

		}

		return rested;

	}

	private void checkAndMoveRight(ArrayList<String> tower, ArrayList<String> shape, String check, int width){
		
		boolean room = true;

		for (int i = 0; i < shape.size(); i++){

			String line = tower.get(i);

			int index = line.lastIndexOf(check);
			
			if ( index == (width-1)){
				room = false;
			} else if (line.charAt(index + 1) == '#'){
				room = false;
			} 

		}

		if (room){
			
			for (int i = 0; i < tower.size(); i++) {
				
				String string = tower.get(i);

				if (string.contains(check) == true){

					StringBuilder temp = new StringBuilder(string);

					int add = temp.indexOf(check) -1;
					temp.insert(add, " ");

					int remove = temp.lastIndexOf(check) + 1;
					temp.delete(remove, remove+1);

					tower.set(i, temp.toString());

				}
				
			}
		} else {
			System.out.println("Can't move right!");
		}
	}

	private void checkAndMoveLeft(ArrayList<String> tower, ArrayList<String> shape, String check){

		boolean room = true;

		for (int i = 0; i < shape.size(); i++){

			String line = tower.get(i);
			
			int index = line.indexOf(check);

			if (index != -1){
				if (index == 0){
					room = false;
				} else if (tower.get(i).charAt(index - 1) == '#'){
					room = false;
				}
			}

		}

		if (room){

			for (int i =0; i < tower.size(); i++) {
				
				String string = tower.get(i);

				if (string.contains(check) == true){

					StringBuilder temp = new StringBuilder(string);
					
					int add = temp.lastIndexOf(check)+1;
					temp.insert(add, " ");

					int remove = temp.indexOf(check) -1 ;
					temp.delete(remove, remove+1);
					
					tower.set(i, temp.toString());
					
				}
				
			}
			
		} else {
			System.out.println("Can't move left!");
		}

	}

	private ArrayList<String> getShape(int times, int width, int edge){

		ArrayList<ArrayList<String>> shapes = allShapes(width, edge);

		ArrayList<String> shape = shapes.get(times % shapes.size());

		int shapeWitdh = shape.get(0).length();
		int rest = width-shapeWitdh-edge;

		for (int i = 0; i < shape.size();i++){
			String modify = shape.get(i);

			StringBuilder temp = new StringBuilder();

			for (int j = 0; j < edge; j++){
				temp.append(" ");
			}

			temp.append(modify);

			for (int j = 0; j < rest; j++){
				temp.append(" ");
			}

			shape.set(i, temp.toString());
		}

		return shape;

	}

	private ArrayList<ArrayList<String>> allShapes(int width, int edge){

		ArrayList<ArrayList<String>> allShapes = new ArrayList<>();

		ArrayList<String> minus = new ArrayList<>();
		ArrayList<String> plus = new ArrayList<>();
		ArrayList<String> j = new ArrayList<>();
		ArrayList<String> longone = new ArrayList<>();
		ArrayList<String> square = new ArrayList<>();
		
		// minus

		minus.add("@@@@");

		// plus

		plus.add(" @ ");
		plus.add("@@@");
		plus.add(" @ ");

		// j

		j.add("  @");
		j.add("  @");
		j.add("@@@");

		// longone

		longone.add("@");
		longone.add("@");
		longone.add("@");
		longone.add("@");

		// square

		square.add("@@");
		square.add("@@");

		// done!
		
		allShapes.add(minus);
		allShapes.add(plus);
		allShapes.add(j);
		allShapes.add(longone);
		allShapes.add(square);

		return allShapes;

	}

	private void part2(ArrayList<String> data){

	}

}
