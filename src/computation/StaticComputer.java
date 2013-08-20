package computation;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * Gets passed the expected PIN and tries to guess the real PIN. <br>
 * @author Jay Wang
 *
 */
public class StaticComputer {
    

    private static int realPin; 
    private static int guessedPin;
    
    private static int[] guessedPinDigits = new int[4];
    public static int counter; 
    static String FILENAME = "results.txt";

    private static ArrayList<Integer> firstNumber;
    private static ArrayList<Integer> secondNumber;
    private static ArrayList<Integer> thirdNumber;
    private static ArrayList<Integer> fourthNumber;
    
    public static Set<Integer> used = new HashSet<Integer>();
    static FileOutputStream fos;
    
    static ArrayList<ArrayList<Integer>> master = new ArrayList<ArrayList<Integer>>();
    
    
    public StaticComputer() {
        
    }
    
    public static void calculate(int rp, int gp, BufferedReader reader, Context context) throws IOException {
        
        fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                
        counter = 0;
        realPin = rp;
        guessedPin = gp;
        splitPin();
        populateArrayLists(reader);
        firstNumber = master.get(guessedPinDigits[0]);
        secondNumber = master.get(guessedPinDigits[1]);
        thirdNumber = master.get(guessedPinDigits[2]);
        fourthNumber = master.get(guessedPinDigits[3]); 
        
        String result = "";
        for (int i = 0; i < 10; i++) {
            result += firstNumber.get(i).toString();
            for (int j = 0; j < 10; j++) {
                result += secondNumber.get(j).toString();
                for (int k = 0; k < 10; k++) {
                    result += thirdNumber.get(k).toString();
                    for (int l = 0; l < 10; l++) {
                        result += fourthNumber.get(l).toString();
                        counter++;
                        if (testResult(Integer.parseInt(result))) {
                            String output = "**" + result + "**";
                            fos.write(output.getBytes());
                            return;
                        }
                        else {
                            String output = result + ",";
                            fos.write(output.toString().getBytes());
                            result = result.substring(0, result.length() - 1);
                        }
                    }
                    result = result.substring(0, result.length() - 1);
                }
                result = result.substring(0, result.length() - 1);
            }
            result = result.substring(0, result.length() - 1);
        }
        fos.close();
    }
    
    
    public static void calculate_updated(int rp, int gp, BufferedReader reader, Context context) throws IOException {
        
        fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            
        realPin = rp;
        guessedPin = gp;
        Log.i("Guessed Pin:", ""+gp);
        splitPin();
        master.clear();
        populateArrayLists(reader);
        firstNumber = master.get(guessedPinDigits[0]);
        Log.i("firstNumber:", firstNumber.toString());
        secondNumber = master.get(guessedPinDigits[1]);
        thirdNumber = master.get(guessedPinDigits[2]);
        fourthNumber = master.get(guessedPinDigits[3]); 
        used.clear();

        String result = "";
        int increment = 0;
        counter = 0;
        while (used.size() < 10000) {
            increment++;
            for (int i = 0; i < increment; i++) {
                result += firstNumber.get(i).toString();
                for (int j = 0; j < increment; j++) {
                    result += secondNumber.get(j).toString();
                    for (int k = 0; k < increment; k++) {
                        result += thirdNumber.get(k).toString();
                        for (int l = 0; l < increment; l++) {
                            result += fourthNumber.get(l).toString();
                            if (!used.contains(Integer.parseInt(result))) {
                                counter++;
                                if (testResult(Integer.parseInt(result))) {
                                    String output = "**" + result + "**";
                                    fos.write(output.getBytes());
                                    return;
                                }
                                else {
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
    }
    
    
    
    private static boolean testResult(int guess) {
        used.add(guess);
        if (realPin == guess) {
            return true;
        }
        return false;
    }
    
    private static void splitPin() {
        int tempPin = guessedPin;
        int i = 3;        
        
        guessedPinDigits[0] = 0;
        guessedPinDigits[1] = 0;
        guessedPinDigits[2] = 0;
        guessedPinDigits[3] = 0;
         
        while (tempPin > 0) {
            guessedPinDigits[i] = tempPin % 10; 
            tempPin = tempPin / 10;
            i--;
        }
    }
    
    private static void populateArrayLists(BufferedReader reader) throws IOException {
        
        String line;
        while ((line = reader.readLine()) != null) {
            master.add(processLine(line.split(", ")));
        }
        reader.close();        
    }
    
    private static ArrayList<Integer> processLine (String[] strings) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (String str : strings) {
            ret.add(Integer.parseInt(str.trim()));// Exception in this line
        }
        return ret;
    }
    
    
}
