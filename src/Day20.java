import java.util.ArrayList;

public class Day20 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day20(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Node> original = new ArrayList<>();
		Node start = parseData(data, original, 1);

		moveNodes(original);

		long sum = getFinal(1000, 2000, 3000, start);

		System.out.println("The sum of the three coordinates is " + sum);

	}

	private long getFinal(int first, int second, int third, Node start) {

		long a = 0;
		long b = 0;
		long c = 0;

		while (start.getValue() != 0){
			start = start.getNext();
		}

		for (int i = 1; i <= third; i++){

			start = start.getNext();

			if (i == first ){ a = start.getValue(); }
			if (i == second ){ b = start.getValue(); }
			if (i == third ){ c = start.getValue(); }

		}

		return (a + b + c);

	}

	private void moveNodes(ArrayList<Node> original ) {

		for (Node current : original){
			
			Node insert = current;
			long value = current.getValue() % (original.size()-1);

			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());

			if (value == 0){

				current.getNext().setPrevious(insert);
				current.getPrevious().setNext(insert);

			} else if (value < 0){ 

				for (long i = 0; i > value; i--){
					insert = insert.getPrevious();
				} 

				Node before = insert.getPrevious();

				current.setPrevious(before);		
				before.setNext(current);

				current.setNext(insert);
				insert.setPrevious(current);

			} else {

				for (long i = 0; i < value; i++){
					insert = insert.getNext();
				}

				Node after = insert.getNext();

				current.setNext(after);
				after.setPrevious(current);
				
				current.setPrevious(insert);		
				insert.setNext(current);

			}

			current.setMoved();

		}
		
	}

	private Node parseData(ArrayList<String> data, ArrayList<Node> original, long mul){

		Node start = null;
		Node last = null;
		
		String temp = data.get(0);
		long value = Integer.parseInt(temp) * mul;

		start = new Node(value, null, null);
		last = start;
		original.add(start);

		for (int i = 1; i < data.size(); i++) {

			temp = data.get(i);
			
			if (! temp.isBlank()){

				value = Integer.parseInt(temp) * mul;
				Node current = new Node(value, last, null);
				last.setNext(current);

				original.add(current);
				last = current;

			}
			
		}

		last.setNext(start);
		start.setPrevious(last);

		return start;
	}

	private void part2(ArrayList<String> data){

		ArrayList<Node> original = new ArrayList<>();
		Node start = parseData(data, original, 811589153);

		for (int i = 0; i < 10; i++){
			moveNodes(original);
		}	

		long sum = getFinal(1000, 2000, 3000, start);

		System.out.println("The sum of the three coordinates is " + sum);

	}

}
