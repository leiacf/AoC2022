import java.io.*;
import java.util.*;

public class Main {

	static int day = 2;

	static StringBuilder inputpath = new StringBuilder();
	static StringBuilder testpath = new StringBuilder();
	static ArrayList<String> input = new ArrayList<String>();
	static ArrayList<String> testinput = new ArrayList<String>();

	public static void main(String[] args){

		setup();
		start(day);

	}

	private static void setup(){

		inputpath.append("input\\");
		inputpath.append(String.valueOf(day));
		inputpath.append(".txt");

		testpath.append("input\\");
		testpath.append("test");
		testpath.append(String.valueOf(day));
		testpath.append(".txt");

		File testFile = new File(testpath.toString());
		File inputFile = new File(inputpath.toString());

		try {
			BufferedReader reader = new BufferedReader(new FileReader(testFile));

			String line = null;

			try {

				while ((line = reader.readLine()) != null) {

					testinput.add(line);

				}

			} catch (IOException e) {
				System.out.println(e.toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));

			String line = null;

			try {

				while ((line = reader.readLine()) != null) {

					input.add(line);

				}

			} catch (IOException e) {
				System.out.println(e.toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}

	}

	private static void start(int day){

		switch (day){

			case 1:
				Day1 day1 = new Day1(testinput, input);
				day1.start();
				break;
			case 2:
				Day2 day2 = new Day2(testinput, input);
				day2.start();
				break;
			case 3:
				Day3 day3 = new Day3(testinput, input);
				day3.start();
				break;
			case 4:
				Day4 day4 = new Day4(testinput, input);
				day4.start();
				break;
			case 5:
				Day5 day5 = new Day5(testinput, input);
				day5.start();
				break;
			case 6:
				Day6 day6 = new Day6(testinput, input);
				day6.start();
				break;
			case 7:
				Day7 day7 = new Day7(testinput, input);
				day7.start();
				break;
			case 8:
				Day8 day8 = new Day8(testinput, input);
				day8.start();
				break;
			case 9:
				Day9 day9 = new Day9(testinput, input);
				day9.start();
				break;
			case 10:
				Day10 day10 = new Day10(testinput, input);
				day10.start();
				break;
			case 11:
				Day11 day11 = new Day11(testinput, input);
				day11.start();
				break;
			case 12:
				Day12 day12 = new Day12(testinput, input);
				day12.start();
				break;
			case 13:
				Day13 day13 = new Day13(testinput, input);
				day13.start();
				break;
			case 14:
				Day14 day14 = new Day14(testinput, input);
				day14.start();
				break;
			case 15:
				Day15 day15 = new Day15(testinput, input);
				day15.start();
				break;
			case 16:
				Day16 day16 = new Day16(testinput, input);
				day16.start();
				break;
			case 17:
				Day17 day17 = new Day17(testinput, input);
				day17.start();
				break;
			case 18:
				Day18 day18 = new Day18(testinput, input);
				day18.start();
				break;
			case 19:
				Day19 day19 = new Day19(testinput, input);
				day19.start();
				break;
			case 20:
				Day20 day20 = new Day20(testinput, input);
				day20.start();
				break;
			case 21:
				Day21 day21 = new Day21(testinput, input);
				day21.start();
				break;
			case 22:
				Day22 day22 = new Day22(testinput, input);
				day22.start();
				break;
			case 23:
				Day23 day23 = new Day23(testinput, input);
				day23.start();
				break;
			case 24:
				Day24 day24 = new Day24(testinput, input);
				day24.start();
				break;
			case 25:
				Day25 day25 = new Day25(testinput, input);
				day25.start();
				break;
			default:
				break;
		}

	}

}
