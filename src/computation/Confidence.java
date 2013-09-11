package computation;

/**
 * This object is used to sort the indexes of the guessed PIN digits. 
 * @author Jay Wang
 *
 */
public class Confidence implements Comparable<Confidence>{
	
	protected int index;
	private int confidence;
	
	public Confidence(int index, int confidence) {
		this.index = index;
		this.confidence = confidence;
	}

	@Override
	public int compareTo(Confidence arg0) {
		if (arg0.confidence > this.confidence) {
			return 1;
		} else if (arg0.confidence < this.confidence) {
			return -1;
		}
		return 0;
	}
}
