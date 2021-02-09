package HomeWork2;

public class QueueGenerator {

	///////////////////// How to call me: /////////////////////
	//QueueGenerator gen = QueueGenerator.getInstance();
	// gen.getNumber()....

	private int number;
	private static QueueGenerator single_instance = null;

	// private constructor restricted to this class itself
	private QueueGenerator() {
		this.number = 0;
	}

	public int getNumber() {
		return number; // returns last use of generator
	}

	public static QueueGenerator getInstance(){
		if(single_instance==null)
			single_instance = new QueueGenerator();

		return single_instance;
	}

	public int getNext() {
		return ++number;
	}


	@Override
	public String toString() {
		return "Enqueue number = " + number;
	}


}
