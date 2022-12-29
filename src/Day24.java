import java.util.ArrayList;

public class Day24 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day24(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Node24[]> map = new ArrayList<>();
		ArrayList<Node24> next = new ArrayList<>();
		boolean end = false;
		int i = 0;

		createMap(map, data);
		printMap(map);

		Node24 start = map.get(0)[1];
		Node24[] line = map.get(map.size()-1);
		Node24 exit = line[line.length-2];
		
		next.add(start);

		while (end == false){

			end = runDungeon(map, next, exit);
			i++;

			//printMap(map);

		}

		next.removeAll(next);
		next.add(exit);
		end = false;

		while (end == false){

			end = runDungeon(map, next, start);
			i++;

			//printMap(map);

		}

		next.removeAll(next);
		next.add(start);
		end = false;

		while (end == false){

			end = runDungeon(map, next, exit);
			i++;

			//printMap(map);

		}

		System.out.println("Found the exit after: " + i + " minutes");

	}

	private boolean runDungeon(ArrayList<Node24[]> map, ArrayList<Node24> next, Node24 exit){

		moveBlizzards(map);

		moveHuman(next);
		
		if (next.contains(exit)){
			return true;
		}

		return false;
		
	}

	private void moveHuman(ArrayList<Node24> current){

		ArrayList<Node24> next = new ArrayList<>();

		for (Node24 node : current){

			Node24 temp = node.getUp();
			if (valid(temp)){
				if (next.contains(temp) == false){
					next.add(temp);	
				}	
			}

			temp = node.getRight();
			if (valid(temp)){
				if (next.contains(temp) == false){
					next.add(temp);	
				}	
			}

			temp = node.getDown();
			if (valid(temp)){
				if (next.contains(temp) == false){
					next.add(temp);	
				}	
			}

			temp = node.getLeft();
			if (valid(temp)){
				if (next.contains(temp) == false){
					next.add(temp);	
				}	
			}

			if (valid(node)){
				if (next.contains(node) == false){
					next.add(node);	
				}
			}

		}

		current.removeAll(current);
		current.addAll(next);

	}

	private boolean valid(Node24 current){

		if (current.hasBlizzard() || current.isWall()){
			return false;
		}

		return true;

	}

	private void moveBlizzards(ArrayList<Node24[]> map){

		for (int i = 0; i < map.size(); i++){
			
			for (int j = 0; j < map.get(i).length; j++){

				Node24 temp = map.get(i)[j];

				while (temp.hasBlizzard()){

					Blizzard blizzard = temp.getBlizzard();
					char direction = blizzard.getDirection();

					Node24 destination = null;

					switch (direction){

						case '^':

							destination = temp.getUp();

							while (destination.isWall()){
								destination = destination.getUp();
							} 

							break;

						case '>':

							destination = temp.getRight();

							while (destination.isWall()){
								destination = destination.getRight();
							} 

							break;

						case '<':

							destination = temp.getLeft();

							while (destination.isWall()) {
								destination = destination.getLeft();
							} 

							break;

						case 'v':

							destination = temp.getDown();

							while (destination.isWall()) {
								destination = destination.getDown();
							}
							
							break;

						default:

							break;

					}

					destination.addNewBlizzard(blizzard);

				}

			}
		}

		// change blizzardlists

		for (int i = 0; i < map.size(); i++){

			Node24[] temp = map.get(i);

			for (int j = 0; j < temp.length; j++){

				temp[j].changeBlizzardList();

			}

		}

	}

	private void createMap(ArrayList<Node24[]> map, ArrayList<String> data){

		for (int j = 0; j < data.size(); j++){

			String line = data.get(j);

			Node24[] temp = new Node24[line.length()];

			for (int i = 0; i < line.length(); i++){

				char value = line.charAt(i);
				Blizzard blizzard = null;

				temp[i] = new Node24(value, null, null, null, null, i, j);

				if ((value == '<') || (value == '>') || (value == '^') || (value == 'v')){
					blizzard = new Blizzard(value, i, j);
					temp[i].addBlizzard(blizzard);
				}

			}

			map.add(temp);

		}

		for (int i = 0; i < map.size()-1; i++){

			Node24[] up = map.get(i);
			Node24[] down = map.get(i+1);

			for (int j = 0; j < up.length; j++){
				
				Node24 above = up[j];
				Node24 below = down[j];

				above.setDown(below);
				below.setUp(above);

			}

		}

		Node24[] up = map.get(0);
		Node24[] down = map.get(map.size()-1);

		for (int j = 0; j < up.length; j++){
			
			Node24 above = up[j];
			Node24 below = down[j];

			above.setUp(below);
			below.setDown(above);

		}

		for (int i = 0; i < map.size(); i++){

			Node24[] line = map.get(i);
			
			for (int j = 0; j < line.length-1; j++){
				
				Node24 left = line[j];
				Node24 right = line[j+1];

				left.setRight(right);
				right.setLeft(left);

			}

		}

		for (int i = 0; i < map.size(); i++){
			
			Node24[] line = map.get(i);

			Node24 left = line[0];
			Node24 right = line[line.length-1];

			left.setLeft(right);
			right.setRight(left);

		}

	}

	private void printMap(ArrayList<Node24[]> map){

		System.out.println();

		for (int j = 0; j < map.size(); j++){

			Node24[] temp = map.get(j);

			for (int i = 0; i < temp.length; i++){

				Node24 current = temp[i];

				if (current.hasBlizzard()){

					ArrayList<Blizzard> blizzards = current.blizzards();

					if (blizzards.size() == 1){
						Blizzard blizzard = blizzards.get(0);
						System.out.print(blizzard.getDirection());
					} else{
						System.out.print(blizzards.size());
					}

				} else {

					System.out.print(current.getValue());

				}

			}

			System.out.println();

		}

		System.out.println();

	}

	private void part2(ArrayList<String> data){

	}

}
