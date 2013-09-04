package computation;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.content.Context;

/**
 * Logic that handles PIN Guessing
 * @author Jay Wang
 */

public class Computer {

	private static int[] guessedPinDigits = new int[4];
	private static String FILENAME = "results.txt";
	private final static String EMPTY_STRING = "";
	private static Set<Integer> used = new HashSet<Integer>();
	private static FileOutputStream fos;
	private static ArrayList<ArrayList<Integer>> orderings = new ArrayList<ArrayList<Integer>>();
	private static int[] increments = new int[4];

	public static int counter;

	/**
	 * Returns the number of tries it took to guess the PIN.
	 * 
	 * @param realPin
	 * @param guessedPin
	 * @param reader
	 * @param context
	 * @param confidence
	 * @throws IOException
	 */
	public static int calculate(int realPin, int guessedPin, BufferedReader reader,
			Context context, int[] confidence) throws IOException {

		fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		orderings.clear();
		used.clear();
		splitPin(guessedPin);
		populateArrayLists(reader);
		counter = 0;

		for (int j = 0; j < confidence.length; j++) {
			increments[j] = getIncrement(confidence[j]);
		}
		
		while (used.size() < 10000) {
			increment(confidence);
			String result = EMPTY_STRING;

			for (int i = 0; i < increments[0]; i++) {
				result += orderings.get(guessedPinDigits[0]).get(i).toString();
				for (int j = 0; j < increments[1]; j++) {
					result += orderings.get(guessedPinDigits[1]).get(j).toString();
					for (int k = 0; k < increments[2]; k++) {
						result += orderings.get(guessedPinDigits[2]).get(k).toString();
						for (int l = 0; l < increments[3]; l++) {
							result += orderings.get(guessedPinDigits[3]).get(l).toString();
							if (!used.contains(Integer.parseInt(result))) {
								counter++;
								if (testResult(Integer.parseInt(result), realPin)) {
									String output = result;
									fos.write(output.getBytes());
									return counter;
								} else {
									String output = result + ",";
									fos.write(output.toString().getBytes());
								}
							}
							result = result.substring(0, result.length() - 1);
						}
						result = result.substring(0, result.length() - 1);
					}
					result = result.substring(0, result.length() - 1);
				}
				result = result.substring(0, result.length() - 1);
			}
		}
		fos.close();
		return -1;
	}
	
	/**
	 * Helper method used to split the PIN from a String into an int[]
	 */
	private static void splitPin(int guessedPin) {
		int tempPin = guessedPin;
		int i = 3;

		for (int j = 0; j < guessedPinDigits.length; j++) {
			guessedPinDigits[j] = 0;
		}
		
		while (tempPin > 0) {
			guessedPinDigits[i] = tempPin % 10;
			tempPin = tempPin / 10;
			i--;
		}
	}
	
	/**
	 * Helper function used for parsing the orderings.txt file
	 */
	private static void populateArrayLists(BufferedReader reader)
			throws IOException {

		String line;
		while ((line = reader.readLine()) != null) {
			orderings.add(processLine(line.split(", ")));
		}
		reader.close();
	}

	/**
	 * Helper function used for parsing the orderings.txt file
	 */
	private static ArrayList<Integer> processLine(String[] strings) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (String str : strings) {
			ret.add(Integer.parseInt(str.trim()));// Exception in this line
		}
		return ret;
	}
	
	/**
	 * Helper method that returns the appropriate increment based on user confidence
	 */
	private static int getIncrement(int confidence) {
		switch (confidence) {
		case 5:
			return 1;
		case 4:
			return 3;
		case 3:
			return 5;
		case 2:
			return 7;
		default:
			return 10;
		}
	}
	
	/**
	 * Helper method that tests the computer's guess against the correct PIN
	 */
	private static boolean testResult(int computerGuess, int realPin) {
		used.add(computerGuess);
		if (realPin == computerGuess) {
			return true;
		}
		return false;
	}
	
	/**
	 * Helper method that returns the index of the number with lowest confidence
	 */
	private static int getLowestConfidence(int[] confidence) {
		int index = -1;
		int min = 5;
		
		for (int i = 0; i < confidence.length; i++) {
			if (increments[i] >= 10) {
				continue;
			}
			if (confidence[i] <= min) {
				min = confidence[i];
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Helper method that adjusts the increments based on the user's confidence of each digit
	 */
	private static void increment(int[] confidence) {
		int index = getLowestConfidence(confidence);
		if (index != -1) {
			increments[index]++;
		}
	}
}
