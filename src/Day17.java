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
		part1(input);

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
		ArrayList<String> tower = new ArrayList<>();

		StringBuilder floor = new StringBuilder();

		for (int i = 0; i < width; i++){
			floor.append("_");
		}

		tower.add(floor.toString());

		String jetstream = data.get(0);

		for (int i = 0; i < times; i++){
			playTetris(tower, width, edge, jetstream, i);
		}

		for (String string : tower){

			System.out.println(string);

		}

		System.out.println("The tower height is = " + tower.size());

	}

	private void playTetris(ArrayList<String> tower, int width, int edge, String jetstreamm, int times){

		ArrayList<String> shape = getShape(times, width, edge);
		StringBuilder empty = new StringBuilder();

		for (int i = 0; i < width; i++){
			empty.append(" ");
		}

		for (int i = 0; i < 3; i++){
			tower.add(0, empty.toString());
		}

		// TODO: 2 from the left edge
		// TODO: Implement jetstreams - Remember to wrap if we run out!

		for (int i = shape.size()-1; i >= 0; i--){
			tower.add(0, shape.get(i));
		}

	}

	private ArrayList<String> getShape(int times, int width, int edge){

		ArrayList<ArrayList<String>> shapes = allShapes(width, edge);

		return shapes.get(times % shapes.size());

	}

	private ArrayList<ArrayList<String>> allShapes(int width, int edge){

		ArrayList<ArrayList<String>> allShapes = new ArrayList<>();

		ArrayList<String> minus = new ArrayList<>();
		ArrayList<String> plus = new ArrayList<>();
		ArrayList<String> j = new ArrayList<>();
		ArrayList<String> longone = new ArrayList<>();
		ArrayList<String> square = new ArrayList<>();
		
		// minus

		minus.add("####");

		// plus

		plus.add(" # ");
		plus.add("###");
		plus.add(" # ");

		// j

		j.add("  #");
		j.add("  #");
		j.add("###");

		// longone

		longone.add("#");
		longone.add("#");
		longone.add("#");
		longone.add("#");

		// square

		square.add("##");
		square.add("##");

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
