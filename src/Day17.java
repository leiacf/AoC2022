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

		int times = 2022;
		//int times = 10;
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

		for (int i = 0; i < tower.size(); i++){
			System.out.println(tower.get(i));
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

		while (rested == false){

			rested = moveAndFall(tower, jetstreams.charAt(index), width, shape);
			index++;

			if (index >= jetstreams.length()){
				index = 0;
			}

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

		rested = fall(tower, shape, check, width);
		
		return rested;

	}

	private boolean fall(ArrayList<String> tower, ArrayList<String> shape, String check, int width){

		int i = 0;
		String under = tower.get(i);

		while (under.contains(check) == false){
			i++;
			under = tower.get(i);
		}

		i = i + shape.size();
		under = tower.get(i);	

		if (under.contains("#") || under.contains("_")) {		

			String over = tower.get(i-1);

			if (timeToRest(under, over)){
				rest(i, shape, tower, check);
				return true;
			}

		} 

		if (under.contains("#") == false && under.contains("_") == false){
			
			tower.remove(i);

		} else {

			checkAndMoveDown(tower, shape, check, width, i);

		}

		return false;

	}

	private void rest(int i, ArrayList<String> shape, ArrayList<String> tower, String check){
		
		i = i - shape.size();

		for ( ; i < shape.size(); i++){

			StringBuilder temp = new StringBuilder(tower.get(i));

			while (temp.indexOf(check) != -1){
				int start = temp.indexOf(check);
				temp.replace(start, start+1, "#");
			}

			tower.set(i, temp.toString());

		}
	}

	private boolean timeToRest(String under, String over){

		boolean rested = false;

		for (int i = 0; i < over.length(); i++){

			if ((over.charAt(i) == '@') && ((under.charAt(i) == '#') || (under.charAt(i) == '_'))){
				
				rested = true;
				break;

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

					int add = temp.indexOf(check);
					temp.insert(add, " ");
					
					int remove = temp.lastIndexOf(check) + 1;
					temp.delete(remove, remove+1);

					tower.set(i, temp.toString());

				}
				
			}
		} 

	}

	private void checkAndMoveLeft(ArrayList<String> tower, ArrayList<String> shape, String check){

		boolean room = true;
		int i = 0;
		String bottom = tower.get(i);

		while (bottom.contains(check) == false){
			i++;
			bottom = tower.get(i);
		}

		for (int k = i; k < shape.size(); k++){

			bottom = tower.get(k);
			
			int index = bottom.indexOf(check);

			if (index != -1){
				if (index == 0){
					room = false;
				} else if (tower.get(k).charAt(index - 1) == '#'){
					room = false;
				}
			}

		}

		if (room){

			for (int j =0; j < tower.size(); j++) {
				
				String string = tower.get(j);

				if (string.contains(check) == true){

					StringBuilder temp = new StringBuilder(string);

					int add = temp.lastIndexOf(check)+1;
					temp.insert(add, " ");

					int remove = temp.indexOf(check)-1;
					temp.delete(remove, remove+1);

					tower.set(j, temp.toString());
					
				}
				
			}
			
		} 

	}

	private void checkAndMoveDown(ArrayList<String> tower, ArrayList<String> shape, String check, int width, int i){
		
		boolean room = true;

		String under = tower.get(i);
		String over = tower.get(i-1);

		for (int k = i-1; k > shape.size(); k--){

			over = tower.get(k);
			
			for (int j = 0; j < under.length(); j++){

				if ((over.charAt(j) == '@') && (under.charAt(j) == '#')){
					room = false;
				} 
			
			}

		}

		if (room){
			
			for (; i > 0; i--) {

				under = tower.get(i);
				over = tower.get(i-1);

				if (over.contains(check) == true){

					StringBuilder tempUnder = new StringBuilder(under);
					StringBuilder tempOver = new StringBuilder(over);

					for (int j = 0; j < over.length(); j++){

						if (over.charAt(j) == '@'){
							tempUnder.replace(j, j+1, check);
							tempOver.replace(j, j+1, " ");
						}

					}
										
					tower.set(i, tempUnder.toString());
					tower.set(i-1, tempOver.toString());

				}
			
			}

		}

		if ((tower.get(0).contains("_") == false) && (tower.get(0).contains("#") == false)){
			tower.remove(0);
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
