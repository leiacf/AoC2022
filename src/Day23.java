import java.util.ArrayList;

public class Day23 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day23(ArrayList<String> testinput, ArrayList<String> input) {

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

		int rounds = 10;

		ArrayList<Elf> elves = new ArrayList<>();
		ArrayList<String> moves = new ArrayList<>();
			
		moves.add("N");
		moves.add("S");
		moves.add("W");
		moves.add("E");

		parseData(elves, data);

		for (int i = 0; i < rounds; i++){
			round(elves, moves);
		}

		int empty = getEmpty(elves);

		System.out.println("The number of empty slots are: " + empty);

	}

	private void parseData(ArrayList<Elf> elves, ArrayList<String> data){

		for (int i = 0; i < data.size(); i ++){
			
			String line = data.get(i);

			for (int j = 0; j < line.length(); j++){

				if (line.charAt(j) == '#'){
					Elf elf = new Elf(j, i);
					elves.add(elf);
				}

			}
		}

	}

	private boolean round(ArrayList<Elf> elves, ArrayList<String> moves){

		// first half

		for(Elf current : elves) {
 
			ArrayList<String> adjacent = checkAdjacent(current, elves);
			
			for (String move : moves){

				if (adjacent.isEmpty()){
					current.setPropose(" ");
					break;
				}

				if (adjacent.contains(move) == false){
					current.setPropose(move);
					break;
				}

			}

        }

		String move = moves.remove(0);
		moves.add(move);

		// second half

		for(Elf current : elves){

			String cProposed = current.getPropose();
			int cPX = 0;
			int cPY = 0;
			int cX = current.getX();
			int cY = current.getY();

			switch (cProposed){
				case "N":
					cPX = cX;
					cPY = cY-1;
					break;

				case "W":
					cPX = cX-1;
					cPY = cY;
					break;

				case "S":
					cPX = cX;
					cPY = cY+1;
					break;

				case "E":
					cPX = cX+1;
					cPY = cY;
					break;

				default:
					break;
			}

			for (Elf test : elves) {

				if (test == current){
					continue;
				}

				String tProposed = test.getPropose();

				int tPX = 0;
				int tPY = 0;
				int tX = test.getX();
				int tY = test.getY();

				switch (tProposed){

					case "N":
						tPX = tX;
						tPY = tY-1;
						break;
	
					case "W":
						tPX = tX-1;
						tPY = tY;
						break;
	
					case "S":
						tPX = tX;
						tPY = tY+1;
						break;
	
					case "E":
						tPX = tX+1;
						tPY = tY;
						break;
	
					default:
						break;

				}

				if (tPX == cPX && tPY == cPY){

					current.setMove(false);
					test.setMove(false);

				}

			}

        }

		if (moving(elves) == false){
			return false;
		}

		for (Elf current : elves) {

			if (current.canMove){

				String cProposed = current.getPropose();

				int cPX = 0;
				int cPY = 0;
				int cX = current.getX();
				int cY = current.getY();

				switch (cProposed){
					case "N":
						cPX = cX;
						cPY = cY-1;
						break;

					case "W":
						cPX = cX-1;
						cPY = cY;
						break;

					case "S":
						cPX = cX;
						cPY = cY+1;
						break;

					case "E":
						cPX = cX+1;
						cPY = cY;
						break;

					default:
						break;
				}

				current.setLocation(cPX, cPY);
				
			}
			
			current.setPropose(" ");
			current.setMove(true);

		}

		return true;
	}

	private boolean moving(ArrayList<Elf> elves){

		boolean moving = false;

		for(Elf current : elves){

			if (current.canMove()){
				moving = true;
				break;
			}

		}	

		return moving;

	}

	private ArrayList<String> checkAdjacent(Elf current, ArrayList<Elf> elves){

		ArrayList<String> adjacent = new ArrayList<>();

		int cX = current.getX();
		int cY = current.getY();

		ArrayList<Integer> northeast = new ArrayList<>();
		northeast.add(cY-1);
		northeast.add(cX+1);

		ArrayList<Integer> north = new ArrayList<>();
		north.add(cY-1);
		north.add(cX);

		ArrayList<Integer> northwest = new ArrayList<>();
		northwest.add(cY-1);
		northwest.add(cX-1);

		ArrayList<Integer> west = new ArrayList<>();
		west.add(cY);
		west.add(cX-1);

		ArrayList<Integer> southwest = new ArrayList<>();
		southwest.add(cY+1);
		southwest.add(cX-1);

		ArrayList<Integer> south = new ArrayList<>();
		south.add(cY+1);
		south.add(cX);

		ArrayList<Integer> southeast = new ArrayList<>();
		southeast.add(cY+1);
		southeast.add(cX+1);

		ArrayList<Integer> east = new ArrayList<>();
		east.add(cY);
		east.add(cX+1);

		for (Elf test : elves){

			if (current == test){
				continue;
			}

			int tX = test.getX();
			int tY = test.getY();

			// NE

			if (northeast.get(0) == tY && northeast.get(1) == tX){
				adjacent.add("N");
				adjacent.add("E");
			}

			// N

			if (adjacent.contains("N") == false){

				if (north.get(0) == tY && north.get(1) == tX){
					adjacent.add("N");
				}
			}

			// NW
			
			if (northwest.get(0) == tY && northwest.get(1) == tX){
			
				if (adjacent.contains("N") == false){
					adjacent.add("N");
				}	
				
				adjacent.add("W");

			}

			// W

			if (adjacent.contains("W") == false){

				if (west.get(0) == tY && west.get(1) == tX){
					adjacent.add("W");
				}
			}			

			// SW

			if (southwest.get(0) == tY && southwest.get(1) == tX){
				
				if (adjacent.contains("W") == false){
					adjacent.add("W");
				}	
				
				adjacent.add("S");

			}

			// S

			if (adjacent.contains("S") == false){
				
				if (south.get(0) == tY && south.get(1) == tX){
					adjacent.add("S");
				}
			}

			// SE

			if (southeast.get(0) == tY && southeast.get(1) == tX){
				
				if (adjacent.contains("S") == false){
					adjacent.add("S");
				}	
				
				adjacent.add("E");

			}

			// E

			if (adjacent.contains("E") == false){
				if (east.get(0) == tY && east.get(1) == tX){
					adjacent.add("E");
				}
			}
		
        }

		return adjacent;

	}

	private int getEmpty(ArrayList<Elf> elves){

		int yMIN = Integer.MAX_VALUE;
		int yMAX = Integer.MIN_VALUE;
		int xMIN = Integer.MAX_VALUE;
		int xMAX = Integer.MIN_VALUE;

		for(Elf current : elves){

			int cX = current.getX();
			int cY = current.getY();

			if (cX < xMIN ){
				xMIN = cX;
			}

			if (cX > xMAX){
				xMAX = cX;
			}

			if (cY < yMIN ){
				yMIN = cY;
			}

			if (cY > yMAX){
				yMAX = cY;
			}

		}

		int y = 1;
		int x = 1;

		for (int i = xMIN; i < xMAX; i++){
			x++;
		}

		for (int i = yMIN; i < yMAX; i++){
			y++;
		}
		
		int total = x*y;
		int result = total - elves.size();
		
		return result;
	}

	private void part2(ArrayList<String> data){

		int rounds = 0;
		boolean moving = true;
		ArrayList<String> moves = new ArrayList<>();
		ArrayList<Elf> elves = new ArrayList<>();
		
		moves.add("N");
		moves.add("S");
		moves.add("W");
		moves.add("E");
	
		parseData(elves, data);

		while (moving){

			rounds++;
			moving = round(elves, moves);
	
		}
	
		System.out.println("The first round without movement is: " + rounds);

	}
	
}
