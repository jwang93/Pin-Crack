package computation;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import android.content.Context;
import android.util.Log;

/**
 * Logic that handles PIN Guessing. All algorithmic modifications should be made
 * here.
 * 
 * @author Jay Wang
 */

public class Computer {

	private static int[] guessedPinDigits = new int[4];
	private static String FILENAME = "results.txt";
	private final static String EMPTY_STRING = "";
	private final static int MAX_GUESSES = 10000;
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
	public static int calculate(int realPin, int guessedPin,
			BufferedReader reader, Context context, int[] confidence)
			throws IOException {

		fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		orderings.clear();
		used.clear();
		splitPin(guessedPin);
		populateArrayLists(reader);
		counter = 0;

		int[] order = sortPositions(confidence);
//		order[0] = 3;
//		order[1] = 2;
//		order[2] = 1;
//		order[3] = 0;
		
		char[] build = new char[4];

		for (int j = 0; j < confidence.length; j++) {
			increments[j] = getIncrement(confidence[j]);
		}

		while (used.size() < MAX_GUESSES) {
			increment(confidence);
			String result = EMPTY_STRING;
			for (int i = 0; i < increments[order[0]]; i++) {
				build[order[0]] = getChar(order[0], i);
				for (int j = 0; j < increments[order[1]]; j++) {
					build[order[1]] = getChar(order[1], j);
					for (int k = 0; k < increments[order[2]]; k++) {
						build[order[2]] = getChar(order[2], k);
						for (int l = 0; l < increments[order[3]]; l++) {
							build[order[3]] = getChar(order[3], l);
							result = appendChars(build);
							if (!used.contains(Integer.parseInt(result))) {
								counter++;
					            used.add(Integer.parseInt(result));
								if (testResult(Integer.parseInt(result),realPin)) {
									String output = result;
									fos.write(output.getBytes());
									return counter;
								} else {
									String output = result + ",";
									fos.write(output.toString().getBytes());
								}
							}
						}
					}
				}
			}
		}
		fos.close();
		return -1;
	}

	/**
	 * Helper method to encapsulate the disgusting creation of a String from
	 * characters
	 * 
	 * @param build
	 */
	private static String appendChars(char[] build) {
		return new StringBuilder().append(build[0]).append(build[1])
				.append(build[2]).append(build[3]).toString();
	}

	/**
	 * Helper method to encapsulate the procurement of a character to place in
	 * build array
	 * 
	 * @param position
	 * @param index
	 * @return
	 */
	private static char getChar(int position, int index) {
		return orderings.get(guessedPinDigits[position]).get(index).toString()
				.charAt(0);
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
     * Helper method that tests the computer's guess against the correct PIN
     */
    private static boolean testResult(int computerGuess, int realPin) {
            if (realPin == computerGuess) {
                    return true;
            }
            return false;
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
	 * Helper method that returns the appropriate increment based on user
	 * confidence
	 */
	public static int getIncrement(int confidence) {
		switch (confidence) {
		case 5:
			return 1;
		case 4:
			return 2;
		case 3:
			return 4;
		case 2:
			return 6;
		case 1:
			return 8;
		default:
			return 10;
		}
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
	 * Helper method that adjusts the increments based on the user's confidence
	 * of each digit
	 */
	private static void increment(int[] confidence) {
		int index = getLowestConfidence(confidence);
		if (index != -1) {
			increments[index]++;
		}
	}

	/**
	 * Helper method to reorder the for loop in calculate. Bases position on the
	 * confidence of the digit. Larger confidence means higher position on the
	 * loop.
	 * 
	 * @param confidence
	 * @return
	 */
	public static int[] sortPositions(int[] confidence) {
		int[] ret = new int[4];

		List<Confidence> list = new ArrayList<Confidence>();
		for (int i = 0; i < confidence.length; i++) {
			list.add(new Confidence(i, confidence[i]));
		}
		Collections.sort(list);

		for (int j = 0; j < confidence.length; j++) {
			ret[j] = list.get(j).index;
		}
		
		return ret;
	}
}