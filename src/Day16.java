import java.util.ArrayList;

public class Day16 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day16(ArrayList<String> testinput, ArrayList<String> input) {

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

		long minutes = 30;
		ArrayList<Valve> valves = new ArrayList<>();

		parseData(data, valves);

		print(valves);

		long flow = runSimulation(valves, minutes);			

		System.out.println("The maximum amount of flow after " + minutes + " minutes is " + flow);

	}

	private long runSimulation(ArrayList<Valve> valves, long minutes){

		long flow = 0;

		for (int i = 0; i < minutes; i++){

			// TODO: All possibilities while retaining state - without slowing down too much

		}

		return flow;

	}

	private void print(ArrayList<Valve> valves){

		for (Valve valve : valves) {
			
			ArrayList<Valve> tunnel = valve.getTunnelList();

			System.out.println("Valve: " + valve.getName() + " has flowrate: " + valve.getFlowRate());
			System.out.print("Tunnel(s): ");

			for (int i = 0; i < tunnel.size(); i++){
				if (i == tunnel.size()-1){
					System.out.print(tunnel.get(i).getName());
				} else {
					System.out.print(tunnel.get(i).getName() + " , ");
				}	
			}

			System.out.println();
			System.out.println();

		}

	}

	private void parseData(ArrayList<String> data, ArrayList<Valve> valves) {

		for (String line : data){

			line = line.replace("Valve ", "");
			line = line.replace(" has flow rate=", " ");
			line = line.replace("; tunnel leads to valve ", " ");
			line = line.replace("; tunnels lead to valves ", " ");
			line = line.replace(", ", ",");

			String[] splits = line.split(" ");
			

			Valve temp = new Valve(splits[0], Long.parseLong(splits[1]), splits[2]);
			valves.add(temp);

		}

		for (Valve valve : valves){

			String tunnels[] = valve.getTunnelString().split(",");

			for (int i = 0; i < tunnels.length; i++){

				String search = tunnels[i];
				
				for (Valve to : valves){

					if (to.getName().equals(search)){
						valve.addTunnel(to);
						to.addTunnel(valve);
						break;
					}

				}

			}

		}

	}

	private void part2(ArrayList<String> data){

	}

}
