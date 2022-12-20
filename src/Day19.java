import java.util.ArrayList;

public class Day19 {

	ArrayList<String> testinput;
	ArrayList<String> input;

	Day19(ArrayList<String> testinput, ArrayList<String> input) {

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

		ArrayList<Blueprint> blueprints= new ArrayList<>();
		int sum = 0;
		int time = 24;
		int orebots = 1;

		parseData(data, blueprints);

		for (Blueprint blueprint : blueprints) {
			blueprint.setQuality(simulate(blueprint, time, orebots));
		}

		for (Blueprint blueprint : blueprints) {
			sum+= blueprint.getQuality();
		}

		System.out.println("The quality level of all blueprints are: " + sum);

	}

	private void parseData(ArrayList<String> data, ArrayList<Blueprint> blueprints){

		for (String line : data){

			line = line.replace("Blueprint ", "");
			line = line.replace(": Each ore robot costs ", " ");
			line = line.replace(" ore. Each clay robot costs ", " ");
			line = line.replace(" ore. Each obsidian robot costs ", " ");
			line = line.replace(" ore and ", " ");
			line = line.replace(" clay. Each geode robot costs ", " ");
			line =  line.replace("obsidian.", "" );

			String[] numbers = line.split(" ");

			Blueprint temp = new Blueprint(numbers);
			blueprints.add(temp);

		}

	}

	private int simulate(Blueprint blueprint, int time, int orebots){

		int ore = 0;
		int clay = 0;
		int obsidian = 0;
		int geode = 0;

		int claybots = 0;
		int obsidianbots = 0;
		int geodebots = 0;


		System.out.println("Blueprint: " + blueprint.getId() + " Orebot: " + blueprint.getOreBotCost() 
							+ " Claybot: " + blueprint.getClayBotCost() + " Obsidianbot: " 
							+ blueprint.getObsidianBotCost()[0] + "," + blueprint.getObsidianBotCost()[1] 
							+ " Geodebot: " + blueprint.getGeodeBotCost()[0] + "," + blueprint.getGeodeBotCost()[1]);

		for (int i = 1; i <= time; i++){

			// while the factory is doing stuff, bots are fetching

			ore += orebots;
			clay += claybots;
			obsidian += obsidianbots;
			geode += geodebots;

			// factorio, don't use stuff we don't have yet!
			// dumb factory, doesn't save

			if ((ore-orebots >= blueprint.getGeodeBotCost()[0]) && (obsidian-obsidianbots >= blueprint.getGeodeBotCost()[1])){
				
				geodebots++;
				ore -= blueprint.getGeodeBotCost()[0];
				obsidian -= blueprint.getGeodeBotCost()[1];

			} else if ((ore-orebots >= blueprint.getObsidianBotCost()[0]) && (clay-claybots >= blueprint.getObsidianBotCost()[1])){

				obsidianbots++;
				ore -= blueprint.getObsidianBotCost()[0];
				clay -= blueprint.getObsidianBotCost()[1];

			} else if (ore-orebots >= blueprint.getClayBotCost()){

				claybots++;
				ore -= blueprint.getClayBotCost();

			} else if (ore-orebots >= blueprint.getOreBotCost()){
				
				orebots++;
				ore -= blueprint.getOreBotCost();
			}

			// TODO: Predict saving
			// TODO: Branch out for smartest choice?
			//		Save / Don't save
			//		Which bot gives me the best value? 

		}

		System.out.println("Got " + ore + " ore");
		System.out.println("Got " + clay + " clay");
		System.out.println("Got " + obsidian + " obsidian");
		System.out.println("Got " + geode + " geodes");


		return geode * blueprint.getId();

	}

	private void part2(ArrayList<String> data){

	}

}
