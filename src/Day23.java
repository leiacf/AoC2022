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

			if (adjacent.isEmpty()){
	
				current.setProposed(" ");
				current.setProposedLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
				current.setMove(false);
	
			} else if (adjacent.size() == 4){

				current.setProposed(" ");
				current.setProposedLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
				current.setMove(false);
			
			} else {
			
				for (String move : moves){

					if (adjacent.contains(move) == false){
						propose(current, move);
						break;
					}

				}

			}

        }

		String move = moves.remove(0);
		moves.add(move);

		// second half

		for(Elf current : elves){

			int cX = current.getPX();
			int cY = current.getPY();

			for (Elf test : elves) {

				if (test == current){
					continue;
				}

				if ((cX == test.getPX()) && (cY == test.getPY())){

					current.setMove(false);
					test.setMove(false);

				}

			}

		}

		if (moving(elves) == false){
			return false;
		}

		for (Elf current : elves) {

			if (current.canMove()){
				current.move();
			}

			current.setMove(true);

		}

		return true;
	}

	private void propose(Elf current, String move){

		int cPX = 0;
		int cPY = 0;
		int cX = current.getX();
		int cY = current.getY();

		switch (move){
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

		current.setProposed(move);
		current.setProposedLocation(cPX, cPY);
		current.setMove(true);

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

		for (Elf test : elves){

			if (current == test){
				continue;
			}

			int tX = test.getX();
			int tY = test.getY();

			// NW N NE
			if (tY == (cY-1) ){

				if ( tX == (cX-1) ){

					if (adjacent.contains("W") == false){
						adjacent.add("W");
					}

					if (adjacent.contains("N") == false){
						adjacent.add("N");
					}
										
				}
				
				if ( tX == (cX+1) ){

					if (adjacent.contains("E") == false){
						adjacent.add("E");
					}

					if (adjacent.contains("N") == false){
						adjacent.add("N");
					} 
				}
				
				if (tX == cX){

					if (adjacent.contains("N") == false){
						adjacent.add("N");
					} 

				}

			}

			// SW S SE
			if ( tY == (cY+1) ){

				if ( tX == (cX-1) ){

					if (adjacent.contains("W") == false){
						adjacent.add("W");
					}

					if (adjacent.contains("S") == false){
						adjacent.add("S");
					}
										
				}
				
				if ( tX == (cX+1) ){

					if (adjacent.contains("E") == false){
						adjacent.add("E");
					}

					if (adjacent.contains("S") == false){
						adjacent.add("S");
					} 

				}
				
				if (tX == cX){

					if (adjacent.contains("S") == false){
						adjacent.add("S");
					} 

				}	

			}

			// W E 
			if ( tY == cY ){

				if ( tX == (cX-1) ){
					if (adjacent.contains("W") == false){
						adjacent.add("W");
					} 
				}
				
				if ( tX == (cX+1) ){
					if (adjacent.contains("E") == false){
						adjacent.add("E");
					}
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
