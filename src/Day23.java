import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		//part1(input); 4331 too high

		System.out.println();
		System.out.println("Part 2: ");
		System.out.println();

		part2(testinput);
		part2(input);

	}

	private void part1(ArrayList<String> data){

		int rounds = 5;
		HashMap<Elf, String> map = new HashMap<>();
		ArrayList<String> moves = new ArrayList<>();
			
		moves.add("N");
		moves.add("S");
		moves.add("W");
		moves.add("E");

		parseData(map, data);

		System.out.println("== Initial State ==");

		printMap(map, -2, 10, -3, 11);

		for (int i = 0; i < rounds; i++){
			round(map, moves);
			System.out.println("== End of Round " + (i+1) + " ==");
			printMap(map, -2, 10, -3, 11);

		}

		int empty = getEmpty(map);

		System.out.println("The number of empty slots are: " + empty);

	}

	private void parseData(HashMap<Elf, String> map, ArrayList<String> data){

		for (int i = 0; i < data.size(); i ++){
			
			String line = data.get(i);

			for (int j = 0; j < line.length(); j++){

				if (line.charAt(j) == '#'){
					Elf elf = new Elf(j, i);
					map.put(elf, "#");
				}

			}
		}

	}

	private void round(HashMap<Elf, String> map, ArrayList<String> moves){

		// first half

		for(Map.Entry<Elf, String> elf : map.entrySet()) {
            
            Elf current = elf.getKey();

			ArrayList<String> adjacent = checkAdjacent(current, map);
			
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

			System.out.println("Elf at " + current.getY() + "," + current.getX() + " Proposed to move: " + current.getPropose());

        }

		String move = moves.remove(0);
		moves.add(move);

		// second half

		for(Map.Entry<Elf, String> elf : map.entrySet()) {
            
            Elf current = elf.getKey();
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
					cPX = cX + 1;
					cPY = cY;
					break;

				case "S":
					cPX = cX;
					cPY = cY+1;
					break;

				case "E":
					cPX = cX - 1;
					cPY = cY;
					break;

				default:
					break;
			}

			for(Map.Entry<Elf, String> other : map.entrySet()) {
            
				Elf test = other.getKey();

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
						tPX = tX + 1;
						tPY = tY;
						break;
	
					case "S":
						tPX = tX;
						tPY = tY+1;
						break;
	
					case "E":
						tPX = tX - 1;
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

		for(Map.Entry<Elf, String> elf : map.entrySet()) {
            
			Elf current = elf.getKey();

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
						cPX = cX + 1;
						cPY = cY;
						break;

					case "S":
						cPX = cX;
						cPY = cY+1;
						break;

					case "E":
						cPX = cX - 1;
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

	}

	private ArrayList<String> checkAdjacent(Elf current, HashMap<Elf, String> map){

		ArrayList<String> adjacent = new ArrayList<>();

		int cX = current.getX();
		int cY = current.getY();

		ArrayList<Integer> northeast = new ArrayList<>();
		northeast.add(cY-1);
		northeast.add(cX-1);

		ArrayList<Integer> north = new ArrayList<>();
		north.add(cY-1);
		north.add(cX);

		ArrayList<Integer> northwest = new ArrayList<>();
		northwest.add(cY-1);
		northwest.add(cX+1);

		ArrayList<Integer> west = new ArrayList<>();
		west.add(cY);
		west.add(cX+1);

		ArrayList<Integer> southwest = new ArrayList<>();
		southwest.add(cY+1);
		southwest.add(cX+1);

		ArrayList<Integer> south = new ArrayList<>();
		south.add(cY+1);
		south.add(cX);

		ArrayList<Integer> southeast = new ArrayList<>();
		southeast.add(cY+1);
		southeast.add(cX-1);

		ArrayList<Integer> east = new ArrayList<>();
		east.add(cY);
		east.add(cX-1);

		for(Map.Entry<Elf, String> elf : map.entrySet()) {
            
            Elf test = elf.getKey();

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

	private int getEmpty(HashMap<Elf, String> map){

		int yMIN = Integer.MAX_VALUE;
		int yMAX = Integer.MIN_VALUE;
		int xMIN = Integer.MAX_VALUE;
		int xMAX = Integer.MIN_VALUE;

		for(Map.Entry<Elf, String> elf : map.entrySet()) {
            
			Elf current = elf.getKey();

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
		int result = total - map.size();
		
		return result;
	}

	private void part2(ArrayList<String> data){

	}

	private void printMap(HashMap<Elf, String> map, int yMIN, int yMAX, int xMIN, int xMAX) {

		int xOffset = 0;
		int yOffset = 0;

		if (yMIN < 0){
			yOffset = Math.abs(yMIN);
			yMAX += yOffset;
		}

		if (xMIN < 0){
			xOffset = Math.abs(xMIN);			
			xMAX += xOffset;
		}

		String[][] plot = new String[yMAX][xMAX];

		for (int i = 0; i < yMAX; i++){

			for (int j = 0; j < xMAX; j++){

				plot[i][j] = ".";

			}

		}

		for(Map.Entry<Elf, String> elf : map.entrySet()) {

			Elf current = elf.getKey();
			String value = elf.getValue();

			plot[current.getY()+yOffset][current.getX()+xOffset] = value;

		}

		for (int i = 0; i < yMAX; i++){

			for (int j = 0; j < xMAX; j++){

				System.out.print(plot[i][j]);

			}

			System.out.println();

		}

		System.out.println();

	}

}
