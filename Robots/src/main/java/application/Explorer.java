package main.java.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import main.java.controller.Controller;
import main.java.entity.Heading;
import main.java.entity.Location;
import main.java.entity.Move;
import main.java.robot.Robot;

public class Explorer {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Please enter the command file name");
		String input = scanner.nextLine();
		File file = new File(input);
		List<Robot> robots = new ArrayList<Robot>();
		try {
			robots = createRobots(file);
		} catch (IOException e) {
			System.out.println("Failed to read file: testfile.txt" + e);
		}

		// submit tasks
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Boolean>> resultsList = new ArrayList<Future<Boolean>>();

		for (Robot robot : robots) {
			Future<Boolean> future = executor.submit(robot);
			resultsList.add(future);
		}
		// check results
		for (Future<Boolean> fut : resultsList) {

			try {
				System.out.println(fut.get());
			} catch (InterruptedException e) {
				System.out.println(e);
			} catch (ExecutionException e) {
				System.out.println(e);
			}

		}
		// shut down the executor service now
		executor.shutdown();

		scanner.close();
	}

	private static List<Robot> createRobots(File file) throws IOException {
		List<Robot> robots = new ArrayList<Robot>();
		Controller controller = new Controller(10, 10);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = null;
		int i = 0;
		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(" ");
			Move[] moves = getMoves(tokens[3]);
			Robot robot = new Robot(moves, controller, new Location(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
					Heading.valueOf(tokens[2])), 3, i);
			robots.add(robot);
			i++;
		}

		br.close();
		return robots;
	}

	/**
	 * Breaks the moves string into an array of Move
	 * @param m
	 * @return
	 */
	private static Move[] getMoves(String m) {
		String[] s = m.split("");
		Move[] moves = new Move[s.length];
		for (int i = 0; i < s.length; i++) {
			moves[i] = Move.valueOf(s[i]);
		}
		return moves;
	}
}
