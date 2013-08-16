package computation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Application;
import android.content.Context;


/**
 * <b>Secretary</b> - manage file saving and loading <br><br>
 * Also has other functionality such as writing to a file, counting lines in a file, removing lines
 * from a file <br>
 * <b>Functions:<b>
 * <li>write(String value) --> allows you to write value to a session file</li>
 * <li>count(String fileName, String patternToMatch) --> allows you to count how many lines match a
 * given regex pattern</li>
 * <ul>remove(String fileName, String remove) --> allows you to remove String remove from a file </ul>
 * <ul>saveSession(String fileName) --> allows you to save a session file onto your disk</ul>
 * <ul>loadSession(String filename, String methodName, Object object) --> allows you to load a file from
 * your disk into your machine and perform</ul>
 * <ul>loadSession(String fileName, Invokeable object) --> allows you to pass in an anonymous function that 
 * implements invoke however you like</ul>
 * whatever method you want on it (that method must take a String as parameter).
 * 
 * @author Jay Wang
 */

public class Secretary extends Application {
    
    private static Context context;

    private FileWriter myFileWriter;
    private FileChannel sourceChannel;
    private FileChannel targetChannel;
    private final String FILE_PATH;
    private final String SESSION_FILE;
    private static final String TMP_FILE = "tmp.txt";
    
    public void onCreate() {
        super.onCreate();
        Secretary.context = getApplicationContext();
    }

    /**
     * To instantiate a Secretary, you need to feed the constructor two strings
     * String directory - the path of the directory where you want the generated file to be saved
     * String fileName - the name you want your file to be
     * 
     * @param directory
     * @param fileName
     * @throws IOException
     */
    public Secretary (String directory) throws IOException {

        FILE_PATH = directory;
        SESSION_FILE = TMP_FILE;
        myFileWriter = new FileWriter(FILE_PATH + SESSION_FILE);

    }

    /*
     * PUBLIC METHODS
     */

    /**
     * This will save the session.txt file into a file called fileName and save
     * that file in src/Files.
     * 
     * @param String fileName - name of the file (i.e. Example1.txt)
     * @throws IOException
     */
    public void saveSession (String fileName) throws IOException {
        save(SESSION_FILE, fileName);
    }

    /**
     * This will load the File fileName and iterate through each line of the file
     * allowing the user to do whatever operation he/she chooses based on what
     * method he/she sends over.
     * 
     * NOTE - this method must take a String as a parameter
     * 
     * The user can decide what to do which each line of the file:
     * parse it, print it to console, etc...
     * 
     * @param String fileName - name of the file (i.e. Example1.txt)
     * @param String methodName - the name of the method you want to invoke on the line
     * @param Object object - the class that has the method you want to invoke
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void loadSession (String fileName, String methodName, Object object)
                                                                               throws SecurityException,
                                                                               NoSuchMethodException,
                                                                               IllegalArgumentException,
                                                                               IOException,
                                                                               IllegalAccessException,
                                                                               InvocationTargetException {

        @SuppressWarnings("rawtypes")
        Class[] parameterTypes = { String.class };
        Method method = object.getClass().getMethod(methodName, parameterTypes);
        load(fileName, method, object);
    }

    /**
     * This will load the File fileName and iterate through each line of the file
     * allowing the user to do whatever operation he/she chooses based on what
     * method he/she sends over.
     * 
     * NOTE - this method must take an Invokeable Object as a parameter
     * 
     * The user can decide what to do which each line of the file:
     * parse it, print it to console, etc...
     * 
     * @param String fileName - name of the file (i.e. Example1.txt)
     * @param Invokeable object - the anonymous class (of type Invokeable) that you create
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void loadSession (String fileName, Invokeable object)
                                                                throws IOException,
                                                                SecurityException,
                                                                NoSuchMethodException,
                                                                IllegalArgumentException,
                                                                IllegalAccessException,
                                                                InvocationTargetException {

        InputStream inputStream = getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        
        String line;
        while ((line = reader.readLine()) != null) {
            object.invoke(line);

        }
        reader.close();

    }

    /**
     * This will write the string parameter to the session.txt file.
     * 
     * @param String string - the string that is going to be written to the file
     * @throws IOException
     */
    public void write (String string) throws IOException {
        write(myFileWriter, string);
    }

    /**
     * This method will count of the number of occurrences of a given line pattern
     * in a file. The user needs to send a patternToMatch (regular expression). This method
     * will return the number of occurrences of lines in the file that match the given
     * pattern.
     * 
     * @param String fileName - name of the file (i.e. Example1.txt)
     * @param String patternToMatch - the pattern that you want a line to match to
     * @return An integer of the number of occurrences of search in the file.
     * @throws IOException
     */
    public int count (String fileName, String patternToMatch) throws IOException {
        String line;
        int count = 0;
        InputStream inputStream = getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Pattern pattern = Pattern.compile(patternToMatch);

        while ((line = reader.readLine()) != null) {
            Matcher match = pattern.matcher(line);
            if (match.find()) count++;
        }
        reader.close();
        return count;
    }

    /**
     * The method will remove all occurrences of a given line from a file. May
     * be useful if the user is trying to filter results.
     * 
     * @param String fileName - name of the file (i.e. Example1.txt)
     * @param String remove - the line that is to be removed from a file
     * @throws IOException
     */
    public void remove (String fileName, String remove) throws IOException {

        InputStream inputStream = getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        
        String line;
        FileWriter writer = new FileWriter(FILE_PATH + TMP_FILE);
        while ((line = reader.readLine()) != null) {
            if (!line.equals(remove)) write(writer, line);
        }
        reader.close();
        save(TMP_FILE, fileName);
        new File(FILE_PATH + TMP_FILE).delete();

    }

    /*
     * PRIVATE METHODS
     */

    private void save (String fileToCopy, String fileToSave) throws IOException {

        File copy = new File(FILE_PATH + fileToCopy);
        File save = new File(FILE_PATH + fileToSave);
        sourceChannel = new FileInputStream(copy).getChannel();
        targetChannel = new FileOutputStream(save).getChannel();
        targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        if (sourceChannel != null) {
            sourceChannel.close();
        }
        if (targetChannel != null) {
            targetChannel.close();
        }
        copy.deleteOnExit();

    }

    private void write (FileWriter writer, String string) throws IOException {
        writer.write(string + System.getProperty("line.separator"));
        writer.flush();

    }

    private BufferedReader getReader (String fileName) throws FileNotFoundException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(FILE_PATH + fileName));
        return reader;
    }

    private void load (String fileName, Method method, Object object)
                                                                     throws IllegalArgumentException,
                                                                     IOException,
                                                                     IllegalAccessException,
                                                                     InvocationTargetException {
        InputStream inputStream = getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            method.invoke(object, line);
        }
        reader.close();
    }

    public static interface Invokeable {
        public void invoke (String line);
    }
}
