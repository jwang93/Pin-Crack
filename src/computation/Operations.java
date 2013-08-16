package computation;
import java.io.IOException;
import java.util.ArrayList;

public class Operations {

    private int counter = 0;
    ArrayList<ArrayList<Integer>> master = new ArrayList<ArrayList<Integer>>();
    
   

    public void splitLists (String line) {
        ArrayList<Integer> elements = processLine(line.split(","));
        master.add(elements);
    }
    
    public ArrayList<ArrayList<Integer>> getMaster() {
        return master;
    }

    private ArrayList<Integer> processLine (String[] strings) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (String str : strings) {
            ret.add(Integer.parseInt(str.trim()));// Exception in this line
        }
        return ret;
    }
}
